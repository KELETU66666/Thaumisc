package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import thaumcraft.common.lib.SoundsTC;

import java.util.List;
public class RunicGirdleAir extends ItemBase implements IBauble {
    public RunicGirdleAir() {
        super("runic_girdle_air", keletupack.ITEM_TAB);
        this.maxStackSize=1;
    }
    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.BELT;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (player instanceof EntityPlayer && !player.world.isRemote) {
            if (player.getAbsorptionAmount() == 0 && ItemNBTHelper.getInt(itemstack, "time", 0) == 0) {
                player.world.playSound(null, player.posX, player.posY, player.posZ,
                        SoundsTC.poof, SoundCategory.PLAYERS, 1F,
                        1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);

                List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(player,
                        player.getEntityBoundingBox().grow(4.5, 2, 4.5));

                for (Entity e : entities) {
                    if (e instanceof EntityLivingBase) {
                        EntityLivingBase mob = (EntityLivingBase) e;
                        mob.knockBack(player, 2F, player.posX - mob.posX, player.posZ - mob.posZ);
                    }
                }
                ItemNBTHelper.setInt(itemstack, "time", 400);
            }else if(ItemNBTHelper.getInt(itemstack, "time",0)>0){
                ItemNBTHelper.setInt(itemstack, "time", ItemNBTHelper.getInt(itemstack, "time",0)-1);}
        }
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stack) {
        if (tab == keletupack.ITEM_TAB) {
            ItemStack itemstack = new ItemStack(this);
            ItemNBTHelper.setByte(itemstack, "TC.RUNIC", (byte) 9);
            stack.add(itemstack);
            ItemNBTHelper.setInt(itemstack, "time", 0);
        }
    }
}
