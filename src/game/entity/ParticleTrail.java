package game.entity;

import game.graphics.Screen;
import game.graphics.Sprite;

public class ParticleTrail extends particle {
	
	int time=0;
	double spread=1;
	boolean magic=false;
		
	public ParticleTrail(int x, int y, int timeSpan, Sprite s,double spread) {
		super(x, y, timeSpan, s);
		this.spread=spread;
	}
	
	public ParticleTrail(int x, int y, int timeSpan, Sprite s,double spread, boolean magic) {
		super(x, y, timeSpan, s);
		this.magic=magic;
		this.spread=spread;
	}

	public void update(){
		time++;
		if (time>=10000)time=0;
		if (time>this.timeSpan+rnd.nextGaussian()*25){
			remove();
			if(magic&&rnd.nextInt(7)<1){
				Emitter e = new ParticleEmitter((int)xx, (int)yy, 10, 3, new Sprite(1, rnd.nextInt(0xffffffd)), level);
				level.addEntity(e);
			}
		}
		xa=ya=0;
		xa+=rnd.nextGaussian()*spread;
		ya+=rnd.nextGaussian()*spread;
		move(xa,ya);
	}
	
	
	public void render(Screen screen){
		screen.renderSprite((int)xx, (int)yy - (int)zz, sprite, false);
	}
}
