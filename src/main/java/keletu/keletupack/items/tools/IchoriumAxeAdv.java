package keletu.keletupack.items.tools;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import thaumcraft.common.lib.utils.BlockUtils;

import javax.annotation.Nullable;
import java.util.List;

public class IchoriumAxeAdv extends ItemAxe implements IHasModel {

    public IchoriumAxeAdv(String name, CreativeTabs tab, ToolMaterial material) {

        super(material, 11.0F, -3.0F);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);
        this.addPropertyOverride(new ResourceLocation("ichoriumaxeadv:awaken"), new IItemPropertyGetter() {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (stack.getTagCompound() != null && stack.getTagCompound().getInteger("awaken") != 0) {
                    if (stack.getTagCompound() != null && stack.getTagCompound().getInteger("awaken") == 2) {
                        return 2.0F;
                    }
                    return 1.0F;
                }
                return 0.0F;
            }
        });

        ModItems.ITEMS.add(this);
    }

    public static boolean harvestAdditionalBlock(World world, EntityPlayer pl, ItemStack toolStack, BlockPos originPos, BlockPos breakPos) {
        IBlockState blockState = world.getBlockState(breakPos);
        Block block = blockState.getBlock();

        if (block.isAir(blockState, world, breakPos))
            return false;

        if (ForgeHooks.blockStrength(world.getBlockState(originPos), pl, world, originPos) / ForgeHooks.blockStrength(blockState, pl, world, breakPos) > 10f)
            return false;

        if (!world.isRemote) {

            if (block.removedByPlayer(blockState, world, breakPos, pl, true)) {
                toolStack.onBlockDestroyed(world, blockState, breakPos, pl);
                block.onPlayerDestroy(world, breakPos, blockState);
                block.harvestBlock(world, pl, breakPos, blockState, world.getTileEntity(breakPos), toolStack);
                ((EntityPlayerMP) pl).connection.sendPacket(new SPacketBlockChange(world, breakPos));
            }
        } else {
            if (block.removedByPlayer(blockState, world, breakPos, pl, true)) {
                block.onPlayerDestroy(world, breakPos, blockState);
                toolStack.onBlockDestroyed(world, blockState, breakPos, pl);

                if (toolStack.getCount() <= 0 && toolStack == pl.getHeldItemMainhand()) {
                    ForgeEventFactory.onPlayerDestroyItem(pl, toolStack, EnumHand.MAIN_HAND);
                    pl.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
        }

        return true;
    }

    public static void breakBlockEvent(BlockEvent.BreakEvent event) {
        if (!(event.getWorld()).isRemote && event.getPlayer() != null) {
            ItemStack heldItem = event.getPlayer().getHeldItem(event.getPlayer().getActiveHand());
            if (heldItem != null) {
                List<EnumInfusionEnchantment> list = EnumInfusionEnchantment.getInfusionEnchantments(heldItem);
                if (ForgeHooks.isToolEffective((IBlockAccess)event.getWorld(), event.getPos(), heldItem))
                    if (!event.getPlayer().isSneaking()) {
                        event.setCanceled(true);
                        if (!event.getPlayer().getName().equals("FakeThaumcraftBore"))
                            heldItem.damageItem(1, (EntityLivingBase)event.getPlayer());
                        BlockUtils.breakFurthestBlock(event.getWorld(), event.getPos(), event.getState(), event.getPlayer());
                    }
            }
        }
    }


    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
        if (stack.getTagCompound() != null && stack.getTagCompound().getInteger("awaken") == 2) {
            BlockPos breakPos = pos;

            while (world.getBlockState(breakPos = breakPos.up()).getBlock().isWood(world, breakPos)) {
                harvestAdditionalBlock(world, (EntityPlayer) entity, stack, pos, breakPos);

            }
        }
        return super.onBlockDestroyed(stack, world, state, pos, entity);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking()) {
            NBTTagCompound nbtTagCompound = stack.getTagCompound();

            if (nbtTagCompound == null) {
                nbtTagCompound = new NBTTagCompound();
                stack.setTagCompound(nbtTagCompound);
            }

            nbtTagCompound.setInteger("awaken", (nbtTagCompound.getInteger("awaken") + 1) % 3);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}