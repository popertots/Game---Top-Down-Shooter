package game.entity.item;

import game.graphics.Sprite;

public class EquipableItem extends item {

	boolean equipped = false;
	
	public EquipableItem(int x, int y, Sprite b, Sprite s) {
		super(x, y, b, s);
		System.out.println(returnType(this)+", "+x+", "+y);
	}
	
	public boolean getEquip(){
		return equipped;
	}
	
	public void equip(){
		equipped=!equipped;
	}
}
