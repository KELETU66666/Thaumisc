package otherthaumcraft.huige233.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void registerItemRenderer( Item item, int meta, String id )
    {
        ModelLoader.setCustomModelResourceLocation( item, meta, new ModelResourceLocation( item.getRegistryName(), id ) );
    }


    public void preInit( FMLPreInitializationEvent event )
    {
        super.preInit( event );
    }


    public void init( FMLInitializationEvent event )
    {
        super.init(event);
    }
}
