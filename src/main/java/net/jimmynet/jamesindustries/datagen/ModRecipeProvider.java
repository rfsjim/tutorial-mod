package net.jimmynet.jamesindustries.datagen;

import java.util.concurrent.CompletableFuture;

import net.jimmynet.jamesindustries.JamesiumIndustries;
import net.jimmynet.jamesindustries.item.ModItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import net.neoforged.neoforge.common.Tags.Items;

public class ModRecipeProvider extends RecipeProvider  {
    
    protected ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
    }

    @Override 
    protected void buildRecipes() {

        // Shapeless for Pet Rabbit Spawn Egg
        ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ModItems.PET_RABBIT_SPAWN_EGG)
        .requires(Items.EGGS)
        .requires(Items.CROPS_CARROT)
        .unlockedBy("has_chicken_egg", this.has(Items.EGGS))
        .unlockedBy("has_carrot", this.has(Items.CROPS_CARROT))
        .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override 
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return JamesiumIndustries.MODID + "_recipes";
        }
    }
}