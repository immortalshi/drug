package com.bird.drugmod.item.custom;

import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Timer;
import java.util.TimerTask;

public class PestleItem extends SwordItem implements Vanishable {

    public PestleItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);
        pTarget.setNoActionTime(100);
        Mob mob = (Mob)pTarget;
        mob.setNoAi(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                pTarget.addEffect(new MobEffectInstance(MobEffects.LUCK, 20 * 30));
                mob.setNoAi(false);
            }
        }, 5000);
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
