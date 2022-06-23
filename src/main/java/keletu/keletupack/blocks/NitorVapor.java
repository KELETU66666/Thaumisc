package keletu.keletupack.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NitorVapor extends Block {

    public NitorVapor() {
        super(Material.AIR);
        this.setTickRandomly(true);
    }

    // TODO Actual meaning and necessity
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    // TODO Actual meaning and necessity
    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    // TODO Actual meaning and necessity
    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    // TODO Actual meaning and necessity
    @Override
    public boolean isCollidable()
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    // TODO According to MCP, this affects ambient occlusion & culling?
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return Block.NULL_AABB;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote)
            world.scheduleBlockUpdate(pos, this, tickRate(world), 0);
    }
}