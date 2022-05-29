package keletu.keletupack.event;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LivingEvent {
    public static void register(ResourceLocation resourceLocation) {
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END)
            return;
        NBTTagCompound nbt = e.player.getEntityData();
        if (nbt.getBoolean("can_fly")) {
            e.player.capabilities.allowFlying = true;
            nbt.setBoolean("can_fly", false);
        } else if (nbt.hasKey("can_fly")) {
            if (!e.player.capabilities.isCreativeMode) {
                e.player.capabilities.allowFlying = false;
                e.player.capabilities.isFlying = false;
            }
            nbt.removeTag("can_fly");
        }
    }
}
