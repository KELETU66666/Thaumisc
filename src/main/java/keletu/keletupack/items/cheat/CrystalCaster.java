package keletu.keletupack.items.cheat;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.common.items.casters.ItemCaster;

public class CrystalCaster extends ItemCaster implements IHasModel, IVisDiscountGear {

    public CrystalCaster() {
        super("crystal_caster", 0);
        this.setCreativeTab(keletupack.ITEM_TAB);
        ModItems.ITEMS.add(this);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        float i = AuraHelper.getVis(world, entity.getPosition());
        float k = AuraHelper.getAuraBase(world, entity.getPosition());
        float f = AuraHelper.getFlux(world, entity.getPosition());
        if (entity instanceof EntityPlayer && world.isRemote) {
            if (i < 99999 && ((EntityPlayer) entity).getHeldItem(EnumHand.MAIN_HAND).getItem() == this) {
                AuraHelper.addVis(world, entity.getPosition(), 99999 - i);
                AuraHelper.drainFlux(world, entity.getPosition(), f, false);
            }
            if(((EntityPlayer) entity).getHeldItem(EnumHand.MAIN_HAND).getItem() != this && i > 500){
                AuraHelper.drainVis(world, entity.getPosition(), i-k, false);
                AuraHelper.drainFlux(world, entity.getPosition(), f, false);
            }
        }
    }
    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 90;
    }


}