package keletu.keletupack.items.food;

import keletu.keletupack.event.LivingEvent;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.events.WarpEvents;

import javax.annotation.Nullable;

import java.util.List;

import static keletu.keletupack.event.LivingEvent.checkWarpEvent;

public class PureTear extends ItemFood implements IHasModel {
    public PureTear(String name, int amount, float saturation, boolean isWolfFood, CreativeTabs tab) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);
        setAlwaysEdible();
        setHasSubtypes(true);
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
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (ThaumcraftApi.internalMethods.getActualWarp(player) + ThaumcraftCapabilities.getWarp(player).get(EnumWarpType.TEMPORARY) > 0 && (stack.getMetadata() == 0 || (stack.getMetadata() != 0 && worldIn.rand.nextInt(100) == 0))) {
                player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("message.clearsuccessful.warp")));
                    if (ThaumcraftApi.internalMethods.getActualWarp(player) + ThaumcraftCapabilities.getWarp(player).get(EnumWarpType.TEMPORARY) > 0) {
                        player.addTag("puring");
                    }
                }
            else
                player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("message.clearfailed.warp")));
            player.world.playSound(null, player.posX, player.posY, player.posZ,
                    SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.PLAYERS, 1F,
                    1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
        }
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
        keletupack.proxy.registerItemRenderer(this, 1, "inventory");
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

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(stack.getMetadata() == 0)
        tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC + I18n.translateToLocal("item.puretear.name"));
        if(stack.getMetadata() == 1)
            tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC + I18n.translateToLocal("item.dirtytear.name"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}