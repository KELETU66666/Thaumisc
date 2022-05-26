package keletu.keletupack.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final ItemFood TAINT_MEAT = new food("taint_meat", 0, 0.0F, false, CreativeTabs.FOOD);
}
