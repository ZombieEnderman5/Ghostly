package zombieenderman5.ghostly.common.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeEventFactory;

import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.common.entity.monster.IPartiallyIncorporeal;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;

public class ItemBowOfCorporeality extends BowItem implements IToolOfCorporeality
{
    public ItemBowOfCorporeality() {
        super(new Properties()
                .maxStackSize( 1)
                .group(Ghostly.ITEMS)
                .maxDamage(433));
    }

    private ItemStack findAmmo(PlayerEntity player)
    {
        if (this.isArrow(player.getHeldItem(Hand.OFF_HAND)))
        {
            return player.getHeldItem(Hand.OFF_HAND);
        }
        else if (this.isArrow(player.getHeldItem(Hand.MAIN_HAND)))
        {
            return player.getHeldItem(Hand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.isArrow(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof ArrowItem;
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */

        @Override
        public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

        if (entityLiving instanceof PlayerEntity) {
        	PlayerEntity entityplayer = (PlayerEntity) entityLiving;
            boolean flag = entityplayer.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = this.findAmmo(entityplayer);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag)
            {
                if (itemstack.isEmpty())
                {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);

                if ((double)f >= 0.1D)
                {
                    boolean flag1 = entityplayer.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

                    if (!worldIn.isRemote)
                    {
                        ArrowItem itemarrow = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity entityarrow = createArrow(worldIn, itemstack, entityplayer);
                        entityarrow.setDirectionAndMovement(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                            entityarrow.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

                        if (j > 0)
                        {
                            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

                        if (k > 0)
                        {
                            entityarrow.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
                        {
                            entityarrow.setFire(100);
                        }

                        stack.damageItem(1, entityplayer, (player) -> {
                            player.sendBreakAnimation(entityplayer.getActiveHand());
                        });

                        if (flag1 || entityplayer.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW))
                        {
                            entityarrow.pickupStatus = ArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(entityarrow);
                    }

                    worldIn.playSound((PlayerEntity) null, entityplayer.getPosX(), entityplayer.getPosY(), entityplayer.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!flag1 && !entityplayer.abilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            entityplayer.inventory.deleteStack(itemstack);
                        }
                    }

                    entityplayer.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

	/**
     * Gets the velocity of the arrow entity from the bow's charge
     */
    public static float getArrowVelocity(int charge)
    {
        float f = (float)charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        return f;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public UseAction getUseAction(ItemStack stack)
    {
        return UseAction.BOW;
    }

    /**
     * Called when the equipped item is right clicked.
     */
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !this.findAmmo(playerIn).isEmpty();

        ActionResult<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !flag)
        {
            return flag ? new ActionResult(ActionResultType.PASS, itemstack) : new ActionResult(ActionResultType.FAIL, itemstack);
        }
        else
        {
            playerIn.setActiveHand(handIn);
            return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
        }
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    @Override
    public int getItemEnchantability()
    {
        return 0;
    }
    
    @Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        if (target instanceof IPartiallyIncorporeal) {
        	//target.playSound(GhostlySoundManager.CORPOREALITY_TOOL_HIT, 1.0F, 1.0F);
        }
        return true;
    }
	
	@Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("ghostly.corporeality.melee_and_ranged_tool.information").mergeStyle(TextFormatting.GOLD));
    }

	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
    {
        if (stack.getItem() instanceof SpectralArrowItem) {
        	SpectralArrowEntity entityspectralarrow = new SpectralArrowEntity(worldIn, shooter);
            return entityspectralarrow;
        } else if (stack.getItem() instanceof ItemDustedCorporealityArrow) {
        	EntityDustedCorporealityArrow entitydustedcorporealityarrow = new EntityDustedCorporealityArrow(worldIn, shooter);
            return entitydustedcorporealityarrow;
        } else {
            ArrowEntity entitytippedarrow = new ArrowEntity(worldIn, shooter);
            entitytippedarrow.setPotionEffect(stack);
            return entitytippedarrow;
        }
    }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		
		toRepair = new ItemStack(this, 1);
		repair = new ItemStack(Items.STRING, 1);
		
		return true;
		
	}
	
}