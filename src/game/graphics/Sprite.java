package game.graphics;


public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width,height;
	public String color;
	
	//tiles
	public static Sprite grass = new Sprite(32, 0, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(32, 3, 1, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(32, 3, 5, SpriteSheet.tiles);
	public static Sprite crate = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite flower1 = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite flower2 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite flower3 = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite wallHS = new Sprite(32, 6, 2, SpriteSheet.tiles);
	public static Sprite wallHG = new Sprite(32, 7, 2, SpriteSheet.tiles);
	public static Sprite wallVL = new Sprite(32, 0, 3, SpriteSheet.tiles);
	public static Sprite wallVR = new Sprite(32, 1, 3, SpriteSheet.tiles);
	public static Sprite wallBL = new Sprite(32, 2, 3, SpriteSheet.tiles);
	public static Sprite wallBR = new Sprite(32, 3, 3, SpriteSheet.tiles);
	public static Sprite wallTL = new Sprite(32, 4, 3, SpriteSheet.tiles);
	public static Sprite wallTR = new Sprite(32, 5, 3, SpriteSheet.tiles);
	public static Sprite wallEL = new Sprite(32, 7, 3, SpriteSheet.tiles);
	public static Sprite wallER = new Sprite(32, 6, 3, SpriteSheet.tiles);
	public static Sprite stonePathV = new Sprite(32, 4, 1, SpriteSheet.tiles);
	public static Sprite stonePathH = new Sprite(32, 5, 1, SpriteSheet.tiles);
	public static Sprite grassPathV = new Sprite(32, 1, 0, SpriteSheet.tiles);
	public static Sprite grassPathH = new Sprite(32, 2, 0, SpriteSheet.tiles);
	public static Sprite grassPathRBL = new Sprite(32, 5, 0, SpriteSheet.tiles);
	public static Sprite grassPathRBR = new Sprite(32, 4, 0, SpriteSheet.tiles);
	public static Sprite grassPathRTL = new Sprite(32, 6, 0, SpriteSheet.tiles);
	public static Sprite grassPathRTR = new Sprite(32, 3, 0, SpriteSheet.tiles);
	public static Sprite stoneGrassT = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassB = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassL = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassR = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassTR = new Sprite(32, 4, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassTL = new Sprite(32, 5, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassBR = new Sprite(32, 7, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassBL = new Sprite(32, 6, 6, SpriteSheet.tiles);
	public static Sprite stoneGrassTM = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite stoneGrassBM = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite stoneGrassRM = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite stoneGrassLM = new Sprite(32, 3, 7, SpriteSheet.tiles);
	public static Sprite door = new Sprite(32, 4, 4, SpriteSheet.tiles);


	//if tile not defined
	public static Sprite voidSprite = new Sprite(32, 6, 7, SpriteSheet.tiles);
	
	//Wizard
	public static Sprite WizardSide0 = new Sprite(32, 0, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide1 = new Sprite(32, 1, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide2 = new Sprite(32, 2, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide3 = new Sprite(32, 3, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide4 = new Sprite(32, 4, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide5 = new Sprite(32, 5, 0, SpriteSheet.Wizard);
	public static Sprite WizardSide6 = new Sprite(32, 6, 0, SpriteSheet.Wizard);

	public static Sprite WizardForward0 = new Sprite(32, 0, 1, SpriteSheet.Wizard);
	public static Sprite WizardForward1 = new Sprite(32, 1, 1, SpriteSheet.Wizard);
	public static Sprite WizardForward2 = new Sprite(32, 2, 1, SpriteSheet.Wizard);
	public static Sprite WizardForward3 = new Sprite(32, 3, 1, SpriteSheet.Wizard);
	public static Sprite WizardForward4 = new Sprite(32, 4, 1, SpriteSheet.Wizard);

	public static Sprite WizardBack0 = new Sprite(32, 0, 2, SpriteSheet.Wizard);
	public static Sprite WizardBack1 = new Sprite(32, 1, 2, SpriteSheet.Wizard);
	
	public static Sprite WizardDead = new Sprite(32, 7, 0, SpriteSheet.Wizard);

	
	//Enemies
	public static Sprite Enemy1 = new Sprite(32,0,0,SpriteSheet.Enemy1);
	public static Sprite Enemy2 = new Sprite(32,0,0,SpriteSheet.Enemy2);
	public static Sprite Enemy3 = new Sprite(32,0,0,SpriteSheet.Enemy3);
	public static Sprite Enemy4 = new Sprite(32,0,0,SpriteSheet.Enemy4);
	public static Sprite Enemy5 = new Sprite(32,0,0,SpriteSheet.Enemy5);
	

	//Projectiles
	public static Sprite Pr_Wzd_1 = new Sprite(32,0,0,SpriteSheet.projectiles);
	public static Sprite Pr_Wzd_2 = new Sprite(32,1,0,SpriteSheet.projectiles);
	public static Sprite Pr_Wzd_3 = new Sprite(32,2,0,SpriteSheet.projectiles);
	public static Sprite Pr_Wzd_4 = new Sprite(32,3,0,SpriteSheet.projectiles);
	public static Sprite Pr_Wzd_5 = new Sprite(32,4,0,SpriteSheet.projectiles);

	
	//map
	public static Sprite map = new Sprite(64,0,0,SpriteSheet.map);
	public static Sprite mini = new Sprite(36,0,0,SpriteSheet.mini);
	
	//inv
	public static Sprite invInactive = new Sprite(10,4,13,SpriteSheet.tiles);
	public static Sprite invActive = new Sprite(10,4,14,SpriteSheet.tiles);
	public static Sprite invActiveOv = new Sprite(10,5,14,SpriteSheet.tiles);
	public static Sprite invHead = new Sprite(16,4,8,SpriteSheet.tiles);
	public static Sprite invBody = new Sprite(16,5,8,SpriteSheet.tiles);
	public static Sprite invLegs = new Sprite(16,4,9,SpriteSheet.tiles);
	public static Sprite invFeet = new Sprite(16,5,9,SpriteSheet.tiles);
	public static Sprite invWeapon = new Sprite(16,6,8,SpriteSheet.tiles);
	public static Sprite invRing = new Sprite(16,6,9,SpriteSheet.tiles);

	
	
	//items
	public static Sprite armorHeadLeather = new Sprite(16,2,0,SpriteSheet.Items);
	public static Sprite armorBodyLeather = new Sprite(16,3,0,SpriteSheet.Items);
	public static Sprite armorLegsLeather = new Sprite(16,2,1,SpriteSheet.Items);
	public static Sprite armorFeetLeather = new Sprite(16,3,1,SpriteSheet.Items);

	public static Sprite armorHeadGold = new Sprite(16,0,0,SpriteSheet.Items);
	public static Sprite armorBodyGold = new Sprite(16,1,0,SpriteSheet.Items);
	public static Sprite armorLegsGold = new Sprite(16,0,1,SpriteSheet.Items);
	public static Sprite armorFeetGold = new Sprite(16,1,1,SpriteSheet.Items);
	
	public static Sprite armorHeadGoldS = new Sprite(8,8,0,SpriteSheet.Items);
	public static Sprite armorBodyGoldS = new Sprite(8,9,0,SpriteSheet.Items);
	public static Sprite armorLegsGoldS = new Sprite(8,8,1,SpriteSheet.Items);
	public static Sprite armorFeetGoldS = new Sprite(8,9,1,SpriteSheet.Items);

	public static Sprite armorHeadEmerald = new Sprite(16,2,2,SpriteSheet.Items);
	public static Sprite armorBodyEmerald = new Sprite(16,3,2,SpriteSheet.Items);
	public static Sprite armorLegsEmerald = new Sprite(16,2,3,SpriteSheet.Items);
	public static Sprite armorFeetEmerald = new Sprite(16,3,3,SpriteSheet.Items);

	public static Sprite armorHeadSapphire = new Sprite(16,0,2,SpriteSheet.Items);
	public static Sprite armorBodySapphire = new Sprite(16,1,2,SpriteSheet.Items);
	public static Sprite armorLegsSapphire = new Sprite(16,0,3,SpriteSheet.Items);
	public static Sprite armorFeetSapphire = new Sprite(16,1,3,SpriteSheet.Items);

	// -Sourced owenrudhall@gmail.com  - pink armor
	public static Sprite armorHeadPink = new Sprite(16,0,4,SpriteSheet.Items);
	public static Sprite armorBodyPink = new Sprite(16,1,4,SpriteSheet.Items);
	public static Sprite armorLegsPink = new Sprite(16,0,5,SpriteSheet.Items);
	public static Sprite armorFeetPink = new Sprite(16,1,5,SpriteSheet.Items);
	
	public static Sprite armorHeadIron = new Sprite(16,2,4,SpriteSheet.Items);
	public static Sprite armorBodyIron = new Sprite(16,3,4,SpriteSheet.Items);
	public static Sprite armorLegsIron = new Sprite(16,2,5,SpriteSheet.Items);
	public static Sprite armorFeetIron = new Sprite(16,3,5,SpriteSheet.Items);
	
	public static Sprite wandBlue = new Sprite(8,10,0,SpriteSheet.Items);
	public static Sprite wandGreen = new Sprite(8,11,0,SpriteSheet.Items);
	public static Sprite wandYellow = new Sprite(8,10,1,SpriteSheet.Items);
	public static Sprite wandBlack = new Sprite(8,11,1,SpriteSheet.Items);
	
	public static Sprite ringGold = new Sprite(8,10,2,SpriteSheet.Items);
	public static Sprite ringBlue = new Sprite(8,11,2,SpriteSheet.Items);
	public static Sprite ringGreen = new Sprite(8,10,3,SpriteSheet.Items);
	public static Sprite ringRed = new Sprite(8,11,3,SpriteSheet.Items);
	
	public static Sprite potionHealth = new Sprite(8,10,4,SpriteSheet.Items);
	public static Sprite potionMana = new Sprite(8,11,4,SpriteSheet.Items);
	public static Sprite potionStamina = new Sprite(8,10,5,SpriteSheet.Items);
	public static Sprite potionMulti = new Sprite(8,11,5,SpriteSheet.Items);

	
	// -Sourced suledurrani1@gmail.com -Food
	public static Sprite foodChicken = new Sprite(8,10,6,SpriteSheet.Items);
	public static Sprite foodCake = new Sprite(8,11,6,SpriteSheet.Items);
	public static Sprite foodCarrot = new Sprite(8,10,7,SpriteSheet.Items);
	public static Sprite foodChoc = new Sprite(8,11,7,SpriteSheet.Items);

	//GameOver
	public static Sprite GameOver = new Sprite(333,0,0,SpriteSheet.GameOver);


	

	//particles
	public static Sprite particleWhite = new Sprite(1, 0xffffdd);
	public static Sprite particleBlue = new Sprite(1, 0x0000aa);
	public static Sprite particleGreen = new Sprite(1, 0x00cc00);
	public static Sprite particleYellow = new Sprite(1, 0xcccc00);
	public static Sprite particleBlack = new Sprite(1, 0x000000);
	public static Sprite particleRed = new Sprite(1, 0x660000);


	public Sprite(int size, int x, int y , SpriteSheet sheet){
		this.width=size;
		this.height=size;
		SIZE = size;
		pixels = new int[size*size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	//for animation
	public Sprite(int width, int height, SpriteSheet sheet){
		this.width=width;
		this.height=height;
		SIZE = width==height?width:-1;
		pixels = new int[width*height];
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int x, int y , SpriteSheet sheet, String color){
		this.width=size;
		this.height=size;
		SIZE = size;
		pixels = new int[size*size];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		this.color = color;
		load();
	}
		
	public Sprite(int size, int color){
		this.width=size;
		this.height=size;
		SIZE = size;
		pixels = new int[size*size];
		setColor(color);
	}
	
	public Sprite(int width, int height, int color){
		SIZE=0;
		this.width=width;
		this.height = height;
		pixels = new int[width*height];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = width==height?width:-1;
		this.width=width;
		this.height=height;
		this.pixels=pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width*height; i++){
			pixels[i] = color;
		}
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	

	private void load (){
		for(int y = 0; y < SIZE; y++){
			for(int x =0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
