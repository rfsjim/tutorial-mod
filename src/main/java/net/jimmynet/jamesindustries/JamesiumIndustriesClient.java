package net.jimmynet.jamesindustries;

import net.jimmynet.jamesindustries.entity.ModEntities;

import net.minecraft.client.renderer.entity.RabbitRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = JamesiumIndustries.MODID, dist = Dist.CLIENT)

@EventBusSubscriber(modid = JamesiumIndustries.MODID, value = Dist.CLIENT)
public class JamesiumIndustriesClient {
    public JamesiumIndustriesClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent 
    static void onRegisterClientEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
            ModEntities.PET_RABBIT.get(),
            RabbitRenderer::new
        );
    }
}
