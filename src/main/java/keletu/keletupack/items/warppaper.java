package keletu.keletupack.items;

import keletu.keletupack.keletupack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;

public class warppaper extends ItemBase{
    public warppaper() {
        super("warp_paper", keletupack.ITEM_TAB);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(!worldIn.isRemote) {
            ItemStack item = playerIn.getHeldItem(handIn);
            item.setCount(item.getCount() - 1);
            playerIn.sendMessage(new TextComponentString(I18n.translateToLocal("warp.paper") + ThaumcraftApi.internalMethods.getActualWarp(playerIn)));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
