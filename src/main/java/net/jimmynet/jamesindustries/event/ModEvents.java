package net.jimmynet.jamesindustries.event;

import java.util.List;
import java.util.Set;

import net.jimmynet.jamesindustries.datagen.ModLootTableSubProvider;
import net.jimmynet.jamesindustries.datagen.ModModelProvider;
import net.jimmynet.jamesindustries.datagen.ModRecipeProvider;
import net.jimmynet.jamesindustries.entity.ModEntities;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModEvents {

    public static void Runner(IEventBus modEventBus) {

        // Listener to run the dynamic data generation
        modEventBus.addListener(ModEvents::gatherData);

        // Register the item to a creative tab
        modEventBus.addListener(ModEvents::addCreative);

        // Register rabbit entity attributes
        modEventBus.addListener(ModEvents::registerAttributes);
    }

    // Add the red ore block item to the building blocks tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.RED_ORE_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.PET_RABBIT_SPAWN_EGG);
        }
    }

    // Creates the data provider
    private static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
        event.createProvider(ModRecipeProvider.Runner::new);
        event.createProvider(
            (output, lookupProvider) -> new LootTableProvider(
                output,
                Set.of(),
                List.of(
                    new LootTableProvider.SubProviderEntry(
                        ModLootTableSubProvider::new,
                        LootContextParamSets.EMPTY
                    )
                ),
                lookupProvider
            )
        );
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
            ModEntities.PET_RABBIT.get(),
            Rabbit.createAttributes().build()
        );
    }
}
