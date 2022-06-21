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
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandTP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
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
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static net.minecraft.command.CommandBase.getEntity;

public class BlockBedrockPortal extends BlockContainer
{

    public BlockBedrockPortal(String name, Material materialIn)
    {
        super(materialIn);
        setUnlocalizedName(name).setRegistryName(name).setCreativeTab(keletupack.ITEM_TAB);
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
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollidedWithBlock(world, pos, state, entity);

        if (entity instanceof EntityPlayer && !world.isRemote) {
            if (entity.dimension != 31871) {

                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                BlockPos pos1 = pos.add(0, 251 - y, 0);
                BlockPos pos2 = pos.add(0, 252 - y, 0);
                BlockPos pos3 = pos.add(0, 253 - y, 0);
                BlockPos pos4 = pos.add(0, 254 - y, 0);
                BlockPos pos5 = pos.add(0, 255 - y, 0);

                IBlockState state1 = world.getBlockState(pos);
                IBlockState state2 = world.getBlockState(pos);

                state1.getBlock().equals(Blocks.AIR);
                state2.getBlock().equals(this);

                Objects.requireNonNull(entity.getServer()).getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, 31871, new TeleporterBedrock((WorldServer) world));
                ((EntityPlayerMP) entity).connection.player.setPositionAndUpdate(x + 0.5, 251, z + 0.5);

                if (entity.world.getBlockState(pos1).getBlock() == Blocks.BEDROCK) {
                    entity.world.setBlockToAir(pos1);
                }
                if (entity.world.getBlockState(pos2).getBlock() == Blocks.BEDROCK) {
                    entity.world.setBlockToAir(pos2);
                }
                if (entity.world.getBlockState(pos3).getBlock() == Blocks.BEDROCK) {
                    entity.world.setBlockToAir(pos3);
                }
                if (entity.world.getBlockState(pos4).getBlock() == Blocks.BEDROCK) {
                    entity.world.setBlockToAir(pos4);
                }
                if (entity.world.getBlockState(pos5).getBlock() == Blocks.BEDROCK && state2.getBlock().equals(this)) {
                    entity.world.setBlockState(pos5, state2);
                }
            } else{

                entity.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) entity, 0, new TeleporterBedrock((WorldServer) world));
                ((EntityPlayerMP) entity).connection.player.setPositionAndUpdate(world.getSpawnPoint().getX(), world.getSpawnPoint().getY() + 3, world.getSpawnPoint().getZ());
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