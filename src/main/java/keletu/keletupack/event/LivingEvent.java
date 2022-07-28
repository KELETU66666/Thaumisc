package keletu.keletupack.event;

import com.google.common.collect.Multimap;
import keletu.keletupack.ConfigKP;
import keletu.keletupack.enchantments.EnchantmentsKP;
import keletu.keletupack.items.armor.KamiArmor;
import keletu.keletupack.items.tools.DistortionPick;
import keletu.keletupack.items.tools.IchoriumPickAdv;
import keletu.keletupack.util.Reference;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class LivingEvent {
    public static void register(ResourceLocation resourceLocation) {
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase != TickEvent.Phase.END)
            return;
        NBTTagCompound nbt = e.player.getEntityData();
        if (nbt.getBoolean("can_fly")) {
            e.player.capabilities.allowFlying = true;
            nbt.setBoolean("can_fly", false);
        } else if (nbt.hasKey("can_fly")) {
            if (!e.player.capabilities.isCreativeMode) {
                e.player.capabilities.allowFlying = false;
                e.player.capabilities.isFlying = false;
            }
            nbt.removeTag("can_fly");
        }
    }

    @SubscribeEvent
    public void playerJumps(net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent event)
    {
        ItemStack stack = event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(event.getEntity() instanceof EntityPlayer && stack.getItem() instanceof KamiArmor && stack.getItemDamage() != 1)
            event.getEntityLiving().motionY += 0.2750000059604645 * 1.25;
    }


    @SubscribeEvent
    public void fall(LivingFallEvent e)
    {
        ItemStack boots = e.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(!boots.isEmpty() && boots.getItem() instanceof KamiArmor && boots.getItemDamage() != 1)
        {
            e.setDamageMultiplier(0);
            e.setCanceled(true);
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
        if (event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
            ((EntityPlayer) event.getSource().getTrueSource()).getHeldEquipment();
            ItemStack equip = ((EntityPlayer) event.getSource().getTrueSource()).getHeldItem(EnumHand.MAIN_HAND);

            if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.educational, equip) > 0 && event.getEntityLiving() instanceof EntityLiving && EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, equip) == 0) {
                try {
                    int learning = 3 * EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.educational, equip);
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
            if(EnchantmentHelper.getEnchantmentLevel(EnchantmentsKP.consuming, equip) > 0) {
                for (int x = 0; x < event.getDrops().size(); x++) {
                    ItemStack drop = (ItemStack) event.getDrops().get(x);
                    if (drop != null && isGarbage(drop))
                        event.getDrops().remove(x);
                }
            }
        }
    }

    private boolean isGarbage(ItemStack drop) {
        for(int id : OreDictionary.getOreIDs(drop)) {
            for(String ore : ConfigKP.ConfigKP.trashpile) {
                if(OreDictionary.getOreName(id).equals(ore))
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
                if (event.getEntityLiving() instanceof EntityVillager){
                    addDrop(event, new ItemStack(Items.EMERALD, 1, 0));}
                else if (rand.nextInt(35) < 3)
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
}