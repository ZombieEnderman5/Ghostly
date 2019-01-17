package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityMutatedDonkey extends EntityMob {

	private static final DataParameter<Byte> STATUS = EntityDataManager.<Byte>createKey(AbstractHorse.class, DataSerializers.BYTE);
    private float mouthOpenness;
    private float prevMouthOpenness;
    private int openMouthCounter;
    public int tailCounter;
	
	public EntityMutatedDonkey(World worldIn) {
		
		super(worldIn);
		
		this.setSize(0.675F, 2.175F);
		
	}

	@Override
	public float getEyeHeight() {
		
		return this.height - 0.2F;
		
	}
	
	@Override
	protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATUS, Byte.valueOf((byte)0));
    }

    public static void registerFixesMutatedDonkey(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityMutatedDonkey.class);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
            this.openMouthCounter = 0;
            this.setMutatedDonkeyWatchableBoolean(64, false);
        }
        
        if (this.tailCounter > 0 && ++this.tailCounter > 8) {
            this.tailCounter = 0;
        }
        
        if (this.world.isRemote && this.dataManager.isDirty()) {
            this.dataManager.setClean();
        }
        
        this.prevMouthOpenness = this.mouthOpenness;

        if (this.getMutatedDonkeyWatchableBoolean(64)) {
            this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;

            if (this.mouthOpenness > 1.0F) {
                this.mouthOpenness = 1.0F;
            }
        } else {
            this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;

            if (this.mouthOpenness < 0.0F) {
                this.mouthOpenness = 0.0F;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public float getMouthOpennessAngle(float f1) {
        return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * f1;
    }
    
    private void openMutatedDonkeyMouth() {
        if (!this.world.isRemote) {
            this.openMouthCounter = 1;
            this.setMutatedDonkeyWatchableBoolean(64, true);
        }
    }
    
    protected boolean getMutatedDonkeyWatchableBoolean(int i1) {
        return (((Byte)this.dataManager.get(STATUS)).byteValue() & i1) != 0;
    }
    
    protected void setMutatedDonkeyWatchableBoolean(int i1, boolean b1) {
        byte b0 = ((Byte)this.dataManager.get(STATUS)).byteValue();

        if (b1) {
            this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 | i1)));
        } else {
            this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 & ~i1)));
        }
    }
    
	@Override
	protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AIFleeAtSetHP(this, 10.0F, 1.2F, 1.0F));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.addTargetingAI();
    }
	
	protected void addTargetingAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityWitch.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, AbstractIllager.class, true));
    }
	
	@Override
	protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (this.rand.nextInt(200) == 0) {
            this.moveTail();
        }
		
		if (!GhostlyConfig.MOBS.mutatedDonkeys) {
			this.setDead();
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		this.openMutatedDonkeyMouth();
        return SoundEvents.ENTITY_DONKEY_ANGRY;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		this.openMutatedDonkeyMouth();
        return SoundEvents.ENTITY_DONKEY_HURT;
    }

	@Override
    protected SoundEvent getDeathSound() {
		this.openMutatedDonkeyMouth();
        return SoundEvents.ENTITY_DONKEY_DEATH;
    }

	protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_HORSE_STEP;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
	
	@Override
	@Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_DONKEY;
    }
	
	@Override
	public int getTalkInterval() {
        return 400;
    }
	
	private void moveTail() {
        this.tailCounter = 1;
    }
	
	class AIFleeAtSetHP extends EntityAIBase {
		private final Predicate<Entity> canBeSeenSelector;
		/** The entity we are attached to */
		protected EntityMutatedDonkey mutatedDonkey;
		private final double farSpeed;
		private final double nearSpeed;
		protected EntityLivingBase closestLivingEntity;
		private final float avoidDistance;
		/** The PathEntity of our entity */
		private Path path;
		/** The PathNavigate of our entity */
		private final PathNavigate navigation;
		private final Predicate <? super EntityLivingBase> avoidTargetSelector;

		public AIFleeAtSetHP(EntityMutatedDonkey mutatedDonkeyIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this(mutatedDonkeyIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
		}

		public AIFleeAtSetHP(EntityMutatedDonkey mutatedDonkeyIn, Predicate <? super EntityLivingBase> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this.canBeSeenSelector = new Predicate<Entity>() {
				@Override
				public boolean apply(@Nullable Entity p_apply_1_) {
					return p_apply_1_.isEntityAlive() && AIFleeAtSetHP.this.mutatedDonkey.getEntitySenses().canSee(p_apply_1_) && !AIFleeAtSetHP.this.mutatedDonkey.isOnSameTeam(p_apply_1_);
				}
		    };
		    this.mutatedDonkey = mutatedDonkeyIn;
		    this.avoidTargetSelector = avoidTargetSelectorIn;
		    this.avoidDistance = avoidDistanceIn;
		    this.farSpeed = farSpeedIn;
		    this.nearSpeed = nearSpeedIn;
		    this.navigation = mutatedDonkeyIn.getNavigator();
		    this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {

		    if (this.mutatedDonkey.getAttackTarget() == null || this.mutatedDonkey.getHealth() > (this.mutatedDonkey.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedWolfFleeHealthPercentage) || (this.mutatedDonkey.getAttackTarget() != null && this.mutatedDonkey.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") != null && this.mutatedDonkey.getAttackTarget().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() < 1.0D) || (this.mutatedDonkey.getAttackTarget() != null && this.mutatedDonkey.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") == null)) {
		        return false;
		    } else {
		        this.closestLivingEntity = this.mutatedDonkey.getAttackTarget();
		        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.mutatedDonkey, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

		        if (vec3d == null) {
		            return false;
		        } else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.mutatedDonkey)) {
		            return false;
		        } else {
		            this.path = this.navigation.getPathToXYZ(vec3d.x, vec3d.y, vec3d.z);
		            return this.path != null;
		        }
		    }
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		@Override
		public boolean shouldContinueExecuting() {
		    return !this.navigation.noPath() && this.mutatedDonkey.getHealth() <= (this.mutatedDonkey.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedWolfFleeHealthPercentage);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		@Override
		public void startExecuting() {
		    this.navigation.setPath(this.path, this.farSpeed);
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		@Override
		public void resetTask() {
		    this.closestLivingEntity = null;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@Override
		public void updateTask() {
		    if (this.mutatedDonkey.getDistanceSq(this.closestLivingEntity) < 49.0D) {
		        this.mutatedDonkey.getNavigator().setSpeed(this.nearSpeed);
		    } else {
		        this.mutatedDonkey.getNavigator().setSpeed(this.farSpeed);
		    }
		}
		
	}
	
}
