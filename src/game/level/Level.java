package game.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.entity.Entity;
import game.entity.ParticleTrail;
import game.entity.Projectile;
import game.entity.particle;
import game.entity.item.item;
import game.entity.mob.Boss;
import game.entity.mob.NPC;
import game.entity.mob.Player;
import game.graphics.Screen;
import game.input.Keyboard;
import game.level.tiles.tile;

public class Level {

	protected int width, height, num=0;
	protected int[] tilesInt;
	public int[] tiles;
	int[] enemies;
	protected Random rnd = new Random();
	public Keyboard key;
	
	public static List<Player> Players = new ArrayList<Player>();
	public List<Entity> Entities = new ArrayList<Entity>();
	public List<Projectile> Projectiles = new ArrayList<Projectile>();
	public List<particle> particles = new ArrayList<particle>();
	public List<ParticleTrail> ParticleTrails = new ArrayList<ParticleTrail>();
	public List<item> items = new ArrayList<item>();
	public List<NPC> npcs = new ArrayList<NPC>();
	public List<Boss> Boss = new ArrayList<Boss>();
	
	
	
	
	public Level(int width, int height,Keyboard key){
		this.key = key;
		this.width = width;
		this.height = height;
		tilesInt = new int[width*height];
		generateLevel();
	}

	public Level(String pathT,String pathE,Keyboard key) {
		this.key = key;
		loadLevel(pathT,pathE);
		generateLevel();
	}
	
	private void removeEntities(){
		for(int i=0;i<Entities.size();i++){
			if(Entities.get(i).isRemoved())Entities.remove(i);
		}
		for(int i=0;i<Projectiles.size();i++){
			if(Projectiles.get(i).isRemoved())Projectiles.remove(i);
		}
		for(int i=0;i<particles.size();i++){
			if(particles.get(i).isRemoved())particles.remove(i);
		}
		for(int i=0;i<ParticleTrails.size();i++){
			if(ParticleTrails.get(i).isRemoved())ParticleTrails.remove(i);
		}
		for(int i=0;i<items.size();i++){
			if(items.get(i).isRemoved())items.remove(i);
		}
		for(int i=0;i<npcs.size();i++){
			if(npcs.get(i).isRemoved())npcs.remove(i);
		}
		for(int i=0;i<Boss.size();i++){
			if(Boss.get(i).isRemoved())Boss.remove(i);
		}
		for(int i=0;i<Players.size();i++){
			if(Players.get(i).isRemoved())Players.remove(i);
		}
	}
	
	public void addEntity(Entity e){
		e.init(this);
		if (e instanceof particle){
			particles.add((particle)e);
		}
		else if (e instanceof ParticleTrail){
			ParticleTrails.add((ParticleTrail)e);
		}
		else if(e instanceof Projectile){
			Projectiles.add((Projectile)e);
		}
		else if(e instanceof item){
			items.add((item)e);
		}
		else if(e instanceof Player){
			Players.add((Player)e);
		}
		else if(e instanceof NPC){
			npcs.add((NPC)e);
		}
		else if(e instanceof Boss){
			Boss.add((Boss)e);
		}
		else{
			Entities.add(e);
		}
	}

	protected void loadLevel(String pathT,String pathE) {
		loadLevel(pathT,pathE);
		generateLevel();
	}

	protected void generateLevel() {
		
	}
	
	public List<Projectile> getProjectiles(){
		return Projectiles;
	}
	
	public void update(){
		for(int i=0;i<Players.size();i++){
			Players.get(i).update();
		}
		for(int i=0;i<Entities.size();i++){
			Entities.get(i).update();
		}
		for(int i=0;i<Projectiles.size();i++){
			Projectiles.get(i).update();
		}
		for(int i=0;i<particles.size();i++){
			particles.get(i).update();
		}
		for(int i=0;i<ParticleTrails.size();i++){
			ParticleTrails.get(i).update();
		}
		for(int i=0;i<items.size();i++){
			items.get(i).update();
		}
		for(int i=0;i<npcs.size();i++){
			if(DistanceBetween((Entity)Players.get(0),(Entity)npcs.get(i))<512){
				npcs.get(i).update();
			}
			if((i+1)<npcs.size()){
				if(npcs.get(i+1).y<npcs.get(i).y){
					NPC temp = npcs.get(i);
					npcs.set(i, npcs.get(i+1));
					npcs.set(i+1, temp);
				}
			}
		}
		for(int i=0;i<Boss.size();i++){
			Boss.get(i).update();
		}
		removeEntities();
	}
	
	public double DistanceBetween(Entity O, Entity T){
		int dist = (int) Math.sqrt(Math.pow(O.x-T.x, 2)+Math.pow(O.y-T.y,2));
		return dist;
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 5;
		int x1 = (xScroll + Screen.width + 32) >> 5;
		int y0 = yScroll >> 5;
		int y1 = (yScroll + Screen.height + 32) >> 5;
		for (int y = y0; y < y1; y++){
			for (int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i=0;i<Entities.size();i++){
			Entities.get(i).render(screen);
		}
		for(int i=0;i<particles.size();i++){
			if(DistanceBetween((Entity)Players.get(0),(Entity)particles.get(i))<256){
				particles.get(i).render(screen);
			}
		}
		for(int i=0;i<items.size();i++){
			items.get(i).render(screen);
		}
		for(int i=0;i<Projectiles.size();i++){
			Projectiles.get(i).render(screen);
		}
		for(int i=0;i<ParticleTrails.size();i++){
			ParticleTrails.get(i).render(screen);
		}
		for(int i=0;i<Players.size();i++){
			Players.get(i).render(screen);
		}
		for(int i=0;i<npcs.size();i++){
			if(DistanceBetween((Entity)Players.get(0),(Entity)npcs.get(i))<256){
				npcs.get(i).render(screen);
			}
		}
		for(int i=0;i<Boss.size();i++){
			Boss.get(i).render(screen);
		}
	}
	
	public tile getTile(int x, int y){
		//set outside map to void
		if(x < 0 || y < 0 || x >= width || y >= height) return tile.voidTile;
		
		//Green
		if(tiles[x+y*width]==0xff00ff00)return tile.grass;
		if(tiles[x+y*width]==0xff00aa00)return tile.flower1;
		if(tiles[x+y*width]==0xff00aa01)return tile.flower2;
		if(tiles[x+y*width]==0xff00aa02)return tile.flower3;
		
		
		//Black
		if(tiles[x+y*width]==0xff000000)return tile.wallHS;
		if(tiles[x+y*width]==0xff000001)return tile.wallHG;
		if(tiles[x+y*width]==0xff000002)return tile.wallVL;
		if(tiles[x+y*width]==0xff000003)return tile.wallVR;
		if(tiles[x+y*width]==0xff000004)return tile.wallBL;
		if(tiles[x+y*width]==0xff000005)return tile.wallBR;
		if(tiles[x+y*width]==0xff000006)return tile.wallTL;
		if(tiles[x+y*width]==0xff000007)return tile.wallTR;
		if(tiles[x+y*width]==0xff000008)return tile.wallEL;
		if(tiles[x+y*width]==0xff000009)return tile.wallER;
		if(tiles[x+y*width]==0xff00000a)return tile.door;


		
		//Grey
		if(tiles[x+y*width]==0xff666666)return tile.stone;
		if(tiles[x+y*width]==0xff333333)return tile.rock;
		
		if(tiles[x+y*width]==0xffb26e00)return tile.crate;

		return tile.voidTile;
	}
	
	public boolean TileCollision(int x, int y, int size,int xo, int yo){
		boolean solid = false;

		for(int c = 0;c<4;c++){
			int xt =(x+c%2*size)+xo>>5;
			int yt =(y+c/2*size)+yo>>5;
			if(getTile((int)xt, (int)yt).solid())solid=true;
		}
		return solid;
	}
}
