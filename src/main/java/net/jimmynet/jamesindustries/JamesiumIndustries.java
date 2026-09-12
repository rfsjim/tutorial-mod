package net.jimmynet.jamesindustries;

import net.jimmynet.jamesindustries.event.ModEvents;
import net.jimmynet.jamesindustries.registry.ModRegistry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(JamesiumIndustries.MODID)

public class JamesiumIndustries {
    
    public static final String MODID = "jamesiumindustries";

    public JamesiumIndustries(IEventBus modEventBus) {
        
        ModRegistry.register(modEventBus);
        ModEvents.registerListeners(modEventBus);
    }
}