package keletu.keletupack.util.handler;

import keletu.keletupack.event.LivingEvent;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler {
    public static void registerEvents()
    {
        LivingEvent can_fly = new LivingEvent();
        MinecraftForge.EVENT_BUS.register(can_fly);
    }
}
