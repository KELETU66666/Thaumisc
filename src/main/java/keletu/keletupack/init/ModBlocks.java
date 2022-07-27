package keletu.keletupack.init;

import keletu.keletupack.blocks.BlockBase;
import keletu.keletupack.blocks.BlockBedrockPortal;
import keletu.keletupack.blocks.BlockEtherealBloom;
import keletu.keletupack.blocks.NitorVapor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    public static final Block ICHOR_BLOCK = new BlockBase("ichor_block", Material.IRON);
    public static final Block BEDROCK_PORTAL = new BlockBedrockPortal("bedrock_portal", Material.PORTAL);
    public static final Block ETHEREAL_BLOOM = new BlockEtherealBloom();
    public static final Block NITOR_VAPOR = new NitorVapor();
}