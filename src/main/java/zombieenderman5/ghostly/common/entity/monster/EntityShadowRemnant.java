package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

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
import net.minecraft.util.DamageSource;
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

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_VEX;
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
        public boolean shouldExecute()
        {
            return this.parentEntity.getAttackTarget() != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            this.attackTimer = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
            double d0 = 7.0D;

            if (entitylivingbase.getDistanceSq(this.parentEntity) < d0 * d0 && this.parentEntity.canEntityBeSeen(entitylivingbase))
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
        public boolean shouldExecute()
        {
            return EntityShadowRemnant.this.owner != null && EntityShadowRemnant.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityShadowRemnant.this.owner.getAttackTarget(), false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityShadowRemnant.this.setAttackTarget(EntityShadowRemnant.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

    class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityShadowRemnant vex)
        {
            super(vex);
        }

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
        public boolean shouldExecute()
        {
            return !EntityShadowRemnant.this.getMoveHelper().isUpdating() && EntityShadowRemnant.this.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
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

                if (EntityShadowRemnant.this.world.isAirBlock(blockpos1) && (EntityShadowRemnant.this.getAttackTarget() != null ? EntityShadowRemnant.this.getAttackTarget().getDistanceSqToCenter(blockpos1) < 100.0D : true))
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