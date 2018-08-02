package zombieenderman5.ghostly.common.entity.monster;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.ai.EntityAIFleeLight;
import zombieenderman5.ghostly.common.entity.ai.EntityAIRestrictLight;

public class EntityInfestedEnderman extends EntityEnderman {

	private static final Set<Block> CARRIABLE_BLOCKS = Sets.<Block>newIdentityHashSet();
	
	private static final DataParameter<Optional<IBlockState>> CARRIED_BLOCK = EntityDataManager.<Optional<IBlockState>>createKey(EntityInfestedEnderman.class, DataSerializers.OPTIONAL_BLOCK_STATE);
	private static final DataParameter<Boolean> SCREAMING = EntityDataManager.<Boolean>createKey(EntityInfestedEnderman.class, DataSerializers.BOOLEAN);
	
	private static final Predicate<Entity> INFEST_ENDERMAN_SELECTOR = new Predicate<Entity>()
    {
        public boolean apply(@Nullable Entity p_apply_1_)
        {
            return p_apply_1_ instanceof EntityEnderman && !(p_apply_1_ instanceof EntityInfestedEnderman) && GhostlyConfig.MOBS.infestedEndermenInfestOtherEndermen && ((EntityLivingBase)p_apply_1_).attackable();
        }
    };
	
    @Override
    protected void applyEntityAttributes() {
    	
    	super.applyEntityAttributes();
    	
    	this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140.0D);
    	this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
    	this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(13.0D);
    	
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

            for (int k = 0; k < 10; ++k)
            {
            	if (Minecraft.getMinecraft().world != null && this.world.isRemote) {
            		
            		double d2 = this.rand.nextGaussian() * 0.02D;
                    double d0 = this.rand.nextGaussian() * 0.02D;
                    double d1 = this.rand.nextGaussian() * 0.02D;
                    Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
                    Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            	}
                
            }
        }
		
	}
    
    @Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio)
	    {
	        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, entityIn, strength, xRatio, zRatio);
	        if(event.isCanceled()) return;
	        strength = event.getStrength(); xRatio = event.getRatioX(); zRatio = event.getRatioZ();
	        if (this.rand.nextDouble() >= this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue())
	        {
	            this.isAirBorne = true;
	            float f = MathHelper.sqrt(xRatio * xRatio + zRatio * zRatio);
	            this.motionX /= 4.0D;
	            this.motionZ /= 4.0D;
	            this.motionX -= xRatio / (double)f * (double)strength;
	            this.motionZ -= zRatio / (double)f * (double)strength;

	            if (this.onGround)
	            {
	                this.motionY /= 4.0D;
	                this.motionY += (double)strength;

	                if (this.motionY > 0.4000000059604645D)
	                {
	                    this.motionY = 0.4000000059604645D;
	                }
	            }
	        }
	    }
    
	public EntityInfestedEnderman(World worldIn) {
		
		super(worldIn);
		
	}
	
	public static void registerFixesInfestedEnderman(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityInfestedEnderman.class);
    }
	
	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		this.dataManager.set(SCREAMING, Boolean.valueOf(true));
		
		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.6D;
        }
		
		if (this.world.getLightBrightness(new BlockPos(this)) > (float)GhostlyConfig.MOBS.shadeDissipationLightLevel)
		{

			if (!this.world.isRemote)
			{
				this.attackEntityFrom(DamageSource.GENERIC, 1.0F);
			}

		}
		
		if (!GhostlyConfig.MOBS.infestedEndermen) this.setDead();
		
	}
	
	@Override
	public float getBlockPathWeight(BlockPos pos) {

		return this.world.getLightBrightness(pos) > (float)GhostlyConfig.MOBS.shadeDissipationLightLevel ? -10.0F : super.getBlockPathWeight(pos);
	}
	
	@Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CARRIED_BLOCK, Optional.absent());
        this.dataManager.register(SCREAMING, Boolean.valueOf(false));
    }
	
	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D, 0.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIFleeLight(this, 1.0D));
		this.tasks.addTask(1, new EntityAIRestrictLight(this));
        this.tasks.addTask(10, new EntityInfestedEnderman.AIPlaceBlock(this));
        this.tasks.addTask(11, new EntityInfestedEnderman.AITakeBlock(this));
        this.targetTasks.addTask(1, new EntityInfestedEnderman.AIFindPlayer(this));
        this.targetTasks.addTask(4, new EntityInfestedEnderman.AIInfestEndermanAttack(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		
		if (source.isExplosion()) {

			return super.attackEntityFrom(source, amount / 3);

		} else if (source.isFireDamage()) {

			return super.attackEntityFrom(source, amount / 3);

		} else if (source.isMagicDamage()) {

			return super.attackEntityFrom(source, amount / 3);

		} else if (source instanceof EntityDamageSourceIndirect) {

			return super.attackEntityFrom(source, amount / 3);

		} else {

			return super.attackEntityFrom(source, amount / 3 * 2);

		}
		
	}
	
	static
    {
        CARRIABLE_BLOCKS.add(Blocks.GRASS);
        CARRIABLE_BLOCKS.add(Blocks.DIRT);
        CARRIABLE_BLOCKS.add(Blocks.SAND);
        CARRIABLE_BLOCKS.add(Blocks.GRAVEL);
        CARRIABLE_BLOCKS.add(Blocks.YELLOW_FLOWER);
        CARRIABLE_BLOCKS.add(Blocks.RED_FLOWER);
        CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM);
        CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM);
        CARRIABLE_BLOCKS.add(Blocks.TNT);
        CARRIABLE_BLOCKS.add(Blocks.CACTUS);
        CARRIABLE_BLOCKS.add(Blocks.CLAY);
        CARRIABLE_BLOCKS.add(Blocks.PUMPKIN);
        CARRIABLE_BLOCKS.add(Blocks.MELON_BLOCK);
        CARRIABLE_BLOCKS.add(Blocks.MYCELIUM);
        CARRIABLE_BLOCKS.add(Blocks.NETHERRACK);
    }
	
	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);

        if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityEnderman && GhostlyConfig.MOBS.infestedEndermenInfestOtherEndermen && !this.world.isRemote)
        {
            if (this.world.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean())
            {
                return;
            }

            EntityEnderman entityvillager = (EntityEnderman)entityLivingIn;
            EntityInfestedEnderman entityzombievillager = new EntityInfestedEnderman(this.world);
            entityzombievillager.copyLocationAndAnglesFrom(entityvillager);
            this.world.removeEntity(entityvillager);
            entityzombievillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombievillager)), null);
            entityzombievillager.setNoAI(entityvillager.isAIDisabled());

            if (entityvillager.hasCustomName())
            {
                entityzombievillager.setCustomNameTag(entityvillager.getCustomNameTag());
                entityzombievillager.setAlwaysRenderNameTag(entityvillager.getAlwaysRenderNameTag());
            }
            
            this.world.spawnEntity(entityzombievillager);
            this.world.playSound((EntityPlayer)null, new BlockPos(this), GhostlySoundManager.INFESTED_ENDERMEN_INFEST_ENDERMAN, SoundCategory.HOSTILE, 1.0F, 1.0F);
            this.setDead();
            this.onDeath(getLastDamageSource());
        }
    }
	
	@Override
	protected SoundEvent getAmbientSound()
    {
        return this.isScreaming() ? GhostlySoundManager.INFESTED_ENDERMEN_SCREAM : GhostlySoundManager.INFESTED_ENDERMEN_AMBIENT;
    }
	
	static class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        private final EntityInfestedEnderman enderman;
        /** The player */
        private EntityPlayer player;
        private int aggroTime;
        private int teleportTime;

        public AIFindPlayer(EntityInfestedEnderman p_i45842_1_)
        {
            super(p_i45842_1_, EntityPlayer.class, false);
            this.enderman = p_i45842_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            double d0 = this.getTargetDistance();
            this.player = this.enderman.world.getNearestAttackablePlayer(this.enderman.posX, this.enderman.posY, this.enderman.posZ, d0, d0, (Function)null, new Predicate<EntityPlayer>()
            {
                public boolean apply(@Nullable EntityPlayer p_apply_1_)
                {
                    return p_apply_1_ != null;
                }
            });
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting()
        {
            this.aggroTime = 5;
            this.teleportTime = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        @Override
        public void resetTask()
        {
            this.player = null;
            super.resetTask();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        @Override
        public boolean shouldContinueExecuting()
        {
            if (this.player != null)
            {
            	this.enderman.faceEntity(this.player, 10.0F, 10.0F);
                return true;
            }
            else
            {
                return this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() ? true : super.shouldContinueExecuting();
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            if (this.player != null)
            {
                if (--this.aggroTime <= 0)
                {
                    this.targetEntity = this.player;
                    this.player = null;
                    super.startExecuting();
                }
            }
            else
            {
                if (this.targetEntity != null)
                {
                	 if (((EntityPlayer)this.targetEntity).getDistanceSq(this.enderman) < 16.0D)
                     {
                         this.enderman.teleportRandomly();
                     }

                     this.teleportTime = 0;
                     
                    if (((EntityPlayer)this.targetEntity).getDistanceSq(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.targetEntity))
                    {
                        this.teleportTime = 0;
                    }
                }

                super.updateTask();
            }
        }
    }

	static class AIPlaceBlock extends EntityAIBase
    {
        private final EntityInfestedEnderman enderman;

        public AIPlaceBlock(EntityInfestedEnderman p_i45843_1_)
        {
            this.enderman = p_i45843_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            if (this.enderman.getHeldBlockState() == null)
            {
                return false;
            }
            else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.world, this.enderman))
            {
                return false;
            }
            else
            {
                return this.enderman.getRNG().nextInt(2000) == 0;
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            Random random = this.enderman.getRNG();
            World world = this.enderman.world;
            int i = MathHelper.floor(this.enderman.posX - 1.0D + random.nextDouble() * 2.0D);
            int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 2.0D);
            int k = MathHelper.floor(this.enderman.posZ - 1.0D + random.nextDouble() * 2.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            IBlockState iblockstate = world.getBlockState(blockpos);
            IBlockState iblockstate1 = world.getBlockState(blockpos.down());
            IBlockState iblockstate2 = this.enderman.getHeldBlockState();

            if (iblockstate2 != null && this.canPlaceBlock(world, blockpos, iblockstate2.getBlock(), iblockstate, iblockstate1))
            {
                world.setBlockState(blockpos, iblockstate2, 3);
                this.enderman.setHeldBlockState((IBlockState)null);
            }
        }

        private boolean canPlaceBlock(World p_188518_1_, BlockPos p_188518_2_, Block p_188518_3_, IBlockState p_188518_4_, IBlockState p_188518_5_)
        {
            if (!p_188518_3_.canPlaceBlockAt(p_188518_1_, p_188518_2_))
            {
                return false;
            }
            else if (p_188518_4_.getMaterial() != Material.AIR)
            {
                return false;
            }
            else if (p_188518_5_.getMaterial() == Material.AIR)
            {
                return false;
            }
            else
            {
                return p_188518_5_.isFullCube();
            }
        }
    }

	static class AITakeBlock extends EntityAIBase
    {
        private final EntityInfestedEnderman enderman;

        public AITakeBlock(EntityInfestedEnderman p_i45841_1_)
        {
            this.enderman = p_i45841_1_;
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            if (this.enderman.getHeldBlockState() != null)
            {
                return false;
            }
            else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.enderman.world, this.enderman))
            {
                return false;
            }
            else
            {
                return this.enderman.getRNG().nextInt(20) == 0;
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void updateTask()
        {
            Random random = this.enderman.getRNG();
            World world = this.enderman.world;
            int i = MathHelper.floor(this.enderman.posX - 2.0D + random.nextDouble() * 4.0D);
            int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 3.0D);
            int k = MathHelper.floor(this.enderman.posZ - 2.0D + random.nextDouble() * 4.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();
            RayTraceResult raytraceresult = world.rayTraceBlocks(new Vec3d((double)((float)MathHelper.floor(this.enderman.posX) + 0.5F), (double)((float)j + 0.5F), (double)((float)MathHelper.floor(this.enderman.posZ) + 0.5F)), new Vec3d((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F)), false, true, false);
            boolean flag = raytraceresult != null && raytraceresult.getBlockPos().equals(blockpos);

            if (EntityInfestedEnderman.CARRIABLE_BLOCKS.contains(block) && flag)
            {
                this.enderman.setHeldBlockState(iblockstate);
                world.setBlockToAir(blockpos);
            }
        }
    }
	
	static class AIInfestEndermanAttack extends EntityAINearestAttackableTarget<EntityLivingBase>
    {
        public AIInfestEndermanAttack(EntityInfestedEnderman shade)
        {
            super(shade, EntityLivingBase.class, 0, true, true, EntityInfestedEnderman.INFEST_ENDERMAN_SELECTOR);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        @Override
        public boolean shouldExecute()
        {
            return GhostlyConfig.MOBS.infestedEndermenInfestOtherEndermen && super.shouldExecute();
        }
    }
	
}
