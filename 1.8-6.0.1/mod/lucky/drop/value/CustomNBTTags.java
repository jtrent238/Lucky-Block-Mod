package mod.lucky.drop.value;

import net.minecraftforge.common.ChestGenHooks;
import java.util.Locale;
import net.minecraft.inventory.IInventory;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.MathHelper;
import mod.lucky.util.LuckyFunction;
import mod.lucky.drop.func.DropProcessData;
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
    public static String[] nbtHashVariables;
    
    public static NBTTagCompound getEnchantment(final int a1, final int a2) {
        final NBTTagCompound v1 = /*EL:79*/new NBTTagCompound();
        /*SL:81*/v1.func_74777_a("id", (short)a1);
        /*SL:82*/v1.func_74777_a("lvl", (short)a2);
        /*SL:84*/return v1;
    }
    
    public static NBTTagCompound getPotionEffect(final int a1, final int a2, final int a3) {
        final NBTTagCompound v1 = /*EL:89*/new NBTTagCompound();
        /*SL:91*/v1.func_74774_a("Id", (byte)a1);
        /*SL:92*/v1.func_74774_a("Amplifier", (byte)a2);
        /*SL:93*/v1.func_74768_a("Duration", a3);
        /*SL:95*/return v1;
    }
    
    public static ArrayList<NBTTagCompound> getRandomList(final int v1, final int v2, final NBTTagCompound... v3) {
        final int v4 = /*EL:100*/v3.length - (CustomNBTTags.random.nextInt(v2 + 1 - v1) + v1);
        final ArrayList<NBTTagCompound> v5 = /*EL:102*/new ArrayList<NBTTagCompound>(v3.length);
        /*SL:103*/for (final NBTTagCompound a1 : v3) {
            /*SL:105*/v5.add((NBTTagCompound)a1.func_74737_b());
        }
        /*SL:108*/for (int a2 = 0; a2 < v4; ++a2) {
            final int a3 = CustomNBTTags.random.nextInt(/*EL:110*/v5.size());
            /*SL:111*/v5.remove(a3);
        }
        /*SL:114*/return v5;
    }
    
    public static NBTTagList getRandomEnchantmentList(final int a2, final int a3, final NBTTagCompound... v1) {
        final ArrayList<NBTTagCompound> v2 = getRandomList(/*EL:119*/a2, a3, v1);
        final NBTTagList v3 = /*EL:121*/new NBTTagList();
        /*SL:122*/for (final NBTTagCompound a4 : v2) {
            /*SL:124*/a4.func_74777_a("lvl", (short)(CustomNBTTags.random.nextInt(a4.func_74765_d("lvl")) + 1));
            /*SL:125*/v3.func_74742_a((NBTBase)a4);
        }
        /*SL:128*/return v3;
    }
    
    public static NBTTagList getRandomPotionEffectList(final int a3, final int v1, final NBTTagCompound... v2) {
        final ArrayList<NBTTagCompound> v3 = getRandomList(/*EL:133*/a3, v1, v2);
        final NBTTagList v4 = /*EL:135*/new NBTTagList();
        /*SL:136*/for (final NBTTagCompound a4 : v3) {
            /*SL:138*/a4.func_74774_a("Amplifier", (byte)CustomNBTTags.random.nextInt(a4.func_74771_c("Amplifier") + 1));
            final int a5 = /*EL:139*/(int)(a4.func_74762_e("Duration") / 3.0f);
            /*SL:140*/a4.func_74768_a("Duration", CustomNBTTags.random.nextInt(a4.func_74762_e("Duration") + 1 - a5) + a5);
            /*SL:141*/v4.func_74742_a((NBTBase)a4);
        }
        /*SL:144*/return v4;
    }
    
    public static NBTBase getNBTTagFromString(final String v-1, final DropProcessData v0) {
        /*SL:153*/if (v-1.equals("#luckySwordEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.unbreaking);
        }
        /*SL:154*/if (v-1.equals("#luckyAxeEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:155*/if (v-1.equals("#luckyToolEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:157*/if (v-1.equals("#luckyHelmetEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.respiration, CustomNBTTags.aquaAffinity, CustomNBTTags.unbreaking);
        }
        /*SL:158*/if (v-1.equals("#luckyChestplateEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:159*/if (v-1.equals("#luckyLeggingsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:160*/if (v-1.equals("#luckyBootsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:162*/if (v-1.equals("#luckyBowEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(3, 5, CustomNBTTags.unbreaking, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity);
        }
        /*SL:163*/if (v-1.equals("#luckyFishingRodEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.unbreaking, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:165*/if (v-1.equals("#randEnchantment")) {
            return (NBTBase)getRandomEnchantmentList(1, 1, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.efficiency, CustomNBTTags.silkTouch, CustomNBTTags.unbreaking, CustomNBTTags.fortune, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:167*/if (v-1.equals("#luckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(7, 10, CustomNBTTags.speed, CustomNBTTags.haste, CustomNBTTags.strength, CustomNBTTags.instantHealth, CustomNBTTags.jumpBoost, CustomNBTTags.regeneration, CustomNBTTags.resistance, CustomNBTTags.fireResistance, CustomNBTTags.waterBreathing, CustomNBTTags.invisibility, CustomNBTTags.nightVision, CustomNBTTags.healthBoost, CustomNBTTags.absorbtion, CustomNBTTags.saturation);
        }
        /*SL:168*/if (v-1.equals("#unluckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(5, 7, CustomNBTTags.slowness, CustomNBTTags.miningFatigue, CustomNBTTags.instantDamage, CustomNBTTags.nausea, CustomNBTTags.blindness, CustomNBTTags.hunger, CustomNBTTags.weakness, CustomNBTTags.poison, CustomNBTTags.wither);
        }
        /*SL:170*/if (v-1.equals("#randFireworksRocket")) {
            return (NBTBase)LuckyFunction.getRandomFireworksRocket();
        }
        /*SL:172*/if (v-1.startsWith("#randLaunchMotion")) {
            try {
                float v = /*EL:176*/0.9f;
                int v2 = /*EL:177*/15;
                /*SL:178*/if (v-1.startsWith("#randLaunchMotion(")) {
                    final String a1 = /*EL:180*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                    final String[] a2 = /*EL:181*/DropStringUtils.splitBracketString(a1, ',');
                    /*SL:182*/a2[0] = DropStringUtils.removeNumSuffix(a2[0]);
                    /*SL:183*/v = ValueParser.getFloat(a2[0], v0);
                    /*SL:184*/v2 = ValueParser.getInteger(a2[1], v0);
                }
                final float v3 = /*EL:187*/MathHelper.func_76142_g(CustomNBTTags.random.nextFloat() * 360.0f);
                final float v4 = /*EL:188*/-90.0f + (CustomNBTTags.random.nextInt(v2 * 2) - v2);
                final float v5 = /*EL:189*/-MathHelper.func_76126_a(v3 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v4 / 180.0f * 3.1415927f) * v;
                final float v6 = /*EL:190*/MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v4 / 180.0f * 3.1415927f) * v;
                final float v7 = /*EL:191*/-MathHelper.func_76126_a(v4 / 180.0f * 3.1415927f) * v;
                final NBTTagList v8 = /*EL:193*/new NBTTagList();
                /*SL:194*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v5));
                /*SL:195*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v7));
                /*SL:196*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v6));
                /*SL:197*/return (NBTBase)v8;
            }
            catch (Exception ex) {}
        }
        /*SL:204*/if (v-1.startsWith("#chest")) {
            final TileEntityChest v9 = /*EL:206*/new TileEntityChest();
            /*SL:208*/if (v-1.startsWith("#chest(")) {
                final String v10 = /*EL:210*/v-1.substring(v-1.indexOf(40), v-1.lastIndexOf(41) + 1);
                NBTTagCompound v11 = /*EL:211*/null;
                try {
                    /*SL:215*/v11 = ValueParser.getNBTTag(v10, v0);
                }
                catch (Exception v12) {
                    System.err.println(/*EL:219*/"Lucky Block: Error reading chest NBT Tag");
                    /*SL:220*/v12.printStackTrace();
                    /*SL:221*/return null;
                }
                final int v13 = /*EL:224*/v11.func_74762_e("maxTotalAmount");
                final NBTTagList v14 = /*EL:226*/v11.func_150295_c("contents", 10);
                final List<WeightedRandomChestContent> v15 = /*EL:227*/new ArrayList<WeightedRandomChestContent>(v14.func_74745_c());
                /*SL:229*/for (int v16 = 0; v16 < v14.func_74745_c(); ++v16) {
                    final NBTTagCompound v17 = /*EL:231*/v14.func_150305_b(v16);
                    int v18 = /*EL:233*/v17.func_74764_b("minAmount") ? v17.func_74762_e("minAmount") : 1;
                    int v19 = /*EL:234*/v17.func_74764_b("maxAmount") ? v17.func_74762_e("maxAmount") : 1;
                    final int v20 = /*EL:235*/v17.func_74764_b("weight") ? v17.func_74762_e("weight") : 1;
                    /*SL:236*/if (v18 == 0) {
                        v18 = 1;
                    }
                    /*SL:237*/if (v19 == 0) {
                        v19 = 2;
                    }
                    final ItemStack v21 = /*EL:240*/ItemStack.func_77949_a(v17);
                    /*SL:242*/v15.add(new WeightedRandomChestContent(v21, v18, v19, v20));
                }
                /*SL:244*/WeightedRandomChestContent.func_177630_a(CustomNBTTags.random, (List)v15, (IInventory)v9, v13);
            }
            else {
                final String v10 = /*EL:248*/String.valueOf(v-1.charAt(6)).toLowerCase(Locale.ENGLISH) + v-1.substring(7, v-1.length());
                /*SL:249*/WeightedRandomChestContent.func_177630_a(CustomNBTTags.random, ChestGenHooks.getItems(v10, CustomNBTTags.random), (IInventory)v9, ChestGenHooks.getCount(v10, CustomNBTTags.random));
            }
            final NBTTagCompound v22 = /*EL:252*/new NBTTagCompound();
            /*SL:253*/v9.func_145841_b(v22);
            /*SL:254*/return (NBTBase)v22.func_150295_c("Items", 10);
        }
        /*SL:257*/return null;
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
        CustomNBTTags.nbtHashVariables = new String[] { "#luckySwordEnchantments", "#luckyAxeEnchantments", "#luckyToolEnchantments", "#luckyHelmetEnchantments", "#luckyLeggingsEnchantments", "#luckyBootsEnchantments", "#luckyBowEnchantments", "#luckyFishingRodEnchantments", "#randEnchantment", "#luckyPotionEffects", "#unluckyPotionEffects", "#randFireworksRocket", "#randLaunchMotion", "#chest" };
    }
}
