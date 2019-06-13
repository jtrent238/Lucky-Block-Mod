package mod.lucky.drop.value;

import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import java.lang.reflect.Type;
import net.minecraft.world.storage.loot.RandomValueRange;
import com.google.gson.GsonBuilder;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.inventory.IInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.WorldServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.math.MathHelper;
import mod.lucky.util.LuckyFunction;
import mod.lucky.drop.func.DropProcessData;
import java.util.Iterator;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;
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
    private static final Gson GSON_INSTANCE;
    public static Random random;
    public static String[] nbtHashVariables;
    
    public static NBTTagCompound getEnchantment(final int a1, final int a2) {
        final NBTTagCompound v1 = /*EL:90*/new NBTTagCompound();
        /*SL:92*/v1.func_74777_a("id", (short)a1);
        /*SL:93*/v1.func_74777_a("lvl", (short)a2);
        /*SL:95*/return v1;
    }
    
    public static NBTTagCompound getPotionEffect(final int a1, final int a2, final int a3) {
        final NBTTagCompound v1 = /*EL:100*/new NBTTagCompound();
        /*SL:102*/v1.func_74774_a("Id", (byte)a1);
        /*SL:103*/v1.func_74774_a("Amplifier", (byte)a2);
        /*SL:104*/v1.func_74768_a("Duration", a3);
        /*SL:106*/return v1;
    }
    
    public static ArrayList<NBTTagCompound> getRandomList(final int v1, final int v2, final NBTTagCompound... v3) {
        final int v4 = /*EL:111*/v3.length - (CustomNBTTags.random.nextInt(v2 + 1 - v1) + v1);
        final ArrayList<NBTTagCompound> v5 = /*EL:113*/new ArrayList<NBTTagCompound>(v3.length);
        /*SL:114*/for (final NBTTagCompound a1 : v3) {
            /*SL:116*/v5.add(a1.func_74737_b());
        }
        /*SL:119*/for (int a2 = 0; a2 < v4; ++a2) {
            final int a3 = CustomNBTTags.random.nextInt(/*EL:121*/v5.size());
            /*SL:122*/v5.remove(a3);
        }
        /*SL:125*/return v5;
    }
    
    public static NBTTagList getRandomEnchantmentList(final int a2, final int a3, final NBTTagCompound... v1) {
        final ArrayList<NBTTagCompound> v2 = getRandomList(/*EL:130*/a2, a3, v1);
        final NBTTagList v3 = /*EL:132*/new NBTTagList();
        /*SL:133*/for (final NBTTagCompound a4 : v2) {
            /*SL:135*/a4.func_74777_a("lvl", (short)(CustomNBTTags.random.nextInt(a4.func_74765_d("lvl")) + 1));
            /*SL:136*/v3.func_74742_a((NBTBase)a4);
        }
        /*SL:139*/return v3;
    }
    
    public static NBTTagList getRandomPotionEffectList(final int a3, final int v1, final NBTTagCompound... v2) {
        final ArrayList<NBTTagCompound> v3 = getRandomList(/*EL:144*/a3, v1, v2);
        final NBTTagList v4 = /*EL:146*/new NBTTagList();
        /*SL:147*/for (final NBTTagCompound a4 : v3) {
            /*SL:149*/a4.func_74774_a("Amplifier", (byte)CustomNBTTags.random.nextInt(a4.func_74771_c("Amplifier") + 1));
            final int a5 = /*EL:150*/(int)(a4.func_74762_e("Duration") / 3.0f);
            /*SL:151*/a4.func_74768_a("Duration", CustomNBTTags.random.nextInt(a4.func_74762_e("Duration") + 1 - a5) + a5);
            /*SL:152*/v4.func_74742_a((NBTBase)a4);
        }
        /*SL:155*/return v4;
    }
    
    public static NBTBase getNBTTagFromString(String v-1, final DropProcessData v0) {
        /*SL:162*/if (v-1.equals("#luckySwordEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.unbreaking);
        }
        /*SL:163*/if (v-1.equals("#luckyAxeEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:164*/if (v-1.equals("#luckyToolEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.efficiency, CustomNBTTags.unbreaking, CustomNBTTags.fortune);
        }
        /*SL:166*/if (v-1.equals("#luckyHelmetEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.respiration, CustomNBTTags.aquaAffinity, CustomNBTTags.unbreaking);
        }
        /*SL:167*/if (v-1.equals("#luckyChestplateEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:168*/if (v-1.equals("#luckyLeggingsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:169*/if (v-1.equals("#luckyBootsEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(4, 6, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.unbreaking);
        }
        /*SL:171*/if (v-1.equals("#luckyBowEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(3, 5, CustomNBTTags.unbreaking, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity);
        }
        /*SL:172*/if (v-1.equals("#luckyFishingRodEnchantments")) {
            return (NBTBase)getRandomEnchantmentList(2, 3, CustomNBTTags.unbreaking, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:174*/if (v-1.equals("#randEnchantment")) {
            return (NBTBase)getRandomEnchantmentList(1, 1, CustomNBTTags.protection, CustomNBTTags.fireProtection, CustomNBTTags.featherFalling, CustomNBTTags.blastProtection, CustomNBTTags.projectileProtection, CustomNBTTags.thorns, CustomNBTTags.sharpness, CustomNBTTags.smite, CustomNBTTags.baneOfArthroponds, CustomNBTTags.knockBack, CustomNBTTags.fireAspect, CustomNBTTags.looting, CustomNBTTags.efficiency, CustomNBTTags.silkTouch, CustomNBTTags.unbreaking, CustomNBTTags.fortune, CustomNBTTags.power, CustomNBTTags.punch, CustomNBTTags.flame, CustomNBTTags.infinity, CustomNBTTags.luckOfTheSea, CustomNBTTags.lure);
        }
        /*SL:176*/if (v-1.equals("#luckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(7, 10, CustomNBTTags.speed, CustomNBTTags.haste, CustomNBTTags.strength, CustomNBTTags.instantHealth, CustomNBTTags.jumpBoost, CustomNBTTags.regeneration, CustomNBTTags.resistance, CustomNBTTags.fireResistance, CustomNBTTags.waterBreathing, CustomNBTTags.invisibility, CustomNBTTags.nightVision, CustomNBTTags.healthBoost, CustomNBTTags.absorbtion, CustomNBTTags.saturation);
        }
        /*SL:177*/if (v-1.equals("#unluckyPotionEffects")) {
            return (NBTBase)getRandomPotionEffectList(5, 7, CustomNBTTags.slowness, CustomNBTTags.miningFatigue, CustomNBTTags.instantDamage, CustomNBTTags.nausea, CustomNBTTags.blindness, CustomNBTTags.hunger, CustomNBTTags.weakness, CustomNBTTags.poison, CustomNBTTags.wither);
        }
        /*SL:179*/if (v-1.equals("#randFireworksRocket")) {
            return (NBTBase)LuckyFunction.getRandomFireworksRocket();
        }
        /*SL:181*/if (v-1.startsWith("#randLaunchMotion")) {
            try {
                float v = /*EL:185*/0.9f;
                int v2 = /*EL:186*/15;
                /*SL:187*/if (v-1.startsWith("#randLaunchMotion(")) {
                    final String a1 = /*EL:189*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                    final String[] a2 = /*EL:190*/DropStringUtils.splitBracketString(a1, ',');
                    /*SL:191*/a2[0] = DropStringUtils.removeNumSuffix(a2[0]);
                    /*SL:192*/v = ValueParser.getFloat(a2[0], v0);
                    /*SL:193*/v2 = ValueParser.getInteger(a2[1], v0);
                }
                final float v3 = /*EL:196*/MathHelper.func_76142_g(CustomNBTTags.random.nextFloat() * 360.0f);
                final float v4 = /*EL:197*/-90.0f + (CustomNBTTags.random.nextInt(v2 * 2) - v2);
                final float v5 = /*EL:198*/-MathHelper.func_76126_a(v3 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v4 / 180.0f * 3.1415927f) * v;
                final float v6 = /*EL:199*/MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v4 / 180.0f * 3.1415927f) * v;
                final float v7 = /*EL:200*/-MathHelper.func_76126_a(v4 / 180.0f * 3.1415927f) * v;
                final NBTTagList v8 = /*EL:202*/new NBTTagList();
                /*SL:203*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v5));
                /*SL:204*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v7));
                /*SL:205*/v8.func_74742_a((NBTBase)new NBTTagDouble((double)v6));
                /*SL:206*/return (NBTBase)v8;
            }
            catch (Exception ex) {}
        }
        /*SL:213*/if (v-1.startsWith("#motionFromDirection(")) {
            try {
                final String v9 = /*EL:217*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                final String[] v10 = /*EL:218*/DropStringUtils.splitBracketString(v9, ',');
                /*SL:219*/v10[2] = DropStringUtils.removeNumSuffix(v10[2]);
                final int v11 = /*EL:221*/ValueParser.getInteger(v10[0], v0);
                final int v12 = /*EL:222*/ValueParser.getInteger(v10[1], v0);
                final float v5 = /*EL:223*/ValueParser.getFloat(v10[2], v0);
                final float v6 = /*EL:224*/-MathHelper.func_76126_a(v11 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v12 / 180.0f * 3.1415927f) * v5;
                final float v7 = /*EL:225*/MathHelper.func_76134_b(v11 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v12 / 180.0f * 3.1415927f) * v5;
                final float v13 = /*EL:226*/-MathHelper.func_76126_a(v12 / 180.0f * 3.1415927f) * v5;
                final NBTTagList v14 = /*EL:228*/new NBTTagList();
                /*SL:229*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v6));
                /*SL:230*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v13));
                /*SL:231*/v14.func_74742_a((NBTBase)new NBTTagDouble((double)v7));
                /*SL:232*/return (NBTBase)v14;
            }
            catch (Exception ex2) {}
        }
        /*SL:239*/if (v-1.startsWith("#bowMotion")) {
            try {
                float v = /*EL:243*/1.0f;
                float v15 = /*EL:244*/0.0f;
                /*SL:245*/if (v-1.startsWith("#bowMotion(")) {
                    final String v16 = /*EL:247*/v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41));
                    final String[] v17 = /*EL:248*/DropStringUtils.splitBracketString(v16, ',');
                    /*SL:249*/v17[0] = DropStringUtils.removeNumSuffix(v17[0]);
                    /*SL:250*/if (v17.length > 1) {
                        v17[1] = DropStringUtils.removeNumSuffix(v17[1]);
                    }
                    /*SL:251*/v = ValueParser.getFloat(v17[0], v0);
                    /*SL:252*/if (v17.length > 1) {
                        v15 = ValueParser.getFloat(v17[1], v0);
                    }
                }
                final Entity v18 = /*EL:255*/v0.getPlayer();
                EntityArrow v19;
                /*SL:257*/if (v18 instanceof EntityLivingBase) {
                    v19 = (EntityArrow)new EntityTippedArrow(v0.getWorld(), (EntityLivingBase)v18);
                }
                else {
                    /*SL:258*/v19 = (EntityArrow)new EntityTippedArrow(v0.getWorld(), v18.field_70165_t, v18.field_70163_u, v18.field_70161_v);
                }
                /*SL:259*/v19.func_184547_a(v18, v18.field_70125_A + HashVariables.randomFloatClamp(v0.getWorld().field_73012_v, -v15, v15), v18.field_70177_z + HashVariables.randomFloatClamp(v0.getWorld().field_73012_v, -v15, v15), 0.0f, v0.getBowPower(), 1.0f);
                final NBTTagList v20 = /*EL:261*/new NBTTagList();
                /*SL:262*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70159_w));
                /*SL:263*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70181_x));
                /*SL:264*/v20.func_74742_a((NBTBase)new NBTTagDouble(v19.field_70179_y));
                /*SL:265*/return (NBTBase)v20;
            }
            catch (Exception ex3) {}
        }
        /*SL:272*/v-1 = v-1.replace("#chestVillageBlacksmith", "#chestLootTable(" + LootTableList.field_186423_e.func_110623_a() + ")");
        /*SL:273*/v-1 = v-1.replace("#chestBonusChest", "#chestLootTable(" + LootTableList.field_186420_b.func_110623_a() + ")");
        /*SL:274*/v-1 = v-1.replace("#chestDungeonChest", "#chestLootTable(" + LootTableList.field_186422_d.func_110623_a() + ")");
        /*SL:276*/if (v-1.startsWith("#customChestLootTable(")) {
            try {
                final String v9 = /*EL:280*/ValueParser.getString(v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41)));
                final LootTable.Serializer v21 = /*EL:281*/new LootTable.Serializer();
                final LootTable v22 = /*EL:282*/ForgeHooks.loadLootTable(CustomNBTTags.GSON_INSTANCE, LootTableList.field_186420_b, v9, true, v0.getWorld().func_184146_ak());
                final TileEntityChest v23 = /*EL:284*/new TileEntityChest();
                final LootContext.Builder v24 = /*EL:285*/new LootContext.Builder((WorldServer)v0.getWorld());
                /*SL:286*/if (v0.getPlayer() != null && v0.getPlayer() instanceof EntityPlayer) {
                    v24.func_186469_a(((EntityPlayer)v0.getPlayer()).func_184817_da());
                }
                /*SL:288*/v22.func_186460_a((IInventory)v23, CustomNBTTags.random, v24.func_186471_a());
                final NBTTagCompound v25 = /*EL:290*/new NBTTagCompound();
                /*SL:291*/v23.func_189515_b(v25);
                /*SL:292*/return (NBTBase)v25.func_150295_c("Items", 10);
            }
            catch (Exception v26) {
                System.err.println(/*EL:297*/"Lucky Block: Error creating chest from .json loot table");
                /*SL:298*/v26.printStackTrace();
            }
        }
        /*SL:302*/if (v-1.startsWith("#chestLootTable(")) {
            final TileEntityChest v27 = /*EL:304*/new TileEntityChest();
            final String v28 = /*EL:305*/ValueParser.getString(v-1.substring(v-1.indexOf(40) + 1, v-1.lastIndexOf(41)));
            /*SL:306*/v27.func_145834_a(v0.getWorld());
            /*SL:307*/v27.func_189404_a(new ResourceLocation("minecraft", v28), CustomNBTTags.random.nextLong());
            /*SL:308*/v27.func_70301_a(0);
            final NBTTagCompound v29 = /*EL:310*/new NBTTagCompound();
            /*SL:311*/v27.func_189515_b(v29);
            /*SL:312*/return (NBTBase)v29.func_150295_c("Items", 10);
        }
        /*SL:315*/return null;
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
        GSON_INSTANCE = new GsonBuilder().registerTypeAdapter((Type)RandomValueRange.class, (Object)new RandomValueRange.Serializer()).registerTypeAdapter((Type)LootPool.class, (Object)new LootPool.Serializer()).registerTypeAdapter((Type)LootTable.class, (Object)new LootTable.Serializer()).registerTypeHierarchyAdapter((Class)LootEntry.class, (Object)new LootEntry.Serializer()).registerTypeHierarchyAdapter((Class)LootFunction.class, (Object)new LootFunctionManager.Serializer()).registerTypeHierarchyAdapter((Class)LootCondition.class, (Object)new LootConditionManager.Serializer()).registerTypeHierarchyAdapter((Class)LootContext.EntityTarget.class, (Object)new LootContext.EntityTarget.Serializer()).create();
        CustomNBTTags.random = new Random();
        CustomNBTTags.nbtHashVariables = new String[] { "#luckySwordEnchantments", "#luckyAxeEnchantments", "#luckyToolEnchantments", "#luckyHelmetEnchantments", "#luckyLeggingsEnchantments", "#luckyBootsEnchantments", "#luckyBowEnchantments", "#luckyFishingRodEnchantments", "#randEnchantment", "#luckyPotionEffects", "#unluckyPotionEffects", "#randFireworksRocket", "#randLaunchMotion", "#motionFromDirection", "#bowMotion", "#chestLootTable", "#customChestLootTable" };
    }
}
