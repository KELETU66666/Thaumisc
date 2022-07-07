package keletu.keletupack.items;

import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.zeith.thaumicadditions.init.ItemsTAR;
import thaumcraft.common.lib.SoundsTC;

import java.util.List;

public class LootBagCrystal extends Item implements IHasModel {
    public LootBagCrystal(String name, CreativeTabs tab) {
        super();
        setMaxStackSize(16);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModItems.ITEMS.add(this);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(I18n.translateToLocal("tc.lootbag"));
    }

    @NotNull
    public ActionResult<ItemStack> 	onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        Entity e = new EntityItem(world);
        int ingotrate = e.world.rand.nextInt(Math.abs(3) +  1) + Math.min(1, 6);
        int nuggetrate = e.world.rand.nextInt(Math.abs(5) + 1) + Math.min(3, 26);
        int ingotrateAN = e.world.rand.nextInt(Math.abs(3) +  1) + Math.min(1, 6);
        int nuggetrateAN = e.world.rand.nextInt(Math.abs(5) + 1) + Math.min(3, 26);
        int ingotrateMJ = e.world.rand.nextInt(Math.abs(3) +  1) + Math.min(1, 6);
        int nuggetrateMJ = e.world.rand.nextInt(Math.abs(5) + 1) + Math.min(3, 26);
        if (!world.isRemote) {
            {
                if(Loader.isModLoaded("thaumadditions")){
                world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.ADAMINITE_INGOT, ingotrateAN)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.MITHMINITE_INGOT, ingotrate)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.MITHRILLIUM_INGOT, ingotrateMJ)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.ADAMINITE_NUGGET, nuggetrateAN)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.MITHMINITE_NUGGET, nuggetrate)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.MITHRILLIUM_NUGGET, nuggetrateMJ)));
                    world.spawnEntity(new EntityItem(e.world, player.posX, player.posY, player.posZ, new ItemStack(ItemsTAR.ADAMINITE_FABRIC, 1)));
                }
            }
            player.playSound(SoundsTC.coins, 0.75F, 1.0F);
        }
        player.getHeldItem(hand).shrink(1);
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}