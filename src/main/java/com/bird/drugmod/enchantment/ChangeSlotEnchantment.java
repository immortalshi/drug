package com.bird.drugmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class ChangeSlotEnchantment extends Enchantment {

    protected ChangeSlotEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public void doPostAttack(LivingEntity pAttack, Entity pTarget, int level) {

        LivingEntity target = (LivingEntity) pTarget;

        Level world = pAttack.getLevel();

        BlockPos pos = pAttack.blockPosition();

        this.removeSlot(pAttack, target, world, pos);

        super.doPostAttack(pAttack, pTarget, level);
    }

    @Override
    public int getMaxLevel() {
        return super.getMaxLevel();
    }

    public void removeSlot(LivingEntity pAttack, LivingEntity pTarget, Level world, BlockPos pos) {

        ItemStack tarMainHand = pTarget.getMainHandItem();
        ItemStack tarOffHand = pTarget.getOffhandItem();
        ItemStack tarHead = pTarget.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack tarChest = pTarget.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack tarLegs = pTarget.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack tarFeet = pTarget.getItemBySlot(EquipmentSlot.FEET);

        ItemStack atkMainHand = pAttack.getMainHandItem();
        ItemStack atkOffHand = pAttack.getOffhandItem();
        ItemStack atkHead = pAttack.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack atkChest = pAttack.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack atkLegs = pAttack.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack atkFeet = pAttack.getItemBySlot(EquipmentSlot.FEET);

        pAttack.setItemSlot(EquipmentSlot.MAINHAND, tarMainHand);
        pAttack.setItemSlot(EquipmentSlot.OFFHAND, tarOffHand);
        pAttack.setItemSlot(EquipmentSlot.HEAD, tarHead);
        pAttack.setItemSlot(EquipmentSlot.CHEST, tarChest);
        pAttack.setItemSlot(EquipmentSlot.LEGS, tarLegs);
        pAttack.setItemSlot(EquipmentSlot.FEET, tarFeet);

        pTarget.setItemSlot(EquipmentSlot.MAINHAND, atkMainHand );
        pTarget.setItemSlot(EquipmentSlot.OFFHAND, atkOffHand);
        pTarget.setItemSlot(EquipmentSlot.HEAD, atkHead);
        pTarget.setItemSlot(EquipmentSlot.CHEST, atkChest);
        pTarget.setItemSlot(EquipmentSlot.LEGS, atkLegs);
        pTarget.setItemSlot(EquipmentSlot.FEET, atkFeet);


    }
}
