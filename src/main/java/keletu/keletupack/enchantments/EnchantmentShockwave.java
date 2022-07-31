package keletu.keletupack.enchantments;


import keletu.keletupack.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class EnchantmentShockwave extends Enchantment {
    public EnchantmentShockwave(int id) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setRegistryName("shockwave");
        this.setName("shockwave");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return ench != Enchantments.FEATHER_FALLING && ench != EnchantmentsKP.slowfall;
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return 20 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
}