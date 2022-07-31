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

public class EnchantmentAscentBoost extends Enchantment {
    public EnchantmentAscentBoost(int id) {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setRegistryName("ascentboost");
        this.setName("ascentboost");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMinEnchantability(int level)
    {
        return 20 + 10 * (level - 1);
    }
}