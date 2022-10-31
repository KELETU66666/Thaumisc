package keletu.keletupack.items.resources;

import forestry.core.gui.elements.layouts.PaneLayout;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.ItemBase;
import keletu.keletupack.keletupack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nullable;
import java.util.Objects;

public class ResourceCrimson extends ItemBase {

    public ResourceCrimson() {
        super("resourcecrimson", keletupack.ITEM_TAB);
        setUnlocalizedName(Objects.requireNonNull(this.getRegistryName()).getResourcePath());
        setHasSubtypes(true);
        setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("meta"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (stack.getMetadata() == 1) {
                    return 1.0F;
                }
                return 0.0F;
            }
        });

        ModItems.ITEMS.add(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (!isInCreativeTab(tab)) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack item) {
        return super.getUnlocalizedName() + "." + item.getItemDamage();
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ItemStack stack = player.getHeldItem(hand);
            if (player.isSneaking() && ((player.getHeldItemOffhand().getMetadata() == 1 && player.getHeldItemMainhand().getMetadata() == 0) || (player.getHeldItemOffhand().getMetadata() == 0 && player.getHeldItemMainhand().getMetadata() == 1))) {
                NBTTagCompound nbtTagCompound = stack.getTagCompound();

                if (nbtTagCompound == null) {
                    nbtTagCompound = new NBTTagCompound();
                    stack.setTagCompound(nbtTagCompound);
                }

                if (player.getHeldItemMainhand().getItem() == this && player.getHeldItemOffhand().getItem() == this && player.getHeldItemOffhand().getMetadata() == 1 && player.getHeldItemMainhand().getMetadata() == 0 && nbtTagCompound.getInteger("invite_progress") < 5) {
                    nbtTagCompound.setInteger("invite_progress", (nbtTagCompound.getInteger("invite_progress") + 1));
                    player.getHeldItemOffhand().shrink(1);
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }

            if (!player.isSneaking() && stack.getMetadata() == 0) {
                if (!player.getTags().contains("mission_1") && !player.getTags().contains("mission_2") && !player.getTags().contains("mission_3") && player.getTags().contains("crimson_invite_0") && ThaumcraftApi.internalMethods.getActualWarp(player) > 35) {
                    player.removeTag("crimson_invite_0");
                    if (player.world.rand.nextInt(3) == 1) {
                        player.addTag("mission_2");
                    } else if (player.world.rand.nextInt(3) == 2) {
                        player.addTag("mission_3");
                    } else {
                        player.addTag("mission_1");
                    }
                } else if (player.getTags().contains("crimson_invite_1") && ThaumcraftApi.internalMethods.getActualWarp(player) > 40) {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_2")));
                } else if (player.getTags().contains("crimson_invite_2") && ThaumcraftApi.internalMethods.getActualWarp(player) > 45) {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_3")));
                    if (player.getHeldItemMainhand().getItem() == this && player.getHeldItemOffhand().getItem() == ModItems.TaintCrawler && player.getHeldItemOffhand().getCount() >= 5) {
                        player.getHeldItemOffhand().shrink(5);
                        player.addTag("crimson_invite_3");
                        player.removeTag("crimson_invite_2");
                    }
                }
            }
                if (player.getTags().contains("mission_1")) {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_1")));
                    if (player.getHeldItemMainhand().getItem() == this && player.getHeldItemOffhand().getItem() == ItemsTC.ingots && player.getHeldItemOffhand().getMetadata() == 1 && player.getHeldItemOffhand().getCount() >= 3) {
                        player.getHeldItemOffhand().shrink(3);
                        player.addTag("crimson_invite_1");
                        player.removeTag("mission_1");
                    }
                }
                if (player.getTags().contains("mission_2")) {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_1_1")));
                    if (player.getHeldItemMainhand().getItem() == this && player.getHeldItemOffhand().getItem() == Items.DIAMOND && player.getHeldItemOffhand().getCount() >= 10) {
                        player.getHeldItemOffhand().shrink(10);
                        player.addTag("crimson_invite_1");
                        player.removeTag("mission_2");
                    }
                }
                if (player.getTags().contains("mission_3")) {
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_1_2")));
                    if (player.getHeldItemMainhand().getItem() == this && player.getHeldItemOffhand().getItem() == ItemsTC.fabric && player.getHeldItemOffhand().getCount() >= 20) {
                        player.getHeldItemOffhand().shrink(20);
                        player.addTag("crimson_invite_1");
                        player.removeTag("mission_3");
                    }
                }
            }
        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
}
