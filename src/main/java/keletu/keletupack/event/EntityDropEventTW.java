package keletu.keletupack.event;

import com.verdantartifice.thaumicwonders.common.entities.monsters.EntityCorruptionAvatar;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class EntityDropEventTW {
    private void addDrop(List<EntityItem> drops, Entity e, int min, int max) {
        drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, new ItemStack(ItemsKP.LOOT_BAG_CRYSTAL, 1)));
    }

    @SubscribeEvent
    public void onDrops(LivingDropsEvent event) {
        Entity e = event.getEntity();
        if(e instanceof EntityCorruptionAvatar) {
            addDrop(event.getDrops(), e, 1, 1);
        }
    }
}