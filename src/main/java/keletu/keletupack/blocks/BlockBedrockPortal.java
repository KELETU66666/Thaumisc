package keletu.keletupack.blocks;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;

import jdk.nashorn.internal.ir.BlockStatement;
import keletu.keletupack.blocks.tiles.TileBedrockPortal;
import keletu.keletupack.dim.TestWorldProvider;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.TeleporterBedrock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class BlockBedrockPortal extends BlockContainer
{

    public BlockBedrockPortal(String name, Material materialIn)
    {
        super(materialIn);
        setTranslationKey(name).setRegistryName(name).setCreativeTab(keletupack.ITEM_TAB);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileBedrockPortal();
    }

    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : false;
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public void onEntityCollision(World par1World, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollision(par1World, pos, state, entity);

        if (entity.world.provider.isSurfaceWorld() && entity.dimension != 31871) {

            if (entity instanceof EntityPlayer && !par1World.isRemote) {

                int x = pos.getX();
                int y = 250;
                int z = pos.getZ();
                BlockPos pos1 = pos.add(x, y, z);
                BlockPos pos2 = pos.add(x, y + 1, z);
                BlockPos pos3 = pos.add(x, y + 2, z);
                BlockPos pos4 = pos.add(x, y + 3, z);
                BlockPos pos5 = pos.add(x, y + 4, z);

                IBlockState state1 = par1World.getBlockState(pos);
                IBlockState state2 = par1World.getBlockState(pos);

                state1.getBlock().equals(Blocks.AIR);
                state2.getBlock().equals(this);

                Objects.requireNonNull(entity.getServer()).getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, 31871, new TeleporterBedrock((WorldServer) par1World));
                    if (entity.world.getBlockState(pos1).getBlock() == Blocks.BEDROCK) {
                        entity.world.setBlockToAir(pos1);
                    }
                    if (entity.world.getBlockState(pos2) == Blocks.BEDROCK) {
                        entity.world.setBlockToAir(pos2);
                    }
                    if (entity.world.getBlockState(pos3) == Blocks.BEDROCK) {
                        entity.world.setBlockToAir(pos3);
                    }
                    if (entity.world.getBlockState(pos4) == Blocks.BEDROCK) {
                        entity.world.setBlockToAir(pos4);
                    }
                    if (entity.world.getBlockState(pos5) == Blocks.BEDROCK && state2.getBlock().equals(this)) {
                        entity.world.setBlockState(pos5, state2);
                    }
                    ((EntityPlayerMP) entity).connection.setPlayerLocation(x + 0.5, 251, z + 0.5, 0, 0);
                }
        }
        else if (entity.dimension == 31871) {
            if (entity instanceof EntityPlayer && !par1World.isRemote) {

                entity.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, 0, new TeleporterBedrock((WorldServer) par1World));

                int x = par1World.getSpawnPoint().getX();
                int y = par1World.getSpawnPoint().getY();
                int z = par1World.getSpawnPoint().getZ();

                ((EntityPlayerMP) entity).connection.setPlayerLocation(x + 0.5, y + 3, z + 0.5, 0, 0);
            }
        }

    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.BLACK;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}