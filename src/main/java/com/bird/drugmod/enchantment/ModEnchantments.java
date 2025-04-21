package com.bird.drugmod.enchantment;

import com.bird.drugmod.DrugMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DrugMod.MOD_ID);


    //除你武器
    public static RegistryObject<Enchantment> REMOVE_SLOT =
            ENCHANTMENTS.register("remove_slot",
                    () -> new RemoveSlotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.BOW, EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND));

    //置换
    public static RegistryObject<Enchantment> CHANGE_SLOT =
            ENCHANTMENTS.register("change_slot",
                    () -> new ChangeSlotEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.TRIDENT, EquipmentSlot.MAINHAND,EquipmentSlot.OFFHAND));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
