package game.entity;

import game.graphics.Sprite;
import game.level.Level;

public class ParticleEmitter extends Emitter {
	
	public ParticleEmitter(int x, int y, int timeSpan, int amount, Sprite s, Level level) {
		super(x, y, game.entity.Emitter.type.PARTICLE, amount, level);
		for (int i = 0;i<amount;i++){
				level.addEntity(new particle(x,y,timeSpan,s));
		}
	}
	
	public ParticleEmitter(int x, int y, int timeSpan, int amount, Sprite s, Level level,boolean magic) {
		super(x, y, game.entity.Emitter.type.PARTICLE, amount, level);
		for (int i = 0;i<amount;i++){
				level.addEntity(new particle(x,y,timeSpan,s,magic));
		}
	}
}
