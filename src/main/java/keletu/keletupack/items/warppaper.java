package keletu.keletupack.items;

import keletu.keletupack.keletupack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

public class warppaper extends ItemBase {
    public warppaper() {
        super("warp_paper", keletupack.ITEM_TAB);
    }

    public static int[] getIndividualWarps(EntityPlayer player) {
        ITextComponent userName = player.getDisplayName();
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);
        int[] totals = new int[]{warp.get(IPlayerWarp.EnumWarpType.PERMANENT), warp.get(IPlayerWarp.EnumWarpType.NORMAL), warp.get(IPlayerWarp.EnumWarpType.TEMPORARY)};
        return totals;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        if (!world.isRemote) {
            ItemStack item = player.getHeldItem(handIn);
            int totalWarp = ThaumcraftApi.internalMethods.getActualWarp(player);
            int[] individualWarps = getIndividualWarps(player);
            String severity;
            if (totalWarp <= 10)
                severity = I18n.translateToLocal("chat.warp.minorwarp");
            else if (totalWarp <= 25)
                severity = I18n.translateToLocal("chat.warp.averagewarp");
            else if (totalWarp <= 50)
                severity = I18n.translateToLocal("chat.warp.majorwarp");
            else
                severity = I18n.translateToLocal("chat.warp.deadlywarp");
            player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + severity));
            player.sendMessage(new TextComponentString(
                    " (" + individualWarps[0] + " " + I18n.translateToLocal("chat.warp.permanentwarp") +
                            ", " + individualWarps[1] + " " + I18n.translateToLocal("chat.warp.normalwarp") +
                            ", " + individualWarps[2] + " " + I18n.translateToLocal("chat.warp.tempwarp") + ")"));
            if (!player.capabilities.isCreativeMode && ThaumcraftApi.internalMethods.getActualWarp(player) > 10) {
                item.setCount(item.getCount() - 1);
            }
        }
        return super.onItemRightClick(world, player, handIn);
    }
}
