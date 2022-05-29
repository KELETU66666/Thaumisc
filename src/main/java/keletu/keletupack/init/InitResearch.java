package keletu.keletupack.init;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;

public class InitResearch {

    public static void registerResearch() {
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("keletupack", "research/research.json"));

    }
}
