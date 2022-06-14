package keletu.keletupack.dim;


import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class TestWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.testDimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "TEST";
    }

    @Override
    public ChunkGeneratorMining createChunkGenerator() {
        return new ChunkGeneratorMining(world, getSeed());
    }
}