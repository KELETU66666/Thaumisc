package keletu.keletupack.loot;

import keletu.keletupack.ConfigKP;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.util.Reference;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler {
    @SubscribeEvent
    public static void onLootTablesLoaded(LootTableLoadEvent event) {
        if (ConfigKP.ConfigKP.SHARDDROPRATE != 0) {
            if (event.getName().equals(LootTableList.ENTITIES_ENDERMAN)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_END, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":ender_shard")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_enderman");
                event.getTable().addPool(table);
            } else if (event.getName().equals(LootTableList.ENTITIES_ENDERMITE)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_END, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":ender_shard1")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_endermite");
                event.getTable().addPool(table);
            } else if (event.getName().equals(LootTableList.ENTITIES_SHULKER)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_END, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":ender_shard2")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_shulker");
                event.getTable().addPool(table);
            } else if (event.getName().equals(LootTableList.ENTITIES_ZOMBIE_PIGMAN)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_NETHER, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":nether_shard")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_pigzombie");
                event.getTable().addPool(table);
            } else if (event.getName().equals(LootTableList.ENTITIES_BLAZE)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_NETHER, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":nether_shard1")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_blaze");
                event.getTable().addPool(table);
            } else if (event.getName().equals(LootTableList.ENTITIES_WITHER_SKELETON)) {
                LootPool table = new LootPool(
                        new LootEntry[]{
                                new LootEntryItem(ItemsKP.SHARD_NETHER, 1, 1, new LootFunction[0],
                                        new LootCondition[]{new KilledByPlayer(false)},
                                        Reference.MOD_ID + ":nether_shard2")},
                        new LootCondition[]{new RandomChance(ConfigKP.ConfigKP.SHARDDROPRATE)}, new RandomValueRange(1),
                        new RandomValueRange(0), Reference.MOD_ID + "_witherskeleton");
                event.getTable().addPool(table);
            }
        }
    }
}