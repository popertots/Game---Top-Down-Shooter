package game.entity;

import game.graphics.Screen;
import game.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static int RoF=0;
	private int trail=0; 
	private double spread=1;

	public WizardProjectile(int x, int y, double dir, int RoF) {
		super(x, y, dir);
		WizardProjectile.RoF=RoF;
		range = 5000;
		speed = 10;
		damage = 20;
		sprite = Sprite.Pr_Wzd_5;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public WizardProjectile(int x, int y, double dir, int RoF,int trail,double spread,int dmg) {
		super(x, y, dir);
		WizardProjectile.RoF=RoF;
		range = 5000;
		speed = 5;
		damage = 20;
		sprite = Sprite.Pr_Wzd_3;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		this.trail=trail;
		this.spread=spread;
		WizardProjectile.dmg=dmg;
	}

	public void update(){
		if(level.TileCollision((int)(x+nx),(int)(y+ny),10,11,11) || x<-10 || y<-10){
			remove();
			Emitter e = new ParticleEmitter((int)x+16, (int)y+16, 25, 25,Sprite.particleWhite, level,true);
			level.addEntity(e);
		}
		if(trail!=0){
			//level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 15, 25,new Sprite(1,trail),spread, level,false));
			level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 25, 1,new Sprite(1,0x000000),spread, level,false));
			level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 10, 5,new Sprite(1,0xffff00),spread, level,false));
			level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 10, 5,new Sprite(1,0xaaaa00),spread, level,false));
			level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 15, 25,new Sprite(1,0x880000),spread, level,false));
			level.addEntity(new TrailEmitter((int)x+16, (int)y+16, 15, 25,new Sprite(1,0xaa0000),spread, level,false));
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
			Emitter e = new ParticleEmitter((int)x+16, (int)y+16, 25, 25,Sprite.particleWhite, level);
			level.addEntity(e);
		}
	}

	private double getDistance() {
		double dist;
		dist = Math.sqrt(Math.abs((xO-x)*(xO-x)+(yO-y)*(yO-y)));
		return dist;
	}
}
