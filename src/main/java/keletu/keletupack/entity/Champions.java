package keletu.keletupack.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.EntityUtils;

public class Champions extends EntityMob {

    public Champions(World worldIn) {
        super(worldIn);
    }

    EntityMob mob;
    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data) {
        EntityUtils.makeChampion(this, true);

        return super.onInitialSpawn(diff, data);
    }

}
