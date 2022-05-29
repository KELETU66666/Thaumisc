package keletu.keletupack.event;

import keletu.keletupack.common.ItemsKP;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;

import java.util.Random;

public class LootTableEvent {
    @SubscribeEvent
    public void CustomLootTableDrop(LivingDropsEvent event) {
        Random rand = new Random();
        if(event.getEntityLiving() instanceof EntityBlaze)
        {
            if(rand.nextInt(5) == 0)
            {
                event.getEntityLiving().entityDropItem(new ItemStack(ItemsKP.SHARD_NETHER), 0.0f);
            }
        }
    }
}
