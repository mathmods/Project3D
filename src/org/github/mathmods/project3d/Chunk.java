package org.github.mathmods.project3d;

public class Chunk {
	public static Block[][][] chunk = new Block[16][256][16];
	
	public int x;
	public int z;
	
	public Chunk(int x, int z){
		this.x = x;
		this.z = z;
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
}
