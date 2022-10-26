package keletu.keletupack.init;

import keletu.keletupack.items.armor.IchorArmor;
import keletu.keletupack.items.armor.KamiArmor;
import keletu.keletupack.items.armor.ShadowArmor;
import keletu.keletupack.items.food.NetherCake;
import keletu.keletupack.items.food.food;
import keletu.keletupack.items.food.warpfood;
import keletu.keletupack.items.tools.*;
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
    public static final ItemFood NetherSwart  = new food("nether_swart", 2, 0.4F, false, keletupack.ITEM_TAB);
    public static final Item.ToolMaterial MATERIAL_MORPH = EnumHelper.addToolMaterial("morph", 3, 1500, 10.0F, 3.0F, 18);
    public static final Item.ToolMaterial MATERIAL_ICHORIUM= EnumHelper.addToolMaterial("ICHOR", 4, -1, 10F, 5F, 25);
    public static final ItemArmor.ArmorMaterial MATERIAL_ICHOR = EnumHelper.addArmorMaterial("ichor", Reference.MOD_ID + ":ichor", -1, new int[]{3, 6, 8, 3}, 22, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F);
    public static final ItemSword IchoriumSword = new IchoriumSword("ichorium_sword", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemPickaxe IchoriumPick = new IchoriumPick("ichorium_pick", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemAxe IchoriumAxe = new IchoriumAxe("ichorium_axe", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemSpade IchoriumShovel = new IchoriumShovel("ichorium_shovel", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final Item IchorHelm = new IchorArmor("ichor_helm", MATERIAL_ICHOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item IchorChest = new IchorArmor("ichor_chest", MATERIAL_ICHOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item IchorLegs = new IchorArmor("ichor_legs", MATERIAL_ICHOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item IchorBoots = new IchorArmor("ichor_boots", MATERIAL_ICHOR, 1, EntityEquipmentSlot.FEET);
    public static final Item IchorHelmAdv = new KamiArmor("kami_helm", MATERIAL_ICHOR, 1, EntityEquipmentSlot.HEAD);
    public static final Item IchorChestAdv = new KamiArmor("kami_chest", MATERIAL_ICHOR, 1, EntityEquipmentSlot.CHEST);
    public static final Item IchorLegsAdv = new KamiArmor("kami_legs", MATERIAL_ICHOR, 2, EntityEquipmentSlot.LEGS);
    public static final Item IchorBootsAdv = new KamiArmor("kami_boots", MATERIAL_ICHOR, 1, EntityEquipmentSlot.FEET);
    public static final ItemPickaxe IchoriumPickAdv = new IchoriumPickAdv("ichorium_pick_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemAxe IchoriumAxeAdv = new IchoriumAxeAdv("ichorium_axe_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemSpade IchoriumShovelAdv = new IchoriumShovelAdv("ichorium_shovel_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final Item IchoriumSwordAdv = new IchoriumSwordAdv("ichorium_sword_adv", keletupack.ITEM_TAB, MATERIAL_ICHORIUM);
    public static final ItemSpade MorphShovel = new MorphShovel("morph_shovel", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemSword MorphSword = new MorphSword("morph_sword", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemPickaxe MorphPickaxe = new MorphPick("morph_pick", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemAxe MorphAxe = new MorphAxe("morph_axe", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemPickaxe DISTORTIONPICK = new DistortionPick("distortion_pick", keletupack.ITEM_TAB, MATERIAL_MORPH);
    public static final ItemSword RIDINGCROP = new RidingCrop("riding_crop", keletupack.ITEM_TAB, Item.ToolMaterial.WOOD);
    public static final Item ARCANEDISASSEMBLER = new ArcaneDisassembler();
    public static final ItemArmor.ArmorMaterial SHADOW_FORTRESS = EnumHelper.addArmorMaterial("SHADOW_FORTRESS", "shadow", 3000, new int[]{
            0, 6, 10, 4
    }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4F);
    public static final Item ShadowHelm = new ShadowArmor("shadow_fortress_helm", SHADOW_FORTRESS, 1, EntityEquipmentSlot.HEAD);
    public static final Item ShadowChest = new ShadowArmor("shadow_fortress_chest", SHADOW_FORTRESS, 1, EntityEquipmentSlot.CHEST);
    public static final Item ShadowLegs = new ShadowArmor("shadow_fortress_legs", SHADOW_FORTRESS, 1, EntityEquipmentSlot.LEGS);
    public static final Item.ToolMaterial MATERIAL_SHADOW = EnumHelper.addToolMaterial("SHADOW", 3, 2500, 17.0F, 6.0F, 30);
    public static final ItemSword ShadowSword = new ShadowSword("shadow_sword", keletupack.ITEM_TAB, MATERIAL_SHADOW);
    public static final ItemPickaxe ShadowPick = new ShadowPick("shadow_pick", keletupack.ITEM_TAB, MATERIAL_SHADOW);
    public static final ItemAxe ShadowAxe = new ShadowAxe("shadow_axe", keletupack.ITEM_TAB, MATERIAL_SHADOW);
    public static final ItemSpade ShadowShovel = new ShadowShovel("shadow_shovel", keletupack.ITEM_TAB, MATERIAL_SHADOW);
    public static final ItemHoe ShadowHoe = new ShadowHoe("shadow_hoe", keletupack.ITEM_TAB, MATERIAL_SHADOW);
}