package keletu.keletupack.init;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;

public class InitResearch {

    public static void registerResearch() {

        ResearchCategories.registerCategory("THAUMISC", "UNLOCKINFUSION",
                new AspectList(),
                new ResourceLocation("keletupack", "textures/items/coin_magic.png"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_5.jpg"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("keletupack", "research/research.json"));

    }
}
