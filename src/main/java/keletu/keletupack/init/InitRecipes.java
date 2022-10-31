package keletu.keletupack.init;


import keletu.keletupack.blocks.BlockCrimsonPortalPlaceHolder;
import keletu.keletupack.common.BlocksKP;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.enchantments.EnchantmentsKP;
import keletu.keletupack.enchantments.inchantment.EnumInfusionEnchantmentKP;
import keletu.keletupack.enchantments.inchantment.InfusionEnchantmentRecipeKP;
import keletu.keletupack.items.armor.ShadowArmor;
import keletu.keletupack.util.ItemNBTHelper;
import keletu.keletupack.util.Reference;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.*;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.resources.ItemCrystalEssence;
import thaumcraft.common.lib.crafting.DustTriggerMultiblock;

public class InitRecipes {
    private static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void initRecipes() {
        initMultiblockRecipes();
        initArcaneRecipes();
        initCrucibleRecipes();
        initInfusionRecipes();
    }

    public static ItemStack taintCrystal(Aspect asp, int quantity){
        ItemStack crystal = new ItemStack(ItemsTC.crystalEssence, quantity);
        ((ItemCrystalEssence) ItemsTC.crystalEssence).setAspects(crystal, new AspectList().add(asp, 100));
        return crystal;
    }

    private static void initMultiblockRecipes() {
        initCrimsonCllaer();
    }

    private static void initCrimsonCllaer() {
        Part A = new Part(BlocksTC.stoneArcane, BlocksTC.stoneAncient);
        Part N = new Part(Blocks.RED_NETHER_BRICK, Blocks.NETHER_BRICK);
        Part O = new Part(Blocks.OBSIDIAN, BlocksTC.stoneEldritchTile);
        Part G = new Part(Blocks.GOLD_BLOCK, BlocksTC.amberBlock);
        Part S = new Part(BlocksTC.matrixSpeed, BlocksTC.stoneAncientGlyphed);
        Part C = new Part(BlocksTC.matrixCost, BlocksTC.stoneAncientGlyphed);
        Part V = new Part(BlocksTC.metalBlockVoid, ModBlocks.PLACE_HOLDER);
        Part[][][] CrimsonCallerBlueprint = {
                {
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, V, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                        { null, null, null, null, null, null, null, null, null ,null, null, null, null},
                },
                {
                        { null, null, null, A, null, null, null, null, null, A, null, null, null },
                        { null, null, A, N, O, null, null, null, O, N, A, null, null },
                        { null, A, G, N, N, O, A, O, N, N, G, A, null },
                        { A, N, N, G, N, N, A, N, N, G, N, N, A },
                        { null, O, N, N, G, O, O, O, G, N, N, O, null },
                        { null, null, O, N, O, C, S, C, O, N, O, null, null },
                        { null, null, A, A, O, S, O, S, O, A, A, null, null },
                        { null, null, O, N, O, C, S, C, O, N, O, null, null },
                        { null, O, N, N, G, O, O, O, G, N, N, O, null },
                        { A, N, N, G, N, N, A, N, N, G, N, N, A },
                        { null, A, G, N, N, O, A, O, N, N, G, A, null },
                        { null, null, A, N, O, null, null, null, O, N, A, null, null },
                        { null, null, null, A, null, null, null, null, null, A, null, null, null }
                }
        };
        IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("", CrimsonCallerBlueprint));
        ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation(Reference.MOD_ID, "crimson_caller"), new ThaumcraftApi.BluePrint(
                "",
                CrimsonCallerBlueprint,
                new ItemStack(Blocks.RED_NETHER_BRICK, 40),
                new ItemStack(Blocks.GOLD_BLOCK, 12),
                new ItemStack(BlocksTC.stoneArcane, 24),
                new ItemStack(BlocksTC.matrixCost, 4),
                new ItemStack(BlocksTC.matrixSpeed, 4),
                new ItemStack(BlocksTC.metalBlockVoid)));
    }
private static void initArcaneRecipes() {
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "warp_paper"), new ShapedArcaneRecipe(
                        defaultGroup,
                "WARP_PAPER",
                30,
                new AspectList(),
                new ItemStack(ItemsKP.WARP_PAPER,3,0),
                    "PSP",
                    'P', new ItemStack(Items.PAPER),
                    'S', new ItemStack(ItemsTC.salisMundus)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_cloth"), new ShapedArcaneRecipe(
                        defaultGroup,
            "ICHOR",
            750,
            new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10),
            new ItemStack(ItemsKP.RESOURCETMISC,3,4),
            "CCC",
            "III",
            "DDD",
            'C', new ItemStack(ItemsTC.fabric),
            'I', new ItemStack(ItemsKP.RESOURCETMISC,1,2),
            'D', new ItemStack(Items.DIAMOND)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_ingot"), new ShapedArcaneRecipe(
                  defaultGroup,
                  "ICHOR",
                    500,
                  new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5),
                        new ItemStack(ItemsKP.RESOURCETMISC,1,3),
                        " T ",
                        "IDI",
                        " I ",
                        'T', new ItemStack(ItemsTC.ingots, 1, 0),
                        'I', new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                        'D', new ItemStack(Items.DIAMOND)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_sword"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORIUMTOOLS",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHORIUM_SWORD),
            "I",
            "I",
            "S",
            'I', new ItemStack(ItemsKP.RESOURCETMISC,1,3),
            'S', new ItemStack(BlocksTC.logSilverwood)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_pick"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORIUMTOOLS",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHORIUM_PICK),
            "III",
            " S ",
            " S ",
            'I', new ItemStack(ItemsKP.RESOURCETMISC,1,3),
            'S', new ItemStack(BlocksTC.logSilverwood)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_shovel"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORIUMTOOLS",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHORIUM_SHOVEL),
            " I ",
            " S ",
            " S ",
            'I', new ItemStack(ItemsKP.RESOURCETMISC,1,3),
            'S', new ItemStack(BlocksTC.logSilverwood)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_axe"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORIUMTOOLS",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHORIUM_AXE),
            "II ",
            "IS ",
            " S ",
            'I', new ItemStack(ItemsKP.RESOURCETMISC,1,3),
            'S', new ItemStack(BlocksTC.logSilverwood)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_helm"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_HELM),
            "CCC",
            "C C",
            'C', new ItemStack(ItemsKP.RESOURCETMISC,1,4)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_chest"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_CHEST),
            "C C",
            "CCC",
            "CCC",
            'C', new ItemStack(ItemsKP.RESOURCETMISC,1,4)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_legs"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_LEGS),
            "CCC",
            "C C",
            "C C",
            'C', new ItemStack(ItemsKP.RESOURCETMISC,1,4)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_boots"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_BOOTS),
            "C C",
            "C C",
            'C', new ItemStack(ItemsKP.RESOURCETMISC,1,4)));
}

private static void initCrucibleRecipes() {
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_metal"), new CrucibleRecipe(
            "SHADOW_METAL",
            new ItemStack(ItemsKP.RESOURCETMISC,1,7),
            "nuggetIron",
            new AspectList().add(Aspect.DARKNESS, 15).add(Aspect.METAL, 30).add(Aspect.DARKNESS, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat1"), new CrucibleRecipe(
                "TAINT_MEAT",
                new ItemStack(ItemsKP.TAINT_MEAT),
                new ItemStack(Items.CHICKEN),
                new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
        ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat2"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsKP.TAINT_MEAT),
            new ItemStack(Items.PORKCHOP),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat3"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsKP.TAINT_MEAT),
            new ItemStack(Items.BEEF),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat4"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsKP.TAINT_MEAT),
            new ItemStack(Items.MUTTON),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat5"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsKP.TAINT_MEAT),
            new ItemStack(Items.RABBIT),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat6"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsKP.TAINT_MEAT),
            new ItemStack(Items.FISH),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "ethereal_bloom"), new CrucibleRecipe(
            "ETHEREAL_BLOOM",
            new ItemStack(ModBlocks.ETHEREAL_BLOOM),
            new ItemStack(BlocksTC.shimmerleaf),
            new AspectList().add(Aspect.PLANT, 40).add(Aspect.MAGIC, 40).add(Aspect.LIFE, 40).add(Aspect.FLUX, 25)
    ));}
private static void initInfusionRecipes() {
      ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "dirty_tear"), new InfusionRecipe(
                "DIRTY_TEAR",
                new ItemStack(ModItems.PureTear, 1, 1),
                4,
                new AspectList().add(Aspect.EXCHANGE, 60).add(Aspect.ELDRITCH, 60),
                new ItemStack(Items.NETHER_STAR),
                new Object[] {
                        new ItemStack(Items.GHAST_TEAR),
                        "gemQuartz",
                        "gemDiamond",
                        new ItemStack(ItemsTC.salisMundus),
                        new ItemStack(Items.GHAST_TEAR),
                        "gemQuartz",
                        "gemDiamond",
                        new ItemStack(ItemsTC.salisMundus)
                }
        ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "pure_tear"), new InfusionRecipe(
            "PURE_TEAR",
            new ItemStack(ModItems.PureTear, 1, 0),
            4,
            new AspectList().add(Aspect.EXCHANGE, 60).add(Aspect.ELDRITCH, 60),
            new ItemStack(Items.NETHER_STAR),
            new Object[] {
                    new ItemStack(ModItems.PureTear, 1, 1),
                    "gemQuartz",
                    "gemDiamond",
                    new ItemStack(ItemsTC.salisMundus),
                    new ItemStack(ModItems.PureTear, 1, 1),
                    "gemQuartz",
                    "gemDiamond",
                    new ItemStack(ItemsTC.salisMundus)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "cleansing_amulet"), new InfusionRecipe(
            "CLEANSING_AMULET",
            new ItemStack(ItemsKP.CLEANSING_AMULET),
            8,
            new AspectList().add(Aspect.EXCHANGE, 125).add(Aspect.ELDRITCH, 125).add(Aspect.MAGIC, 250),
            new ItemStack(ItemsTC.baubles, 1, 0),
            new Object[] {
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ModItems.PureTear, 1, 0),
                    "ingotGold",
                    "gemDiamond",
                    new ItemStack(ItemsTC.salisMundus),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ModItems.PureTear, 1, 0),
                    "ingotGold",
                    "gemDiamond",
                    new ItemStack(ItemsTC.salisMundus)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_block"), new InfusionRecipe(
            "ICHOR@1",
            new ItemStack(BlocksKP.ICHOR_BLOCK),
            8,
            new AspectList().add(Aspect.LIGHT, 125).add(Aspect.MAN, 125).add(Aspect.SOUL, 250),
            new ItemStack(Items.NETHER_STAR),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,0),
                    new ItemStack(Items.ENDER_EYE),
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "kami_helm"), new InfusionRecipe(
            "KAMIHELM",
            new ItemStack(ItemsKP.KAMI_HELM),
            40,
            new AspectList().add(Aspect.WATER, 150).add(Aspect.AURA, 125).add(Aspect.MIND, 60).add(Aspect.LIFE, 60).add(Aspect.LIGHT, 250).add(Aspect.PROTECT, 125),
                    new ItemStack(ItemsKP.ICHOR_HELM),
            new Object[]{
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.thaumonomicon),
                    new ItemStack(Items.CHORUS_FRUIT_POPPED),
                    new ItemStack(Items.GOLDEN_HELMET),
                    PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.NIGHT_VISION),
                    new ItemStack(ItemsTC.goggles),
                    new ItemStack(Items.GHAST_TEAR),
                    new ItemStack(Items.FISH),
                    new ItemStack(Items.CAKE),
                    new ItemStack(Items.ENDER_EYE)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "kami_chest"), new InfusionRecipe(
            "KAMICHEST",
            new ItemStack(ItemsKP.KAMI_CHEST),
            40,
            new AspectList().add(Aspect.AIR, 150).add(Aspect.PROTECT, 125).add(Aspect.FLIGHT, 125).add(Aspect.ORDER, 125).add(Aspect.LIGHT, 250).add(Aspect.ELDRITCH, 60),
            new ItemStack(ItemsKP.ICHOR_CHEST),
            new Object[]{
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.thaumonomicon),
                    new ItemStack(Items.CHORUS_FRUIT_POPPED),
                    new ItemStack(Items.GOLDEN_CHESTPLATE),
                    new ItemStack(ItemsTC.ringCloud),
                    new ItemStack(Items.ELYTRA),
                    new ItemStack(Items.SHIELD),
                    new ItemStack(Items.FEATHER),
                    new ItemStack(Items.GHAST_TEAR),
                    new ItemStack(Items.ARROW)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "kami_legs"), new InfusionRecipe(
            "KAMILEGS",
            new ItemStack(ItemsKP.KAMI_LEGS),
            40,
            new AspectList().add(Aspect.AIR, 150).add(Aspect.PROTECT, 125).add(Aspect.FLIGHT, 125).add(Aspect.ORDER, 125).add(Aspect.LIGHT, 250).add(Aspect.ELDRITCH, 60),
            new ItemStack(ItemsKP.ICHOR_LEGS),
            new Object[]{
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.thaumonomicon),
                    new ItemStack(Items.CHORUS_FRUIT_POPPED),
                    new ItemStack(Items.GOLDEN_CHESTPLATE),
                    PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.FIRE_RESISTANCE),
                    new ItemStack(ItemsTC.modules,1 ,1),
                    new ItemStack(BlocksTC.lampArcane),
                    new ItemStack(Items.LAVA_BUCKET),
                    new ItemStack(Items.FIRE_CHARGE),
                    new ItemStack(Items.BLAZE_ROD)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "kami_boots"), new InfusionRecipe(
            "KAMIBOOTS",
            new ItemStack(ItemsKP.KAMI_BOOTS),
            40,
            new AspectList().add(Aspect.EARTH, 150).add(Aspect.PROTECT, 125).add(Aspect.TOOL, 125).add(Aspect.MOTION, 125).add(Aspect.LIGHT, 250).add(Aspect.PLANT, 60).add(Aspect.FLIGHT, 60),
            new ItemStack(ItemsKP.ICHOR_BOOTS),
            new Object[]{
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.thaumonomicon),
                    new ItemStack(Items.CHORUS_FRUIT_POPPED),
                    new ItemStack(Items.GOLDEN_BOOTS),
                    new ItemStack(BlocksTC.grassAmbient),
                    new ItemStack(Items.WHEAT_SEEDS),
                    new ItemStack(BlocksTC.lampGrowth),
                    new ItemStack(ItemsTC.turretPlacer,1, 2),
                    new ItemStack(Blocks.WOOL),
                    new ItemStack(Items.LEAD)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_pick_adv"), new InfusionRecipe(
            "ICHOR_PICK_ADV",
            new ItemStack(ItemsKP.ICHORIUM_PICK_ADV),
            32,
            new AspectList().add(Aspect.FIRE, 150).add(Aspect.DESIRE, 60).add(Aspect.METAL, 125).add(Aspect.TOOL, 250).add(Aspect.SENSES, 60).add(Aspect.EARTH, 125),
            new ItemStack(ItemsKP.ICHORIUM_PICK),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,3),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.elementalPick),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(Blocks.TNT),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(ItemsTC.elementalPick),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,4)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_shovel_adv"), new InfusionRecipe(
            "ICHOR_SHOVEL_ADV",
            new ItemStack(ItemsKP.ICHORIUM_SHOVEL_ADV),
            32,
            new AspectList().add(Aspect.TOOL, 250).add(Aspect.SENSES, 60).add(Aspect.EARTH, 125).add(Aspect.TRAP, 60),
            new ItemStack(ItemsKP.ICHORIUM_SHOVEL),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,3),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.elementalShovel),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(Blocks.TNT),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(ItemsTC.elementalShovel),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,4)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_axe_adv"), new InfusionRecipe(
            "ICHOR_AXE_ADV",
            new ItemStack(ItemsKP.ICHORIUM_AXE_ADV),
            32,
            new AspectList().add(Aspect.WATER, 150).add(Aspect.PLANT, 125).add(Aspect.TOOL, 250).add(Aspect.SENSES, 60),
            new ItemStack(ItemsKP.ICHORIUM_AXE),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,3),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.elementalAxe),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(Blocks.TNT),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(ItemsTC.elementalAxe),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,4)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_sword_adv"), new InfusionRecipe(
            "ICHOR_SWORD_ADV",
            new ItemStack(ItemsKP.ICHORIUM_SWORD_ADV),
            32,
            new AspectList().add(Aspect.AIR, 150).add(Aspect.DESIRE, 250).add(Aspect.ORDER, 60).add(Aspect.ENERGY, 125).add(Aspect.CRYSTAL, 60).add(Aspect.SOUL, 125).add(Aspect.AVERSION, 125),
            new ItemStack(ItemsKP.ICHORIUM_SWORD),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,3),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,2),
                    new ItemStack(ItemsTC.elementalSword),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(Blocks.CACTUS),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.mechanismComplex),
                    new ItemStack(ItemsTC.elementalSword),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,4)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "morph_shovel"), new InfusionRecipe(
            "MORPH_TOOLS",
            new ItemStack(ItemsKP.MORPH_SHOVEL),
            1,
            new AspectList().add(Aspect.TOOL, 30).add(Aspect.SENSES, 30).add(Aspect.EXCHANGE, 30),
            new ItemStack(ItemsTC.thaumiumShovel),
            new Object[]{
                    new ItemStack(ItemsTC.quicksilver),
                    new ItemStack(ItemsTC.nuggets, 1, 10),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    new ItemStack(BlocksTC.logSilverwood)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "morph_axe"), new InfusionRecipe(
            "MORPH_TOOLS",
            new ItemStack(ItemsKP.MORPH_AXE),
            1,
            new AspectList().add(Aspect.TOOL, 15).add(Aspect.SENSES, 30).add(Aspect.EXCHANGE, 30).add(Aspect.AVERSION, 15),
            new ItemStack(ItemsTC.thaumiumAxe),
            new Object[]{
                    new ItemStack(ItemsTC.quicksilver),
                    new ItemStack(ItemsTC.nuggets, 1, 10),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    new ItemStack(BlocksTC.logSilverwood)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "morph_pick"), new InfusionRecipe(
            "MORPH_TOOLS",
            new ItemStack(ItemsKP.MORPH_PICK),
            1,
            new AspectList().add(Aspect.TOOL, 30).add(Aspect.SENSES, 30).add(Aspect.EXCHANGE, 30),
            new ItemStack(ItemsTC.thaumiumPick),
            new Object[]{
                    new ItemStack(ItemsTC.quicksilver),
                    new ItemStack(ItemsTC.nuggets, 1, 10),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    new ItemStack(BlocksTC.logSilverwood)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "morph_sword"), new InfusionRecipe(
            "MORPH_TOOLS",
            new ItemStack(ItemsKP.MORPH_SWORD),
            1,
            new AspectList().add(Aspect.AVERSION, 30).add(Aspect.SENSES, 30).add(Aspect.EXCHANGE, 30),
            new ItemStack(ItemsTC.thaumiumSword),
            new Object[]{
                    new ItemStack(ItemsTC.quicksilver),
                    new ItemStack(ItemsTC.nuggets, 1, 10),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EXCHANGE),
                    new ItemStack(BlocksTC.logSilverwood)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "distortion_pick"), new InfusionRecipe(
            "DISTORTION_PICK",
            new ItemStack(ItemsKP.DISTORTION_PICK),
            1,
            new AspectList().add(Aspect.ENTROPY, 30).add(Aspect.TOOL, 30).add(Aspect.FLUX, 45),
            new ItemStack(ItemsTC.thaumiumPick),
            new Object[]{
                    new ItemStack(ItemsTC.nuggets,1 ,10),
                    taintCrystal(Aspect.FLUX, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY),
                    new ItemStack(BlocksTC.logGreatwood)
            }
    ));
    ItemStack isRR = new ItemStack(ItemsKP.RUNIC_RING);
    ItemNBTHelper.setByte(isRR, "TC.RUNIC", (byte) 5);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_ring"), new InfusionRecipe(
            "RUNIC_BAUBLES",
            isRR,
            2,
            new AspectList().add(Aspect.ENERGY, 40).add(Aspect.MAGIC, 40).add(Aspect.PROTECT, 20),
            new ItemStack(ItemsTC.baubles, 1, 1),
            new Object[]{
                    new ItemStack(ItemsTC.scribingTools),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.morphicResonator),
                    new ItemStack(ItemsTC.visResonator),
                    new ItemStack(ItemsTC.salisMundus)
            }
    ));
    ItemStack isRA = new ItemStack(ItemsKP.RUNIC_AMULET);
    ItemNBTHelper.setByte(isRA, "TC.RUNIC", (byte) 8);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_amulet"), new InfusionRecipe(
            "RUNIC_BAUBLES",
            isRA,
            3,
            new AspectList().add(Aspect.ENERGY, 50).add(Aspect.MAGIC, 45).add(Aspect.PROTECT, 25),
            new ItemStack(ItemsTC.baubles, 1, 0),
            new Object[]{
                    new ItemStack(ItemsTC.scribingTools),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.morphicResonator),
                    new ItemStack(ItemsTC.visResonator),
                    new ItemStack(ItemsTC.salisMundus)
            }
    ));
    ItemStack isRG = new ItemStack(ItemsKP.RUNIC_GIRDLE);
    ItemNBTHelper.setByte(isRG, "TC.RUNIC", (byte) 10);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_girdle"), new InfusionRecipe(
            "RUNIC_BAUBLES",
            isRG,
            4,
            new AspectList().add(Aspect.ENERGY, 60).add(Aspect.MAGIC, 50).add(Aspect.PROTECT, 30),
            new ItemStack(ItemsTC.baubles, 1, 2),
            new Object[]{
                    new ItemStack(ItemsTC.scribingTools),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.amber),
                    new ItemStack(ItemsTC.morphicResonator),
                    new ItemStack(ItemsTC.fabric),
                    new ItemStack(ItemsTC.visResonator),
                    new ItemStack(ItemsTC.salisMundus)
            }
    ));
    ItemStack isRRA = new ItemStack(ItemsKP.RUNIC_RING_WATER);
    ItemNBTHelper.setByte(isRRA, "TC.RUNIC", (byte) 4);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_ring_water"), new InfusionRecipe(
            "RUNIC_BAUBLES_AQUA",
            isRRA,
            4,
            new AspectList().add(Aspect.WATER, 75).add(Aspect.MAGIC, 45).add(Aspect.LIFE, 70).add(Aspect.PROTECT, 30),
            isRR,
            new Object[]{
                    ThaumcraftApiHelper.makeCrystal(Aspect.WATER),
                    ThaumcraftApiHelper.makeCrystal(Aspect.WATER),
                    new ItemStack(ItemsTC.visResonator),
                    ThaumcraftApiHelper.makeCrystal(Aspect.WATER),
                    ThaumcraftApiHelper.makeCrystal(Aspect.WATER),
                    PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.LONG_REGENERATION)
            }
    ));
    ItemStack isRAE = new ItemStack(ItemsKP.RUNIC_AMULET_EARTH);
    ItemNBTHelper.setByte(isRAE, "TC.RUNIC", (byte) 7);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_amulet_earth"), new InfusionRecipe(
            "RUNIC_BAUBLES_TERRA",
            isRAE,
            5,
            new AspectList().add(Aspect.WATER, 45).add(Aspect.MAGIC, 70).add(Aspect.VOID, 70).add(Aspect.PROTECT, 35),
            isRA,
            new Object[]{
                    ThaumcraftApiHelper.makeCrystal(Aspect.EARTH),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EARTH),
                    new ItemStack(ItemsTC.visResonator),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EARTH),
                    ThaumcraftApiHelper.makeCrystal(Aspect.EARTH),
                    PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.STRONG_STRENGTH)
            }
    ));
    ItemStack isRGA = new ItemStack(ItemsKP.RUNIC_GIRDLE_AIR);
    ItemNBTHelper.setByte(isRGA, "TC.RUNIC", (byte) 9);
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "runic_girdle_air"), new InfusionRecipe(
            "RUNIC_BAUBLES_AER",
            isRGA,
            6,
            new AspectList().add(Aspect.AIR, 125).add(Aspect.MAGIC, 125).add(Aspect.PROTECT, 50),
            isRG,
            new Object[]{
                    ThaumcraftApiHelper.makeCrystal(Aspect.AIR),
                    ThaumcraftApiHelper.makeCrystal(Aspect.AIR),
                    new ItemStack(ItemsTC.visResonator),
                    ThaumcraftApiHelper.makeCrystal(Aspect.AIR),
                    ThaumcraftApiHelper.makeCrystal(Aspect.AIR),
                    PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.STRONG_HARMING)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_fortress_helm"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new ItemStack(ModItems.ShadowHelm),
            16,
            new AspectList().add(Aspect.METAL, 100).add(Aspect.PROTECT, 80).add(Aspect.MAGIC, 45).add(Aspect.DARKNESS, 120).add(Aspect.VOID, 85),
            new ItemStack(ItemsTC.voidHelm),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    "ingotIron",
                    "ingotIron",
                    "gemEmerald"
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_fortress_chest"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new ItemStack(ModItems.ShadowChest),
            16,
            new AspectList().add(Aspect.METAL, 150).add(Aspect.PROTECT, 100).add(Aspect.MAGIC, 70).add(Aspect.DARKNESS, 150).add(Aspect.VOID, 100),
            new ItemStack(ItemsTC.voidChest),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    "ingotIron",
                    "leather"
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_fortress_legs"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new ItemStack(ModItems.ShadowLegs),
            16,
            new AspectList().add(Aspect.METAL, 125).add(Aspect.PROTECT, 90).add(Aspect.MAGIC, 65).add(Aspect.DARKNESS, 125).add(Aspect.VOID, 90),
            new ItemStack(ItemsTC.voidLegs),
            new Object[]{
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    new ItemStack(ItemsKP.RESOURCETMISC,1,6),
                    "ingotIron",
                    "leather"
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "arcane_disassembler"), new InfusionRecipe(
            "ARCANE_DISASSEMBLER",
            new ItemStack(ModItems.ARCANEDISASSEMBLER),
            6,
            new AspectList().add(Aspect.TOOL, 95).add(Aspect.MECHANISM, 50).add(Aspect.METAL, 50).add(Aspect.DARKNESS, 50),
            new ItemStack(BlocksTC.metalBlockVoid),
            new Object[]{
                    Ingredient.fromItem(ItemsTC.primordialPearl),
                    new ItemStack(ItemsTC.voidAxe),
                    new ItemStack(ItemsTC.ingots, 1, 0),
                    new ItemStack(ItemsTC.voidPick),
                    new ItemStack(ItemsTC.ingots, 1, 0),
                    new ItemStack(ItemsTC.voidSword),
                    new ItemStack(ItemsTC.ingots, 1, 0),
                    new ItemStack(ItemsTC.voidShovel)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_helm_goggles"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new Object[] { "goggles", new NBTTagByte((byte)1) },
            5,
            (new AspectList()).add(Aspect.SENSES, 40).add(Aspect.AURA, 20).add(Aspect.PROTECT, 20),
            new ItemStack(ModItems.ShadowHelm, 1, 32767),
            new Object[]{
                    new ItemStack(Items.SLIME_BALL),
                    new ItemStack(ItemsTC.goggles, 1, 32767)
            }
            ));

    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_helm_warp"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new Object[] { "mask", new NBTTagInt(0) },
            8,
            new AspectList().add(Aspect.MIND, 80).add(Aspect.LIFE, 80).add(Aspect.PROTECT, 20),
            new ItemStack(ModItems.ShadowHelm, 1, 32767),
            new Object[] {
                    "plateIron",
                    "dyeBlack",
                    "plateIron",
                    "leather",
                    BlocksTC.shimmerleaf,
                    ItemsTC.brain
    }
    ));

    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_helm_wither"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new Object[] { "mask", new NBTTagInt(1) },
            8,
            new AspectList().add(Aspect.ENTROPY, 80).add(Aspect.DEATH, 80).add(Aspect.PROTECT, 20),
            new ItemStack(ModItems.ShadowHelm, 1, 32767),
            new Object[] {
                    "plateIron",
                    "dyeWhite",
                    "plateIron",
                    "leather",
                    Items.POISONOUS_POTATO,
                    new ItemStack(Items.SKULL, 1, 1)
    }
    ));

    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "shadow_helm_lifesteal"), new InfusionRecipe(
            "SHADOW_FORTRESS_ARMOR",
            new Object[] { "mask", new NBTTagInt(2) },
            8,
            new AspectList().add(Aspect.UNDEAD, 80).add(Aspect.LIFE, 80).add(Aspect.PROTECT, 20),
            new ItemStack(ModItems.ShadowHelm, 1, 32767),
            new Object[] {
                    "plateIron",
                    "dyeRed",
                    "plateIron",
                    "leather",
                    Items.GHAST_TEAR,
                    Items.MILK_BUCKET
    }
    ));
    ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
    ItemEnchantedBook.addEnchantment(book, new EnchantmentData(EnchantmentsKP.voidtouched, 1));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "bookofvoid"), new InfusionRecipe(
            "MORPH_TOOLS&&BASEELDRITCH",
            book,
            8,
            new AspectList().add(Aspect.ENTROPY, 70),
            new ItemStack(Items.BOOK),
            new Object[] {
                    "ingotVoid",
                    "ingotVoid",
                    "ingotVoid",
                    "ingotVoid"
            }
    ));
    
    ItemStack cpower = new ItemStack(ItemsTC.voidSword);
    EnumInfusionEnchantmentKP.addInfusionEnchantment(cpower, EnumInfusionEnchantmentKP.CRIMSONPOWER, 1);
    InfusionEnchantmentRecipeKP IECPOWER = new InfusionEnchantmentRecipeKP(EnumInfusionEnchantmentKP.CRIMSONPOWER, (new AspectList()).add(Aspect.FLUX, 1000), new IngredientNBTTC(new ItemStack(BlocksTC.crystalTaint)), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint), new ItemStack(BlocksTC.crystalTaint));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("keletupack:IECPOWER"), (InfusionRecipe)IECPOWER);
    ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("keletupack:IECPOWERFAKE"), new InfusionEnchantmentRecipeKP(IECPOWER, cpower));

}
}
