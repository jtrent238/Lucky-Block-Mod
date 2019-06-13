package mod.lucky.drop.value;

import java.util.Collection;
import java.util.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import org.apache.commons.lang3.StringEscapeUtils;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessData;
import java.util.ArrayList;
import com.google.common.collect.HashBiMap;

public class ValueParser
{
    public static HashBiMap<String, Class> classTypeToString;
    private static ArrayList<String> DROP_TAGS;
    
    public static Object getObject(final String a1, final Class a2, final DropProcessData a3) throws Exception {
        /*SL:27*/if (a2 == String.class) {
            return getString(a1, a3);
        }
        /*SL:28*/if (a2 == Integer.class) {
            return getInteger(a1, a3);
        }
        /*SL:29*/if (a2 == Boolean.class) {
            return getBoolean(a1, a3);
        }
        /*SL:30*/if (a2 == Float.class) {
            return getFloat(a1, a3);
        }
        /*SL:31*/if (a2 == Double.class) {
            return getDouble(a1, a3);
        }
        /*SL:32*/if (a2 == Short.class) {
            return getShort(a1, a3);
        }
        /*SL:33*/if (a2 == Byte.class) {
            return getByte(a1, a3);
        }
        /*SL:34*/if (a2 == NBTTagCompound.class) {
            return getNBTTag(a1, a3);
        }
        /*SL:35*/return null;
    }
    
    public static NBTBase getNBTBaseFromValue(final Object a1) {
        /*SL:39*/if (a1.getClass() == String.class) {
            return (NBTBase)new NBTTagString((String)a1);
        }
        /*SL:40*/if (a1.getClass() == Integer.class) {
            return (NBTBase)new NBTTagInt((int)a1);
        }
        /*SL:41*/if (a1.getClass() == Boolean.class) {
            /*SL:42*/return (NBTBase)new NBTTagByte((byte)(((boolean)a1) ? 1 : 0));
        }
        /*SL:43*/if (a1.getClass() == Float.class) {
            return (NBTBase)new NBTTagFloat((float)a1);
        }
        /*SL:44*/if (a1.getClass() == NBTTagCompound.class) {
            return (NBTBase)a1;
        }
        /*SL:45*/return null;
    }
    
    public static Object getValueFromNBTBase(final NBTBase a1) {
        /*SL:49*/if (a1.getClass() == NBTTagString.class) {
            return ((NBTTagString)a1).func_150285_a_();
        }
        /*SL:50*/if (a1.getClass() == NBTTagInt.class) {
            return ((NBTTagInt)a1).func_150287_d();
        }
        /*SL:51*/if (a1.getClass() == NBTTagByte.class) {
            /*SL:52*/return ((NBTTagByte)a1).func_150290_f() == 1;
        }
        /*SL:53*/if (a1.getClass() == NBTTagFloat.class) {
            return ((NBTTagFloat)a1).func_150288_h();
        }
        /*SL:54*/if (a1.getClass() == NBTTagCompound.class) {
            return a1;
        }
        /*SL:55*/return null;
    }
    
    public static String getString(final String a1) throws NumberFormatException {
        /*SL:59*/return getString(a1, null);
    }
    
    public static String getString(String a1, final DropProcessData a2) {
        /*SL:63*/a1 = HashVariables.processString(a1, a2);
        /*SL:64*/a1 = StringEscapeUtils.unescapeJava(a1);
        /*SL:65*/if (a1.startsWith("\"") && a1.endsWith("\"")) {
            /*SL:66*/a1 = a1.substring(1, a1.length() - 1);
        }
        /*SL:67*/return a1;
    }
    
    public static Double calculateNum(String v-7) throws NumberFormatException {
        /*SL:71*/v-7 = DropStringUtils.removeNumSuffix(v-7);
        /*SL:72*/v-7 = v-7.trim();
        double doubleValue = /*EL:73*/0.0;
        String substring = /*EL:75*/"";
        /*SL:76*/if (v-7.startsWith("-") || v-7.startsWith("+")) {
            /*SL:77*/substring = v-7.substring(0, 1);
            /*SL:78*/v-7 = v-7.substring(1, v-7.length());
        }
        final char v-8 = /*EL:81*/v-7.contains("+") ? /*EL:82*/'+' : (v-7.contains("-") ? /*EL:84*/'-' : (v-7.contains("*") ? /*EL:86*/'*' : (v-7.contains("/") ? '/' : '\0')));
        /*SL:87*/if (v-8 != '\0') {
            final String[] splitBracketString = /*EL:88*/DropStringUtils.splitBracketString(v-7, v-8);
            /*SL:89*/if (splitBracketString.length >= 2) {
                final double a1 = /*EL:90*/Double.valueOf(substring + splitBracketString[0]);
                final double v1 = /*EL:91*/Double.valueOf(splitBracketString[1]);
                /*SL:93*/switch (v-8) {
                    case '+': {
                        /*SL:95*/doubleValue = a1 + v1;
                        /*SL:96*/break;
                    }
                    case '-': {
                        /*SL:98*/doubleValue = a1 - v1;
                        /*SL:99*/break;
                    }
                    case '*': {
                        /*SL:101*/doubleValue = a1 * v1;
                        /*SL:102*/break;
                    }
                    case '/': {
                        /*SL:104*/doubleValue = a1 / v1;
                        break;
                    }
                }
            }
        }
        else {
            /*SL:108*/doubleValue = Double.valueOf(substring + v-7);
        }
        /*SL:110*/return doubleValue;
    }
    
    public static Integer getInteger(final String a1) throws NumberFormatException {
        /*SL:114*/return getInteger(a1, null);
    }
    
    public static Integer getInteger(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:119*/a1 = HashVariables.processString(a1, a2);
        /*SL:120*/return (int)(Object)calculateNum(a1);
    }
    
    public static Double getDouble(final String a1) throws NumberFormatException {
        /*SL:124*/return getDouble(a1, null);
    }
    
    public static Double getDouble(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:129*/a1 = HashVariables.processString(a1, a2);
        /*SL:130*/return calculateNum(a1);
    }
    
    public static Float getFloat(final String a1) throws NumberFormatException {
        /*SL:134*/return getFloat(a1, null);
    }
    
    public static Float getFloat(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:139*/a1 = HashVariables.processString(a1, a2);
        /*SL:140*/return (float)(Object)calculateNum(a1);
    }
    
    public static Boolean getBoolean(final String a1) throws NumberFormatException {
        /*SL:144*/return getBoolean(a1, null);
    }
    
    public static Boolean getBoolean(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:149*/a1 = HashVariables.processString(a1, a2);
        /*SL:150*/if (!a1.equals("true") && !a1.equals("false")) {
            /*SL:151*/throw new NumberFormatException("Lucky Block: Unknown boolean format: " + a1);
        }
        /*SL:152*/return Boolean.valueOf(a1);
    }
    
    public static Short getShort(final String a1) {
        /*SL:156*/return getShort(a1, null);
    }
    
    public static Short getShort(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:161*/a1 = HashVariables.processString(a1, a2);
        /*SL:162*/return (short)(Object)calculateNum(a1);
    }
    
    public static Byte getByte(final String a1) throws NumberFormatException {
        /*SL:166*/return getByte(a1, null);
    }
    
    public static Byte getByte(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:171*/a1 = HashVariables.processString(a1, a2);
        /*SL:172*/return (byte)(Object)calculateNum(a1);
    }
    
    public static NBTTagCompound getNBTTag(final String a1) throws Exception {
        /*SL:176*/return getNBTTag(a1, null);
    }
    
    public static NBTTagCompound getNBTTag(final String a1, final DropProcessData a2) throws Exception {
        final Object v1 = getNBTTagValue(/*EL:181*/a1, a2);
        /*SL:182*/if (v1 instanceof NBTTagCompound) {
            return (NBTTagCompound)v1;
        }
        /*SL:183*/throw new Exception();
    }
    
    public static Object getNBTTagValue(final String a1, final DropProcessData a2) throws Exception {
        /*SL:187*/return getNBTTagValue(a1, a2, null, null);
    }
    
    public static Object getNBTTagValue(String v-4, final DropProcessData v-3, final NBTBase v-2, final String v-1) throws Exception {
        /*SL:196*/if (v-4.startsWith("(") && v-4.endsWith(")")) {
            String[] a4 = /*EL:198*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagCompound v1 = /*EL:199*/new NBTTagCompound();
            /*SL:200*/for (final String a2 : a4) {
                /*SL:201*/if (a2 != null) {
                    if (!a2.equals("")) {
                        final String a3 = /*EL:202*/a2.substring(0, a2.indexOf("="));
                        /*SL:203*/a4 = a2.substring(a2.indexOf("=") + 1);
                        setNBTTagValue(/*EL:204*/(NBTBase)v1, a3, getNBTTagValue(a4, v-3, (NBTBase)v1, a3));
                    }
                }
            }
            /*SL:207*/return v1;
        }
        /*SL:209*/if (v-4.startsWith("[") && v-4.endsWith("]")) {
            final String[] v2 = /*EL:211*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagList v3 = /*EL:212*/new NBTTagList();
            /*SL:213*/for (final String v4 : v2) {
                /*SL:214*/if (v4 != null) {
                    if (!v4.equals("")) {
                        setNBTTagValue(/*EL:215*/(NBTBase)v3, null, getNBTTagValue(v4, v-3, (NBTBase)v3, v-1));
                    }
                }
            }
            /*SL:217*/return v3;
        }
        /*SL:220*/if (v-4.startsWith("\"") && v-4.endsWith("\"")) {
            /*SL:221*/if (v-2 != null && v-2 instanceof NBTTagList && v-1 != null && ValueParser.DROP_TAGS.contains(v-1.toLowerCase())) {
                /*SL:226*/v-4 = HashVariables.autoCancelHash(v-4);
            }
            /*SL:228*/return getString(v-4, v-3);
        }
        /*SL:230*/if (v-4.startsWith("#")) {
            final NBTBase v5 = /*EL:231*/CustomNBTTags.getNBTTagFromString(v-4, v-3);
            /*SL:232*/if (v5 != null) {
                return v5;
            }
        }
        /*SL:235*/v-4 = getString(v-4, v-3);
        try {
            /*SL:237*/if (v-4.endsWith("f") || v-4.endsWith("F")) {
                /*SL:238*/return getFloat(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:239*/if (v-4.endsWith("d") || v-4.endsWith("D") || DropStringUtils.hasDecimalPoint(v-4)) {
                /*SL:240*/return getDouble(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:241*/if (v-4.endsWith("s") || v-4.endsWith("S")) {
                /*SL:242*/return getShort(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:243*/if (v-4.endsWith("b") || v-4.endsWith("B")) {
                /*SL:244*/return getByte(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:245*/if (v-4.equals("true") || v-4.equals("false")) {
                /*SL:246*/return getBoolean(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:247*/return getInteger(DropStringUtils.removeNumSuffix(v-4), v-3);
        }
        catch (Exception ex) {
            try {
                final String[] v2 = /*EL:251*/v-4.split(":");
                /*SL:252*/if (v2.length >= 1) {
                    int v6 = /*EL:253*/0;
                    /*SL:254*/if (v2[0].endsWith("b") || v2[0].endsWith("B")) {
                        v6 = 1;
                    }
                    final int[] v7 = /*EL:256*/new int[v2.length];
                    final byte[] v8 = /*EL:257*/new byte[v2.length];
                    /*SL:258*/for (int v9 = 0; v9 < v2.length; ++v9) {
                        /*SL:259*/if (v6 == 0) {
                            v7[v9] = getInteger(v2[v9], v-3);
                        }
                        else {
                            /*SL:261*/v2[v9] = DropStringUtils.removeNumSuffix(v2[v9]);
                            /*SL:262*/v8[v9] = getByte(v2[v9], v-3);
                        }
                    }
                    /*SL:266*/if (v6 == 0 && v7.length > 0) {
                        return v7;
                    }
                    /*SL:267*/if (v6 == 1 && v8.length > 0) {
                        return v8;
                    }
                }
            }
            catch (Exception ex2) {}
            /*SL:272*/return v-4;
        }
    }
    
    public static void setNBTTagValue(final NBTBase a3, final String v1, final Object v2) {
        /*SL:276*/if (a3 instanceof NBTTagCompound) {
            final NBTTagCompound a4 = /*EL:277*/(NBTTagCompound)a3;
            /*SL:278*/if (v2 instanceof String) {
                a4.func_74778_a(v1, (String)v2);
            }
            /*SL:279*/if (v2 instanceof Boolean) {
                a4.func_74757_a(v1, (boolean)v2);
            }
            /*SL:280*/if (v2 instanceof Integer) {
                a4.func_74768_a(v1, (int)v2);
            }
            /*SL:281*/if (v2 instanceof Float) {
                a4.func_74776_a(v1, (float)v2);
            }
            /*SL:282*/if (v2 instanceof Double) {
                a4.func_74780_a(v1, (double)v2);
            }
            /*SL:283*/if (v2 instanceof Short) {
                a4.func_74777_a(v1, (short)v2);
            }
            /*SL:284*/if (v2 instanceof Byte) {
                a4.func_74774_a(v1, (byte)v2);
            }
            /*SL:285*/if (v2 instanceof int[]) {
                a4.func_74783_a(v1, (int[])v2);
            }
            /*SL:286*/if (v2 instanceof byte[]) {
                a4.func_74773_a(v1, (byte[])v2);
            }
            /*SL:287*/if (v2 instanceof NBTTagCompound) {
                /*SL:288*/a4.func_74782_a(v1, (NBTBase)v2);
            }
            /*SL:289*/if (v2 instanceof NBTTagList) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
        }
        /*SL:291*/if (a3 instanceof NBTTagList) {
            final NBTTagList a5 = /*EL:292*/(NBTTagList)a3;
            /*SL:293*/if (v2 instanceof String) {
                a5.func_74742_a((NBTBase)new NBTTagString((String)v2));
            }
            /*SL:294*/if (v2 instanceof Integer) {
                a5.func_74742_a((NBTBase)new NBTTagInt((int)v2));
            }
            /*SL:295*/if (v2 instanceof Float) {
                a5.func_74742_a((NBTBase)new NBTTagFloat((float)v2));
            }
            /*SL:296*/if (v2 instanceof Double) {
                a5.func_74742_a((NBTBase)new NBTTagDouble((double)v2));
            }
            /*SL:297*/if (v2 instanceof Short) {
                a5.func_74742_a((NBTBase)new NBTTagShort((short)v2));
            }
            /*SL:298*/if (v2 instanceof Byte) {
                a5.func_74742_a((NBTBase)new NBTTagByte((byte)v2));
            }
            /*SL:299*/if (v2 instanceof int[]) {
                a5.func_74742_a((NBTBase)new NBTTagIntArray((int[])v2));
            }
            /*SL:300*/if (v2 instanceof byte[]) {
                a5.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v2));
            }
            /*SL:301*/if (v2 instanceof NBTTagCompound) {
                a5.func_74742_a((NBTBase)v2);
            }
            /*SL:302*/if (v2 instanceof NBTTagList) {
                a5.func_74742_a((NBTBase)v2);
            }
        }
    }
    
    public static Item getItem(final String v-3, final DropProcessData v-2) {
        Item func_111206_d = null;
        try {
            final int a1 = getInteger(/*EL:309*/v-3, v-2);
            final Item a2 = /*EL:310*/Item.func_150899_d(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:312*/v-3, v-2);
            /*SL:313*/func_111206_d = Item.func_111206_d(v-3);
        }
        /*SL:315*/return func_111206_d;
    }
    
    public static Block getBlock(final String v-3, final DropProcessData v-2) {
        Block func_149684_b = null;
        try {
            final int a1 = getInteger(/*EL:321*/v-3, v-2);
            final Block a2 = /*EL:322*/Block.func_149729_e(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:324*/v-3, v-2);
            /*SL:325*/func_149684_b = Block.func_149684_b(v-3);
        }
        /*SL:327*/return func_149684_b;
    }
    
    public static ItemStack getItemStack(final String v-1, final DropProcessData v0) {
        try {
            final String[] v = /*EL:332*/v-1.split(":");
            final Item v2 = getItem(/*EL:333*/v[0], null);
            /*SL:335*/if (v.length == 1 && v2 != null) {
                return new ItemStack(v2);
            }
            /*SL:336*/if (v.length == 2) {
                final Item a1 = getItem(/*EL:337*/v[0] + v[1], v0);
                /*SL:338*/if (a1 == null && v2 != null) {
                    /*SL:339*/return new ItemStack(v2, 1, (int)getInteger(v[1]));
                }
                /*SL:340*/return new ItemStack(a1);
            }
            else/*SL:341*/ if (v.length >= 3) {
                final Item a2 = getItem(/*EL:342*/v[0] + v[1], v0);
                /*SL:343*/if (a2 != null) {
                    return new ItemStack(a2, 1, (int)getInteger(v[3]));
                }
            }
        }
        catch (Exception v3) {
            /*SL:346*/return null;
        }
        /*SL:348*/return null;
    }
    
    static {
        (ValueParser.classTypeToString = (HashBiMap<String, Class>)HashBiMap.<String, Class>create()).put("string", String.class);
        ValueParser.classTypeToString.put("int", Integer.class);
        ValueParser.classTypeToString.put("boolean", Boolean.class);
        ValueParser.classTypeToString.put("float", Float.class);
        ValueParser.classTypeToString.put("nbt", NBTTagCompound.class);
        ValueParser.DROP_TAGS = new ArrayList<String>(Arrays.<String>asList("drops", "impact"));
    }
}
