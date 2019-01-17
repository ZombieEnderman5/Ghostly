package zombieenderman5.ghostly.common.entity.monster;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityMutatedSheep extends EntityMob {
	
	private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.<Byte>createKey(EntityMutatedSheep.class, DataSerializers.BYTE);
	private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);
	
	public EntityMutatedSheep(World worldIn) {
		
		super(worldIn);
		
		this.setSize(0.9F, 2.0F);
		
	}

	@Override
	public float getEyeHeight() {
		
		return this.height - 0.2F;
		
	}
	
	@Override
	protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DYE_COLOR, Byte.valueOf((byte)0));
    }
	
	private static float[] createSheepColor(EnumDyeColor dyeColor) {
        float[] f = dyeColor.getColorComponentValues();
        float f1 = 0.75F;
        return new float[] {f[0] * 0.75F, f[1] * 0.75F, f[2] * 0.75F};
    }

    @SideOnly(Side.CLIENT)
    public static float[] getDyeRgb(EnumDyeColor dyeColor) {
        return DYE_TO_RGB.get(dyeColor);
    }
	
    public EnumDyeColor getFleeceColor() {
        return EnumDyeColor.byMetadata(((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 15);
    }
    
    public void setFleeceColor(EnumDyeColor color) {
        byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();
        this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & 240 | color.getMetadata() & 15)));
    }
    
    public static EnumDyeColor getRandomMutatedSheepColor(Random random) {
        int i = random.nextInt(100);

        if (i < 5) {
            return EnumDyeColor.BLACK;
        } else if (i < 10) {
            return EnumDyeColor.GRAY;
        } else if (i < 15) {
            return EnumDyeColor.SILVER;
        } else if (i < 18) {
            return EnumDyeColor.BROWN;
        } else {
            return random.nextInt(500) == 0 ? EnumDyeColor.PINK : EnumDyeColor.WHITE;
        }
    }
    
    public static void registerFixesSheep(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityMutatedSheep.class);
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
		
		if (!GhostlyConfig.MOBS.mutatedSheep) {
			this.setDead();
		}
	}
	
	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setFleeceColor(getRandomMutatedSheepColor(this.world.rand));
        return livingdata;
    }
	
	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
	@Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("Color", (byte)this.getFleeceColor().getMetadata());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setFleeceColor(EnumDyeColor.byMetadata(compound.getByte("Color")));
    }
	
	@Override
	protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

	@Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

	protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SHEEP_STEP;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
	
	@Override
	@Nullable
    protected ResourceLocation getLootTable() {
		switch (this.getFleeceColor()) {
            case WHITE:
            default:
                return LootTableList.ENTITIES_SHEEP_WHITE;
            case ORANGE:
                return LootTableList.ENTITIES_SHEEP_ORANGE;
            case MAGENTA:
                return LootTableList.ENTITIES_SHEEP_MAGENTA;
            case LIGHT_BLUE:
                return LootTableList.ENTITIES_SHEEP_LIGHT_BLUE;
            case YELLOW:
                return LootTableList.ENTITIES_SHEEP_YELLOW;
            case LIME:
                return LootTableList.ENTITIES_SHEEP_LIME;
            case PINK:
                return LootTableList.ENTITIES_SHEEP_PINK;
            case GRAY:
                return LootTableList.ENTITIES_SHEEP_GRAY;
            case SILVER:
                return LootTableList.ENTITIES_SHEEP_SILVER;
            case CYAN:
                return LootTableList.ENTITIES_SHEEP_CYAN;
            case PURPLE:
                return LootTableList.ENTITIES_SHEEP_PURPLE;
            case BLUE:
                return LootTableList.ENTITIES_SHEEP_BLUE;
            case BROWN:
                return LootTableList.ENTITIES_SHEEP_BROWN;
            case GREEN:
                return LootTableList.ENTITIES_SHEEP_GREEN;
            case RED:
                return LootTableList.ENTITIES_SHEEP_RED;
            case BLACK:
                return LootTableList.ENTITIES_SHEEP_BLACK;
        }
    }
	
	static {
        for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
            DYE_TO_RGB.put(enumdyecolor, createSheepColor(enumdyecolor));
        }

        DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[] {0.9019608F, 0.9019608F, 0.9019608F});
    }
	
	class AIFleeAtSetHP extends EntityAIBase {
		private final Predicate<Entity> canBeSeenSelector;
		/** The entity we are attached to */
		protected EntityMutatedSheep mutatedSheep;
		private final double farSpeed;
		private final double nearSpeed;
		protected EntityLivingBase closestLivingEntity;
		private final float avoidDistance;
		/** The PathEntity of our entity */
		private Path path;
		/** The PathNavigate of our entity */
		private final PathNavigate navigation;
		private final Predicate <? super EntityLivingBase> avoidTargetSelector;

		public AIFleeAtSetHP(EntityMutatedSheep mutatedSheepIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this(mutatedSheepIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
		}

		public AIFleeAtSetHP(EntityMutatedSheep mutatedSheepIn, Predicate <? super EntityLivingBase> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this.canBeSeenSelector = new Predicate<Entity>() {
				@Override
				public boolean apply(@Nullable Entity p_apply_1_) {
					return p_apply_1_.isEntityAlive() && AIFleeAtSetHP.this.mutatedSheep.getEntitySenses().canSee(p_apply_1_) && !AIFleeAtSetHP.this.mutatedSheep.isOnSameTeam(p_apply_1_);
				}
		    };
		    this.mutatedSheep = mutatedSheepIn;
		    this.avoidTargetSelector = avoidTargetSelectorIn;
		    this.avoidDistance = avoidDistanceIn;
		    this.farSpeed = farSpeedIn;
		    this.nearSpeed = nearSpeedIn;
		    this.navigation = mutatedSheepIn.getNavigator();
		    this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {

		    if (this.mutatedSheep.getAttackTarget() == null || this.mutatedSheep.getHealth() > (this.mutatedSheep.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedSheepFleeHealthPercentage) || (this.mutatedSheep.getAttackTarget() != null && this.mutatedSheep.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") != null && this.mutatedSheep.getAttackTarget().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() < 1.0D) || (this.mutatedSheep.getAttackTarget() != null && this.mutatedSheep.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") == null)) {
		        return false;
		    } else {
		        this.closestLivingEntity = this.mutatedSheep.getAttackTarget();
		        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.mutatedSheep, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

		        if (vec3d == null) {
		            return false;
		        } else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.mutatedSheep)) {
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
		    return !this.navigation.noPath() && this.mutatedSheep.getHealth() <= (this.mutatedSheep.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedSheepFleeHealthPercentage);
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
		    if (this.mutatedSheep.getDistanceSq(this.closestLivingEntity) < 49.0D) {
		        this.mutatedSheep.getNavigator().setSpeed(this.nearSpeed);
		    } else {
		        this.mutatedSheep.getNavigator().setSpeed(this.farSpeed);
		    }
		}
		
	}
	
}
