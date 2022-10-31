package keletu.keletupack.blocks.tiles;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.casters.IInteractWithCaster;
import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
import thaumcraft.common.lib.SoundsTC;

public class TileEntityCrimsonPortalPlaceHolder extends TileEntity{

    public boolean checkLocation()
    {
        boolean valid;
        valid = world.getBlockState(pos.down(1)).getBlock() == BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.south(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.west(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.east(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.north(1).west(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.south(1).west(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.north(1).east(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;
        valid = valid && world.getBlockState(pos.south(1).east(1).down(1)).getBlock()== BlocksTC.stoneAncientGlyphed;

        valid = valid && world.getBlockState(pos.north(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.west(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.east(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;

        valid = valid && world.getBlockState(pos.north(2).west(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(2).east(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(2).west(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(2).east(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(1).west(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(1).east(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(1).west(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(1).east(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;

        valid = valid && world.getBlockState(pos.north(2).west(2).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.north(2).east(2).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(2).west(2).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(2).east(2).down(1)).getBlock()== BlocksTC.amberBlock;

        valid = valid && world.getBlockState(pos.north(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.west(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.east(3).down(1)).getBlock()== BlocksTC.stoneAncient;

        valid = valid && world.getBlockState(pos.north(3).west(1).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).east(1).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).west(1).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).east(1).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(1).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(1).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(1).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(1).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;

        valid = valid && world.getBlockState(pos.north(3).west(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).east(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).west(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).east(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(2).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(2).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(2).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(2).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;

        valid = valid && world.getBlockState(pos.north(3).west(3).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.north(3).east(3).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(3).west(3).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(3).east(3).down(1)).getBlock()== BlocksTC.amberBlock;

        valid = valid && world.getBlockState(pos.north(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.west(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.east(4).down(1)).getBlock()== BlocksTC.stoneAncient;

        valid = valid && world.getBlockState(pos.north(4).west(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(4).east(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(4).west(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(4).east(1).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(1).west(4).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(1).east(4).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(1).west(4).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(1).east(4).down(1)).getBlock()== BlocksTC.stoneEldritchTile;

        valid = valid && world.getBlockState(pos.north(4).west(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(4).east(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(4).west(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(4).east(2).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(2).west(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(2).east(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(2).west(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(2).east(4).down(1)).getBlock()== Blocks.NETHER_BRICK;

        valid = valid && world.getBlockState(pos.north(4).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(4).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(4).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(4).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).west(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).east(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).west(4).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).east(4).down(1)).getBlock()== Blocks.NETHER_BRICK;

        valid = valid && world.getBlockState(pos.north(4).west(4).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.north(4).east(4).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(4).west(4).down(1)).getBlock()== BlocksTC.amberBlock;
        valid = valid && world.getBlockState(pos.south(4).east(4).down(1)).getBlock()== BlocksTC.amberBlock;

        valid = valid && world.getBlockState(pos.north(5).west(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(5).east(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(5).west(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(5).east(2).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(2).west(5).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.north(2).east(5).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(2).west(5).down(1)).getBlock()== BlocksTC.stoneEldritchTile;
        valid = valid && world.getBlockState(pos.south(2).east(5).down(1)).getBlock()== BlocksTC.stoneEldritchTile;

        valid = valid && world.getBlockState(pos.north(5).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(5).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(5).west(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(5).east(3).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).west(5).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.north(3).east(5).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).west(5).down(1)).getBlock()== Blocks.NETHER_BRICK;
        valid = valid && world.getBlockState(pos.south(3).east(5).down(1)).getBlock()== Blocks.NETHER_BRICK;

        valid = valid && world.getBlockState(pos.north(5).west(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(5).east(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(5).west(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(5).east(4).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(4).west(5).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(4).east(5).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(4).west(5).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(4).east(5).down(1)).getBlock()== BlocksTC.stoneAncient;

        valid = valid && world.getBlockState(pos.north(6).west(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(6).east(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(6).west(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(6).east(3).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(3).west(6).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.north(3).east(6).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(3).west(6).down(1)).getBlock()== BlocksTC.stoneAncient;
        valid = valid && world.getBlockState(pos.south(3).east(6).down(1)).getBlock()== BlocksTC.stoneAncient;
        return valid;

    }
}
