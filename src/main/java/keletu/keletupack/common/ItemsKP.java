package keletu.keletupack.common;

import keletu.keletupack.items.EnderShard;
import keletu.keletupack.items.NetherShard;
import keletu.keletupack.items.warppaper;
import keletu.keletupack.keletupack;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import keletu.keletupack.util.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ItemsKP {
    public static final Item WARP_PAPER = new warppaper();
    public static final Item TAINT_MEAT = null;
    public static final Item CLEANSING_AMULET =null;
    public static final Item ICHOR = null;
    public static final Item ICHOR_CLOTH =null;
    public static final Item ICHOR_INGOT = null;
    public static final Item SHARD_NETHER = new NetherShard();
    public static final Item SHARD_END = new EnderShard();
    public static final Item ICHORIUM_SWORD =null;
    public static final Item ICHORIUM_AXE = null;
    public static final Item ICHORIUM_PICK =null;
    public static final Item ICHORIUM_SHOVEL = null;
    public static final Item ICHOR_HELM =null;
    public static final Item ICHOR_CHEST = null;
    public static final Item ICHOR_LEGS =null;
    public static final Item ICHOR_BOOTS =null;
    public static final Item KAMI_HELM =null;
    public static final Item KAMI_CHEST = null;
    public static final Item KAMI_LEGS =null;
    public static final Item KAMI_BOOTS =null;

}
