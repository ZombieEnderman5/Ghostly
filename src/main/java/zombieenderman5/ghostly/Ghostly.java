package zombieenderman5.ghostly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderDustedCorporealityArrow;
import zombieenderman5.ghostly.common.core.RegistryHandler;
import zombieenderman5.ghostly.common.world.gen.GhostlyOreGenerator;

@Mod("ghostly")
public class Ghostly {
	public static final String MOD_ID = "ghostly";

	public Ghostly() {

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);

		RegistryHandler.init();
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.addListener(GhostlyOreGenerator::handleWorldGen);
	}

	private void setup(final FMLCommonSetupEvent event) {
	}
	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.DUSTED_ARROW.get(), RenderDustedCorporealityArrow::new);

		ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_CORPOREALITY.get(),
				new ResourceLocation("pull"),
				(stack, world, entity) -> {
					if (entity == null) {
						return 0.0F;
					} else {
						return entity.getActiveItemStack() != stack ? 0.0F :
								(float)(stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
					}
				});

		ItemModelsProperties.registerProperty(RegistryHandler.BOW_OF_CORPOREALITY.get(),
				new ResourceLocation("pulling"),
				(stack, world, entity) -> {
					return entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F;
				});
	}

	public static final ItemGroup BLOCKS = new ItemGroup("ghostly_blocks") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(RegistryHandler.CORPOREALITE_BLOCK.get());
		}
	};

	public static final ItemGroup ITEMS = new ItemGroup("ghostly_miscellaneous") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(RegistryHandler.CORPOREALITE_INGOT.get());
		}
	};

	public static final ItemGroup FOOD = new ItemGroup("ghostly_foodstuffs") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(RegistryHandler.SICKENED_SPIDER_EYE.get());
		}
	};
}