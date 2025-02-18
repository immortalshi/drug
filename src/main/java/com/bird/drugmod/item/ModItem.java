package com.bird.drugmod.item;

import com.bird.drugmod.DrugMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {
    //创建Item对象
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DrugMod.MOD_ID);

    //钵
    public static final RegistryObject<Item> CHINA_BOWL = ITEMS.register("china_bowl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //杵
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //注册
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
