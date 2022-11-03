package keletu.keletupack.entity;

import io.netty.buffer.ByteBuf;
import keletu.keletupack.util.ItemNBTHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.casters.FocusMediumRoot;
import thaumcraft.api.casters.FocusNode;
import thaumcraft.api.casters.FocusPackage;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.items.casters.foci.*;
import thaumcraft.common.lib.utils.EntityUtils;

public class ThaumaturgeSpeller extends EntityMob implements IRangedAttackMob, IEntityAdditionalSpawnData {
    public ThaumaturgeSpeller(World worldIn) {
        super(worldIn);
        this.experienceValue = 10;
        this.setAbsorptionAmount(80);

        setDropChance(EntityEquipmentSlot.CHEST, 0.05F);
        setDropChance(EntityEquipmentSlot.FEET, 0.05F);
        setDropChance(EntityEquipmentSlot.HEAD, 0.05F);
        setDropChance(EntityEquipmentSlot.LEGS, 0.05F);

        ItemStack item = new ItemStack(ItemsTC.travellerBoots);
        ItemNBTHelper.setByte(item, "TC.RUNIC", (byte) 2);

        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.fortressHelm));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.fortressChest));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.fortressLegs));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, item);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemsTC.elementalSword));
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(ItemsTC.casterBasic));

        EntityUtils.makeChampion(this, true);
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new AILongRangeAttack(this, 2.0D, 1.0D, 20, 40, 20.0F));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8D));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8D));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, true));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, true));
    }

    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("HomeD")) {
            setHomePosAndDistance(new BlockPos(nbt.getInteger("HomeX"), nbt.getInteger("HomeY"), nbt.getInteger("HomeZ")), nbt.getInteger("HomeD"));
        }
    }



    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if (getMaximumHomeDistance() > 0.0F) {
            nbt.setInteger("HomeD", (int)getMaximumHomeDistance());
            nbt.setInteger("HomeX", getHomePosition().getX());
            nbt.setInteger("HomeY", getHomePosition().getY());
            nbt.setInteger("HomeZ", getHomePosition().getZ());
        }
    }

    public IEntityLivingData onInitialSpawn(DifficultyInstance diff, IEntityLivingData data) {
        setEnchantmentBasedOnDifficulty(diff);

        return super.onInitialSpawn(diff, data);
    }

    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
            FocusPackage p = new FocusPackage(this);
            FocusMediumRoot root = new FocusMediumRoot();
            double off = (double)(this.getDistance(target) / 6.0F);
            root.setupFromCasterToTarget(this, target, off);
            p.addNode(root);
            FocusMediumProjectile fp = new FocusMediumProjectile();
            fp.initialize();
            fp.getSetting("speed").setValue(5);
            p.addNode(fp);
        FocusNode effect = (this.rand.nextBoolean() ? new FocusEffectCurse() : (this.rand.nextBoolean() ? new FocusEffectFlux() : (this.rand.nextBoolean() ? new FocusEffectEarth() : (this.rand.nextBoolean() ? new FocusEffectAir() : new FocusEffectFire()))));
        effect.initialize();
        effect.getSetting("power").setValue(5);
        p.addNode(effect);

        FocusEngine.castFocusPackage(this, p, true);
            this.swingArm(this.getActiveHand());

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


    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.getHomePosition().getX());
        data.writeInt(this.getHomePosition().getY());
        data.writeInt(this.getHomePosition().getZ());
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

    public void readSpawnData(ByteBuf data) {
        this.setHomePosAndDistance(new BlockPos(data.readInt(), data.readInt(), data.readInt()), 8);
    }

    public int getTalkInterval() {
        return 500;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte par1) {
        if (par1 == 19) {
            for(int i = 0; i < 3; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2, new int[0]);
            }
        } else {
            super.handleStatusUpdate(par1);
        }

    }

    public void setSwingingArms(boolean swingingArms) {
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(!this.getTags().contains("isbattle") && source.getImmediateSource() instanceof EntityPlayer)
            this.addTag("isbattle");
        return super.attackEntityFrom(source, amount);
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (this.ticksExisted%12000 == 0 && !this.getTags().contains("isbattle"))
        {
            this.setDead();
        }

        if(this.getTags().contains("isbattle") && this.ticksExisted%6000 == 0)
        {
            this.removeTag("isbattle");
        }
    }
}
