package keletu.keletupack.items.tools;

import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ShadowAxe extends ItemAxe implements IHasModel{
    public ShadowAxe(String name, CreativeTabs tab, ToolMaterial material) {

        super(material,11.0F,-3.0F);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModItems.ITEMS.add(this);
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsKP.RESOURCETMISC, 1, 6)) ? true : super
                .getIsRepairable(stack1, stack2);
    }
    
    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}