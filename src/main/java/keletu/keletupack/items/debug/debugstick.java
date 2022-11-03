package keletu.keletupack.items.debug;

import keletu.keletupack.event.LivingEvent;
import keletu.keletupack.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

public class debugstick extends ItemBase {
    public debugstick(String name, CreativeTabs tab) {
        super(name, tab);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        player.removeTag("crimson_invite_0");
        player.removeTag("crimson_invite_1");
        player.removeTag("crimson_invite_2");
        player.removeTag("crimson_invite_3");
        player.removeTag("crimson_invite_3_1");
        player.addTag("crimson_invite_4");
        player.removeTag("crimson_invite_final");
        player.removeTag("mission_1");
        player.removeTag("mission_2");
        player.removeTag("mission_3");
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
