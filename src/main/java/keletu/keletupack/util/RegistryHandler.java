package keletu.keletupack.util;

import keletu.keletupack.blocks.tiles.TileBedrockPortal;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.init.ModBlocks;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.*;
import keletu.keletupack.items.baubles.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
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
        event.getRegistry().registerAll(new coin_witchery());
        event.getRegistry().registerAll(new coin_adventure());
        event.getRegistry().registerAll(new coin_bleed());
        event.getRegistry().registerAll(new coin_magic());
        event.getRegistry().registerAll(new EnderShard());
        event.getRegistry().registerAll(new NetherShard());
        event.getRegistry().registerAll(new Ichor());
        event.getRegistry().registerAll(new IchorCloth());
        event.getRegistry().registerAll(new IchorIngot());
        event.getRegistry().registerAll(new IchorPouch());
        event.getRegistry().registerAll(new RunicRing());
        event.getRegistry().registerAll(new RunicGirdle());
        event.getRegistry().registerAll(new RunicAmulet());
        event.getRegistry().registerAll(new RunicRingWater());
        event.getRegistry().registerAll(new RunicGirdleAir());
        event.getRegistry().registerAll(new RunicAmuletEarth());
        event.getRegistry().registerAll(new ShadowIngot());
        event.getRegistry().registerAll(new ShadowNugget());
    }
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileBedrockPortal.register("bedrock_portal", TileBedrockPortal.class);
    }

    @SubscribeEvent
    public static void onModelRegister( ModelRegistryEvent event )
    {
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
        proxy.registerComplexObjectTag(new ItemStack(ItemsKP.SHARD_NETHER), new AspectList().add(Aspect.FIRE, 2).add(Aspect.UNDEAD, 2).add(Aspect.DEATH, 2));
        proxy.registerComplexObjectTag(new ItemStack(ItemsKP.SHARD_END), new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.DESIRE, 2).add(Aspect.DARKNESS, 2));
    }
}
