package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityGiantShadowOrb extends AbstractShadowOrb
{
	public int explosionPower = 2;
	
    public EntityGiantShadowOrb(World worldIn)
    {
        super(worldIn);
    }

    public EntityGiantShadowOrb(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ)
    {
        super(worldIn, shooter, accelX, accelY, accelZ);
    }

    public EntityGiantShadowOrb(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ)
    {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
    }

    public static void registerFixesGiantShadowOrb(DataFixer fixer)
    {
        EntityFireball.registerFixesFireball(fixer, "GiantShadowOrb");
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
            	boolean flag = result.entityHit.attackEntityFrom(AbstractShadowOrb.causeShadowOrbDamage(this, this.shootingEntity), 6.0F);

                if (flag)
                {
                    this.applyEnchantments(this.shootingEntity, result.entityHit);
                    
                    if (result.entityHit instanceof EntityLivingBase)
                    {
                    	((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
                    }
                }
            }
            
            boolean flag = this.world.getGameRules().getBoolean("mobGriefing");
            this.world.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.explosionPower, false, flag);
            this.setDead();
        }
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("ExplosionPower", this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("ExplosionPower", 99))
        {
            this.explosionPower = compound.getInteger("ExplosionPower");
        }
    }
    
    @Override
	protected boolean existenceRequirements()
	{
		return GhostlyConfig.MOBS.darknessMages;
	}
}