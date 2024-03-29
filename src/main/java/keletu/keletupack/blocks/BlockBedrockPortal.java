package keletu.keletupack.blocks;

import keletu.keletupack.ConfigKP;
import keletu.keletupack.blocks.tiles.TileBedrockPortal;
import keletu.keletupack.dim.TestWorldProvider;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import keletu.keletupack.util.TeleporterBedrock;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class BlockBedrockPortal extends BlockContainer implements IHasModel
{

    public BlockBedrockPortal(String name, Material materialIn)
    {
        super(materialIn);
        setUnlocalizedName(name).setRegistryName(name).setCreativeTab(keletupack.ITEM_TAB);
        setResistance(6000000.0F);
        setHardness(-2.0F);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileBedrockPortal();
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }



    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollidedWithBlock(world, pos, state, entity);

        if (entity instanceof EntityPlayer && !world.isRemote) {
            if (entity.dimension != ConfigKP.ConfigKP.BEDROCKDIMENSIONID) {

                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                BlockPos pos1 = pos.add(0, 251 - y, 0);
                BlockPos pos2 = pos.add(0, 252 - y, 0);
                BlockPos pos3 = pos.add(0, 253 - y, 0);
                BlockPos pos4 = pos.add(0, 254 - y, 0);
                BlockPos pos5 = pos.add(0, 255 - y, 0);

                entity.changeDimension(ConfigKP.ConfigKP.BEDROCKDIMENSIONID, new TeleporterBedrock((WorldServer) world));
                entity.setPositionAndUpdate(x + 0.5, 251, z + 0.5);


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
                if (entity.world.getBlockState(pos5).getBlock() == Blocks.BEDROCK) {
                    entity.world.setBlockState(pos5, ModBlocks.BEDROCK_PORTAL.getDefaultState());
                }
            } else{

                entity.changeDimension(0, new TeleporterBedrock((WorldServer) world));
                    if (((EntityPlayer) entity).bedLocation != null)
                        entity.setPositionAndUpdate(((EntityPlayer) entity).bedLocation.getX(), ((EntityPlayer) entity).bedLocation.getY() + 3, ((EntityPlayer) entity).bedLocation.getZ());
                   else
                        entity.setPositionAndUpdate(world.getSpawnPoint().getX(), world.getSpawnPoint().getY() + 3, world.getSpawnPoint().getZ());
            }
        }
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.BLACK;
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}