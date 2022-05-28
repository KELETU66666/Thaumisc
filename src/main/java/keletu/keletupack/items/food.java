package keletu.keletupack.items;

import keletu.keletupack.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;

public class food extends ItemFood implements IHasModel {
    public food(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
        super(amount, saturation, isWolfFood);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(!worldIn.isRemote) {
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,6, EnumWarpType.TEMPORARY);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,4, EnumWarpType.PERMANENT);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,5, EnumWarpType.NORMAL);
        }
    }
    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
