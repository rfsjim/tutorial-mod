package net.jimmynet.jamesindustries.entity.passive;

import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class PetRabbitEntity extends Rabbit {
    
    public PetRabbitEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);
    }
}