package keletu.keletupack.compat.avaritia;

import com.google.gson.JsonObject;
import keletu.keletupack.ConfigKP;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

import java.util.function.BooleanSupplier;

public class CrystalCasterConditionFactory implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> (Loader.isModLoaded("avaritia") && ConfigKP.ConfigKP.ENABLECRYSTALCASTER) == JsonUtils.getBoolean(json, "value", true);
    }
}
