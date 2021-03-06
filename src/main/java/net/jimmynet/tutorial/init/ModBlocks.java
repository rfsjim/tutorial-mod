package net.jimmynet.tutorial.init;

import net.jimmynet.tutorial.Reference;
import net.jimmynet.tutorial.block.BlockTutorial;
import net.jimmynet.tutorial.lib.Names;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static BlockTutorial tutorialBlock;
	
	public static void init()
	{
		
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, Names.TUTORIAL_BLOCK);
		tutorialBlock = new BlockTutorial();
		tutorialBlock.setRegistryName(location);
		GameRegistry.register(tutorialBlock);
		GameRegistry.register(new ItemBlock(tutorialBlock), location);
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher)
	{
		
		Item item = Item.getItemFromBlock(tutorialBlock);
		ModelResourceLocation model = new ModelResourceLocation(Reference.RESOURCE_PREFIX + Names.TUTORIAL_BLOCK, "inventory");
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
	}
}
