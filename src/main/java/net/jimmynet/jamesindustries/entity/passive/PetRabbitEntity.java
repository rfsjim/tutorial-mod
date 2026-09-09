package net.jimmynet.jamesindustries.entity.passive;

import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class PetRabbitEntity extends Rabbit {

    private static final long PETTING_COOLDOWN_TICKS = 200L;
    private long nextPetTime = 0L;
    
    public PetRabbitEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);
    }

    @Override 
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.isEmpty()) {

            if (!this.level().isClientSide() && this.level() instanceof ServerLevel serverLevel) {
                this.patRabbit(player, serverLevel);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    private void patRabbit(Player player, ServerLevel serverLevel) {
        
        long currentTime = this.level().getGameTime();

        if (currentTime >= this.nextPetTime) {

            this.nextPetTime = currentTime + PETTING_COOLDOWN_TICKS;

            this.heal(2.0F);
            player.heal(2.0F);

            serverLevel.sendParticles(
                ParticleTypes.HEART,
                this.getX(),
                this.getY()+ 0.5F,
                this.getZ() + 0.5F,
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        } else {
            serverLevel.playSound(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                SoundEvents.RABBIT_AMBIENT,
                SoundSource.NEUTRAL,
                0.5F,
                1.6F
            );
        }
    }
}