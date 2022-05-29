package keletu.keletupack.util.handler;

import keletu.keletupack.event.LootTableEvent;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler {
    public static void registerEvents()
    {
        LootTableEvent lootevent = new LootTableEvent();

        MinecraftForge.EVENT_BUS.register(lootevent);
    }
}
