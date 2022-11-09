package keletu.keletupack.util.handler;


import keletu.keletupack.entity.EntityRender;
import keletu.keletupack.entity.ThaumaturgeSpeller;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders(){

        RenderingRegistry.registerEntityRenderingHandler(ThaumaturgeSpeller.class, new IRenderFactory<ThaumaturgeSpeller>()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public Render<? super ThaumaturgeSpeller> createRenderFor(RenderManager manager)
            {
                return new EntityRender(manager) ;
            }
        });
    }
}