package keletu.keletupack;

import keletu.keletupack.compat.Tconstruct.TConstructHandler;
import keletu.keletupack.compat.avaritia.InitRecipeAvaritia;
import keletu.keletupack.compat.avaritia.InitResearchIf;
import keletu.keletupack.compat.magicbees.InitRecipeCompatMB;
import keletu.keletupack.compat.magicbees.InitResearchMB;
import keletu.keletupack.compat.thaumicwonders.InitRecipeCompatTW;
import keletu.keletupack.enchantments.EnchantmentsKP;
import keletu.keletupack.entity.PassiveCreeper;
import keletu.keletupack.entity.ThaumoobCaster;
import keletu.keletupack.init.*;
import keletu.keletupack.loot.LootTableHandler;
import keletu.keletupack.proxy.CommonProxy;
import keletu.keletupack.research.theorycraft.CardLearnFoolish;
import keletu.keletupack.research.theorycraft.CardLearnFoolish1;
import keletu.keletupack.structure.StructureNoobHouse;
import keletu.keletupack.util.Reference;
import keletu.keletupack.util.handler.RenderHandler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.api.research.theorycraft.TheorycraftManager;

@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION
)
public class keletupack {

    public static CreativeTabs ITEM_TAB = new ItemTabKP();
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;
    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(Reference.MOD_ID)
    public static keletupack INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        RenderHandler.registerEntityRenders();
        GameRegistry.registerWorldGenerator(new StructureNoobHouse(), 1000);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "passive_creeper"), PassiveCreeper.class, "creeperPassive", 0, this, 160, 4, true);
        //EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "ThaumKnight"), EntityCultistKnight.class, "CultistKnight", id++, Thaumcraft.instance, 64, 3, true, 9438728, 128);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" +  "thaumaturge_speller"), ThaumoobCaster.class, "ThaumaturgeSpeller", 1, this, 64, 3, true, 9438728, 8388608);
        if(Loader.isModLoaded("tconstruct")) {
            TConstructHandler.preInit(event);
        }
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        InitRecipes.initRecipes();
        InitResearch.registerResearch();
        MinecraftForge.EVENT_BUS.register(LootTableHandler.class);
        if(Loader.isModLoaded("thaumicwonders")) {
            InitRecipeCompatTW.InitRecipeCompat();
        }
        if(Loader.isModLoaded("magicbees")) {
            InitRecipeCompatMB.InitRecipeCompat();
            InitResearchMB.registerResearchMB();
        }
        if(Loader.isModLoaded("avaritia")) {
            InitRecipeAvaritia.InitRecipeCompat();
            InitResearchIf.registerResearchIf();
        }
        TheorycraftManager.registerCard(CardLearnFoolish.class);
        TheorycraftManager.registerCard(CardLearnFoolish1.class);
    proxy.registerDisplayInformationInit();
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    /**
     * Forge will automatically look up and bind blocks to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(Reference.MOD_ID)
    public static class Blocks {
      /*
          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below
      */
    }

    /**
     * Forge will automatically look up and bind items to the fields in this class
     * based on their registry name.
     */
    @GameRegistry.ObjectHolder(Reference.MOD_ID)
    public static class Items {
      /*
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
      */
    }

    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
       /** Listen for the register event for creating custom items */
       @SubscribeEvent
       public static void addItems(RegistryEvent.Register<Item> event) {

           /*
             event.getRegistry().register(new ItemBlock(Blocks.myBlock).setRegistryName(MOD_ID, "myBlock"));
             event.getRegistry().register(new MySpecialItem().setRegistryName(MOD_ID, "mySpecialItem"));
            */
       }
       /** Listen for the register event for creating custom blocks */
       @SubscribeEvent
       public static void addBlocks(RegistryEvent.Register<Block> event) {
       }
    }
    }
    /* EXAMPLE ITEM AND BLOCK - you probably want these in separate files
    public static class MySpecialItem extends Item {

    }

    public static class MySpecialBlock extends Block {

    }
    */
