package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityTinyShadowOrb;

public class EntityShadowRemnant extends EntityMob
{
    protected static final DataParameter<Float> SIZE = EntityDataManager.<Float>createKey(EntityShadowRemnant.class, DataSerializers.FLOAT);
    @Nullable
    private EntityLiving owner;
    @Nullable
    private BlockPos boundOrigin;
    private boolean limitedLifespan;
    private int limitedLifeTicks;

    public EntityShadowRemnant(World worldIn)
    {
        super(worldIn);
        this.isImmuneToFire = true;
        this.moveHelper = new EntityShadowRemnant.AIMoveControl(this);
        this.setSize(0.4F, 0.4F);
        this.experienceValue = 3;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();
        this.setNoGravity(true);

        if (this.limitedLifespan && --this.limitedLifeTicks <= 0)
        {
            this.limitedLifeTicks = 20;
            this.attackEntityFrom(DamageSource.STARVE, 1.0F);
        }
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(4, new EntityShadowRemnant.AIMoveRandom());
        this.tasks.addTask(5, new EntityShadowRemnant.AIShadowOrbAttack(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[] {}));
        this.targetTasks.addTask(2, new EntityShadowRemnant.AICopyOwnerTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SIZE, Float.valueOf(1.0F));
    }

    public static void registerFixesShadowRemnant(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityShadowRemnant.class);
    }
    
    @Override
	public void fall(float distance, float damageMultiplier) {}
    
    @Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio)
    {
        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, entityIn, strength, xRatio, zRatio);
        if(event.isCanceled()) return;
        strength = event.getStrength() / 5; xRatio = event.getRatioX(); zRatio = event.getRatioZ();
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

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        
        if (compound.hasUniqueId("Owner"))
        {
        	this.owner = (EntityLiving) this.getServer().getEntityFromUuid(compound.getUniqueId("Owner"));
        }

        if (compound.hasKey("BoundX"))
        {
            this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"), compound.getInteger("BoundZ"));
        }

        if (compound.hasKey("LifeTicks"))
        {
            this.setLimitedLife(compound.getInteger("LifeTicks"));
        }
        
        if (compound.hasKey("Size", 99))
        {
        	this.setSize(compound.getFloat("Size"));
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        
        if (this.owner != null)
        {
        	compound.setUniqueId("Owner", this.owner.getUniqueID());
        }

        if (this.boundOrigin != null)
        {
            compound.setInteger("BoundX", this.boundOrigin.getX());
            compound.setInteger("BoundY", this.boundOrigin.getY());
            compound.setInteger("BoundZ", this.boundOrigin.getZ());
        }

        if (this.limitedLifespan)
        {
            compound.setInteger("LifeTicks", this.limitedLifeTicks);
        }
        
        compound.setFloat("Size", this.getSize());
    }
    
    @Override
    public boolean canBreatheUnderwater()
    {
        return true;
    }

    @Nullable
    public EntityLiving getOwner()
    {
        return this.owner;
    }

    @Nullable
    public BlockPos getBoundOrigin()
    {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOriginIn)
    {
        this.boundOrigin = boundOriginIn;
    }

    public float getSize()
    {
        return ((Float)this.dataManager.get(SIZE)).floatValue();
    }

    private void setSize(float value)
    {
        this.dataManager.set(SIZE, Float.valueOf(value));
    }

    public void setOwner(EntityLiving ownerIn)
    {
        this.owner = ownerIn;
    }

    public void setLimitedLife(int limitedLifeTicksIn)
    {
        this.limitedLifespan = true;
        this.limitedLifeTicks = limitedLifeTicksIn;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_VEX_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_VEX_HURT;
    }
    
    protected SoundEvent getStepSound()
    {
		return null;
	}

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }
    
    @Override
	protected void playStepSound(BlockPos pos, Block blockIn)
    {
		this.playSound(this.getStepSound(), 0.15F, 1.0F);

	}
    
    @Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn) {

		return false;

	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {

		return this.world.getLightBrightness(pos) >= (float)GhostlyConfig.MOBS.shadowRemnantDissipationLightLevel ? -10.0F : super.getBlockPathWeight(pos);
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

		} else if (source instanceof EntityDamageSourceIndirect && !(source.getImmediateSource() instanceof EntityCorporealityArrow)) {

			return false;

		} else if (source.getTrueSource() != null && sourceLiving != null && (sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.swordOfCorporeality || sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.axeOfCorporeality || sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.pickaxeOfCorporeality || sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.shovelOfCorporeality || sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.hoeOfCorporeality || sourceLiving.getHeldItemMainhand().getItem() == GhostlyItemManager.bowOfCorporeality)) {

			return super.attackEntityFrom(source, amount);

		} else {
			
			return super.attackEntityFrom(source, amount / 5);
			
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
    
    static class AIShadowOrbAttack extends EntityAIBase
    {
        private final EntityShadowRemnant parentEntity;
        public int attackTimer;

        public AIShadowOrbAttack(EntityShadowRemnant shadowRemnant)
        {
            this.parentEntity = shadowRemnant;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return this.parentEntity.getAttackTarget() != null;
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
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
            double d0 = 7.0D;

            if (entitylivingbase.getDistanceSq(this.parentEntity) <= d0 * d0 && this.parentEntity.canEntityBeSeen(entitylivingbase))
            {
                World world = this.parentEntity.world;
                --this.attackTimer;

                if (this.attackTimer <= 0)
                {
                    double d1 = 4.0D;
                    Vec3d vec3d = this.parentEntity.getLook(1.0F);
                    double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * (this.parentEntity.width / 2.0F + 0.1F));
                    double d3 = entitylivingbase.posY - (this.parentEntity.posY + vec3d.y * (this.parentEntity.height / 2.0F + 0.1F));
                    double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * (this.parentEntity.width / 2.0F + 0.1F));
                    this.parentEntity.playSound(SoundEvents.ENTITY_BLAZE_SHOOT, 1.0F, (this.parentEntity.getRNG().nextFloat() - this.parentEntity.getRNG().nextFloat()) * 0.2F + 1.4F);
                    EntityTinyShadowOrb tinyshadoworb = new EntityTinyShadowOrb(world, this.parentEntity, d2, d3, d4);
                    tinyshadoworb.posX = this.parentEntity.posX + vec3d.x * (this.parentEntity.width / 2.0F + 0.1F);
                    tinyshadoworb.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5D;
                    tinyshadoworb.posZ = this.parentEntity.posZ + vec3d.z * (this.parentEntity.width / 2.0F + 0.1F);
                    world.spawnEntity(tinyshadoworb);
                    this.attackTimer = 60;
                }
            }
        }
    }

    class AICopyOwnerTarget extends EntityAITarget
    {
        public AICopyOwnerTarget(EntityCreature creature)
        {
            super(creature, false);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return EntityShadowRemnant.this.owner != null && EntityShadowRemnant.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityShadowRemnant.this.owner.getAttackTarget(), false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            EntityShadowRemnant.this.setAttackTarget(EntityShadowRemnant.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

    class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityShadowRemnant shadowRemnant)
        {
            super(shadowRemnant);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - EntityShadowRemnant.this.posX;
                double d1 = this.posY - EntityShadowRemnant.this.posY;
                double d2 = this.posZ - EntityShadowRemnant.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < EntityShadowRemnant.this.getEntityBoundingBox().getAverageEdgeLength())
                {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityShadowRemnant.this.motionX *= 0.5D;
                    EntityShadowRemnant.this.motionY *= 0.5D;
                    EntityShadowRemnant.this.motionZ *= 0.5D;
                }
                else
                {
                    EntityShadowRemnant.this.motionX += d0 / d3 * 0.05D * this.speed;
                    EntityShadowRemnant.this.motionY += d1 / d3 * 0.05D * this.speed;
                    EntityShadowRemnant.this.motionZ += d2 / d3 * 0.05D * this.speed;

                    if (EntityShadowRemnant.this.getAttackTarget() == null)
                    {
                        EntityShadowRemnant.this.rotationYaw = -((float)MathHelper.atan2(EntityShadowRemnant.this.motionX, EntityShadowRemnant.this.motionZ)) * (180F / (float)Math.PI);
                        EntityShadowRemnant.this.renderYawOffset = EntityShadowRemnant.this.rotationYaw;
                    }
                    else
                    {
                        double d4 = EntityShadowRemnant.this.getAttackTarget().posX - EntityShadowRemnant.this.posX;
                        double d5 = EntityShadowRemnant.this.getAttackTarget().posZ - EntityShadowRemnant.this.posZ;
                        EntityShadowRemnant.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        EntityShadowRemnant.this.renderYawOffset = EntityShadowRemnant.this.rotationYaw;
                    }
                }
            }
        }
    }

    class AIMoveRandom extends EntityAIBase
    {
        public AIMoveRandom()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return !EntityShadowRemnant.this.getMoveHelper().isUpdating() && EntityShadowRemnant.this.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            BlockPos blockpos = EntityShadowRemnant.this.getBoundOrigin();

            if (blockpos == null)
            {
                blockpos = new BlockPos(EntityShadowRemnant.this);
            }

            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos1 = blockpos.add(EntityShadowRemnant.this.rand.nextInt(15) - 7, EntityShadowRemnant.this.rand.nextInt(11) - 5, EntityShadowRemnant.this.rand.nextInt(15) - 7);
                
                if (EntityShadowRemnant.this.getAttackTarget() != null && EntityShadowRemnant.this.getAttackTarget().getDistanceSqToCenter(blockpos1) > 49.0D)
                {
                	blockpos1 = new BlockPos(EntityShadowRemnant.this.getAttackTarget().posX + (EntityShadowRemnant.this.rand.nextInt(7) - 3), EntityShadowRemnant.this.getAttackTarget().posY + (EntityShadowRemnant.this.rand.nextInt(9) - 4), EntityShadowRemnant.this.getAttackTarget().posZ + (EntityShadowRemnant.this.rand.nextInt(7) - 3));
                }

                if (EntityShadowRemnant.this.world.isAirBlock(blockpos1))
                {
                    EntityShadowRemnant.this.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);

                    if (EntityShadowRemnant.this.getAttackTarget() == null)
                    {
                        EntityShadowRemnant.this.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }

                    break;
                }
            }
        }
    }
}