package net.jimmynet.jamesindustries.block;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    // Create a Deferred Register to hold Blocks which will all be registered under the "jamesiumindustries" namespace
    public static final DeferredRegister.Blocks BLOCKS =
        DeferredRegister.createBlocks(JamesiumIndustries.MODID);

    // Creates a new Block with the id "jamesiumindustries:red_ore_block", combining the namespace and path
    public static final DeferredBlock<Block> RED_ORE_BLOCK = 
        BLOCKS.registerSimpleBlock(
            "red_ore_block",
            p -> p.mapColor(MapColor.STONE)
        );

    // Constructor for ModBlocks
    private ModBlocks() {}
}