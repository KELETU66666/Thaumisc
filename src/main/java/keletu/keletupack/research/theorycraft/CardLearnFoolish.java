package keletu.keletupack.research.theorycraft;


import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.items.resources.ResourceCrimson;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.theorycraft.ResearchTableData;
import thaumcraft.api.research.theorycraft.TheorycraftCard;

public class CardLearnFoolish extends TheorycraftCard {
    ItemStack rite = new ItemStack(ItemsKP.RESOURCECRIMSON);

    public int getInspirationCost() {
        return 2;
    }

    public String getLocalizedName() {
        return (new TextComponentTranslation("card.learnwise.name", new Object[0])).getFormattedText();
    }

    public String getLocalizedText() {
        return (new TextComponentTranslation("card.learnwise.text", new Object[0])).getFormattedText();
    }

    public ItemStack[] getRequiredItems() {
        return new ItemStack[] { this.rite };
    }

    public boolean[] getRequiredItemsConsumed() {
        return new boolean[] { false };
    }

    public boolean initialize(EntityPlayer player, ResearchTableData data) {
        if(ThaumcraftApi.internalMethods.getActualWarp(player) < 50)
            return false;
        return player.getTags().contains("crimson_invite_1");
    }

    public boolean activate(EntityPlayer player, ResearchTableData data) {
        int tot = 0;
        tot += data.getTotal("ARTIFICE");
        tot += data.getTotal("ALCHEMY");
        tot += data.getTotal("INFUSION");
        tot += data.getTotal("BASICS");
        tot += data.getTotal("AUROMANCY");
        tot += data.getTotal("GOLEMANCY");
        if (tot >= 0) {
            String[] cats = { "ARTIFICE", "ALCHEMY", "INFUSION", "BASICS", "AUROMANCY", "GOLEMANCY" };
            int tries = 0;
            while (tot > 0 && tries < 1000) {
                tries++;
                for (String category : cats) {
                    if (data.getTotal(category) > 0) {
                        data.addTotal(category, -1);
                        tot--;
                        if (tot <= 0)
                            break;
                    }
                }
            }
            data.addTotal("ELDRITCH", tries);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player, 3, IPlayerWarp.EnumWarpType.NORMAL);
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
