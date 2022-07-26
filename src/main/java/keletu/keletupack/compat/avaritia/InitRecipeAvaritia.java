package keletu.keletupack.compat.avaritia;

import com.google.common.collect.Maps;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.util.Reference;
import magicbees.bees.EnumBeeModifiers;
import magicbees.item.ItemMagicBeesFrame;
import magicbees.item.types.EnumResourceType;
import morph.avaritia.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

import java.util.Map;

import static magicbees.init.ItemRegister.magicFrame;

public class InitRecipeAvaritia {
    private static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void InitRecipeCompat() {
        compatInfusionRecipe();
    }

    private static void compatInfusionRecipe() {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "akashicrecord"), new InfusionRecipe(
                "AKASHIC_RECORD",
                new ItemStack(ItemsKP.AKASHIC_RECORD),
                20,
                new AspectList().add(Aspect.MIND, 250).add(Aspect.MAGIC, 250),
                new ItemStack(ItemsTC.curio, 1, 4),
                new Object[]{
                        ModItems.infinity_ingot,
                        ModItems.infinity_ingot,
                        ModItems.infinity_ingot,
                        ModItems.infinity_ingot
                }
        ));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "big_pearl"), new InfusionRecipe(
                "AKASHIC_RECORD",
                new ItemStack(ItemsKP.BIG_PEARL),
                20,
                new AspectList().add(Aspect.AIR, 250).add(Aspect.ELDRITCH, 250).add(Aspect.WATER, 250).add(Aspect.EARTH, 250).add(Aspect.FIRE, 250).add(Aspect.ORDER, 250).add(Aspect.ENTROPY, 250).add(Aspect.MAGIC, 250),
                new ItemStack(ItemsTC.primordialPearl),
                new Object[]{
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl),
                        new ItemStack(ItemsTC.primordialPearl)
                }
        ));
    }
}