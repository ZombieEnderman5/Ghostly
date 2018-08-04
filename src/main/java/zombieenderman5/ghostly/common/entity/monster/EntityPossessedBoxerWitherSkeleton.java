package zombieenderman5.ghostly.common.entity.monster;

import java.util.UUID;

import javax.annotation.Nullable;

import ladysnake.dissolution.api.corporeality.IPossessable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
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
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;

public class EntityPossessedBoxerWitherSkeleton extends AbstractSkeleton implements IRangedAttackMob, IPossessedBoxer {
	
	private static final DataParameter<Integer> EYE_TYPE = EntityDataManager.<Integer>createKey(EntityPossessedBoxerWitherSkeleton.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> GLOVES_TYPE = EntityDataManager.<Integer>createKey(EntityPossessedBoxerWitherSkeleton.class, DataSerializers.VARINT);
	private static int oldEyeType = -1;
	private static int newEyeType = -1;
	private static boolean multicolorEyes = false;
	
	public EntityPossessedBoxerWitherSkeleton(World worldIn)
    {
        super(worldIn);
        this.setSize(0.7F, 2.4F);
        this.isImmuneToFire = true;
    }

	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyes && newEyeType == -1) {
			
			oldEyeType = this.getEyeType();
			this.setEyeType(this.rand.nextInt(4));
			newEyeType = this.getEyeType();
			
		} else if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyes && newEyeType != -1) {
			
			this.setEyeType(newEyeType);
			
		}
		
		if (this.getEyeType() <= 3 && multicolorEyes && GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyes && oldEyeType != -1) {
			
			this.setEyeType(oldEyeType);
			
		}
		
		if (!Loader.isModLoaded("theboxingdead") || !GhostlyConfig.MOBS.possessedBoxerWitherSkeletons) {
			
			this.setDead();
			
		}
		
	}
	
	@Override
	protected void entityInit() {
		
		super.entityInit();
		
		this.getDataManager().register(EYE_TYPE, Integer.valueOf(0));
		this.getDataManager().register(GLOVES_TYPE, Integer.valueOf(0));
		
	}
	
	@Override
	protected void initEntityAI () {
		
		this.tasks.addTask(1, new EntityAISwimming(this));
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
	
	public int getGlovesType() {
		
		return ((Integer)this.dataManager.get(GLOVES_TYPE)).intValue();
		
	}
	
	public void setGlovesType(int glovesType) {
		
		this.dataManager.set(GLOVES_TYPE, Integer.valueOf(glovesType));
		
	}
	
	@Override
	protected void applyEntityAttributes()
    {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(62.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
    }
	
    public static void registerFixesPossessedBoxerWitherSkeleton(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityPossessedBoxerWitherSkeleton.class);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_WITHER_SKELETON;
    }

    @Override
	protected SoundEvent getAmbientSound()
    {
        return GhostlySoundManager.POSSESSED_BOXER_WITHER_SKELETON_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return GhostlySoundManager.POSSESSED_BOXER_WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return GhostlySoundManager.POSSESSED_BOXER_WITHER_SKELETON_DEATH;
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_WITHER_SKELETON_STEP;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

        if (cause.getTrueSource() instanceof EntityCreeper)
        {
            EntityCreeper entitycreeper = (EntityCreeper)cause.getTrueSource();

            if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop())
            {
                entitycreeper.incrementDroppedSkulls();
                this.entityDropItem(new ItemStack(Items.SKULL, 1, 1), 0.0F);
            }
        }
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    @Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        
    }
    
    @Override
	public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setInteger("EyeType", this.getEyeType());
        compound.setInteger("GlovesType", this.getGlovesType());
        
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        this.setEyeType(compound.getInteger("EyeType"));
        this.setGlovesType(compound.getInteger("GlovesType"));
        
    }

	@Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    }
	
    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.setCombatTask();
        
        this.setEyeType(this.rand.nextInt(4));
        
        this.setGlovesType(rand.nextInt(2));
        
        if (GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyes && GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyesChance != 0 && this.rand.nextInt(GhostlyConfig.AESTHETICS.multicolorPossessedBoxerWitherSkeletonEyesChance) == 0) {
    		
    		this.setEyeType(this.getEyeType() + this.rand.nextInt(11));
    		
    		if (this.getEyeType() > 3) {
    			
    			multicolorEyes = true;
    			
    		}
    		
    	}
    	
    	if (!this.getHeldItemMainhand().isEmpty() || !this.getHeldItemOffhand().isEmpty()) {
    		
    		this.setEnchantmentBasedOnDifficulty(difficulty);
    		
    	}
    	
    	this.setCanPickUpLoot(false);
        
        return livingdata;
    }

    @Override
    public float getEyeHeight()
    {
        return 2.1F;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (!super.attackEntityAsMob(entityIn))
        {
            return false;
        }
        else
        {
            if (entityIn instanceof EntityLivingBase)
            {
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 1));
            }

            return true;
        }
    }

    @Override
    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityArrow entityarrow = super.getArrow(p_190726_1_);
        entityarrow.setFire(100);
        return entityarrow;
    }
	
    public static boolean dissolutionGeneratePossessedVersion() {
    	
    	return false;
    	
    }
    
    public boolean canExist() {
    	
    	return GhostlyConfig.MOBS.possessedBoxerWitherSkeletons;
    	
    }
    
}
