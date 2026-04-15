package net.corey.tntmod.item;

import net.corey.tntmod.Tntmod;
import net.corey.tntmod.item.custom.FuelItem;
import net.corey.tntmod.item.custom.LedgBarItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Items

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tntmod.MODID);

    public static final RegistryObject<Item> ROUGH_SAPPHIRE = ITEMS.register("rough_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CUT_SAPPHIRE = ITEMS.register("cut_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WORLD_ESSENCE = ITEMS.register("world_essence",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMPTY_BOTTLE = ITEMS.register("empty_bottle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AMBER = ITEMS.register("amber",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOADSTONE = ITEMS.register("toadstone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CAMELLIA = ITEMS.register("camellia",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE1 = ITEMS.register("rune1",
            () -> new Item(new Item.Properties()));

    // --------------------------------------------------------------------------------------------------------------------------------
    // Food

    public static final RegistryObject<Item> PARASITE = ITEMS.register("parasite",
            () -> new Item(new Item.Properties().stacksTo(1).food(ModFoods.PARASITE)));

    public static final RegistryObject<Item> LEDGBAR = ITEMS.register("ledgbar",
            () -> new LedgBarItem(new Item.Properties().food(ModFoods.LEDGBAR)));

    // --------------------------------------------------------------------------------------------------------------------------------
    // Fuel

    public static final RegistryObject<Item> TALLOW = ITEMS.register("tallow",
            () -> new FuelItem(new Item.Properties(), 12800));

    // --------------------------------------------------------------------------------------------------------------------------------
    // Tools 'n' Weapons

    public static final RegistryObject<Item> HOOK_SWORD = ITEMS.register("hook_sword",
            () -> new SwordItem(ModToolTiers.WORLD_ESSENCE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> SILVERED_SWORD = ITEMS.register("silvered_sword",
            () -> new SwordItem(ModToolTiers.WORLD_ESSENCE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModToolTiers.WORLD_ESSENCE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> PARASITE_SWORD = ITEMS.register("parasite_sword",
            () -> new SwordItem(ModToolTiers.WORLD_ESSENCE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> CLAW_SCYTHE = ITEMS.register("claw_scythe",
            () -> new SwordItem(ModToolTiers.WORLD_ESSENCE, 0,0, new Item.Properties()));

    // --------------------------------------------------------------------------------------------------------------------------------
    // Armor

    public static final RegistryObject<Item> TEST_MASK = ITEMS.register("test_mask",
            () -> new ArmorItem(ModArmorMaterials.TEST_MASK, ArmorItem.Type.HELMET, new Item.Properties()));

    // --------------------------------------------------------------------------------------------------------------------------------

    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
