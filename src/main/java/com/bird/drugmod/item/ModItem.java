package com.bird.drugmod.item;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.item.custom.Detector;
import com.bird.drugmod.item.custom.PestleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
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
            () -> new PestleItem(Tiers.DIAMOND, -1, 0.6f, new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //药钵
    public static final RegistryObject<Item> PESTLE_BOWL = ITEMS.register("pestle_bowl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //熊原矿
    public static final RegistryObject<Item> RAW_BEAR = ITEMS.register("raw_bear",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //次元熊锭
    public static final RegistryObject<Item> BEAR_INGOT = ITEMS.register("bear_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //探测器
    public static final RegistryObject<Item> DETECTOR = ITEMS.register("detector",
            () -> new Detector(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //注册
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
