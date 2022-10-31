package keletu.keletupack.research.theorycraft;


import keletu.keletupack.common.ItemsKP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardLearnFoolish1 extends TheorycraftCard {

    ItemStack rite = new ItemStack(ItemsKP.RESOURCECRIMSON);

    public int getInspirationCost() {
        return 1;
    }

    public String getLocalizedName() {
        return (new TextComponentTranslation("card.biblechanting.name", new Object[0])).getFormattedText();
    }

    public ItemStack[] getRequiredItems() {
        return new ItemStack[] { this.rite };
    }

    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] { false };
    }

    public String getLocalizedText() {
        return (new TextComponentTranslation("card.biblechanting.text", new Object[0])).getFormattedText();
    }

    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        if(ThaumcraftApi.internalMethods.getActualWarp(player) < 50 || !player.getTags().contains("crimson_invite_1"))
            return false;
        int tot = data.getTotal("ELDRITCH");
        return (tot > 0);
    }

    public boolean activate(EntityPlayer player, ResearchTableData data) {
        int tot = data.getTotal("ELDRITCH");
        if (tot > 0) {
            int tries = 0;
            while (tries < 1000) {
                tries++;
                    if (data.getTotal("ELDRITCH") > 0) {
                        data.addTotal("ELDRITCH", -1);
                        tot--;
                        if (tot <= 0)
                            break;
                    }
            }
            ThaumcraftApi.internalMethods.addWarpToPlayer(player, tries / 10, IPlayerWarp.EnumWarpType.TEMPORARY);
            if(player.getTags().contains("crimson_invite_1")) {
                if (player.world.rand.nextInt(10) > 3) {
                    player.addTag("crimson_invite_2");
                    player.removeTag("crimson_invite_1");
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, IPlayerWarp.EnumWarpType.PERMANENT);
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_2_1")));
                } else {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_2_2")));
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, IPlayerWarp.EnumWarpType.NORMAL);
                }
            }
            data.penaltyStart++;
            return true;
        }
        return false;
    }
}
