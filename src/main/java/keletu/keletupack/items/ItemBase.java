package keletu.keletupack.items;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name, CreativeTabs tab) {
        setUnlocalizedName(name).setRegistryName(name).setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }


    @Override
    public void registerModels() {
        for (int i = 0; i < 8; i++) {
            keletupack.proxy.registerItemRenderer(this, i, "inventory");
        }
    }

}
