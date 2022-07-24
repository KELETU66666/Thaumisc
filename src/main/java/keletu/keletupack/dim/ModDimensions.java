package keletu.keletupack.dim;

import keletu.keletupack.ConfigKP;
import keletu.keletupack.util.Reference;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

    public static DimensionType testDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(Reference.MOD_ID, "_test", ConfigKP.ConfigKP.BEDROCKDIMENSIONID, TestWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(ConfigKP.ConfigKP.BEDROCKDIMENSIONID, testDimensionType);
    }
}