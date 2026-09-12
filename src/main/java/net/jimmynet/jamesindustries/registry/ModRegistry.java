package net.jimmynet.jamesindustries.registry;

import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.item.ModCreativeModeTabs;
import net.jimmynet.jamesindustries.item.ModItems;
import net.neoforged.bus.api.IEventBus;

public class ModRegistry {

    public static void Runner(IEventBus modEventBus) {
        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register entities
        ModEntities.ENTITY_TYPES.register(modEventBus);
    }
}
