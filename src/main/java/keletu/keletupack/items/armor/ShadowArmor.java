package keletu.keletupack.items.armor;

import java.util.List;

import com.verdantartifice.thaumicwonders.common.items.armor.CustomArmorHelper;
import keletu.keletupack.clinet.ModelShadowFortressArmor;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.items.*;
import thaumcraft.client.renderers.models.gear.ModelFortressArmor;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.IThaumcraftItems;

import javax.annotation.Nullable;

public class ShadowArmor extends ItemArmor implements ISpecialArmor, IGoggles, IRevealer, IThaumcraftItems, IVisDiscountGear, IWarpingGear, IHasModel {
    ModelBiped model1;

    ModelBiped model2;

    ModelBiped model;

    public ShadowArmor(String name, ItemArmor.ArmorMaterial material, int renderIndex, EntityEquipmentSlot armorType) {
        super(material, renderIndex, armorType);
        this.model1 = null;
        this.model2 = null;
        this.model = null;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(keletupack.ITEM_TAB);

        ModItems.ITEMS.add(this);
    }

    public Item getItem() {
        return (Item)this;
    }

    public String[] getVariantNames() {
        return new String[] { "normal" };
    }

    public int[] getVariantMeta() {
        return new int[] { 0 };
    }

    @SideOnly(Side.CLIENT)
    public ItemMeshDefinition getCustomMesh() {
        return null;
    }

    public ModelResourceLocation getCustomModelResourceLocation(String variant) {
        return new ModelResourceLocation("thaumcraft:" + variant);
    }

    @Override
    @Nullable
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot,
                                    ModelBiped _default) {

        if (model == null) {
            if (slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.FEET)
                model = new ModelShadowFortressArmor(1.0F);
            else
                model = new ModelShadowFortressArmor(0.5F);

            model.bipedHead.showModel = slot == EntityEquipmentSlot.HEAD;
            model.bipedHeadwear.showModel = slot == EntityEquipmentSlot.HEAD;
            model.bipedBody.showModel = slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.LEGS;
            model.bipedRightArm.showModel = slot == EntityEquipmentSlot.CHEST;
            model.bipedLeftArm.showModel = slot == EntityEquipmentSlot.CHEST;
            model.bipedRightLeg.showModel = slot == EntityEquipmentSlot.LEGS;
            model.bipedLeftLeg.showModel = slot == EntityEquipmentSlot.LEGS;
        }

        return model;
    }

    public enum MaskType {

        NONE(0, ""),
        WARP_REDUCTION(1, "item.fortress_helm.mask.0"),
        WITHER(2, "item.fortress_helm.mask.1"),
        LIFESTEAL(3, "item.fortress_helm.mask.2");

        private int id;
        private String name;

        private MaskType(int i, String n) {
            id = i;
            name = n;
        }

        public int getID() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Nullable
        public static MaskType fromID(int id) {
            for (MaskType t : values()) {
                if (t.getID() == id)
                    return t;
            }

            return null;
        }

    }


    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "keletupack:textures/models/armor/shadow_armor.png";
    }

    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("goggles"))
            tooltip.add(TextFormatting.DARK_PURPLE +
                    I18n.translateToLocal("item.goggles.name"));
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("mask"))
            tooltip.add(TextFormatting.GOLD +
                    I18n.translateToLocal("item.fortress_helm.mask." + stack
                            .getTagCompound().getInteger("mask")));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsKP.SHADOW_INGOT)) ? true : super
                .getIsRepairable(stack1, stack2);
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 5;
    }

    @Override
    public int getWarp (final ItemStack stack, final EntityPlayer player) {
        return 5;
    }

    //Code by yorkeJohn, not me, under CC-BY-NC-ND 4.0 license.

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int priority = 0;
        double ratio = this.damageReduceAmount / 25.0D;
        if (source.isMagicDamage() == true) {
            priority = 1;
            ratio = this.damageReduceAmount / 35.0D;
        } else if (source.isFireDamage() == true || source.isExplosion()) {
            priority = 1;
            ratio = this.damageReduceAmount / 20.0D;
        } else if (source.isUnblockable()) {
            priority = 0;
            ratio = 0.0D;
        }
        ISpecialArmor.ArmorProperties ap = new ISpecialArmor.ArmorProperties(priority, ratio, armor.getMaxDamage() + 1 - armor.getItemDamage());
        if (player instanceof EntityPlayer) {
            double set = 0.750D;
            int q = 0;
            for (int a = 1; a < 4; a++) {
                ItemStack piece = (ItemStack) ((EntityPlayer) player).inventory.armorInventory.get(a);
                if (piece != null && !piece.isEmpty() && piece.getItem() instanceof ShadowArmor) {
                    set += 0.150D;
                    if (piece.hasTagCompound() && piece.getTagCompound().hasKey("mask"))
                        set += 0.05D;
                    q++;
                    if (q <= 1) {
                        ap.Armor++;
                        ap.Toughness++;
                    }
                }
            }
            ratio *= set;
        }
        return new ISpecialArmor.ArmorProperties(priority, ratio, armor.getMaxDamage() + 1 - armor.getItemDamage());
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        int q = 0;
        int ar = 0;
        for (int a = 1; a < 4; a++) {
            ItemStack piece = (ItemStack)player.inventory.armorInventory.get(a);
            if (piece != null && !piece.isEmpty() && piece.getItem() instanceof ShadowArmor) {
                if (piece.hasTagCompound() && piece.getTagCompound().hasKey("mask"))
                    ar++;
                q++;
                if (q <= 1)
                    ar++;
            }
        }
        return ar;
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        if (source != DamageSource.FALL)
            stack.damageItem(damage, entity);
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("goggles"));
    }

    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("goggles"));
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
