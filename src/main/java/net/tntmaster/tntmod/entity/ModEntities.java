package net.tntmaster.tntmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tntmaster.tntmod.Tntmod;
import net.tntmaster.tntmod.entity.custom.PupfishEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tntmod.MODID);

    public static final RegistryObject<EntityType<PupfishEntity>> PUPFISH =
            ENTITY_TYPES.register("pupfish", () -> EntityType.Builder.of(PupfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(.3f, .6f).build("pupfish"));

    public static final RegistryObject<EntityType<PupfishEntity>> SHELBFISH =
            ENTITY_TYPES.register("shelbfish", () -> EntityType.Builder.of(PupfishEntity::new, MobCategory.WATER_AMBIENT)
                    .sized(.3f, .6f).build("shelbfish"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
