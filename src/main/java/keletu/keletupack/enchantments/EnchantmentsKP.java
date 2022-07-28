package keletu.keletupack.enchantments;

import keletu.keletupack.util.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid= Reference.MOD_ID)
public class EnchantmentsKP {

    public static final List<Enchantment> ENCHANTNENTS = new ArrayList<Enchantment>();
    public static final Enchantment wrath = new EnchantmentWrath(197);
    public static final Enchantment greedy = new EnchantmentGreedy(198);
    public static final Enchantment consuming = new EnchantmentConsuming(199);
    public static final Enchantment educational = new EnchantmentEducational(200);
    public static final Enchantment voidtouched = new EnchantmentVoid(201);

}
