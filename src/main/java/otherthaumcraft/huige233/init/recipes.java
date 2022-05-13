package com.otherthaumcraft.huige233.common.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.huige233.otherthaumcraft.OtherThaumcraft;
import com.huige233.otherthaumcraft.common.items.ItemsOT;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IDustTrigger;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.IngredientNBTTC;
import thaumcraft.api.crafting.Part;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

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
        
private static void initCrucibleRecipes() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(OtherThaumcraft.MODID, "taint_meat"), new CrucibleRecipe(
                "TAINT_MEAT",
                new ItemStack(ItemsOT.TAINT_MEAT),
                new ItemStack(Items.CHICKEN),
                new AspectList().add(Aspect.TAINT, 20).add(Aspect.PERMUTATIO, 10)
        ));
        
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
        ));
