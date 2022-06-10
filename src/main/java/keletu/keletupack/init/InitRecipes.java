package keletu.keletupack.init;


import keletu.keletupack.common.BlocksKP;
import keletu.keletupack.keletupack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.util.Reference;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemGenericEssentiaContainer;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.items.resources.ItemCrystalEssence;

public class InitRecipes {
    private static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void initRecipes() {
        initArcaneRecipes();
        initCrucibleRecipes();
        initInfusionRecipes();
    }

    public static ItemStack getCrystal(Aspect asp, int quantity){
        ItemStack crystal = new ItemStack(ItemsTC.crystalEssence, quantity);
        ((ItemCrystalEssence) ItemsTC.crystalEssence).setAspects(crystal, new AspectList().add(asp, 100));
        return crystal;
    }

private static void initArcaneRecipes() {
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "warp_paper"), new ShapedArcaneRecipe(
                        defaultGroup,
                "WARP_PAPER",
                30,
                new AspectList(),
                new ItemStack(ItemsKP.WARP_PAPER,3,0),
                    "PSP",
                    "   ",
                    "   ",
                    'P', new ItemStack(Items.PAPER),
                    'S', new ItemStack(ItemsTC.salisMundus)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_cloth"), new ShapedArcaneRecipe(
                        defaultGroup,
            "ICHOR",
            750,
            new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10),
            new ItemStack(ItemsKP.ICHOR_CLOTH,3,0),
            "CCC",
            "III",
            "DDD",
            'C', new ItemStack(ItemsTC.fabric),
            'I', new ItemStack(ItemsKP.ICHOR),
            'D', new ItemStack(Items.DIAMOND)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_ingot"), new ShapedArcaneRecipe(
                  defaultGroup,
                  "ICHOR",
                    500,
                  new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5),
                   new ItemStack(ItemsKP.ICHOR_INGOT),
                        " T ",
                        "IDI",
                        " I ",
                        'T', new ItemStack(ItemsTC.ingots, 1, 0),
                        'I', new ItemStack(ItemsKP.ICHOR),
                        'D', new ItemStack(Items.DIAMOND)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_sword"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORIUMTOOLS",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHORIUM_SWORD),
            " I ",
            " I ",
            " S ",
            'I', new ItemStack(ItemsKP.ICHOR_INGOT),
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
            'I', new ItemStack(ItemsKP.ICHOR_INGOT),
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
            'I', new ItemStack(ItemsKP.ICHOR_INGOT),
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
            'I', new ItemStack(ItemsKP.ICHOR_INGOT),
            'S', new ItemStack(BlocksTC.logSilverwood)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_helm"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_HELM),
            "CCC",
            "C C",
            "   ",
            'C', new ItemStack(ItemsKP.ICHOR_CLOTH)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_chest"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_CHEST),
            "C C",
            "CCC",
            "CCC",
            'C', new ItemStack(ItemsKP.ICHOR_CLOTH)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_legs"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_LEGS),
            "CCC",
            "C C",
            "C C",
            'C', new ItemStack(ItemsKP.ICHOR_CLOTH)));
    ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_boots"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHORARMOR",
            150,
            new AspectList().add(Aspect.AIR, 3).add(Aspect.WATER, 3).add(Aspect.ORDER, 3).add(Aspect.EARTH, 3).add(Aspect.FIRE, 3).add(Aspect.ENTROPY, 3),
            new ItemStack(ItemsKP.ICHOR_BOOTS),
            "C C",
            "C C",
            "   ",
            'C', new ItemStack(ItemsKP.ICHOR_CLOTH)));
}
        
private static void initCrucibleRecipes() {
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
    ));}
private static void initInfusionRecipes() {
      ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "cleansing_amulet"), new InfusionRecipe(
                "CLEANSING_AMULET",
                new ItemStack(ItemsKP.CLEANSING_AMULET),
                8,
                new AspectList().add(Aspect.MIND, 100).add(Aspect.ORDER, 75).add(Aspect.AURA, 50).add(Aspect.ENERGY, 50).add(Aspect.EXCHANGE, 30),
                new ItemStack(ItemsTC.amuletVis),
                new Object[] {
                        new ItemStack(ItemsTC.bathSalts),
                        new ItemStack(ItemsTC.sanitySoap),
                        new ItemStack(ItemsTC.bathSalts),
                        Ingredient.fromItem(ItemsTC.primordialPearl),
                }
        ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_block"), new InfusionRecipe(
            "ICHOR@1",
            new ItemStack(BlocksKP.ICHOR_BLOCK),
            8,
            new AspectList().add(Aspect.LIGHT, 125).add(Aspect.MAN, 125).add(Aspect.SOUL, 250),
            new ItemStack(Items.NETHER_STAR),
            new Object[]{
                    new ItemStack(ItemsKP.SHARD_NETHER),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsKP.SHARD_END),
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
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR),
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
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR),
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
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR),
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
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR),
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
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR_INGOT),
                    new ItemStack(ItemsTC.elementalPick),
                    new ItemStack(ItemsTC.morphicResonator),
                    new ItemStack(Blocks.TNT),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.visResonator),
                    new ItemStack(ItemsTC.elementalPick),
                    new ItemStack(ItemsKP.ICHOR_CLOTH)
            }
    ));
    ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichorium_shovel_adv"), new InfusionRecipe(
            "ICHOR_SHOVEL_ADV",
            new ItemStack(ItemsKP.ICHORIUM_SHOVEL_ADV),
            32,
            new AspectList().add(Aspect.TOOL, 250).add(Aspect.SENSES, 60).add(Aspect.EARTH, 125).add(Aspect.TRAP, 60),
            new ItemStack(ItemsKP.ICHORIUM_SHOVEL),
            new Object[]{
                    new ItemStack(ItemsKP.ICHOR),
                    new ItemStack(ItemsKP.ICHOR_INGOT),
                    new ItemStack(ItemsTC.elementalShovel),
                    new ItemStack(ItemsTC.morphicResonator),
                    new ItemStack(Blocks.TNT),
                    new ItemStack(ItemsTC.clusters, 1, 6),
                    new ItemStack(ItemsTC.clusters, 1, 0),
                    new ItemStack(ItemsTC.clusters, 1, 1),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsTC.visResonator),
                    new ItemStack(ItemsTC.elementalShovel),
                    new ItemStack(ItemsKP.ICHOR_CLOTH)
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
                    getCrystal(Aspect.FLUX, 1),
                    ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY),
                    new ItemStack(BlocksTC.logGreatwood)
            }
    ));
    }
}
