package keletu.keletupack.entity;

import io.netty.buffer.ByteBuf;
import keletu.keletupack.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.casters.FocusMediumRoot;
import thaumcraft.api.casters.FocusPackage;
import thaumcraft.api.casters.IFocusElement;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.items.casters.foci.*;

import javax.print.DocFlavor;
import java.util.Random;

public class ThaumoobCaster extends Thaumoob implements IRangedAttackMob, IEntityAdditionalSpawnData {
    public int rage = 0;
    private static final DataParameter<Boolean> RITUALIST;
    public ThaumoobCaster(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI() {
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(2, (EntityAIBase)new AILongRangeAttack(this, 2.0D, 1.0D, 20, 40, 24.0F));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, false));
        this.tasks.addTask(4, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.tasks.addTask(5, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.tasks.addTask(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8D));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8D));
        this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, true));
        this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityEldritchGuardian.class, true));
        this.targetTasks.addTask(4, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, AbstractIllager.class, true));
    }

    protected void setItemStackToSlot() {
        super.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemsTC.casterBasic.getDefaultInstance());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    }

    protected void setLoot(DifficultyInstance diff) {
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.goggles));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.clothChest));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.clothLegs));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.clothBoots));

    }

    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
            FocusPackage p = new FocusPackage(this);
            FocusMediumRoot root = new FocusMediumRoot();
            double off = (double)(this.getDistance(target) / 6.0F);
            root.setupFromCasterToTarget(this, target, off);
            p.addNode(root);
            FocusMediumProjectile fp = new FocusMediumProjectile();
            fp.initialize();
            fp.getSetting("speed").setValue(2);
            p.addNode(fp);
            p.addNode((IFocusElement)(this.rand.nextBoolean() ? new FocusEffectCurse() : (this.rand.nextBoolean() ? new FocusEffectFlux() : (this.rand.nextBoolean() ? new FocusEffectEarth() : (this.rand.nextBoolean() ? new FocusEffectAir() : new FocusEffectFire())))));
            FocusEngine.castFocusPackage(this, p, true);
            this.swingArm(this.getActiveHand());

    }

    protected boolean canDespawn() {
        return false;
    }

    public void entityInit() {
        super.entityInit();
        this.getDataManager().register(RITUALIST, false);
    }

    public boolean getIsRitualist() {
        return (Boolean)this.getDataManager().get(RITUALIST);
    }

    public void setIsRitualist(boolean par1) {
        this.getDataManager().set(RITUALIST, par1);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setIsRitualist(Boolean.valueOf(par1NBTTagCompound.getBoolean("ritualist")));
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("ritualist", this.getIsRitualist());
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.getHomePosition().getX());
        data.writeInt(this.getHomePosition().getY());
        data.writeInt(this.getHomePosition().getZ());
    }

    public void readSpawnData(ByteBuf data) {
        this.setHomePosAndDistance(new BlockPos(data.readInt(), data.readInt(), data.readInt()), 8);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.world.isRemote && this.getIsRitualist()) {
            double d0 = (double)this.getHomePosition().getX() + 0.5D - this.posX;
            double d1 = (double)this.getHomePosition().getY() + 1.5D - (this.posY + (double)this.getEyeHeight());
            double d2 = (double)this.getHomePosition().getZ() + 0.5D - this.posZ;
            double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
            float f = (float)(Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
            float f1 = (float)(-(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D));
            this.rotationPitch = this.updateRotation(this.rotationPitch, f1, 10.0F);
            this.rotationYawHead = this.updateRotation(this.rotationYawHead, f, (float)this.getVerticalFaceSpeed());
        }

        if (!this.world.isRemote && this.getIsRitualist() && this.rage >= 5) {
            this.setIsRitualist(false);
        }

    }

    private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
        float f3 = MathHelper.wrapDegrees(p_75652_2_ - p_75652_1_);
        if (f3 > p_75652_3_) {
            f3 = p_75652_3_;
        }

        if (f3 < -p_75652_3_) {
            f3 = -p_75652_3_;
        }

        return p_75652_1_ + f3;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VILLAGER_AMBIENT;
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

    static {
        RITUALIST = EntityDataManager.createKey(Thaumoob.class, DataSerializers.BOOLEAN);
    }
}
