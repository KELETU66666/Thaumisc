package keletu.keletupack.util.handler;

import keletu.keletupack.event.LivingEvent;
import keletu.keletupack.event.LootTableEvent;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler {
    public static void registerEvents()
    {
        LootTableEvent lootevent = new LootTableEvent();
        LivingEvent can_fly = new LivingEvent();
        MinecraftForge.EVENT_BUS.register(lootevent);
        MinecraftForge.EVENT_BUS.register(can_fly);
    }
}
