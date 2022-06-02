package keletu.keletupack.items.armor;

import com.google.common.collect.Multimap;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import keletu.keletupack.util.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.common.lib.events.PlayerEvents;

import java.util.List;

public class KamiArmor extends ItemArmor implements IVisDiscountGear, IGoggles, IHasModel {
    public static final ArmorMaterial ICHORADV = EnumHelper.addArmorMaterial("ICHORADV", "ichoradv", 0, new int[]{
            3,
            8,
            6,
            3
    }, 40, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3F);
    private final int[] discounts = new int[]{
            0,
            0,
            4,
            4,
            4,
            4
    };

    public KamiArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, CreativeTabs tab) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer mp, ItemStack itemStack) {
        ItemStack tmp;
        boolean headWorn = !(tmp = mp.getItemStackFromSlot(EntityEquipmentSlot.HEAD)).isEmpty() && tmp.getItem() instanceof KamiArmor;
        boolean bodyWorn = !(tmp = mp.getItemStackFromSlot(EntityEquipmentSlot.CHEST)).isEmpty() && tmp.getItem() instanceof KamiArmor;
        boolean beltWorn = !(tmp = mp.getItemStackFromSlot(EntityEquipmentSlot.LEGS)).isEmpty() && tmp.getItem() instanceof KamiArmor;
        boolean bootsWorn = !(tmp = mp.getItemStackFromSlot(EntityEquipmentSlot.FEET)).isEmpty() && tmp.getItem() instanceof KamiArmor;

        switch (armorType) {
            case HEAD: {
                if (mp.isInWater() && mp.ticksExisted % 10 == 0)
                    mp.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 31, 0, true, false));
                if (mp.isInLava() && mp.ticksExisted % 10 == 0)
                    mp.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 31, 0, true, false));
                int food = mp.getFoodStats().getFoodLevel();
                if (food > 0 && food < 18 && mp.shouldHeal()
                        && mp.ticksExisted % 80 == 0)
                    mp.heal(1F);
            }
            break;

            case CHEST: {
                mp.getEntityData().setBoolean("can_fly", true);
                doProjectileEffect(mp);
            }
            break;
            case LEGS: {
                if (mp.getActivePotionEffect(MobEffects.FIRE_RESISTANCE) == null || mp.getActivePotionEffect(MobEffects.FIRE_RESISTANCE).getDuration() <= 1) {
                    mp.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1, 0, false, false));
                    if (mp.isBurning()) {
                        mp.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20, 1, true, false));
                        mp.extinguish();
                    }
                }
            }
            break;

            case FEET: {
                {
                    if (!mp.capabilities.isFlying && mp.moveForward > 0.0F) {
                        if (mp.world.isRemote && !mp.isSneaking()) {
                            if (!PlayerEvents.prevStep.containsKey(Integer.valueOf(mp.getEntityId())))
                                PlayerEvents.prevStep.put(Integer.valueOf(mp.getEntityId()), Float.valueOf(mp.stepHeight));
                            mp.stepHeight = 1.0F;
                        }
                        if (mp.onGround) {
                            float bonus = 0.05F;
                            if (mp.isInWater())
                                bonus /= 4.0F;
                            mp.moveRelative(0.0F, 0.0F, bonus, 1.0F);
                        } else {
                            if (mp.isInWater())
                                mp.moveRelative(0.0F, 0.0F, 0.025F, 1.0F);
                            mp.jumpMovementFactor = 0.05F;
                        }
                    }
                    if (mp.getActivePotionEffect(MobEffects.HASTE) == null || mp.getActivePotionEffect(MobEffects.HASTE).getDuration() <= 1) {
                        mp.addPotionEffect(new PotionEffect(MobEffects.HASTE, 200, 1, false, false));
                    }
                }
                BlockPos posBelow = mp.getPosition().down();
                IBlockState blockStateBelow = mp.world.getBlockState(posBelow);
                if (blockStateBelow.getBlock() == Blocks.DIRT) {
                    mp.getEntityWorld().setBlockState(mp.getPosition().down(), Blocks.GRASS.getDefaultState(), 0);
                }
            }
            break;

        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
        if (slot == armorType) {
            if (slot == EntityEquipmentSlot.LEGS) {
            } else if (slot == EntityEquipmentSlot.FEET) {

            }
        }
        return map;
    }

    @Override
    public KamiArmor setTranslationKey(String key) {
        return (KamiArmor) super.setTranslationKey(key);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return Reference.MOD_ID + ":textures/models/armor/kami_layer_" + (slot == EntityEquipmentSlot.LEGS ? "2" : "1") + ".png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot
            armorSlot, ModelBiped _default) {
        return null;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer mp) {
        return discounts[armorType.ordinal()];
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public boolean showIngamePopups(ItemStack stack, EntityLivingBase owner) {
        return armorType == EntityEquipmentSlot.HEAD;
    }

    private void doProjectileEffect(EntityPlayer mp) {
        if (!mp.isSneaking()) {
            List<EntityLargeFireball> ghastFireballs = mp.world.getEntitiesWithinAABB(EntityLargeFireball.class, new AxisAlignedBB(mp.posX - 5, mp.posY - 5, mp.posZ - 5, mp.posX + 5, mp.posY + 5, mp.posZ + 5));
            for (EntityLargeFireball fireball : ghastFireballs) {
                if (mp.getDistance(fireball) < 3) {
                    fireball.setDead();
                }
                fireball.attackEntityFrom(DamageSource.causePlayerDamage(mp), 1);
                mp.world.playSound(fireball.posX, fireball.posY, fireball.posZ, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.NEUTRAL, 0.5F, 2.6F + (mp.world.rand.nextFloat() - mp.world.rand.nextFloat()) * 0.8F, false);
            }
            List<EntitySmallFireball> blazeFireballs = mp.world.getEntitiesWithinAABB(EntitySmallFireball.class, new AxisAlignedBB(mp.posX - 2, mp.posY - 2, mp.posZ - 2, mp.posX + 2, mp.posY + 2, mp.posZ + 2));
            for (EntitySmallFireball fireball : blazeFireballs) {
                mp.world.playSound(fireball.posX, fireball.posY, fireball.posZ, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.NEUTRAL, 0.5F, 2.6F + (mp.world.rand.nextFloat() - mp.world.rand.nextFloat()) * 0.8F, false);
                fireball.setDead();
            }
            List<EntityArrow> entityArrows = mp.world.getEntitiesWithinAABB(EntityArrow.class, new AxisAlignedBB(mp.posX - 2, mp.posY - 2, mp.posZ - 2, mp.posX + 2, mp.posY + 2, mp.posZ + 2));
            for (EntityArrow arrow : entityArrows) {
                mp.world.playSound(arrow.posX, arrow.posY, arrow.posZ, SoundEvents.ITEM_SHIELD_BLOCK, SoundCategory.NEUTRAL, 0.5F, 2.6F + (mp.world.rand.nextFloat() - mp.world.rand.nextFloat()) * 0.8F, false);
                arrow.setDead();
            }
        }
    }

    public void performEffect(EntityLivingBase entity, int p_76394_2_) {
        if (!entity.getEntityWorld().isRemote && entity.onGround && entity.getEntityWorld().isAirBlock(entity.getPosition()) && entity.getEntityWorld().getBlockState(entity.getPosition().down()).isNormalCube()) {
            entity.getEntityWorld().setBlockState(entity.getPosition(), Blocks.DIRT.getDefaultState(), 0);
        }
    }
}