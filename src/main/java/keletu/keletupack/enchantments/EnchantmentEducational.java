package keletu.keletupack.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentEducational extends Enchantment {
    public EnchantmentEducational(int id) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("educational");
        this.setName("educational");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return ench!= Enchantments.LOOTING && ench!= EnchantmentsKP.greedy && ench != this;
    }

    public int getMinEnchantability(int ench)
    {
        return 10 + (ench - 1) * 9;    }

    public int getMaxEnchantability(int ench) {
        return super.getMinEnchantability(ench) + 50;
    }
}