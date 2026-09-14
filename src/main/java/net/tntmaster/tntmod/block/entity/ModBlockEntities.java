package net.tntmaster.tntmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Tntmod.MODID);

    public static final RegistryObject<BlockEntityType<TapePlayerBlockEntity>> TAPE_PLAYER_BE =
            BLOCK_ENTITIES.register("tape_player_be", () ->
                    BlockEntityType.Builder.of(TapePlayerBlockEntity::new,
                            ModBlocks.TAPE_PLAYER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
