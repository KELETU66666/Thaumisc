package keletu.keletupack.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import thaumcraft.common.entities.monster.EntityPech;

import static thaumcraft.common.entities.monster.cult.EntityCultist.LOOT;


public class Thaumoob
        extends EntityMob
{
    public Thaumoob(World worldIn) {
        super(worldIn);
        setSize(0.6F, 1.8F);
        this.experienceValue = 10;
        ((PathNavigateGround)getNavigator()).setBreakDoors(true);

        setDropChance(EntityEquipmentSlot.CHEST, 0.05F);
        setDropChance(EntityEquipmentSlot.FEET, 0.05F);
        setDropChance(EntityEquipmentSlot.HEAD, 0.05F);
        setDropChance(EntityEquipmentSlot.LEGS, 0.05F);
    }




    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }



    protected void entityInit() {
        super.entityInit();
    }


    public boolean canPickUpLoot() {
        return false;
    }


    protected boolean isValidLightLevel() {
        return true;
    }



    protected Item getDropItem() {
        return Item.getItemById(0);
    }

    protected ResourceLocation getLootTable() {
        return LOOT;
    }







    protected void setLoot(DifficultyInstance diff) {}







    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance diff) {}






    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data) {
        setLoot(diff);
        setEnchantmentBasedOnDifficulty(diff);

        return super.onInitialSpawn(diff, data);
    }






    protected boolean canDespawn() {
        return true;
    }






    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("HomeD")) {
            setHomePosAndDistance(new BlockPos(nbt.getInteger("HomeX"), nbt.getInteger("HomeY"), nbt.getInteger("HomeZ")), nbt.getInteger("HomeD"));
        }
    }



    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if (getHomePosition() != null && getMaximumHomeDistance() > 0.0F) {
            nbt.setInteger("HomeD", (int)getMaximumHomeDistance());
            nbt.setInteger("HomeX", getHomePosition().getX());
            nbt.setInteger("HomeY", getHomePosition().getY());
            nbt.setInteger("HomeZ", getHomePosition().getZ());
        }
    }



    public boolean isOnSameTeam(Entity el) {
        return (el instanceof EntityVillager || el instanceof EntityPech);
    }



    public boolean canAttackClass(Class<? extends EntityLivingBase> clazz) {
        if (clazz == EntityVillager.class || clazz == EntityPech.class || clazz == Thaumoob.class)
        {

            return false; }
        return super.canAttackClass(clazz);
    }
}
