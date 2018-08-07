package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlyLootTableManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.ai.EntityAIFleeLightDarknessMage;
import zombieenderman5.ghostly.common.entity.ai.EntityAIRestrictLightDarknessMage;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedVenomCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityGiantShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.EntityShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.ICorporealityProjectile;
import zombieenderman5.ghostly.common.item.IToolOfCorporeality;

public class EntityDarknessMage extends EntityMob implements IPartiallyIncorporeal {

	private static final DataParameter<Byte> SPELL = EntityDataManager.<Byte>createKey(EntityDarknessMage.class, DataSerializers.BYTE);
	protected int spellTicks;
    private SpellType activeSpell = EntityDarknessMage.SpellType.NONE;
	
	public EntityDarknessMage(World worldIn) {
		
		super(worldIn);
		
		experienceValue = 7;
		isImmuneToFire = true;
		setSize(1.0F, 3.0F);
		setCanPickUpLoot(false);
		
	}
	
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SPELL, Byte.valueOf((byte)0));
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.spellTicks = compound.getInteger("SpellTicks");
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("SpellTicks", this.spellTicks);
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
		if (this.world.getLightBrightness(new BlockPos(this)) > (float)GhostlyConfig.MOBS.darknessMageDissipationLightLevel)
		{

			if (!this.world.isRemote)
			{
				this.playSound(GhostlySoundManager.DARKNESS_MAGE_DISSIPATE, 1.0F, 1.0F);
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
            this.motionY /= 2;
        }
		
		if (!GhostlyConfig.MOBS.darknessMages) this.setDead();
		
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);

	}
	
	@Override
	protected void initEntityAI() {

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityDarknessMage.AICastingSpell());
		this.tasks.addTask(3, new EntityDarknessMage.AIBlindnessSpell());
        this.tasks.addTask(3, new EntityDarknessMage.AIWitherSpell());
        this.tasks.addTask(3, new EntityDarknessMage.AISummonAssistanceSpell());
        this.tasks.addTask(3, new EntityDarknessMage.AILaunchOrbSpell());
        this.tasks.addTask(3, new EntityDarknessMage.AILaunchGiantOrbSpell());
		this.tasks.addTask(1, new EntityAIFleeLightDarknessMage(this, 1.0D));
		this.tasks.addTask(1, new EntityAIRestrictLightDarknessMage(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 70.0F, 1.0f));
		this.applyEntityAI();

	}

	protected void applyEntityAI() {

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[]{EntityLivingBase.class}));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {

		super.fall(distance, 0.2F);

	}

	@Override
	protected SoundEvent getAmbientSound() {

		return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_AMBIENT_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_AMBIENT;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_HURT_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_HURT;

	}

	@Override
	protected SoundEvent getDeathSound() {

		return GhostlySoundManager.DARKNESS_MAGE_DEATH;

	}

	protected SoundEvent getStepSound() {

		return null;

	}

	@Override
	protected ResourceLocation getLootTable() {

		return GhostlyLootTableManager.ENTITIES_DARKNESS_MAGE;

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

		return this.world.getLightBrightness(pos) >= (float)GhostlyConfig.MOBS.darknessMageDissipationLightLevel ? -10.0F : super.getBlockPathWeight(pos);
	}

	@Override
	public void onDeath(DamageSource cause) {

		super.onDeath(cause);

	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		EntityLivingBase sourceLiving = null;
		
		if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase) {
			
			sourceLiving = (EntityLivingBase) source.getTrueSource();
			
		}
		
		if (source.isExplosion()) {

			return false;

		} else if (source.isFireDamage()) {

			return false;

		} else if (source.isMagicDamage()) {

			return false;

		} else if (source instanceof EntityDamageSourceIndirect && !(source.getImmediateSource() instanceof EntityCorporealityArrow) && !(source.getImmediateSource() instanceof EntityDustedCorporealityArrow || source.getImmediateSource() instanceof EntityDustedVenomCorporealityArrow)) {

			return false;

		} else if (source instanceof EntityDamageSourceIndirect && source.getImmediateSource() instanceof EntityCorporealityArrow && !(source.getImmediateSource() instanceof EntityDustedCorporealityArrow || source.getImmediateSource() instanceof EntityDustedVenomCorporealityArrow)) {

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
	
	public boolean isSpellcasting()
    {
        if (this.world.isRemote)
        {
            return ((Byte)this.dataManager.get(SPELL)).byteValue() > 0;
        }
        else
        {
            return this.spellTicks > 0;
        }
    }
	
	@SideOnly(Side.CLIENT)
    public EntityDarknessMage.DarknessMageArmPose getArmPose()
    {
		
		if (isSpellcasting()) return EntityDarknessMage.DarknessMageArmPose.SPELLCASTING;
		
        return EntityDarknessMage.DarknessMageArmPose.EMPTY;
    }

	public void setSpellType(EntityDarknessMage.SpellType spellType)
    {
        this.activeSpell = spellType;
        this.dataManager.set(SPELL, Byte.valueOf((byte)spellType.id));
    }

    protected EntityDarknessMage.SpellType getSpellType()
    {
        return !this.world.isRemote ? this.activeSpell : EntityDarknessMage.SpellType.getFromId(((Byte)this.dataManager.get(SPELL)).byteValue());
    }
	
    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();

        if (this.spellTicks > 0)
        {
            --this.spellTicks;
        }
    }
    
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote && this.isSpellcasting())
        {
            EntityDarknessMage.SpellType entityspellcasterillager$spelltype = getSpellType();
            double d0 = entityspellcasterillager$spelltype.particleSpeed[0];
            double d1 = entityspellcasterillager$spelltype.particleSpeed[1];
            double d2 = entityspellcasterillager$spelltype.particleSpeed[2];
            float f = this.renderYawOffset * 0.017453292F + MathHelper.cos((float)this.ticksExisted * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.world.spawnParticle(entityspellcasterillager$spelltype.particleType, this.posX + (double)f1 * 0.6D, this.posY + 2.5D, this.posZ + (double)f2 * 0.6D, d0, d1, d2);
            this.world.spawnParticle(entityspellcasterillager$spelltype.particleType, this.posX - (double)f1 * 0.6D, this.posY + 2.5D, this.posZ - (double)f2 * 0.6D, d0, d1, d2);
        }
    }
    
    protected int getSpellTicks()
    {
        return this.spellTicks;
    }

    protected SoundEvent getSpellSound() {
    	
    	if (this.getSpellType() == EntityDarknessMage.SpellType.BLINDNESS) return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_PREPARE_BLINDNESS_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_PREPARE_BLINDNESS;
    	if (this.getSpellType() == EntityDarknessMage.SpellType.WITHER) return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_PREPARE_WITHER_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_PREPARE_WITHER;
    	if (this.getSpellType() == EntityDarknessMage.SpellType.SUMMON_ASSISTANCE) return GhostlySoundManager.DARKNESS_MAGE_PREPARE_SUMMON;
    	if (this.getSpellType() == EntityDarknessMage.SpellType.LAUNCH_ORB) return GhostlySoundManager.DARKNESS_MAGE_PREPARE_ORB;
    	if (this.getSpellType() == EntityDarknessMage.SpellType.LAUNCH_GIANT_ORB) return GhostlySoundManager.DARKNESS_MAGE_PREPARE_ORB;
    	
		return null;
		
	}
    
    @SideOnly(Side.CLIENT)
    public static enum DarknessMageArmPose
    {
        EMPTY,
        SPELLCASTING;
    }
	
    public static enum SpellType
    {
        NONE(0, EnumParticleTypes.CLOUD, 0.0D, 0.0D, 0.0D),
        BLINDNESS(1, EnumParticleTypes.SPELL, 0.3D, 0.3D, 0.3D),
        WITHER(1, EnumParticleTypes.SPELL, 0.3D, 0.3D, 0.3D),
        SUMMON_ASSISTANCE(2, EnumParticleTypes.FIREWORKS_SPARK, 0.3D, 0.3D, 0.3D),
        LAUNCH_ORB(3, EnumParticleTypes.SMOKE_NORMAL, 0.3D, 0.3D, 0.3D),
        LAUNCH_GIANT_ORB(4, EnumParticleTypes.SMOKE_LARGE, 0.3D, 0.3D, 0.3D);

        private final int id;
        private final EnumParticleTypes particleType;
        /** Particle motion speed. An array with 3 values: x, y, and z. */
        private final double[] particleSpeed;

        private SpellType(int idIn, EnumParticleTypes type, double xParticleSpeed, double yParticleSpeed, double zParticleSpeed)
        {
            id = idIn;
            particleType = type;
            particleSpeed = new double[] {xParticleSpeed, yParticleSpeed, zParticleSpeed};
        }

        public static EntityDarknessMage.SpellType getFromId(int idIn)
        {
            for (EntityDarknessMage.SpellType entityspellcasterillager$spelltype : values())
            {
                if (idIn == entityspellcasterillager$spelltype.id)
                {
                    return entityspellcasterillager$spelltype;
                }
            }

            return NONE;
        }
    }
    
    public class AICastingSpell extends EntityAIBase
    {
        public AICastingSpell()
        {
            this.setMutexBits(3);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return EntityDarknessMage.this.getSpellTicks() > 0;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
            EntityDarknessMage.this.navigator.clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask()
        {
            super.resetTask();
            EntityDarknessMage.this.setSpellType(EntityDarknessMage.SpellType.NONE);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            if (EntityDarknessMage.this.getAttackTarget() != null)
            {
                EntityDarknessMage.this.getLookHelper().setLookPositionWithEntity(EntityDarknessMage.this.getAttackTarget(), (float)EntityDarknessMage.this.getHorizontalFaceSpeed(), (float)EntityDarknessMage.this.getVerticalFaceSpeed());
            }
        }
    }
    
    public abstract class AIUseSpell extends EntityAIBase
    {
    	protected int spellWarmup;
		protected int spellCooldown;
		
		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {
			
			if (getAttackTarget() == null) {
				return false;
			} else if (isSpellcasting()) {
				return false;
			} else {
				return EntityDarknessMage.this.ticksExisted >= spellCooldown;
			}
		}
		
		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		@Override
		public void resetTask() {
			
			super.resetTask();
			setSpellType(EntityDarknessMage.SpellType.NONE);
			spellTicks = 0;
		}
		
		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		@Override
		public boolean shouldContinueExecuting() {
			
			return getAttackTarget() != null && spellWarmup > 0;
		}
		
		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		@Override
		public void startExecuting() {
			
			spellWarmup = getCastWarmupTime();
			spellTicks = getCastingTime();
			SoundEvent soundevent = getSpellPrepareSound();
			
			if (soundevent != null) {
				playSound(soundevent, 1.0F, 1.0F);
			}
			
			setSpellType(getSpellType());
		}
		
		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void updateTask() {
			
			--spellWarmup;
			
			if (spellWarmup == 0) {
				castSpell();
				spellCooldown = EntityDarknessMage.this.ticksExisted + getCastingInterval();
				playSound(getSpellSound(), 1.0F, 1.0F);
			}
		}
		
		protected abstract void castSpell();
		
		protected int getCastWarmupTime() {
			
			return 20;
		}
		
		protected abstract int getCastingTime();
		
		protected abstract int getCastingInterval();
		
		@Nullable
		protected abstract SoundEvent getSpellPrepareSound();
		
		protected abstract EntityDarknessMage.SpellType getSpellType();
		
		public boolean basicExecutionRequirements() {
			
			return EntityDarknessMage.this.getAttackTarget() != null && canEntityBeSeen(getAttackTarget()) && EntityDarknessMage.this.getDistanceSq(getAttackTarget()) > 100.0D;
		}
	}
    
    class AIBlindnessSpell extends EntityDarknessMage.AIUseSpell
    {

    	private int lastTargetId;
    	
        private AIBlindnessSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            if (!super.shouldExecute())
            {
                return false;
            }
            else if (EntityDarknessMage.this.getAttackTarget() != null && EntityDarknessMage.this.getAttackTarget().getEntityId() == this.lastTargetId) {
            	
            	return false;
            	
            } else {
            	
            	return basicExecutionRequirements();
            	
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
            this.lastTargetId = EntityDarknessMage.this.getAttackTarget().getEntityId();
        }

        @Override
		protected int getCastWarmupTime() {
			
			return 30;
		}
        
        @Override
        protected int getCastingTime()
        {
            return 20;
        }

        @Override
        protected int getCastingInterval()
        {
            return 100;
        }

        @Override
        protected void castSpell()
        {
            EntityDarknessMage.this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 400));
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_PREPARE_BLINDNESS_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_PREPARE_BLINDNESS;
        }

        @Override
        protected EntityDarknessMage.SpellType getSpellType()
        {
            return EntityDarknessMage.SpellType.BLINDNESS;
        }
    }
    
    class AIWitherSpell extends EntityDarknessMage.AIUseSpell
    {

        private AIWitherSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
        	if (!super.shouldExecute())
            {
                return false;
            }
            else {
            	
            	return basicExecutionRequirements();
            	
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }

        @Override
		protected int getCastWarmupTime() {
			
			return 60;
		}
        
        @Override
        protected int getCastingTime()
        {
            return 20;
        }

        @Override
        protected int getCastingInterval()
        {
            return 220;
        }

        @Override
        protected void castSpell()
        {
            EntityDarknessMage.this.getAttackTarget().addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlyConfig.AUDIO.alternateDarknessMageAudio ? GhostlySoundManager.DARKNESS_MAGE_PREPARE_WITHER_ALTERNATE : GhostlySoundManager.DARKNESS_MAGE_PREPARE_WITHER;
        }

        @Override
        protected EntityDarknessMage.SpellType getSpellType()
        {
            return EntityDarknessMage.SpellType.WITHER;
        }
    }
    
    class AISummonAssistanceSpell extends EntityDarknessMage.AIUseSpell
    {

        private AISummonAssistanceSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
        	if (!super.shouldExecute())
            {
                return false;
            }
        	else if (!GhostlyConfig.MOBS.shadowRemnants) {
        		return false;
        	}
            else {
            	
            	return basicExecutionRequirements();
            	
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }

        @Override
		protected int getCastWarmupTime() {
			
			return 60;
		}
        
        @Override
        protected int getCastingTime()
        {
            return 20;
        }

        @Override
        protected int getCastingInterval()
        {
            return 150;
        }

        @Override
        protected void castSpell()
        {
        	BlockPos blockpos = (new BlockPos(EntityDarknessMage.this)).add(-2 + EntityDarknessMage.this.rand.nextInt(5), 1, -2 + EntityDarknessMage.this.rand.nextInt(5));
            EntityShadowRemnant assistantToSummon = new EntityShadowRemnant(EntityDarknessMage.this.world);
            assistantToSummon.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
            assistantToSummon.onInitialSpawn(EntityDarknessMage.this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
            assistantToSummon.setOwner(EntityDarknessMage.this);
            assistantToSummon.setBoundOrigin(blockpos);
            assistantToSummon.setAttackTarget(EntityDarknessMage.this.getAttackTarget());
            assistantToSummon.setRevengeTarget(EntityDarknessMage.this.getRevengeTarget());
            EntityDarknessMage.this.world.spawnEntity(assistantToSummon);
            if (Ghostly.side == Side.CLIENT && Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote && GhostlyConfig.AESTHETICS.shadePossessUndeadEffects) {
				
				double dist = EntityDarknessMage.this.getDistance(assistantToSummon.getPositionVector().x, assistantToSummon.getPositionVector().y, assistantToSummon.getPositionVector().z);
        		Vec3d location = new Vec3d(EntityDarknessMage.this.posX, EntityDarknessMage.this.posY + EntityDarknessMage.this.getEyeHeight(), EntityDarknessMage.this.posZ);
    			Vec3d direction = location.subtract(assistantToSummon.getPositionVector()).normalize();
    			direction = direction.subtract(direction.add(direction));
        		
        		for (double d = 0.0D; d < EntityDarknessMage.this.getDistanceSq(assistantToSummon); d += 0.2) {
        			
        			 Vec3d dir = direction.scale(d);
                     Vec3d pos = location.add(dir);
                     
                     Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
        		}
				
			}
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlySoundManager.DARKNESS_MAGE_PREPARE_SUMMON;
        }

        @Override
        protected EntityDarknessMage.SpellType getSpellType()
        {
            return EntityDarknessMage.SpellType.SUMMON_ASSISTANCE;
        }
    }
    
    class AILaunchOrbSpell extends EntityDarknessMage.AIUseSpell
    {

        private AILaunchOrbSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
        	if (!super.shouldExecute())
            {
                return false;
            }
            else {
            	
            	return basicExecutionRequirements();
            	
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }

        @Override
		protected int getCastWarmupTime() {
			
			return 30;
		}
        
        @Override
        protected int getCastingTime()
        {
            return 30;
        }

        @Override
        protected int getCastingInterval()
        {
            return 160;
        }

        @Override
        protected void castSpell()
        {
            EntityDarknessMage darknessMage = EntityDarknessMage.this;
            EntityLivingBase livingBase = darknessMage.getAttackTarget();
            double x = livingBase.posX - darknessMage.posX;
			double y = livingBase.getEntityBoundingBox().minY + (double) (livingBase.height / 2.0F) - (darknessMage.posY + (double) (darknessMage.height / 2.0F));
			double z = livingBase.posZ - darknessMage.posZ;
			EntityShadowOrb orb = new EntityShadowOrb(darknessMage.world, darknessMage, x, y, z);
			orb.posY = darknessMage.posY + (double) (darknessMage.height / 2.0F) + 0.5D;
			darknessMage.world.spawnEntity(orb);
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlySoundManager.DARKNESS_MAGE_PREPARE_ORB;
        }

        @Override
        protected EntityDarknessMage.SpellType getSpellType()
        {
            return EntityDarknessMage.SpellType.LAUNCH_ORB;
        }
    }
    
    class AILaunchGiantOrbSpell extends EntityDarknessMage.AIUseSpell
    {

        private AILaunchGiantOrbSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
        	if (!super.shouldExecute())
            {
                return false;
            }
            else {
            	
            	return basicExecutionRequirements();
            	
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }

        @Override
		protected int getCastWarmupTime() {
			
			return 50;
		}
        
        @Override
        protected int getCastingTime()
        {
            return 50;
        }

        @Override
        protected int getCastingInterval()
        {
            return 250;
        }

        @Override
        protected void castSpell()
        {
            EntityDarknessMage darknessMage = EntityDarknessMage.this;
            EntityLivingBase livingBase = darknessMage.getAttackTarget();
            double x = livingBase.posX - darknessMage.posX;
			double y = livingBase.getEntityBoundingBox().minY + (double) (livingBase.height / 2.0F) - (darknessMage.posY + (double) (darknessMage.height / 2.0F));
			double z = livingBase.posZ - darknessMage.posZ;
			EntityGiantShadowOrb orb = new EntityGiantShadowOrb(darknessMage.world, darknessMage, x, y, z);
			orb.posY = darknessMage.posY + (double) (darknessMage.height / 2.0F) + 0.5D;
			darknessMage.world.spawnEntity(orb);
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlySoundManager.DARKNESS_MAGE_PREPARE_ORB;
        }

        @Override
        protected EntityDarknessMage.SpellType getSpellType()
        {
            return EntityDarknessMage.SpellType.LAUNCH_GIANT_ORB;
        }
    }
    
}
