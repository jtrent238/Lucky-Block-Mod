package mod.lucky.drop.value;

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
import com.google.common.collect.HashBiMap;

public class ValueParser
{
    public static HashBiMap<String, Class> classTypeToString;
    
    public static Object getObject(final String a1, final Class a2, final DropProcessData a3) throws Exception {
        /*SL:38*/if (a2 == String.class) {
            return getString(a1, a3);
        }
        /*SL:39*/if (a2 == Integer.class) {
            return getInteger(a1, a3);
        }
        /*SL:40*/if (a2 == Boolean.class) {
            return getBoolean(a1, a3);
        }
        /*SL:41*/if (a2 == Float.class) {
            return getFloat(a1, a3);
        }
        /*SL:42*/if (a2 == Double.class) {
            return getDouble(a1, a3);
        }
        /*SL:43*/if (a2 == Short.class) {
            return getShort(a1, a3);
        }
        /*SL:44*/if (a2 == Byte.class) {
            return getByte(a1, a3);
        }
        /*SL:45*/if (a2 == NBTTagCompound.class) {
            return getNBTTag(a1, a3);
        }
        /*SL:46*/return null;
    }
    
    public static NBTBase getNBTBaseFromValue(final Object a1) {
        /*SL:51*/if (a1.getClass() == String.class) {
            return (NBTBase)new NBTTagString((String)a1);
        }
        /*SL:52*/if (a1.getClass() == Integer.class) {
            return (NBTBase)new NBTTagInt((int)a1);
        }
        /*SL:53*/if (a1.getClass() == Boolean.class) {
            return (NBTBase)new NBTTagByte((byte)(((boolean)a1) ? 1 : 0));
        }
        /*SL:54*/if (a1.getClass() == Float.class) {
            return (NBTBase)new NBTTagFloat((float)a1);
        }
        /*SL:55*/if (a1.getClass() == NBTTagCompound.class) {
            return (NBTBase)a1;
        }
        /*SL:56*/return null;
    }
    
    public static Object getValueFromNBTBase(final NBTBase a1) {
        /*SL:61*/if (a1.getClass() == NBTTagString.class) {
            return ((NBTTagString)a1).func_150285_a_();
        }
        /*SL:62*/if (a1.getClass() == NBTTagInt.class) {
            return ((NBTTagInt)a1).func_150287_d();
        }
        /*SL:63*/if (a1.getClass() == NBTTagByte.class) {
            return ((NBTTagByte)a1).func_150290_f() == 1;
        }
        /*SL:64*/if (a1.getClass() == NBTTagFloat.class) {
            return ((NBTTagFloat)a1).func_150288_h();
        }
        /*SL:65*/if (a1.getClass() == NBTTagCompound.class) {
            return a1;
        }
        /*SL:66*/return null;
    }
    
    public static String getString(final String a1) throws NumberFormatException {
        /*SL:71*/return getString(a1, null);
    }
    
    public static String getString(String a1, final DropProcessData a2) {
        /*SL:76*/a1 = HashVariables.processString(a1, a2);
        /*SL:77*/a1 = StringEscapeUtils.unescapeJava(a1);
        /*SL:78*/if (a1.startsWith("\"") && a1.endsWith("\"")) {
            a1 = a1.substring(1, a1.length() - 1);
        }
        /*SL:79*/return a1;
    }
    
    public static Double calculateNum(String v-6) throws NumberFormatException {
        /*SL:84*/v-6 = DropStringUtils.removeNumSuffix(v-6);
        double doubleValue = /*EL:85*/0.0;
        final char v-7 = /*EL:86*/v-6.contains("+") ? '+' : (v-6.contains("-") ? '-' : (v-6.contains("*") ? '*' : (v-6.contains("/") ? '/' : '\0')));
        /*SL:87*/if (v-7 != '\0' && !v-6.trim().startsWith(String.valueOf(v-7))) {
            final String[] splitBracketString = /*EL:89*/DropStringUtils.splitBracketString(v-6, v-7);
            /*SL:90*/if (splitBracketString.length >= 2) {
                final double a1 = /*EL:92*/Double.valueOf(splitBracketString[0]);
                final double v1 = /*EL:93*/Double.valueOf(splitBracketString[1]);
                /*SL:95*/switch (v-7) {
                    case '+': {
                        /*SL:98*/doubleValue = a1 + v1;
                        /*SL:99*/break;
                    }
                    case '-': {
                        /*SL:101*/doubleValue = a1 - v1;
                        /*SL:102*/break;
                    }
                    case '*': {
                        /*SL:104*/doubleValue = a1 * v1;
                        /*SL:105*/break;
                    }
                    case '/': {
                        /*SL:107*/doubleValue = a1 / v1;
                        break;
                    }
                }
            }
        }
        else {
            /*SL:112*/doubleValue = Double.valueOf(v-6);
        }
        /*SL:114*/return doubleValue;
    }
    
    public static Integer getInteger(final String a1) throws NumberFormatException {
        /*SL:119*/return getInteger(a1, null);
    }
    
    public static Integer getInteger(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:124*/a1 = HashVariables.processString(a1, a2);
        /*SL:125*/return (int)(Object)calculateNum(a1);
    }
    
    public static Double getDouble(final String a1) throws NumberFormatException {
        /*SL:130*/return getDouble(a1, null);
    }
    
    public static Double getDouble(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:135*/a1 = HashVariables.processString(a1, a2);
        /*SL:136*/return calculateNum(a1);
    }
    
    public static Float getFloat(final String a1) throws NumberFormatException {
        /*SL:141*/return getFloat(a1, null);
    }
    
    public static Float getFloat(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:146*/a1 = HashVariables.processString(a1, a2);
        /*SL:147*/return (float)(Object)calculateNum(a1);
    }
    
    public static Boolean getBoolean(final String a1) throws NumberFormatException {
        /*SL:152*/return getBoolean(a1, null);
    }
    
    public static Boolean getBoolean(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:157*/a1 = HashVariables.processString(a1, a2);
        /*SL:158*/if (!a1.equals("true") && !a1.equals("false")) {
            throw new NumberFormatException("Lucky Block: Unknown boolean format: " + a1);
        }
        /*SL:159*/return Boolean.valueOf(a1);
    }
    
    public static Short getShort(final String a1) {
        /*SL:164*/return getShort(a1, null);
    }
    
    public static Short getShort(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:169*/a1 = HashVariables.processString(a1, a2);
        /*SL:170*/return (short)(Object)calculateNum(a1);
    }
    
    public static Byte getByte(final String a1) throws NumberFormatException {
        /*SL:175*/return getByte(a1, null);
    }
    
    public static Byte getByte(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:180*/a1 = HashVariables.processString(a1, a2);
        /*SL:181*/return (byte)(Object)calculateNum(a1);
    }
    
    public static NBTTagCompound getNBTTag(final String a1) throws Exception {
        /*SL:186*/return getNBTTag(a1, null);
    }
    
    public static NBTTagCompound getNBTTag(final String a1, final DropProcessData a2) throws Exception {
        final Object v1 = getNBTTagValue(/*EL:191*/a1, a2);
        /*SL:192*/if (v1 instanceof NBTTagCompound) {
            return (NBTTagCompound)v1;
        }
        /*SL:193*/throw new Exception();
    }
    
    public static Object getNBTTagValue(String v-6, final DropProcessData v-5) throws Exception {
        /*SL:198*/if (v-6.startsWith("(") && v-6.endsWith(")")) {
            final String[] array = /*EL:200*/DropStringUtils.splitBracketString(v-6.substring(1, v-6.length() - 1), ',');
            final NBTTagCompound a3 = /*EL:201*/new NBTTagCompound();
            /*SL:202*/for (final String v1 : array) {
                /*SL:204*/if (v1 != null) {
                    if (!v1.equals("")) {
                        final String a1 = /*EL:205*/v1.substring(0, v1.indexOf("="));
                        final String a2 = /*EL:206*/v1.substring(v1.indexOf("=") + 1);
                        setNBTTagValue(/*EL:207*/(NBTBase)a3, a1, getNBTTagValue(a2, v-5));
                    }
                }
            }
            /*SL:209*/return a3;
        }
        /*SL:211*/if (v-6.startsWith("[") && v-6.endsWith("]")) {
            final String[] array = /*EL:213*/DropStringUtils.splitBracketString(v-6.substring(1, v-6.length() - 1), ',');
            final NBTTagList a4 = /*EL:214*/new NBTTagList();
            /*SL:215*/for (final String v1 : array) {
                /*SL:217*/if (v1 != null) {
                    if (!v1.equals("")) {
                        setNBTTagValue(/*EL:218*/(NBTBase)a4, null, getNBTTagValue(v1, v-5));
                    }
                }
            }
            /*SL:220*/return a4;
        }
        /*SL:223*/if (v-6.startsWith("\"") && v-6.endsWith("\"")) {
            /*SL:225*/return getString(v-6, v-5);
        }
        /*SL:227*/if (v-6.startsWith("#")) {
            final NBTBase nbtTagFromString = /*EL:229*/CustomNBTTags.getNBTTagFromString(v-6, v-5);
            /*SL:230*/if (nbtTagFromString != null) {
                return nbtTagFromString;
            }
        }
        /*SL:233*/v-6 = getString(v-6, v-5);
        try {
            /*SL:236*/if (v-6.endsWith("f") || v-6.endsWith("F")) {
                return getFloat(DropStringUtils.removeNumSuffix(v-6), v-5);
            }
            /*SL:237*/if (v-6.endsWith("d") || v-6.endsWith("D") || DropStringUtils.hasDecimalPoint(v-6)) {
                return getDouble(DropStringUtils.removeNumSuffix(v-6), v-5);
            }
            /*SL:238*/if (v-6.endsWith("s") || v-6.endsWith("S")) {
                return getShort(DropStringUtils.removeNumSuffix(v-6), v-5);
            }
            /*SL:239*/if (v-6.endsWith("b") || v-6.endsWith("B")) {
                return getByte(DropStringUtils.removeNumSuffix(v-6), v-5);
            }
            /*SL:240*/if (v-6.equals("true") || v-6.equals("false")) {
                return getBoolean(DropStringUtils.removeNumSuffix(v-6), v-5);
            }
            /*SL:241*/return getInteger(DropStringUtils.removeNumSuffix(v-6), v-5);
        }
        catch (Exception ex) {
            try {
                final String[] array = /*EL:248*/v-6.split(":");
                /*SL:249*/if (array.length > 1) {
                    int n2 = /*EL:251*/0;
                    /*SL:252*/if (array[0].endsWith("b") || array[0].endsWith("B")) {
                        n2 = 1;
                    }
                    final int[] array4 = /*EL:254*/new int[array.length];
                    final byte[] array5 = /*EL:255*/new byte[array.length];
                    /*SL:256*/for (int v2 = 0; v2 < array.length; ++v2) {
                        /*SL:258*/if (n2 == 0) {
                            array4[v2] = getInteger(array[v2], v-5);
                        }
                        else {
                            /*SL:261*/array[v2] = DropStringUtils.removeNumSuffix(array[v2]);
                            /*SL:262*/array5[v2] = getByte(array[v2], v-5);
                        }
                    }
                    /*SL:266*/if (n2 == 0 && array4.length > 0) {
                        return array4;
                    }
                    /*SL:267*/if (n2 == 1 && array5.length > 0) {
                        return array5;
                    }
                }
            }
            catch (Exception ex2) {}
            /*SL:274*/return v-6;
        }
    }
    
    public static void setNBTTagValue(final NBTBase a3, final String v1, final Object v2) {
        /*SL:279*/if (a3 instanceof NBTTagCompound) {
            final NBTTagCompound a4 = /*EL:281*/(NBTTagCompound)a3;
            /*SL:282*/if (v2 instanceof String) {
                a4.func_74778_a(v1, (String)v2);
            }
            /*SL:283*/if (v2 instanceof Boolean) {
                a4.func_74757_a(v1, (boolean)v2);
            }
            /*SL:284*/if (v2 instanceof Integer) {
                a4.func_74768_a(v1, (int)v2);
            }
            /*SL:285*/if (v2 instanceof Float) {
                a4.func_74776_a(v1, (float)v2);
            }
            /*SL:286*/if (v2 instanceof Double) {
                a4.func_74780_a(v1, (double)v2);
            }
            /*SL:287*/if (v2 instanceof Short) {
                a4.func_74777_a(v1, (short)v2);
            }
            /*SL:288*/if (v2 instanceof Byte) {
                a4.func_74774_a(v1, (byte)v2);
            }
            /*SL:289*/if (v2 instanceof int[]) {
                a4.func_74783_a(v1, (int[])v2);
            }
            /*SL:290*/if (v2 instanceof byte[]) {
                a4.func_74773_a(v1, (byte[])v2);
            }
            /*SL:291*/if (v2 instanceof NBTTagCompound) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
            /*SL:292*/if (v2 instanceof NBTTagList) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
        }
        /*SL:294*/if (a3 instanceof NBTTagList) {
            final NBTTagList a5 = /*EL:296*/(NBTTagList)a3;
            /*SL:297*/if (v2 instanceof String) {
                a5.func_74742_a((NBTBase)new NBTTagString((String)v2));
            }
            /*SL:298*/if (v2 instanceof Integer) {
                a5.func_74742_a((NBTBase)new NBTTagInt((int)v2));
            }
            /*SL:299*/if (v2 instanceof Float) {
                a5.func_74742_a((NBTBase)new NBTTagFloat((float)v2));
            }
            /*SL:300*/if (v2 instanceof Double) {
                a5.func_74742_a((NBTBase)new NBTTagDouble((double)v2));
            }
            /*SL:301*/if (v2 instanceof Short) {
                a5.func_74742_a((NBTBase)new NBTTagShort((short)v2));
            }
            /*SL:302*/if (v2 instanceof Byte) {
                a5.func_74742_a((NBTBase)new NBTTagByte((byte)v2));
            }
            /*SL:303*/if (v2 instanceof int[]) {
                a5.func_74742_a((NBTBase)new NBTTagIntArray((int[])v2));
            }
            /*SL:304*/if (v2 instanceof byte[]) {
                a5.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v2));
            }
            /*SL:305*/if (v2 instanceof NBTTagCompound) {
                a5.func_74742_a((NBTBase)v2);
            }
            /*SL:306*/if (v2 instanceof NBTTagList) {
                a5.func_74742_a((NBTBase)v2);
            }
        }
    }
    
    public static Item getItem(final String v-2, final DropProcessData v-1) {
        Item v0;
        try {
            final int a1 = getInteger(/*EL:315*/v-2, v-1);
            /*SL:316*/v0 = Item.func_150899_d(a1);
        }
        catch (Exception v) {
            final String a2 = getString(/*EL:320*/v-2, v-1);
            /*SL:321*/v0 = Item.func_111206_d(v-2);
        }
        /*SL:323*/return v0;
    }
    
    public static Block getBlock(final String v-2, final DropProcessData v-1) {
        Block v0;
        try {
            final int a1 = getInteger(/*EL:331*/v-2, v-1);
            /*SL:332*/v0 = Block.func_149729_e(a1);
        }
        catch (Exception v) {
            final String a2 = getString(/*EL:336*/v-2, v-1);
            /*SL:337*/v0 = Block.func_149684_b(v-2);
        }
        /*SL:339*/return v0;
    }
    
    public static ItemStack getItemStack(final String v-1, final DropProcessData v0) {
        try {
            final String[] v = /*EL:346*/v-1.split(":");
            final Item v2 = getItem(/*EL:347*/v[0], null);
            /*SL:349*/if (v.length == 1 && v2 != null) {
                return new ItemStack(v2);
            }
            /*SL:350*/if (v.length == 2) {
                final Item a1 = getItem(/*EL:352*/v[0] + v[1], v0);
                /*SL:353*/if (a1 == null && v2 != null) {
                    return new ItemStack(v2, 1, (int)getInteger(v[1]));
                }
                /*SL:354*/return new ItemStack(a1);
            }
            else/*SL:356*/ if (v.length >= 3) {
                final Item a2 = getItem(/*EL:358*/v[0] + v[1], v0);
                /*SL:359*/if (a2 != null) {
                    return new ItemStack(a2, 1, (int)getInteger(v[3]));
                }
            }
        }
        catch (Exception v3) {
            /*SL:364*/return null;
        }
        /*SL:366*/return null;
    }
    
    static {
        (ValueParser.classTypeToString = (HashBiMap<String, Class>)HashBiMap.<String, Class>create()).put("string", String.class);
        ValueParser.classTypeToString.put("int", Integer.class);
        ValueParser.classTypeToString.put("boolean", Boolean.class);
        ValueParser.classTypeToString.put("float", Float.class);
        ValueParser.classTypeToString.put("nbt", NBTTagCompound.class);
    }
}
