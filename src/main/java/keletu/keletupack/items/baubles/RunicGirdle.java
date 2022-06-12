package keletu.keletupack.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RunicGirdle extends ItemBase implements IBauble {
    public RunicGirdle() {
        super("runic_girdle", keletupack.ITEM_TAB);
        this.maxStackSize=1;
    }
    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.BELT;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> stack) {
        if(tab == keletupack.ITEM_TAB) {
            ItemStack itemstack = new ItemStack(this);
            ItemNBTHelper.setByte(itemstack, "TC.RUNIC", (byte) 10);
            stack.add(itemstack);
        }
    }
}
