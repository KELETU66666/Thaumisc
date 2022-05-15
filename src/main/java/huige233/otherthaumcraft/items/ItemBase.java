package huige233.otherthaumcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import huige233.otherthaumcraft.OtherThaumcraft;
import huige233.otherthaumcraft.util.IHasModel;

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
