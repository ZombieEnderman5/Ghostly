package zombieenderman5.ghostly.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class ItemDustedCorporealityArrow extends ItemArrow {
	
	public ItemDustedCorporealityArrow()
    {
		setUnlocalizedName("dusted_arrow_of_corporeality");
		setRegistryName("dusted_arrow_of_corporeality");
        setCreativeTab(GhostlyCreativeTabManager.combat);
    }
	
	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == ItemDustedCorporealityArrow.class;
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.CORPOREAL_RARITY;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		tooltip.add(I18n.translateToLocal("ghostly.dusted_arrow_of_corporeality.damage_information"));
		tooltip.add(I18n.translateToLocal("ghostly.dusted_arrow_of_corporeality.arrow_information"));
		tooltip.add(I18n.translateToLocal("ghostly.dusted_arrow_of_corporeality.bow_information"));
		tooltip.add(I18n.translateToLocal("ghostly.dusted_arrow_of_corporeality.wither_information"));
		
	}
	
}