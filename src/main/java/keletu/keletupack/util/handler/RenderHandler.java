package keletu.keletupack.util.handler;


import keletu.keletupack.entity.EntityRender;
import keletu.keletupack.entity.ThaumaturgeSpeller;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders(){

        RenderingRegistry.registerEntityRenderingHandler(ThaumaturgeSpeller.class, new IRenderFactory<ThaumaturgeSpeller>()
        {
            @Override
            public Render<? super ThaumaturgeSpeller> createRenderFor(RenderManager manager)
            {
                return new EntityRender(manager) ;
            }
        });
    }
}