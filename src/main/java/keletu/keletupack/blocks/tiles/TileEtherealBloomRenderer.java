package keletu.keletupack.blocks.tiles;


import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;

@SideOnly(Side.CLIENT)
public class TileEtherealBloomRenderer extends TileEntitySpecialRenderer {
    private static final ResourceLocation tx1 = new ResourceLocation("keletupack", "textures/models/crystalcapacitor.png");

    private static final ResourceLocation tx2 = new ResourceLocation("keletupack", "textures/models/bloom_leaves.png");

    private static final ResourceLocation tx3 = new ResourceLocation("keletupack", "textures/models/bloom_stalk.png");

    private ModelCube model = new ModelCube();

    public static final ResourceLocation texture = new ResourceLocation("keletupack", "textures/blocks/nodes.png");

    public void render(TileEntity tile, double x, double y, double z, float pt, int destroyStage, float alpha) {
        float rc1 = ((TileEtherealBloom)tile).growthCounter + pt;
        float rc2 = rc1;
        float rc3 = rc1 - 33.0F;
        float rc4 = rc1 - 66.0F;
        if (rc1 > 100.0F)
            rc1 = 100.0F;
        if (rc2 > 50.0F)
            rc2 = 50.0F;
        if (rc3 < 0.0F)
            rc3 = 0.0F;
        if (rc3 > 33.0F)
            rc3 = 33.0F;
        if (rc4 < 0.0F)
            rc4 = 0.0F;
        if (rc4 > 33.0F)
            rc4 = 33.0F;
        float scale1 = rc1 / 100.0F;
        float scale2 = rc2 / 60.0F + 0.1666666F;
        float scale3 = rc3 / 33.0F;
        float scale4 = rc4 / 33.0F * 0.7F;
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glAlphaFunc(516, 0.003921569F);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glDisable(2884);
        int i = ((TileEtherealBloom)tile).counter % 32;
        bindTexture(texture);
        UtilsFX.renderFacingQuad(tile.getPos().getX() + 0.5D, (tile.getPos().getY() + scale1), tile.getPos().getZ() + 0.5D, 32, 32, 192 + i, scale1, 11197951, scale1, 1, pt);
        GL11.glEnable(2884);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D - (scale4 / 8.0F), y + scale1 - (scale4 / 6.0F), z + 0.5D - (scale4 / 8.0F));
        GL11.glScaled((scale4 / 4.0F), (scale4 / 3.0F), (scale4 / 4.0F));
        bindTexture(tx1);
        this.model.render();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        float r1 = MathHelper.sin((((TileEtherealBloom)tile).counter + pt) / 12.0F) * 2.0F;
        float r2 = MathHelper.sin((((TileEtherealBloom)tile).counter + pt) / 11.0F) * 2.0F;
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.25D, z + 0.5D);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        int a;
        for (a = 0; a < 4; a++) {
            GL11.glPushMatrix();
            GL11.glScaled(scale3, scale1, scale3);
            GL11.glRotatef((90 * a), 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(r1, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(r2, 0.0F, 0.0F, 1.0F);
            UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y + 0.6D, z + 0.5D);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        for (a = 0; a < 4; a++) {
            GL11.glPushMatrix();
            GL11.glScaled(scale4, (scale1 * 0.7F), scale4);
            GL11.glRotatef((90 * a), 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(r2, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(r1, 0.0F, 0.0F, 1.0F);
            UtilsFX.renderQuadCentered(tx2, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5D, y, z + 0.5D);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        for (a = 0; a < 4; a++) {
            GL11.glPushMatrix();
            GL11.glTranslated((scale1 / 2.0F), 0.0D, 0.0D);
            GL11.glScaled(scale1, scale2, scale2);
            GL11.glRotatef((90 * a), 1.0F, 0.0F, 0.0F);
            UtilsFX.renderQuadCentered(tx3, 1.0F, 1.0F, 1.0F, 1.0F, 200, 771, 1.0F);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glPopMatrix();
    }
}