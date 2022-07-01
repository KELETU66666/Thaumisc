package keletu.keletupack.init;

import keletu.keletupack.items.NetherCake;
import keletu.keletupack.items.armor.IchorArmor;
import keletu.keletupack.items.armor.KamiArmor;
import keletu.keletupack.items.food;
import keletu.keletupack.items.tools.*;
import keletu.keletupack.items.warpfood;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final ItemFood TAINT_MEAT = new warpfood("taint_meat", 0, 0.0F, false, keletupack.ITEM_TAB);
    public static final ItemFood NetherCake = new NetherCake("nether_cake", 0, 0.0F, false, keletupack.ITEM_TAB);
    public static final ItemFood NrtherSwart  = new food("nether_swart", 2, 0.4F, false, keletupack.ITEM_TAB);
    public static final Item.ToolMaterial MATERIAL_ICHORIUM = EnumHelper.addToolMaterial("ichorium", 6, -1, 8.0F, 4.0F, 22);
    public static final Item.ToolMaterial MATERIAL_MORPH = EnumHelper.addToolMaterial("morph", 7, 1500, 8.0F, 3.0F, 15);
    public static final Item.ToolMaterial MATERIAL_LEATHER_K = EnumHelper.addToolMaterial("ridingcrop", 0, 64, 0.0F, 0.0F, 10);
    public static final Item.ToolMaterial MATERIAL_ICHORIUM_AWAKENED = EnumHelper.addToolMaterial("ichorium_awakened", 7, -1, 10.0F, 6.0F, 22);
    public static final ItemArmor.ArmorMaterial MATERIAL_ICHOR = EnumHelper.addArmorMaterial("ichor", Reference.MOD_ID + ":ichor", -1, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
    public static final ItemSword IchoriumSword = new IchoriumSword("ichorium_sword", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemPickaxe IchoriumPick = new IchoriumPick("ichorium_pick", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemAxe IchoriumAxe = new IchoriumAxe("ichorium_axe", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemSpade IchoriumShovel = new IchoriumShovel("ichorium_shovel", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final Item IchorHelm = new IchorArmor("ichor_helm", MATERIAL_ICHOR, 1, EntityEquipmentSlot.HEAD, keletupack.ITEM_TAB);
    public static final Item IchorChest = new IchorArmor("ichor_chest", MATERIAL_ICHOR, 1, EntityEquipmentSlot.CHEST, keletupack.ITEM_TAB);
    public static final Item IchorLegs = new IchorArmor("ichor_legs", MATERIAL_ICHOR, 2, EntityEquipmentSlot.LEGS, keletupack.ITEM_TAB);
    public static final Item IchorBoots = new IchorArmor("ichor_boots", MATERIAL_ICHOR, 1, EntityEquipmentSlot.FEET, keletupack.ITEM_TAB);
    public static final Item IchorHelmAdv = new KamiArmor("kami_helm", MATERIAL_ICHOR, 1, EntityEquipmentSlot.HEAD, keletupack.ITEM_TAB);
    public static final Item IchorChestAdv = new KamiArmor("kami_chest", MATERIAL_ICHOR, 1, EntityEquipmentSlot.CHEST, keletupack.ITEM_TAB);
    public static final Item IchorLegsAdv = new KamiArmor("kami_legs", MATERIAL_ICHOR, 2, EntityEquipmentSlot.LEGS, keletupack.ITEM_TAB);
    public static final Item IchorBootsAdv = new KamiArmor("kami_boots", MATERIAL_ICHOR, 1, EntityEquipmentSlot.FEET, keletupack.ITEM_TAB);
    public static final ItemPickaxe IchoriumPickAdv = new IchoriumPickAdv("ichorium_pick_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM_AWAKENED);
    public static final ItemAxe IchoriumAxeAdv = new IchoriumAxeAdv("ichorium_axe_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM_AWAKENED);
    public static final ItemSpade IchoriumShovelAdv = new IchoriumShovelAdv("ichorium_shovel_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM_AWAKENED);
    public static final Item IchoriumSwordAdv = new IchoriumSwordAdv("ichorium_sword_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM_AWAKENED);
    public static final ItemSpade MorphShovel = new MorphShovel("morph_shovel", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemSword MorphSword = new MorphSword("morph_sword", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemPickaxe MorphPickaxe = new MorphPick("morph_pick", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemAxe MorphAxe = new MorphAxe("morph_axe", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemPickaxe DISTORTIONPICK = new DistortionPick("distortion_pick", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemSword RIDINGCROP = new RidingCrop("riding_crop", keletupack.ITEM_TAB, MATERIAL_LEATHER_K);
    public static final Item ARCANEDISASSEMBLER = new ArcaneDisassembler();
}