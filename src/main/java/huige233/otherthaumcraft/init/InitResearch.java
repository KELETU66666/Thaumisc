package huige233.otherthaumcraft.init;

import huige233.otherthaumcraft.OtherThaumcraft;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.items.ItemsTC;

public class InitResearch {

	public static void registerResearch() {
		ThaumcraftApi.registerResearchLocation(new ResourceLocation("research/research"));
    
  }
}
