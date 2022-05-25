package com.keletu.keletupack.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader
{
    public static Item coin_adventure = new coin_adventure();
    public static Item coin_bleed = new coin_bleed();
    public static Item coin_magic = new coin_magic();
    public static Item coin_witchery = new coin_witchery();

    public static void init()
    {
        ForgeRegistries.ITEMS.register(coin_adventure.setRegistryName("coin_adventure"));
        ForgeRegistries.ITEMS.register(coin_bleed.setRegistryName("coin_bleed"));
        ForgeRegistries.ITEMS.register(coin_magic.setRegistryName("coin_magic"));
        ForgeRegistries.ITEMS.register(coin_witchery.setRegistryName("coin_witchery"));
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        registerRender(coin_adventure);
        registerRender(coin_bleed);
        registerRender(coin_magic);
        registerRender(coin_witchery);
    }
}

