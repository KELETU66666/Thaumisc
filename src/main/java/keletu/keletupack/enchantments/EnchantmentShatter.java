package keletu.keletupack.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentShatter extends Enchantment {
    public EnchantmentShatter(int id) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("shatter");
        this.setName("shatter");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return ench != EnchantmentsKP.desintegrate && ench != Enchantments.EFFICIENCY;
    }

    @Override
    public int getMinEnchantability(int level)
    {
        return 20 + 3 * (level - 1);
    }
}