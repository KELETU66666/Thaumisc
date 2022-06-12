package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.ThaumcraftInvHelper;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;

public class RunicAmuletEarth extends ItemBase implements IBauble {
    public RunicAmuletEarth() {
        super("runic_amulet_earth", keletupack.ITEM_TAB);
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
            if(player.getAbsorptionAmount() == 0 && ItemNBTHelper.getInt(itemstack, "time",0)==0){
                p.setAbsorptionAmount(8);
                ItemNBTHelper.setInt(itemstack, "time", 1200);
            }else if(ItemNBTHelper.getInt(itemstack, "time",0)>0){
                ItemNBTHelper.setInt(itemstack, "time", ItemNBTHelper.getInt(itemstack, "time",0)-1);
            }
        }
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == keletupack.ITEM_TAB) {
            ItemStack item = new ItemStack(this);
            ItemNBTHelper.setByte(item, "TC.RUNIC", (byte) 7);
            ItemNBTHelper.setInt(item, "time", 0);
            items.add(item);
        }
    }
}
