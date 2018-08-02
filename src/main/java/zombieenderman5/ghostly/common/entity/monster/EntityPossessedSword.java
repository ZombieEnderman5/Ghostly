package zombieenderman5.ghostly.common.entity.monster;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;

public class EntityPossessedSword extends EntityMob {

	private static boolean isSwooping = false;

	public EntityPossessedSword(World worldIn) {

		super(worldIn);

		this.setSize(0.2F, 0.6F);

		this.moveHelper = new EntityPossessedSword.AIMoveControl(this);
		
		this.experienceValue = 1;
		
		this.setPathPriority(PathNodeType.BLOCKED, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_CACTUS, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_OTHER, -1.0F);

	}

	@Override
	public void onLivingUpdate() {

		if (!GhostlyConfig.MOBS.possessedSwords) this.setDead();
		
		super.onLivingUpdate();

	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
	{
	}

	@Override
	public void travel(float strafe, float vertical, float forward)
	{
		if (this.isInWater())
		{
			this.moveRelative(strafe, vertical, forward, 0.02F);
			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.800000011920929D;
			this.motionZ *= 0.800000011920929D;
		}
		else if (this.isInLava())
		{
			this.moveRelative(strafe, vertical, forward, 0.02F);
			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}
		else
		{
			float f = 0.91F;

			if (this.onGround)
			{
				BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
				IBlockState underState = this.world.getBlockState(underPos);
				f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
			}

			float f1 = 0.16277136F / (f * f * f);
			this.moveRelative(strafe, vertical, forward, this.onGround ? 0.1F * f1 : 0.02F);
			f = 0.91F;

			if (this.onGround)
			{
				BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
				IBlockState underState = this.world.getBlockState(underPos);
				f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
			}

			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			this.motionX *= (double)f;
			this.motionY *= (double)f;
			this.motionZ *= (double)f;
		}

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d1 = this.posX - this.prevPosX;
		double d0 = this.posZ - this.prevPosZ;
		float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

		if (f2 > 1.0F)
		{
			f2 = 1.0F;
		}

		this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	/**
	 * Returns true if this entity should move as if it were on a ladder (either because it's actually on a ladder, or
	 * for AI reasons)
	 */
	 @Override
	 public boolean isOnLadder()
	{
		 return false;
	}

	@Override
	protected int decreaseAirSupply(int air) {

		return air;

	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(70.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);

	}

	@Override
	protected void initEntityAI() {

		super.initEntityAI();

		this.tasks.addTask(4, new EntityPossessedSword.AISwoopAtEntity(this));
		this.tasks.addTask(8, new EntityPossessedSword.AIMoveRandom());
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));

	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
		{
			this.setDead();
		}

	}

	public static void registerFixesPossessedSword(DataFixer fixer)
	{
		EntityLiving.registerFixesMob(fixer, EntityPossessedSword.class);
	}

	public boolean isSwooping() {

		return EntityPossessedSword.isSwooping;

	}

	public void setSwooping(boolean isSwooping) {

		EntityPossessedSword.isSwooping = isSwooping;

	}

	@Override
	protected SoundEvent getAmbientSound() {

		return null;

	}

	protected SoundEvent getHurtSound() {

		return null;

	}

	@Override
	protected SoundEvent getDeathSound() {

		return SoundEvents.ITEM_SHIELD_BREAK;

	}

	@Override
	protected ResourceLocation getLootTable() {

		return null;

	}

	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
    	livingdata = super.onInitialSpawn(difficulty, livingdata);
    	
    	this.setCanPickUpLoot(false);
    	this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
		this.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
    	
    	return livingdata;
    }
	
	@Override
	protected void onDeathUpdate()
	{
		++this.deathTime;

		if (this.deathTime >= 0)
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
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1, 276);
			}
		}
	}

	static class AISwoopAtEntity extends EntityAIBase {

		private static EntityPossessedSword possessedSword;

		public AISwoopAtEntity(EntityPossessedSword possessedSwordIn)
		{
			AISwoopAtEntity.possessedSword = possessedSwordIn;
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute()
		{
			return AISwoopAtEntity.possessedSword.getMoveHelper().isUpdating() && AISwoopAtEntity.possessedSword.isSwooping() && AISwoopAtEntity.possessedSword.getAttackTarget() != null && AISwoopAtEntity.possessedSword.getAttackTarget().isEntityAlive();
		}

		@Override
		public void startExecuting() {

			EntityLivingBase entitylivingbase = AISwoopAtEntity.possessedSword.getAttackTarget();
			Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
			AISwoopAtEntity.possessedSword.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
			AISwoopAtEntity.possessedSword.setSwooping(true);
			AISwoopAtEntity.possessedSword.playSound(GhostlySoundManager.POSSESSED_SWORD_SWOOP, 1.0F, 1.0F);

		}

		@Override
		public void resetTask()
		{
			AISwoopAtEntity.possessedSword.setSwooping(false);
		}

		@Override
		public void updateTask() {

			EntityLivingBase entitylivingbase = AISwoopAtEntity.possessedSword.getAttackTarget();

			if (AISwoopAtEntity.possessedSword.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox())) {

				AISwoopAtEntity.possessedSword.attackEntityAsMob(entitylivingbase);
				AISwoopAtEntity.possessedSword.setSwooping(false);

			} else {

				double d0 = AISwoopAtEntity.possessedSword.getDistanceSq(entitylivingbase);

				if (d0 < 9.0D) {

					Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
					AISwoopAtEntity.possessedSword.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);

				}

			}

		}

	}

	class AIMoveControl extends EntityMoveHelper
	{
		public AIMoveControl(EntityPossessedSword possessedSword)
		{
			super(possessedSword);
		}

		@Override
		public void onUpdateMoveHelper()
		{
			if (this.action == EntityMoveHelper.Action.MOVE_TO)
			{
				double d0 = this.posX - EntityPossessedSword.this.posX;
				double d1 = this.posY - EntityPossessedSword.this.posY;
				double d2 = this.posZ - EntityPossessedSword.this.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				d3 = (double)MathHelper.sqrt(d3);

				if (d3 < EntityPossessedSword.this.getEntityBoundingBox().getAverageEdgeLength())
				{
					this.action = EntityMoveHelper.Action.WAIT;
					EntityPossessedSword.this.motionX *= 0.5D;
					EntityPossessedSword.this.motionY *= 0.5D;
					EntityPossessedSword.this.motionZ *= 0.5D;
				}
				else
				{
					EntityPossessedSword.this.motionX += d0 / d3 * 0.05D * this.speed;
					EntityPossessedSword.this.motionY += d1 / d3 * 0.05D * this.speed;
					EntityPossessedSword.this.motionZ += d2 / d3 * 0.05D * this.speed;

					if (EntityPossessedSword.this.getAttackTarget() == null)
					{
						EntityPossessedSword.this.rotationYaw = -((float)MathHelper.atan2(EntityPossessedSword.this.motionX, EntityPossessedSword.this.motionZ)) * (180F / (float)Math.PI);
						EntityPossessedSword.this.renderYawOffset = EntityPossessedSword.this.rotationYaw;
					}
					else
					{
						double d4 = EntityPossessedSword.this.getAttackTarget().posX - EntityPossessedSword.this.posX;
						double d5 = EntityPossessedSword.this.getAttackTarget().posZ - EntityPossessedSword.this.posZ;
						EntityPossessedSword.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
						EntityPossessedSword.this.renderYawOffset = EntityPossessedSword.this.rotationYaw;
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
			return !EntityPossessedSword.this.getMoveHelper().isUpdating() && EntityPossessedSword.this.rand.nextInt(7) == 0;
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
			BlockPos blockpos = null;

			if (blockpos == null)
			{
				blockpos = new BlockPos(EntityPossessedSword.this);
			}

			for (int i = 0; i < 3; ++i)
			{
				BlockPos blockpos1 = blockpos.add(EntityPossessedSword.this.rand.nextInt(15) - 7, EntityPossessedSword.this.rand.nextInt(11) - 5, EntityPossessedSword.this.rand.nextInt(15) - 7);

				if (EntityPossessedSword.this.world.isAirBlock(blockpos1))
				{
					EntityPossessedSword.this.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);

					if (EntityPossessedSword.this.getAttackTarget() == null)
					{
						EntityPossessedSword.this.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
					}

					break;
				}
			}
		}
	}

}
