package org.github.mathmods.project3d;

public class BlockDirt extends Block {

	public BlockDirt(){
		y = -1;
	}
	
	@Override
	public void registerBlockTextures(TextureHelper t){
		this.blockIcon = t.getTexture("dirt");
	}
	
}
