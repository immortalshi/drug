package com.bird.drugmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.w3c.dom.Text;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Coin extends Item {
    private Tiers tiers;
    public List<MobEffectInstance> EFFECTS;


    public Coin(Tiers tier, int nutrition, float saturationMod, Properties pProperties) {
        super(pProperties.food(new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturationMod)
                .alwaysEat().build()));
        this.tiers = tier;
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide) {
            pEntityLiving.curePotionEffects(pStack);
        }

        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pEntityLiving instanceof Player && !((Player) pEntityLiving).getAbilities().instabuild) {
            Player player = (Player) pEntityLiving;
            player.getFoodData().eat(this, pStack); // 玩家食用物品时恢复饥饿值和饱食度
            for (MobEffectInstance effect : EFFECTS) {
                player.addEffect(new MobEffectInstance(effect));
            }
            pStack.shrink(1);
        }

        return pStack;
    }


    public void setImmData() {
//        player.sendSystemMessage(Component.literal(this.tiers + "材质"));
        switch (this.tiers.toString()) {
            case "STONE":
                setEffectData(0, 0.2f);
                break;
            case "IRON":
                setEffectData(1, 0.5f);
                break;
            case "GOLD":
                setEffectData(2, 1.0f);
                break;
            case "NETHERITE":
                setEffectData(3, 9.0f);
                break;
        }
    }

    public void setEffectData(int level, float time) {
        this.EFFECTS = Lists.newArrayList(
                new MobEffectInstance(MobEffects.NIGHT_VISION, setEffTime(time), setEffLv(level), true, false, true),
                new MobEffectInstance(MobEffects.ABSORPTION, setEffTime(time), setEffLv(level), true, false, true),
                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, setEffTime(time), setEffLv(level), true, false, true),
                new MobEffectInstance(MobEffects.DAMAGE_BOOST, setEffTime(time), setEffLv(level), true, false, true),
                new MobEffectInstance(MobEffects.HEAL, setEffTime(time), setEffLv(level), true, false, true)
                );
        if(Objects.equals(this.tiers.toString(), "NETHERITE")) {
            this.EFFECTS.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, setEffTime(time), setEffLv(level), true, false, true));
        }





//        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, setEffTime(time, player), setEffLv(level, player), true, false, true));
//        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, setEffTime(time, player), setEffLv(level, player), true, false, true));
//        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, setEffTime(time, player), setEffLv(level, player), true, false, true));
//        player.addEffect(new MobEffectInstance(MobEffects.HEAL, setEffTime(time, player), setEffLv(level, player), true, false, true));
    }

    public int setEffTime(float tick) {
        int time = (int) (tick * 20 * 10);
//        player.sendSystemMessage(Component.literal(time + "Tick"));
        return time;
    }

    public int setEffLv(int lv) {

        int level = (int) Math.pow(2, lv) - 1;
//        player.sendSystemMessage(Component.literal(level + "Lv"));
        return level;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        setImmData();
        System.out.println(EFFECTS);
//        MutableComponent textWhenFeeding = TextUtils.getTranslation("tooltip.dog_food.when_feeding");
        MutableComponent textWhenFeeding = Component.literal("玩家食用时:");
        tooltip.add(textWhenFeeding.withStyle(ChatFormatting.GRAY));

        for (MobEffectInstance effectInstance : EFFECTS) {
            MutableComponent effectDescription = Component.literal(" ");
            MutableComponent effectName = Component.translatable(effectInstance.getDescriptionId());
            effectDescription.append(effectName);
            MobEffect effect = effectInstance.getEffect();

            if (effectInstance.getAmplifier() > 0) {
                effectDescription.append(" ").append(Component.translatable("potion.potency." + effectInstance.getAmplifier()));
            }

            if (effectInstance.getDuration() > 20) {
                effectDescription.append(" (").append(MobEffectUtil.formatDuration(effectInstance, 1.0F)).append(")");
            }

            tooltip.add(effectDescription.withStyle(effect.getCategory().getTooltipFormatting()));
        }
    }

    public boolean isFoil(ItemStack pStack) {
        return Objects.equals(this.tiers.toString(), "NETHERITE");
    }
}
