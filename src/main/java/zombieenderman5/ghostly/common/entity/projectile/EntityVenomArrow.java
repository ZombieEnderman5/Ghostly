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

public class EntityVenomArrow extends EntityArrow {

	public EntityVenomArrow(World worldIn) {
		
		super(worldIn);
		
	}
	
	public EntityVenomArrow(World worldIn, EntityLivingBase shooter) {
		
		super(worldIn, shooter);
		
	}

	public EntityVenomArrow(World worldIn, double x, double y, double z) {
		
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
		
		living.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
		
	}
	
}
