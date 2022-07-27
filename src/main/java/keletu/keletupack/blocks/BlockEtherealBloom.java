package keletu.keletupack.blocks;

import keletu.keletupack.blocks.tiles.TileEtherealBloom;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import thaumcraft.Thaumcraft;

public class BlockEtherealBloom extends BlockBush implements IHasModel {
    public BlockEtherealBloom() {
        super(Material.PLANTS);
        setCreativeTab(keletupack.ITEM_TAB);
        setSoundType(SoundType.PLANT);
        setLightLevel(0.8F);
        setUnlocalizedName("ethereal_bloom");
        setRegistryName("ethereal_bloom");

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEtherealBloom tile = (TileEtherealBloom)worldIn.getTileEntity(pos);
        if (tile != null)
            tile.growthCounter = 0;
    }

    protected boolean canSustainBush(IBlockState state) {
        return state.isFullBlock();
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return (TileEntity)new TileEtherealBloom();
    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Cave;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
