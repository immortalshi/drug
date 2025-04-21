package com.bird.drugmod.item;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.item.custom.BambooCoal;
import com.bird.drugmod.item.custom.Coin;
import com.bird.drugmod.item.custom.Detector;
import com.bird.drugmod.item.custom.PestleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
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
//    public static final RegistryObject<Item> PESTLE_BOWL = ITEMS.register("pestle_bowl",
//            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //熊原矿
    public static final RegistryObject<Item> RAW_BEAR = ITEMS.register("raw_bear",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //次元熊锭
    public static final RegistryObject<Item> BEAR_INGOT = ITEMS.register("bear_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //赤硝原矿
    public static final RegistryObject<Item> RAW_NITER = ITEMS.register("raw_niter",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //赤霄粉
    public static final RegistryObject<Item> NITER_DUST = ITEMS.register("niter_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //硫磺粉
    public static final RegistryObject<Item> BRIMSTONE_DUST = ITEMS.register("brimstone_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //探测器
    public static final RegistryObject<Item> DETECTOR = ITEMS.register("detector",
            () -> new Detector(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //铜币
    public static final RegistryObject<Item> COPPER_COIN = ITEMS.register("copper_coin",
            () -> new Coin(Tiers.STONE, 8, 0.2f, new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //银币
    public static final RegistryObject<Item> IRON_COIN = ITEMS.register("iron_coin",
            () -> new Coin(Tiers.IRON, 10, 0.4f, new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //金币
    public static final RegistryObject<Item> GOLD_COIN = ITEMS.register("gold_coin",
            () -> new Coin(Tiers.GOLD, 12, 1.0f, new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //星星币
    public static final RegistryObject<Item> STAR_COIN = ITEMS.register("star_coin",
            () -> new Coin(Tiers.NETHERITE, 72, 1.2f, new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB).rarity(Rarity.EPIC)));

    //次元珍珠
    public static final RegistryObject<Item> DIM_PEARL = ITEMS.register("dim_pearl",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //熊熊球
    public static final RegistryObject<Item> BEAR_BALL = ITEMS.register("bear_ball",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //筛子
    public static final RegistryObject<Item> SIEVE = ITEMS.register("sieve",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //竹炭
    public static final RegistryObject<Item> Bamboo_Coal = ITEMS.register("bamboo_coal",
            () -> new BambooCoal(new Item.Properties().tab(ModCreativeModeTab.DRUG_TAB)));

    //注册
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
