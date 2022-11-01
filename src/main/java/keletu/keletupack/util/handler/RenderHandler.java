package keletu.keletupack.util.handler;


import keletu.keletupack.entity.EntityRender;
import keletu.keletupack.entity.ThaumoobCaster;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders(){

        RenderingRegistry.registerEntityRenderingHandler(ThaumoobCaster.class, new IRenderFactory<ThaumoobCaster>()
        {
            @Override
            public Render<? super ThaumoobCaster> createRenderFor(RenderManager manager)
            {
                return new EntityRender(manager) ;
            }
        });
    }
}