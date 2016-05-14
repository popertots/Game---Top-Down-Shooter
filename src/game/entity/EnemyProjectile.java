package game.entity;

import game.graphics.Screen;
import game.graphics.Sprite;

public class EnemyProjectile extends Projectile {

	public static int RoF=0;

	public EnemyProjectile(int x, int y, double dir, int RoF) {
		super(x, y, dir);
		EnemyProjectile.RoF=RoF;
		range = 240;
		speed = rnd.nextInt(5)+1;
		damage = 20;
		sprite = Sprite.Pr_Wzd_5;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update(){
		if(level.TileCollision((int)(x+nx),(int)(y+ny),10,11,11) || x<-10 || y<-10){
			remove();
		}
		move();
	}
	
	public void render(Screen screen){
		screen.renderProjectile((int)x, (int)y, this);
	}
	
	public void move(){
		x += nx;
		y += ny;
		
		if (getDistance()>range){
			remove();
		}
	}

	private double getDistance() {
		double dist;
		dist = Math.sqrt(Math.abs((xO-x)*(xO-x)+(yO-y)*(yO-y)));
		return dist;
	}
}
