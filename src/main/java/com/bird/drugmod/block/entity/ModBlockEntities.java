package com.bird.drugmod.block.entity;

import com.bird.drugmod.DrugMod;
import com.bird.drugmod.block.ModBlocks;
import com.bird.drugmod.block.custom.DryingRack;
import com.bird.drugmod.block.entity.custom.DryingRackEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DrugMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DryingRackEntity>> DRYING_RACK =
            BLOCK_ENTITIES.register("drying_rack",
                    ()->BlockEntityType.Builder.of(DryingRackEntity::new,
                            ModBlocks.DRYING_RACK.get()).build(null));


//    public static final RegistryObject<BlockEntityType<ShowCaseBlockEntity>> SHOWCASE =
//            BLOCK_ENTITIES.register("showcase",
//                    ()->BlockEntityType.Builder.of(ShowCaseBlockEntity::new,
//                            ModBlocks.SHOWCASE.get()).build(null));
//    public static final RegistryObject<BlockEntityType<ShowBoxEntity>> SHOWBOX =
//            BLOCK_ENTITIES.register("showbox",
//                    ()->BlockEntityType.Builder.of(ShowBoxEntity::new,
//                            ModBlocks.SHOWBOX.get()).build(null));
//
//    public static final RegistryObject<BlockEntityType<PlateEntity>> PLATE =
//            BLOCK_ENTITIES.register("plate",
//                    ()->BlockEntityType.Builder.of(PlateEntity::new,
//                            ModBlocks.PLATE.get()).build(null));
//
//    public static final RegistryObject<BlockEntityType<TableEntity>> TABLE =
//            BLOCK_ENTITIES.register("table",
//                    ()->BlockEntityType.Builder.of(TableEntity::new,
//                            ModBlocks.TABLE.get()).build(null));
    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
