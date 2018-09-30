package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
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

/** If spellcaster, uses lightning to attack instead of fireballs **/
public class EntityShashkra extends EntityMob {

	private static final DataParameter<Byte> SPELL = EntityDataManager.<Byte>createKey(EntityShashkra.class, DataSerializers.BYTE);
	private static final DataParameter<Boolean> SPELLCASTER = EntityDataManager.<Boolean>createKey(EntityShashkra.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FIREBALLING = EntityDataManager.<Boolean>createKey(EntityShashkra.class, DataSerializers.BOOLEAN);
	protected int spellTicks;
    private SpellType activeSpell = EntityShashkra.SpellType.NONE;
	private int explosionStrength = 1;
	
	public EntityShashkra(World worldIn) {
		
		super(worldIn);
		
		experienceValue = 10;
		isImmuneToFire = true;
		setSize(1.0F, 2.5F);
		
	}
	
	/** Gets whether this Shashkra is a spellcaster **/
	public boolean getSpellcaster() {	
		return (boolean) this.dataManager.get(SPELLCASTER).booleanValue();
	}
	
	/** Sets whether this Shashkra is a spellcaster **/
	protected void setSpellcaster(boolean spellcaster) {
		this.dataManager.set(SPELLCASTER, Boolean.valueOf(spellcaster));
	}
	
	/** Gets whether this Shashkra is about to spit a fireball (doesn't affect AI) **/
	public boolean getFireballing() {	
		return (boolean) this.dataManager.get(FIREBALLING).booleanValue();
	}
	
	/** Sets whether this Shashkra is about to spit a fireball (doesn't affect AI) **/
	protected void setFireballing(boolean fireballing) {
		this.dataManager.set(FIREBALLING, Boolean.valueOf(fireballing));
	}
	
	/** Gets the explosion strength of this Shashkra's fireballs **/
	public int getFireballStrength()
    {
        return this.explosionStrength;
    }
	
	public static void registerFixesShashkra(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityShashkra.class);
    }
	
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SPELL, Byte.valueOf((byte)0));
        this.dataManager.register(SPELLCASTER, Boolean.valueOf(false));
        this.dataManager.register(FIREBALLING, Boolean.valueOf(false));
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("SpellTicks", 99))
        {
            this.spellTicks = compound.getInteger("SpellTicks");
        }
        setSpellcaster(compound.getBoolean("Spellcaster"));
        if (compound.hasKey("ExplosionStrength", 99))
        {
            this.explosionStrength = compound.getInteger("ExplosionStrength");
        }
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("SpellTicks", getSpellTicks());
        compound.setBoolean("Spellcaster", getSpellcaster());
        compound.setInteger("ExplosionStrength", getFireballStrength());
    }
	
	@Override
	public void onLivingUpdate()
	{	
		if (!GhostlyConfig.MOBS.shashkras) this.setDead();
		if (getSpellcaster() && (!GhostlyConfig.MOBS.shashkras || !GhostlyConfig.MOBS.spellcasterShashkras)) this.setDead();
		
		super.onLivingUpdate();
	}
	
	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(70.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);

	}
	
	@Override
	protected void initEntityAI() {

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityShashkra.AICastingSpell());
		this.tasks.addTask(3, new EntityShashkra.AILightningSpell(this));
		this.tasks.addTask(3, new EntityShashkra.AIFireballAttack(this));
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
	protected SoundEvent getAmbientSound() {

		return GhostlySoundManager.SHASHKRA_AMBIENT;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return GhostlySoundManager.SHASHKRA_HURT;

	}

	@Override
	protected SoundEvent getDeathSound() {

		return GhostlySoundManager.SHASHKRA_DEATH;

	}

	@Override
	protected ResourceLocation getLootTable() {

		return GhostlyLootTableManager.ENTITIES_SHASHKRA;

	}

	@Override
	public void onDeath(DamageSource cause) {

		super.onDeath(cause);

	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		if (source.isFireDamage()) {

			return false;

		} else {
			
			return super.attackEntityFrom(source, amount);
			
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
	
    public EntityShashkra.ShashkraArmPose getArmPose()
    {
		
		if (isSpellcasting()) return EntityShashkra.ShashkraArmPose.SPELLCASTING;
		
        return EntityShashkra.ShashkraArmPose.EMPTY;
    }

	public void setSpellType(EntityShashkra.SpellType spellType)
    {
        this.activeSpell = spellType;
        this.dataManager.set(SPELL, Byte.valueOf((byte)spellType.id));
    }

    protected EntityShashkra.SpellType getSpellType()
    {
        return !this.world.isRemote ? this.activeSpell : EntityShashkra.SpellType.getFromId(((Byte)this.dataManager.get(SPELL)).byteValue());
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
            EntityShashkra.SpellType entityshashkra$spelltype = getSpellType();
            double d0 = entityshashkra$spelltype.particleSpeed[0];
            double d1 = entityshashkra$spelltype.particleSpeed[1];
            double d2 = entityshashkra$spelltype.particleSpeed[2];
            float f = this.renderYawOffset * 0.017453292F + MathHelper.cos((float)this.ticksExisted * 0.6662F) * 0.25F;
            float f1 = MathHelper.cos(f);
            float f2 = MathHelper.sin(f);
            this.world.spawnParticle(entityshashkra$spelltype.particleType, this.posX + (double)f1 * 0.6D, this.posY + 2.5D, this.posZ + (double)f2 * 0.6D, d0, d1, d2);
            this.world.spawnParticle(entityshashkra$spelltype.particleType, this.posX - (double)f1 * 0.6D, this.posY + 2.5D, this.posZ - (double)f2 * 0.6D, d0, d1, d2);
        }
    }
    
    protected int getSpellTicks()
    {
        return this.spellTicks;
    }

    protected SoundEvent getSpellSound() {
    	
    	if (this.getSpellType() == EntityShashkra.SpellType.LIGHTNING) return GhostlySoundManager.SHASHKRA_PREPARE_LIGHTNING;
    	
		return null;
		
	}
    
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
    	livingdata = super.onInitialSpawn(difficulty, livingdata);
    	
    	if (GhostlyConfig.MOBS.spellcasterShashkras && this.rand.nextInt(2) == 0) {
    		setSpellcaster(true);
    	}
    	
    	setCanPickUpLoot(false);
    	
    	return livingdata;
    }
    
    public static enum ShashkraArmPose
    {
        EMPTY,
        SPELLCASTING;
    }
	
    public static enum SpellType
    {
        NONE(0, EnumParticleTypes.CLOUD, 0.0D, 0.0D, 0.0D),
        LIGHTNING(1, EnumParticleTypes.FIREWORKS_SPARK, 0.3D, 0.3D, 0.3D);

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

        public static EntityShashkra.SpellType getFromId(int idIn)
        {
            for (EntityShashkra.SpellType entityshashkra$spelltype : values())
            {
                if (idIn == entityshashkra$spelltype.id)
                {
                    return entityshashkra$spelltype;
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
            return EntityShashkra.this.getSpellTicks() > 0;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            super.startExecuting();
            EntityShashkra.this.navigator.clearPath();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask()
        {
            super.resetTask();
            EntityShashkra.this.setSpellType(EntityShashkra.SpellType.NONE);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            if (EntityShashkra.this.getAttackTarget() != null)
            {
                EntityShashkra.this.getLookHelper().setLookPositionWithEntity(EntityShashkra.this.getAttackTarget(), (float)EntityShashkra.this.getHorizontalFaceSpeed(), (float)EntityShashkra.this.getVerticalFaceSpeed());
            }
        }
    }
    
    public abstract class AIUseSpell extends EntityAIBase
    {
    	protected int spellWarmup;
		protected int spellCooldown;
		private final EntityShashkra shashkra;
		
		public AIUseSpell(EntityShashkra shashkraIn) {
			shashkra = shashkraIn;
		}
		
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
				return shashkra.ticksExisted >= spellCooldown;
			}
		}
		
		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		@Override
		public void resetTask() {
			
			super.resetTask();
			setSpellType(EntityShashkra.SpellType.NONE);
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
				spellCooldown = shashkra.ticksExisted + getCastingInterval();
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
		
		protected abstract EntityShashkra.SpellType getSpellType();
		
		public boolean basicExecutionRequirements() {
			
			return shashkra.getSpellcaster() && shashkra.getAttackTarget() != null && canEntityBeSeen(getAttackTarget()) && shashkra.getDistanceSq(getAttackTarget()) > 100.0D;
		}
	}
    
    class AILightningSpell extends EntityShashkra.AIUseSpell
    {
        private AILightningSpell(EntityShashkra shashkraIn)
        {
            super(shashkraIn);
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
            else if (EntityShashkra.this.getAttackTarget() != null) {
            	
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
            EntityShashkra.this.world.addWeatherEffect(new EntityLightningBolt(EntityShashkra.this.world, EntityShashkra.this.getAttackTarget().posX, EntityShashkra.this.getAttackTarget().posY, EntityShashkra.this.getAttackTarget().posZ, false));
        }

        @Override
        protected SoundEvent getSpellPrepareSound()
        {
            return GhostlySoundManager.SHASHKRA_PREPARE_LIGHTNING;
        }

        @Override
        protected EntityShashkra.SpellType getSpellType()
        {
            return EntityShashkra.SpellType.LIGHTNING;
        }
    }
    
    static class AIFireballAttack extends EntityAIBase
    {
        public int attackTimer;
        private final EntityShashkra shashkra;

        public AIFireballAttack(EntityShashkra shashkra)
        {
            this.shashkra = shashkra;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return !shashkra.getSpellcaster() && this.shashkra.getAttackTarget() != null && shashkra.canEntityBeSeen(shashkra.getAttackTarget());
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            this.attackTimer = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask()
        {
            this.shashkra.setFireballing(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.shashkra.getAttackTarget();
            double d0 = 64.0D;

            if (entitylivingbase.getDistanceSq(this.shashkra) < 225.0D && this.shashkra.canEntityBeSeen(entitylivingbase))
            {
                World world = this.shashkra.world;
                ++this.attackTimer;

                if (this.attackTimer == 10)
                {
                    world.playSound(shashkra.posX, shashkra.posY, shashkra.posZ, GhostlySoundManager.SHASHKRA_FIREBALL_WARNING, SoundCategory.HOSTILE, 2.0F, 1.0F, false);
                }

                if (this.attackTimer == 20)
                {
                    double d1 = 4.0D;
                    Vec3d vec3d = this.shashkra.getLook(1.0F);
                    double d2 = entitylivingbase.posX - (this.shashkra.posX + vec3d.x * 4.0D);
                    double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.shashkra.posY + (double)(this.shashkra.height / 2.0F));
                    double d4 = entitylivingbase.posZ - (this.shashkra.posZ + vec3d.z * 4.0D);
                    world.playEvent((EntityPlayer)null, 1016, new BlockPos(this.shashkra), 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.shashkra, d2, d3, d4);
                    entitylargefireball.explosionPower = this.shashkra.getFireballStrength();
                    entitylargefireball.posX = this.shashkra.posX + vec3d.x * 0.2D;
                    entitylargefireball.posY = this.shashkra.posY + (double)(this.shashkra.height / 2.0F) + 0.5D;
                    entitylargefireball.posZ = this.shashkra.posZ + vec3d.z * 0.2D;
                    world.spawnEntity(entitylargefireball);
                    this.attackTimer = -40;
                }
            }
            else if (this.attackTimer > 0)
            {
                --this.attackTimer;
            }

            this.shashkra.setFireballing(this.attackTimer > 10);
        }
    }
    
}
