package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelZombie;

public class ModelPossessedZombie extends ModelZombie {
	
	public void changeBipedHeadwearVisibility(boolean visible) {
        
        bipedHeadwear.showModel = visible;
        
    }
	
}
