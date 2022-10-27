package keletu.keletupack.blocks;

import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.armor.KamiArmor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class NitorVapor extends Block {

    public NitorVapor() {
        super(Material.AIR);
        this.setTickRandomly(true);
        this.setRegistryName("nitor_vapor");

        ModBlocks.BLOCKS.add(this);
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

    public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid)
    {
        return false;
    }

    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
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

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {


        if (!world.isRemote) {
            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1));

            if (players.isEmpty()) world.setBlockToAir(pos);
            else if (players.stream().noneMatch(p -> p.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof KamiArmor))
                world.setBlockToAir(pos);

        }
    }
}