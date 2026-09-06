package net.jimmynet.jamesindustries.item;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.block.ModBlocks;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    
    // Create a Deferred Register to hold Items which will all be registered under the "jamesiumindustries" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(JamesiumIndustries.MODID);

    // Creates a new food item with the id "jamesiumindustries:magic_cube", nutrition 1 and saturation 2
    public static final DeferredItem<Item> MAGIC_CUBE = ITEMS.registerSimpleItem("magic_cube", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Creates a new BlockItem with the id "jamesiumindustries:red_ore_block", combining the namespace and path
    public static final DeferredItem<BlockItem> RED_ORE_ITEM = ITEMS.registerSimpleBlockItem("red_ore_block", ModBlocks.RED_ORE_BLOCK);

    // Constructor for ModItems
    private ModItems() {}
}
