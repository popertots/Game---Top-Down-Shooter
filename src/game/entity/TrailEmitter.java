package game.entity;

import game.graphics.Sprite;
import game.level.Level;

public class TrailEmitter extends Emitter {
	
	public TrailEmitter(int x, int y, int timeSpan, int amount, Sprite s,double spread, Level level) {
		super(x, y, game.entity.Emitter.type.PARTICLE, amount, level);
		for (int i = 0;i<amount;i++){
				level.addEntity(new ParticleTrail(x,y,timeSpan,s,spread));
		}
	}
	
	public TrailEmitter(int x, int y, int timeSpan, int amount, Sprite s,double spread, Level level,boolean magic) {
		super(x, y, game.entity.Emitter.type.PARTICLE, amount, level);
		for (int i = 0;i<amount;i++){
				level.addEntity(new ParticleTrail(x,y,timeSpan,s,spread,magic));
		}
	}
}
