package keletu.keletupack.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentPounce extends Enchantment {
    public EnchantmentPounce(int id) {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setRegistryName("pounce");
        this.setName("pounce");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}