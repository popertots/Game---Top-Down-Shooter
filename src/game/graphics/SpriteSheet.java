package game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE, WIDTH, HEIGHT;
	public int[] pixels;
	
	//sheets
	public static SpriteSheet tiles = new SpriteSheet("/Textures/SpriteSheet.png",256);
	public static SpriteSheet projectiles = new SpriteSheet("/Textures/Projectiles.png",256);
	public static SpriteSheet Wizard = new SpriteSheet("/Textures/Characters/PlayerWizard.png",256);
	
	public static SpriteSheet Enemy1 = new SpriteSheet("/Textures/Enemies/Enemy1.png",32);
	public static SpriteSheet Enemy2 = new SpriteSheet("/Textures/Enemies/Enemy2.png",32);
	public static SpriteSheet Enemy3 = new SpriteSheet("/Textures/Enemies/Enemy3.png",32);
	public static SpriteSheet Enemy4 = new SpriteSheet("/Textures/Enemies/Enemy4.png",32);
	public static SpriteSheet Enemy5 = new SpriteSheet("/Textures/Enemies/Enemy5.png",32);

	public static SpriteSheet map = new SpriteSheet("/Maps/test.png",64);
	public static SpriteSheet mini = new SpriteSheet("/Textures/mini.png",36);
	public static SpriteSheet Items = new SpriteSheet("/Textures/Items.png",128);
	public static SpriteSheet GameOver = new SpriteSheet("/Textures/GameOver.png",333);
	public static SpriteSheet PressEnter = new SpriteSheet("/Textures/PressEnter.png",130,18);
	public static SpriteSheet Pause = new SpriteSheet("/Textures/Pause.png",120,150);
	public static SpriteSheet PauseButton = new SpriteSheet("/Textures/PauseButton.png",75,26);
	public static SpriteSheet PauseButtonA = new SpriteSheet("/Textures/PauseButtonA.png",75,26);

	
	
	//npcs
	public static SpriteSheet NPC1 = new SpriteSheet("/Textures/Enemies/Npc1.png",128);
	public static SpriteSheet NPC2 = new SpriteSheet("/Textures/Enemies/Npc2.png",128);
	public static SpriteSheet NPC3 = new SpriteSheet("/Textures/Enemies/Npc3.png",128);
	public static SpriteSheet NPC4 = new SpriteSheet("/Textures/Enemies/Npc4.png",128);
	public static SpriteSheet NPC5 = new SpriteSheet("/Textures/Enemies/Npc5.png",128);


	
	//sub-sheets
	public static SpriteSheet WizardUp = new SpriteSheet(Wizard,0,2,2,1,32);
	public static SpriteSheet WizardDown = new SpriteSheet(Wizard,0,1,8,1,32);
	public static SpriteSheet WizardSide = new SpriteSheet(Wizard,0,0,7,1,32);
	
	public static SpriteSheet NPC1Down = new SpriteSheet(NPC1,0,0,4,1,32);
	public static SpriteSheet NPC1Up = new SpriteSheet(NPC1,0,1,4,1,32);
	public static SpriteSheet NPC1Side = new SpriteSheet(NPC1,0,2,4,1,32);

	public static SpriteSheet NPC2Down = new SpriteSheet(NPC2,0,0,4,1,32);
	public static SpriteSheet NPC2Up = new SpriteSheet(NPC2,0,1,4,1,32);
	public static SpriteSheet NPC2Side = new SpriteSheet(NPC2,0,2,4,1,32);
	
	public static SpriteSheet NPC3Down = new SpriteSheet(NPC3,0,0,4,1,32);
	public static SpriteSheet NPC3Up = new SpriteSheet(NPC3,0,1,4,1,32);
	public static SpriteSheet NPC3Side = new SpriteSheet(NPC3,0,2,4,1,32);
	
	public static SpriteSheet NPC4Down = new SpriteSheet(NPC4,0,0,4,1,32);
	public static SpriteSheet NPC4Up = new SpriteSheet(NPC4,0,1,4,1,32);
	public static SpriteSheet NPC4Side = new SpriteSheet(NPC4,0,2,4,1,32);
	
	public static SpriteSheet NPC5Down = new SpriteSheet(NPC5,0,0,4,1,32);
	public static SpriteSheet NPC5Up = new SpriteSheet(NPC5,0,1,4,1,32);
	public static SpriteSheet NPC5Side = new SpriteSheet(NPC5,0,2,4,1,32);

	
	private Sprite[] Sprites;

	public Sprite[] getSprite(){
		return Sprites;	
	}
	
	public SpriteSheet (String path, int size){
		this.path = path;
		this.SIZE = size;
		WIDTH = SIZE;
		HEIGHT = SIZE;
		pixels = new int[size*size];
		load();
	}
	
	public SpriteSheet (String path, int width, int height){
		this.path = path;
		SIZE = 32;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH*HEIGHT];
		load();
	}

	public SpriteSheet (SpriteSheet sheet, int x, int y, int width, int height, int spriteSize){
		int xx=x*spriteSize;
		int yy=y*spriteSize;
		int w=width*spriteSize;
		int h=height*spriteSize;
		WIDTH = w;
		HEIGHT = h;
		SIZE = w;
		pixels = new int[w*h];
		for(int y0 = 0; y0< h;y0++){
			int yp=yy+y0;
			for(int x0 = 0; x0<w;x0++){
				int xp=xx+x0;
				pixels[x0+y0*w]=sheet.pixels[xp+yp*sheet.WIDTH];
			}
		}
		int frame = 0;
		Sprites = new Sprite[width*height];
		for(int ya=0;ya<height;ya++){
			for(int xa=0;xa<width;xa++){
				int[] spritePixels = new int[spriteSize*spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++){
					for(int x0 = 0; x0 < spriteSize; x0++){
						spritePixels[x0+y0*spriteSize]=pixels[(x0+xa*spriteSize)+(y0+ya*spriteSize)*WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize ,spriteSize);
				Sprites[frame++]=sprite;
			}
		}
	}

	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}