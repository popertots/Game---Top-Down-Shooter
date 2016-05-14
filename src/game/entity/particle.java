package game.entity;

import game.graphics.Screen;
import game.graphics.Sprite;


public class particle extends Entity {

	protected Sprite sprite;
	protected int timeSpan;
	private int time=0;
	protected double xa, ya, za, xx, yy, zz;	
	private boolean magic=false;
	
	public particle(int x, int y, int timeSpan, Sprite s){
		this.x=x;
		this.y=y;
		this.xx=x;
		this.yy=y;
		this.timeSpan=(timeSpan+rnd.nextInt((timeSpan))-(timeSpan/2));
		this.sprite = s;
		this.xa=rnd.nextGaussian()*2/3;
		this.ya=rnd.nextGaussian()*2/3;
		this.zz=rnd.nextFloat()*5;
	}
	
	public particle(int x, int y, int timeSpan, Sprite s, boolean magic){
		this.x=x;
		this.y=y;
		this.xx=x;
		this.yy=y;
		this.timeSpan=(timeSpan+rnd.nextInt((timeSpan))-(timeSpan/2));
		sprite = s;
		this.xa=rnd.nextGaussian()*2/3;
		this.ya=rnd.nextGaussian()*2/3;
		this.zz=rnd.nextFloat()*5;
		this.magic=magic;
	}
	
	public void update(){
		time++;
		if (time>=10000)time=0;
		if (time>timeSpan){
			remove();
			if(magic){
				if(rnd.nextInt(7)<1){
					Emitter e = new ParticleEmitter((int)xx, (int)yy, 10, 3, new Sprite(1, rnd.nextInt(0xffffffd)), level);
					level.addEntity(e);
				}
			}
		}
		za-=0.1;
		if(zz<0){
			zz=0;
			za*=-.85;
			xa*=.85;
			ya*=.85;
		}
		
		move((xx+xa),(yy+ya)+(zz+za));
	}
	
	protected void move(double x, double y) {
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public void render(Screen screen){
		screen.renderSprite((int)xx, (int)yy - (int)zz, sprite, false);
	}
}