package zombieenderman5.ghostly.common.entity.monster;

import java.util.List;
import java.util.UUID;

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
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.Item;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityMutatedHorse extends EntityMob implements IInventoryChangedListener {

	private static final DataParameter<Byte> STATUS = EntityDataManager.<Byte>createKey(AbstractHorse.class, DataSerializers.BYTE);
	private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
	private static final UUID ARMOR_TOUGHNESS_MODIFIER_UUID = UUID.fromString("50BBC6D1-BEEC-4675-97F8-931C5C9A5494");
	private static final DataParameter<Integer> MUTATED_HORSE_VARIANT = EntityDataManager.<Integer>createKey(EntityMutatedHorse.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MUTATED_HORSE_ARMOR = EntityDataManager.<Integer>createKey(EntityMutatedHorse.class, DataSerializers.VARINT);
    private static final DataParameter<ItemStack> MUTATED_HORSE_ARMOR_STACK = EntityDataManager.<ItemStack>createKey(EntityMutatedHorse.class, DataSerializers.ITEM_STACK);
	private static final String[] MUTATED_HORSE_TEXTURES = new String[] {"ghostly:textures/entity/mutated_horse/mutated_horse_white.png", "ghostly:textures/entity/mutated_horse/mutated_horse_creamy.png", "ghostly:textures/entity/mutated_horse/mutated_horse_chestnut.png", "ghostly:textures/entity/mutated_horse/mutated_horse_brown.png", "ghostly:textures/entity/mutated_horse/mutated_horse_black.png", "ghostly:textures/entity/mutated_horse/mutated_horse_gray.png", "ghostly:textures/entity/mutated_horse/mutated_horse_darkbrown.png"};
    private static final String[] MUTATED_HORSE_TEXTURES_ABBR = new String[] {"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] MUTATED_HORSE_MARKING_TEXTURES = new String[] {null, "ghostly:textures/entity/mutated_horse/mutated_horse_markings_white.png", "ghostly:textures/entity/mutated_horse/mutated_horse_markings_whitefield.png", "ghostly:textures/entity/mutated_horse/mutated_horse_markings_whitedots.png", "ghostly:textures/entity/mutated_horse/mutated_horse_markings_blackdots.png"};
    private static final String[] MUTATED_HORSE_MARKING_TEXTURES_ABBR = new String[] {"", "wo_", "wmo", "wdo", "bdo"};
    protected ContainerHorseChest mutatedHorseChest;
    private String texturePrefix;
    private final String[] mutatedHorseTexturesArray = new String[3];
    private float mouthOpenness;
    private float prevMouthOpenness;
    private int openMouthCounter;
    public int tailCounter;
    private IItemHandler itemHandler = null;
    private boolean pickedUpArmor;
	
	public EntityMutatedHorse(World worldIn) {
		
		super(worldIn);
		
		this.setSize(0.9F, 2.9F);
		this.initMutatedHorseChest();
		this.pickedUpArmor = false;
		
	}

	@Override
	public float getEyeHeight() {
		
		return this.height - 0.25F;
		
	}
	
	protected void initMutatedHorseChest() {
        ContainerHorseChest containerhorsechest = this.mutatedHorseChest;
        this.mutatedHorseChest = new ContainerHorseChest("MutatedHorseChest", 1);
        this.mutatedHorseChest.setCustomName(this.getName());

        if (containerhorsechest != null) {
            containerhorsechest.removeInventoryChangeListener(this);
            int i = Math.min(containerhorsechest.getSizeInventory(), this.mutatedHorseChest.getSizeInventory());

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = containerhorsechest.getStackInSlot(j);

                if (!itemstack.isEmpty()) {
                    this.mutatedHorseChest.setInventorySlotContents(j, itemstack.copy());
                }
            }
        }

        this.mutatedHorseChest.addInventoryChangeListener(this);
        this.updateMutatedHorseSlots();
        this.itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this.mutatedHorseChest);
    }
	
	protected void updateMutatedHorseSlots() {
        this.setMutatedHorseArmorStack(this.mutatedHorseChest.getStackInSlot(0));
    }
	
	@Override
	public void onInventoryChanged(IInventory invBasic) {
        HorseArmorType horsearmortype = this.getMutatedHorseArmorType();
        this.updateMutatedHorseSlots();
        HorseArmorType horsearmortype1 = this.getMutatedHorseArmorType();

        if (this.ticksExisted > 20 && horsearmortype != horsearmortype1 && horsearmortype1 != HorseArmorType.NONE) {
            this.playSound(SoundEvents.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
        }
    }
	
	public void setMutatedHorseArmorStack(ItemStack itemStackIn) {
        HorseArmorType horsearmortype = HorseArmorType.getByItemStack(itemStackIn);
        this.dataManager.set(MUTATED_HORSE_ARMOR, Integer.valueOf(horsearmortype.getOrdinal()));
        this.dataManager.set(MUTATED_HORSE_ARMOR_STACK, itemStackIn);
        this.resetTexturePrefix();

        if (!this.world.isRemote) {
            this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
            int armorAmount = 0;
            int armorToughnessAmount = 0;
            if (horsearmortype == HorseArmorType.IRON) {
            	armorAmount = 15;
            } else if (horsearmortype == HorseArmorType.GOLD) {
            	armorAmount = 11;
            } else if (horsearmortype == HorseArmorType.DIAMOND) {
            	armorAmount = 20;
            	armorToughnessAmount = 8;
            }

            if (armorAmount != 0) {
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "Mutated horse armor bonus", (double)armorAmount, 0)).setSaved(false));
            }
            if (armorToughnessAmount != 0) {
            	this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).removeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID);
                this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).applyModifier((new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER_UUID, "Mutated horse armor toughness bonus", (double)armorToughnessAmount, 0)).setSaved(false));
            }
        }
    }

    public HorseArmorType getMutatedHorseArmorType() {
        HorseArmorType armor = HorseArmorType.getByItemStack(this.dataManager.get(MUTATED_HORSE_ARMOR_STACK)); //First check the Forge armor DataParameter
        if (armor == HorseArmorType.NONE) armor = HorseArmorType.getByOrdinal(this.dataManager.get(MUTATED_HORSE_ARMOR)); //If the Forge armor DataParameter returns NONE, fallback to the vanilla armor DataParameter. This is necessary to prevent issues with Forge clients connected to vanilla servers.
        return armor;
    }
	
	@Override
	protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATUS, Byte.valueOf((byte)0));
        this.dataManager.register(MUTATED_HORSE_VARIANT, Integer.valueOf(0));
        this.dataManager.register(MUTATED_HORSE_ARMOR, Integer.valueOf(HorseArmorType.NONE.getOrdinal()));
        this.dataManager.register(MUTATED_HORSE_ARMOR_STACK, ItemStack.EMPTY);
    }

    public static void registerFixesMutatedHorse(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityMutatedHorse.class);
        fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(EntityMutatedHorse.class, new String[] {"ArmorItem"}));
    }
	
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getMutatedHorseVariant());
        if (!this.mutatedHorseChest.getStackInSlot(0).isEmpty()) {
            compound.setTag("ArmorItem", this.mutatedHorseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setMutatedHorseVariant(compound.getInteger("Variant"));
        if (compound.hasKey("ArmorItem", 10)) {
            ItemStack itemstack = new ItemStack(compound.getCompoundTag("ArmorItem"));

            if (!itemstack.isEmpty() && isArmor(itemstack)) {
                this.mutatedHorseChest.setInventorySlotContents(0, itemstack);
            }
        }

        this.updateMutatedHorseSlots();
    }

    public void setMutatedHorseVariant(int variant) {
        this.dataManager.set(MUTATED_HORSE_VARIANT, Integer.valueOf(variant));
        this.resetTexturePrefix();
    }

    public int getMutatedHorseVariant() {
        return ((Integer)this.dataManager.get(MUTATED_HORSE_VARIANT)).intValue();
    }

    private void resetTexturePrefix() {
        this.texturePrefix = null;
    }
    
    @SideOnly(Side.CLIENT)
    private void setMutatedHorseTexturePaths() {
        int i = this.getMutatedHorseVariant();
        int j = (i & 255) % 7;
        int k = ((i & 65280) >> 8) % 5;
        ItemStack armorStack = this.dataManager.get(MUTATED_HORSE_ARMOR_STACK);
        String texture = !armorStack.isEmpty() ? armorStack.getItem().getHorseArmorTexture(this, armorStack) : HorseArmorType.getByOrdinal(this.dataManager.get(MUTATED_HORSE_ARMOR)).getTextureName(); //If armorStack is empty, the server is vanilla so the texture should be determined the vanilla way
        this.mutatedHorseTexturesArray[0] = MUTATED_HORSE_TEXTURES[j];
        this.mutatedHorseTexturesArray[1] = MUTATED_HORSE_MARKING_TEXTURES[k];
        this.mutatedHorseTexturesArray[2] = texture;
        this.texturePrefix = "mutated_horse/" + MUTATED_HORSE_TEXTURES_ABBR[j] + MUTATED_HORSE_MARKING_TEXTURES_ABBR[k];
    }

    @SideOnly(Side.CLIENT)
    public String getMutatedHorseTexture() {
        if (this.texturePrefix == null) {
            this.setMutatedHorseTexturePaths();
        }

        return this.texturePrefix;
    }

    @SideOnly(Side.CLIENT)
    public String[] getVariantTexturePaths() {
        if (this.texturePrefix == null) {
            this.setMutatedHorseTexturePaths();
        }

        return this.mutatedHorseTexturesArray;
    }
    
    public boolean isArmor(ItemStack stack) {
        return HorseArmorType.isHorseArmor(stack);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
            this.openMouthCounter = 0;
            this.setMutatedHorseWatchableBoolean(64, false);
        }
        
        if (this.tailCounter > 0 && ++this.tailCounter > 8) {
            this.tailCounter = 0;
        }
        
        if (this.world.isRemote && this.dataManager.isDirty()) {
            this.dataManager.setClean();
            this.resetTexturePrefix();
        }
        
        ItemStack armor = this.mutatedHorseChest.getStackInSlot(0);
        if (isArmor(armor)) armor.getItem().onHorseArmorTick(world, this, armor);
        
        this.prevMouthOpenness = this.mouthOpenness;

        if (this.getMutatedHorseWatchableBoolean(64)) {
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
    
    private void openMutatedHorseMouth() {
        if (!this.world.isRemote) {
            this.openMouthCounter = 1;
            this.setMutatedHorseWatchableBoolean(64, true);
        }
    }
    
    protected boolean getMutatedHorseWatchableBoolean(int i1) {
        return (((Byte)this.dataManager.get(STATUS)).byteValue() & i1) != 0;
    }
    
    protected void setMutatedHorseWatchableBoolean(int i1, boolean b1) {
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
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		if (this.rand.nextInt(200) == 0) {
            this.moveTail();
        }
		
		if (!GhostlyConfig.MOBS.mutatedHorses) {
			this.setDead();
		}
		
		List<Entity> intersectionList = this.world.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(new Vec3d(this.getEntityBoundingBox().minX, this.getEntityBoundingBox().minY, this.getEntityBoundingBox().minZ), new Vec3d(this.getEntityBoundingBox().maxX, this.getEntityBoundingBox().maxY, this.getEntityBoundingBox().maxZ)));
		EntityItem intersectionItemEntity = null;
		EntityItem oldIntersectionItemEntity = null;
		
		if (!intersectionList.isEmpty()) {
			for (int i = 0; i < intersectionList.size(); i++) {
				if (intersectionList.get(i) instanceof EntityItem) {
					intersectionItemEntity = (EntityItem) intersectionList.get(i);
					if (intersectionItemEntity != null && this.dataManager.get(MUTATED_HORSE_ARMOR_STACK) == ItemStack.EMPTY) {
						if (intersectionItemEntity.getItem().getItem() == Items.IRON_HORSE_ARMOR) {
							this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
							this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
							intersectionList.get(i).setDead();
							this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 1.0F);
							intersectionItemEntity = null;
							this.pickedUpArmor = true;
						} else if (intersectionItemEntity.getItem().getItem() == Items.GOLDEN_HORSE_ARMOR) {
							this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
							this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
							intersectionList.get(i).setDead();
							this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0F, 1.0F);
							intersectionItemEntity = null;
							this.pickedUpArmor = true;
						} else if (intersectionItemEntity.getItem().getItem() == Items.DIAMOND_HORSE_ARMOR) {
							this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
							this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
							intersectionList.get(i).setDead();
							this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.0F);
							intersectionItemEntity = null;
							this.pickedUpArmor = true;
						}
					}
					if (intersectionItemEntity != null && this.dataManager.get(MUTATED_HORSE_ARMOR_STACK) != ItemStack.EMPTY) {
						if (this.dataManager.get(MUTATED_HORSE_ARMOR_STACK).getItem() == Items.GOLDEN_HORSE_ARMOR) {
							if (intersectionItemEntity.getItem().getItem() == Items.IRON_HORSE_ARMOR) {
								oldIntersectionItemEntity = new EntityItem(this.world, this.posX, this.posY, this.posZ);
								oldIntersectionItemEntity.setItem(this.dataManager.get(MUTATED_HORSE_ARMOR_STACK));
								this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
								this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
								intersectionList.get(i).setDead();
								this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 1.0F);
								intersectionItemEntity = null;
								this.world.spawnEntity(oldIntersectionItemEntity);
								oldIntersectionItemEntity = null;
								this.pickedUpArmor = true;
							} else if (intersectionItemEntity.getItem().getItem() == Items.DIAMOND_HORSE_ARMOR) {
								oldIntersectionItemEntity = new EntityItem(this.world, this.posX, this.posY, this.posZ);
								oldIntersectionItemEntity.setItem(this.dataManager.get(MUTATED_HORSE_ARMOR_STACK));
								this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
								this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
								intersectionList.get(i).setDead();
								this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.0F);
								intersectionItemEntity = null;
								this.world.spawnEntity(oldIntersectionItemEntity);
								oldIntersectionItemEntity = null;
								this.pickedUpArmor = true;
							}
						} else if (this.dataManager.get(MUTATED_HORSE_ARMOR_STACK).getItem() == Items.IRON_HORSE_ARMOR) {
							if (intersectionItemEntity.getItem().getItem() == Items.DIAMOND_HORSE_ARMOR) {
								oldIntersectionItemEntity = new EntityItem(this.world, this.posX, this.posY, this.posZ);
								oldIntersectionItemEntity.setItem(this.dataManager.get(MUTATED_HORSE_ARMOR_STACK));
								this.setMutatedHorseArmorStack(intersectionItemEntity.getItem());
								this.mutatedHorseChest.setInventorySlotContents(0, intersectionItemEntity.getItem());
								intersectionList.get(i).setDead();
								this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 1.0F);
								intersectionItemEntity = null;
								this.world.spawnEntity(oldIntersectionItemEntity);
								oldIntersectionItemEntity = null;
								this.pickedUpArmor = true;
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean canDespawn() {
		if (this.dataManager.get(MUTATED_HORSE_ARMOR_STACK) != ItemStack.EMPTY && this.pickedUpArmor) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
		
		if (!this.world.isRemote && this.dataManager.get(MUTATED_HORSE_ARMOR_STACK) != ItemStack.EMPTY && this.pickedUpArmor) {
			EntityItem armorItem = new EntityItem(this.world, this.posX, this.posY, this.posZ);
			armorItem.setItem(this.dataManager.get(MUTATED_HORSE_ARMOR_STACK));
			this.world.spawnEntity(armorItem);
		}
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		this.openMutatedHorseMouth();
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		this.openMutatedHorseMouth();
        return SoundEvents.ENTITY_HORSE_HURT;
    }

	@Override
    protected SoundEvent getDeathSound() {
		this.openMutatedHorseMouth();
        return SoundEvents.ENTITY_HORSE_DEATH;
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
        return LootTableList.ENTITIES_HORSE;
    }
	
	@Override
	public int getTalkInterval() {
        return 400;
    }
	
	private void moveTail() {
        this.tailCounter = 1;
    }
	
	@Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int i;

        if (livingdata instanceof EntityMutatedHorse.GroupData) {
            i = ((EntityMutatedHorse.GroupData)livingdata).variant;
        } else {
            i = this.rand.nextInt(7);
            livingdata = new EntityMutatedHorse.GroupData(i);
        }

        this.setMutatedHorseVariant(i | this.rand.nextInt(5) << 8);
        
        if (this.rand.nextFloat() <= GhostlyConfig.MOBS.mutatedHorseArmorSpawnIronChance && GhostlyConfig.MOBS.mutatedHorseArmorSpawnIronChance > -0.1D) {
        	this.setMutatedHorseArmorStack(new ItemStack(Items.IRON_HORSE_ARMOR, 1));
			this.mutatedHorseChest.setInventorySlotContents(0, new ItemStack(Items.IRON_HORSE_ARMOR, 1));
        } else if (this.rand.nextFloat() <= GhostlyConfig.MOBS.mutatedHorseArmorSpawnGoldChance && GhostlyConfig.MOBS.mutatedHorseArmorSpawnGoldChance > -0.1D) {
        	this.setMutatedHorseArmorStack(new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
			this.mutatedHorseChest.setInventorySlotContents(0, new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
        } else if (this.rand.nextFloat() <= GhostlyConfig.MOBS.mutatedHorseArmorSpawnDiamondChance && GhostlyConfig.MOBS.mutatedHorseArmorSpawnDiamondChance > -0.1D) {
        	this.setMutatedHorseArmorStack(new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
			this.mutatedHorseChest.setInventorySlotContents(0, new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
        }
        return livingdata;
    }

    public static class GroupData implements IEntityLivingData {
    	public int variant;

        public GroupData(int variantIn) {
        	this.variant = variantIn;
        }
    }
	
	class AIFleeAtSetHP extends EntityAIBase {
		private final Predicate<Entity> canBeSeenSelector;
		/** The entity we are attached to */
		protected EntityMutatedHorse mutatedHorse;
		private final double farSpeed;
		private final double nearSpeed;
		protected EntityLivingBase closestLivingEntity;
		private final float avoidDistance;
		/** The PathEntity of our entity */
		private Path path;
		/** The PathNavigate of our entity */
		private final PathNavigate navigation;
		private final Predicate <? super EntityLivingBase> avoidTargetSelector;

		public AIFleeAtSetHP(EntityMutatedHorse mutatedHorseIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this(mutatedHorseIn, Predicates.alwaysTrue(), avoidDistanceIn, farSpeedIn, nearSpeedIn);
		}

		public AIFleeAtSetHP(EntityMutatedHorse mutatedHorseIn, Predicate <? super EntityLivingBase> avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
			this.canBeSeenSelector = new Predicate<Entity>() {
				@Override
				public boolean apply(@Nullable Entity p_apply_1_) {
					return p_apply_1_.isEntityAlive() && AIFleeAtSetHP.this.mutatedHorse.getEntitySenses().canSee(p_apply_1_) && !AIFleeAtSetHP.this.mutatedHorse.isOnSameTeam(p_apply_1_);
				}
		    };
		    this.mutatedHorse = mutatedHorseIn;
		    this.avoidTargetSelector = avoidTargetSelectorIn;
		    this.avoidDistance = avoidDistanceIn;
		    this.farSpeed = farSpeedIn;
		    this.nearSpeed = nearSpeedIn;
		    this.navigation = mutatedHorseIn.getNavigator();
		    this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		@Override
		public boolean shouldExecute() {

		    if (this.mutatedHorse.getAttackTarget() == null || this.mutatedHorse.getHealth() > (this.mutatedHorse.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedHorseFleeHealthPercentage) || (this.mutatedHorse.getAttackTarget() != null && this.mutatedHorse.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") != null && this.mutatedHorse.getAttackTarget().getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue() < 1.0D) || (this.mutatedHorse.getAttackTarget() != null && this.mutatedHorse.getAttackTarget().getAttributeMap().getAttributeInstanceByName("generic.attackDamage") == null)) {
		        return false;
		    } else {
		        this.closestLivingEntity = this.mutatedHorse.getAttackTarget();
		        Vec3d vec3d = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.mutatedHorse, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

		        if (vec3d == null) {
		            return false;
		        } else if (this.closestLivingEntity.getDistanceSq(vec3d.x, vec3d.y, vec3d.z) < this.closestLivingEntity.getDistanceSq(this.mutatedHorse)) {
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
		    return !this.navigation.noPath() && this.mutatedHorse.getHealth() <= (this.mutatedHorse.getMaxHealth() * (float) GhostlyConfig.MOBS.mutatedHorseFleeHealthPercentage);
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
		    if (this.mutatedHorse.getDistanceSq(this.closestLivingEntity) < 49.0D) {
		        this.mutatedHorse.getNavigator().setSpeed(this.nearSpeed);
		    } else {
		        this.mutatedHorse.getNavigator().setSpeed(this.farSpeed);
		    }
		}
		
	}
}
