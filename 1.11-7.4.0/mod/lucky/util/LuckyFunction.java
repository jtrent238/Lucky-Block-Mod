package mod.lucky.util;

import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.PotionType;
import mod.lucky.drop.DropContainer;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTBase;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import java.util.ArrayList;
import java.util.Random;

public class LuckyFunction
{
    public static int[] potionEffectList;
    public static int[] potionEffectListGood;
    public static int[] potionEffectListBad;
    public static int[] mobEggList;
    public static int[] mobIDList;
    public static String[] mobNameList;
    private static Random random;
    private static ArrayList<String> potionNames;
    
    public static NBTTagCompound getRandomFireworksRocket() {
        final Random random = /*EL:76*/new Random();
        final NBTTagCompound nbtTagCompound = /*EL:78*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound2 = /*EL:79*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound3 = /*EL:80*/new NBTTagCompound();
        final NBTTagList list = /*EL:81*/new NBTTagList();
        /*SL:84*/nbtTagCompound3.func_74774_a("Type", (byte)random.nextInt(5));
        /*SL:85*/nbtTagCompound3.func_74757_a("Flicker", random.nextBoolean());
        /*SL:86*/nbtTagCompound3.func_74757_a("Trail", random.nextBoolean());
        final int n = /*EL:87*/random.nextInt(4) + 1;
        final int[] v0 = /*EL:88*/new int[n];
        /*SL:89*/for (int v = 0; v < n; ++v) {
            /*SL:91*/v0[v] = Integer.valueOf(ItemDye.field_150922_c[random.nextInt(14)]);
        }
        /*SL:93*/nbtTagCompound3.func_74783_a("Colors", v0);
        /*SL:96*/list.func_74742_a((NBTBase)nbtTagCompound3);
        /*SL:99*/nbtTagCompound2.func_74782_a("Explosions", (NBTBase)list);
        /*SL:100*/nbtTagCompound2.func_74774_a("Flight", (byte)(random.nextInt(2) + 1));
        /*SL:103*/nbtTagCompound.func_74782_a("Fireworks", (NBTBase)nbtTagCompound2);
        /*SL:104*/return nbtTagCompound;
    }
    
    public static int getRandomPotionDamage() {
        /*SL:109*/return calculatePotionDamage(getRandomPotionEffect(), 0);
    }
    
    public static int calculatePotionDamage(final int a1, final int a2) {
        int v1 = /*EL:116*/0;
        int v2 = /*EL:117*/0;
        final int v3 = LuckyFunction.random.nextInt(/*EL:118*/3);
        /*SL:119*/if (v3 == 1) {
            v1 = 32;
        }
        /*SL:120*/if (v3 == 2) {
            v2 = 64;
        }
        int v4 = /*EL:123*/0;
        /*SL:124*/if (a2 == 0) {
            /*SL:126*/if (LuckyFunction.random.nextInt(2) == 0) {
                v4 = 16384;
            }
        }
        else/*SL:128*/ if (a2 == 1) {
            v4 = 0;
        }
        else/*SL:129*/ if (a2 == 2) {
            v4 = 16384;
        }
        /*SL:131*/return a1 + v1 + v2 + v4;
    }
    
    public static String getRandomPotionName() {
        /*SL:136*/return LuckyFunction.potionNames.get(LuckyFunction.random.nextInt(LuckyFunction.potionNames.size()));
    }
    
    @Deprecated
    public static EntityLiving getMobByNameOrId(final World a1, final String a2, final int a3) {
        /*SL:143*/if (a2.equals(LuckyFunction.mobNameList[0]) || a3 == LuckyFunction.mobIDList[0]) {
            return (EntityLiving)new EntityCreeper(a1);
        }
        /*SL:144*/if (a2.equals(LuckyFunction.mobNameList[1]) || a3 == LuckyFunction.mobIDList[1]) {
            return (EntityLiving)new EntitySkeleton(a1);
        }
        /*SL:145*/if (a2.equals(LuckyFunction.mobNameList[2]) || a3 == LuckyFunction.mobIDList[2]) {
            return (EntityLiving)new EntitySpider(a1);
        }
        /*SL:146*/if (a2.equals(LuckyFunction.mobNameList[3]) || a3 == LuckyFunction.mobIDList[3]) {
            return (EntityLiving)new EntityGiantZombie(a1);
        }
        /*SL:147*/if (a2.equals(LuckyFunction.mobNameList[4]) || a3 == LuckyFunction.mobIDList[4]) {
            return (EntityLiving)new EntityZombie(a1);
        }
        /*SL:148*/if (a2.equals(LuckyFunction.mobNameList[5]) || a3 == LuckyFunction.mobIDList[5]) {
            return (EntityLiving)new EntitySlime(a1);
        }
        /*SL:149*/if (a2.equals(LuckyFunction.mobNameList[6]) || a3 == LuckyFunction.mobIDList[6]) {
            return (EntityLiving)new EntityGhast(a1);
        }
        /*SL:150*/if (a2.equals(LuckyFunction.mobNameList[7]) || a3 == LuckyFunction.mobIDList[7]) {
            return (EntityLiving)new EntityPigZombie(a1);
        }
        /*SL:151*/if (a2.equals(LuckyFunction.mobNameList[8]) || a3 == LuckyFunction.mobIDList[8]) {
            return (EntityLiving)new EntityEnderman(a1);
        }
        /*SL:152*/if (a2.equals(LuckyFunction.mobNameList[9]) || a3 == LuckyFunction.mobIDList[9]) {
            return (EntityLiving)new EntityCaveSpider(a1);
        }
        /*SL:153*/if (a2.equals(LuckyFunction.mobNameList[10]) || a3 == LuckyFunction.mobIDList[10]) {
            return (EntityLiving)new EntitySilverfish(a1);
        }
        /*SL:154*/if (a2.equals(LuckyFunction.mobNameList[11]) || a3 == LuckyFunction.mobIDList[11]) {
            return (EntityLiving)new EntityBlaze(a1);
        }
        /*SL:155*/if (a2.equals(LuckyFunction.mobNameList[12]) || a3 == LuckyFunction.mobIDList[12]) {
            return (EntityLiving)new EntityMagmaCube(a1);
        }
        /*SL:156*/if (a2.equals(LuckyFunction.mobNameList[13]) || a3 == LuckyFunction.mobIDList[13]) {
            return (EntityLiving)new EntityDragon(a1);
        }
        /*SL:157*/if (a2.equals(LuckyFunction.mobNameList[14]) || a3 == LuckyFunction.mobIDList[14]) {
            return (EntityLiving)new EntityWither(a1);
        }
        /*SL:158*/if (a2.equals(LuckyFunction.mobNameList[15]) || a3 == LuckyFunction.mobIDList[15]) {
            return (EntityLiving)new EntityWitch(a1);
        }
        /*SL:161*/if (a2.equals(LuckyFunction.mobNameList[16]) || a3 == LuckyFunction.mobIDList[16]) {
            return (EntityLiving)new EntityBat(a1);
        }
        /*SL:162*/if (a2.equals(LuckyFunction.mobNameList[17]) || a3 == LuckyFunction.mobIDList[17]) {
            return (EntityLiving)new EntityPig(a1);
        }
        /*SL:163*/if (a2.equals(LuckyFunction.mobNameList[18]) || a3 == LuckyFunction.mobIDList[18]) {
            return (EntityLiving)new EntitySheep(a1);
        }
        /*SL:164*/if (a2.equals(LuckyFunction.mobNameList[19]) || a3 == LuckyFunction.mobIDList[19]) {
            return (EntityLiving)new EntityCow(a1);
        }
        /*SL:165*/if (a2.equals(LuckyFunction.mobNameList[20]) || a3 == LuckyFunction.mobIDList[20]) {
            return (EntityLiving)new EntityChicken(a1);
        }
        /*SL:166*/if (a2.equals(LuckyFunction.mobNameList[21]) || a3 == LuckyFunction.mobIDList[21]) {
            return (EntityLiving)new EntitySquid(a1);
        }
        /*SL:167*/if (a2.equals(LuckyFunction.mobNameList[22]) || a3 == LuckyFunction.mobIDList[22]) {
            return (EntityLiving)new EntityWolf(a1);
        }
        /*SL:168*/if (a2.equals(LuckyFunction.mobNameList[23]) || a3 == LuckyFunction.mobIDList[23]) {
            return (EntityLiving)new EntityMooshroom(a1);
        }
        /*SL:169*/if (a2.equals(LuckyFunction.mobNameList[24]) || a3 == LuckyFunction.mobIDList[24]) {
            return (EntityLiving)new EntitySnowman(a1);
        }
        /*SL:170*/if (a2.equals(LuckyFunction.mobNameList[25]) || a3 == LuckyFunction.mobIDList[25]) {
            return (EntityLiving)new EntityOcelot(a1);
        }
        /*SL:171*/if (a2.equals(LuckyFunction.mobNameList[26]) || a3 == LuckyFunction.mobIDList[26]) {
            return (EntityLiving)new EntityIronGolem(a1);
        }
        /*SL:172*/if (a2.equals(LuckyFunction.mobNameList[27]) || a3 == LuckyFunction.mobIDList[27]) {
            return (EntityLiving)new EntityHorse(a1);
        }
        /*SL:173*/if (a2.equals(LuckyFunction.mobNameList[28]) || a3 == LuckyFunction.mobIDList[28]) {
            return (EntityLiving)new EntityVillager(a1);
        }
        /*SL:175*/return null;
    }
    
    public static Entity getEntity(final World a1, final int a2, final String a3) {
        /*SL:180*/if (!EntityList.field_75627_a.containsKey(a2)) {
            /*SL:183*/return null;
        }
        /*SL:187*/return EntityList.func_75616_a(a2, a1);
    }
    
    public static int getRandomStatusEffect() {
        /*SL:193*/return Potion.field_188414_b.func_148757_b(Potion.field_188414_b.func_186801_a(LuckyFunction.random));
    }
    
    public static int getRandomPotionEffect() {
        /*SL:198*/return LuckyFunction.random.nextInt(14) + 1;
    }
    
    public static int getRandomPotionEffectGood() {
        /*SL:203*/return LuckyFunction.potionEffectListGood[LuckyFunction.random.nextInt(LuckyFunction.potionEffectListGood.length)];
    }
    
    public static int getRandomPotionEffectBad() {
        /*SL:208*/return LuckyFunction.potionEffectListBad[LuckyFunction.random.nextInt(LuckyFunction.potionEffectListBad.length)];
    }
    
    public static int getRandomMobEggID() {
        final Object[] v1 = EntityList.field_75627_a.values().toArray();
        final EntityList.EntityEggInfo v2 = /*EL:214*/(EntityList.EntityEggInfo)v1[LuckyFunction.random.nextInt(v1.length)];
        /*SL:215*/return EntityList.getID(EntityList.getClass(v2.field_75613_a));
    }
    
    public static String getRandomMobEggName() {
        final Object[] v1 = EntityList.field_75627_a.values().toArray();
        final EntityList.EntityEggInfo v2 = /*EL:221*/(EntityList.EntityEggInfo)v1[LuckyFunction.random.nextInt(v1.length)];
        /*SL:222*/return v2.field_75613_a.toString();
    }
    
    public static int getPlayerDirection(final EntityPlayer a1, final int a2) {
        int v1 = /*EL:227*/(int)a1.field_70177_z;
        final int v2 = /*EL:228*/360 / a2;
        /*SL:229*/if (v1 < 0) {
            v1 += 360;
        }
        /*SL:230*/v1 += v2 / 2;
        /*SL:231*/v1 %= 360;
        /*SL:232*/return v1 / v2;
    }
    
    public static int adjustHeight(final World a2, final int a3, final int a4, final int a5, final int v1) {
        boolean v2 = /*EL:237*/false;
        int v3 = /*EL:238*/a5;
        int v4 = /*EL:239*/0;
        /*SL:240*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:242*/if (a2.func_180495_p(new BlockPos(a4, a6, v1)).func_185914_p()) {
                /*SL:244*/v4 = 0;
                /*SL:245*/v3 = a6 + 1;
            }
            else {
                /*SL:249*/++v4;
            }
            /*SL:252*/if (v4 == a3) {
                /*SL:254*/v2 = true;
                /*SL:255*/break;
            }
        }
        /*SL:259*/if (v2) {
            /*SL:261*/return v3;
        }
        /*SL:265*/return -1;
    }
    
    public static NBTTagList getNBTTagListFromStringArray(final String[] v1) {
        final NBTTagList v2 = /*EL:271*/new NBTTagList();
        /*SL:272*/for (final String a1 : v1) {
            /*SL:274*/v2.func_74742_a((NBTBase)new NBTTagString(a1));
        }
        /*SL:276*/return v2;
    }
    
    public static String[] getStringArrayFromNBTTagList(final NBTTagList v1) {
        final String[] v2 = /*EL:281*/new String[(v1 == null) ? 0 : v1.func_74745_c()];
        /*SL:282*/for (int a1 = 0; a1 < v2.length; ++a1) {
            /*SL:284*/v2[a1] = v1.func_150307_f(a1);
        }
        /*SL:286*/return v2;
    }
    
    public static ArrayList<DropContainer> getDropsFromStringArray(final String[] v-4) {
        final ArrayList<DropContainer> list = /*EL:291*/new ArrayList<DropContainer>();
        /*SL:292*/for (final String v1 : v-4) {
            final DropContainer a1 = /*EL:294*/new DropContainer();
            /*SL:295*/a1.readFromString(v1);
            /*SL:296*/list.add(a1);
        }
        /*SL:298*/return list;
    }
    
    static {
        LuckyFunction.potionEffectList = new int[] { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14 };
        LuckyFunction.potionEffectListGood = new int[] { 1, 5, 6, 8, 10, 12, 13, 14, 16, 14 };
        LuckyFunction.potionEffectListBad = new int[] { 2, 7, 18, 19 };
        LuckyFunction.mobEggList = new int[] { 50, 51, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 90, 91, 92, 93, 94, 95, 96, 98, 100, 120 };
        LuckyFunction.mobIDList = new int[] { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 120 };
        LuckyFunction.mobNameList = new String[] { "creeper", "skeleton", "spider", "giant zombie", "zombie", "slime", "ghast", "zombie pigman", "enderman", "cave spider", "silverfish", "blaze", "magma cube", "ender dragon", "wither", "witch", "bat", "pig", "sheep", "cow", "chicken", "squid", "wolf", "mooshroom", "snow golem", "ocelot", "iron golem", "horse", "villager" };
        LuckyFunction.random = new Random();
        LuckyFunction.potionNames = new ArrayList<String>();
        for (final ResourceLocation v0 : PotionType.field_185176_a.func_148742_b()) {
            final String v = v0.func_110623_a();
            if (!v.equals("empty") && !v.equals("water") && !v.equals("mundane") && !v.equals("thick") && !v.equals("awkward")) {
                LuckyFunction.potionNames.add(v);
            }
        }
    }
}
