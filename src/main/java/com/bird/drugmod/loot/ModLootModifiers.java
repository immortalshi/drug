package com.bird.drugmod.loot;


import com.bird.drugmod.DrugMod;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DrugMod.MOD_ID);

    // 增加一个编码器和解码器
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_TURTLE_MEAT =
            LOOT_MODIFIER_SERIALIZERS.register("add_turtle_meat",TurtleMeatFromTurtleAdditionModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
