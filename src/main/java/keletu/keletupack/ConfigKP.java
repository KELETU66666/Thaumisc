package keletu.keletupack;

import keletu.keletupack.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Config(modid = Reference.MOD_ID, category = "")
public class ConfigKP {


    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    private static class EventHandler {

        private EventHandler() {
        }

        public static ArrayList<String> trash = new ArrayList<String>();

        String trashlist = "dirt;sand;gravel;cobblestone;netherrack";
        String[] trashpile = trashlist.split(";");{
            for(String garbage : trashpile) {
                trash.add(garbage);
            }
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MOD_ID)) {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

    @Config.LangKey("keletupack.configkp.langkey")
    @Config.Comment("keletupack.configkp.comment")
    public static final configkp ConfigKP = new configkp();

    public static class configkp {
        @Config.LangKey("keletupack.configkp.bedrockworddim")
        @Config.Comment("keletupack.configkp.bedrockworddim.comment")
        @Config.RequiresMcRestart
        public int BEDROCKDIMENSIONID = 31871;

        @Config.LangKey("keletupack.configkp.cleansingcharmrequiretime")
        @Config.Comment("keletupack.configkp.cleansingcharmrequiretime.comment")
        @Config.RequiresMcRestart
        public int CLEASINGCHARMTICK = 1200;

        @Config.LangKey("keletupack.configkp.enablepackitem")
        @Config.Comment("keletupack.configkp.enablepackitem.comment")
        @Config.RequiresMcRestart
        public boolean ENABLEPACKITEM = false;

        @Config.LangKey("keletupack.configkp.enablecrystalbag")
        @Config.Comment("keletupack.configkp.enablecrystalbag.comment")
        @Config.RequiresMcRestart
        public boolean ENABLECRYSTALBAG = true;

        @Config.LangKey("keletupack.configkp.dimensionalsharddroprate")
        @Config.Comment("keletupack.configkp.dimensionalsharddroprate.comment")
        @Config.RangeInt(min = 0, max = 1)
        @Config.RequiresMcRestart
        public float SHARDDROPRATE = 0.0065F;

        @Config.LangKey("keletupack.configkp.enablecrystalcaster")
        @Config.Comment("keletupack.configkp.enablecrystalcaster.comment")
        public boolean ENABLECRYSTALCASTER = true;

        @Config.LangKey("keletupack.configkp.enablecrystalcaster")
        @Config.Comment("keletupack.configkp.enablecrystalcaster.comment")
        public String trashlist = "dirt;sand;gravel;cobblestone;netherrack";
        public String[] trashpile = trashlist.split(";");
    }

}
