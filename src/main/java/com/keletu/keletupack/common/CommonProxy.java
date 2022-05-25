package com.keletu.keletupack.common;

import com.keletu.keletupack.common.item.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
    public void preInit(FMLPreInitializationEvent event)
    {
        ItemLoader.init();
    }
}
