package game.entity.item;

import game.graphics.Sprite;

public class WeaponItem extends EquipableItem {
	
	public int RoF = 0;
	public int accuracy = 0;

	
	public WeaponItem(int x, int y, Sprite s, int damage) {
		super(x, y, s, s);
		this.damage=damage;
		System.out.println(returnType(this)+", "+x+", "+y);
	}
	
	public WeaponItem(int x, int y, Sprite s, int damage,String Buff){
		super(x, y, s, s);
		this.damage=damage;
		ApplyBuff(Buff);
	}
	
	public void ApplyBuff(String Buff){
		String[] split1 = Buff.split("|");
		for(int i = 0; i < split1.length;i++){
			System.out.print(split1[i]);
		}
	}
}
