package keletu.keletupack.items.food;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class food extends ItemFood implements IHasModel {
    public food(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setAlwaysEdible();
        ModItems.ITEMS.add(this);
    }
    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
