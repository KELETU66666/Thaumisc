package com.keletu.keletupack;


import com.keletu.keletupack.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



@Mod(modid = keletupack.MODID, name = keletupack.NAME, version =keletupack.VERSION, acceptedMinecraftVersions =keletupack.acceptedMinecraftVersions)
public class keletupack {
public static final String

    MODID = "keletupack";
    public static final String NAME = "Keletu's Pack";
    public static final String VERSION = "1.0";
    public static final String acceptedMinecraftVersions = "1.12.2";

    @SidedProxy(clientSide = "com.keletu.keletupack.client.ClientProxy",
            serverSide = "com.keletu.keletupack.common.CommonProxy")
            public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
