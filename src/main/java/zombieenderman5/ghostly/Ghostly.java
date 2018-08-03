package zombieenderman5.ghostly;

import org.apache.logging.log4j.Logger;

import gatocreador887.hardcoredimensionexpansion.util.HDEReference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyEntityManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;
import zombieenderman5.ghostly.common.proxy.CommonProxy;
import zombieenderman5.theboxingdead.TBDReference;

@Mod(modid = GhostlyReference.MOD_ID, name = GhostlyReference.MOD_NAME, version = GhostlyReference.MOD_VERSION, dependencies = GhostlyReference.MOD_DEPENDENCIES)
public class Ghostly {

	public static Logger logger;
	public static Side side;

	@Instance
	public static Ghostly instance;

	@SidedProxy(clientSide = GhostlyReference.CLIENT_PROXY_PATH, serverSide = GhostlyReference.COMMON_PROXY_PATH)
	public static CommonProxy proxy;

	@EventHandler
	public void preInitialization(FMLPreInitializationEvent event) {

		side = event.getSide();

		logger = event.getModLog();

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Beginning pre-initialization stage");

		}

		proxy.entityRegisterRenders(event);

		GhostlyEntityManager.preInitialization(event);
		GhostlyCreativeTabManager.preInitialization(event);
		GhostlyItemManager.preInitialization(event);

		MinecraftForge.EVENT_BUS.register(new GhostlySoundManager());
		MinecraftForge.EVENT_BUS.register(Ghostly.class);
		
		MinecraftForge.TERRAIN_GEN_BUS.register(new GhostlyTerrainGenEventHandler());

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Ending pre-initialization stage");

		}

	}

	@EventHandler
	public void initialization(FMLInitializationEvent event) {

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Beginning initialization stage");

		}

		GhostlyEntityManager.initialization(event);

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Ending initialization stage");

		}

	}

	@EventHandler
	public void postInitialization(FMLPostInitializationEvent event) {

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Beginning post-initialization stage");

		}

		GhostlyEntityManager.postInitialization(event);
		
		EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation("minecraft", "zombie"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_zombie"));
        EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation("minecraft", "skeleton"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_skeleton"));
        EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation("minecraft", "husk"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_husk"));
        EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation("minecraft", "wither_skeleton"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_skeleton"));
        EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation("minecraft", "stray"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_stray"));
        
        if (Loader.isModLoaded("theboxingdead")) {
        	
        	try {
            	
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(TBDReference.MOD_ID, "boxer_zombie"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_zombie"));
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(TBDReference.MOD_ID, "boxer_skeleton"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_skeleton"));
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(TBDReference.MOD_ID, "boxer_husk"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_husk"));
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(TBDReference.MOD_ID, "boxer_wither_skeleton"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_wither_skeleton"));
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(TBDReference.MOD_ID, "boxer_stray"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_stray"));
            	
            } catch (NoClassDefFoundError ncdfe) {
            	
            	
            	
            }
        	
        }
        
        if (Loader.isModLoaded("hardcoredimensionexpansion")) {
        	
        	try {
            	
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(HDEReference.ID, "hunchbone"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_hunchbone"));
            	EntityShade.POSSESSABLE_ENTITY_CLASSES.put(new ResourceLocation(HDEReference.ID, "wither_hunchbone"), new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_hunchbone"));
            	
            } catch (NoClassDefFoundError ncdfe) {
            	
            	
            	
            }
        	
        }

		if (GhostlyConfig.logging) {

			logger.info("<Ghostly> Ending post-initialization stage");

		}

	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		
		if (GhostlyConfig.logging) {
			
			logger.info("<Ghostly> Registering item models");
			
		}
		
		proxy.registerRenders();
		
		if (GhostlyConfig.logging) {
			
			logger.info("<Ghostly> Item models registered");
			
		}
		
	}
	
}
