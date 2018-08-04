package zombieenderman5.ghostly.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class ItemShadowDust extends Item {
	
	public ItemShadowDust() {
		
		setUnlocalizedName("shadow_dust");
		setRegistryName("shadow_dust");
		setCreativeTab(GhostlyCreativeTabManager.miscellaneous);
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.SHADOW_RARITY;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		tooltip.add("Dust arrows with this to make them damage Shades by 1.5x");
		
	}
	
}
