package keletu.keletupack.event;

import com.google.common.collect.Multimap;
import keletu.keletupack.ConfigKP;
import keletu.keletupack.enchantments.EnchantmentsKP;
import keletu.keletupack.items.armor.KamiArmor;
import keletu.keletupack.items.tools.DistortionPick;
import keletu.keletupack.items.tools.IchoriumPickAdv;
import keletu.keletupack.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.lib.SoundsTC;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class LivingEvent {
    public static void register(ResourceLocation resourceLocation) {
    }

    public final String NBTLastTarget = "TTEnchantLastTarget";

    public final String NBTSuccessiveStrike = "TTEnchantSuccessiveStrike";

    public final String NBTTunnelDirection = "TTEnchantTunnelDir";

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END)
            return;
        NBTTagCompound nbt = e.player.getEntityData();
        if (nbt.getBoolean("can_fly")) {
            e.player.capabilities.allowFlying = true;
            nbt.setBoolean("can_fly", false);
        } else if (nbt.hasKey("can_fly")) {
            if (!e.player.capabilities.isCreativeMode && !e.player.isSpectator()) {
                e.player.capabilities.allowFlying = false;
                e.player.capabilities.isFlying = false;
            }
            nbt.removeTag("can_fly");
        }
    }

    @SubscribeEvent
    public void playerJumps(net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent event) {
        ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (event.getEntity() instanceof EntityPlayer && stack.getItem() instanceof KamiArmor && stack.getItemDamage() != 1)
            event.getEntityLiving().motionY += 0.3;

        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ItemStack stack1 = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            int boost = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.ascentboost, stack1);

            if (boost >= 1 && !player.isSneaking())
                player.motionY *= (boost + 2) / 2D;
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityUpdate(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event) {
        final double min = -0.0784000015258789;

        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int slowfall = EnchantmentHelper.getMaxEnchantmentLevel(EnchantmentsKP.slowfall, player);
            if (slowfall > 0 && !event.getEntityLiving().isSneaking() && event.getEntityLiving().motionY < min && event.getEntityLiving().fallDistance >= 2.9) {
                event.getEntityLiving().motionY /= 1 + slowfall * 0.33F;
                event.getEntityLiving().fallDistance = Math.max(2.9F, player.fallDistance - slowfall / 3F);

                player.world.spawnParticle(EnumParticleTypes.CLOUD, player.posX + 0.25, player.posY - 1, player.posZ + 0.25, -player.motionX, player.motionY, -player.motionZ);

            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void onEvent(LivingEntityUseItemEvent.Tick event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            ItemStack item = event.getItem();
            int time = event.getDuration();
            int quickDraw = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.quickdraw, item);
            if (item.isEmpty()) {
                return;
            }

            if (quickDraw > 0 && item.getItem() instanceof ItemBow) {

                    if ((item.getItem().getMaxItemUseDuration(item) - time) % (6 - quickDraw) == 0)
                        event.setDuration(time - 1);
            }
        }
    }


    @SubscribeEvent
    public void fall(LivingFallEvent e) {
        ItemStack boots = e.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if (!boots.isEmpty() && boots.getItem() instanceof KamiArmor && boots.getItemDamage() != 1) {
            e.setDamageMultiplier(0);
            e.setCanceled(true);
        }

        if (e.getEntityLiving() instanceof EntityPlayer) {
            int shockwave = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.shockwave, boots);
            if (shockwave > 0) {
                for (EntityLivingBase target : (List<EntityLivingBase>) e.getEntity().world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(e.getEntity().posX - 10, e.getEntity().posY - 10, e.getEntity().posZ - 10, e.getEntity().posX + 10, e.getEntity().posY + 10, e.getEntity().posZ + 10))) {
                    if (target != e.getEntity() && e.getDistance() > 3) {
                        target.attackEntityFrom(DamageSource.FALL, .1F * shockwave * e.getDistance());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerBreaking(PlayerEvent.BreakSpeed event) {
        BlockPos pos = event.getPos();
        if (event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND) != null) {
            ItemStack stack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
            if (stack.getItem() instanceof DistortionPick) {
                World world = event.getEntityPlayer().world;
                BlockPos pos1;
                pos1 = pos.add(0, 0, 0);
                float hardness = world.getBlockState(pos1).getBlockHardness(world, pos1);
                if (hardness == 0.0F)
                    event.setNewSpeed(0.0F);
                else if (hardness < 5.0F)
                    event.setNewSpeed(0.1F);
                else if (hardness < 20.0F)
                    event.setNewSpeed(hardness / 2.0F);
                else
                    event.setNewSpeed(5.0F + hardness);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteractEvent(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getHand() == EnumHand.MAIN_HAND) {
            ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
            if (event.getEntityPlayer().world.getBlockState(event.getPos()).getBlock().equals(Blocks.BEDROCK)) {
                if (stack != null && stack.getItem() instanceof IchoriumPickAdv) {
                    stack.getItem().onBlockStartBreak(stack, event.getPos(), event.getEntityPlayer());
                }
            }
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer && !(event.getEntity() instanceof EntityLivingBase)) {
            ItemStack equip = ((EntityPlayer) event.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND);
            LivingExperienceDropEvent event1 = new LivingExperienceDropEvent(((EntityLiving) event.getEntityLiving()), ((EntityPlayer) event.getSource().getTrueSource()), 5);

            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.educational, equip) > 0 && event.getEntityLiving() instanceof EntityLiving && EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, equip) == 0) {
                try {
                    int learning = 3 * event1.getOriginalExperience() * EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.educational, equip);
                    while (learning > 0) {
                        int xp = EntityXPOrb.getXPSplit(learning);
                        learning -= xp;
                        event.getEntityLiving().world.spawnEntity(new EntityXPOrb(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, xp));
                    }
                } catch (Throwable ignored) {
                }
            }
        }
    }

    @SubscribeEvent
    public void onHarvest(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer player = event.getHarvester();
        if (player != null) {
            player.inventory.getCurrentItem();
            ItemStack equip = player.inventory.getCurrentItem();
            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.consuming, equip) > 0) {
                for (int x = 0; x < event.getDrops().size(); x++) {
                    ItemStack drop = (ItemStack) event.getDrops().get(x);
                    if (drop != null && isGarbage(drop))
                        event.getDrops().remove(x);
                }
            }
        }
    }

    private boolean isGarbage(ItemStack drop) {
        for (int id : OreDictionary.getOreIDs(drop)) {
            for (String ore : ConfigKP.ConfigKP.trashpile) {
                if (OreDictionary.getOreName(id).equals(ore))
                    return true;
            }
        }
        return false;
    }

    public void addDrop(LivingDropsEvent event, ItemStack drop) {
        EntityItem entityitem = new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, drop);
        event.getDrops().add(entityitem);
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        Random rand = new Random();
        if ((event.getEntityLiving() instanceof EntityVillager || event.getEntityLiving() instanceof IMob) && event.isRecentlyHit() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            ItemStack equip = player.getHeldItem(EnumHand.MAIN_HAND);
            if (equip != null && EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.greedy, equip) > 0 && event.getLootingLevel() <= 0) {
                if (event.getEntityLiving() instanceof EntityVillager) {
                    addDrop(event, new ItemStack(Items.EMERALD, 1, 0));
                } else if (rand.nextInt(35) < 3)
                    addDrop(event, new ItemStack(Items.EMERALD));
            }
        }

        if (event.isRecentlyHit() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer && !(event.getSource().getTrueSource() instanceof FakePlayer)) {
            if (event.getEntityLiving() instanceof IMob) {
                int wrath = 2;
                int greed = 0;
                ItemStack heldItem = ((EntityPlayer) event.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND);
                if (heldItem != null) {
                    if (heldItem.getItem() instanceof ItemTool) {
                        Multimap map = heldItem.getItem().getAttributeModifiers(EntityEquipmentSlot.MAINHAND, heldItem);
                        Collection collect = map.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
                        if (collect instanceof AttributeModifier) {
                            wrath += (int) ((AttributeModifier) collect).getAmount();
                        }
                    } else if (heldItem.getItem() instanceof ItemSword) {
                        wrath += (int) (((ItemSword) heldItem.getItem()).getAttackDamage() + 4.0F);
                    }

                    wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, heldItem);
                    wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, heldItem);

                    if (!event.getEntityLiving().isImmuneToFire()) {
                        wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, heldItem);
                        wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, heldItem);
                    }

                    if (event.getEntityLiving().isEntityUndead()) {
                        wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, heldItem);
                    }

                    if (event.getEntityLiving().getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
                        wrath += EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, heldItem);
                    }

                    greed += EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, heldItem);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityDamaged(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
            ItemStack heldItem = attacker.getHeldItem(EnumHand.MAIN_HAND);

            if (heldItem == null)
                return;


            if (attacker instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) attacker;

                ItemStack legs = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
                int pounce = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.pounce, legs);
                if (pounce > 0) {
                    BlockPos pos = new BlockPos((int) Math.floor(player.posX), (int) Math.floor(player.posY) - 1, (int) Math.floor(player.posZ));
                    if (player.world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                        event.setAmount((float) (event.getAmount() * (1 + (.25 * pounce))));
                    }
                }

            }

            int finalStrike = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.finalStrike, heldItem);
            if (finalStrike > 0) {
                Random rand = new Random();
                if (rand.nextInt(20 - finalStrike) == 0) {
                    event.setAmount((float) (event.getAmount() * 3));
                }
            }

            int valiance = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.valiance, heldItem);
            if (valiance > 0) {
                if (attacker.getHealth() / attacker.getMaxHealth() < .5F) {
                    event.setAmount((float) (event.getAmount() * (1 + .1 * valiance)));
                }
            }

            int vampirism = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.vampirism, heldItem);
            if (vampirism > 0) {
                attacker.heal(vampirism);
                event.getEntityLiving().world.playSound(event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, SoundsTC.zap, SoundCategory.NEUTRAL, 0.6F, 1F, false);
            }

            int focusedStrikes = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.focusedStrike, heldItem);

            int dispersedStrikes = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.dispersedStrikes, heldItem);

            if (focusedStrikes > 0 || dispersedStrikes > 0) {
                int lastTarget = heldItem.getTagCompound().getInteger(NBTLastTarget);
                int successiveStrikes = heldItem.getTagCompound().getInteger(NBTSuccessiveStrike);
                int entityId = event.getEntityLiving().getEntityId();

                if (lastTarget != entityId) {
                    heldItem.getTagCompound().setInteger(NBTSuccessiveStrike, 0);
                    successiveStrikes = 0;
                } else {
                    heldItem.getTagCompound().setInteger(NBTSuccessiveStrike, successiveStrikes + 1);
                    successiveStrikes = 1;
                }

                if (focusedStrikes > 0) {
                    event.setAmount(event.getAmount() / 2);
                    event.setAmount((float) (event.getAmount() + (.5 * successiveStrikes * event.getAmount() * focusedStrikes)));
                }
                if (dispersedStrikes > 0) {
                    event.setAmount((float) (event.getAmount() * (1 + 0.2 * successiveStrikes)));
                    event.setAmount(event.getAmount() / (1 + (successiveStrikes * 2)));
                }

                heldItem.getTagCompound().setInteger("TTEnchantLastTarget", entityId);

            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        ItemStack item = event.getPlayer().getHeldItem(EnumHand.MAIN_HAND);
        int tunnel = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.tunnel, item);
        if (tunnel > 0) {
            float dir = event.getPlayer().rotationYaw;
            item.getTagCompound().setFloat(NBTTunnelDirection, dir);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGetHarvestSpeed(PlayerEvent.BreakSpeed event) {
        ItemStack heldItem = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);

        if (heldItem == null)
            return;

        int shatter = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.shatter, heldItem);
        if (shatter > 0) {
            if (event.getState().getBlockHardness(event.getEntityPlayer().world, BlockPos.ORIGIN) > 20F) {
                event.setNewSpeed((float) (event.getOriginalSpeed() * 3 * shatter));
            } else {
                event.setNewSpeed((float) (event.getOriginalSpeed() * .8));
            }
        }

        int tunnel = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.tunnel, heldItem);
        if (tunnel > 0) {
            float dir = event.getEntityPlayer().rotationYaw;
            if (heldItem.getTagCompound() != null && heldItem.getTagCompound().hasKey(NBTTunnelDirection)) {
                float oldDir = heldItem.getTagCompound().getFloat(NBTTunnelDirection);
                float dif = Math.abs(oldDir - dir);
                if (dif < 50) {
                    event.setNewSpeed((float) (event.getOriginalSpeed() * (1 + (.2 * tunnel))));
                } else {
                    event.setNewSpeed((float) (event.getOriginalSpeed() * .3));
                }
            }
        }

        int desintegrate = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.desintegrate, heldItem);
        int autoSmelt = EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.autosmelt, heldItem);

        boolean desintegrateApplies = desintegrate > 0 && event.getState().getBlockHardness(event.getEntityPlayer().world, BlockPos.ORIGIN) <= 1.5F;
        boolean autoSmeltApplies = autoSmelt > 0 && event.getState().getMaterial() == Material.WOOD;

        if (desintegrateApplies || autoSmeltApplies) {
            heldItem.damageItem(1, event.getEntityPlayer());
            event.setNewSpeed(Float.MAX_VALUE);
        } else if (desintegrate > 0 || autoSmelt > 0)
            event.setCanceled(true);
    }
}