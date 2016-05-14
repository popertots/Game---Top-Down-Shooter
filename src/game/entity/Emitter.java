package game.entity;

import game.level.Level;

public class Emitter extends Entity {
		
	public enum type{
		MOB, PARTICLE;
	}
	
	
	
	public Emitter(int x, int y, type type, int amount, Level level){
		init(level);
		this.x=x;
		this.y=y;
	}
}
