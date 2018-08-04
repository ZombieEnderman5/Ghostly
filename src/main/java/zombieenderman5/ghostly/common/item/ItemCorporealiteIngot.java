package zombieenderman5.ghostly.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;
import zombieenderman5.ghostly.common.core.GhostlySoundManager;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

public class ItemCorporealiteIngot extends Item {
	
	public ItemCorporealiteIngot() {
		
		setUnlocalizedName("corporealite_ingot");
		setRegistryName("corporealite_ingot");
		setCreativeTab(GhostlyCreativeTabManager.miscellaneous);
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.CORPOREAL_RARITY;
    }
	
}
