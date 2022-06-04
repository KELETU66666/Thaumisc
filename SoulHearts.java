package keletu.keletupack.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoulHeartHandler {

    private static final String COMPOUND = Reference.MOD_ID;
    private static final String TAG_HP = "soulHearts";
    private static final int MAX_HP = 20;

    public static void addHearts(EntityPlayer player) {
        addHP(player, 1);
        updateClient(player);
    }

    public static boolean addHP(EntityPlayer player, int hp) {
        int current = getHP(player);
        if (current >= MAX_HP)
            return false;

        setHP(player, Math.min(MAX_HP, current + hp));
        return true;
    }

    // Returns overflow damage
    public static int removeHP(EntityPlayer player, int hp) {
        int current = getHP(player);
        int newHp = current - hp;
        setHP(player, Math.max(0, newHp));

        return Math.max(0, -newHp);
    }

    public static void setHP(EntityPlayer player, int hp) {
        NBTTagCompound cmp = getCompoundToSet(player);
        cmp.setInteger(TAG_HP, hp);
    }

    public static int getHP(EntityPlayer player) {
        NBTTagCompound cmp = getCompoundToSet(player);
        return cmp.hasKey(TAG_HP) ? cmp.getInteger(TAG_HP) : 0;
    }

    private static NBTTagCompound getCompoundToSet(EntityPlayer player) {
        NBTTagCompound cmp = player.getEntityData();
        if (!cmp.hasKey(COMPOUND))
            cmp.setTag(COMPOUND, new NBTTagCompound());

        return cmp.getCompoundTag(COMPOUND);
    }

    public static void updateClient(EntityPlayer player) {
        if (player instanceof EntityPlayerMP && ((EntityPlayerMP) player).getServerWorld() != null) {
            ThaumicTinkerer.netHandler.sendTo(new PacketSoulHearts(getHP(player)), (EntityPlayerMP) player);
        }
    }

    @SubscribeEvent
    public void onPlayerDamage(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer && event.getAmount() > 0) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            event.setAmount(removeHP(player, (int) event.getAmount()));
            updateClient(player);
        }
    }

    // =============== METHODS COPIED FROM ENTITYLIVING ==================== //

    protected float applyArmorCalculations(EntityLivingBase entity, DamageSource par1DamageSource, float par2) {
        if (!par1DamageSource.isUnblockable()) {
            int i = 25 - entity.getTotalArmorValue();
            float f1 = par2 * i;
            //			this.damageArmor(par2);
            par2 = f1 / 25.0F;
        }

        return par2;
    }

    protected float applyPotionDamageCalculations(EntityLivingBase entity, DamageSource par1DamageSource, float par2) {
        int i;
        int j;
        float f1;

        if (entity.getActivePotionEffect(MobEffects.RESISTANCE) == null  && par1DamageSource != DamageSource.OUT_OF_WORLD) {
            i = (entity.getActivePotionEffect(MobEffects.RESISTANCE).getAmplifier() + 1) * 5;
            j = 25 - i;
            f1 = par2 * j;
            par2 = f1 / 25.0F;
        }

        if (par2 <= 0.0F)
            return 0.0F;
        else {
            i = EnchantmentHelper.getEnchantmentModifierDamage(entity.getActiveHand(), par1DamageSource);

            if (i > 20)
                i = 20;

            if (i > 0 && i <= 20) {
                j = 25 - i;
                f1 = par2 * j;
                par2 = f1 / 25.0F;
            }

            return par2;
        }
    }
}
