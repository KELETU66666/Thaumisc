package keletu.keletupack.items.armor;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.items.IVisDiscountGear;

public class IchorArmor extends ItemArmor implements IVisDiscountGear, ISpecialArmor {

    public IchorArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(keletupack.ITEM_TAB);

        ModItems.ITEMS.add(this);
    }

    private final int[] discounts = new int[]{0, 0, 3, 4, 4, 4};

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, getArmorMaterial().getDamageReductionAmount(armorType) * 0.0425, Integer.MAX_VALUE);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @NotNull ItemStack armor, int slot) {
        return getArmorMaterial().getDamageReductionAmount(armorType);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @NotNull ItemStack stack, DamageSource source, int damage, int slot) {
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return discounts[armorType.ordinal()];
    }
}