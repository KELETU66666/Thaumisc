package keletu.keletupack.init;


import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import keletu.keletupack.common.ItemsOT;
import keletu.keletupack.util.Reference;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

public class InitRecipes {
    private static ResourceLocation defaultGroup = new ResourceLocation("");
    
    public static void initRecipes() {
        initArcaneRecipes();
        initCrucibleRecipes();
        initInfusionRecipes();
    }
  
private static void initArcaneRecipes() {
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "warp_paper"), new ShapedArcaneRecipe(
                        defaultGroup,
                "WARP_PAPER",
                30,
                new AspectList(),
                new ItemStack(ItemsOT.WARP_PAPER,3,0),
                    "PSP",
                    "   ",
                    "   ",
                    'P', new ItemStack(Items.PAPER),
                    'S', new ItemStack(ItemsTC.salisMundus)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_cloth"), new ShapedArcaneRecipe(
                        defaultGroup,
            "ICHOR_CLOTH",
            750,
            new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10).add(Aspect.EARTH, 10).add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10),
            new ItemStack(ItemsOT.ICHOR_CLOTH,3,0),
            "CCC",
            "III",
            "DDD",
            'C', new ItemStack(ItemsTC.fabric),
            'I', new ItemStack(ItemsOT.ICHOR),
            'D', new ItemStack(Items.DIAMOND)));
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "ichor_ingot"), new ShapedArcaneRecipe(
            defaultGroup,
            "ICHOR_INGOT",
            500,
            new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ORDER, 5).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5),
            new ItemStack(ItemsOT.ICHOR_INGOT),
            " T ",
            "IDI",
            " I ",
            'T', new ItemStack(ItemsTC.ingots, 1, 0),
            'I', new ItemStack(ItemsOT.ICHOR),
            'D', new ItemStack(Items.DIAMOND)));
}
        
private static void initCrucibleRecipes() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat1"), new CrucibleRecipe(
                "TAINT_MEAT",
                new ItemStack(ItemsOT.TAINT_MEAT),
                new ItemStack(Items.CHICKEN),
                new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
        ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat2"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsOT.TAINT_MEAT),
            new ItemStack(Items.PORKCHOP),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat3"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsOT.TAINT_MEAT),
            new ItemStack(Items.BEEF),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat4"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsOT.TAINT_MEAT),
            new ItemStack(Items.MUTTON),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat5"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsOT.TAINT_MEAT),
            new ItemStack(Items.RABBIT),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));
    ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "taint_meat6"), new CrucibleRecipe(
            "TAINT_MEAT",
            new ItemStack(ItemsOT.TAINT_MEAT),
            new ItemStack(Items.FISH),
            new AspectList().add(Aspect.FLUX, 20).add(Aspect.EXCHANGE, 10)
    ));}
private static void initInfusionRecipes() {
      ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "cleansing_amulet"), new InfusionRecipe(
                "CLEANSING_AMULET",
                new ItemStack(ItemsOT.CLEANSING_AMULET),
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
            "ICHOR",
            new ItemStack(ItemsOT.ICHOR),
            8,
            new AspectList().add(Aspect.LIGHT, 125).add(Aspect.MAN, 125).add(Aspect.SOUL, 250),
            new ItemStack(Items.NETHER_STAR),
            new Object[] {
                    new ItemStack(ItemsOT.SHARD_NETHER),
                    new ItemStack(Items.DIAMOND),
                    new ItemStack(ItemsOT.SHARD_END),
                    new ItemStack(Items.ENDER_EYE),
            }
    ));}
}
