package org.github.mathmods.project3d;

public class Chunk {
	public static Block[][][] chunk = new Block[16][256][16];
	
	public int x;
	public int z;
	
	private World world;
	
	public Chunk(int x, int z, World world){
		this.x = x;
		this.z = z;
		this.world = world;
	}
	
	public void setBlock(Block block, int x, int y, int z){
		block.x = this.x + x;
		block.y = y;
		block.z = this.z + z;
		chunk[x][y][z] = block;
	}
	
	public void setToAir(int x, int y, int z){
		chunk[x][y][z] = null;
	}
	
	public Block getBlock(int x, int y, int z){
		if(chunk[x][y][z] != null){
			return chunk[x][y][z];
		}else{
			return null;
		}
	}
	
	public Position[] getDrawable(Chunk c){
		Position[] p = new Position[100000];
		int nextDraw = 0;
		for(int x = 0;x<16;x++){
			for(int z = 0;z<16;z++){
				for(int y = 0; y<256;y++){
					if(world.getBlock(this.x+x, y, this.z+z) != null){
						if(world.getBlock(this.x+x, y, this.z+z+1) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}else if(world.getBlock(this.x+x, y, this.z+z-1) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}else if(world.getBlock(this.x+x+1, y, this.z+z) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}else if(world.getBlock(this.x+x-1, y, this.z+z-1) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}else if(world.getBlock(this.x+x, y+1, this.z+z-1) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}else if(world.getBlock(this.x+x, y-1, this.z+z-1) == null){
							p[nextDraw] = new Position(this.x+x, y, this.z+z);
							System.out.println("Loaded: " + world.getBlock(p[nextDraw]));
							nextDraw++;
						}
					}
				}
			}
		}
		return p;
	}
}
