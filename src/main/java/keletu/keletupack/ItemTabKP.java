package keletu.keletupack;

import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemTabKP extends CreativeTabs {

    public ItemTabKP( ) {
        super("item_tab_kp" );
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.IchorHelmAdv);
    }
}
