package net.corey.tntmod.item;

import net.corey.tntmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.corey.tntmod.Tntmod;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tntmod.MODID);

    public static final RegistryObject<CreativeModeTab> TNT_TAB = CREATIVE_MODE_TABS.register("tnt_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.TNT))
                    .title(Component.translatable("creativetab.tnt_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ROUGH_SAPPHIRE.get());
                        pOutput.accept(ModItems.CUT_SAPPHIRE.get());
                        pOutput.accept(ModItems.WORLD_ESSENCE.get());
                        pOutput.accept(ModItems.EMPTY_BOTTLE.get());
                        pOutput.accept(ModItems.AMBER.get());
                        pOutput.accept(ModItems.TOADSTONE.get());
                        pOutput.accept(ModItems.CAMELLIA.get());
                        pOutput.accept(ModItems.RUNE1.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());

                        pOutput.accept(ModItems.PARASITE.get());
                        pOutput.accept(ModItems.LEDGBAR.get());

                        pOutput.accept(ModItems.TALLOW.get());

                        pOutput.accept(ModItems.HOOK_SWORD.get());
                        pOutput.accept(ModItems.SILVERED_SWORD.get());
                        pOutput.accept(ModItems.AMETHYST_SWORD.get());
                        pOutput.accept(ModItems.PARASITE_SWORD.get());
                        pOutput.accept(ModItems.CLAW_SCYTHE.get());

                        pOutput.accept(ModItems.TEST_MASK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }


}
