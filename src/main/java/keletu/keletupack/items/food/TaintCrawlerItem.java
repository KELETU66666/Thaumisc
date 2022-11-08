package keletu.keletupack.items.food;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.potions.PotionFluxTaint;

public class TaintCrawlerItem extends ItemFood implements IHasModel {
    public TaintCrawlerItem(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setAlwaysEdible();
        setPotionEffect(new PotionEffect(PotionFluxTaint.instance, 2000, 4, false, false), 1F);
        ModItems.ITEMS.add(this);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if(!worldIn.isRemote) {
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,3 + worldIn.rand.nextInt(2), IPlayerWarp.EnumWarpType.TEMPORARY);
            ThaumcraftApi.internalMethods.addWarpToPlayer(player,3 + worldIn.rand.nextInt(2), IPlayerWarp.EnumWarpType.NORMAL);
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (!playerIn.world.isRemote) {
            target.addPotionEffect(new PotionEffect(PotionFluxTaint.instance, 2000, 1));
            stack.shrink(1);
            if(target instanceof EntityPlayer) {
                ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer) target, 3 + target.world.rand.nextInt(2), IPlayerWarp.EnumWarpType.TEMPORARY);
                ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer) target, 3 + target.world.rand.nextInt(2), IPlayerWarp.EnumWarpType.NORMAL);

            }return true;
        }return false;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }}
