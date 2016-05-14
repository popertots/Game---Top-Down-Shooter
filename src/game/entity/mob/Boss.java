package game.entity.mob;

import game.graphics.Screen;
import game.graphics.Sprite;

public class Boss extends mob{
	
	public int x;
	public int y;
	public int health;
	public int minions;
	public Sprite[] sprites;
	private boolean spawned=false;

	

	public Boss(int x, int y, int health, int minions, Sprite[] sprites) {
		this.x=x<<5;
		this.y=y<<5;
		this.health=health;
		this.minions=minions;
		this.sprites=sprites;
	}
	
	public void update(){
		if(!spawned){
			for(int i = 0; i < 32*32; i++){
				if(rnd.nextInt(32*32)==0){
					int spritenum=rnd.nextInt(4);
					level.addEntity(new NPC(this.x+(rnd.nextInt(64)-32), this.y+(rnd.nextInt(64)-32), 1, 300,1, spritenum==0?Sprite.Enemy1:spritenum==1?Sprite.Enemy2:spritenum==2?Sprite.Enemy3:Sprite.Enemy4));
				}
			}
			spawned = true;
		}
		int xrand = rnd.nextInt(25);
		int yrand = rnd.nextInt(25);

		if(xrand==0){
			x++;
		}else if(xrand==1){
			x--;
		}
		
		if(yrand==0){
			y++;
		}else if(yrand==1){
			y--;
		}
			
	}

	public void render(Screen screen) {
		screen.renderSprite(x, y, sprites[0], false);
	}
}