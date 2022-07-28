package keletu.keletupack.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.tools.ItemPrimalCrusher;

public class EnchantmentGreedy extends Enchantment {
    public EnchantmentGreedy(int id) {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setRegistryName("greedy");
        this.setName("greedy");

        EnchantmentsKP.ENCHANTNENTS.add(this);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 20;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return this !=ench&&ench!= EnchantmentsKP.educational && ench!= Enchantments.LOOTING;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }
}