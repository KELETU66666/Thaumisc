package keletu.keletupack.enchantments;


import keletu.keletupack.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.tools.ItemPrimalCrusher;

public class EnchantmentSlowFall extends Enchantment {
    public EnchantmentSlowFall(int id) {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setRegistryName("slowfall");
        this.setName("slowfall");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return ench != Enchantments.FEATHER_FALLING && ench != this;
    }

    @Override
    public int getMinEnchantability(int ench)
    {
        return 25 + 15 * (ench - 1);
    }

    @Override
    public int getMaxEnchantability(int ench)
    {
        return super.getMinEnchantability(ench) + 40;
    }
}