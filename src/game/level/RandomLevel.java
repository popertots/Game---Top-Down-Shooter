package game.level;

import game.input.Keyboard;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random rnd = new Random();
	
	public RandomLevel(int width, int height,Keyboard key) {
		super(width, height,key);
	}
	
	protected void generateLevel() {
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tilesInt[x + y * width] = rnd.nextInt(100);
			}
		}
	}
}
