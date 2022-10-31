package keletu.keletupack.enchantments.inchantment;

import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;

import java.util.*;

public class Aspects extends AspectList implements Comparable<AspectList> {
    public static HashMap<String, Aspect> compatAspects = new HashMap<>();
    public static HashMap<String, Aspect[]> ingotAspects = new HashMap<>();
    public static ArrayList<Aspect> yangAspects = new ArrayList<>();
    public static ArrayList<Aspect> yinAspects = new ArrayList<>();

    // Like a billion constructor!! lmao
    public Aspects() { super(); }
    public Aspects(ItemStack stack) { super(stack); }
    public Aspects(String a1, int i1) { this(new String[]{a1}, new int[]{i1}); }
    public Aspects(String a1, int i1, String a2, int i2) { this(new String[]{a1, a2}, new int[]{i1, i2}); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3) { this(new String[]{a1, a2, a3}, new int[]{i1, i2, i3}); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4) { this(new String[]{a1, a2, a3, a4}, new int[]{i1, i2, i3, i4}); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5) { this(new String[]{a1, a2, a3, a4, a5}, new int[]{i1, i2, i3, i4, i5}); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5, String a6, int i6) { this(new String[]{a1, a2, a3, a4, a5, a6}, new int[]{i1, i2, i3, i4, i5, i6}); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5, String a6, int i6, String a7, int i7) { this(new String[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{i1, i2, i3, i4, i5, i6, i7}); }
    public Aspects(String a1) { this(new String[]{a1}, new int[]{1}); }
    public Aspects(String a1, String a2) { this(new String[]{a1, a2}, new int[]{1, 1}); }
    public Aspects(String a1, String a2, String a3) { this(new String[]{a1, a2, a3}, new int[]{1, 1, 1}); }
    public Aspects(String a1, String a2, String a3, String a4) { this(new String[]{a1, a2, a3, a4}, new int[]{1, 1, 1, 1}); }
    public Aspects(String a1, String a2, String a3, String a4, String a5) { this(new String[]{a1, a2, a3, a4, a5}, new int[]{1, 1, 1, 1, 1}); }
    public Aspects(String a1, String a2, String a3, String a4, String a5, String a6) { this(new String[]{a1, a2, a3, a4, a5, a6}, new int[]{1, 1, 1, 1, 1, 1}); }
    public Aspects(String a1, String a2, String a3, String a4, String a5, String a6, String a7) { this(new String[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{1, 1, 1, 1, 1, 1, 1}); }
    public Aspects(String[] aspects) { this(aspects, fillOnes(aspects.length)); }
    public Aspects(String a1, int i1, HashMap<String, Aspect> custom) { this(new String[]{a1}, new int[]{i1}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, HashMap<String, Aspect> custom) { this(new String[]{a1, a2}, new int[]{i1, i2}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3}, new int[]{i1, i2, i3}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4}, new int[]{i1, i2, i3, i4}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5}, new int[]{i1, i2, i3, i4, i5}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5, String a6, int i6, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5, a6}, new int[]{i1, i2, i3, i4, i5, i6}, custom); }
    public Aspects(String a1, int i1, String a2, int i2, String a3, int i3, String a4, int i4, String a5, int i5, String a6, int i6, String a7, int i7, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{i1, i2, i3, i4, i5, i6, i7}, custom); }
    public Aspects(String a1, HashMap<String, Aspect> custom) { this(new String[]{a1}, new int[]{1}, custom); }
    public Aspects(String a1, String a2, HashMap<String, Aspect> custom) { this(new String[]{a1, a2}, new int[]{1, 1}, custom); }
    public Aspects(String a1, String a2, String a3, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3}, new int[]{1, 1, 1}, custom); }
    public Aspects(String a1, String a2, String a3, String a4, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4}, new int[]{1, 1, 1, 1}, custom); }
    public Aspects(String a1, String a2, String a3, String a4, String a5, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5}, new int[]{1, 1, 1, 1, 1}, custom); }
    public Aspects(String a1, String a2, String a3, String a4, String a5, String a6, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5, a6}, new int[]{1, 1, 1, 1, 1, 1}, custom); }
    public Aspects(String a1, String a2, String a3, String a4, String a5, String a6, String a7, HashMap<String, Aspect> custom) { this(new String[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{1, 1, 1, 1, 1, 1, 1}, custom); }
    public Aspects(String[] aspects, HashMap<String, Aspect> custom) { this(aspects, fillOnes(aspects.length), custom); }
    public Aspects(AspectList al) { super(); this.add(al); }
    public Aspects(String[] a, int[] i) { this(a, i, new HashMap<>()); }
    public Aspects(String[] aspects, int[] amounts, HashMap<String, Aspect> custom) {
        super();
        assert aspects.length == amounts.length;
        for (int i = 0; i < amounts.length; i++) {
            Aspect aspect;
            if (exists(aspects[i])) aspect = compatAspects.get(aspects[i]);
            else if (custom.containsKey(aspects[i])) aspect = custom.get(aspects[i]);
            else continue; // default: all nulls
            add(aspect, amounts[i]);
        }
    }
    public Aspects(Aspect a1, int i1) { this(new Aspect[]{a1}, new int[]{i1}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2) { this(new Aspect[]{a1, a2}, new int[]{i1, i2}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2, Aspect a3, int i3) { this(new Aspect[]{a1, a2, a3}, new int[]{i1, i2, i3}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2, Aspect a3, int i3, Aspect a4, int i4) { this(new Aspect[]{a1, a2, a3, a4}, new int[]{i1, i2, i3, i4}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2, Aspect a3, int i3, Aspect a4, int i4, Aspect a5, int i5) { this(new Aspect[]{a1, a2, a3, a4, a5}, new int[]{i1, i2, i3, i4, i5}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2, Aspect a3, int i3, Aspect a4, int i4, Aspect a5, int i5, Aspect a6, int i6) { this(new Aspect[]{a1, a2, a3, a4, a5, a6}, new int[]{i1, i2, i3, i4, i5, i6}); }
    public Aspects(Aspect a1, int i1, Aspect a2, int i2, Aspect a3, int i3, Aspect a4, int i4, Aspect a5, int i5, Aspect a6, int i6, Aspect a7, int i7) { this(new Aspect[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{i1, i2, i3, i4, i5, i6, i7}); }
    public Aspects(Aspect a1) { this(new Aspect[]{a1}, new int[]{1}); }
    public Aspects(Aspect a1, Aspect a2) { this(new Aspect[]{a1, a2}, new int[]{1, 1}); }
    public Aspects(Aspect a1, Aspect a2, Aspect a3) { this(new Aspect[]{a1, a2, a3}, new int[]{1, 1, 1}); }
    public Aspects(Aspect a1, Aspect a2, Aspect a3, Aspect a4) { this(new Aspect[]{a1, a2, a3, a4}, new int[]{1, 1, 1, 1}); }
    public Aspects(Aspect a1, Aspect a2, Aspect a3, Aspect a4, Aspect a5) { this(new Aspect[]{a1, a2, a3, a4, a5}, new int[]{1, 1, 1, 1, 1}); }
    public Aspects(Aspect a1, Aspect a2, Aspect a3, Aspect a4, Aspect a5, Aspect a6) { this(new Aspect[]{a1, a2, a3, a4, a5, a6}, new int[]{1, 1, 1, 1, 1, 1}); }
    public Aspects(Aspect a1, Aspect a2, Aspect a3, Aspect a4, Aspect a5, Aspect a6, Aspect a7) { this(new Aspect[]{a1, a2, a3, a4, a5, a6, a7}, new int[]{1, 1, 1, 1, 1, 1, 1}); }
    public Aspects(Aspect[] aspects) { this(aspects, fillOnes(aspects.length)); }
    public Aspects(Aspect[] aspects, int[] amounts) {
        super();
        assert aspects.length == amounts.length;
        for (int i = 0; i < amounts.length; i++) add(aspects[i], amounts[i]);
    }


    // utils

    public static Aspects getPrimals() {
        return new Aspects(Aspect.FIRE, Aspect.WATER, Aspect.AIR, Aspect.EARTH, Aspect.ORDER, Aspect.ENTROPY);
    }

    public Aspect[] getYinAspects() {
        ArrayList<Aspect> ret = new ArrayList<>();
        for (Aspect a : getAspects()) if (yinAspects.contains(a)) ret.add(a);
        return ret.toArray(new Aspect[0]);
    }

    public Aspect[] getYangAspects() {
        ArrayList<Aspect> ret = new ArrayList<>();
        for (Aspect a : getAspects()) if (yangAspects.contains(a)) ret.add(a);
        return ret.toArray(new Aspect[0]);
    }

    public static Aspect[] getIngotAspect(String ingot) { return ingotAspects.getOrDefault(WordUtils.capitalizeFully(ingot), null); }
    public static String getIngot(Aspect ...aspect) {
        master:
        for (Map.Entry<String, Aspect[]> e : ingotAspects.entrySet()) {
            Aspect[] s = e.getValue();
            if (s.length != aspect.length) continue;
            List<Aspect> aspects = Arrays.asList(aspect);
            for (Aspect a : s) if (!aspects.contains(a)) continue master;
            return e.getKey();
        }
        return null;
    }

    public static int getReducedPrimalAmount(Aspect aspect) { return new Aspects(aspect).getReducedPrimalAmount(); }
    public static int getReducedPrimalAmount(String aspect) { return new Aspects(aspect).getReducedPrimalAmount(); }
    public int getReducedPrimalAmount() {
        return AspectHelper.reduceToPrimals(this).visSize();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder text = new StringBuilder();
        text.append("[");
        for (Aspect a : getAspects()) {
            text.append("<aspect:").append(a.getTag()).append("> * ").append(getAmount(a)).append(", ");
        }
        return text.substring(0, text.length() - 2) + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AspectList)) return false;
        return ((AspectList) obj).aspects.equals(aspects);
    }
    @Override
    protected Object clone() { return copy(); }
    @Override
    public int compareTo(AspectList o) { return visSize() - o.visSize(); }
    public boolean isEmpty() { return getAspects().length == 0; }

    public int getAmount(String key) { return super.getAmount(Aspect.getAspect(key)); }
    public boolean reduce(String key, int amount) { if (key == null) return false; return super.reduce(Aspect.getAspect(key), amount); }
    public Aspects remove(String key, int amount) { if (key == null) return this; return remove(Aspect.getAspect(key), amount);}
    public Aspects remove(String aspect, int amount, String fallback) { return remove(aspect, amount, fallback, amount); }
    public Aspects remove(String aspect, int amount, String fallback, int amount2) {
        if (exists(aspect)) return remove(aspect, amount);
        else if (fallback == null) return this;
        else return remove(fallback, amount2);
    }
    public Aspects remove(Aspect key) { return remove(key, getAmount(key)); }
    public Aspects remove(String key) { return remove(key, getAmount(key)); }
    public Aspects remove(String aspect, String fallback) {
        if (exists(aspect)) return remove(aspect);
        else if (fallback == null) return this;
        else return remove(fallback);
    }
    public Aspects remove(Aspect key, int amount) {
        int a = getAmount(key) - amount;
        if (a <= 0) aspects.remove(key);
        else aspects.put(key, a);
        return this;
    }
    public Aspects add(String aspect, int amount) { if (aspect == null) return this; return add(Aspect.getAspect(aspect), amount); }
    public Aspects add(String aspect, int amount, String fallback) { return add(aspect, amount, fallback, amount); }
    public Aspects add(String aspect, int amount, String fallback, int amount2) {
        if (exists(aspect)) return add(aspect, amount);
        else if (fallback == null) return this;
        else return add(fallback, amount2);
    }
    public Aspects add(Aspect aspect, int amount) {
        this.aspects.put(aspect, getAmount(aspect) + amount);
        return this;
    }
    public Aspects merge(String aspect, int amount) { if (aspect == null) return this; return merge(Aspect.getAspect(aspect), amount); }
    public Aspects merge(String aspect, int amount, String fallback) { return merge(aspect, amount, fallback, amount); }
    public Aspects merge(String aspect, int amount, String fallback, int amount2) {
        if (exists(aspect)) return merge(aspect, amount);
        else if (fallback == null) return this;
        else return merge(fallback, amount2);
    }
    public Aspects merge(Aspect aspect, int amount) {
        if (amount > getAmount(aspect)) this.aspects.put(aspect, amount);
        return this;
    }

    @Override
    public Aspects merge(AspectList in) {
        if (in == null) return this;
        this.aspects = super.merge(in).aspects; return this;
    }

    @Override
    public Aspects add(AspectList in) {
        if (in == null) return this;
        this.aspects = super.add(in).aspects; return this;
    }

    @Override
    public Aspects remove(AspectList in) {
        if (in == null) return this;
        this.aspects = super.remove(in).aspects; return this;
    }

    public Aspects multiply(float m) {
        for (Aspect aspect : getAspects())
            aspects.put(aspect, Math.round(m * getAmount(aspect)));
        return this;
    }

    public String[] tags() {
        return Arrays.stream(getAspects()).map(Aspect::getTag).toArray(String[]::new);
    }

    public static int[] fillOnes(int length) {
        int[] ret = new int[length];
        Arrays.fill(ret, 1);
        return ret;
    }

    @Override
    public Aspects copy() {
        return new Aspects(this);
    }

    public static boolean exists(String aspect) { return aspect != null && compatAspects.containsKey(aspect); }
}