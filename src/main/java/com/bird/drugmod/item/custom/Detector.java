package com.bird.drugmod.item.custom;

import com.bird.drugmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class Detector extends Item {
    public Detector(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getLevel().isClientSide()) {
            BlockPos pos = useOnContext.getClickedPos();
            Player player = useOnContext.getPlayer();
            boolean isFound = false;

            if (player.isShiftKeyDown()) {


                for(int i = -1; i <= 2; i++) {
                    for(int j = -1; j <= 2; j++) {
                        BlockPos bp = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + j);

                        for(int k = 0; k <= bp.getY() + 1; k++) {
                            Block below = useOnContext.getLevel().getBlockState(bp.below(k)).getBlock();
                            if(isValuableBlock(below)) {
                                outPutBlockMsg(bp.below(k), player, below);
                                isFound = true;
//                               break;
                            }
                        }
                    }
                }


            }else {
                for (int i = 0; i <= pos.getY() + 64; i++) {
                    Block below = useOnContext.getLevel().getBlockState(pos.below(i)).getBlock();
                    if (isValuableBlock(below)) {
                        outPutBlockMsg(pos.below(i), player, below);
                        isFound = true;
//                        useOnContext.getLevel().playSound(player, pos, ModSounds.KUN_SOUND.get(), SoundSource.BLOCKS, 1f, 1f);

                        break;
                    }
                }
            }
        }


        useOnContext.getItemInHand().hurtAndBreak(1, useOnContext.getPlayer(), (player) -> {
            player.broadcastBreakEvent(player.getUsedItemHand());
        });

        return super.useOn(useOnContext);
    }

    public void outPutBlockMsg(BlockPos blockPos, Player player, Block below) {
        player.sendSystemMessage(Component.literal("检测到" + below.asItem().getDescription() + "位于" + "(" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    public void outPutChestMsg(BlockPos blockPos, Player player, Block below) {
        player.sendSystemMessage(Component.literal("检测到宝箱位于" + "(" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    public boolean isValuableBlock(Block block) {
//        Block a = ModBlocks.NITER_ORE.get();
//        System.out.println(block + " : " + ModBlocks.NITER_ORE);
        return block == Blocks.CHEST || block == ModBlocks.BEAR_ORE.get() || block == ModBlocks.DEEPSLATE_BEAR_ORE.get() || block == ModBlocks.NITER_ORE.get() || block == ModBlocks.DEEPSLATE_NITER_ORE.get() || block == ModBlocks.BRIMSTONE.get();
/*        return block == Blocks.ANCIENT_DEBRIS || block == Blocks.DIAMOND_ORE || block == Blocks.IRON_ORE ||
                block == Blocks.GOLD_ORE || block == Blocks.COAL_ORE || block == Blocks.EMERALD_ORE || block == Blocks.CHEST || block == ModBlocks.BEAR_ORE.get() || block == ModBlocks.DEEPSLATE_BEAR_ORE.get();*/

    }

    public boolean isChest(Block block) {
        return block == Blocks.CHEST;
    }
}
