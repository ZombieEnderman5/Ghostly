package zombieenderman5.ghostly.client.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zombieenderman5.ghostly.common.core.GhostlyEntityManager;
import zombieenderman5.ghostly.common.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {

	}
	
	@Override
	public void entityRegisterRenders(FMLPreInitializationEvent event) {

		GhostlyEntityManager.registerEntityRenderingHandlers(event);

	}
	
}
