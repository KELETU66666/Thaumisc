package keletu.keletupack.dim;

import keletu.keletupack.util.Reference;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModDimensions {

    public static DimensionType testDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        testDimensionType = DimensionType.register(Reference.MOD_ID, "_test", 31871, TestWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(31871, testDimensionType);
    }
}