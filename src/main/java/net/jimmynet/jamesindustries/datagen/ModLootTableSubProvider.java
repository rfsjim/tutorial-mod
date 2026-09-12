package net.jimmynet.jamesindustries.datagen;

import java.util.function.BiConsumer;

import net.jimmynet.jamesindustries.loot.ModLootTableKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

/**
 * 
 * ModLootTableSubProvider Custom Loot Tables
 */
public class ModLootTableSubProvider implements LootTableSubProvider {
    protected final HolderLookup.Provider lookupProvider;
    
    public ModLootTableSubProvider(HolderLookup.Provider lookupProvider) {
        this.lookupProvider = lookupProvider;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
    
            LootTable.Builder table = LootTable.lootTable()
            .withPool(
                LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1F))
                .add(LootItem.lootTableItem(Items.IRON_NUGGET)
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))))
                .add(LootItem.lootTableItem(Items.GOLD_NUGGET))
                .add(LootItem.lootTableItem(Items.COPPER_NUGGET))
                .add(LootItem.lootTableItem(Items.EMERALD))
                .add(LootItem.lootTableItem(Items.DIAMOND))
            );
        
        consumer.accept(ModLootTableKeys.PET_RABBIT_GIFT, table);
    }
}
