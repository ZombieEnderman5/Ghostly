package zombieenderman5.ghostly.client.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyCombat;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyTools;

public class GhostlyCreativeTabManager {
	
	public static CreativeTabs combat;
	public static CreativeTabs tools;
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		combat = new TabGhostlyCombat("ghostly_combat");
		tools = new TabGhostlyTools("ghostly_tools");
		
	}
	
}
package zombieenderman5.ghostly.client.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyBlocks;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyCombat;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyMiscellaneous;
import zombieenderman5.ghostly.client.creative_tabs.TabGhostlyTools;

public class GhostlyCreativeTabManager {
	
	public static CreativeTabs combat;
	public static CreativeTabs tools;
	public static CreativeTabs blocks;
	public static CreativeTabs miscellaneous;
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		combat = new TabGhostlyCombat("ghostly_combat");
		tools = new TabGhostlyTools("ghostly_tools");
		blocks = new TabGhostlyBlocks("ghostly_blocks");
		miscellaneous = new TabGhostlyMiscellaneous("ghostly_miscellaneous");
		
	}
	
}
