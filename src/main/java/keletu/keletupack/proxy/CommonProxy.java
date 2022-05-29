package keletu.keletupack.proxy;

import keletu.keletupack.event.LootTableEvent;
import keletu.keletupack.util.handler.EventHandler;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void registerItemRenderer( Item item, int meta, String id )
    {
    }


    public void preInit( FMLPreInitializationEvent event )
    {
        LootTableList.register(new ResourceLocation("modid", "loot_table_name"));
        EventHandler.registerEvents();
    }


    public void init( FMLInitializationEvent event )
    {
    }
}
