package game.entity.mob;

import game.entity.EnemyProjectile;
import game.entity.Entity;
import game.entity.Projectile;
import game.entity.WizardProjectile;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.input.Mouse;

public abstract class mob extends Entity {

	protected Sprite sprite;
	public int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;
	
	public void move(int xa, int ya){
		if (xa!=0&&ya!=0){
			move(xa,0);
			move(0,ya);
			return;
		}
		
		dir = 0;
		if (ya > 0) dir = 0;
		if (ya < 0) dir = 2;
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		
		if (!collision(xa, ya)){
			x += xa;
			y += ya;
		}else{
			
		}
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	//true for player, false for enemy
	protected void shoot(int x, int y, double dir,int RoF,int trail,double spread,int dmg){
		Projectile p = new WizardProjectile(x,y,dir,RoF, trail,spread, dmg);
		level.addEntity(p);
	}
	
	protected void shoot(int x, int y, double dir,int RoF,boolean target){
		Projectile p;
		if(target){
			p = new WizardProjectile(x,y,dir,RoF);
		}else{
			p = new EnemyProjectile(x,y,dir,RoF);
		}
		level.addEntity(p);
	}
	
	protected boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c = 0;c<4;c++){
			int xt =((x + xa)+c%2*21+5)>>5;
			int yt =((y + ya)+c/2*16+15)>>5;
			if(level.getTile(xt, yt).solid())solid=true;
		}
		return solid;
	}
}