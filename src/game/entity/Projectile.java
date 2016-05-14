package game.entity;

import game.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {
	
	protected final int xO, yO;
	protected Sprite sprite;
	public double x;
	public double y;
	protected double nx;
	protected double ny;
	protected double angle;
	protected double distance;
	protected double speed;
	protected double range;
	protected double damage;
	protected final Random rnd = new Random();
	public static int dmg=0;
	
	public Projectile(int x, int y, double dir){
		xO = x;
		yO = y;
		angle = dir;
		this.x = xO;
		this.y = yO;
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public int getSpriteSize(){
		return sprite.SIZE;
	}
	
	public void move(){
		
	}
	
}
