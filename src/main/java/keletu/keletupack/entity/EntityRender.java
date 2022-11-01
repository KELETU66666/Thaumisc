package keletu.keletupack.entity;


import keletu.keletupack.util.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class EntityRender extends RenderLiving<Thaumoob>{

    public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/thaumic_noob.png");


    protected ResourceLocation getEntityTexture(Thaumoob entity){

        return TEXTURES;
    }

    public EntityRender(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBiped(), 0.5F);
        addLayer((LayerRenderer)new LayerHeldItem((RenderLivingBase)this));
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor((RenderLivingBase)this) {
            protected void initArmor() {
                this.modelLeggings = new ModelBiped();
                this.modelArmor = new ModelBiped();
            }
        };
        addLayer((LayerRenderer)layerbipedarmor);
    }
}