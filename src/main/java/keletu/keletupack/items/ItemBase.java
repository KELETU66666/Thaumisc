package keletu.keletupack.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name, CreativeTabs tab) {
        setTranslationKey(name).setRegistryName(name).setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
