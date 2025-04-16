package com.bird.drugmod.event;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.item.ModItem;
import com.bird.drugmod.villager.ModVillagers;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = DrugMod.MOD_ID)
public class ModEvents {

    //村民交易事件
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.CAPITALIST.get()) {
            //获取村民交易列表，1-5级村民的交易物品。
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            List<VillagerTrades.ItemListing> level1 = trades.get(1);
            List<VillagerTrades.ItemListing> level2 = trades.get(2);
            List<VillagerTrades.ItemListing> level3 = trades.get(3);
            List<VillagerTrades.ItemListing> level4 = trades.get(4);
            List<VillagerTrades.ItemListing> level5 = trades.get(5);

            //绿宝石换熊头
            level1.add((trader, rand) -> itemToItem(ModItem.BEAR_INGOT.get(), Items.EMERALD,  2, 1,16, 4));
            //绿宝石换铜
            level1.add((trader, rand) -> itemToItem(Items.COPPER_INGOT, Items.EMERALD,  1, 4,16, 4));
            //铜换绿宝石
            level1.add((trader, rand) -> itemToItem(Items.EMERALD, Items.COPPER_INGOT,  16, 1,16, 4));
            //绿宝石换铜币
            level1.add((trader, rand) -> itemToItem(ModItem.COPPER_COIN.get(), Items.EMERALD,  4, 1,16, 4));
            level1.add((trader, rand) -> itemToItem(Items.SHIELD, ModItem.COPPER_COIN.get(),  8, 1,16, 4));
            level1.add((trader, rand) -> itemToItem(Items.AMETHYST_SHARD, ModItem.COPPER_COIN.get(),  2, 1,16, 4));

            //绿宝石换铁
            level2.add((trader, rand) -> itemToItem(Items.IRON_INGOT, Items.EMERALD,  1, 2,16, 8));
            //铁换绿宝石
            level2.add((trader, rand) -> itemToItem(Items.EMERALD, Items.IRON_INGOT,  4, 1,16, 8));
            //铜币换铁币
            level2.add((trader, rand) -> itemToItem(ModItem.IRON_COIN.get(), ModItem.COPPER_COIN.get(),  10, 1,16, 8));
            //铁币换铜币
            level2.add((trader, rand) -> itemToItem(ModItem.COPPER_COIN.get(), ModItem.IRON_COIN.get(),  1, 5,16, 8));

            level2.add((trader, rand) -> itemToItem(Items.GLOWSTONE_DUST, ModItem.IRON_COIN.get(),  1, 8,16, 12));
            level2.add((trader, rand) -> itemToItem(Items.REDSTONE, ModItem.IRON_COIN.get(),  1, 8,16, 12));
            level2.add((trader, rand) -> itemToItem(Items.LAPIS_LAZULI, ModItem.IRON_COIN.get(),  1, 8,16, 12));
            level2.add((trader, rand) -> itemToItem(Items.IRON_INGOT, ModItem.IRON_COIN.get(),  1, 5,16, 12));
            level2.add((trader, rand) -> itemToItem(Items.GOLD_INGOT, ModItem.IRON_COIN.get(),  5, 1,16, 12));


            //绿宝石换金
            level3.add((trader, rand) -> itemToItem(Items.GOLD_INGOT, Items.EMERALD,  1, 3,16, 12));
            //金换绿宝石
            level3.add((trader, rand) -> itemToItem(Items.EMERALD, Items.GOLD_INGOT,  4, 1,16, 12));
            //铁币换金币
            level3.add((trader, rand) -> itemToItem(ModItem.GOLD_COIN.get(), ModItem.IRON_COIN.get(),  10, 1,16, 12));
            //金币换铁币
            level3.add((trader, rand) -> itemToItem(ModItem.IRON_COIN.get(), ModItem.GOLD_COIN.get(),  1, 5,16, 12));

            level3.add((trader, rand) -> itemsToItem(ModItem.COPPER_COIN.get(), ModItem.COPPER_COIN.get(),  64, 36, ModItem.GOLD_COIN.get(), 1,16, 12));

            level3.add((trader, rand) -> itemToItem(ModItem.COPPER_COIN.get(), ModItem.GOLD_COIN.get(),  1, 50,16, 12));
            level3.add((trader, rand) -> itemToItem(Items.DIAMOND, ModItem.GOLD_COIN.get(),  2, 1,16, 12));
            level3.add((trader, rand) -> itemToItem(Items.EMERALD, Items.DIAMOND,  1, 1,16, 12));

            level4.add((trader, rand) -> itemsToItem(ModItem.IRON_COIN.get(), ModItem.GOLD_COIN.get(),  5, 1, Items.DIAMOND, 1,16, 16));
            level4.add((trader, rand) -> itemsToItem(ModItem.IRON_COIN.get(), ModItem.GOLD_COIN.get(),  30, 5, Items.NETHERITE_INGOT, 1,16, 16));
            level4.add((trader, rand) -> itemToItem(Items.NETHERITE_INGOT, ModItem.GOLD_COIN.get(),  8, 1,16, 16));
            level4.add((trader, rand) -> itemToItem(Items.TRIDENT, ModItem.GOLD_COIN.get(),  6, 1,16, 16));
            level4.add((trader, rand) -> itemToItem(Items.DIAMOND_SWORD, ModItem.GOLD_COIN.get(),  3, 1,16, 16));
            level4.add((trader, rand) -> itemToItem(Items.DIAMOND_PICKAXE, ModItem.GOLD_COIN.get(),  4, 1,16, 16));

            level5.add((trader, rand) -> itemToItem(Items.NETHERITE_SWORD, ModItem.GOLD_COIN.get(),  12, 1,16, 20));
            level5.add((trader, rand) -> itemToItem(Items.NETHERITE_PICKAXE, ModItem.GOLD_COIN.get(),  15, 1,16, 20));
            level5.add((trader, rand) -> itemToItem(Items.WITHER_SKELETON_SKULL, ModItem.GOLD_COIN.get(),  3, 1,16, 20));
            level5.add((trader, rand) -> itemToItem(Items.ENCHANTED_GOLDEN_APPLE, ModItem.GOLD_COIN.get(),  32, 1,16, 20));
            level5.add((trader, rand) -> itemsToItem(ModItem.GOLD_COIN.get(), ModItem.GOLD_COIN.get(),  64, 36, ModItem.STAR_COIN.get(), 1,16, 20));
            level5.add((trader, rand) -> itemToItem(Items.TOTEM_OF_UNDYING, ModItem.GOLD_COIN.get(),  20, 1,16, 16));


        }
    }

    public static MerchantOffer itemToItem(ItemLike coin, ItemLike itemLike, int cost, int buy, int uses, int exp){
        return new CoinForItems(coin.asItem(), itemLike.asItem(), cost, buy, uses, exp).getOffer(null, null);
    }

    public static MerchantOffer itemsToItem(ItemLike fstCoin, ItemLike sedCoin, int fstCost, int sedCost, ItemLike itemLike, int cost, int uses, int exp){
        return new CoinsForItems(fstCoin.asItem(), sedCoin.asItem(), fstCost, sedCost, itemLike.asItem(), cost, uses, exp).getOffer(null, null);
    }

    //以物易物（单个）
    static class CoinForItems implements VillagerTrades.ItemListing {
        private final ItemStack coin;
        private final Item item;
        private final int cost;
        private final int buy;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public CoinForItems(ItemLike pCoin, ItemLike pItem, int pCost, int pBuy, int pMaxUses, int pVillagerXp) {
            this.coin = new ItemStack(pCoin);
            this.item = pItem.asItem();
            this.cost = pCost;
            this.buy = pBuy;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            ItemStack $$2 = new ItemStack(this.item, this.cost);
            return new MerchantOffer($$2, new ItemStack(this.coin.getItem(), this.buy), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    //以物易物（多个）
    static class CoinsForItems implements VillagerTrades.ItemListing {
        private final ItemStack fstCoin;
        private final ItemStack sedCoin;
        private final int fromCount;
        private final int emeraldCost;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public CoinsForItems(ItemLike pFstCoin, ItemLike pSedCoin, int pFromCount, Item pToItem, int pToCount, int pMaxUses, int pVillagerXp) {
            this(pFstCoin, pSedCoin, pFromCount, 1, pToItem, pToCount, pMaxUses, pVillagerXp);
        }

        public CoinsForItems(ItemLike pFstCoin, ItemLike pSedCoin, int pFromCount, int pEmeraldCost, Item pToItem, int pToCount, int pMaxUses, int pVillagerXp) {
            this.fstCoin = new ItemStack(pFstCoin);
            this.sedCoin = new ItemStack(pSedCoin);
            this.fromCount = pFromCount;
            this.emeraldCost = pEmeraldCost;
            this.toItem = new ItemStack(pToItem);
            this.toCount = pToCount;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(this.fstCoin.getItem(), this.fromCount), new ItemStack(this.sedCoin.getItem(), this.emeraldCost), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
