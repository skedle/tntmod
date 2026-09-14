package net.tntmaster.tntmod.datagen;

import net.minecraft.tags.ItemTags;
import net.tntmaster.tntmod.Tntmod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tntmaster.tntmod.item.ModItems;
import net.tntmaster.tntmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, Tntmod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.DISCO_GIRL_DISC.get());

        this.tag(ModTags.Items.TAPE_ITEM)
                .add(ModItems.TEST_TAPE.get());


    }

}
