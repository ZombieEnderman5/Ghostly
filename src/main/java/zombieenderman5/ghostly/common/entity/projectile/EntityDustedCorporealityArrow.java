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
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

public class EntityDustedCorporealityArrow extends EntityCorporealityArrow
{
    public EntityDustedCorporealityArrow(World worldIn)
    {
        super(worldIn);
    }

    public EntityDustedCorporealityArrow(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
    }

    public EntityDustedCorporealityArrow(World worldIn, double x, double y, double z)
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
            this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
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
        if (living instanceof EntityShade) {
        	living.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
        } else {
        	living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200));
        }
    }

    public static void registerFixesDustedArrow(DataFixer fixer)
    {
        EntityCorporealityArrow.registerFixesArrow(fixer, "DustedCorporealityArrow");
    }
}