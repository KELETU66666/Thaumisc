package keletu.keletupack.items.tools;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import thaumcraft.api.items.IRechargable;

import javax.annotation.Nonnull;

import static net.minecraft.util.text.translation.I18n.translateToLocal;
import static thaumcraft.api.items.RechargeHelper.consumeCharge;
import static thaumcraft.api.items.RechargeHelper.getCharge;

public class ArcaneDisassembler extends Item implements IHasModel, IRechargable  {

    public ArcaneDisassembler () {
        super();
        setCreativeTab(keletupack.ITEM_TAB);
        setMaxStackSize(1);
        setUnlocalizedName("arcane_disassembler");
        setRegistryName("arcane_disassembler");
        setMaxDamage(1200);

        ModItems.ITEMS.add(this);
    }

    public int getMaxCharge(ItemStack stack, EntityLivingBase player) {
        return 1000;
    }

    public IRechargable.EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) {
        return IRechargable.EnumChargeDisplay.NORMAL;
    }

    @Override
    public boolean showDurabilityBar (final ItemStack stack) {
        return true;
    }

    @Override
    public boolean canHarvestBlock(@Nonnull IBlockState state, ItemStack stack) {
        return state.getBlock() != Blocks.BEDROCK;
    }

    @Override
    public boolean hitEntity (final ItemStack stack, final EntityLivingBase entity, final EntityLivingBase player) {
        if (getCharge(stack) > 0) {
            entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 20);
        }
        else {
            entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 4);
        }

        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (stack.getTagCompound() != null && stack.getTagCompound().getInteger("mode") == 0 && getCharge(stack) > 1) {
            return 20;
        }
        else if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("mode") == 1 && getCharge(stack) > 1) {
            return 8;
        }
        else if(stack.getTagCompound() != null && stack.getTagCompound().getInteger("mode") == 2 && getCharge(stack) > 1) {
            return 128;
        }else{
            return 0;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityliving) {
        if(entityliving instanceof EntityPlayer) {
            consumeCharge(itemstack, entityliving, 2);
            itemstack.damageItem(2, entityliving);
        }
        return true;
    }

    @Override
    public EnumRarity getRarity (final ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking())     {
            NBTTagCompound nbtTagCompound = stack.getTagCompound();

            if (nbtTagCompound == null)
            {
                nbtTagCompound = new NBTTagCompound();
                stack.setTagCompound(nbtTagCompound);
            }

            nbtTagCompound.setInteger("mode", (nbtTagCompound.getInteger("mode") + 1) % 4);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack) && getCharge(itemstack) == 0 && itemstack.getTagCompound().getInteger("mode") == 3)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR)
            {
                if (block == Blocks.GRASS_PATH && getCharge(itemstack) > 0 && itemstack.getTagCompound().getInteger("mode") != 3 && itemstack.getTagCompound() != null)
                {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    consumeCharge(itemstack, player, 1);
                    itemstack.damageItem(1, player);
                    return EnumActionResult.SUCCESS;
                }

                if (block == Blocks.DIRT && getCharge(itemstack) > 0 && itemstack.getTagCompound().getInteger("mode") != 3 && itemstack.getTagCompound() != null)
                {
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT))
                    {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            consumeCharge(itemstack, player, 1);
                            itemstack.damageItem(1, player);
                            return EnumActionResult.SUCCESS;

                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            consumeCharge(itemstack, player, 1);
                            itemstack.damageItem(1, player);
                            return EnumActionResult.SUCCESS;
                    }
                }
                if (block == Blocks.GRASS && getCharge(itemstack) > 0 && itemstack.getTagCompound().getInteger("mode") != 3 && itemstack.getTagCompound() != null){
                    IBlockState iblockstate1 = Blocks.GRASS_PATH.getDefaultState();
                    worldIn.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    if (!worldIn.isRemote)
                    {
                        worldIn.setBlockState(pos, iblockstate1, 11);
                        consumeCharge(itemstack, player, 1);
                        itemstack.damageItem(1, player);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            return EnumActionResult.PASS;
        }
    }

    protected void setBlock(ItemStack stack, EntityPlayer player, World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if (!worldIn.isRemote)
        {
            worldIn.setBlockState(pos, state, 11);
            stack.damageItem(1, player);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote && entityIn != null && (entityIn.ticksExisted % 20 == 0) && entityIn instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) entityIn;
            if (stack.isItemDamaged()) {
                stack.damageItem(-1, entityPlayer);
            }
        }
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
        public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}