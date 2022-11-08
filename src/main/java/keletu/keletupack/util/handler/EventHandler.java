package keletu.keletupack.util.handler;

import keletu.keletupack.compat.thaumicwonders.EntityDropEventTW;
import keletu.keletupack.event.LivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class EventHandler {
    public static void registerEvents()
    {
        MinecraftForge.EVENT_BUS.register(new LivingEvent());
        if(Loader.isModLoaded("thaumicwonders") && Loader.isModLoaded("thaumadditions")){
        EntityDropEventTW event = new EntityDropEventTW();
        MinecraftForge.EVENT_BUS.register(event);}
    }
}
