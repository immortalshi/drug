package com.bird.drugmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

public class BambooCoal extends Item implements Vanishable {
    public BambooCoal(Properties pProperties) {
        super(pProperties);
    }

    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 20 * 180;
    }
}
