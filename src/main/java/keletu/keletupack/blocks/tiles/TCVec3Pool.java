package keletu.keletupack.blocks.tiles;

import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TCVec3Pool {
    private final int truncateArrayResetThreshold;

    private final int minimumSize;

    private final List vec3Cache = new ArrayList();

    private int nextFreeSpace = 0;

    private int maximumSizeSinceLastTruncation = 0;

    private int resetCount = 0;

    public TCVec3Pool(int par1, int par2) {
        this.truncateArrayResetThreshold = par1;
        this.minimumSize = par2;
    }

    public TCVec3 getVecFromPool(double par1, double par3, double par5) {
        TCVec3 var7;
        if (skipCache())
            return new TCVec3(this, par1, par3, par5);
        if (this.nextFreeSpace >= this.vec3Cache.size()) {
            var7 = new TCVec3(this, par1, par3, par5);
            this.vec3Cache.add(var7);
        } else {
            var7 = (TCVec3) this.vec3Cache.get(this.nextFreeSpace);
            var7.setComponents(par1, par3, par5);
        }
        this.nextFreeSpace++;
        return var7;
    }

    public void clear() {
        if (!skipCache()) {
            if (this.nextFreeSpace > this.maximumSizeSinceLastTruncation)
                this.maximumSizeSinceLastTruncation = this.nextFreeSpace;
            if (this.resetCount++ == this.truncateArrayResetThreshold) {
                int var1 = Math.max(this.maximumSizeSinceLastTruncation, this.vec3Cache.size() - this.minimumSize);
                while (this.vec3Cache.size() > var1)
                    this.vec3Cache.remove(var1);
                this.maximumSizeSinceLastTruncation = 0;
                this.resetCount = 0;
            }
            this.nextFreeSpace = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    public void clearAndFreeCache() {
        if (!skipCache()) {
            this.nextFreeSpace = 0;
            this.vec3Cache.clear();
        }
    }

    public int getPoolSize() {
        return this.vec3Cache.size();
    }

    public int getNextFreeSpace() {
        return this.nextFreeSpace;
    }

    private boolean skipCache() {
        return (this.minimumSize < 0 || this.truncateArrayResetThreshold < 0);
    }
}