package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;

public class EntityCorporealityArrow extends EntityArrow implements ICorporealityProjectile {

	public EntityCorporealityArrow(World worldIn) {
		
		super(worldIn);
		
	}
	
	public EntityCorporealityArrow(World worldIn, EntityLivingBase shooter) {
		
		super(worldIn, shooter);
		
	}

	public EntityCorporealityArrow(World worldIn, double x, double y, double z) {
		
        this(worldIn);
        this.setPosition(x, y, z);
        
    }
	
	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(Items.ARROW, 1);
	}

	@Override
	protected void arrowHit(EntityLivingBase living) {
		
		super.arrowHit(living);
		
		if (living instanceof IPartiallyIncorporeal) living.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
		
	}
	
}
