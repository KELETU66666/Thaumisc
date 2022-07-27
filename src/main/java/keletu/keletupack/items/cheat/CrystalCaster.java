package keletu.keletupack.items.cheat;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Optional;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.common.items.casters.ItemCaster;
import thaumcraft.common.world.aura.AuraHandler;
import thecodex6824.thaumicaugmentation.api.augment.AugmentableItem;
import thecodex6824.thaumicaugmentation.api.augment.CapabilityAugmentableItem;
import thecodex6824.thaumicaugmentation.api.augment.IAugmentableItem;
import thecodex6824.thaumicaugmentation.common.capability.provider.SimpleCapabilityProvider;

public class CrystalCaster extends ItemCaster implements IHasModel, IVisDiscountGear {

    public CrystalCaster() {
        super("crystal_caster", 0);
        this.setCreativeTab(keletupack.ITEM_TAB);
        ModItems.ITEMS.add(this);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        float i = AuraHelper.getVis(world, entity.getPosition());
        float k = AuraHelper.getAuraBase(world, entity.getPosition());
        float f = AuraHelper.getFlux(world, entity.getPosition());
        if (entity instanceof EntityPlayer && world.isRemote) {
            if (i < k * 1.25 && ((EntityPlayer) entity).getHeldItem(EnumHand.MAIN_HAND).getItem() == this) {
                AuraHelper.addVis(world, entity.getPosition(), k - i );
                AuraHelper.drainFlux(world, entity.getPosition(), f, false);
            }
        }
    }
    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 90;
    }

    @Override
    @Optional.Method(modid = "thaumicaugmentation")
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        SimpleCapabilityProvider<IAugmentableItem> provider =
                new SimpleCapabilityProvider<>(new AugmentableItem(999), CapabilityAugmentableItem.AUGMENTABLE_ITEM);
        if (nbt != null && nbt.hasKey("Parent", Constants.NBT.TAG_COMPOUND))
            provider.deserializeNBT(nbt.getCompoundTag("Parent"));

        return provider;
    }
}