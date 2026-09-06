package net.jimmynet.jamesindustries.datagen;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, JamesiumIndustries.MODID);
    }

    @Override
    protected void registerModels(
        BlockModelGenerators blockModels,
        ItemModelGenerators itemModels) {

        blockModels.createTrivialCube(ModBlocks.RED_ORE_BLOCK.get());

        itemModels.generateFlatItem(
            ModItems.MAGIC_CUBE.get(),
            ModelTemplates.FLAT_ITEM
        );

    }
}



