package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import gatocreador887.hardcoredimensionexpansion.common.core.HDELootTableManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;

public class EntityPossessedHunchbone extends AbstractSkeleton implements IPossessed {

	private static final DataParameter<Integer> EYE_TYPE = EntityDataManager.<Integer>createKey(EntityPossessedHunchbone.class, DataSerializers.VARINT);
	private static int oldEyeType = -1;
	private static int newEyeType = -1;
	private static boolean multicolorEyes = false;
	
	public EntityPossessedHunchbone(World worldIn) {
		
		super(worldIn);
	}
	
	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyes && newEyeType == -1) {
			
			oldEyeType = this.getEyeType();
			this.setEyeType(this.rand.nextInt(4));
			newEyeType = this.getEyeType();
			
		} else if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyes && newEyeType != -1) {
			
			this.setEyeType(newEyeType);
			
		}
		
		if (this.getEyeType() <= 3 && multicolorEyes && GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyes && oldEyeType != -1) {
			
			this.setEyeType(oldEyeType);
			
		}
		
		if (!Loader.isModLoaded("hardcoredimensionexpansion") || !GhostlyConfig.MOBS.possessedHunchbones) {
			
			this.setDead();
			
		}
		
	}
	
	@Override
	protected void entityInit() {
		
		super.entityInit();
		
		this.getDataManager().register(EYE_TYPE, Integer.valueOf(0));
		
	}
	
	@Override
	protected void initEntityAI () {
		
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		
	}
	
	public int getEyeType() {
		
		return ((Integer)this.dataManager.get(EYE_TYPE)).intValue();
		
	}
	
	public void setEyeType(int eyeType) {
		
		this.dataManager.set(EYE_TYPE, Integer.valueOf(eyeType));
		
	}
	
	@Override
	protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(90.0D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
	}
	
	public static void registerFixesPossessedHunchbone(DataFixer fixer) {
		
		EntityLiving.registerFixesMob(fixer, EntityPossessedHunchbone.class);
	}
	
	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		
		if (Loader.isModLoaded("hardcoredimensionexpansion")) {
			
			try {
				
				return HDELootTableManager.ENTITIES_HUNCHBONE;
				
			} catch (NoClassDefFoundError ncdfe) {
				
				
				
			}
			
		}
		
		return null;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		
		return GhostlySoundManager.POSSESSED_HUNCHBONE_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		
		return GhostlySoundManager.POSSESSED_HUNCHBONE_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		
		return GhostlySoundManager.POSSESSED_HUNCHBONE_DEATH;
	}
	
	@Override
	protected float getSoundPitch() {
		
		return super.getSoundPitch() - 0.25F;
	}
	
	@Override
	protected SoundEvent getStepSound() {
		
		return SoundEvents.ENTITY_SKELETON_STEP;
	}
	
	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource cause) {
		
		super.onDeath(cause);
		
		if (cause.getTrueSource() instanceof EntityCreeper) {
			EntityCreeper entitycreeper = (EntityCreeper) cause.getTrueSource();
			
			if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop()) {
				entitycreeper.incrementDroppedSkulls();
				entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
			}
		}
	}
	
	@Override
	protected EntityArrow getArrow(float p_190726_1_) {
		
		ItemStack itemstack = getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
		
		if (itemstack.getItem() == Items.SPECTRAL_ARROW) {
			EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(world, this);
			entityspectralarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
			return entityspectralarrow;
		} else {
			EntityArrow entityarrow = super.getArrow(p_190726_1_);
			
			if (itemstack.getItem() == Items.TIPPED_ARROW && entityarrow instanceof EntityTippedArrow) {
				((EntityTippedArrow) entityarrow).setPotionEffect(itemstack);
			}
			
			return entityarrow;
		}
	}
	
	/**
	 * Gives armor or weapon for entity based on given DifficultyInstance
	 */
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		
		super.setEquipmentBasedOnDifficulty(difficulty);
		
		setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
		
		if (rand.nextFloat() < (world.getDifficulty() == EnumDifficulty.NORMAL ? 0.3F : world.getDifficulty() == EnumDifficulty.HARD ? 0.5F : 0.15F)) {
			if (rand.nextInt(5) == 0) {
				setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
			} else if (rand.nextInt(3) == 0) {
				setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			}
		}
		this.setEnchantmentBasedOnDifficulty(difficulty);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setInteger("EyeType", this.getEyeType());
        
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        this.setEyeType(compound.getInteger("EyeType"));
        
    }
	
	/**
	 * Called only once on an entity when first time spawned, via egg, mob spawner,
	 * natural spawning etc, but not called when entity is reloaded from nbt. Mainly
	 * used for initializing attributes and inventory
	 */
	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		setCombatTask();
		
		this.setEyeType(this.rand.nextInt(4));
    	
    	if (GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyes && GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyesChance != 0 && this.rand.nextInt(GhostlyConfig.AESTHETICS.multicolorPossessedHunchboneEyesChance) == 0) {
    		
    		this.setEyeType(this.getEyeType() + this.rand.nextInt(11));
    		
    		if (this.getEyeType() > 3) {
    			
    			multicolorEyes = true;
    			
    		}
    		
    	}
    	
    	if (!this.getHeldItemMainhand().isEmpty() || !this.getHeldItemOffhand().isEmpty()) {
    		
    		this.setEnchantmentBasedOnDifficulty(difficulty);
    		
    	}
    	
    	this.setCanPickUpLoot(true);
		
		return livingdata;
	}

	public static boolean dissolutionGeneratePossessedVersion() {
    	
    	return false;
    	
    }
	
    public boolean canExist() {
    	
    	return GhostlyConfig.MOBS.possessedHunchbones;
    	
    }
	
}
