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
        ));}
}
