package keletu.keletupack.enchantments.inchantment;


import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.IRechargable;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class InfusionEnchantmentRecipeKP extends InfusionRecipe{
    EnumInfusionEnchantmentKP enchantment;

    public InfusionEnchantmentRecipeKP(EnumInfusionEnchantmentKP ench, AspectList as, Object... components){
        super(ench.research, null, 4, as, Ingredient.EMPTY, components);
        this.enchantment = ench;
    }

    public InfusionEnchantmentRecipeKP(InfusionEnchantmentRecipeKP recipe, ItemStack in){
        super(recipe.enchantment.research, null, recipe.instability, recipe.aspects, in, recipe.components.toArray());
        this.enchantment = recipe.enchantment;
    }

    @Override
    public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player){
        if(central == null || central.isEmpty() || !ThaumcraftCapabilities.knowsResearch(player, this.research)){ return false; }
        if(EnumInfusionEnchantmentKP.getInfusionEnchantmentLevel(central, this.enchantment) >= this.enchantment.maxLevel){ return false; }
        if(!this.enchantment.toolClasses.contains("all")){
            String at;
            Multimap<String, AttributeModifier> itemMods = central.getAttributeModifiers(EntityEquipmentSlot.MAINHAND);
            boolean cool = itemMods.containsKey(SharedMonsterAttributes.ATTACK_DAMAGE.getName()) && this.enchantment.toolClasses.contains("weapon");
            if(!cool && central.getItem() instanceof ItemTool){
                Set<String> tcs = central.getItem().getToolClasses(central);
                for(String tc : tcs){
                    if(!this.enchantment.toolClasses.contains(tc))
                        continue;
                    cool = true;
                    break;
                }
            }
            if(!cool && central.getItem() instanceof ItemSword){
                at = "blade";
                if(this.enchantment.toolClasses.contains("blade") || this.enchantment.toolClasses.contains(at)){
                    cool = true;
                }
            }
            if(!cool && central.getItem() instanceof IRechargable && this.enchantment.toolClasses.contains("chargable")){
                cool = true;
            }
            if(!cool){ return false; }
        }
        return (this.getRecipeInput() == Ingredient.EMPTY || this.getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, this.getComponents()) != null;
    }

    @Override
    public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps){
        if(input == null)
            return null;
        ItemStack out = input.copy();
        int cl = EnumInfusionEnchantmentKP.getInfusionEnchantmentLevel(out, this.enchantment);
        if(cl >= this.enchantment.maxLevel)
            return null;
        List<EnumInfusionEnchantmentKP> el = EnumInfusionEnchantmentKP.getInfusionEnchantments(input);
        Random rand = new Random(System.nanoTime());
        if(rand.nextInt(10) < el.size()){
            int base = 1;
            if(input.hasTagCompound()){
                assert input.getTagCompound() != null;
                base += input.getTagCompound().getByte("TC.WARP");
            }
            out.setTagInfo("TC.WARP", (NBTBase)new NBTTagByte((byte)base));
        }
        EnumInfusionEnchantmentKP.addInfusionEnchantment(out, this.enchantment, cl + 1);
        return out;
    }

    @Override
    public Aspects getAspects(EntityPlayer player, ItemStack input, List<ItemStack> comps){
        Aspects out = new Aspects();
        if(input == null || input.isEmpty())
            return out;
        int cl = EnumInfusionEnchantmentKP.getInfusionEnchantmentLevel(input, this.enchantment) + 1;
        if(cl > this.enchantment.maxLevel)
            return out;
        List<EnumInfusionEnchantmentKP> el = EnumInfusionEnchantmentKP.getInfusionEnchantments(input);
        int otherEnchantments = el.size();
        if(el.contains(this.enchantment)){
            --otherEnchantments;
        }
        float modifier = cl + otherEnchantments * 0.33f;
        for(Aspect a : this.getAspects().getAspects()){
            out.add(a, (int)(this.getAspects().getAmount(a) * modifier));
        }
        return out;
    }
}