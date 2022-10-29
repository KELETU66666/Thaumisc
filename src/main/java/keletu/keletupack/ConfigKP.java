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

    @Config.LangKey("keletupack.configkp.langkey")
    @Config.Comment("keletupack.configkp.comment")
    public static final warpKP warpKP = new warpKP();

    public static class warpKP {
        @Config.LangKey("keletupack.warpkp.summonbat")
        @Config.Comment("Min warp for summon bats")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int SummonBat = 20;

        @Config.LangKey("keletupack.warpkp.poison")
        @Config.Comment("Min warp for poison")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int BloodPoison = 32;

        @Config.LangKey("keletupack.warpkp.jumpboost3")
        @Config.Comment("Min warp for jump boots effect lvl3")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int JumpBoostLesser = 36;

        @Config.LangKey("keletupack.warpkp.thunderrain")
        @Config.Comment("Min warp for rain")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int ThunderAndRain = 48;

        @Config.LangKey("keletupack.warpkp.nausea")
        @Config.Comment("Min warp for Nausea")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int Nausea = 50;

        @Config.LangKey("keletupack.warpkp.summoncreeper")
        @Config.Comment("Min warp for Summon Creeper")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int PassiveCreeper = 55;

        @Config.LangKey("keletupack.warpkp.thunder")
        @Config.Comment("Min warp for Summon Thunder")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int ThunderNoRain = 60;

        @Config.LangKey("keletupack.warpkp.summonbat")
        @Config.Comment("Min warp for summon bats")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int SummonFireBat = 61;

        @Config.LangKey("keletupack.warpkp.summonanimal")
        @Config.Comment("Min warp for Summon Animals")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int SummonAnimal = 64;

        @Config.LangKey("keletupack.warpkp.jumpboost20")
        @Config.Comment("Min warp for jump boots effect lvl20")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int JumpBoostHigher = 80;

        @Config.LangKey("keletupack.warpkp.randomteleport")
        @Config.Comment("Min warp for Random Teleport")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int RandomTeleport = 90;

        @Config.LangKey("keletupack.warpkp.summonwither")
        @Config.Comment("Min warp for Summon Wither")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int SummonWither = 150;

        @Config.LangKey("keletupack.warpkp.enableforceunlock")
        @Config.Comment("Enable Forced player unlock eldritch")
        @Config.RequiresMcRestart
        public boolean EnableForceEldritch = true;

        @Config.LangKey("keletupack.warpkp.unlockeldritch")
        @Config.Comment("Min Actual warp to FORCE Unlock Eldritch")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int ForceEldritch = 50;

        @Config.LangKey("keletupack.warpkp.speed")
        @Config.Comment("Min warp for Speed Effect")
        @Config.RangeInt(min = 10)
        @Config.RequiresMcRestart
        public int Lighter = 95;
    }

    @Config.LangKey("keletupack.configkp.langkey")
    @Config.Comment("keletupack.configkp.comment")
    public static final CompatKP CompatKP = new CompatKP();

    public static class CompatKP {
        @Config.LangKey("keletupack.configkp.aurastrikedamage")
        @Config.Comment("Aura Strike Damage Bonus")
        @Config.RangeInt(min = 0, max = 100)
        @Config.RequiresMcRestart
        public float AURASTRIKEBONUS = 6;

        @Config.LangKey("keletupack.configkp.ichorbasedamage")
        @Config.Comment("Ichorium Part Base Damage")
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public float IchorAttackDamage = 3.5F;
    }
}
