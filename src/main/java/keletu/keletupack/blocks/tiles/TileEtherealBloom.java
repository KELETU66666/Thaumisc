package keletu.keletupack.blocks.tiles;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import thaumcraft.common.blocks.world.taint.ITaintBlock;
import thaumcraft.common.lib.utils.Utils;

public class TileEtherealBloom extends TileEntity implements ITickable {
    public int counter = 0;

    public int rad;

    public int rad1 = 0;

    public int growthCounter = 100;

    public int foundTaint;

    public static final int bloomsleep = 300;

    public int sleepcounter;

    public boolean sleep;

    public TileEtherealBloom() {
        this.counter = 0;
        this.rad1 = 0;
        this.growthCounter = 100;
        this.foundTaint = 300;
        this.sleepcounter = 300 * 4;
        this.sleep = false;
    }

    public void resetSleep() {
        this.foundTaint = 300;
        this.sleepcounter = 300 * 4;
        this.sleep = false;
    }

    public void update() {
        if (this.counter == 0) {
            this.counter = this.world.rand.nextInt(100);
            this.rad = this.counter;
        }
        this.counter++;
        if (this.foundTaint == 0) {
            this.sleepcounter--;
            this.sleep = (this.sleepcounter != 0);
            if (!this.sleep) {
                this.counter = 0;
                this.sleepcounter = 300 * 4;
            }
        }
        if (!this.world.isRemote && this.counter % 20 == 0 && !this.sleep) {
            this.rad = (int) (this.rad + 5.0D + Math.sqrt((1 + this.rad1)) * 5.0D + this.world.rand.nextInt(5));
            if (this.rad > 360) {
                this.rad -= 360;
                this.rad1 += 5 + this.world.rand.nextInt(5);
                if (this.rad1 > 87)
                    this.rad1 -= 87;
            }
            boolean foundsomething = false;
            TCVec3 vsource = TCVec3.createVectorHelper(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D);
            for (int q = 1; q < 8; q++) {
                TCVec3 vtar = TCVec3.createVectorHelper(q, 0.0D, 0.0D);
                vtar.rotateAroundZ(this.rad1 / 180.0F * 3.1415927F);
                vtar.rotateAroundY(this.rad / 180.0F * 3.1415927F);
                TCVec3 vres1 = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
                TCVec3 vres2 = vsource.addVector(-vtar.xCoord, -vtar.yCoord, -vtar.zCoord);
                BlockPos t1 = new BlockPos(MathHelper.floor(vres1.xCoord), MathHelper.floor(vres1.yCoord), MathHelper.floor(vres1.zCoord));
                while (this.world.isAirBlock(t1) && t1.getY() > 0)
                    t1 = t1.down();
                BlockPos t2 = new BlockPos(MathHelper.floor(vres2.xCoord), MathHelper.floor(vres2.yCoord), MathHelper.floor(vres2.zCoord));
                while (this.world.isAirBlock(t2) && t2.getY() > 0)
                    t2 = t2.down();
                if (clearBlock(t1))
                    foundsomething = true;
                if (clearBlock(t2))
                    foundsomething = true;
                if (foundsomething) {
                    resetSleep();
                    break;
                }
            }
            if (this.foundTaint > 0 && !foundsomething)
                this.foundTaint--;
        }
        if (this.world.isRemote &&
                this.growthCounter == 0)
        this.growthCounter++;
    }

    private boolean clearBlock(BlockPos p) {
        boolean bc = Utils.resetBiomeAt(this.world, p);
        Block bt = this.world.getBlockState(p).getBlock();
        if (bt instanceof ITaintBlock) {
            ((ITaintBlock) bt).die(this.world, p, this.world.getBlockState(p));
            bc = true;
        }
        return bc;
    }
}
