package zombieenderman5.ghostly.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

public class ItemShovelOfCorporeality extends ItemSpade {
	
	public ItemShovelOfCorporeality() {
		
		super(GhostlyItemManager.CORPOREALITY_TOOL_MATERIAL);
		
		setUnlocalizedName("shovel_of_corporeality");
		setRegistryName("shovel_of_corporeality");
		setCreativeTab(GhostlyCreativeTabManager.tools);
		
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        if (target instanceof EntityShade) {
        	stack.damageItem(2, attacker);
        	target.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
        }
        else {
        	stack.damageItem(3, attacker);
        }
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.CORPOREAL_RARITY;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		tooltip.add("Shades hit with this take full damage");
		
	}
	
}
