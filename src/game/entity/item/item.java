package game.entity.item;

import game.entity.Entity;
import game.entity.ParticleEmitter;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.graphics.Sprite;

public class item extends Entity{

	public Sprite Sprite;
	public Sprite bSprite;
	public Sprite sSprite;
	
	public int damage = 0;
	public int health = 0;
	public int mana = 0;
	public int Speed = 0;

	public int ReHealth = 0;
	public int ReMana = 0;

	public int manaDec = 0;
	public int healthDec = 0;
	public int staminaDec = 0;
	
	public boolean inv = false;

	
	public item(int x, int y, Sprite b, Sprite s){
		this.x=x;
		this.y=y;
		this.bSprite = b;
		this.sSprite = s;
		this.Sprite = bSprite;
		inv=false;
	}
	
	
	public void update(){
		if(!inv){
			for(int i=0;i<level.Players.size();i++){
				if(DistanceTo(level.Players.get(i))<16){
					pickUp(this);
				}
			}
		}
	}
	
	
	public void pickUp(item I){
		if(this instanceof useableItem || this instanceof WeaponItem){
		boolean valid = false;
		int pos = 0;
		while(!valid && pos <= 9){
			if(Player.inventory[pos]!=null){
				pos++;
			}else{
				valid=true;
			}
		}
		boolean valid2 = true;
		for(int o = 0; o < Player.inventory.length;o++){
			if(I==Player.inventory[o]){
				valid2=false;
			}
		}
		if(pos<9&&valid2){
			inv=true;
			I.SmallSprite();
			Player.inventory[pos]=I;
			level.addEntity(new ParticleEmitter((int)I.x+16, (int)I.y+16, 5, 25,game.graphics.Sprite.particleBlue, level,true));
			I.remove();
		}else{
			inv=false;
		}
		}
	}
	
	public void spriteSwitch(){
		if(Sprite==bSprite)Sprite=sSprite;
		else if(Sprite==sSprite) Sprite=bSprite;
	}
	
	public void SmallSprite(){
		Sprite=sSprite;
	}
	
	public void BigSprite(){
		Sprite=bSprite;
	}
	
	public void render(Screen screen){
		screen.renderSprite(x+8, y+8, Sprite,false);
	}

	public void use() {
		
	}
}
