package net.jimmynet.jamesindustries.item.tools;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.common.Tags;

public class ModToolMaterials {
    private ModToolMaterials() {}

    public static final ToolMaterial NETHER_MATERIAL = new ToolMaterial(
        BlockTags.INCORRECT_FOR_STONE_TOOL,
        225,
        5F,
        3,
        25,
        Tags.Items.BRICKS_NETHER);
}