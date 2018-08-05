package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;

public class EntityVenomCorporealityArrow extends EntityArrow implements ICorporealityProjectile {

	public EntityVenomCorporealityArrow(World worldIn) {
		
		super(worldIn);
		
	}
	
	public EntityVenomCorporealityArrow(World worldIn, EntityLivingBase shooter) {
		
		super(worldIn, shooter);
		
	}

	public EntityVenomCorporealityArrow(World worldIn, double x, double y, double z) {
		
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
		living.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
		
	}
	
}
