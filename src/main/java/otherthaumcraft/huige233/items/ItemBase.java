package otherthaumcraft.huige233.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import otherthaumcraft.huige233.OtherThaumcraft;
import otherthaumcraft.huige233.util.IHasModel;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name, CreativeTabs tab) {
        setTranslationKey(name).setRegistryName(name).setCreativeTab(tab);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        OtherThaumcraft.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
