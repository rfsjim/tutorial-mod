package net.jimmynet.tutorial.block;

import net.jimmynet.tutorial.Reference;
import net.jimmynet.tutorial.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockTutorial extends Block {

	public BlockTutorial()
	{
		
		super(Material.ROCK);
		setHardness(5.0F);
		setResistance(10.0F);
		setSoundType(SoundType.LADDER);
		setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "tile." + Reference.RESOURCE_PREFIX + Names.TUTORIAL_BLOCK;
	}

}
