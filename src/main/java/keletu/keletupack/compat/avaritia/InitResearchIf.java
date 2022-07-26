package keletu.keletupack.compat.avaritia;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;

public class InitResearchIf {

    public static void registerResearchMB() {

        ThaumcraftApi.registerResearchLocation(new ResourceLocation("keletupack", "research/researchif.json"));
    }
}
