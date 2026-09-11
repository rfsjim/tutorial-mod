package net.jimmynet.jamesindustries.entity.passive;

import net.jimmynet.jamesindustries.JamesiumIndustries;

import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;

public class PetRabbitEntity extends Rabbit {

    private static final long PETTING_COOLDOWN_TICKS = 200L;
    private long nextPetTime = 0L;
    public int nuggetTime;
    
    public PetRabbitEntity(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);

        this.nuggetTime = this.random.nextInt(600) + 200;
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

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level() instanceof ServerLevel serverLevel) {
            if (this.isAlive() && !this.isBaby() && this.getVariant() != Rabbit.Variant.EVIL && --this.nuggetTime <=0)
            {
                this.dropGift(serverLevel);
            }
        }
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

    private void dropGift(ServerLevel serverLevel) {

        ResourceKey<LootTable> lootTableId = ResourceKey.create(
            Registries.LOOT_TABLE,
            Identifier.fromNamespaceAndPath(JamesiumIndustries.MODID, "pet_rabbit_gift")
        );

        if (this.dropFromGiftLootTable(serverLevel, lootTableId, this::spawnAtLocation)) {
            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0F,
                (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F
            );

            serverLevel.sendParticles(
                ParticleTypes.EGG_CRACK,
                this.getX(),
                this.getY()+ 0.5F,
                this.getZ() + 0.5F,
                5,
                0.2,
                0.2,
                0.2,
                0
            );
        }
        
        this.nuggetTime = this.random.nextInt(600) + 200;
    }
}