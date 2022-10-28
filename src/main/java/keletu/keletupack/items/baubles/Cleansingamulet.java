package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.ConfigKP;
import keletu.keletupack.event.LivingEvent;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.lib.events.WarpEvents;

import java.util.List;

public class Cleansingamulet extends ItemBase implements IBauble {
    public Cleansingamulet() {
        super("cleansing_amulet", keletupack.ITEM_TAB);
        this.maxStackSize=1;
    }
    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player instanceof EntityPlayer && !player.world.isRemote) {
            EntityPlayer p = (EntityPlayer) player;
            NonNullList<ItemStack> inv = ((EntityPlayer)player).inventory.mainInventory;
            if(ThaumcraftApi.internalMethods.getActualWarp(p)>0){
                if(ItemNBTHelper.getInt(itemstack, "time",0)>0){
                    ItemNBTHelper.setInt(itemstack, "time", ItemNBTHelper.getInt(itemstack, "time",0)-1);
                }else if(ItemNBTHelper.getInt(itemstack, "time",0)==0){
                    if(ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.TEMPORARY) * 0.05 > 1) {
                        ThaumcraftApi.internalMethods.addWarpToPlayer(p, (int) (-ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.TEMPORARY) * 0.05), IPlayerWarp.EnumWarpType.TEMPORARY);}else
                    {ThaumcraftApi.internalMethods.addWarpToPlayer(p, -1, IPlayerWarp.EnumWarpType.TEMPORARY);}
                    if(ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.PERMANENT) * 0.05 > 1) {
                        ThaumcraftApi.internalMethods.addWarpToPlayer(p, (int) (-ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.PERMANENT) * 0.05), IPlayerWarp.EnumWarpType.PERMANENT);                        WarpEvents.checkWarpEvent(p);}else
                    {ThaumcraftApi.internalMethods.addWarpToPlayer(p, -1, IPlayerWarp.EnumWarpType.PERMANENT);}
                    if(ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.NORMAL) * 0.05 > 1) {
                        ThaumcraftApi.internalMethods.addWarpToPlayer(p, (int) (-ThaumcraftCapabilities.getWarp(p).get(IPlayerWarp.EnumWarpType.NORMAL) * 0.05), IPlayerWarp.EnumWarpType.NORMAL);;}else
                    {ThaumcraftApi.internalMethods.addWarpToPlayer(p, -1, IPlayerWarp.EnumWarpType.NORMAL);}
                    WarpEvents.checkWarpEvent(p);
                    LivingEvent.checkWarpEvent(p);
                    ItemNBTHelper.setInt(itemstack, "time", ConfigKP.ConfigKP.CLEASINGCHARMTICK);
                }
            }
        }
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == keletupack.ITEM_TAB) {
            ItemStack item = new ItemStack(this);
            ItemNBTHelper.setInt(item, "time", ConfigKP.ConfigKP.CLEASINGCHARMTICK);
            items.add(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC + I18n.translateToLocal("item.cleansingamulet.name"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
