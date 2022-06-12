package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RunicAmulet extends ItemBase implements IBauble {
    public RunicAmulet() {
        super("runic_amulet", keletupack.ITEM_TAB);
        this.maxStackSize=1;
    }
    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stack) {
        if(tab == keletupack.ITEM_TAB) {
            ItemStack itemstack = new ItemStack(this);
            ItemNBTHelper.setByte(itemstack, "TC.RUNIC", (byte) 8);
            stack.add(itemstack);
        }
    }
}
