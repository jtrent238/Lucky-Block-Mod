package mod.lucky.util;

import net.minecraft.world.World;
import net.minecraft.entity.item.EntityFireworkRocket;
import java.util.Iterator;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;

public class CustomNBTTags
{
    public static final NBTTagCompound protection;
    public static final NBTTagCompound fireProtection;
    public static final NBTTagCompound featherFalling;
    public static final NBTTagCompound blastProtection;
    public static final NBTTagCompound projectileProtection;
    public static final NBTTagCompound respiration;
    public static final NBTTagCompound aquaAffinity;
    public static final NBTTagCompound thorns;
    public static final NBTTagCompound sharpness;
    public static final NBTTagCompound smite;
    public static final NBTTagCompound baneOfArthroponds;
    public static final NBTTagCompound knockBack;
    public static final NBTTagCompound fireAspect;
    public static final NBTTagCompound looting;
    public static final NBTTagCompound efficiency;
    public static final NBTTagCompound silkTouch;
    public static final NBTTagCompound unbreaking;
    public static final NBTTagCompound fortune;
    public static final NBTTagCompound power;
    public static final NBTTagCompound punch;
    public static final NBTTagCompound flame;
    public static final NBTTagCompound infinity;
    public static final NBTTagCompound luckOfTheSea;
    public static final NBTTagCompound lure;
    public static final NBTTagCompound speed;
    public static final NBTTagCompound slowness;
    public static final NBTTagCompound haste;
    public static final NBTTagCompound miningFatigue;
    public static final NBTTagCompound strength;
    public static final NBTTagCompound instantHealth;
    public static final NBTTagCompound instantDamage;
    public static final NBTTagCompound jumpBoost;
    public static final NBTTagCompound nausea;
    public static final NBTTagCompound regeneration;
    public static final NBTTagCompound resistance;
    public static final NBTTagCompound fireResistance;
    public static final NBTTagCompound waterBreathing;
    public static final NBTTagCompound invisibility;
    public static final NBTTagCompound blindness;
    public static final NBTTagCompound nightVision;
    public static final NBTTagCompound hunger;
    public static final NBTTagCompound weakness;
    public static final NBTTagCompound poison;
    public static final NBTTagCompound wither;
    public static final NBTTagCompound healthBoost;
    public static final NBTTagCompound absorbtion;
    public static final NBTTagCompound saturation;
    public static Random random;
    
    public static NBTTagCompound getEnchantment(final int a1, final int a2) {
        final NBTTagCompound v1 = /*EL:70*/new NBTTagCompound();
        /*SL:72*/v1.func_74777_a("id", (short)a1);
        /*SL:73*/v1.func_74777_a("lvl", (short)a2);
        /*SL:75*/return v1;
    }
    
    public static NBTTagCompound getPotionEffect(final int a1, final int a2, final int a3) {
        final NBTTagCompound v1 = /*EL:80*/new NBTTagCompound();
        /*SL:82*/v1.func_74774_a("Id", (byte)a1);
        /*SL:83*/v1.func_74774_a("Amplifier", (byte)a2);
        /*SL:84*/v1.func_74768_a("Duration", a3);
        /*SL:86*/return v1;
    }
    
    public static ArrayList<NBTTagCompound> getRandomList(final int v-6, final int v-5, final NBTTagCompound... v-4) {
        final int n = /*EL:91*/v-4.length - (CustomNBTTags.random.nextInt(v-5 + 1 - v-6) + v-6);
        final ArrayList<NBTTagCompound> list = /*EL:93*/new ArrayList<NBTTagCompound>(v-4.length);
        /*SL:94*/for (final NBTTagCompound a2 : v-4) {
            /*SL:96*/list.add((NBTTagCompound)a2.func_74737_b());
        }
        /*SL:99*/for (int i = 0; i < n; ++i) {
            final int v2 = CustomNBTTags.random.nextInt(/*EL:101*/list.size());
            /*SL:102*/list.remove(v2);
        }
        /*SL:105*/return list;
    }
    
    public static NBTTagList getRandomEnchantmentList(final int a3, final int v1, final NBTTagCompound... v2) {
        final ArrayList<NBTTagCompound> v3 = getRandomList(/*EL:110*/a3, v1, v2);
        final NBTTagList v4 = /*EL:112*/new NBTTagList();
        /*SL:113*/for (final NBTTagCompound a5 : v3) {
            /*SL:115*/a5.func_74777_a("lvl", (short)(CustomNBTTags.random.nextInt(a5.func_74765_d("lvl")) + 1));
            /*SL:116*/v4.func_74742_a((NBTBase)a5);
        }
        /*SL:119*/return v4;
    }
    
    public static NBTTagList getRandomPotionEffectList(final int v1, final int v2, final NBTTagCompound... v3) {
        final ArrayList<NBTTagCompound> v4 = getRandomList(/*EL:124*/v1, v2, v3);
        final NBTTagList v5 = /*EL:126*/new NBTTagList();
        /*SL:127*/for (final NBTTagCompound a2 : v4) {
            /*SL:129*/a2.func_74774_a("Amplifier", (byte)CustomNBTTags.random.nextInt(a2.func_74771_c("Amplifier") + 1));
            final Iterator a3 = /*EL:130*/(Iterator)(int)(a2.func_74762_e("Duration") / 3.0f);
            /*SL:131*/a2.func_74768_a("Duration", CustomNBTTags.random.nextInt(a2.func_74762_e("Duration") + 1 - a3) + a3);
            /*SL:132*/v5.func_74742_a((NBTBase)a2);
        }
        /*SL:135*/return v5;
    }
    
    public static NBTBase getNBTTagFromString(final String v-1) {
        /*SL:140*/if (v-1.equals("#luckySwordEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.unbreaking);
        }
        /*SL:141*/if (v-1.equals("#luckyAxeEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:142*/if (v-1.equals("#luckyToolEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:144*/if (v-1.equals("#luckyHelmetEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.respiration, CustomNBTTags.aquaAffinity, CustomNBTTags.unbreaking);
        }
        /*SL:145*/if (v-1.equals("#luckyChestplateEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:146*/if (v-1.equals("#luckyLeggingsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:147*/if (v-1.equals("#luckyBootsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:149*/if (v-1.equals("#luckyBowEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(3, 5, CustomNBTTags.unbreaking, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity);
        }
        /*SL:150*/if (v-1.equals("#luckyFishingRodEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.unbreaking, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:152*/if (v-1.equals("#randomEnchantment")) {
            return (NBTBase)getRandomEnchantmentList(1, 1, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.efficiency, CustomNBTTags.silkTouch, CustomNBTTags.unbreaking, CustomNBTTags.fortune, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:154*/if (v-1.equals("#luckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(7, 10, CustomNBTTags.speed, CustomNBTTags.haste, CustomNBTTags.strength, CustomNBTTags.instantHealth, CustomNBTTags.jumpBoost, CustomNBTTags.regeneration, CustomNBTTags.resistance, CustomNBTTags.fireResistance, CustomNBTTags.waterBreathing, CustomNBTTags.invisibility, CustomNBTTags.nightVision, CustomNBTTags.healthBoost, CustomNBTTags.absorbtion, CustomNBTTags.saturation);
        }
        /*SL:155*/if (v-1.equals("#unluckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(5, 7, CustomNBTTags.slowness, CustomNBTTags.miningFatigue, CustomNBTTags.instantDamage, CustomNBTTags.nausea, CustomNBTTags.blindness, CustomNBTTags.hunger, CustomNBTTags.weakness, CustomNBTTags.poison, CustomNBTTags.wither);
        }
        /*SL:157*/if (v-1.equals("#randomFireworkRocket")) {
            final EntityFireworkRocket a1 = /*EL:159*/new EntityFireworkRocket((World)null, 0.0, 0.0, 0.0, LuckyFunction.getRandomFireworkRocket());
            final NBTTagCompound v1 = /*EL:160*/new NBTTagCompound();
            /*SL:161*/a1.func_70014_b(v1);
            /*SL:162*/return (NBTBase)v1;
        }
        /*SL:165*/return null;
    }
    
    static {
        protection = getEnchantment(0, 4);
        fireProtection = getEnchantment(1, 4);
        featherFalling = getEnchantment(2, 4);
        blastProtection = getEnchantment(3, 4);
        projectileProtection = getEnchantment(4, 4);
        respiration = getEnchantment(5, 3);
        aquaAffinity = getEnchantment(6, 1);
        thorns = getEnchantment(7, 3);
        sharpness = getEnchantment(16, 5);
        smite = getEnchantment(17, 5);
        baneOfArthroponds = getEnchantment(18, 5);
        knockBack = getEnchantment(19, 2);
        fireAspect = getEnchantment(20, 2);
        looting = getEnchantment(21, 3);
        efficiency = getEnchantment(32, 5);
        silkTouch = getEnchantment(33, 1);
        unbreaking = getEnchantment(34, 3);
        fortune = getEnchantment(35, 3);
        power = getEnchantment(48, 5);
        punch = getEnchantment(49, 2);
        flame = getEnchantment(50, 1);
        infinity = getEnchantment(51, 1);
        luckOfTheSea = getEnchantment(61, 3);
        lure = getEnchantment(62, 3);
        speed = getPotionEffect(1, 3, 9600);
        slowness = getPotionEffect(2, 3, 9600);
        haste = getPotionEffect(3, 3, 9600);
        miningFatigue = getPotionEffect(4, 3, 9600);
        strength = getPotionEffect(5, 3, 9600);
        instantHealth = getPotionEffect(6, 3, 0);
        instantDamage = getPotionEffect(7, 3, 0);
        jumpBoost = getPotionEffect(8, 3, 9600);
        nausea = getPotionEffect(9, 0, 9600);
        regeneration = getPotionEffect(10, 3, 9600);
        resistance = getPotionEffect(11, 3, 9600);
        fireResistance = getPotionEffect(12, 0, 9600);
        waterBreathing = getPotionEffect(13, 0, 9600);
        invisibility = getPotionEffect(14, 0, 9600);
        blindness = getPotionEffect(15, 0, 9600);
        nightVision = getPotionEffect(16, 0, 9600);
        hunger = getPotionEffect(17, 3, 9600);
        weakness = getPotionEffect(18, 3, 9600);
        poison = getPotionEffect(19, 3, 9600);
        wither = getPotionEffect(20, 3, 9600);
        healthBoost = getPotionEffect(21, 3, 9600);
        absorbtion = getPotionEffect(22, 3, 9600);
        saturation = getPotionEffect(23, 3, 9600);
        CustomNBTTags.random = new Random();
    }
}
