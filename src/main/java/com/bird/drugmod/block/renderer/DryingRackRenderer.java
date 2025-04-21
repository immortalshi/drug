package com.bird.drugmod.block.renderer;

import com.bird.drugmod.block.entity.custom.DryingRackEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class DryingRackRenderer implements BlockEntityRenderer<DryingRackEntity> {

    public DryingRackRenderer(BlockEntityRendererProvider.Context cxt) {

    }

    @Override
    public void render(DryingRackEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        LazyOptional<IItemHandler> itemInteractionHandler = pBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER);
//                .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);

        itemInteractionHandler.ifPresent(inv -> {
            for (int i = 0; i < DryingRackEntity.NUM_SLOTS; i++) {
                ItemStack item = inv.getStackInSlot(i);

                if (!item.isEmpty()) {
//                    System.out.println("Rendering item123 " + i);
                    BakedModel bakedmodel = itemRenderer.getModel(item, pBlockEntity.getLevel(), null, 0);
                    pPoseStack.pushPose();

                    pPoseStack.translate(0.5f, 0.45f + (i > 3 ? 0.5f : 0), 0.5f);

                    pPoseStack.mulPose(new Quaternion(0, 135, 0, true));
                    pPoseStack.mulPose(new Quaternion(0, 90 * (i%4), 0, true));
                    pPoseStack.mulPose(new Quaternion(90, 0, 0, true));
                    pPoseStack.translate(0.25f, 0, 0);

                    float uniscale = 0.65f;
                    pPoseStack.scale(uniscale, uniscale, uniscale);
                    itemRenderer.render(item, ItemTransforms.TransformType.GROUND, false, pPoseStack, pBufferSource,
                            pPackedLight, pPackedOverlay, bakedmodel);
                    pPoseStack.popPose();
                }
            }
        });
    }

}
