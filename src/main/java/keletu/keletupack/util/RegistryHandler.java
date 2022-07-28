package keletu.keletupack.util;

import keletu.keletupack.ConfigKP;
import keletu.keletupack.blocks.tiles.TileBedrockPortal;
import keletu.keletupack.blocks.tiles.TileEtherealBloom;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.compat.thaumicwonders.LootBagCrystal;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.*;
import keletu.keletupack.items.baubles.*;
import keletu.keletupack.items.cheat.AkashicRecord;
import keletu.keletupack.items.cheat.BigPearl;
import keletu.keletupack.items.cheat.CrystalCaster;
import keletu.keletupack.items.resources.ResourceKP;
import keletu.keletupack.items.resources.ResourceTmisc;
import keletu.keletupack.keletupack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

@Mod.EventBusSubscriber

public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister( RegistryEvent.Register<Item> event ) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
        event.getRegistry().registerAll(new Cleansingamulet());
        event.getRegistry().registerAll(new warppaper());
        event.getRegistry().registerAll(new RunicRing());
        event.getRegistry().registerAll(new RunicGirdle());
        event.getRegistry().registerAll(new RunicAmulet());
        event.getRegistry().registerAll(new RunicRingWater());
        event.getRegistry().registerAll(new RunicGirdleAir());
        event.getRegistry().registerAll(new RunicAmuletEarth());
        event.getRegistry().registerAll(new ResourceTmisc());
        if(ConfigKP.ConfigKP.ENABLEPACKITEM){
            event.getRegistry().registerAll(new ResourceKP());
        }
        if(Loader.isModLoaded("thaumadditions") && Loader.isModLoaded("thaumicwonders") && ConfigKP.ConfigKP.ENABLECRYSTALBAG == true){
            event.getRegistry().registerAll(new LootBagCrystal("loot_bag_crystal", keletupack.ITEM_TAB));
        }
        if(Loader.isModLoaded("avaritia")){
            event.getRegistry().registerAll(new BigPearl());
            event.getRegistry().registerAll(new AkashicRecord());
            event.getRegistry().registerAll(new CrystalCaster());
        }
    }
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileBedrockPortal.register("bedrock_portal", TileBedrockPortal.class);
        TileBedrockPortal.register("ethereal_bloom", TileEtherealBloom.class);
    }

    @SubscribeEvent
    public static void onModelRegister( ModelRegistryEvent event )
    {
        for ( Item item : ModItems.ITEMS ) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
        for ( Item item : ModItems.ITEMS ) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }
        for (Block block: ModBlocks.BLOCKS)
        {
            if (block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }

    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        AspectEventProxy proxy = event.register;
        proxy.registerComplexObjectTag(new ItemStack(ItemsKP.RESOURCETMISC, 1, 1), new AspectList().add(Aspect.FIRE, 2).add(Aspect.UNDEAD, 2).add(Aspect.DEATH, 2));
        proxy.registerComplexObjectTag(new ItemStack(ItemsKP.RESOURCETMISC, 1, 0), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.DESIRE, 2).add(Aspect.DARKNESS, 2));
    }
}
