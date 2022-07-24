package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.ConfigKP;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;

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
                    ThaumcraftApi.internalMethods.addWarpToPlayer(p,-2, IPlayerWarp.EnumWarpType.PERMANENT);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(p ,3, IPlayerWarp.EnumWarpType.TEMPORARY);
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
}
