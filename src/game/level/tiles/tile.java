package game.level.tiles;

import java.util.Random;

import game.graphics.Screen;
import game.graphics.Sprite;

public class tile {

	public int x, y;
	public Sprite sprite;
	private Random rnd = new Random();
	
	public static tile grass = new GrassTile(Sprite.grass);
	public static tile grassPathV = new GrassTile(Sprite.grassPathV);
	public static tile grassPathH = new GrassTile(Sprite.grassPathH);
	public static tile grassPathRBL = new GrassTile(Sprite.grassPathRBL);
	public static tile grassPathRBR = new GrassTile(Sprite.grassPathRBR);
	public static tile grassPathRTL = new GrassTile(Sprite.grassPathRTL);
	public static tile grassPathRTR = new GrassTile(Sprite.grassPathRTR);
	public static tile rock = new GrassDecorSolid(Sprite.rock);
	public static tile crate = new WallSolid(Sprite.crate);
	public static tile wallHS = new WallSolid(Sprite.wallHS);
	public static tile wallHG = new WallSolid(Sprite.wallHG);
	public static tile wallVL = new WallSolid(Sprite.wallVL);
	public static tile wallVR = new WallSolid(Sprite.wallVR);
	public static tile wallBL = new WallSolid(Sprite.wallBL);
	public static tile wallBR = new WallSolid(Sprite.wallBR);
	public static tile wallTL = new WallSolid(Sprite.wallTL);
	public static tile wallTR = new WallSolid(Sprite.wallTR);
	public static tile wallEL = new WallSolid(Sprite.wallEL);
	public static tile wallER = new WallSolid(Sprite.wallER);
	public static tile flower1 = new GrassDecorSoft(Sprite.flower1);
	public static tile flower2 = new GrassDecorSoft(Sprite.flower2);
	public static tile flower3 = new GrassDecorSoft(Sprite.flower3);
	public static tile stone = new StoneTile(Sprite.stone);
	public static tile stonePathV = new StoneTile(Sprite.stonePathV);
	public static tile stonePathH = new StoneTile(Sprite.stonePathH);
	public static tile stoneGrassT = new StoneTile(Sprite.stoneGrassT);
	public static tile stoneGrassB = new StoneTile(Sprite.stoneGrassB);
	public static tile stoneGrassL = new StoneTile(Sprite.stoneGrassL);
	public static tile stoneGrassR = new StoneTile(Sprite.stoneGrassR);
	public static tile stoneGrassTR = new StoneTile(Sprite.stoneGrassTR);
	public static tile stoneGrassTL = new StoneTile(Sprite.stoneGrassTL);
	public static tile stoneGrassBR = new StoneTile(Sprite.stoneGrassBR);
	public static tile stoneGrassBL = new StoneTile(Sprite.stoneGrassBL);
	public static tile stoneGrassTM = new StoneTile(Sprite.stoneGrassTM);
	public static tile stoneGrassBM = new StoneTile(Sprite.stoneGrassBM);
	public static tile stoneGrassLM = new StoneTile(Sprite.stoneGrassLM);
	public static tile stoneGrassRM = new StoneTile(Sprite.stoneGrassRM);
	public static tile door = new WallSolid(Sprite.door);



	public static tile voidTile = new VoidTile(Sprite.voidSprite);


	
	public tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
	
}
