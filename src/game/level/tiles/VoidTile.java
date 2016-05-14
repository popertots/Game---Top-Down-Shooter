package game.level.tiles;

import game.graphics.Screen;
import game.graphics.Sprite;

public class VoidTile extends tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x<<5, y<<5, this);
	}

	public boolean solid(){
		return true;
	}
}
