package keletu.keletupack.init;


import com.google.common.collect.Maps;
import com.verdantartifice.thaumicwonders.common.items.ItemsTW;
import forestry.core.items.ItemForestry;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.util.Reference;
import magicbees.MagicBees;
import magicbees.api.module.MagicBeesModule;
import magicbees.bees.EnumBeeModifiers;
import magicbees.elec332.corerepack.item.IEnumItem;
import magicbees.elec332.corerepack.item.ItemEnumBased;
import magicbees.item.ItemMagicBeesFrame;
import magicbees.item.types.EnumResourceType;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

import java.util.Map;

import static magicbees.init.ItemRegister.magicFrame;
import static magicbees.init.ItemRegister.resourceItem;

public class InitRecipeCompatMB {
    private static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void InitRecipeCompat() {
        initCrucibleRecipes();
        initArcaneRecipes();
        initInfusionRecipes();
    }

    public static void preInit() {
        Map<EnumBeeModifiers, ItemMagicBeesFrame> framez = Maps.newHashMap();
        framez.put(EnumBeeModifiers.MAGIC, magicFrame = new ItemMagicBeesFrame(EnumBeeModifiers.MAGIC));
    }

    private static void initArcaneRecipes() {
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "essence_oblivion"), new ShapedArcaneRecipe(
                defaultGroup,
                "MB_EssenceOblivion",
                500,
                new AspectList(),
                getResource(EnumResourceType.ESSENCE_SCORNFUL_OBLIVION),
                "DO ",
                'D', new ItemStack(Blocks.DRAGON_EGG),
                'O', getResource(EnumResourceType.DIMENSIONAL_SINGULARITY)));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "frame_magic"), new ShapedArcaneRecipe(
                defaultGroup,
                "MB_FrameMagic",
                100,
                new AspectList(),
                new ItemStack(magicFrame),
                "SSS",
                "SFS",
                "SSS",
                'S', "stickWood",
                'F', new ItemStack(ItemsTC.fabric)));
    }
    private static void initCrucibleRecipes() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_life"), new CrucibleRecipe(
                "MB_EssenceLife",
                getResource(EnumResourceType.ESSENCE_FALSE_LIFE),
                new ItemStack(Blocks.RED_FLOWER),
                new AspectList().add(Aspect.PLANT, 15).add(Aspect.EXCHANGE, 15)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_unstable"), new CrucibleRecipe(
                "MB_EssenceUnstable",
                getResource(EnumResourceType.ESSENCE_FICKLE_PERMANENCE),
                new ItemStack(Blocks.NETHERRACK),
                new AspectList().add(Aspect.ENTROPY, 30).add(Aspect.EXCHANGE, 30)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_time"), new CrucibleRecipe(
                "MB_EssenceTime",
                getResource(EnumResourceType.ESSENCE_LOST_TIME),
                new ItemStack(Items.CLOCK),
                new AspectList().add(Aspect.ORDER, 40).add(Aspect.VOID, 40).add(Aspect.TRAP, 40)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_death"), new CrucibleRecipe(
                "MB_EssenceDeath",
                getResource(EnumResourceType.ESSENCE_SHALLOW_GRAVE),
                new ItemStack(Items.ROTTEN_FLESH),
                new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.DEATH, 15)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_armor"), new CrucibleRecipe(
                "MB_EssenceArmor",
                getResource(EnumResourceType.ESSENCE_EVERLASTING_DURABILITY),
                new ItemStack(Items.LEATHER),
                new AspectList().add(Aspect.EXCHANGE, 10).add(Aspect.PROTECT, 10)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "essence_time"), new CrucibleRecipe(
                "MB_EssenceTime",
                getResource(EnumResourceType.ESSENCE_LOST_TIME),
                new ItemStack(Items.CLOCK),
                new AspectList().add(Aspect.ORDER, 40).add(Aspect.VOID, 40).add(Aspect.TRAP, 40)
        ));
    }
    private static void initInfusionRecipes() {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "dimensional_singularity"), new InfusionRecipe(
                "MB_DimensionalSingularity",
                getResource(EnumResourceType.DIMENSIONAL_SINGULARITY),
                2,
                new AspectList().add(Aspect.ELDRITCH,20).add(Aspect.EXCHANGE, 40),
                "blockGold",
                new Object[]{
                        new ItemStack(Items.ENDER_PEARL),
                        new ItemStack(Items.ENDER_PEARL)
                }
        ));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Reference.MOD_ID, "dimensional_singularity"), new InfusionRecipe(
                "MB_DimensionalSingularity",
                getResource(EnumResourceType.DIMENSIONAL_SINGULARITY),
                2,
                new AspectList().add(Aspect.ELDRITCH,20).add(Aspect.EXCHANGE, 40),
                "blockGold",
                new Object[]{
                        new ItemStack(Items.ENDER_PEARL),
                        new ItemStack(Items.ENDER_PEARL)
                }
        ));
}

    private static ItemStack getResource(EnumResourceType resource) {
        return resourceItem.getStackFromType(resource);
    }
}
