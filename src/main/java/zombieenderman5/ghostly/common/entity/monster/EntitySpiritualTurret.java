package zombieenderman5.ghostly.common.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.core.GhostlyLootTableManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.ICorporealityProjectile;
import zombieenderman5.ghostly.common.item.IToolOfCorporeality;

public class EntitySpiritualTurret extends EntityMob implements IPartiallyIncorporeal {
	
	public EntitySpiritualTurret(World worldIn) {
		
		super(worldIn);
		
		isImmuneToFire = true;
		setSize(1.3F, 2.5F);
		setPathPriority(PathNodeType.LAVA, -1.0F);
        setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
        setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		
	}

	@Override
	public void onLivingUpdate() {
		
		if (!this.onGround && this.motionY < 0.0D)
        {
            this.motionY *= 0.9D;
        }
		
		if (!GhostlyConfig.MOBS.spiritualTurrets) this.setDead();
		
		super.onLivingUpdate();
		
	}
	
	@Override
	public void initEntityAI() {
		
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntitySpiritualTurret.AIShadowOrbAttack(this));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        addTargetingAI();
		
	}
	
	public void addTargetingAI() {
		
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		
	}
	
	@Override
	public void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
		
	}
	
	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio)
	    {
	        net.minecraftforge.event.entity.living.LivingKnockBackEvent event = net.minecraftforge.common.ForgeHooks.onLivingKnockBack(this, entityIn, strength, xRatio, zRatio);
	        if(event.isCanceled()) return;
	        EntityLivingBase livingEntity = null;
	        if (entityIn != null && !(entityIn instanceof IProjectile)) livingEntity = (EntityLivingBase) entityIn;
	        if (entityIn != null && livingEntity != null && livingEntity.getHeldItemMainhand().getItem() instanceof IToolOfCorporeality) {
	        	strength = event.getStrength();
	        } else if (entityIn != null && entityIn instanceof ICorporealityProjectile && !(entityIn instanceof EntityDustedCorporealityArrow)) {
	        	strength = event.getStrength();
	        } else if (entityIn != null && entityIn instanceof EntityDustedCorporealityArrow) {
	        	strength = event.getStrength() * 1.5F;
	        } else {
	        	strength = event.getStrength() * 0.9F;
	        }
	        xRatio = event.getRatioX(); zRatio = event.getRatioZ();
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
	
	@Override
	public void fall(float distance, float damageMultiplier) {

		super.fall(distance, 0.9F);

	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		EntityLivingBase sourceLiving = null;
		
		if (source.getTrueSource() != null && source.getTrueSource() instanceof EntityLivingBase) {
			
			sourceLiving = (EntityLivingBase) source.getTrueSource();
			
		}
		
		if (source.isFireDamage()) {

			return false;

		} else if (source instanceof EntityDamageSourceIndirect && source.getImmediateSource() instanceof EntityCorporealityArrow && !(source.getImmediateSource() instanceof EntityDustedCorporealityArrow)) {

			return super.attackEntityFrom(source, amount);

		} else if (source.getImmediateSource() instanceof EntityDustedCorporealityArrow) {

			return super.attackEntityFrom(source, amount * 1.5F);

		} else if (source.getTrueSource() != null && sourceLiving != null && sourceLiving.getHeldItemMainhand().getItem() instanceof IToolOfCorporeality) {

			return super.attackEntityFrom(source, amount);

		} else {
			
			return super.attackEntityFrom(source, amount * 0.9F);
			
		}

	}
	
	@Override
	protected SoundEvent getAmbientSound() {

		return GhostlySoundManager.SPIRITUAL_TURRET_AMBIENT;

	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {

		return GhostlySoundManager.SPIRITUAL_TURRET_HURT;

	}
	
	@Override
	protected SoundEvent getDeathSound() {

		return GhostlySoundManager.SPIRITUAL_TURRET_DEATH;

	}
	
	@Override
	protected ResourceLocation getLootTable() {

		return GhostlyLootTableManager.ENTITIES_SPIRITUAL_TURRET;

	}
	
	@Override
	public float getEyeHeight() {
		
		return 2.0F;
		
	}
	
	static class AIShadowOrbAttack extends EntityAIBase {

		private final EntitySpiritualTurret spiritualTurret;
		private int attackDelay;
		private boolean willAttack;
		
		public AIShadowOrbAttack(EntitySpiritualTurret spiritualTurretIn) {
			
			spiritualTurret = spiritualTurretIn;
			
		}
		
		@Override
		public boolean shouldExecute() {
			
			EntityLivingBase entitylivingbase = this.spiritualTurret.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
			
		}
		
		@Override
		public void startExecuting() {
			
			willAttack = true;
			attackDelay = 0;
			
		}
		
		@Override
		public void resetTask() {
			
			willAttack = false;
			attackDelay = 20;
			
		}
		
		@Override
		public void updateTask() {
			
			--attackDelay;
			EntityLivingBase entitylivingbase = this.spiritualTurret.getAttackTarget();
            double distSq = this.spiritualTurret.getDistanceSq(entitylivingbase);
            
            if (distSq < 4.0D) {
            	
            	if (attackDelay <= 0) {
            		
            		attackDelay = 20;
            		this.spiritualTurret.attackEntityAsMob(entitylivingbase);
            		
            	}
            	
            	this.spiritualTurret.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            	
            } else if (distSq < this.getFollowDistance() * this.getFollowDistance()) {
            	
            	double distTurretTargetX = entitylivingbase.posX - this.spiritualTurret.posX;
                double distTurretTargetY = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (this.spiritualTurret.posY + (double)(this.spiritualTurret.height / 2.0F));
                double distTurretTargetZ = entitylivingbase.posZ - this.spiritualTurret.posZ;
            	
                if (this.attackDelay <= 0 && willAttack) {
                	
                	attackDelay = 30;
                	Vec3d vec3d = this.spiritualTurret.getLook(1.0F);
                	float f = MathHelper.sqrt(MathHelper.sqrt(distSq));// * 0.5F;
                	this.spiritualTurret.playSound(SoundEvents.ENTITY_WITCH_THROW, 1.0F, 0.5F);
                	EntityShadowOrb entitysmallfireball = new EntityShadowOrb(this.spiritualTurret.world, this.spiritualTurret, distTurretTargetX, distTurretTargetY, distTurretTargetZ);
                	EntityShadowOrb entitysmallfireball1 = new EntityShadowOrb(this.spiritualTurret.world, this.spiritualTurret, distTurretTargetX, distTurretTargetY, distTurretTargetZ);
                	EntityShadowOrb entitysmallfireball2 = new EntityShadowOrb(this.spiritualTurret.world, this.spiritualTurret, distTurretTargetX, distTurretTargetY, distTurretTargetZ);
                	EntityShadowOrb entitysmallfireball3 = new EntityShadowOrb(this.spiritualTurret.world, this.spiritualTurret, distTurretTargetX, distTurretTargetY, distTurretTargetZ);
                	entitysmallfireball.posX = this.spiritualTurret.posX + vec3d.x * 1.4D;
                    entitysmallfireball.posY = this.spiritualTurret.posY + (double)(this.spiritualTurret.height / 2.0F) + 0.0D;
                    entitysmallfireball.posX = this.spiritualTurret.posX + vec3d.z * 1.4D;
                    this.spiritualTurret.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                    this.spiritualTurret.world.spawnEntity(entitysmallfireball);
                    entitysmallfireball1.posX = this.spiritualTurret.posX + vec3d.x * -1.4D;
                    entitysmallfireball1.posY = this.spiritualTurret.posY + (double)(this.spiritualTurret.height / 2.0F) + 0.0D;
                    entitysmallfireball.posX = this.spiritualTurret.posX + vec3d.z * -1.4D;
                    this.spiritualTurret.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                    this.spiritualTurret.world.spawnEntity(entitysmallfireball1);
                    entitysmallfireball2.posX = this.spiritualTurret.posX + vec3d.x * 1.4D;
                    entitysmallfireball2.posY = this.spiritualTurret.posY + (double)(this.spiritualTurret.height / 2.0F) + -0.8D;
                    entitysmallfireball.posX = this.spiritualTurret.posX + vec3d.z * 1.4D;
                    this.spiritualTurret.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                    this.spiritualTurret.world.spawnEntity(entitysmallfireball2);
                    entitysmallfireball3.posX = this.spiritualTurret.posX + vec3d.x * -1.4D;
                    entitysmallfireball3.posY = this.spiritualTurret.posY + (double)(this.spiritualTurret.height / 2.0F) + -0.8D;
                    entitysmallfireball.posX = this.spiritualTurret.posX + vec3d.z * -1.4D;
                    this.spiritualTurret.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                    this.spiritualTurret.world.spawnEntity(entitysmallfireball3);
                    //this.spiritualTurret.attackEntityFrom(new DamageSource("expel_piece"), 4.0F);
                	
                }
                
                this.spiritualTurret.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
                
            }
			
            super.updateTask();
            
		}
		
		private double getFollowDistance() {
			
			IAttributeInstance iattributeinstance = this.spiritualTurret.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
            return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
			
		}
		
	}
	
}
