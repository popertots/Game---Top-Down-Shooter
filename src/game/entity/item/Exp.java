package game.entity.item;

import game.entity.mob.Player;
import game.level.Level;

public class Exp extends instantItem{

	private int speed=1;
	private int ups=0;
	private Player target;
	double angle;
	double nx;
	double ny;
	
	public Exp(int x, int y, game.graphics.Sprite s) {
		super(x, y, s);
		target = Level.Players.get(0);
		angle =  Math.atan2((double)level.Players.get(0).y+8-y, (double)level.Players.get(0).x+8-x);
	}
	
	public void update(){
		angle =  Math.atan2((double)level.Players.get(0).y+8-y, (double)level.Players.get(0).x+8-x);
		nx = (speed/5) * Math.cos(angle);
		ny = (speed/5) * Math.sin(angle);
		ups++;
		speed++;
		ups=0;
		
		System.out.println();

		x+=nx;
		y+=ny;
		
		for(int i=0;i<level.Players.size();i++){
			for(int yp = 0; yp < 16; yp++){
				for(int xp = 0; xp < 16; xp++){
					if(x==level.Players.get(i).x+xp&&y==level.Players.get(i).y+yp){
						level.Players.get(i).exp++;
						remove();
					}
				}
			}
		}
	}
	
	public void move(int xa, int ya){
		if (xa!=0&&ya!=0){
			move(xa,0);move(0,ya);
			return;
		}
		x += xa;
		y += ya;
	}

}
