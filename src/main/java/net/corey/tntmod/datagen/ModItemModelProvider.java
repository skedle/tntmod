package net.corey.tntmod.datagen;

import net.corey.tntmod.Tntmod;
import net.corey.tntmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Tntmod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ROUGH_SAPPHIRE);
        simpleItem(ModItems.CUT_SAPPHIRE);
        simpleItem(ModItems.WORLD_ESSENCE);
        simpleItem(ModItems.EMPTY_BOTTLE);
        simpleItem(ModItems.AMBER);
        simpleItem(ModItems.TOADSTONE);
        simpleItem(ModItems.CAMELLIA);

        simpleItem(ModItems.PARASITE);
        simpleItem(ModItems.LEDGBAR);

        simpleItem(ModItems.TALLOW);




    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Tntmod.MODID, "item/" + item.getId().getPath()));
    }
}
