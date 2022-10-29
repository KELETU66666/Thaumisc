package keletu.keletupack.compat.Tconstruct.fluid;

import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class KPFluidBlock extends BlockFluidClassic implements IHasModel {

    public KPFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);

        this.setRegistryName(fluid.getName());
        this.setUnlocalizedName(fluid.getName());
        ModBlocks.BLOCKS.add(this);
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.LIQUID;
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < 15; i++)
            keletupack.proxy.registerItemRenderer(Item.getItemFromBlock(this), i, "inventory");
    }
}