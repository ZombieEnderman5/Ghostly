package zombieenderman5.ghostly.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;

public class EntitySpectralVenomArrow extends EntityVenomArrow
{
    private int duration = 200;

    public EntitySpectralVenomArrow(World worldIn)
    {
        super(worldIn);
    }

    public EntitySpectralVenomArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    public EntitySpectralVenomArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote && !this.inGround)
        {
            this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected ItemStack getArrowStack()
    {
        return new ItemStack(Items.SPECTRAL_ARROW);
    }

    @Override
    protected void arrowHit(EntityLivingBase living)
    {
        super.arrowHit(living);
        living.addPotionEffect(new PotionEffect(MobEffects.GLOWING, this.duration, 0));
        living.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1));
    }

    public static void registerFixesSpectralArrow(DataFixer fixer)
    {
        EntityCorporealityArrow.registerFixesArrow(fixer, "SpectralVenomArrow");
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("Duration"))
        {
            this.duration = compound.getInteger("Duration");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Duration", this.duration);
    }
}