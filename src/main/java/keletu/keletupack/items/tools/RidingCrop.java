package keletu.keletupack.items.tools;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;


public class RidingCrop extends ItemSword implements IHasModel {

    public RidingCrop(String name, CreativeTabs tab, ToolMaterial material) {
        super(material);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase victim, EntityLivingBase player) {
        stack.damageItem(1, player);
        if (victim instanceof EntityHorse || victim instanceof EntityPig)
            victim.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 5, false, false));
        else if (victim instanceof EntityPlayer || victim instanceof EntityGolem) {
            victim.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1, false, false));
            victim.addPotionEffect(new PotionEffect(MobEffects.HASTE, 200, 1, false, false));
            victim.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 1, false, false));
        }
        if (!player.world.isRemote && player.world.provider.getDimension() == -1 && player.world.rand.nextInt(15) == 1) {
            EntityItem ent = victim.entityDropItem(new ItemStack(Items.REDSTONE, 1, 0), 1.0F);
            ent.motionY += player.world.rand.nextFloat() * 0.05F;
            ent.motionX += (player.world.rand.nextFloat() - player.world.rand.nextFloat()) * 0.1F;
            ent.motionZ += (player.world.rand.nextFloat() - player.world.rand.nextFloat()) * 0.1F;
        }
        return true;
    }


    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 0;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityLivingBase) {
            EntityLivingBase mount = (EntityLivingBase) player.getRidingEntity();
            itemstack.damageItem(1, player);
            player.getSwingProgress(1);
            mount.attackEntityFrom(DamageSource.GENERIC, 1.0F);
            mount.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 5, false, false));
        }
        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack stack2) {
        return stack2.getItem() == Items.LEATHER || super.getIsRepairable(stack, stack2);
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}