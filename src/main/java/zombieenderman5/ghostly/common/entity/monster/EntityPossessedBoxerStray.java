package zombieenderman5.ghostly.common.entity.monster;

import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Nullable;

import ladysnake.dissolution.api.corporeality.IPossessable;
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
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;

public class EntityPossessedBoxerStray extends AbstractSkeleton implements IRangedAttackMob, IPossessedBoxer {
	
	private static final DataParameter<Integer> EYE_TYPE = EntityDataManager.<Integer>createKey(EntityPossessedBoxerStray.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> GLOVES_TYPE = EntityDataManager.<Integer>createKey(EntityPossessedBoxerStray.class, DataSerializers.VARINT);
	private static int oldEyeType = -1;
	private static int newEyeType = -1;
	private static boolean multicolorEyes = false;
	
	public EntityPossessedBoxerStray(World worldIn) {
		
		super(worldIn);
		
	}

	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyes && newEyeType == -1) {
			
			oldEyeType = this.getEyeType();
			this.setEyeType(this.rand.nextInt(4));
			newEyeType = this.getEyeType();
			
		} else if (this.getEyeType() > 3 && !GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyes && newEyeType != -1) {
			
			this.setEyeType(newEyeType);
			
		}
		
		if (this.getEyeType() <= 3 && multicolorEyes && GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyes && oldEyeType != -1) {
			
			this.setEyeType(oldEyeType);
			
		}
		
		if (!Loader.isModLoaded("theboxingdead") || !GhostlyConfig.MOBS.possessedBoxerStrays) {
			
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
	
	public int getGlovesType() {
		
		return ((Integer)this.dataManager.get(GLOVES_TYPE)).intValue();
		
	}
	
	@Override
	protected void applyEntityAttributes()
    {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
    }
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
		if (this.rand.nextFloat() < 0.15F * difficulty.getClampedAdditionalDifficulty())
        {
            int i = this.rand.nextInt(2);
            float f = this.world.getDifficulty() == EnumDifficulty.HARD ? 0.1F : 0.25F;

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            if (this.rand.nextFloat() < 0.095F)
            {
                ++i;
            }

            boolean flag = true;

            for (EntityEquipmentSlot entityequipmentslot : EntityEquipmentSlot.values())
            {
                if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR)
                {
                    ItemStack itemstack = this.getItemStackFromSlot(entityequipmentslot);

                    if (!flag && this.rand.nextFloat() < f)
                    {
                        break;
                    }

                    flag = false;

                    if (itemstack.isEmpty())
                    {
                        Item item = getArmorByChance(entityequipmentslot, i);

                        if (item != null)
                        {
                            this.setItemStackToSlot(entityequipmentslot, new ItemStack(item));
                        }
                    }
                }
            }
        }
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
	
	public void setEyeType(int eyeType) {
		
		this.dataManager.set(EYE_TYPE, Integer.valueOf(eyeType));
		
	}
	
	public void setGlovesType(int glovesType) {
		
		this.dataManager.set(GLOVES_TYPE, Integer.valueOf(glovesType));
		
	}
	
    public static void registerFixesPossessedBoxerStray(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityPossessedBoxerStray.class);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
        return super.getCanSpawnHere() && this.world.canSeeSky(new BlockPos(this));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	livingdata = super.onInitialSpawn(difficulty, livingdata);
    	
    	this.setEyeType(this.rand.nextInt(4));
    	
    	this.setGlovesType(rand.nextInt(2));
    	
    	if (GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyes && GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyesChance != 0 && this.rand.nextInt(GhostlyConfig.AESTHETICS.multicolorPossessedBoxerStrayEyesChance) == 0) {
    		
    		this.setEyeType(this.getEyeType() + this.rand.nextInt(11));
    		
    		if (this.getEyeType() > 3) {
    			
    			multicolorEyes = true;
    			
    		}
    		
    	}
    	
    	if (!this.getHeldItemMainhand().isEmpty() || !this.getHeldItemOffhand().isEmpty()) {
    		
    		this.setEnchantmentBasedOnDifficulty(difficulty);
    		
    	}
    	
    	this.setEquipmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
    	this.setCanPickUpLoot(false);
    	
    	if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
        {
            Calendar calendar = this.world.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
            }
        }
    	
    	return livingdata;
    }
    
    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_STRAY;
    }

    @Override
	protected SoundEvent getAmbientSound()
    {
        return GhostlySoundManager.POSSESSED_BOXER_STRAY_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return GhostlySoundManager.POSSESSED_BOXER_STRAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return GhostlySoundManager.POSSESSED_BOXER_STRAY_DEATH;
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_STRAY_STEP;
    }

    @Override
    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityArrow entityarrow = super.getArrow(p_190726_1_);

        if (entityarrow instanceof EntityTippedArrow)
        {
            ((EntityTippedArrow)entityarrow).addEffect(new PotionEffect(MobEffects.SLOWNESS, 600));
        }

        return entityarrow;
    }
	
    public static boolean dissolutionGeneratePossessedVersion() {
    	
    	return false;
    	
    }
    
    public boolean canExist() {
    	
    	return GhostlyConfig.MOBS.possessedBoxerStrays;
    	
    }
    
}
