package keletu.keletupack.enchantments;

import keletu.keletupack.items.tools.MorphAxe;
import keletu.keletupack.items.tools.MorphPick;
import keletu.keletupack.items.tools.MorphShovel;
import keletu.keletupack.items.tools.MorphSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentVoid extends Enchantment {
    public EnchantmentVoid(int id) {
        super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("voidtouched");
        this.setName("voidtouched");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 30;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 30;
    }

    @Override
    public boolean canApply(ItemStack item) {
        return (item.getItem() instanceof MorphPick || item.getItem() instanceof MorphAxe || item.getItem() instanceof MorphSword || item.getItem() instanceof MorphShovel);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }
}