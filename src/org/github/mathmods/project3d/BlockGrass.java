package org.github.mathmods.project3d;

import org.newdawn.slick.opengl.Texture;

public class BlockGrass extends Block {

	Texture top;
	
	Texture side;
	
	@Override
	public Texture getIcon(int side, int meta){
		switch(side){
		case 1:
			return top;
		case 0:
			return blockIcon;
		default:
			return this.side;
		}
	}
	
	@Override
	public void registerBlockTextures(TextureHelper t){
		blockIcon = t.getTexture("dirt");
		top = t.getTexture("grass_top");
		side = t.getTexture("grass_side");
	}
	
}
