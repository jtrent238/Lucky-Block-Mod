package mod.lucky.drop.value;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.ChestGenHooks;
import java.util.Locale;
import net.minecraft.inventory.IInventory;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.entity.projectile.EntityArrow;
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
        final NBTTagCompound v1 = /*EL:81*/new NBTTagCompound();
        /*SL:83*/v1.func_74777_a("id", (short)a1);
        /*SL:84*/v1.func_74777_a("lvl", (short)a2);
        /*SL:86*/return v1;
    }
    
    public static NBTTagCompound getPotionEffect(final int a1, final int a2, final int a3) {
        final NBTTagCompound v1 = /*EL:91*/new NBTTagCompound();
        /*SL:93*/v1.func_74774_a("Id", (byte)a1);
        /*SL:94*/v1.func_74774_a("Amplifier", (byte)a2);
        /*SL:95*/v1.func_74768_a("Duration", a3);
        /*SL:97*/return v1;
    }
    
    public static ArrayList<NBTTagCompound> getRandomList(final int v1, final int v2, final NBTTagCompound... v3) {
        final int v4 = /*EL:102*/v3.length - (CustomNBTTags.random.nextInt(v2 + 1 - v1) + v1);
        final ArrayList<NBTTagCompound> v5 = /*EL:104*/new ArrayList<NBTTagCompound>(v3.length);
        /*SL:105*/for (final NBTTagCompound a1 : v3) {
            /*SL:107*/v5.add((NBTTagCompound)a1.func_74737_b());
        }
        /*SL:110*/for (int a2 = 0; a2 < v4; ++a2) {
            final int a3 = CustomNBTTags.random.nextInt(/*EL:112*/v5.size());
            /*SL:113*/v5.remove(a3);
        }
        /*SL:116*/return v5;
    }
    
    public static NBTTagList getRandomEnchantmentList(final int a2, final int a3, final NBTTagCompound... v1) {
        final ArrayList<NBTTagCompound> v2 = getRandomList(/*EL:121*/a2, a3, v1);
        final NBTTagList v3 = /*EL:123*/new NBTTagList();
        /*SL:124*/for (final NBTTagCompound a4 : v2) {
            /*SL:126*/a4.func_74777_a("lvl", (short)(CustomNBTTags.random.nextInt(a4.func_74765_d("lvl")) + 1));
            /*SL:127*/v3.func_74742_a((NBTBase)a4);
        }
        /*SL:130*/return v3;
    }
    
    public static NBTTagList getRandomPotionEffectList(final int a3, final int v1, final NBTTagCompound... v2) {
        final ArrayList<NBTTagCompound> v3 = getRandomList(/*EL:135*/a3, v1, v2);
        final NBTTagList v4 = /*EL:137*/new NBTTagList();
        /*SL:138*/for (final NBTTagCompound a4 : v3) {
            /*SL:140*/a4.func_74774_a("Amplifier", (byte)CustomNBTTags.random.nextInt(a4.func_74771_c("Amplifier") + 1));
            final int a5 = /*EL:141*/(int)(a4.func_74762_e("Duration") / 3.0f);
            /*SL:142*/a4.func_74768_a("Duration", CustomNBTTags.random.nextInt(a4.func_74762_e("Duration") + 1 - a5) + a5);
            /*SL:143*/v4.func_74742_a((NBTBase)a4);
        }
        /*SL:146*/return v4;
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
        /*SL:204*/if (v-1.startsWith("#motionFromDirection(")) {
            try {
                final String v9 = /*EL:208*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                final String[] v10 = /*EL:209*/DropStringUtils.splitBracketString(v9, ',');
                /*SL:210*/v10[2] = DropStringUtils.removeNumSuffix(v10[2]);
                final int v11 = /*EL:212*/ValueParser.getInteger(v10[0], v0);
                final int v12 = /*EL:213*/ValueParser.getInteger(v10[1], v0);
                final float v5 = /*EL:214*/ValueParser.getFloat(v10[2], v0);
                final float v6 = /*EL:215*/-MathHelper.func_76126_a(v11 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v12 / 180.0f * 3.1415927f) * v5;
                final float v7 = /*EL:216*/MathHelper.func_76134_b(v11 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v12 / 180.0f * 3.1415927f) * v5;
                final float v13 = /*EL:217*/-MathHelper.func_76126_a(v12 / 180.0f * 3.1415927f) * v5;
                final NBTTagList v14 = /*EL:219*/new NBTTagList();
                /*SL:220*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v6));
                /*SL:221*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v13));
                /*SL:222*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v7));
                /*SL:223*/return (NBTBase)v14;
            }
            catch (Exception ex2) {}
        }
        /*SL:230*/if (v-1.startsWith("#bowMotion")) {
            try {
                float v = /*EL:234*/1.0f;
                float v15 = /*EL:235*/0.0f;
                /*SL:236*/if (v-1.startsWith("#bowMotion(")) {
                    final String v16 = /*EL:238*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                    final String[] v17 = /*EL:239*/DropStringUtils.splitBracketString(v16, ',');
                    /*SL:240*/v17[0] = DropStringUtils.removeNumSuffix(v17[0]);
                    /*SL:241*/if (v17.length > 1) {
                        v17[1] = DropStringUtils.removeNumSuffix(v17[1]);
                    }
                    /*SL:242*/v = ValueParser.getFloat(v17[0], v0);
                    /*SL:243*/if (v17.length > 1) {
                        v15 = ValueParser.getFloat(v17[1], v0);
                    }
                }
                final Entity v18 = /*EL:246*/v0.getPlayer();
                final EntityArrow v19 = /*EL:247*/new EntityArrow(v0.getWorld());
                /*SL:248*/v19.func_70012_b(v18.field_70165_t, v18.field_70163_u + v18.func_70047_e(), v18.field_70161_v, v18.field_70177_z + MathHelper.func_151240_a(v0.getWorld().field_73012_v, -v15, v15), v18.field_70125_A + MathHelper.func_151240_a(v0.getWorld().field_73012_v, -v15, v15));
                final EntityArrow entityArrow = /*EL:249*/v19;
                entityArrow.field_70165_t -= MathHelper.func_76134_b(v19.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
                final EntityArrow entityArrow2 = /*EL:250*/v19;
                entityArrow2.field_70163_u -= 0.10000000149011612;
                final EntityArrow entityArrow3 = /*EL:251*/v19;
                entityArrow3.field_70161_v -= MathHelper.func_76126_a(v19.field_70177_z / 180.0f * 3.1415927f) * 0.16f;
                /*SL:252*/v19.func_70107_b(v19.field_70165_t, v19.field_70163_u, v19.field_70161_v);
                /*SL:253*/v19.field_70159_w = -MathHelper.func_76126_a(v19.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v19.field_70125_A / 180.0f * 3.1415927f);
                /*SL:254*/v19.field_70179_y = MathHelper.func_76134_b(v19.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v19.field_70125_A / 180.0f * 3.1415927f);
                /*SL:255*/v19.field_70181_x = -MathHelper.func_76126_a(v19.field_70125_A / 180.0f * 3.1415927f);
                /*SL:256*/v19.func_70186_c(v19.field_70159_w, v19.field_70181_x, v19.field_70179_y, v0.getBowPower() * v * 1.5f, 1.0f);
                final NBTTagList v20 = /*EL:258*/new NBTTagList();
                /*SL:259*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70159_w));
                /*SL:260*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70181_x));
                /*SL:261*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70179_y));
                /*SL:262*/return (NBTBase)v20;
            }
            catch (Exception ex3) {}
        }
        /*SL:269*/if (v-1.startsWith("#chest")) {
            final TileEntityChest v21 = /*EL:271*/new TileEntityChest();
            /*SL:273*/if (v-1.startsWith("#chest(")) {
                final String v22 = /*EL:275*/v-1.substring(v-1.indexOf(40), v-1.lastIndexOf(41) + 1);
                NBTTagCompound v23 = /*EL:276*/null;
                try {
                    /*SL:280*/v23 = ValueParser.getNBTTag(v22, v0);
                }
                catch (Exception v24) {
                    System.err.println(/*EL:284*/"Lucky Block: Error reading chest NBT Tag");
                    /*SL:285*/v24.printStackTrace();
                    /*SL:286*/return null;
                }
                final int v12 = /*EL:289*/v23.func_74762_e("maxTotalAmount");
                final NBTTagList v20 = /*EL:291*/v23.func_150295_c("contents", 10);
                final List<WeightedRandomChestContent> v25 = /*EL:292*/new ArrayList<WeightedRandomChestContent>(v20.func_74745_c());
                /*SL:294*/for (int v26 = 0; v26 < v20.func_74745_c(); ++v26) {
                    final NBTTagCompound v27 = /*EL:296*/v20.func_150305_b(v26);
                    int v28 = /*EL:298*/v27.func_74764_b("minAmount") ? v27.func_74762_e("minAmount") : 1;
                    int v29 = /*EL:299*/v27.func_74764_b("maxAmount") ? v27.func_74762_e("maxAmount") : 1;
                    final int v30 = /*EL:300*/v27.func_74764_b("weight") ? v27.func_74762_e("weight") : 1;
                    /*SL:301*/if (v28 == 0) {
                        v28 = 1;
                    }
                    /*SL:302*/if (v29 == 0) {
                        v29 = 2;
                    }
                    final ItemStack v31 = /*EL:305*/ItemStack.func_77949_a(v27);
                    /*SL:307*/v25.add(new WeightedRandomChestContent(v31, v28, v29, v30));
                }
                /*SL:309*/WeightedRandomChestContent.func_177630_a(CustomNBTTags.random, (List)v25, (IInventory)v21, v12);
            }
            else {
                final String v22 = /*EL:313*/String.valueOf(v-1.charAt(6)).toLowerCase(Locale.ENGLISH) + v-1.substring(7, v-1.length());
                /*SL:314*/WeightedRandomChestContent.func_177630_a(CustomNBTTags.random, ChestGenHooks.getItems(v22, CustomNBTTags.random), (IInventory)v21, ChestGenHooks.getCount(v22, CustomNBTTags.random));
            }
            final NBTTagCompound v32 = /*EL:317*/new NBTTagCompound();
            /*SL:318*/v21.func_145841_b(v32);
            /*SL:319*/return (NBTBase)v32.func_150295_c("Items", 10);
        }
        /*SL:322*/return null;
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
        CustomNBTTags.nbtHashVariables = new String[] { "#luckySwordEnchantments", "#luckyAxeEnchantments", "#luckyToolEnchantments", "#luckyHelmetEnchantments", "#luckyLeggingsEnchantments", "#luckyBootsEnchantments", "#luckyBowEnchantments", "#luckyFishingRodEnchantments", "#randEnchantment", "#luckyPotionEffects", "#unluckyPotionEffects", "#randFireworksRocket", "#randLaunchMotion", "#motionFromDirection", "#bowMotion", "#chest" };
    }
}
