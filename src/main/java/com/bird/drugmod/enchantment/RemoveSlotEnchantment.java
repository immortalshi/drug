package com.bird.drugmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class RemoveSlotEnchantment extends Enchantment {
    protected RemoveSlotEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_) {
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


        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getMainHandItem()));
        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getOffhandItem()));
        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getItemBySlot(EquipmentSlot.HEAD)));
        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getItemBySlot(EquipmentSlot.CHEST)));
        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getItemBySlot(EquipmentSlot.LEGS)));
        world.addFreshEntity(new ItemEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, pTarget.getItemBySlot(EquipmentSlot.FEET)));

        pTarget.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        pTarget.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
        pTarget.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        pTarget.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
        pTarget.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
        pTarget.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
    }
}
