package org.github.mathmods.project3d;

import java.util.Random;

public class World {

	public Chunk[][] chunks = new Chunk[20][20];
	public Chunk[][] spawnChunks;
	
	public Random random = new Random(11242356);
	
	public World(int chunk){
		spawnChunks = new Chunk[chunk][chunk];
		
		for(int x = 0; x<chunk; x++)for(int y = 0; y<chunk; y++){
			prepareChunks(spawnChunks[x][y], x, y);
			generateFlat(random, spawnChunks[x][y]);
		}
		
		
	}
	
	public void prepareChunks(Chunk chunk, int x, int y){
		chunk = new Chunk(x * 16, y * 16);
		spawnChunks[x][y] = chunk;
	}
	
	public void generateFlat(Random rand, Chunk generating){
		for(int x = 0; x < 16; x++){
			for(int z = 0; z < 16; z++){
				int grasslevely = 50 + rand.nextInt(3);
				int dirtlevels = 3 + rand.nextInt(3);
				generating.setBlock(Block.grass, x, grasslevely, z);
				for(int i = 0; i < dirtlevels; i++){
					generating.setBlock(Block.dirt, x, grasslevely-(i+1), z);
				}
				for(int y = 1; y < grasslevely - dirtlevels - 1; y++){
					generating.setBlock(Block.stone, x, y, z);
				}
				generating.setBlock(Block.bedrock, x, 0, z);
			}
		}	
	}
	
	//public void tryCreateChunks(Chunk chunk, int x, int y){
	//	if(chunks[x][y] == null){
	//		prepareChunks(chunk, x, y);
	//		generateFlat(random, chunk);
	//	}else{
	//		loadChunks(chunk)
	//	}
	//}
}
