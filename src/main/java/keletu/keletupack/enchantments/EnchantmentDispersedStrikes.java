package keletu.keletupack.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentDispersedStrikes extends Enchantment {
    public EnchantmentDispersedStrikes(int id) {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("dispersedstrike");
        this.setName("dispersedstrike");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return ench == Enchantments.MENDING && ench == Enchantments.UNBREAKING && ench == Enchantments.KNOCKBACK && ench == Enchantments.LOOTING;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}