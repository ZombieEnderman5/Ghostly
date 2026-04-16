package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import net.minecraftforge.fml.network.NetworkHooks;
import zombieenderman5.ghostly.common.core.RegistryHandler;
import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;


public class EntityDustedCorporealityArrow extends AbstractArrowEntity implements ICorporealityProjectile {
    public EntityDustedCorporealityArrow(EntityType<? extends EntityDustedCorporealityArrow> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityDustedCorporealityArrow(World worldIn, LivingEntity shooter) {
        super(RegistryHandler.DUSTED_ARROW.get(), shooter, worldIn);
    }


    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(RegistryHandler.DUSTED_ARROW_OF_CORPOREALITY.get());
    }

    @Override
    protected void arrowHit(LivingEntity living) {
        super.arrowHit(living);
        this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);

        // Your custom effect
        if (!(living instanceof IPartiallyIncorporeal)) {
          //  living.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
        } else {
            living.addPotionEffect(new EffectInstance(Effects.WITHER, 200));
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}