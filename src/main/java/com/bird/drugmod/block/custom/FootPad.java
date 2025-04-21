package com.bird.drugmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;


public class FootPad extends Block implements EntityBlock, SimpleWaterloggedBlock {

    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape ver_shape = Stream.of(
            Block.box(1, 7, 2, 15, 8, 14),
            Block.box(2, 0, 3, 4, 7, 5),
            Block.box(2, 0, 11, 4, 7, 13),
            Block.box(12, 0, 3, 14, 7, 5),
            Block.box(12, 0, 11, 14, 7, 13),
            Block.box(4, 5, 3.5, 12, 7, 4.5),
            Block.box(4, 5, 11.5, 12, 7, 12.5),
            Block.box(2.5, 5, 5, 3.5, 7, 11),
            Block.box(12.5, 5, 5, 13.5, 7, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    
    public static final VoxelShape hor_shape = Stream.of(
            Block.box(2, 7, 1, 14, 8, 15),
            Block.box(11, 0, 2, 13, 7, 4),
            Block.box(3, 0, 2, 5, 7, 4),
            Block.box(11, 0, 12, 13, 7, 14),
            Block.box(3, 0, 12, 5, 7, 14),
            Block.box(11.5, 5, 4, 12.5, 7, 12),
            Block.box(3.5, 5, 4, 4.5, 7, 12),
            Block.box(5, 5, 2.5, 11, 7, 3.5),
            Block.box(5, 5, 12.5, 11, 7, 13.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public FootPad(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidState = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case EAST -> hor_shape;
            case WEST -> hor_shape;
            case SOUTH -> ver_shape;
            default -> ver_shape;
        };

//        VoxelShape shape = null;
//        switch(direction) {
//            case NORTH:
//            case SOUTH:
//                shape = ver_shape;
//            case EAST:
//            case WEST:
//                shape = hor_shape;
//        }

//        System.out.println(shape);
//        return shape;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING))); //
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction side, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if(state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return state;
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

}
