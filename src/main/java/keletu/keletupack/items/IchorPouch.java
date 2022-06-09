package keletu.keletupack.items;

import keletu.keletupack.inventory.ExtendPlayerInventory;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class IchorPouch extends ItemBase
{
    public IchorPouch()
    {
        super("ichor_pouch", keletupack.ITEM_TAB);
        setMaxStackSize(1);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if(playerIn.inventory instanceof ExtendPlayerInventory)
        {
            ExtendPlayerInventory inventory = (ExtendPlayerInventory) playerIn.inventory;
            if(inventory.getBackpackItems().get(0).isEmpty())
            {
                playerIn.inventory.setInventorySlotContents(41, heldItem.copy());
                heldItem.setCount(0);
                playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);
                return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, heldItem);
    }
}
