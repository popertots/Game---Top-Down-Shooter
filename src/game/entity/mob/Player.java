package game.entity.mob;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import game.Game;
import game.entity.Emitter;
import game.entity.EnemyProjectile;
import game.entity.ParticleEmitter;
import game.entity.Projectile;
import game.entity.WizardProjectile;
import game.entity.item.Exp;
import game.entity.item.WeaponItem;
import game.entity.item.item;
import game.entity.item.useableItem;
import game.graphics.Animation;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Keyboard;
import game.input.Mouse;

public class Player extends mob{

	private Keyboard input;
	private Sprite sprite;
	public int RoF=0;
	public boolean sprint = false, walking = false, dead = false,DB=false,pause=false;
	public int speed = 2, SprintS = 3, spawnX=3<<5,spawnY=3<<5,exp=0;
	private long deathtime=0, spawn=0;
	public int DmgRst=0;
	public int ReHealth = 1, ReStamina = 1, ReMana = 1; 
	public int health=1000,stamina=1000,mana=1000,Lives=3;
	public Random rnd = new Random();
	private Animation up = new Animation(SpriteSheet.WizardUp,32,32,2);
	private Animation down = new Animation(SpriteSheet.WizardDown,32,32,8);
	private Animation side = new Animation(SpriteSheet.WizardSide,32,32,7);
	private int invX = 1, invY = 1;


	public static item[] inventory = new item[10];
	public item[] armory = new item[4];
	public WeaponItem Wand, Ring;
	
	public Player(int x, int y, Keyboard input){
		spawn=System.currentTimeMillis();
		this.x = x<<5;
		this.y = y<<5;
		this.input = input;
		sprite = Sprite.WizardForward0;
		RoF = WizardProjectile.RoF;
		for(int i = 0; i < inventory.length;i++){
			inventory[i]=null;
		}
	}

	public void update(){
		//level.addEntity(new Exp((int)(x+16+(rnd.nextGaussian()*25)), (int)(y+16+(rnd.nextGaussian()*25)), new Sprite(rnd.nextInt(2)+1,0xffffff)));
		if (!dead){
			updateAnim();
			updateStats();
			updateInv();
			updateMovement();
			updateShooting();
			removeProjectiles();
		}
	}

	public void updateInv(){
		if(input.num1){
			invX=0;
			invY=2;
		}
		if(input.num2){
			invX=1;
			invY=2;
		}
		if(input.num3){
			invX=2;
			invY=2;
		}
		if(input.num4){
			invX=0;
			invY=1;
		}
		if(input.num5){
			invX=1;
			invY=1;
		}
		if(input.num6){
			invX=2;
			invY=1;
		}
		if(input.num7){
			invX=0;
			invY=0;
		}
		if(input.num8){
			invX=1;
			invY=0;
		}
		if(input.num9){
			invX=2;
			invY=0;	
		}
		
		for(int i = 0; i < inventory.length;i++){
			if(inventory[i] instanceof useableItem){
			
			}else inventory[i]=null;
		}
		if(input.enter&&inventory[invX+(2-invY)*3] instanceof useableItem){
			inventory[invX+(2-invY)*3].use();
			inventory[invX+(2-invY)*3]=null;
		}
	}
	
	private void updateStats() {
		if(health<1000&&!walking){
			health+=ReHealth;
		}
		if(!input.shift){
			if(stamina<1000){
				stamina+=ReStamina;
			}
		}
		if(Mouse.getB()!=1&&Mouse.getB()!=3){
			if(mana<1000){
				mana+=ReMana;
			}
		}
		
		for(int i = 0; i < level.Projectiles.size(); i++){
			if(level.Projectiles.get(i) instanceof EnemyProjectile){
				for(int yp = 0; yp < 32; yp++){
					for(int xp = 0; xp < 24; xp++){
						if((int)level.Projectiles.get(i).x == (int)(x+xp)-10 && (int)level.Projectiles.get(i).y == (int)(y+yp)-12){
							applyDamage(25);
							level.Projectiles.get(i).remove();
						}
					}
				}
			}
		}
		
		if(health <= 0 ){
			Die();
		}
	}

	private void Die() {
		sprite = Sprite.WizardDead;
		dead=true;
		Lives--;
		deathtime=(System.currentTimeMillis()+3500);
	}
	
	public void applyDamage(int damage){
		health-=damage;
	}
	public void addHealth(int amount){
		if(health+amount>1000)health=1000;else health+=amount; 
	}
	public void addStamina(int amount){
		if(stamina+amount>1000)stamina=1000;else stamina+=amount; 
	}
	public void addMana(int amount){
		if(mana+amount>1000)mana=1000;else mana+=amount;
	}
	public void applyEffect(int color){
		for(int i = 0;i<rnd.nextInt(32)+32;i++){
			level.addEntity(new Exp((int)(x+12+(rnd.nextGaussian()*5)), (int)(y+8+(rnd.nextGaussian()*5)), new Sprite(rnd.nextInt(2)+1,color)));
		}
	}


	private void updateAnim() {
		up.update();
		down.update();
		side.update();
	}

	private void removeProjectiles() {
		for(int i =0;i < level.Projectiles.size();i++){
			Projectile p = level.Projectiles.get(i);
			if (p.isRemoved()){
				level.Projectiles.remove(i);
			}
		}
	}

	private void updateMovement() {
		int xa = 0, ya = 0;
		if(input.shift&&!sprint&&stamina>0){
			sprint=true;
			stamina-=5;
		}else sprint=false;
		if(sprint){
			if (input.up) ya = ya - speed-SprintS;
			if (input.down) ya = ya + speed+SprintS;
			if (input.left) xa = xa - speed-SprintS;
			if (input.right) xa = xa + speed+SprintS;
		}else{
			if (input.up) ya = ya - speed;
			if (input.down) ya = ya + speed;
			if (input.left) xa = xa - speed;
			if (input.right) xa = xa + speed;
		}
		if (xa != 0 || ya != 0){
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	private void updateShooting() {
		if (RoF>0)RoF--;
		if(Mouse.getB()==1 && RoF<=0){
			if(mana>15){
				double dx = Mouse.getX()-Game.getWindowWidth()/2;
				double dy = Mouse.getY()-Game.getWindowHeight()/2;
				double dir = Math.atan2(dy,dx);
				System.out.println(dir);
				if(Wand==null){
					shoot(x,y,dir+(rnd.nextGaussian()/25),25,rnd.nextInt(0xffffff),.75,0);
					RoF = WizardProjectile.RoF;
					mana-=15;
				}else{
					shoot(x,y,dir+(rnd.nextGaussian()/25),25,rnd.nextInt(0xffffff),.75,Wand.damage+(Ring.damage));
					RoF = Wand.RoF+(Ring.RoF);
					mana-=Wand.manaDec+(Ring.manaDec);
				}
			}
		}
		if(Mouse.getB()==3 && RoF<=0){
			if(mana>250){
				blast();
				mana-=250;
			}
		}
	}

	public void blast() {
		for(int i=0;i<22.5;i++){
			shoot(x,y,i,50,true);
			RoF = WizardProjectile.RoF;
		}		
	}

	public void render(Screen screen){
		int flip = 0;
		pause=Game.getPause();
		if(!dead){
			if(!pause){
				if(input.up&&input.down){
					sprite = Sprite.WizardForward0;
				}
				else if(input.left&&input.right){
					sprite = Sprite.WizardForward0;
				}
				else if(input.left){
					flip=1;
					side.setFrameRate(5);
					sprite = side.GetSprite();
				}
				else if(input.right){
					flip=0;
					side.setFrameRate(5);
					sprite = side.GetSprite();
				}
				else if(input.up){
					flip=0;
					up.setFrameRate(10);
					sprite = up.GetSprite();
				}
				else if(input.down){
					flip=0;
					down.setFrameRate(5);
					sprite = down.GetSprite();
				}
				else{
					sprite = Sprite.WizardForward0;
				}
				DB=false;
				screen.renderMob(x, y, sprite, flip);
			}
		}
		else{
			screen.renderMob(x, y, sprite, flip);
			screen.renderSprite(0, 0, Sprite.GameOver, true);
			if(Lives>0){
				if(System.currentTimeMillis()%1000>500){
					screen.renderSheet(Screen.width/2-64, Screen.height/2, SpriteSheet.PressEnter, true);
				}
			}else{
				if(deathtime-System.currentTimeMillis()<=0){
					System.exit(0);
				}
			}
			if(DB==false){
				Emitter e = new ParticleEmitter((int)x+16, (int)y+16, 100, 250,Sprite.particleRed, level);
				Emitter r = new ParticleEmitter((int)x+16, (int)y+16, 150, 50,new Sprite(3,0x660000), level);
				level.addEntity(e);
				level.addEntity(r);
				DB=true;
			}
			if(input.enter&&Lives>0){
				health=mana=stamina=1000;
				x=spawnX;
				y=spawnY;
				dead=false;
			}
		}
		if(input.enter){
		screen.xOffset+=rnd.nextInt(11)-5;
		screen.yOffset+=rnd.nextInt(11)-5;

		}
	}
	public int getInvX(){
		return invX;
	}
	public int getInvY(){
		return invY;
	}
	
	public void save(Screen screen,int index) {
		BufferedWriter writer = null;
		try
		{
			String string = "|";
			string+=x;
			string+=","+y;
			string+=","+health;
			string+=","+stamina;
			string+=","+mana;
			string+=",|";
			if(level.npcs.size()>0){
				string+="&";
				string+=level.npcs.get(0).x;
				string+=","+level.npcs.get(0).y;
				string+=","+level.npcs.get(0).speed;
				string+=","+level.npcs.get(0).health;
				string+=","+level.npcs.get(0).mode;
				for(int i = 1; i < level.npcs.size(); i++){
					string+="&";
					string+=level.npcs.get(i).x+",";
					string+=level.npcs.get(i).y+",";
					string+=level.npcs.get(i).speed+",";
					string+=level.npcs.get(i).health+",";
					string+=level.npcs.get(i).mode+",";
				}
				string+="&|";
			}else string+="null|";
			
			if(index==1){
			    writer = new BufferedWriter( new FileWriter("Save_Data.txt"));
			    writer.write(string);
			}
			else if(index==2){
				writer = new BufferedWriter( new FileWriter("Save_Data.txt"));
			    writer.write(string);
			}
			else if(index==3){
				writer = new BufferedWriter( new FileWriter("Save_Data.txt"));
			    writer.write(string);
			}
		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		System.err.println("Saved at time: "+System.currentTimeMillis());
	}
}
