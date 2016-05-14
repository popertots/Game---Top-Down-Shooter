package game.entity.item;

import game.graphics.Sprite;

public class useableItem extends item {
	int amount, mode;


	public useableItem(int x, int y, Sprite b, Sprite s) {
		super(x, y, b, s);
	}
	
	public useableItem(int x, int y, Sprite s, int amount,int mode) {
		super(x, y, s, s);
		this.amount=amount;
		this.mode=mode;
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
	
	public void use(){
		if(mode==1){
			level.Players.get(0).addHealth(amount);
		}
		if(mode==2){
			level.Players.get(0).addStamina(amount);
		}
		if(mode==3){
			level.Players.get(0).addMana(amount);
		}
	}
}