package com.bird.drugmod.item.custom;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public class PestleItem extends SwordItem implements Vanishable {

    public PestleItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);

        System.out.println(pStack);
        System.out.println(pTarget);
        System.out.println(pAttacker);

        pTarget.setNoActionTime(100);

        Mob mob = (Mob)pTarget;
        mob.setNoAi(true);

        Block block = pTarget.getBlockStateOn().getBlock();

        mob.getCommandSenderWorld().scheduleTick(block, 100, () -> {
            mob.setNoAi(false);  // 5秒后恢复AI
        });
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
