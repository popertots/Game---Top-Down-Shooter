package game.entity.item;

import game.graphics.Sprite;

public class instantItem extends item {
	int amount, mode;

	public instantItem(int x, int y, Sprite s) {
		super(x, y, s, s);
	}
	
	public instantItem(int x, int y, Sprite s, int amount,int mode) {
		super(x, y, s, s);
		this.amount=amount;
		this.mode=mode;
	}
	
	public void update(){
		for(int i=0;i<level.Players.size();i++){
			if(DistanceTo(level.Players.get(i))<16){
				use();
				level.Players.get(0).applyEffect(0x00dd00);
				remove();
			}
		}
	}
	
	public void use(){
		if(mode==0){
			
		}else if(mode==1){
			level.Players.get(0).addHealth(amount);
		}else if(mode == 2){
			level.Players.get(0).addStamina(amount);
		}else if(mode == 3){
			level.Players.get(0).addMana(amount);
		}
	}
}
