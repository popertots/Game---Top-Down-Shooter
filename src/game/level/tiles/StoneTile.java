package game.level.tiles;

import game.graphics.Screen;
import game.graphics.Sprite;

public class StoneTile extends tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
		
	}
	
	public void render(int x, int y, Screen screen){
			screen.renderTile(x<<5, y<<5, this);
	}
}
