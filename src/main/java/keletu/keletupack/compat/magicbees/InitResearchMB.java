package keletu.keletupack.compat.magicbees;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;

public class InitResearchMB {

    public static void registerResearchMB() {

        ThaumcraftApi.registerResearchLocation(new ResourceLocation("keletupack", "research/researchmb.json"));
        ResearchCategories.registerCategory("MAGICBEES", "UNLOCKARTIFICE",
                new AspectList(),
                new ResourceLocation("magicbees", "textures/items/beezard.png"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_3.jpg"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
    }
}
