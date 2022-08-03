package keletu.keletupack.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.tools.ItemPrimalCrusher;

public class EnchantmentGreedy extends Enchantment {
    public EnchantmentGreedy(int id) {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("greedy");
        this.setName("greedy");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int ench) {
        return 20;
    }

    @Override
    public int getMaxEnchantability(int ench) {
        return super.getMinEnchantability(ench) + 50;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return ench != EnchantmentsKP.educational && ench != Enchantments.LOOTING && ench != this;
    }
    public boolean canApply(ItemStack stack)
    {
        return stack.getItem() instanceof ItemAxe ? true : super.canApply(stack);
    }

    public boolean isTreasureEnchantment()
    {
        return true;
    }
}