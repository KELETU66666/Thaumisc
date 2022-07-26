package keletu.keletupack.items.cheat;

import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BigPearl extends Item implements IHasModel {
    public BigPearl() {
        setUnlocalizedName("big_pearl");
        setRegistryName("big_pearl");
        setCreativeTab(keletupack.ITEM_TAB);
        setMaxDamage(8);
        setMaxStackSize(1);
        setNoRepair();
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

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
