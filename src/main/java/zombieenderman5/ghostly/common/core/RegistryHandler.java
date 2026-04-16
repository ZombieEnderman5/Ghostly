package zombieenderman5.ghostly.common.core;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.common.block.BlockCorporealite;
import zombieenderman5.ghostly.common.block.BlockCorporealiteOre;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;
import zombieenderman5.ghostly.common.item.*;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ghostly.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ghostly.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Ghostly.MOD_ID);


    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

    }

    //Blocks
    public static final RegistryObject<Block> CORPOREALITE_BLOCK= registerNormalBlock("corporealite_block", BlockCorporealite::new);
    public static final RegistryObject<Block> CORPOREALITE_ORE= registerNormalBlock("corporealite_ore", BlockCorporealiteOre::new);

    private static <T extends Block> RegistryObject<T> registerNormalBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerNormalBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerNormalBlockItem(String name, RegistryObject<T> block) {
        return RegistryHandler.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Ghostly.BLOCKS)));
    }

    //Items
    public static final RegistryObject<Item> CORPOREALITE_INGOT = ITEMS.register("corporealite_ingot", ItemCorporealiteIngot::new);
    public static final RegistryObject<Item> CORPOREALITE_NUGGET = ITEMS.register("corporealite_nugget", ItemCorporealiteNugget::new);
    public static final RegistryObject<Item> SHADOW_DUST = ITEMS.register("shadow_dust", ItemShadowDust::new);
    public static final RegistryObject<Item> VENOMSTRING = ITEMS.register("venomstring", ItemVenomstring::new);

    //Weapons
    public static final RegistryObject<Item> DUSTED_ARROW_OF_CORPOREALITY = ITEMS.register("dusted_arrow_of_corporeality", ItemDustedCorporealityArrow::new);
    public static final RegistryObject<EntityType<EntityDustedCorporealityArrow>> DUSTED_ARROW =
            ENTITIES.register("dusted_arrow_of_corporeality", () ->
                    EntityType.Builder.<EntityDustedCorporealityArrow>create(EntityDustedCorporealityArrow::new, EntityClassification.MISC)
                            .size(0.5F, 0.5F)
                            .trackingRange(64)
                            .updateInterval(20)
                            .build("dusted_arrow_of_corporeality")
            );
    public static final RegistryObject<Item> BOW_OF_CORPOREALITY = ITEMS.register("bow_of_corporeality", ItemBowOfCorporeality::new);


    //Tools

    //Food
    public static final RegistryObject<Item> SICKENED_SPIDER_EYE = ITEMS.register("sickened_spider_eye", ItemSickenedSpiderEye::new);
    public static final RegistryObject<Item> DARKNESS_MAGE_FLESH = ITEMS.register("darkness_mage_flesh", ItemDarknessMageFlesh::new);


}