package com.bird.drugmod.villager;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.block.ModBlocks;
import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    // 注册一个POI类型，POI用于定义一些特殊的方块。可以被村民感知和利用，例如床，工作台等。
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, DrugMod.MOD_ID);

    // 注册一个村民职业，村民职业定义了村民的工作类型。例如农民、牧夫等。
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, DrugMod.MOD_ID);

    // 这里可以添加具体的POI类型和村民职业的注册代码。例如：
    public static final RegistryObject<PoiType> SAFE_POI = POI_TYPES.register("safe_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SAFE.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> CAPITALIST = VILLAGER_PROFESSION.register("capitalist",
            () -> new VillagerProfession("capitalist", x -> x.get() == SAFE_POI.get(),
                    x -> x.get() == SAFE_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static final RegistryObject<PoiType> DRUG_POI = POI_TYPES.register("drug_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.PESTLE_BOWL.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> DRUG_MASTER = VILLAGER_PROFESSION.register("drug_master",
            () -> new VillagerProfession("drug_master", x -> x.get() == DRUG_POI.get(),
                    x -> x.get() == DRUG_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs(){
        try{
            // 获得PoiType类的一个registerBlockStates方法,通过invoke调用将JUMPY_BLOCK_POI添加到poiType类中。
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates",PoiType.class).invoke(null,SAFE_POI.get());

            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates",PoiType.class).invoke(null,DRUG_POI.get());
        }catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }


    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
