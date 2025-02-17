package com.bird.drugmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab DRUG_TAB = new CreativeModeTab("drugtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.CHINA_BOWL.get());
        }
    };
}
