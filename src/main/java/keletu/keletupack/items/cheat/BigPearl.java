package keletu.keletupack.items.cheat;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BigPearl extends ItemBase{
    public BigPearl() {
        super("big_pearl", keletupack.ITEM_TAB);
        setCreativeTab(keletupack.ITEM_TAB);
        setMaxDamage(8);
        setMaxStackSize(1);
        setNoRepair();

        ModItems.ITEMS.add(this);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
