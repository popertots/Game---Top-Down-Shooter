package game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.mob.NPC;
import game.entity.mob.Player;
import game.graphics.Sprite;
import game.input.Keyboard;

public class SpawnLevel extends Level {
	
	public SpawnLevel (String pathT, String pathE, Keyboard key){
		super(pathT, pathE, key);
	}
	
	protected void loadLevel(String pathT, String pathE) {
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(pathT));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch(IOException e){
			e.printStackTrace();
			System.out.print("Theres no level file.");
		}
		addEntity(new Player(3, 3, key));
		
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(pathE));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			enemies = new int[w*h];
			
			image.getRGB(0, 0, w, h, enemies, 0, w);
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					if(enemies[x+y*width]==-16711936){
						int spritenum = rnd.nextInt(5);
						int H = (rnd.nextInt(10)+1)*100;
						addEntity(new NPC(x<<5, y<<5, 1, H, 1, spritenum==0?Sprite.Enemy1:spritenum==1?Sprite.Enemy2:spritenum==2?Sprite.Enemy3:spritenum==3?Sprite.Enemy4:Sprite.Enemy5));
						//addEntity(new NPC(x<<5, y<<5, 1, H, 1, spritenum==0?Sprite.Enemy1:spritenum==1?Sprite.Enemy2:spritenum==2?Sprite.Enemy3:Sprite.Enemy4));
					}
				}
			}
		} catch(IOException e){
			System.out.print("Theres no enemy file.");
		}
		//Sprite[] l = new Sprite[5]; 
		//l[0]= Sprite.map;
		//addEntity(new Boss(5, 5, 1000, 10, l));
	}
	
	protected void generateLevel(){
		
	}
}
