package keletu.keletupack.enchantments;

import keletu.keletupack.items.tools.MorphAxe;
import keletu.keletupack.items.tools.MorphPick;
import keletu.keletupack.items.tools.MorphShovel;
import keletu.keletupack.items.tools.MorphSword;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentTunnel extends Enchantment {
    public EnchantmentTunnel(int id) {
        super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("tunnel");
        this.setName("tunnel");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return ench != EnchantmentsKP.shatter && ench != Enchantments.EFFICIENCY && ench != this;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}