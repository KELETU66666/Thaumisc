package keletu.keletupack.blocks;


import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class IchorBlock extends BlockBase{
    public IchorBlock(String name, Material material) {
        super(name, material);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(1.0F);
        setResistance(2.0F);
        setHarvestLevel("pickaxe", 2);

    }
}