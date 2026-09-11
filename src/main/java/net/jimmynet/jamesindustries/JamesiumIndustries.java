package net.jimmynet.jamesindustries;

import net.jimmynet.jamesindustries.block.ModBlocks;
import net.jimmynet.jamesindustries.datagen.ModModelProvider;
import net.jimmynet.jamesindustries.datagen.ModRecipeProvider;
import net.jimmynet.jamesindustries.datagen.ModLootTableSubProvider;
import net.jimmynet.jamesindustries.item.ModItems;
import net.jimmynet.jamesindustries.item.ModCreativeModeTabs;
import net.jimmynet.jamesindustries.entity.ModEntities;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.entity.animal.rabbit.Rabbit;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(JamesiumIndustries.MODID)

public class JamesiumIndustries {
    
    // Define mod id in a common place for everything to reference
    public static final String MODID = "jamesiumindustries";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public JamesiumIndustries(IEventBus modEventBus, ModContainer modContainer) {
        
        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);

        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // Register entities
        ModEntities.ENTITY_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (JamesiumIndustries) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Listener to run the dynamic data generation
        modEventBus.addListener(this::gatherData);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register rabbit entity attributes
        modEventBus.addListener(this::registerAttributes);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // Creates the data provider
    public void gatherData(GatherDataEvent.Client event) {
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

    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
            ModEntities.PET_RABBIT.get(),
            Rabbit.createAttributes().build()
        );
    }

    
    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the red ore block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.RED_ORE_ITEM);
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.PET_RABBIT_SPAWN_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}