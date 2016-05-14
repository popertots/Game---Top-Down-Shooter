package game.graphics;

public class Animation extends Sprite {
	
	private int frame=0;
	private Sprite sprite;
	private int rate=5;
	private int time=0;
	private int length=-1;

	public Animation(SpriteSheet sheet, int width, int height, int length) {
		super(width, height, sheet);
		sprite = sheet.getSprite()[0];
		this.length = length;
		if(length>sheet.getSprite().length)System.err.println("~Animation Length Error~");
	}
	
	public void update(){
		time++;
		if(time%rate==0){
			if (frame>=length-1)frame=0;else frame++;
			sprite = sheet.getSprite()[frame];
		}
	}
	
	public Sprite GetSprite(){
		return sprite;
	}
	
	public void setFrameRate(int frames){
		rate = frames;
	}
}