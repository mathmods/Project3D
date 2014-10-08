package org.github.mathmods.project3d;

public class BlockBedrock extends Block {

	@Override
	public void registerBlockTextures(TextureHelper t){
		this.blockIcon = t.getTexture("bedrock");
	}
	
}
