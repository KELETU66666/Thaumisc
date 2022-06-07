package keletu.keletupack.event;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.armor.KamiArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Collections;

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

    @SubscribeEvent
    public void playerJumps(net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent event)
    {
        ItemStack is;
        if(event.getEntity() instanceof EntityPlayer && ((EntityPlayer) event.getEntity()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof KamiArmor)
            event.getEntityLiving().motionY += 0.2750000059604645 * 1.25;
    }


    @SubscribeEvent
    public void fall(LivingFallEvent e)
    {
        ItemStack boots = e.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(!boots.isEmpty() && boots.getItem() instanceof KamiArmor)
        {
            e.setDamageMultiplier(0);
            e.setCanceled(true);
        }
    }

}