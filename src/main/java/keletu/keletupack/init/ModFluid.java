package keletu.keletupack.init;

import keletu.keletupack.compat.Tconstruct.fluid.KPFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluid {

    public static final Fluid fluidIchorium = new KPFluid("molten_ichorium", new ResourceLocation("tconstruct:blocks/fluids/molten_metal"), new ResourceLocation("tconstruct:blocks/fluids/molten_metal_flow")).setColor(0xB26507).setLuminosity(15).setTemperature(1700);;

    public static void registerFluids() {
        registerFluid(fluidIchorium);
    }

    public static void registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
