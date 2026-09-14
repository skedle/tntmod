package net.tntmaster.tntmod.block;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.block.custom.JaronaFlowerBlock;
import net.tntmaster.tntmod.block.custom.JaronaFlowerPotBlock;
import net.tntmaster.tntmod.block.custom.TapePlayerBlock;
import net.tntmaster.tntmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Tntmod.MODID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> TAPE_PLAYER = registerBlock("tape_player",
            () -> new TapePlayerBlock(BlockBehaviour.Properties.copy(Blocks.JUKEBOX).sound(SoundType.METAL)));

    public static final RegistryObject<Block> GOLDEN_FLOWER = registerBlock("golden_flower",
            () -> new JaronaFlowerBlock(() -> MobEffects.LUCK, 1,
                    BlockBehaviour.Properties.copy(Blocks.CORNFLOWER).noCollission().noOcclusion()));

    public static final RegistryObject<Block> POTTED_GOLDEN_FLOWER = BLOCKS.register("potted_golden_flower",
            () -> new JaronaFlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.GOLDEN_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_CORNFLOWER).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
