package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import game.entity.mob.NPC;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;
import game.level.Level;
import game.level.SpawnLevel;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private static int width = 333;
	private static int height = width/16*9;
	private static int scale = 3;
	private int xp = 0, yp = 0, fps = 0, ups = 0, deadtime=0,menuIndex;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level Level;
	private boolean running = false, debug = false;
	private static boolean pause=false;
	private boolean cameraControl = true, cutScene = false,loaded=false;
	private int xScroll = 0, yScroll = 0;
	private long DebugSwitch=0, cameraSwitch=0,loadtime=0,menuuse;
	private Screen screen;
	
	//BufferedImage is the finalised frame to be displayed
	private BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
	
	//Array of pixels to be printed to the screen
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//setup of the windows dimensions
	public Game(){
		Dimension size = new Dimension(width*scale,height*scale);
		setPreferredSize(size);
		screen = new Screen(width,height);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
		Level = new SpawnLevel("/Maps/test.png","/Maps/testEnemy.png",key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static int getWindowWidth(){
		return (width*scale);
	}
	
	public static int getWindowHeight(){
		return (height*scale);
	}

	//when the game starts begin the thread
	private synchronized void start(){
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	//close the game fully
	public synchronized void stop() {
		running = false;
		try{
		thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		//update all the pixels positions at 60fps
		long lt = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running){
			
			long now = System.nanoTime();
			delta += (now - lt) / ns;
			lt = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			
			//display the correct pixels to the screen
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				ups = updates;
				fps = frames;
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void update(){
		key.update();
		if(Level.Players.get(0).dead){
			deadtime = deadtime<Screen.height?deadtime+=1:Screen.height;
		}else{
			deadtime = (deadtime>0?deadtime-3>0?deadtime-=3:0:0);
		}
		if(!pause){
			if(deadtime<120){
				Level.update();
			}
			Level.Players.get(0).update();
		}else{
			
		}
		if(key.space && cutScene){
			if(System.currentTimeMillis() > cameraSwitch + 250){
				cameraControl = true;
				cutScene=false;
				pause=false;
				cameraSwitch=System.currentTimeMillis();
			}
		}
		
		if(key.space && !cutScene){
			if(System.currentTimeMillis() > cameraSwitch + 250){
				cameraControl = false;
				cutScene=true;
				pause=true;
				cameraSwitch=System.currentTimeMillis();
			}
		}
		
		if(pause){
			if(menuIndex==0&&key.enter){
				System.out.println("save1");
				Level.Players.get(0).save(screen,1);
			}
			if(menuIndex==1&&key.enter){
				System.out.println("save2");
				Level.Players.get(0).save(screen,2);
			}
			if(menuIndex==2&&key.enter){
				System.out.println("save3");
				Level.Players.get(0).save(screen,3);
			}
			if(System.currentTimeMillis() > menuuse + 100){
				if(key.down&&menuIndex>=0&&menuIndex<2){
					menuIndex++;
					menuuse=System.currentTimeMillis();
				}
				else if(key.up&&menuIndex>0&&menuIndex<=2){
					menuIndex--;
					menuuse=System.currentTimeMillis();
				}
			}
		}
				
		if (key.keyJ && !cutScene){
			cameraControl = false;
			xScroll-=5;
		}
		
		if (key.keyK && !cutScene){
			cameraControl = false;
			yScroll+=5;
		}
		
		if (key.keyL && !cutScene){
			cameraControl = false;
			xScroll+=5;
		}
		
		if (key.keyI && !cutScene){
			cameraControl = false;
			yScroll-=5;
		}
		
		if(key.ctrl)
		{
			if(System.currentTimeMillis() > DebugSwitch + 250){
				debug = !debug;
				DebugSwitch = System.currentTimeMillis();
			}
		}
	}		

	//The games display
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		if(cameraControl && !cutScene){
			xScroll = Level.Players.get(0).x + 16 - (width / 2) + xp;
			yScroll = Level.Players.get(0).y + 16 - (height / 2) + yp;
		}
		
		Level.render(xScroll, yScroll, screen);
		
		//death fade
		screen.renderSprite(0, 0, new Sprite(Screen.width,deadtime<Screen.height?deadtime/2:Screen.height/2,0x000000), true);
		screen.renderSprite(0, deadtime<Screen.height?Screen.height-deadtime/2:Screen.height/2, new Sprite(Screen.width,deadtime/2<Screen.height/2?deadtime/2:Screen.height/2,0x000000), true);
		
		Level.Players.get(0).render(screen);

		//cut scene
		if(cutScene){
			screen.renderSprite(0, 0, new Sprite(Screen.width,20,0x000000), true);
			screen.renderSprite(0, Screen.height-20, new Sprite(Screen.width,20,0x000000), true);
		}
		
		//pause menu
		if(pause){
			screen.renderSheet(Screen.width/2-60, Screen.height/2-75, SpriteSheet.Pause, true);
			screen.renderSheet(Screen.width/2-(75/2), Screen.height/2-36, SpriteSheet.PauseButton, true);
			screen.renderSheet(Screen.width/2-(75/2), Screen.height/2, SpriteSheet.PauseButton, true);
			screen.renderSheet(Screen.width/2-(75/2), Screen.height/2+36, SpriteSheet.PauseButton, true);
			
			screen.renderSheet(Screen.width/2-(75/2), Screen.height/2-36+(menuIndex*36), SpriteSheet.PauseButtonA, true);
			if(!loaded){
				loaded=true;
			}
		}
		else{
			loaded = false;
		}
		
		//minimap
		screen.renderSprite(width-38, 0, new Sprite(38,height,0x000000), true);
		screen.renderSprite(width-37, 1, Sprite.mini,true);
		
		for(int i = 0; i < Level.Projectiles.size();i++){
			screen.renderSprite(width-35+(int)Level.Projectiles.get(i).x/64, (int)Level.Projectiles.get(i).y/64+3, new Sprite(1,1,0xbb0000), true);
		}
		for(int i = 0; i < Level.npcs.size();i++){
			screen.renderSprite(width-35+Level.npcs.get(i).x/64, Level.npcs.get(i).y/64+3, new Sprite(1,1,0xdd7700), true);
		}
		for(int i = 0; i < Level.items.size();i++){
			screen.renderSprite(width-35+Level.items.get(i).x/64, Level.items.get(i).y/64+3, new Sprite(1,1,0x0000bb), true);
		}
		
		screen.renderSprite(width-35+Level.Players.get(0).x/64, Level.Players.get(0).y/64+3, new Sprite(1,1,0xffff00), true);

		//inventory menu
		for(int i = 0; i < 3;i++){
			for(int o = 0; o < 3;o++){
				screen.renderSprite(width-36+(i*12), height-12-(o*12), Sprite.invInactive, true);
			}
		}

		if(Level.Players.get(0)!=null){
			screen.renderSprite(width-36+(Level.Players.get(0).getInvX()*12), height-36+(Level.Players.get(0).getInvY()*12), Sprite.invActive, true);			
		}

		//equipped inventory
		screen.renderSprite(width-35+(0%2*16), 110-(0/2*16), Sprite.invWeapon, true);
		screen.renderSprite(width-35+(1%2*16), 110-(1/2*16), Sprite.invRing, true);
		screen.renderSprite(width-35+(0%2*16), 110-(2/2*16), Sprite.invLegs, true);
		screen.renderSprite(width-35+(1%2*16), 110-(3/2*16), Sprite.invFeet, true);
		screen.renderSprite(width-35+(0%2*16), 110-(4/2*16), Sprite.invHead, true);
		screen.renderSprite(width-35+(1%2*16), 110-(5/2*16), Sprite.invBody, true);
		
		//health
		screen.renderSprite(width-37, 39, new Sprite(36,7,0xffffff), true);
		screen.renderSprite(width-36, 40, new Sprite(34,5,0x000000), true);
		screen.renderSprite(width-36, 40, new Sprite((Level.Players.get(0).health<0?0:Level.Players.get(0).health>1000?1000:Level.Players.get(0).health)/29,5,0xff0000), true);
		
		//stamina
		screen.renderSprite(width-37, 49, new Sprite(36,7,0xffffff), true);
		screen.renderSprite(width-36, 50, new Sprite(34,5,0x000000), true);
		screen.renderSprite(width-36, 50, new Sprite((Level.Players.get(0).stamina<0?0:Level.Players.get(0).stamina>1000?1000:Level.Players.get(0).stamina)/29,5,0x00ff00), true);
		
		//mana
		screen.renderSprite(width-37, 59, new Sprite(36,7,0xffffff), true);
		screen.renderSprite(width-36, 60, new Sprite(34,5,0x000000), true);
		screen.renderSprite(width-36, 60, new Sprite((Level.Players.get(0).mana<0?0:Level.Players.get(0).mana>1000?1000:Level.Players.get(0).mana)/29,5,0x0000ff), true);
		
		//armory items			- Sourced keiran.young@wickhighschool.org -loop math
		for(int i = 0; i < 4; i++){
			if(Level.Players.get(0).armory[i]!=null){
				screen.renderSprite(width-35+((i%2)*16) + (i>3 ? 4 : 0), 110-((9 - i)/4*16) + (i>3 ? 20 : 0), Level.Players.get(0).armory[i].Sprite, true);
			}
		}
		
		//inventory items
		for(int i = 0; i < 9; i++){
			if(Level.Players.get(0).inventory[i]!=null){
				screen.renderSprite(width-35+((i%3)*12), height-11-((i/3)*12), Level.Players.get(0).inventory[i].Sprite, true);
			}
		}
		if(Level.Players.get(0)!=null){
			screen.renderSprite(width-36+(Level.Players.get(0).getInvX()*12), height-36+(Level.Players.get(0).getInvY()*12), Sprite.invActiveOv, true);
		}
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		
		//display the image in the window
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 24));
		int size = 7 * 25 + 5;
		//debug
		if(debug){
			//debug
			g.setColor(Color.BLACK);
			g.fillRect(width*scale-305, 15, 110, size);
			g.setColor(Color.WHITE);
			g.drawRect(width*scale-305, 15, 110, size);
			g.drawString("pX: " + (Level.Players.get(0).x), width*scale-300, 40);
			g.drawString("pY: " + (Level.Players.get(0).y), width*scale-300, 65);
			g.drawString("cX: " + ((xScroll>>5)+5), width*scale-300, 90);
			g.drawString("cY: " + ((yScroll>>5)+3), width*scale-300, 115);
			g.drawString("D: " + (Level.Players.get(0).dir), width*scale-300, 140);
			g.drawString("U: " + ups, width*scale-300, 165);
			g.drawString("F: " + fps, width*scale-300, 190);
			
			//stats
			g.setColor(Color.BLACK);
			g.fillRect(20, 15, 110, size);
			g.setColor(Color.WHITE);
			g.drawRect(20, 15, 110, size);
			g.drawString("Hp: " + Level.Players.get(0).health/10, 25, 40);
			g.drawString("St: " + Level.Players.get(0).stamina/10, 25, 65);
			g.drawString("Mn: " + Level.Players.get(0).mana/10, 25, 90);
			g.drawString("Sp: " + Level.Players.get(0).speed, 25, 115);
			g.drawString("x: " + Mouse.getX(), 25, 140);
			g.drawString("y: " + Mouse.getY(), 25, 165);
			g.drawString("b: " + Mouse.getB(), 25, 190);

			
			screen.Debug = true;
		}
		else screen.Debug=false;
		
		//always at e bottom ladd!
		g.dispose();
		bs.show();
		
	}
	
	private void load(Player player) {
		long start = System.currentTimeMillis();
        System.out.println("Loading...");
		String string="";
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("Save_Data.txt"));
		} catch (FileNotFoundException e) {				
			System.err.println("No save file");
		}
        String str;
        try {
			while((str = in.readLine()) != null){
			    string+=(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("File located but failed to load");
		}
        String[]load = new String[3];
        String Player = "";
        String Npcs = "";
        int c = 0;
        int i = 0;
        int l = 0;
        while((i+c)<(string.length())){
            c=1;
        	while(string.charAt(i+c)!='|'){
        		c++;
        	}
	        load[l]=string.substring(i+1,i+c);
	        l++;
	        i+=c;
        }

        Player=load[0];
        Npcs=load[1];

        c = 0;
        i = 0;
        l = 0;
        String[]loadP = Player.split(",");
        
        for(int o=0;o<Level.npcs.size();o++){
			Level.npcs.get(o).remove();
		}
        for(int o=0;o<Level.Projectiles.size();o++){
			Level.Projectiles.get(o).remove();
		}
        for(int o=0;o<Level.particles.size();o++){
			Level.particles.get(o).remove();
		}
        for(int o=0;o<Level.items.size();o++){
			Level.items.get(i).remove();
		}
        for(int o=0;o<Level.Entities.size();o++){
			Level.Entities.get(i).remove();
        }
        Level.Players.get(0).x = Integer.parseInt(loadP[0]);
        Level.Players.get(0).y = Integer.parseInt(loadP[1]);
        Level.Players.get(0).health = Integer.parseInt(loadP[2]);
        Level.Players.get(0).stamina = Integer.parseInt(loadP[3]);
        Level.Players.get(0).mana = Integer.parseInt(loadP[4]);
        if(Npcs!=null&&Npcs.length()>0){     
	        i = 0;
	        c = 0;
	        for(int o=0;o<Npcs.length();o++){
	        	if(Npcs.charAt(o)=='&'){
	        		c++;
	        	}
	        }
	        int npcnum = c-1;
	        String[]loadN = new String[npcnum];
	        c = 0;
	        i = 0;
	        l = 0;
	        while((i+c)<Npcs.length()){
	            c=1;
	        	while(Npcs.charAt(i+c)!='&'){
	        		c++;
	        	}
	        	loadN[l]=Npcs.substring(i+1,i+c);
		        l++;
		        i+=c;
	        }
	        i = 0;
	    	c=1;
	        for(int o = 0; o < npcnum; o++){
	        	String[] NpcStat  = loadN[o].split(",");
		        Level.addEntity(new NPC(Integer.parseInt(NpcStat[0]), Integer.parseInt(NpcStat[1]), Integer.parseInt(NpcStat[2]), Integer.parseInt(NpcStat[3]),Integer.parseInt(NpcStat[4])));
	        }
        }
        System.out.println("Loading Completed in " + (System.currentTimeMillis()-start) + "ms");
	}
	public static boolean getPause(){
		return pause;
	}
	
	public static void main(String[] args){
		//setup the window properties
		Game game= new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Popertots Game Engine [0.4.7pa]");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		//start the game
		game.start();
	}
}
