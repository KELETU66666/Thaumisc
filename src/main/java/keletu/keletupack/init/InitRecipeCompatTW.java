package keletu.keletupack.init;


import com.verdantartifice.thaumicwonders.common.items.ItemsTW;
import keletu.keletupack.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Optional;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;

public class InitRecipeCompatTW {
    private static ResourceLocation defaultGroup = new ResourceLocation("");

    public static void InitRecipeCompat() {
        initCrucibleRecipes();
    }

    private static void initCrucibleRecipes() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "shimmerleaf_seed_duplicate"), new CrucibleRecipe(
                "TWOND_MYSTIC_GARDENING",
                new ItemStack(ItemsTW.SHIMMERLEAF_SEED, 2),
                new ItemStack(ItemsTW.SHIMMERLEAF_SEED),
                new AspectList().add(Aspect.PLANT, 5).add(Aspect.AURA, 5)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "cinderpearl_seed_duplicate"), new CrucibleRecipe(
                "TWOND_MYSTIC_GARDENING",
                new ItemStack(ItemsTW.CINDERPEARL_SEED, 2),
                new ItemStack(ItemsTW.CINDERPEARL_SEED),
                new AspectList().add(Aspect.PLANT, 5).add(Aspect.FIRE, 5)
        ));
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(Reference.MOD_ID, "vishroom_spore_duplicate"), new CrucibleRecipe(
                "TWOND_MYSTIC_GARDENING",
                new ItemStack(ItemsTW.VISHROOM_SPORE, 2),
                new ItemStack(ItemsTW.VISHROOM_SPORE),
                new AspectList().add(Aspect.PLANT, 5).add(Aspect.MAGIC, 5)
        ));
    }
}
