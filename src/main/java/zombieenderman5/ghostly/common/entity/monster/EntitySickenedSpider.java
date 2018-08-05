package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlyLootTableManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedVenomCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.ICorporealityProjectile;
import zombieenderman5.ghostly.common.item.IToolOfCorporeality;

public class EntitySickenedSpider extends EntitySpider implements IPartiallyIncorporeal {
	
	public EntitySickenedSpider(World worldIn) {
		
		super(worldIn);
		
		setSize(1.26F, 0.81F);
		
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
    }
	
	@Override
	public float getEyeHeight()
    {
        return 0.585F;
    }
	
	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio)
	    {
	        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, entityIn, strength, xRatio, zRatio);
	        if(event.isCanceled()) return;
	        EntityLivingBase livingEntity = null;
	        if (entityIn != null && !(entityIn instanceof IProjectile)) livingEntity = (EntityLivingBase) entityIn;
	        if (entityIn != null && livingEntity != null && livingEntity.getHeldItemMainhand().getItem() instanceof IToolOfCorporeality) {
	        	strength = event.getStrength();
	        } else if (entityIn != null && entityIn instanceof ICorporealityProjectile && !(entityIn instanceof EntityDustedCorporealityArrow || entityIn instanceof EntityDustedVenomCorporealityArrow)) {
	        	strength = event.getStrength();
	        } else if (entityIn != null && (entityIn instanceof EntityDustedCorporealityArrow || entityIn instanceof EntityDustedVenomCorporealityArrow)) {
	        	strength = event.getStrength() * 1.5F;
	        } else {
	        	strength = event.getStrength() / 2;
	        }
	        xRatio = event.getRatioX(); zRatio = event.getRatioZ();
	        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue())
	        {
	            this.isAirBorne = true;
	            float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
	            this.motionX /= 2.0D;
	            this.motionZ /= 2.0D;
	            this.motionX -= xRatio / (double)f * (double)strength;
	            this.motionZ -= zRatio / (double)f * (double)strength;

	            if (this.onGround)
	            {
	                this.motionY /= 2.0D;
	                this.motionY += (double)strength;

	                if (this.motionY > 0.4000000059604645D)
	                {
	                    this.motionY = 0.4000000059604645D;
	                }
	            }
	        }
	    }
	
	@Override
	public void onLivingUpdate()
	{
		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY /= 2;
        }
		
		if (!GhostlyConfig.MOBS.sickenedSpiders) this.setDead();
		
		super.onLivingUpdate();
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {

		super.fall(distance, 0.2F);

	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		EntityLivingBase sourceLiving = null;
		
		if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase) {
			
			sourceLiving = (EntityLivingBase) source.getTrueSource();
			
		}
		
		if (source instanceof EntityDamageSourceIndirect && source.getImmediateSource() instanceof EntityCorporealityArrow && !(source.getImmediateSource() instanceof EntityDustedCorporealityArrow || source.getImmediateSource() instanceof EntityDustedVenomCorporealityArrow)) {

			return super.attackEntityFrom(source, amount);

		} else if (source.getImmediateSource() instanceof EntityDustedCorporealityArrow || source.getImmediateSource() instanceof EntityDustedVenomCorporealityArrow) {

			return super.attackEntityFrom(source, amount * 1.5F);

		} else if (source.getTrueSource() != null && sourceLiving != null && sourceLiving.getHeldItemMainhand().getItem() instanceof IToolOfCorporeality) {

			return super.attackEntityFrom(source, amount);

		} else {
			
			return super.attackEntityFrom(source, amount / 2);
			
		}

	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 0;

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 7;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 15;
                }

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 1));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
	
	@Override
	protected SoundEvent getAmbientSound()
    {
        return GhostlySoundManager.SICKENED_SPIDER_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return GhostlySoundManager.SICKENED_SPIDER_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return GhostlySoundManager.SICKENED_SPIDER_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

	@Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return GhostlyLootTableManager.ENTITIES_SICKENED_SPIDER;
    }
	
}
