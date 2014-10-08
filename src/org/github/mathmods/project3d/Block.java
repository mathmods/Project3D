package org.github.mathmods.project3d;

import org.newdawn.slick.opengl.Texture;

public class Block {

	public Texture blockIcon;
	public int x;
	public int y;
	public int z;
	
	public static Block stone = new Block();
	public static Block grass = new BlockGrass();
	public static Block dirt = new BlockDirt();
	public static Block bedrock = new BlockBedrock();
	
	public Block(){
		x = 0;
		y = 0;
		z = 0;
		
		registerBlockTextures(new TextureHelper());
	}
	
	public Texture getIcon(int side, int meta){
		return blockIcon;
	}
	
	public void registerBlockTextures(TextureHelper t){
		blockIcon = t.getTexture("stone");
	}
}
