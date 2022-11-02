package keletu.keletupack.event;

import baubles.api.BaublesApi;
import com.google.common.collect.Multimap;
import keletu.keletupack.ConfigKP;
import keletu.keletupack.common.ItemsKP;
import keletu.keletupack.enchantments.EnchantmentsKP;
import keletu.keletupack.enchantments.inchantment.EnumInfusionEnchantmentKP;
import keletu.keletupack.entity.PassiveCreeper;
import keletu.keletupack.entity.ThaumaturgeSpeller;
import keletu.keletupack.init.ModItems;
import keletu.keletupack.items.armor.KamiArmor;
import keletu.keletupack.items.tools.DistortionPick;
import keletu.keletupack.items.tools.IchoriumPickAdv;
import keletu.keletupack.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ModConfig;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.tainted.EntityTaintCrawler;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.events.PlayerEvents;
import thaumcraft.common.lib.events.WarpEvents;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;
import thaumcraft.common.lib.potions.PotionDeathGaze;
import thaumcraft.common.lib.potions.PotionWarpWard;
import thaumcraft.common.lib.utils.EntityUtils;

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

    private static int getWarpFromGear(EntityPlayer player) {
        int w = PlayerEvents.getFinalWarp(player.getHeldItemMainhand(), player);

        for(int a = 0; a < 4; ++a) {
            w += PlayerEvents.getFinalWarp((ItemStack)player.inventory.armorInventory.get(a), player);
        }

        IInventory baubles = BaublesApi.getBaubles(player);

        for(int a = 0; a < baubles.getSizeInventory(); ++a) {
            w += PlayerEvents.getFinalWarp(baubles.getStackInSlot(a), player);
        }

        return w;
    }

    @SubscribeEvent
    public void livingTick(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntity();
            if (!player.world.isRemote) {
                handleWarp(player);
            }
        }
    }

    private static void handleWarp(EntityPlayer player) {
        if (!ModConfig.CONFIG_MISC.wussMode && player.ticksExisted > 0 && player.ticksExisted % 2000 == 0 &&
                !player.isPotionActive(PotionWarpWard.instance))
            checkWarpEvent(player);
        if (player.ticksExisted % 20 == 0 && player.isPotionActive(PotionDeathGaze.instance))
            WarpEvents.checkDeathGaze(player);
    }

    public static void checkWarpEvent(EntityPlayer player) {
        IPlayerWarp wc = ThaumcraftCapabilities.getWarp(player);
        ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
        int tw = wc.get(IPlayerWarp.EnumWarpType.TEMPORARY);
        int nw = wc.get(IPlayerWarp.EnumWarpType.NORMAL);
        int pw = wc.get(IPlayerWarp.EnumWarpType.PERMANENT);
        int warp = tw + nw + pw;
        int actualwarp = pw + nw;
        int gearWarp = getWarpFromGear(player);
        warp += gearWarp;
        int warpCounter = wc.getCounter();
        int r = player.world.rand.nextInt(100);
        if (warpCounter > 0 && warp > 0 && (double)r <= Math.sqrt((double)warpCounter)) {
            warp = Math.min(100, (warp + warp + warpCounter) / 3);
            warpCounter = (int)((double)warpCounter - Math.max(5.0D, Math.sqrt((double)warpCounter) * 2.0D - (double)(gearWarp * 2)));
            wc.setCounter(warpCounter);
            int eff = player.world.rand.nextInt(warp) + gearWarp;
            ItemStack helm = (ItemStack)player.inventory.armorInventory.get(3);
            if (helm.getItem() instanceof ItemFortressArmor && helm.hasTagCompound() && helm.getTagCompound().hasKey("mask") && helm.getTagCompound().getInteger("mask") == 0) {
                eff -= 2 + player.world.rand.nextInt(4);
            }

            PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((byte)0), (EntityPlayerMP)player);
            if (eff > 0) {
                if (eff <= ConfigKP.warpKP.SummonBat) {
                    summonbat(player, warp);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.1")), true);
                }else if (eff <= ConfigKP.warpKP.BloodPoison)
                {player.addPotionEffect(new PotionEffect(MobEffects.POISON, warp * 20, 0));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.2")), true);}
                else if (eff <= ConfigKP.warpKP.JumpBoostLesser)
                {player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 3));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.3")), true);}
                else if (eff <= ConfigKP.warpKP.ThunderAndRain)
                {player.world.getWorldInfo().setRaining(true);
                    player.world.getWorldInfo().setThundering(true);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.4")), true);}
                else if (eff <= ConfigKP.warpKP.Nausea) {
                    player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, warp * 20, 0));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.5")), true);
                }
                else if (eff <= ConfigKP.warpKP.PassiveCreeper){
                    summoncreeper(player, 1);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.6")), true);
                }
                else if (eff <= ConfigKP.warpKP.ThunderNoRain) {
                    player.addTag("lightning");
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.7")), true);
                }
                else if (eff <= ConfigKP.warpKP.SummonFireBat) {
                    summonnetherbat(player, warp);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.1")), true);
                }
                else if (eff <= ConfigKP.warpKP.SummonAnimal) {
                    summonanimal(player, warp);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.8")), true);
                }
                else if (eff <= ConfigKP.warpKP.JumpBoostHigher) {
                    player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 400, 20));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.9")), true);
                }
                else if (eff <= ConfigKP.warpKP.Lighter) {
                    player.addPotionEffect(new PotionEffect(MobEffects.SPEED, warp * 5, 10));
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.12")), true);
                }
                else if (eff <= ConfigKP.warpKP.RandomTeleport) {
                    player.addTag("tmisc_teleport");
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.10")), true);
                }
                else if (eff <= ConfigKP.warpKP.SummonWither) {
                    summonwither(player, 1);
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warptheory.text.11")), true);
                }
            }
                if (ConfigKP.warpKP.EnableForceEldritch && actualwarp > ConfigKP.warpKP.ForceEldritch && !ThaumcraftCapabilities.knowsResearch(player, new String[]{"BASEELDRITCH"}) && player.inventory.hasItemStack(new ItemStack(ItemsTC.thaumonomicon))) {
                    player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("warp.tip.1")), true);
                    ThaumcraftApi.internalMethods.completeResearch(player, "BASEELDRITCH");
                }

        }

    }

    private static void summonSPIDER(EntityPlayer player, int warp, boolean real) {
        int spawns = Math.min(15, warp);

        for(int a = 0; a < spawns; ++a) {
            EntityMindSpider spider = new EntityMindSpider(player.world);
            int i = MathHelper.floor(player.posX);
            int j = MathHelper.floor(player.posY);
            int k = MathHelper.floor(player.posZ);
            boolean success = false;

            for(int l = 0; l < 50; ++l) {
                int i1 = i + MathHelper.getInt(player.world.rand, 7, 24) * MathHelper.getInt(player.world.rand, -1, 1);
                int j1 = j + MathHelper.getInt(player.world.rand, 7, 24) * MathHelper.getInt(player.world.rand, -1, 1);
                int k1 = k + MathHelper.getInt(player.world.rand, 7, 24) * MathHelper.getInt(player.world.rand, -1, 1);
                if (player.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isFullCube()) {
                    spider.setPosition((double)i1, (double)j1, (double)k1);
                    if (player.world.checkNoEntityCollision(spider.getEntityBoundingBox()) && player.world.getCollisionBoxes(spider, spider.getEntityBoundingBox()).isEmpty() && !player.world.containsAnyLiquid(spider.getEntityBoundingBox())) {
                        success = true;
                        break;
                    }
                }
            }

            if (success) {
                spider.setAttackTarget(player);
                if (!real) {
                    spider.setViewer(player.getName());
                    spider.setHarmless(true);
                }

                player.world.spawnEntity(spider);
            }
        }

        player.sendStatusMessage(new TextComponentString(I18n.translateToLocal("warp.text.7")), true);
    }

    private static void summonbat(EntityPlayer player, int warp) {
        int spawns = 15 + player.world.rand.nextInt(30);

        for (int a = 0; a < spawns; ++a) {
            EntityBat bat = new EntityBat(player.world);
            boolean success = false;

            for (int l = 0; l < 6; l++) {
                int i1 = (int) player.posX + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                int j1 = (int) player.posY + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                int k1 = (int) player.posZ + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                if (player.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isFullCube()) {
                    bat.setPosition((double) i1, (double) j1, (double) k1);
                    success = true;
                    break;
                }
            }

            if (success) {
                player.world.spawnEntity(bat);
            }
        }
    }

    private static void summonnetherbat(EntityPlayer player, int warp) {
        int spawns = 15 + player.world.rand.nextInt(30);

        for (int a = 0; a < spawns; ++a) {
            EntityFireBat bat = new EntityFireBat(player.world);
            boolean success = false;

            for (int l = 0; l < 6; l++) {
                int i1 = (int) player.posX + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                int j1 = (int) player.posY + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                int k1 = (int) player.posZ + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                if (player.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isFullCube()) {
                    bat.setPosition((double) i1, (double) j1, (double) k1);
                    success = true;
                    break;
                }
            }

            if (success) {
                player.world.spawnEntity(bat);
            }
        }
    }

    public static void summonanimal(EntityPlayer player, int warp) {
        if(!player.world.isRemote) {
            int spawns = 2 + player.world.rand.nextInt(10);

            for (int a = 0; a < spawns; ++a) {
                EntityLiving victim;
                switch (player.world.rand.nextInt(3)) {
                    case 0:
                        victim = new EntityCow(player.world);
                        break;
                    case 1:
                        victim = new EntityPig(player.world);
                        break;
                    case 2:
                        victim = new EntitySheep(player.world);
                        break;
                    default:
                        victim = new EntityChicken(player.world);
                        break;
                }
                boolean success = false;

                for (int i = 0; i < 6; i++) {
                    int targetX = (int) player.posX + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                    int targetY = (int) player.posY + player.world.rand.nextInt(8) - player.world.rand.nextInt(8) + 25;
                    int targetZ = (int) player.posZ + player.world.rand.nextInt(8) - player.world.rand.nextInt(8);
                    for (int y = targetY; y < targetY + 25; y++) {
                        victim.setPosition((double) targetX, (double) targetY, (double) targetZ);
                        success = true;
                    }
                }
                if (success) {
                    player.world.spawnEntity(victim);
                }
            }
        }
    }

    private static void summonwither(EntityPlayer player, int warp) {
        int spawns = 1;

            EntityWither wither = new EntityWither(player.world);
            boolean success = false;

            for (int l = 0; l < 6; l++) {
                int targetX = (int)player.posX + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
                int targetY = (int)player.posY + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
                int targetZ = (int)player.posZ + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
                if (player.world.getBlockState(new BlockPos(targetX, targetY - 1, targetZ)).isFullCube()) {
                    wither.setPosition((double) targetX, (double) targetY, (double) targetZ);
                    success = true;
                    break;
                }
            }

            if (success) {
                player.world.spawnEntity(wither);
            }
    }

    @SubscribeEvent
    public static void summonlightning(TickEvent.PlayerTickEvent event)
    {
            if (!event.player.world.isRemote && event.player.getTags().contains("lightning")) {
                int lightning = ThaumcraftApi.internalMethods.getActualWarp(event.player);
                int x = (int) event.player.posX + event.player.world.rand.nextInt(3) - event.player.world.rand.nextInt(3);
                int y = (int) event.player.posY;
                int z = (int) event.player.posZ + event.player.world.rand.nextInt(3) - event.player.world.rand.nextInt(3);
                BlockPos pos = new BlockPos(x, y, z);
                if (event.player.world.rand.nextInt(100) == 0 && event.player.world.canBlockSeeSky(pos)) {
                    EntityLightningBolt bolt = new EntityLightningBolt(event.player.world, x, y, z, true);
                    event.player.world.addWeatherEffect(bolt);
                    if (event.player.getTags().contains("lightning"))
                        --lightning;
                    if (lightning <= 0)
                        event.player.removeTag("lightning");

                }
        }
    }

    private static void summoncreeper(EntityPlayer player, int warp) {
        int spawns = 1;

        PassiveCreeper preeper = new PassiveCreeper(player.world);
        boolean success = false;

        for (int l = 0; l < 6; l++) {
            int targetX = (int) player.posX + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
            int targetY = (int) player.posY + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
            int targetZ = (int) player.posZ + player.world.rand.nextInt(4) - player.world.rand.nextInt(4);
            if (player.world.getBlockState(new BlockPos(targetX, targetY - 1, targetZ)).isFullCube()) {
                preeper.setPosition((double) targetX, (double) targetY, (double) targetZ);
                preeper.setCustomNameTag("keletu");
                success = true;
                break;
            }
        }

        if (success) {
            player.world.spawnEntity(preeper);
        }
    }

    @SubscribeEvent
    public void TickEvents(TickEvent.PlayerTickEvent event) {
        if (event.player.getTags().contains("puring") && event.player.ticksExisted % 20 == 0) {
            if(ThaumcraftCapabilities.getWarp(event.player).get(IPlayerWarp.EnumWarpType.TEMPORARY)> 2.5) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, (int) -2.5, IPlayerWarp.EnumWarpType.TEMPORARY);
                checkWarpEvent(event.player);
                WarpEvents.checkWarpEvent(event.player);}else
            {ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, -1, IPlayerWarp.EnumWarpType.TEMPORARY);}
            if(ThaumcraftCapabilities.getWarp(event.player).get(IPlayerWarp.EnumWarpType.PERMANENT)  > 2.5) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, (int) -2.5, IPlayerWarp.EnumWarpType.PERMANENT);
                checkWarpEvent(event.player);
                WarpEvents.checkWarpEvent(event.player);}else
            {ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, -1, IPlayerWarp.EnumWarpType.PERMANENT);}
            if(ThaumcraftCapabilities.getWarp(event.player).get(IPlayerWarp.EnumWarpType.NORMAL) > 2.5) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, (int) -2.5, IPlayerWarp.EnumWarpType.NORMAL);
                checkWarpEvent(event.player);
                WarpEvents.checkWarpEvent(event.player);}else
            {ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, -1, IPlayerWarp.EnumWarpType.NORMAL);}
        }
        if((ThaumcraftApi.internalMethods.getActualWarp(event.player) + ThaumcraftCapabilities.getWarp(event.player).get(IPlayerWarp.EnumWarpType.TEMPORARY)) == 0)
        {
            event.player.removeTag("puring");
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event)
    {
        if (!event.player.world.isRemote && event.player.getTags().contains("tmisc_teleport")  && event.player.ticksExisted % 200 == 0)
        {
            int teleport = ThaumcraftApi.internalMethods.getActualWarp(event.player);
            double d0 = event.player.posX;
            double d1 = event.player.posY;
            double d2 = event.player.posZ;

            for (int i = 0; i < 16; ++i)
            {
                double d3 = event.player.posX + (event.player.getRNG().nextDouble() - 0.5D) * 24.0D;
                double d4 = MathHelper.clamp(event.player.posY + (double)(event.player.getRNG().nextInt(16) - 8), 0.0D, (double)(event.player.world.getActualHeight() - 1));
                double d5 = event.player.posZ + (event.player.getRNG().nextDouble() - 0.5D) * 24.0D;

                if (event.player.isRiding())
                {
                    event.player.dismountRidingEntity();
                }

                if (event.player.attemptTeleport(d3, d4, d5))
                {
                    event.player.world.playSound((EntityPlayer)null, d0, d1, d2, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    event.player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
                    teleport--;
                }

                if (teleport <= 0)
                {
                    event.player.removeTag("tmisc_teleport");
                }
            }
        }
    }

    @SubscribeEvent
    public void WarpSeriesEvents(LivingDeathEvent event) {
        if(event.getSource().getTrueSource() instanceof EntityPlayer && event.getSource().getTrueSource() != null) {
            if (
            !event.getSource().getTrueSource().world.isRemote && ThaumcraftApi.internalMethods.getActualWarp((EntityPlayer) event.getSource().getTrueSource()) > 50 && ((((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.crimsonPlateHelm) || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.crimsonRobeHelm || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.voidRobeHelm)
                    && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.crimsonPlateChest || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.crimsonRobeChest || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.voidRobeChest)
                    && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.crimsonPlateLegs || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.crimsonRobeLegs || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.voidRobeLegs)
                    && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemsTC.crimsonBoots || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemsTC.voidBoots)
                    && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.IRON_SWORD || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == ItemsTC.voidSword)
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_0")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_1")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_2")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_3")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_4")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_3_1")
                    && !event.getSource().getTrueSource().getTags().contains("crimson_invite_final")
                    && event.getEntityLiving() instanceof EntityVillager
            ){
                event.getSource().getTrueSource().sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_0")));
                ItemStack stack = new ItemStack(ItemsKP.RESOURCECRIMSON, 1, 0);
                if (stack.getTagCompound() != null)
                    stack.getTagCompound().setInteger("invite_progress", 0);
                ((EntityPlayer) event.getSource().getTrueSource()).addItemStackToInventory(stack);
                event.getSource().getTrueSource().addTag("crimson_invite_0");

            }

            if (
                    !event.getSource().getTrueSource().world.isRemote && ThaumcraftApi.internalMethods.getActualWarp((EntityPlayer) event.getSource().getTrueSource()) > 50 && ((((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.crimsonPlateHelm) || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.crimsonRobeHelm || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ItemsTC.voidRobeHelm)
                            && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.crimsonPlateChest || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.crimsonRobeChest || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == ItemsTC.voidRobeChest)
                            && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.crimsonPlateLegs || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.crimsonRobeLegs || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsTC.voidRobeLegs)
                            && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemsTC.crimsonBoots || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == ItemsTC.voidBoots)
                            && (((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == Items.IRON_SWORD || ((EntityPlayer) event.getSource().getTrueSource()).getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem() == ItemsTC.voidSword)
                            && (event.getSource().getTrueSource().getTags().contains("crimson_invite_0")
                            || event.getSource().getTrueSource().getTags().contains("crimson_invite_1")
                            || event.getSource().getTrueSource().getTags().contains("crimson_invite_2")
                            || event.getSource().getTrueSource().getTags().contains("crimson_invite_3")
                            || event.getSource().getTrueSource().getTags().contains("crimson_invite_4")
                            || event.getSource().getTrueSource().getTags().contains("crimson_invite_3_1"))
                            && !event.getSource().getTrueSource().getTags().contains("crimson_invite_final")
                            && event.getEntityLiving() instanceof EntityVillager
            ){
                ItemStack stack = new ItemStack(ItemsKP.RESOURCECRIMSON, 1, 0);

                if (stack.getTagCompound() != null)
                ((EntityPlayer) event.getSource().getTrueSource()).addItemStackToInventory(stack);
            }
        }
    }

    @SideOnly(value = Side.CLIENT)
    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        event.getItemStack();
        NBTTagList nbttaglist = EnumInfusionEnchantmentKP.getInfusionEnchantmentTagList(event.getItemStack());
        if(nbttaglist != null) {
            for(int j = 0; j < nbttaglist.tagCount(); ++j) {
                short k = nbttaglist.getCompoundTagAt(j).getShort("id");
                short l = nbttaglist.getCompoundTagAt(j).getShort("lvl");
                if(k < 0 || k >= EnumInfusionEnchantmentKP.values().length)
                    continue;
                String s = TextFormatting.DARK_PURPLE + I18n
                        .translateToLocal("enchantment.infusion." + EnumInfusionEnchantmentKP.values()[k].toString());
                if(EnumInfusionEnchantmentKP.values()[k].maxLevel > 1) {
                    s = s + " " + I18n.translateToLocal("enchantment.level." + l);
                }
                event.getToolTip().add(1, s);
            }
        }
    }

    @SubscribeEvent
    public void onModDamage(LivingHurtEvent e) {
        if (e.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer) e.getSource().getTrueSource();
            EntityLivingBase mob = e.getEntityLiving();
            ItemStack mainHund = attacker.getHeldItemMainhand();

            if (!mainHund.isEmpty() && mainHund.getItem() instanceof ItemSword && (EnumInfusionEnchantmentKP.getInfusionEnchantmentLevel(mainHund, EnumInfusionEnchantmentKP.CRIMSONPOWER) > 0)) {
                if (!(mob instanceof EntityTaintCrawler)) {
                    attacker.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_warning_1")));
                    e.setAmount(1.0f);
                } else {
                    e.setAmount(999);
                }
            }
        }
    }

    private void addDrop(List<EntityItem> drops, Entity e, int min, int max) {
        drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, new ItemStack(ModItems.TaintCrawler, 1)));
    }

    private void addDrop1(List<EntityItem> drops, Entity e, int min, int max) {
        drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, new ItemStack(ItemsKP.RESOURCECRIMSON, 1, 1)));
    }

    @SubscribeEvent
    public void onDrops(LivingDropsEvent event) {
        Entity e = event.getEntity();
        EntityLivingBase player = (EntityLivingBase) event.getSource().getTrueSource();
        if (player instanceof EntityPlayer) {
            if (EnumInfusionEnchantmentKP.getInfusionEnchantmentLevel(player.getHeldItemMainhand(), EnumInfusionEnchantmentKP.CRIMSONPOWER) > 0)
                if (e instanceof EntityTaintCrawler) {
                    addDrop(event.getDrops(), e, 1, 1);
                }
        }
        if (player instanceof EntityPlayer && e instanceof  EntityCultistLeader) {
            if(player.getTags().contains("crimson_invite_4")){
                addDrop1(event.getDrops(), e, 1, 1);
            }
        }
    }

    @SubscribeEvent
    public void CrimsonUpgrade(LivingDeathEvent event) {
            if(event.getSource().getTrueSource() != null && event.getEntity() instanceof ThaumaturgeSpeller && event.getSource().getTrueSource().getTags().contains("crimson_invite_3_1"))
            {
                event.getSource().getTrueSource().sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_3_2")));
                event.getSource().getTrueSource().removeTag("crimson_invite_3_1");
                event.getSource().getTrueSource().addTag("crimson_invite_4");
            }
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();

        if (!entity.world.isRemote && event.getEntity() instanceof EntityMob && event.getWorld().rand.nextInt(100) == 1 && event.getEntity().isNonBoss()) {
            EntityLiving living = (EntityLiving)entity;
            EntityUtils.makeChampion((EntityMob) event.getEntity(), true);
            }
        }

        @SubscribeEvent
        public void summonthaumaturge(TickEvent.PlayerTickEvent e) {
        if (!e.player.world.isRemote && e.player.getTags().contains("crimson_invite_3_1") && e.player.ticksExisted % 12000 == 0) {
            e.player.sendMessage(new TextComponentString(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC + I18n.translateToLocal("ci_information_3_1")));
            ThaumaturgeSpeller thaumaturge = new ThaumaturgeSpeller(e.player.world);
            boolean success = false;

            for (int l = 0; l < 6; l++) {
                int targetX = (int) e.player.posX + e.player.world.rand.nextInt(100) - e.player.world.rand.nextInt(100);
                int targetY = (int) e.player.posY + e.player.world.rand.nextInt(4) - e.player.world.rand.nextInt(4);
                int targetZ = (int) e.player.posZ + e.player.world.rand.nextInt(100) - e.player.world.rand.nextInt(100);
                if (e.player.world.getBlockState(new BlockPos(targetX, targetY - 1, targetZ)).isFullCube()
                && !e.player.world.getBlockState(new BlockPos(targetX, targetY , targetZ)).isFullCube()
                && !e.player.world.getBlockState(new BlockPos(targetX, targetY + 1, targetZ)).isFullCube()
                ) {
                    thaumaturge.setPosition((double) targetX, (double) targetY, (double) targetZ);
                    thaumaturge.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 20000, 0));
                    success = true;
                    break;
                }
            }

            if (success) {
                e.player.world.spawnEntity(thaumaturge);
            }
        }
    }
}