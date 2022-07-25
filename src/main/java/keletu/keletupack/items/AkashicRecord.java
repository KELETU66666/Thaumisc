package keletu.keletupack.items;

import keletu.keletupack.init.ModItems;
import keletu.keletupack.keletupack;
import keletu.keletupack.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;

public class AkashicRecord extends Item implements IHasModel{

    public AkashicRecord() {
        super();
        setUnlocalizedName("akashic_record");
        setRegistryName("akashic_record");
        setCreativeTab(keletupack.ITEM_TAB);

        ModItems.ITEMS.add(this);

    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
        if (!world.isRemote) {
            ItemStack item = player.getHeldItem(handIn);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("BASICS"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("BASICS"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("AUROMANCY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("AUROMANCY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ALCHEMY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("GOLEMANCY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("GOLEMANCY"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ELDRITCH"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ELDRITCH"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("INFUSION"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("INFUSION"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), 999);
            ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ARTIFICE"), 999);
            if (!player.capabilities.isCreativeMode) {
                item.setCount(item.getCount() - 1);
            }

        }
        return super.onItemRightClick(world, player, handIn);
        }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void registerModels() {
        keletupack.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
