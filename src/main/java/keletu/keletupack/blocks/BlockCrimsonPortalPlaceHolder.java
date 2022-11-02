package keletu.keletupack.blocks;

import keletu.keletupack.blocks.tiles.TileEntityCrimsonPortalPlaceHolder;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.casters.IInteractWithCaster;
import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
import thaumcraft.common.lib.SoundsTC;

import java.util.Random;

public class BlockCrimsonPortalPlaceHolder extends BlockContainer implements IHasModel, IInteractWithCaster {
    public BlockCrimsonPortalPlaceHolder(String name, Material materialIn) {
        super(materialIn);
        setUnlocalizedName(name).setRegistryName(name).setCreativeTab(keletupack.ITEM_TAB);
        setResistance(20.0F);
        setHardness(3.0F);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCrimsonPortalPlaceHolder();
    }

    @Override
    public boolean onCasterRightClick(World world, ItemStack stack, EntityPlayer player, BlockPos pos, EnumFacing facing, EnumHand hand) {
        TileEntity te = world.getTileEntity(pos);
        if (!world.isRemote && te instanceof TileEntityCrimsonPortalPlaceHolder && (player.getTags().contains("crimson_invite_4") || player.getTags().contains("crimson_invite_final"))) {
            if (((TileEntityCrimsonPortalPlaceHolder) te).checkLocation()) {
                world.playSound(null, pos, SoundsTC.zap, SoundCategory.BLOCKS, 1.0F, 1.0F);

                // Detonate
                world.setBlockToAir(pos);
                world.createExplosion(null, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, 2.0F, true);

                EntityCultistPortalGreater portal = new EntityCultistPortalGreater(world);
                portal.setNoAI(false);
                portal.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, (float) world.rand.nextInt(360), 0.0F);
                world.spawnEntity(portal);
                return true;
            } else {
                player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + TextFormatting.BOLD.toString() + "233"), true);
                return false;
            }
        }return false;
    }
}
