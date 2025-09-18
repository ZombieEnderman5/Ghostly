package zombieenderman5.ghostly.common.core;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.common.block.BlockCorporealite;
import zombieenderman5.ghostly.common.block.BlockCorporealiteOre;
import zombieenderman5.ghostly.common.item.ItemCorporealiteIngot;
import zombieenderman5.ghostly.common.item.ItemCorporealiteNugget;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ghostly.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ghostly.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
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

}