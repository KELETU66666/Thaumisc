package keletu.keletupack.items.tools;

import keletu.keletupack.items.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSword;

public class IchoriumAxe extends ItemAxe implements IHasModel{
    public IchoriumAxe(String name, CreativeTabs tab, ToolMaterial material) {

        super(material,10.0F,-3.0F);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}