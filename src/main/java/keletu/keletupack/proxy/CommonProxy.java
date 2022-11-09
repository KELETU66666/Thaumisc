package keletu.keletupack.proxy;

import keletu.keletupack.dim.ModDimensions;
import keletu.keletupack.dim.OreClusterGenerator;
import keletu.keletupack.event.LivingEvent;
import keletu.keletupack.util.handler.EventHandler;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void registerItemRenderer( Item item, int meta, String id )
    {
    }


    public void preInit( FMLPreInitializationEvent event )
    {
        EventHandler.registerEvents();
        GameRegistry.registerWorldGenerator(new OreClusterGenerator(), 3);
        ModDimensions.init();
    }

    public void registerDisplayInformationInit() {}

    public void init( FMLInitializationEvent event )
    {
    }

    public void sparkle(float posX, float posY, float posZ, int i) {
    }
}
