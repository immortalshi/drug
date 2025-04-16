package com.bird.drugmod.block.custom;

import com.bird.drugmod.block.entity.ModBlockEntities;
import com.bird.drugmod.block.entity.custom.DryingRackEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DryingRack extends Block implements EntityBlock {

    //构造器
    public DryingRack(Properties pProperties) {
        super(pProperties);
    }

    //透光方块
    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
                                 BlockHitResult pHit) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof DryingRackEntity) {
            DryingRackEntity be = (DryingRackEntity) blockentity;

            if (pPlayer.isCrouching()) {
                if (!pLevel.isClientSide) {
                    be.extractItem(pPlayer);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            } else {

                ItemStack itemstack = pPlayer.getItemInHand(pHand);
                if (!pLevel.isClientSide) {
                    // pPlayer.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    pPlayer.setItemInHand(pHand, be.insertItem(itemstack));
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.PASS;
    }
    //接口实现
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DryingRackEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == ModBlockEntities.DRYING_RACK.get() ? DryingRackEntity::tick : null;
    }

    //破坏方块时调用
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = level.getBlockEntity(pos);
            if (tileentity instanceof DryingRackEntity) {
                tileentity.getCapability(ForgeCapabilities.ITEM_HANDLER)
                        .ifPresent(itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots())
                                .forEach(i -> Block.popResource(level, pos, itemInteractionHandler.getStackInSlot(i))));

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    private static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 0, 0, 2, 16, 2),
            Block.box(0, 0, 14, 2, 16, 16),
            Block.box(14, 0, 0, 16, 16, 2),
            Block.box(14, 0, 14, 16, 16, 16),
            Block.box(1, 6, 1, 15, 7, 15),
            Block.box(1, 14, 1, 15, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }


}