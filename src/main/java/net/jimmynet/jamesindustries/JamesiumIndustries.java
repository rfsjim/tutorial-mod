package net.jimmynet.jamesindustries;

import net.jimmynet.jamesindustries.event.ModEvents;
import net.jimmynet.jamesindustries.registry.ModRegistry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(JamesiumIndustries.MODID)

public class JamesiumIndustries {
    
    // Define mod id in a common place for everything to reference
    public static final String MODID = "jamesiumindustries";

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public JamesiumIndustries(IEventBus modEventBus, ModContainer modContainer) {
        
        ModRegistry.Runner(modEventBus);
        ModEvents.Runner(modEventBus);
    }
}