package keletu.keletupack.proxy;

import keletu.keletupack.blocks.tiles.TileEtherealBloom;
import keletu.keletupack.blocks.tiles.TileEtherealBloomRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void registerItemRenderer( Item item, int meta, String id )
    {
        ModelLoader.setCustomModelResourceLocation( item, meta, new ModelResourceLocation( item.getRegistryName(), id ) );
    }

    private void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
    }

    public void registerDisplayInformationInit() {
        setupTileRenderers();
    }
    public void setupTileRenderers() {
        registerTileEntitySpecialRenderer(TileEtherealBloom.class, (TileEntitySpecialRenderer)new TileEtherealBloomRenderer());
    }

    public void preInit( FMLPreInitializationEvent event )
    {
        super.preInit( event );
    }


    public void init( FMLInitializationEvent event )
    {

    }
}
