package keletu.keletupack.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.World;

public class PassiveCreeper extends EntityCreeper
{
    public PassiveCreeper(World world)
    {
        super(world);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
    }

    @Override
    public int getCreeperState() { return -1; }
}
