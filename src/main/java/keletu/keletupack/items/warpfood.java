package keletu.keletupack.items;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;

public class warpfood extends ItemFood implements IHasModel {
    public warpfood(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setAlwaysEdible();
        ModItems.ITEMS.add(this);
    }
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(!worldIn.isRemote) {
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,5 + worldIn.rand.nextInt(5), EnumWarpType.TEMPORARY);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,4 + worldIn.rand.nextInt(4), EnumWarpType.PERMANENT);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,5 + worldIn.rand.nextInt(5), EnumWarpType.NORMAL);
        }
    }
    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
