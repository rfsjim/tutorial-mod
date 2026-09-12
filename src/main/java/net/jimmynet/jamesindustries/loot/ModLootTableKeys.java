package net.jimmynet.jamesindustries.loot;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * 
 * ModLootTableKeys Shared Runtime Data Definition as a reference for LootTableKeys
 */
public final class ModLootTableKeys {

    public static final ResourceKey<LootTable> PET_RABBIT_GIFT = ResourceKey.create(
        Registries.LOOT_TABLE,
        Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_gift")
    );

    private ModLootTableKeys() {}
}