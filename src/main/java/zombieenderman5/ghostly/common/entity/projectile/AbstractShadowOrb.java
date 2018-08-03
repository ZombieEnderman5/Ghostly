package zombieenderman5.ghostly.common.entity.projectile;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class AbstractShadowOrb extends EntityFireball
{
	public AbstractShadowOrb(World worldIn)
	{
		super(worldIn);
	}
	
	public AbstractShadowOrb(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
    }

    public AbstractShadowOrb(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if (!this.existenceRequirements())
    	{
    		this.setDead();
    	}
    }

    protected abstract boolean existenceRequirements();
    
    @Override
    protected boolean isFireballFiery()
    {
        return false;
    }
	
	public static DamageSource causeShadowOrbDamage(Entity source, @Nullable Entity indirectEntityIn)
    {
        return (new EntityDamageSourceIndirect("shadowOrb", source, indirectEntityIn)).setProjectile().setDamageBypassesArmor().setMagicDamage();
    }
}
