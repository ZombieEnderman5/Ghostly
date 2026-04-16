package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;

public class EntitySpectralCorporealityArrow extends ArrowEntity implements ICorporealityProjectile
{
    private int duration = 200;

    public EntitySpectralCorporealityArrow(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    public EntitySpectralCorporealityArrow(World worldIn, LivingEntity shooter) {

        super(worldIn, shooter);
    }


    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(Items.SPECTRAL_ARROW);
    }

    @Override
    protected void arrowHit(LivingEntity living)
    {
        super.arrowHit(living);
        this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);

        living.addPotionEffect(new EffectInstance(Effects.GLOWING, 0));
        if (living instanceof IPartiallyIncorporeal) {
         //   living.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
    }
}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);

        if (compound.contains("Duration"))
        {
            this.duration = compound.getInt("Duration");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("Duration", this.duration);
    }
}