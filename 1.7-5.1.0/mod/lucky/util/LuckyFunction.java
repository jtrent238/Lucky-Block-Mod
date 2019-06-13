package mod.lucky.util;

import net.minecraft.nbt.NBTTagString;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.init.Items;
import java.util.Random;
import net.minecraft.item.ItemStack;

public class LuckyFunction
{
    public int XOffset;
    public int ZOffset;
    public static int[] potionEffectList;
    public static int[] mobEggList;
    public static int[] mobIDList;
    public static String[] mobNameList;
    
    public static ItemStack getRandomFireworkRocket() {
        final Random random = /*EL:59*/new Random();
        final ItemStack itemStack = /*EL:60*/new ItemStack(Items.field_151152_bP);
        final NBTTagCompound nbtTagCompound = /*EL:62*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound2 = /*EL:63*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound3 = /*EL:64*/new NBTTagCompound();
        final NBTTagList list = /*EL:65*/new NBTTagList();
        /*SL:68*/nbtTagCompound3.func_74774_a("Type", (byte)random.nextInt(5));
        /*SL:69*/nbtTagCompound3.func_74757_a("Flicker", random.nextBoolean());
        /*SL:70*/nbtTagCompound3.func_74757_a("Trail", random.nextBoolean());
        final int n = /*EL:71*/random.nextInt(4) + 1;
        final int[] v0 = /*EL:72*/new int[n];
        /*SL:73*/for (int v = 0; v < n; ++v) {
            /*SL:75*/v0[v] = Integer.valueOf(ItemDye.field_150922_c[random.nextInt(14)]);
        }
        /*SL:77*/nbtTagCompound3.func_74783_a("Colors", v0);
        /*SL:80*/list.func_74742_a((NBTBase)nbtTagCompound3);
        /*SL:83*/nbtTagCompound2.func_74782_a("Explosions", (NBTBase)list);
        /*SL:84*/nbtTagCompound2.func_74774_a("Flight", (byte)(random.nextInt(2) + 1));
        /*SL:87*/nbtTagCompound.func_74782_a("Fireworks", (NBTBase)nbtTagCompound2);
        /*SL:90*/itemStack.func_77982_d(nbtTagCompound);
        /*SL:92*/return itemStack;
    }
    
    public static int getRandomPotionDamage() {
        final Random random = /*EL:97*/new Random();
        final int randomPotionEffect = getRandomPotionEffect();
        int n = /*EL:104*/0;
        int n2 = /*EL:105*/0;
        final int v0 = /*EL:106*/random.nextInt(3);
        /*SL:107*/if (v0 == 1) {
            n = 32;
        }
        /*SL:108*/if (v0 == 2) {
            n2 = 64;
        }
        int v;
        /*SL:112*/if (random.nextInt(2) == 0) {
            v = 0;
        }
        else {
            /*SL:113*/v = 16384;
        }
        /*SL:115*/return randomPotionEffect + n + n2 + v;
    }
    
    public static int getRandomPotionEffect() {
        final Random v1 = /*EL:120*/new Random();
        /*SL:121*/return LuckyFunction.potionEffectList[v1.nextInt(LuckyFunction.potionEffectList.length)];
    }
    
    @Deprecated
    public static EntityLiving getMobByNameOrId(final World a1, final String a2, final int a3) {
        /*SL:128*/if (a2.equals(LuckyFunction.mobNameList[0]) || a3 == LuckyFunction.mobIDList[0]) {
            return (EntityLiving)new EntityCreeper(a1);
        }
        /*SL:129*/if (a2.equals(LuckyFunction.mobNameList[1]) || a3 == LuckyFunction.mobIDList[1]) {
            return (EntityLiving)new EntitySkeleton(a1);
        }
        /*SL:130*/if (a2.equals(LuckyFunction.mobNameList[2]) || a3 == LuckyFunction.mobIDList[2]) {
            return (EntityLiving)new EntitySpider(a1);
        }
        /*SL:131*/if (a2.equals(LuckyFunction.mobNameList[3]) || a3 == LuckyFunction.mobIDList[3]) {
            return (EntityLiving)new EntityGiantZombie(a1);
        }
        /*SL:132*/if (a2.equals(LuckyFunction.mobNameList[4]) || a3 == LuckyFunction.mobIDList[4]) {
            return (EntityLiving)new EntityZombie(a1);
        }
        /*SL:133*/if (a2.equals(LuckyFunction.mobNameList[5]) || a3 == LuckyFunction.mobIDList[5]) {
            return (EntityLiving)new EntitySlime(a1);
        }
        /*SL:134*/if (a2.equals(LuckyFunction.mobNameList[6]) || a3 == LuckyFunction.mobIDList[6]) {
            return (EntityLiving)new EntityGhast(a1);
        }
        /*SL:135*/if (a2.equals(LuckyFunction.mobNameList[7]) || a3 == LuckyFunction.mobIDList[7]) {
            return (EntityLiving)new EntityPigZombie(a1);
        }
        /*SL:136*/if (a2.equals(LuckyFunction.mobNameList[8]) || a3 == LuckyFunction.mobIDList[8]) {
            return (EntityLiving)new EntityEnderman(a1);
        }
        /*SL:137*/if (a2.equals(LuckyFunction.mobNameList[9]) || a3 == LuckyFunction.mobIDList[9]) {
            return (EntityLiving)new EntityCaveSpider(a1);
        }
        /*SL:138*/if (a2.equals(LuckyFunction.mobNameList[10]) || a3 == LuckyFunction.mobIDList[10]) {
            return (EntityLiving)new EntitySilverfish(a1);
        }
        /*SL:139*/if (a2.equals(LuckyFunction.mobNameList[11]) || a3 == LuckyFunction.mobIDList[11]) {
            return (EntityLiving)new EntityBlaze(a1);
        }
        /*SL:140*/if (a2.equals(LuckyFunction.mobNameList[12]) || a3 == LuckyFunction.mobIDList[12]) {
            return (EntityLiving)new EntityMagmaCube(a1);
        }
        /*SL:141*/if (a2.equals(LuckyFunction.mobNameList[13]) || a3 == LuckyFunction.mobIDList[13]) {
            return (EntityLiving)new EntityDragon(a1);
        }
        /*SL:142*/if (a2.equals(LuckyFunction.mobNameList[14]) || a3 == LuckyFunction.mobIDList[14]) {
            return (EntityLiving)new EntityWither(a1);
        }
        /*SL:143*/if (a2.equals(LuckyFunction.mobNameList[15]) || a3 == LuckyFunction.mobIDList[15]) {
            return (EntityLiving)new EntityWitch(a1);
        }
        /*SL:146*/if (a2.equals(LuckyFunction.mobNameList[16]) || a3 == LuckyFunction.mobIDList[16]) {
            return (EntityLiving)new EntityBat(a1);
        }
        /*SL:147*/if (a2.equals(LuckyFunction.mobNameList[17]) || a3 == LuckyFunction.mobIDList[17]) {
            return (EntityLiving)new EntityPig(a1);
        }
        /*SL:148*/if (a2.equals(LuckyFunction.mobNameList[18]) || a3 == LuckyFunction.mobIDList[18]) {
            return (EntityLiving)new EntitySheep(a1);
        }
        /*SL:149*/if (a2.equals(LuckyFunction.mobNameList[19]) || a3 == LuckyFunction.mobIDList[19]) {
            return (EntityLiving)new EntityCow(a1);
        }
        /*SL:150*/if (a2.equals(LuckyFunction.mobNameList[20]) || a3 == LuckyFunction.mobIDList[20]) {
            return (EntityLiving)new EntityChicken(a1);
        }
        /*SL:151*/if (a2.equals(LuckyFunction.mobNameList[21]) || a3 == LuckyFunction.mobIDList[21]) {
            return (EntityLiving)new EntitySquid(a1);
        }
        /*SL:152*/if (a2.equals(LuckyFunction.mobNameList[22]) || a3 == LuckyFunction.mobIDList[22]) {
            return (EntityLiving)new EntityWolf(a1);
        }
        /*SL:153*/if (a2.equals(LuckyFunction.mobNameList[23]) || a3 == LuckyFunction.mobIDList[23]) {
            return (EntityLiving)new EntityMooshroom(a1);
        }
        /*SL:154*/if (a2.equals(LuckyFunction.mobNameList[24]) || a3 == LuckyFunction.mobIDList[24]) {
            return (EntityLiving)new EntitySnowman(a1);
        }
        /*SL:155*/if (a2.equals(LuckyFunction.mobNameList[25]) || a3 == LuckyFunction.mobIDList[25]) {
            return (EntityLiving)new EntityOcelot(a1);
        }
        /*SL:156*/if (a2.equals(LuckyFunction.mobNameList[26]) || a3 == LuckyFunction.mobIDList[26]) {
            return (EntityLiving)new EntityIronGolem(a1);
        }
        /*SL:157*/if (a2.equals(LuckyFunction.mobNameList[27]) || a3 == LuckyFunction.mobIDList[27]) {
            return (EntityLiving)new EntityHorse(a1);
        }
        /*SL:158*/if (a2.equals(LuckyFunction.mobNameList[28]) || a3 == LuckyFunction.mobIDList[28]) {
            return (EntityLiving)new EntityVillager(a1);
        }
        /*SL:160*/return null;
    }
    
    public static Entity getEntity(final World a1, final int a2, final String a3) {
        /*SL:165*/if (!EntityList.field_75627_a.containsKey(a2)) {
            /*SL:167*/return EntityList.func_75620_a(a3, a1);
        }
        /*SL:171*/return EntityList.func_75616_a(a2, a1);
    }
    
    public static int getRandomMobEgg() {
        final Random v1 = /*EL:177*/new Random();
        /*SL:178*/return LuckyFunction.mobEggList[v1.nextInt(LuckyFunction.mobEggList.length)];
    }
    
    public void setOffset(final int a1, final int a2) {
        final Random v1 = /*EL:183*/new Random();
        int v2 = /*EL:188*/0;
        while (true) {
            final int v3 = /*EL:191*/a2 - v1.nextInt(a2 * 2 + 1);
            final int v4 = /*EL:192*/a2 - v1.nextInt(a2 * 2 + 1);
            /*SL:194*/if (v3 >= a1 || v3 <= a1 * -1 || v4 >= a1 || v4 <= a1 * -1) {
                /*SL:196*/this.XOffset = v3;
                /*SL:197*/this.ZOffset = v4;
                /*SL:199*/break;
            }
            /*SL:202*/if (v2 > 1000) {
                /*SL:204*/this.XOffset = a2;
                /*SL:205*/this.ZOffset = 0;
                /*SL:206*/break;
            }
            /*SL:209*/++v2;
        }
    }
    
    public int getXOffset() {
        /*SL:215*/return this.XOffset;
    }
    
    public int getZOffset() {
        /*SL:220*/return this.ZOffset;
    }
    
    public static int getPlayerDirection(final EntityPlayer a1, int a2) {
        int v1 = /*EL:225*/(int)a1.field_70177_z;
        /*SL:227*/a2 = 360 / a2;
        /*SL:229*/if (v1 < 0) {
            v1 += 360;
        }
        /*SL:230*/v1 += a2 / 2;
        /*SL:231*/v1 %= 360;
        final int v2 = /*EL:233*/v1 / a2;
        /*SL:235*/return v2;
    }
    
    public static int adjustHeight(final World a2, final int a3, final int a4, final int a5, final int v1) {
        boolean v2 = /*EL:240*/false;
        int v3 = /*EL:241*/a5;
        int v4 = /*EL:242*/0;
        /*SL:243*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:245*/if (a2.func_147439_a(a4, a6, v1).func_149662_c()) {
                /*SL:247*/v4 = 0;
                /*SL:248*/v3 = a6 + 1;
            }
            else {
                /*SL:252*/++v4;
            }
            /*SL:255*/if (v4 == a3) {
                /*SL:257*/v2 = true;
                /*SL:258*/break;
            }
        }
        /*SL:262*/if (v2) {
            /*SL:264*/return v3;
        }
        /*SL:268*/return -1;
    }
    
    public static String[] splitBracketString(final String v-7, final char v-6) {
        final char[] charArray = /*EL:275*/v-7.toCharArray();
        final int[] array = /*EL:276*/new int[1024];
        int n = /*EL:277*/1;
        int n2 = /*EL:278*/0;
        boolean b = /*EL:279*/false;
        /*SL:281*/array[0] = -1;
        /*SL:283*/for (boolean a2 = 0 != 0; a2 < charArray.length; ++a2) {
            /*SL:285*/if (charArray[a2] == '\"') {
                /*SL:287*/a2 = (a2 > 0 && charArray[a2 - 1] == '\\');
                /*SL:288*/if (!a2) {
                    b = !b;
                }
            }
            /*SL:291*/if ((charArray[a2] == '(' || charArray[a2] == '[' || charArray[a2] == '{') && !b) {
                /*SL:293*/++n2;
            }
            /*SL:295*/if ((charArray[a2] == ')' || charArray[a2] == ']' || charArray[a2] == '}') && !b) {
                /*SL:297*/--n2;
            }
            /*SL:299*/if (charArray[a2] == v-6 && n2 == 0 && !b) {
                /*SL:301*/array[n] = a2;
                /*SL:302*/++n;
            }
        }
        /*SL:305*/array[n] = v-7.length();
        final String[] v0 = /*EL:308*/new String[n];
        /*SL:309*/for (int v = 0; v < v0.length; ++v) {
            /*SL:311*/v0[v] = v-7.substring(array[v] + 1, array[v + 1]);
        }
        /*SL:314*/return v0;
    }
    
    public static NBTTagList getNBTTagListFromArray(final String[] v-1) {
        final NBTTagList v0 = /*EL:319*/new NBTTagList();
        /*SL:320*/for (final String a1 : v-1) {
            /*SL:322*/v0.func_74742_a((NBTBase)new NBTTagString(a1));
        }
        /*SL:324*/return v0;
    }
    
    public static String[] getArrayFromNBTTagList(final NBTTagList v1) {
        final String[] v2 = /*EL:329*/new String[(v1 == null) ? 0 : v1.func_74745_c()];
        /*SL:330*/for (int a1 = 0; a1 < v2.length; ++a1) {
            /*SL:332*/v2[a1] = v1.func_150307_f(a1);
        }
        /*SL:334*/return v2;
    }
    
    static {
        LuckyFunction.potionEffectList = new int[] { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14 };
        LuckyFunction.mobEggList = new int[] { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 90, 91, 92, 93, 94, 95, 96, 98, 100, 120 };
        LuckyFunction.mobIDList = new int[] { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 120 };
        LuckyFunction.mobNameList = new String[] { "creeper", "skeleton", "spider", "giant zombie", "zombie", "slime", "ghast", "zombie pigman", "enderman", "cave spider", "silverfish", "blaze", "magma cube", "ender dragon", "wither", "witch", "bat", "pig", "sheep", "cow", "chicken", "squid", "wolf", "mooshroom", "snow golem", "ocelot", "iron golem", "horse", "villager" };
    }
}
