package com.bird.drugmod.world.feature;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.block.ModBlocks;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    //ConfiguredFeature描述世界生成时的结构和地形，例如矿物
    //CONFIGURED_FEATURE_REGISTRY存储ConfiguredFeature注册表。
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, DrugMod.MOD_ID);
    /*
        创建一个Supplier对象，提供一个包含两个OreConfiguration.TargetBlockState对象的列表
        OreConfiguration.TargetBlockState 描述矿物生成时候的目标方块和替代方块。
        其中第一个参数：RuleTest 表示了替代的规则。 第二个参数BlockState表示了替代的方块和方块的状态。
        OreFeatures.STONE_ORE_REPLACEABLES 表示替代的规则是替代：石头、花岗岩、安山岩
        OreFeatures.DEEPSLATE_ORE_REPLACEABLES 替代深渊的石头。
    */

    //熊矿石科
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_BEAR_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.BEAR_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_BEAR_ORE.get().defaultBlockState())
    ));

    //赤硝矿石科
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_NITER_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.NITER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_NITER_ORE.get().defaultBlockState())
    ));
    /*
     * 由于没有末地石的替代规则，这里使用了BlockMatchTest创建了一个匹配方块的规则。
     * 由于参数是末地石
     * 替换的自然是加入末地的石头
     * */
  /*  public static final Supplier<List<OreConfiguration.TargetBlockState>> END_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE),ModBlocks.BEAR_ORE.get().defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_ZIRCON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES,ModBlocks.BEAR_ORE.get().defaultBlockState())
    )); */

    /*
     * 注册描述bear_ore矿物的生成方式，即将我们刚刚写的内容注册。
     * ConfiguredFeature描述世界生成时的结构和地形，例如矿物
     * Feature.ORE 表示了生成时特定的类型。生成的逻辑。
     * OreConfiguration提供生成了额外数据，其中第一个参数是一个list<TargetBlockState>类型，第二个参数表示了每个矿脉的生成数量。
     * */
    public static final RegistryObject<ConfiguredFeature<?,?>> BEAR_ORE = CONFIGURED_FEATURES.register("bear_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_BEAR_ORES.get(), 8)));

    public static final RegistryObject<ConfiguredFeature<?,?>> NITER_ORE = CONFIGURED_FEATURES.register("niter_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_NITER_ORE.get(), 8)));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}

