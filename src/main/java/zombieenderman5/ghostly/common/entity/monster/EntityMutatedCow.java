package zombieenderman5.ghostly.common.entity.monster;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityMutatedCow extends EntityMob {
	
	public EntityMutatedCow(World worldIn) {
		
		super(worldIn);
		
		this.setSize(1.1F, 2.35F);
		
	}

	@Override
	public float getEyeHeight() {
		
		return this.height - 0.2F;
		
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (!GhostlyConfig.MOBS.mutatedCows) {
			this.setDead();
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COW_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_COW_HURT;
    }

	@Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COW_DEATH;
    }

	protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_COW_STEP;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
	
	@Override
	@Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_COW;
    }
	
	class AIFleeAtSetHP extends EntityAIBase {
		private final Predicate<Entity> canBeSeenSelector;
		/** The entity we are attached to */
		protected EntityMutatedCow mutatedCow;
		private final double farSpeed;
		private final double nearSpeed;
		protected EntityLivingBase closestLivingEntity;
		private final float avoidDistance;
		/** The PathEntity of our entity */
		private Path path;
		/** The PathNavigate of our entity */
		private final PathNavigate navigation;
		private final Predicate <? super EntityLivingBase> avoidTargetSelector;

		public AIFleeAtSetHP(EntityMutatedCow mutatedCowIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this(mutatedCowIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
		}

		public AIFleeAtSetHP(EntityMutatedCow mutatedCowIn, Predicate <? super EntityLivingBase> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this.canBeSeenSelector = new Predicate<Entity>() {
				@Override
				public boolean apply(@Nullable Entity p_apply_1_) {
					return p_apply_1_.isEntityAlive() && AIFleeAtSetHP.this.mutatedCow.getEntitySenses().canSee(p_apply_1_) && !AIFleeAtSetHP.this.mutatedCow.isOnSameTeam(p_apply_1_);
				}
		    };
		    this.mutatedCow = mutatedCowIn;
		    this.avoidTargetSelector = avoidTargetSelectorIn;
		    this.avoidDistance = avoidDistanceIn;
		    this.farSpeed = farSpeedIn;
		    this.nearSpeed = nearSpeedIn;
		    this.navigation = mutatedCowIn.getNavigator();
		    this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {

		    if (this.mutatedCow.getAttackTarget() == null || this.mutatedCow.getHealth() > (this.mutatedCow.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedCowFleeHealthPercentage) || (this.mutatedCow.getAttackTarget() != null && this.mutatedCow.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") != null && this.mutatedCow.getAttackTarget().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() < 1.0D) || (this.mutatedCow.getAttackTarget() != null && this.mutatedCow.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") == null)) {
		        return false;
		    } else {
		        this.closestLivingEntity = this.mutatedCow.getAttackTarget();
		        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.mutatedCow, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

		        if (vec3d == null) {
		            return false;
		        } else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.mutatedCow)) {
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
		    return !this.navigation.noPath() && this.mutatedCow.getHealth() <= (this.mutatedCow.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedCowFleeHealthPercentage);
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
		    if (this.mutatedCow.getDistanceSq(this.closestLivingEntity) < 49.0D) {
		        this.mutatedCow.getNavigator().setSpeed(this.nearSpeed);
		    } else {
		        this.mutatedCow.getNavigator().setSpeed(this.farSpeed);
		    }
		}
		
	}
	
}
