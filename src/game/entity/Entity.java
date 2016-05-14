package game.entity;

import game.entity.item.WeaponItem;
import game.entity.item.instantItem;
import game.entity.item.useableItem;
import game.graphics.Screen;
import game.level.Level;

import java.util.Random;

public abstract class Entity {

	public int x;
	public int y;
	private boolean removed = false;
	protected Level level;
	protected final Random rnd = new Random();
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void init(Level level){
		this.level = level;
	}
	
	public double DistanceTo(Entity E){
		int dist = (int) Math.sqrt(Math.pow(E.x-x, 2)+Math.pow(E.y-y,2));
		return dist;
	}
	
	public double DistanceBetween(Entity Other, Entity This){
		int dist = (int) Math.sqrt(Math.pow(Other.x-This.x, 2)+Math.pow(Other.y-This.y,2));
		return dist;
	}

	public String returnType(Entity E){
		String type=null;
		if(E instanceof useableItem ){
			type = "Usable";
		}
		else if(E instanceof instantItem ){
			type = "Instant";
		}
		else if(E instanceof WeaponItem ){
			type = "Weapon";
		}else{
			type = "nah";
		}
		return type;
	}
}
