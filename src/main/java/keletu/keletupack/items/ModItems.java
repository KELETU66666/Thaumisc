package keletu.keletupack.items;

import keletu.keletupack.items.tools.*;
import keletu.keletupack.items.armor.IchorArmor;
import keletu.keletupack.util.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final ItemFood TAINT_MEAT = new food("taint_meat", 0, 0.0F, false, CreativeTabs.FOOD);
    static final Item.ToolMaterial MATERIAL_ICHORIUM =EnumHelper.addToolMaterial("ichorium", 10, -1, 3.5F, 4.0F, 22);
    public static final ItemArmor.ArmorMaterial MATERIAL_ICHOR = EnumHelper.addArmorMaterial("ichor", Reference.MOD_ID + ":ichor", -1, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
    public static final ItemSword IchoriumSword = new IchoriumSword("ichorium_sword", CreativeTabs.COMBAT, MATERIAL_ICHORIUM);
    public static final ItemPickaxe IchoriumPick = new IchoriumPick("ichorium_pick", CreativeTabs.TOOLS, MATERIAL_ICHORIUM);
    public static final ItemAxe IchoriumAxe = new IchoriumAxe("ichorium_axe", CreativeTabs.TOOLS, MATERIAL_ICHORIUM);
    public static final ItemSpade IchoriumShovel = new IchoriumShovel("ichorium_shovel", CreativeTabs.TOOLS, MATERIAL_ICHORIUM);
    public static final Item IchorHelm = new IchorArmor("ichor_helm",MATERIAL_ICHOR,1, EntityEquipmentSlot.HEAD,CreativeTabs.COMBAT);
    public static final Item IchorChest = new IchorArmor("ichor_chest",MATERIAL_ICHOR,1,EntityEquipmentSlot.CHEST,CreativeTabs.COMBAT);
    public static final Item IchorLegs = new IchorArmor("ichor_legs",MATERIAL_ICHOR,2,EntityEquipmentSlot.LEGS,CreativeTabs.COMBAT);
    public static final Item IchorBoots = new IchorArmor("ichor_boots",MATERIAL_ICHOR,1,EntityEquipmentSlot.FEET,CreativeTabs.COMBAT);
}