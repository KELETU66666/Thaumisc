package keletu.keletupack.init;

import keletu.keletupack.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final Block ICHOR_BLOCK = new BlockBase("ichor_block", Material.IRON);
}