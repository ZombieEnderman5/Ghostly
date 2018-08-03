package zombieenderman5.ghostly.common.entity.monster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.TreeMultiset;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import scala.tools.nsc.doc.model.Public;
import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.ai.EntityAIFleeLight;
import zombieenderman5.ghostly.common.entity.ai.EntityAIRestrictLight;
import zombieenderman5.theboxingdead.common.entity.monster.EntityBoxerHusk;
import zombieenderman5.theboxingdead.common.entity.monster.EntityBoxerSkeleton;
import zombieenderman5.theboxingdead.common.entity.monster.EntityBoxerStray;
import zombieenderman5.theboxingdead.common.entity.monster.EntityBoxerWitherSkeleton;
import zombieenderman5.theboxingdead.common.entity.monster.EntityBoxerZombie;
import zombieenderman5.theboxingdead.common.entity.monster.IBoxer;

public class EntityShade extends EntityMob {

	public static final Map<ResourceLocation, ResourceLocation> POSSESSABLE_ENTITY_CLASSES = new HashMap<ResourceLocation, ResourceLocation>();
	private static final Predicate<Entity> INFEST_ENDERMAN_SELECTOR = new Predicate<Entity>()
    {
		@Override
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_ instanceof EntityEnderman && !(p_apply_1_ instanceof EntityInfestedEnderman) && GhostlyConfig.MOBS.shadesInfestEndermen && ((EntityLivingBase)p_apply_1_).attackable();
        }
    };
	
	public EntityShade(World worldIn) {

		super(worldIn);

		this.experienceValue = 0;
		this.isImmuneToFire = true;

	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio)
	    {
	        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, entityIn, strength, xRatio, zRatio);
	        if(event.isCanceled()) return;
	        strength = event.getStrength(); xRatio = event.getRatioX(); zRatio = event.getRatioZ();
	        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue())
	        {
	            this.isAirBorne = true;
	            float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
	            this.motionX /= 6.0D;
	            this.motionZ /= 6.0D;
	            this.motionX -= xRatio / (double)f * (double)strength;
	            this.motionZ -= zRatio / (double)f * (double)strength;

	            if (this.onGround)
	            {
	                this.motionY /= 6.0D;
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
		if (this.world.getLightBrightness(new BlockPos(this)) > (float)GhostlyConfig.MOBS.shadeDissipationLightLevel)
		{

			if (!this.world.isRemote)
			{
				this.playSound(GhostlySoundManager.SHADE_DISSIPATE, 1.0F, 1.0F);
				this.setDead();

				if (Ghostly.side == Side.CLIENT)
				{
					for (int k = 0; k < 20; ++k)
		            {
		                double d2 = this.rand.nextGaussian() * 0.02D;
		                double d0 = this.rand.nextGaussian() * 0.02D;
		                double d1 = this.rand.nextGaussian() * 0.02D;
		                Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
		            }
				}
			}

		}

		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.3D;
        }
		
		if (!GhostlyConfig.MOBS.shades) this.setDead();
		
		super.onLivingUpdate();
	}

	@Override
	protected int decreaseAirSupply(int air) {

		return air;

	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(70.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.333334D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);

	}

	@Override
	protected void initEntityAI() {

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIFleeLight(this, 1.0D));
		this.tasks.addTask(1, new EntityAIRestrictLight(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 70.0F, 1.0f));
		this.tasks.addTask(3, new AIPossess(this));
		this.applyEntityAI();

	}

	protected void applyEntityAI() {

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[]{EntityLivingBase.class}));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(4, new AIInfestEndermanAttack(this));

	}

	@Override
	public void fall(float distance, float damageMultiplier) {

		

	}

	@Override
	protected SoundEvent getAmbientSound() {

		return GhostlySoundManager.SHADE_AMBIENT;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return GhostlyConfig.AUDIO.alternateShadeAudio ? GhostlySoundManager.SHADE_HURT_ALTERNATE : GhostlySoundManager.SHADE_HURT;

	}

	@Override
	protected SoundEvent getDeathSound() {

		return GhostlySoundManager.SHADE_DEATH;

	}

	protected SoundEvent getStepSound() {

		return null;

	}

	@Override
	protected ResourceLocation getLootTable() {

		return null;

	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {

		this.playSound(this.getStepSound(), 0.15F, 1.0F);

	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {

		return false;

	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {

		return this.world.getLightBrightness(pos) > (float)GhostlyConfig.MOBS.shadeDissipationLightLevel ? -10.0F : super.getBlockPathWeight(pos);
	}

	@Override
	public void onDeath(DamageSource cause) {

		super.onDeath(cause);

	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		if (source.isExplosion()) {

			return false;

		} else if (source.isFireDamage()) {

			return false;

		} else if (source.isMagicDamage()) {

			return false;

		} else if (source instanceof EntityDamageSourceIndirect) {

			return false;

		} else {

			return super.attackEntityFrom(source, amount / 3);

		}

	}

	@Override
	public void onDeathUpdate() {
		
		++this.deathTime;

        if (this.deathTime == 20)
        {
            if (!this.world.isRemote && (this.isPlayer() || this.recentlyHit > 0 && this.canDropLoot() && this.world.getGameRules().getBoolean("doMobLoot")))
            {
                int i = this.getExperiencePoints(this.attackingPlayer);
                i = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.attackingPlayer, i);
                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.world.spawnEntity(new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, j));
                }
            }

            this.setDead();

            for (int k = 0; k < 20; ++k)
            {
                if (Minecraft.getMinecraft().world != null && this.world.isRemote) {
                	
                	double d2 = this.rand.nextGaussian() * 0.02D;
                    double d0 = this.rand.nextGaussian() * 0.02D;
                    double d1 = this.rand.nextGaussian() * 0.02D;
                    Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
                	
                }
            }
        }
		
	}
	
	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);

        if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityEnderman && GhostlyConfig.MOBS.infestedEndermen && GhostlyConfig.MOBS.shadesInfestEndermen && !this.world.isRemote)
        {
            if (this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean())
            {
                return;
            }

            EntityEnderman entityvillager = (EntityEnderman)entityLivingIn;
            EntityInfestedEnderman entityzombievillager = new EntityInfestedEnderman(this.world);
            entityzombievillager.copyLocationAndAnglesFrom(entityvillager);
            this.world.removeEntity(entityvillager);
            entityzombievillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombievillager)), null);
            entityzombievillager.setNoAI(entityvillager.isAIDisabled());

            if (entityvillager.hasCustomName())
            {
                entityzombievillager.setCustomNameTag(entityvillager.getCustomNameTag());
                entityzombievillager.setAlwaysRenderNameTag(entityvillager.getAlwaysRenderNameTag());
            }

            this.world.spawnEntity(entityzombievillager);
            this.world.playSound((EntityPlayer)null, new BlockPos(this), GhostlySoundManager.SHADE_INFEST_ENDERMAN, SoundCategory.HOSTILE, 1.0F, 1.0F);
            this.setDead();
        }
    }
	
	static class AIPossess extends EntityAIWander
    {
        private EntityLiving toPossess;
        private boolean doMerge;

        public AIPossess(EntityShade silverfishIn)
        {
            super(silverfishIn, 1.0D, 10);
            this.setMutexBits(1);
        }

        @Override
        public void resetTask() {
        	
        	this.toPossess = null;
        	
        }
        
        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
        	if (!GhostlyConfig.MOBS.shadesPossessUndead) {
        		
        		return false;
        		
        	}
            /*else if (!this.entity.getNavigator().noPath())
            {
                return false;
            }*/
            else if (this.entity.getHealth() > this.entity.getMaxHealth() / 4.0F)
            {
                return false;
            }
            else
            {
            	Random random = this.entity.getRNG();
                List<EntityLiving> entities = this.entity.world.getEntitiesWithinAABB(EntityLiving.class, this.entity.getEntityBoundingBox().grow(5.0D), entity -> {
                	return POSSESSABLE_ENTITY_CLASSES.keySet().contains(EntityList.getKey(entity)) && entity.getHealth() > 0.0F;
                });
                
                if (!entities.isEmpty()) {
                	
                	this.toPossess = entities.get(random.nextInt(entities.size()));
                	this.doMerge = true;
                	return true;
                	
                }

                this.doMerge = false;
                return super.shouldExecute();
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return this.doMerge ? false : super.shouldContinueExecuting();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            if (!this.doMerge)
            {
                super.startExecuting();
            }
            else
            {
                World world = this.entity.world;
                Random random = this.entity.getRNG();
                
                if (random.nextInt(3) == 0 && this.toPossess.isEntityAlive()) {
                	
                	EntityLiving newMob = (EntityLiving) EntityList.createEntityByIDFromName(POSSESSABLE_ENTITY_CLASSES.get(EntityList.getKey(this.toPossess)), world);
					newMob.setLocationAndAngles(this.toPossess.posX, this.toPossess.posY, this.toPossess.posZ, this.toPossess.rotationYaw, this.toPossess.rotationPitch);
					newMob.setHealth(this.toPossess.getHealth() / this.toPossess.getMaxHealth() * newMob.getMaxHealth());
					newMob.setLeftHanded(this.toPossess.isLeftHanded());
					newMob.setAlwaysRenderNameTag(this.toPossess.getAlwaysRenderNameTag());
					newMob.setCanPickUpLoot(!(this.toPossess instanceof IBoxer) ? true : false);
					newMob.setCustomNameTag(this.toPossess.getCustomNameTag());
					newMob.setEntityInvulnerable(this.toPossess.getIsInvulnerable());
					newMob.setGlowing(this.toPossess.isGlowing());
					newMob.setHeldItem(EnumHand.MAIN_HAND, this.toPossess.getHeldItemMainhand());
					newMob.setHeldItem(EnumHand.OFF_HAND, this.toPossess.getHeldItemOffhand());
					newMob.setNoGravity(this.toPossess.hasNoGravity());
					newMob.setSilent(this.toPossess.isSilent());
					newMob.setSneaking(this.toPossess.isSneaking());
					newMob.setSprinting(this.toPossess.isSprinting());
					newMob.setItemStackToSlot(EntityEquipmentSlot.HEAD, this.toPossess.getItemStackFromSlot(EntityEquipmentSlot.HEAD));
					newMob.setItemStackToSlot(EntityEquipmentSlot.CHEST, this.toPossess.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
					newMob.setItemStackToSlot(EntityEquipmentSlot.LEGS, this.toPossess.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
					newMob.setItemStackToSlot(EntityEquipmentSlot.FEET, this.toPossess.getItemStackFromSlot(EntityEquipmentSlot.FEET));
					
					if (newMob instanceof IPossessed) {
						
						((IPossessed) newMob).setEyeType(GhostlyConfig.MOBS.shadePossessionEyeType);
						
					}
					
					if (Loader.isModLoaded("theboxingdead") && newMob instanceof IPossessedBoxer && this.toPossess instanceof IBoxer) {
						
						((IPossessedBoxer) newMob).setGlovesType(((IBoxer) this.toPossess).getGlovesType());
						
					}
					
					if (newMob instanceof EntityZombie && this.toPossess instanceof EntityZombie) {
						
						((EntityZombie) newMob).setChild(this.toPossess.isChild());
						
					}
					
					world.spawnEntity(newMob);
					
					if (Ghostly.side == Side.CLIENT && Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote && GhostlyConfig.AESTHETICS.shadePossessUndeadEffects) {
						
						double dist = this.entity.getDistance(this.toPossess.getPositionVector().x, this.toPossess.getPositionVector().y, this.toPossess.getPositionVector().z);
                		Vec3d location = new Vec3d(this.entity.posX, this.entity.posY + this.entity.getEyeHeight(), this.entity.posZ);
            			Vec3d direction = location.subtract(this.toPossess.getPositionVector()).normalize();
            			direction = direction.subtract(direction.add(direction));
                		
                		for (double d = 0.0D; d < this.entity.getDistanceSq(this.toPossess); d += 0.2) {
                			
                			 Vec3d dir = direction.scale(d);
                             Vec3d pos = location.add(dir);
                             
                             Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                			
                		}
						
					}
					
					this.toPossess.setDead();
					this.entity.setDead();
					this.entity.playSound(GhostlySoundManager.SHADE_POSSESS_ENTITY, 1.0F, 1.0F);
                	
                } else if (this.toPossess.getHealth() > 0.0F) {
                	
                	this.entity.heal(this.toPossess.getHealth() / 2.0F);
                	
                	if (Ghostly.side == Side.CLIENT && Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote && GhostlyConfig.AESTHETICS.shadePossessUndeadEffects) {
						
						double dist = this.entity.getDistance(this.toPossess.getPositionVector().x, this.toPossess.getPositionVector().y, this.toPossess.getPositionVector().z);
                		Vec3d location = new Vec3d(this.entity.posX, this.entity.posY + this.entity.getEyeHeight(), this.entity.posZ);
            			Vec3d direction = location.subtract(this.toPossess.getPositionVector()).normalize();
            			direction = direction.subtract(direction.add(direction));
                		
                		for (double d = 0.0D; d < this.entity.getDistanceSq(this.toPossess); d += 0.2) {
                			
                			 Vec3d dir = direction.scale(d);
                             Vec3d pos = location.add(dir);
                             
                             Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
                			
                		}
						
					}
                	
                	this.toPossess.attackEntityFrom(this.toPossess.getLastDamageSource() != null ? this.toPossess.getLastDamageSource() : DamageSource.GENERIC, Integer.MAX_VALUE);
                	this.entity.playSound(GhostlySoundManager.SHADE_DESTROY_ENTITY, 1.0F, 10F);
                	
                }
                
                resetTask();
                
            }
        }
    }
	
	static class AIInfestEndermanAttack extends EntityAINearestAttackableTarget<EntityLivingBase>
    {
        public AIInfestEndermanAttack(EntityShade shade)
        {
            super(shade, EntityLivingBase.class, 0, true, true, EntityShade.INFEST_ENDERMAN_SELECTOR);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return GhostlyConfig.MOBS.infestedEndermen && GhostlyConfig.MOBS.shadesInfestEndermen && super.shouldExecute();
        }
    }
	
}
