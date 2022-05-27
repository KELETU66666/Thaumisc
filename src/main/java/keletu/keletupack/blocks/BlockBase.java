package keletu.keletupack.blocks;

import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.items.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);//将你的方块放在哪个物品栏，这里我们选择的是草方块(建筑方块)那一类

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}