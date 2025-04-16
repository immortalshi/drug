package com.bird.drugmod.recipe;

import com.bird.drugmod.DrugMod;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister
            .create(ForgeRegistries.RECIPE_TYPES, DrugMod.MOD_ID);
    public static final RegistryObject<RecipeType<DryingRackRecipe>> DRYING_RACK = RECIPE_TYPES.register("drying_rack",
            () -> registerRecipeType("drying_rack"));

    // Dynamic Names

    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return new RecipeType<>() {
            public String toString() {
                return DrugMod.MOD_ID + ":" + identifier;
            }
        };
    }

    // Serializers
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, DrugMod.MOD_ID);

    public static final RegistryObject<DryingRackSerializer> DRYING_RACK_SERIALIZER = RECIPE_SERIALIZERS
            .register("drying_rack", DryingRackSerializer::new);

//    public static final RecipeBookType RECIPE_TYPE_OVEN = RecipeBookType.create("OVEN");
}
//{
//    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
//            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DrugMod.MOD_ID);
//
//    public static final RegistryObject<RecipeSerializer<DryingRackRecipe>> DRYING_RACK =
//            SERIALIZERS.register("drying_rack", () -> DryingRackRecipe.Serializer.INSTANCE);
//
////    public static final RegistryObject<DryingRackSerializer> DRYING_RACK_SERIALIZER = RECIPE_SERIALIZERS
////            .register("drying_rack", DryingRackSerializer::new);
//
//    public static void register(IEventBus eventBus) {
//        SERIALIZERS.register(eventBus);
//    }
//}
