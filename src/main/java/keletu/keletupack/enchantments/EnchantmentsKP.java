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
    public static final Enchantment ascentboost = new EnchantmentAscentBoost(202);
    public static final Enchantment pounce = new EnchantmentPounce(203);
    public static final Enchantment shockwave = new EnchantmentShockwave(204);
    public static final Enchantment slowfall = new EnchantmentSlowFall(205);
    public static final Enchantment finalStrike = new EnchantmentFinalStrike(206);
    public static final Enchantment valiance = new EnchantmentValiance(207);
    public static final Enchantment vampirism = new EnchantmentVampirism(208);


}
