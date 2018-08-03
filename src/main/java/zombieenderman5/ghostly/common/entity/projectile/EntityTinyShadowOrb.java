package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityTinyShadowOrb extends AbstractShadowOrb
{
    public EntityTinyShadowOrb(World worldIn)
    {
        super(worldIn);
        this.setSize(0.19F, 0.19F);
    }

    public EntityTinyShadowOrb(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.19F, 0.19F);
    }

    public EntityTinyShadowOrb(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.19F, 0.19F);
    }

    public static void registerFixesTinyShadowOrb(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "TinyShadowOrb");
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
            	boolean flag = result.entityHit.attackEntityFrom(AbstractShadowOrb.causeShadowOrbDamage(this, this.shootingEntity), 1.0F);

                if (flag)
                {
                    this.applyEnchantments(this.shootingEntity, result.entityHit);

                    if (result.entityHit instanceof EntityLivingBase)
                    {
                    	((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 80));
                    }
                }
            }

            this.setDead();
        }
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }
    
    @Override
	protected boolean existenceRequirements()
	{
		return true;
	}
}