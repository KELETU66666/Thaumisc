package otherthaumcraft.huige233.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.huige233.otherthaumcraft.OtherThaumcraft;
import com.huige233.otherthaumcraft.common.items.ItemsOT;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.internal.CommonInternals;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.*;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class InitRecipes {
    private static ResourceLocation defaultGroup = new ResourceLocation("");
    
    public static void initRecipes(IForgeRegistry<IRecipe> forgeRegistry) {
        initArcaneRecipes();
        initCrucibleRecipes();
        initInfusionRecipes();
    }
  
private static void initArcaneRecipes() {
                ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(OtherThaumcraft.MODID, "warp_paper"), new ShapedArcaneRecipe(
                defaultGroup,
                "WARP_PAPER",
                30,
                new ItemStack(ItemsOT.warp_paper,1),
                new Object[] {
                        "PSP",
                        "   ",
                        "   ",
                        Character.valueOf('P'), new ItemStack(Items.PAPER),
                        Character.valueOf('S'), new ItemStack(ItemsTC.salisMundus),
                }
        ));
}
        
private static void initCrucibleRecipes() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(OtherThaumcraft.MODID, "taint_meat"), new CrucibleRecipe(
                "TAINT_MEAT",
                new ItemStack(ItemsOT.TAINT_MEAT),
                new ItemStack(Items.CHICKEN),
                new AspectList().add(Aspect.TAINT, 20).add(Aspect.PERMUTATIO, 10)
        ));}
private static void initInfusionRecipes() {
      ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(OtherThaumcraft.MODID, "cleansing_amulet"), new InfusionRecipe(
                "CLEANSING_AMULET",
                new ItemStack(ItemsOT.CLEANSING_AMULET),
                8,
                new AspectList().add(Aspect.MIND, 100).add(Aspect.ORDER, 75).add(Aspect.AURA, 50).add(Aspect.ENERGY, 50).add(Aspect.EXCHANGE, 30),
                new ItemStack(ItemsTC.amulet_vis),
                new Object[] {
                        new ItemStack(ItemsTC.SANITY_SOAP),
                        new ItemStack(ItemsTC.BATH_SALTS),
                        new ItemStack(ItemsTC.BATH_SALTS),
                        Ingredient.fromItem(ItemsTC.primordialPearl),
                }
        ));}
}
