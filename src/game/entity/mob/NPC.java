package game.entity.mob;

import game.entity.Emitter;
import game.entity.EnemyProjectile;
import game.entity.ParticleEmitter;
import game.entity.WizardProjectile;
import game.entity.item.Exp;
import game.entity.item.instantItem;
import game.entity.item.useableItem;
import game.graphics.Animation;
import game.graphics.Screen;
import game.graphics.Sprite;
import game.graphics.SpriteSheet;
import game.input.Mouse;
import game.level.Level;

public class NPC extends mob {
	
	private Sprite sprite;
	private int xa = 0, ya = 0, flip =0, time = 0,RoF=0;
	public int health=1000, maxHealth=0, mode = 0, speed=0, atk=0;
	private boolean RndSpr=true;
	private Animation down;
	private Animation up;
	private Animation side;
	private int[] rand = new int[2];
	
	
	
	public NPC(int x, int y,int speed, int Mode){
		this.x = x;
		this.y = y;
		if(Mode==1){
			rand[0]= rnd.nextInt(64)-32;
			rand[1]= rnd.nextInt(64)-32;
		}
		this.speed=speed;
		if(sprite==null){
			setAnimations();
		}
		this.sprite = down.GetSprite();
		this.mode = Mode;
	}
	
	public NPC(int x, int y,int speed, int health, int Mode){
		this.x = x;
		this.y = y;
		if(Mode==1){
			rand[0]= rnd.nextInt(64)-32;
			rand[1]= rnd.nextInt(64)-32;
		}
		this.speed=speed;
		this.health=health;
		this.maxHealth=health;
		if(sprite==null){
			setAnimations();
		}
		this.sprite = down.GetSprite();
		this.mode = Mode;
	}
	
	public NPC(int x, int y,int speed, int health, int Mode, Sprite sprite){
		this.RoF=EnemyProjectile.RoF;
		this.x = x;
		this.y = y;
		if(Mode==1){
			rand[0]= rnd.nextInt(64)-32;
			rand[1]= rnd.nextInt(64)-32;
		}
		this.sprite=sprite;
		this.speed=speed;
		RndSpr=false;
		this.health=health;
		this.maxHealth=health;
		if(sprite==null){
			setAnimations();
			this.sprite = down.GetSprite();
		}
		this.mode = Mode;
	}
	
	private void setAnimations() {
		if(mode>=0&&mode<=1){
			int ani = rnd.nextInt(5);
			if(ani==0){
				down = new Animation(SpriteSheet.NPC1Down,32,32,4);
				up = new Animation(SpriteSheet.NPC1Up,32,32,4);
				side = new Animation(SpriteSheet.NPC1Side,32,32,4);
			}
			else if(ani==1){
				down = new Animation(SpriteSheet.NPC2Down,32,32,4);
				up = new Animation(SpriteSheet.NPC2Up,32,32,4);
				side = new Animation(SpriteSheet.NPC2Side,32,32,4);
			}
			else if(ani==2){
				down = new Animation(SpriteSheet.NPC3Down,32,32,4);
				up = new Animation(SpriteSheet.NPC3Up,32,32,4);
				side = new Animation(SpriteSheet.NPC3Side,32,32,4);
			}
			else if(ani==3){
				down = new Animation(SpriteSheet.NPC4Down,32,32,4);
				up = new Animation(SpriteSheet.NPC4Up,32,32,4);
				side = new Animation(SpriteSheet.NPC4Side,32,32,4);
			}
			else if(ani==4){
				down = new Animation(SpriteSheet.NPC5Down,32,32,4);
				up = new Animation(SpriteSheet.NPC5Up,32,32,4);
				side = new Animation(SpriteSheet.NPC5Side,32,32,4);
			}
		}
	}
	
	public void update(){

		updateAnim();
		if(x<=0)x=1;
		if(mode==0){
			RandomWalking();
		}else if(mode==1){
			Chasing();
		}else if(mode==2){
			Boss();
		}
		//if stuck in wall, delete
		if (collision(0,0)&&collision(0,1)&&collision(1,0)&&collision(1,1))remove();
		
		updateStats();
	}


	private void Boss() {
		
	}

	private void updateStats() {
		for(int i = 0;i<level.Projectiles.size();i++){
			if(level.Projectiles.get(i) instanceof WizardProjectile){
				for(int yp = 0; yp < 32; yp++){
					for(int xp = 0; xp < 32; xp++){
						if((int)level.Projectiles.get(i).x+xp-16 == x && (int)level.Projectiles.get(i).y+yp-16 == y){
							if(level.Projectiles.get(i).dmg==0){
								health-=100;
							}else{
								health-=level.Projectiles.get(i).dmg;
							}
							level.Projectiles.get(i).remove();
							Emitter r = new ParticleEmitter((int)x+16, (int)y+16, 25, 50,Sprite.particleRed, level);
							level.addEntity(r);
						}
					}
				}
			}
		}
		if(health<=0){
			die();
		}
	}

	private void die() {
		for(int i = 0;i<rnd.nextInt(32)+32;i++){
			level.addEntity(new Exp((int)(x+(rnd.nextGaussian()*25)), (int)(y+(rnd.nextGaussian()*25)), new Sprite(rnd.nextInt(2)+1,(int)(0xdddddd+rnd.nextGaussian()*200))));
		}
		Emitter r = new ParticleEmitter((int)x+16, (int)y+16, 25, 100,new Sprite(3,0x660000), level);
		level.addEntity(r);
		int ItemNum=7;
		if(rnd.nextInt(ItemNum*5)==0){
			level.addEntity(new instantItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.potionHealth, 250,1));
		}
		else if(rnd.nextInt(ItemNum*5)==1){
			level.addEntity(new instantItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.potionStamina, 250,2));
		}
		else if(rnd.nextInt(ItemNum*5)==2){
			level.addEntity(new instantItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.potionMana, 250,3));
		}
		else if(rnd.nextInt(ItemNum*5)==3){
			level.addEntity(new useableItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.foodCake, 250,1));
		}
		else if(rnd.nextInt(ItemNum*5)==4){
			level.addEntity(new useableItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.foodCarrot, 250,1));
		}
		else if(rnd.nextInt(ItemNum*5)==5){
			level.addEntity(new useableItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.foodChicken, 250,1));
		}
		else if(rnd.nextInt(ItemNum*5)==6){
			level.addEntity(new useableItem(x+rnd.nextInt(16), y+rnd.nextInt(16), Sprite.foodChoc, 250,1));
		}
		remove();
	}

	private void Chasing() {
		int dist = (int) DistanceTo(level.Players.get(0));
		
		if(dist<=256){
			if(dist>=64){
				xa=Level.Players.get(0).x+rand[0]>x?1:Level.Players.get(0).x+rand[0]<x?-1:0;
				ya=Level.Players.get(0).y+rand[1]>y?1:Level.Players.get(0).y+rand[1]<y?-1:0;
				move(xa,ya);
			}else{
				move(xa,ya);
			}
		}
		
		// Sqrt(((x2-x1)^2)+((y2-y1)^2))
		if(RoF>0)RoF--;
		if(dist<=128){
			if(RoF<=0){
				try{
					double angle =  Math.atan2((double)Level.Players.get(0).y - y, (double)Level.Players.get(0).x - x);
					double dir = angle+(rnd.nextGaussian()/10);
					shoot(x, y, dir, 30,false);
					RoF = EnemyProjectile.RoF;
				}catch(java.lang.ArithmeticException e){
					
				}
			}
		}else RandomWalking();
	}

	private void updateAnim() {
		if(mode>=0&&mode<=1&&RndSpr){
			down.update();
			up.update();
			side.update();		
		}
	}

	private void RandomWalking() {
		if(atk==0){
			time++;  // time = 1s/60
			if(time>rnd.nextInt(10000)||collision(xa,ya)){
				xa = (rnd.nextInt(3)-1);
				ya = (rnd.nextInt(3)-1);
				time=0;
			}
			if(rnd.nextInt(5)==0 && time%120==0){
				xa=0;
				ya=0;
			}
			boolean col=false;
			for(int i = 0;i<level.npcs.size();i++){
				if((x+xa)>>5==(int)level.npcs.get(i).x>>5&&(y+ya)>>5==(int)level.npcs.get(i).y>>5){
					col=true;
					continue;
				}
			}
			if (xa != 0 || ya != 0 && !col){
				move(xa, ya);
			}
		}
	}

	public void render(Screen screen) {
		if(xa>0){
			flip=0;
			if(RndSpr){
				sprite = side.GetSprite();
			}
		}
		else if(xa<0){
			flip=1;
			if(RndSpr){
				sprite = side.GetSprite();
			}
		}
		else if(ya<0){
			if(RndSpr){
				sprite = up.GetSprite();
			}
		}
		else if(ya>0){
			if(RndSpr){
				sprite = down.GetSprite();
			}
		}
		//health
		screen.renderSprite(x+8, y-2, new Sprite(16,1,0xff0000), false);
		screen.renderSprite(x+8, y-2, new Sprite((health<0?0:health>maxHealth?maxHealth:((int)(((float)health/(float)maxHealth)*100)/6)),1,0x00ff00), false);
		screen.renderMob(x,y,sprite,flip);
	}
}
