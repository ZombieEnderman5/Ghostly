package zombieenderman5.ghostly.client.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
<<<<<<< HEAD
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
=======
import zombieenderman5.ghostly.common.core.GhostlyBlockManager;
>>>>>>> master
import zombieenderman5.ghostly.common.core.GhostlyEntityManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.proxy.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		
		GhostlyItemManager.registerRenders();
		GhostlyBlockManager.registerRenders();
		
	}
	
	@Override
	public void entityRegisterRenders(FMLPreInitializationEvent event) {

		GhostlyEntityManager.registerEntityRenderingHandlers(event);

	}
	
}
