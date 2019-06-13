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
        /*SL:42*/if (a2 == String.class) {
            return getString(a1, a3);
        }
        /*SL:43*/if (a2 == Integer.class) {
            return getInteger(a1, a3);
        }
        /*SL:44*/if (a2 == Boolean.class) {
            return getBoolean(a1, a3);
        }
        /*SL:45*/if (a2 == Float.class) {
            return getFloat(a1, a3);
        }
        /*SL:46*/if (a2 == Double.class) {
            return getDouble(a1, a3);
        }
        /*SL:47*/if (a2 == Short.class) {
            return getShort(a1, a3);
        }
        /*SL:48*/if (a2 == Byte.class) {
            return getByte(a1, a3);
        }
        /*SL:49*/if (a2 == NBTTagCompound.class) {
            return getNBTTag(a1, a3);
        }
        /*SL:50*/return null;
    }
    
    public static NBTBase getNBTBaseFromValue(final Object a1) {
        /*SL:55*/if (a1.getClass() == String.class) {
            return (NBTBase)new NBTTagString((String)a1);
        }
        /*SL:56*/if (a1.getClass() == Integer.class) {
            return (NBTBase)new NBTTagInt((int)a1);
        }
        /*SL:57*/if (a1.getClass() == Boolean.class) {
            return (NBTBase)new NBTTagByte((byte)(((boolean)a1) ? 1 : 0));
        }
        /*SL:58*/if (a1.getClass() == Float.class) {
            return (NBTBase)new NBTTagFloat((float)a1);
        }
        /*SL:59*/if (a1.getClass() == NBTTagCompound.class) {
            return (NBTBase)a1;
        }
        /*SL:60*/return null;
    }
    
    public static Object getValueFromNBTBase(final NBTBase a1) {
        /*SL:65*/if (a1.getClass() == NBTTagString.class) {
            return ((NBTTagString)a1).func_150285_a_();
        }
        /*SL:66*/if (a1.getClass() == NBTTagInt.class) {
            return ((NBTTagInt)a1).func_150287_d();
        }
        /*SL:67*/if (a1.getClass() == NBTTagByte.class) {
            return ((NBTTagByte)a1).func_150290_f() == 1;
        }
        /*SL:68*/if (a1.getClass() == NBTTagFloat.class) {
            return ((NBTTagFloat)a1).func_150288_h();
        }
        /*SL:69*/if (a1.getClass() == NBTTagCompound.class) {
            return a1;
        }
        /*SL:70*/return null;
    }
    
    public static String getString(final String a1) throws NumberFormatException {
        /*SL:75*/return getString(a1, null);
    }
    
    public static String getString(String a1, final DropProcessData a2) {
        /*SL:80*/a1 = HashVariables.processString(a1, a2);
        /*SL:81*/a1 = StringEscapeUtils.unescapeJava(a1);
        /*SL:82*/if (a1.startsWith("\"") && a1.endsWith("\"")) {
            a1 = a1.substring(1, a1.length() - 1);
        }
        /*SL:83*/return a1;
    }
    
    public static Double calculateNum(String v-7) throws NumberFormatException {
        /*SL:88*/v-7 = DropStringUtils.removeNumSuffix(v-7);
        /*SL:89*/v-7 = v-7.trim();
        double doubleValue = /*EL:90*/0.0;
        String substring = /*EL:92*/"";
        /*SL:93*/if (v-7.startsWith("-") || v-7.startsWith("+")) {
            /*SL:95*/substring = v-7.substring(0, 1);
            /*SL:96*/v-7 = v-7.substring(1, v-7.length());
        }
        final char v-8 = /*EL:99*/v-7.contains("+") ? '+' : (v-7.contains("-") ? '-' : (v-7.contains("*") ? '*' : (v-7.contains("/") ? '/' : '\0')));
        /*SL:100*/if (v-8 != '\0') {
            final String[] splitBracketString = /*EL:102*/DropStringUtils.splitBracketString(v-7, v-8);
            /*SL:103*/if (splitBracketString.length >= 2) {
                final double a1 = /*EL:105*/Double.valueOf(substring + splitBracketString[0]);
                final double v1 = /*EL:106*/Double.valueOf(splitBracketString[1]);
                /*SL:108*/switch (v-8) {
                    case '+': {
                        /*SL:111*/doubleValue = a1 + v1;
                        /*SL:112*/break;
                    }
                    case '-': {
                        /*SL:114*/doubleValue = a1 - v1;
                        /*SL:115*/break;
                    }
                    case '*': {
                        /*SL:117*/doubleValue = a1 * v1;
                        /*SL:118*/break;
                    }
                    case '/': {
                        /*SL:120*/doubleValue = a1 / v1;
                        break;
                    }
                }
            }
        }
        else {
            /*SL:125*/doubleValue = Double.valueOf(substring + v-7);
        }
        /*SL:127*/return doubleValue;
    }
    
    public static Integer getInteger(final String a1) throws NumberFormatException {
        /*SL:132*/return getInteger(a1, null);
    }
    
    public static Integer getInteger(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:137*/a1 = HashVariables.processString(a1, a2);
        /*SL:138*/return (int)(Object)calculateNum(a1);
    }
    
    public static Double getDouble(final String a1) throws NumberFormatException {
        /*SL:143*/return getDouble(a1, null);
    }
    
    public static Double getDouble(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:148*/a1 = HashVariables.processString(a1, a2);
        /*SL:149*/return calculateNum(a1);
    }
    
    public static Float getFloat(final String a1) throws NumberFormatException {
        /*SL:154*/return getFloat(a1, null);
    }
    
    public static Float getFloat(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:159*/a1 = HashVariables.processString(a1, a2);
        /*SL:160*/return (float)(Object)calculateNum(a1);
    }
    
    public static Boolean getBoolean(final String a1) throws NumberFormatException {
        /*SL:165*/return getBoolean(a1, null);
    }
    
    public static Boolean getBoolean(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:170*/a1 = HashVariables.processString(a1, a2);
        /*SL:171*/if (!a1.equals("true") && !a1.equals("false")) {
            throw new NumberFormatException("Lucky Block: Unknown boolean format: " + a1);
        }
        /*SL:172*/return Boolean.valueOf(a1);
    }
    
    public static Short getShort(final String a1) {
        /*SL:177*/return getShort(a1, null);
    }
    
    public static Short getShort(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:182*/a1 = HashVariables.processString(a1, a2);
        /*SL:183*/return (short)(Object)calculateNum(a1);
    }
    
    public static Byte getByte(final String a1) throws NumberFormatException {
        /*SL:188*/return getByte(a1, null);
    }
    
    public static Byte getByte(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:193*/a1 = HashVariables.processString(a1, a2);
        /*SL:194*/return (byte)(Object)calculateNum(a1);
    }
    
    public static NBTTagCompound getNBTTag(final String a1) throws Exception {
        /*SL:199*/return getNBTTag(a1, null);
    }
    
    public static NBTTagCompound getNBTTag(final String a1, final DropProcessData a2) throws Exception {
        final Object v1 = getNBTTagValue(/*EL:204*/a1, a2);
        /*SL:205*/if (v1 instanceof NBTTagCompound) {
            return (NBTTagCompound)v1;
        }
        /*SL:206*/throw new Exception();
    }
    
    public static Object getNBTTagValue(final String a1, final DropProcessData a2) throws Exception {
        /*SL:211*/return getNBTTagValue(a1, a2, null, null);
    }
    
    public static Object getNBTTagValue(String v-4, final DropProcessData v-3, final NBTBase v-2, final String v-1) throws Exception {
        /*SL:218*/if (v-4.startsWith("(") && v-4.endsWith(")")) {
            String[] a4 = /*EL:220*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagCompound v1 = /*EL:221*/new NBTTagCompound();
            /*SL:222*/for (final String a2 : a4) {
                /*SL:224*/if (a2 != null) {
                    if (!a2.equals("")) {
                        final String a3 = /*EL:225*/a2.substring(0, a2.indexOf("="));
                        /*SL:226*/a4 = a2.substring(a2.indexOf("=") + 1);
                        setNBTTagValue(/*EL:227*/(NBTBase)v1, a3, getNBTTagValue(a4, v-3, (NBTBase)v1, a3));
                    }
                }
            }
            /*SL:229*/return v1;
        }
        /*SL:231*/if (v-4.startsWith("[") && v-4.endsWith("]")) {
            final String[] v2 = /*EL:233*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagList v3 = /*EL:234*/new NBTTagList();
            /*SL:235*/for (final String v4 : v2) {
                /*SL:237*/if (v4 != null) {
                    if (!v4.equals("")) {
                        setNBTTagValue(/*EL:238*/(NBTBase)v3, null, getNBTTagValue(v4, v-3, (NBTBase)v3, v-1));
                    }
                }
            }
            /*SL:240*/return v3;
        }
        /*SL:243*/if (v-4.startsWith("\"") && v-4.endsWith("\"")) {
            /*SL:245*/if (v-2 != null && v-2 instanceof NBTTagList && v-1 != null && ValueParser.DROP_TAGS.contains(v-1.toLowerCase())) {
                /*SL:248*/v-4 = HashVariables.autoCancelHash(v-4);
            }
            /*SL:250*/return getString(v-4, v-3);
        }
        /*SL:252*/if (v-4.startsWith("#")) {
            final NBTBase v5 = /*EL:254*/CustomNBTTags.getNBTTagFromString(v-4, v-3);
            /*SL:255*/if (v5 != null) {
                return v5;
            }
        }
        /*SL:258*/v-4 = getString(v-4, v-3);
        try {
            /*SL:261*/if (v-4.endsWith("f") || v-4.endsWith("F")) {
                return getFloat(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:262*/if (v-4.endsWith("d") || v-4.endsWith("D") || DropStringUtils.hasDecimalPoint(v-4)) {
                return getDouble(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:263*/if (v-4.endsWith("s") || v-4.endsWith("S")) {
                return getShort(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:264*/if (v-4.endsWith("b") || v-4.endsWith("B")) {
                return getByte(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:265*/if (v-4.equals("true") || v-4.equals("false")) {
                return getBoolean(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:266*/return getInteger(DropStringUtils.removeNumSuffix(v-4), v-3);
        }
        catch (Exception ex) {
            try {
                final String[] v2 = /*EL:273*/v-4.split(":");
                /*SL:274*/if (v2.length >= 1) {
                    int v6 = /*EL:276*/0;
                    /*SL:277*/if (v2[0].endsWith("b") || v2[0].endsWith("B")) {
                        v6 = 1;
                    }
                    final int[] v7 = /*EL:279*/new int[v2.length];
                    final byte[] v8 = /*EL:280*/new byte[v2.length];
                    /*SL:281*/for (int v9 = 0; v9 < v2.length; ++v9) {
                        /*SL:283*/if (v6 == 0) {
                            v7[v9] = getInteger(v2[v9], v-3);
                        }
                        else {
                            /*SL:286*/v2[v9] = DropStringUtils.removeNumSuffix(v2[v9]);
                            /*SL:287*/v8[v9] = getByte(v2[v9], v-3);
                        }
                    }
                    /*SL:291*/if (v6 == 0 && v7.length > 0) {
                        return v7;
                    }
                    /*SL:292*/if (v6 == 1 && v8.length > 0) {
                        return v8;
                    }
                }
            }
            catch (Exception ex2) {}
            /*SL:299*/return v-4;
        }
    }
    
    public static void setNBTTagValue(final NBTBase a3, final String v1, final Object v2) {
        /*SL:304*/if (a3 instanceof NBTTagCompound) {
            final NBTTagCompound a4 = /*EL:306*/(NBTTagCompound)a3;
            /*SL:307*/if (v2 instanceof String) {
                a4.func_74778_a(v1, (String)v2);
            }
            /*SL:308*/if (v2 instanceof Boolean) {
                a4.func_74757_a(v1, (boolean)v2);
            }
            /*SL:309*/if (v2 instanceof Integer) {
                a4.func_74768_a(v1, (int)v2);
            }
            /*SL:310*/if (v2 instanceof Float) {
                a4.func_74776_a(v1, (float)v2);
            }
            /*SL:311*/if (v2 instanceof Double) {
                a4.func_74780_a(v1, (double)v2);
            }
            /*SL:312*/if (v2 instanceof Short) {
                a4.func_74777_a(v1, (short)v2);
            }
            /*SL:313*/if (v2 instanceof Byte) {
                a4.func_74774_a(v1, (byte)v2);
            }
            /*SL:314*/if (v2 instanceof int[]) {
                a4.func_74783_a(v1, (int[])v2);
            }
            /*SL:315*/if (v2 instanceof byte[]) {
                a4.func_74773_a(v1, (byte[])v2);
            }
            /*SL:316*/if (v2 instanceof NBTTagCompound) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
            /*SL:317*/if (v2 instanceof NBTTagList) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
        }
        /*SL:319*/if (a3 instanceof NBTTagList) {
            final NBTTagList a5 = /*EL:321*/(NBTTagList)a3;
            /*SL:322*/if (v2 instanceof String) {
                a5.func_74742_a((NBTBase)new NBTTagString((String)v2));
            }
            /*SL:323*/if (v2 instanceof Integer) {
                a5.func_74742_a((NBTBase)new NBTTagInt((int)v2));
            }
            /*SL:324*/if (v2 instanceof Float) {
                a5.func_74742_a((NBTBase)new NBTTagFloat((float)v2));
            }
            /*SL:325*/if (v2 instanceof Double) {
                a5.func_74742_a((NBTBase)new NBTTagDouble((double)v2));
            }
            /*SL:326*/if (v2 instanceof Short) {
                a5.func_74742_a((NBTBase)new NBTTagShort((short)v2));
            }
            /*SL:327*/if (v2 instanceof Byte) {
                a5.func_74742_a((NBTBase)new NBTTagByte((byte)v2));
            }
            /*SL:328*/if (v2 instanceof int[]) {
                a5.func_74742_a((NBTBase)new NBTTagIntArray((int[])v2));
            }
            /*SL:329*/if (v2 instanceof byte[]) {
                a5.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v2));
            }
            /*SL:330*/if (v2 instanceof NBTTagCompound) {
                a5.func_74742_a((NBTBase)v2);
            }
            /*SL:331*/if (v2 instanceof NBTTagList) {
                a5.func_74742_a((NBTBase)v2);
            }
        }
    }
    
    public static Item getItem(final String v-3, final DropProcessData v-2) {
        Item func_111206_d = null;
        try {
            final int a1 = getInteger(/*EL:340*/v-3, v-2);
            final Item a2 = /*EL:341*/Item.func_150899_d(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:345*/v-3, v-2);
            /*SL:346*/func_111206_d = Item.func_111206_d(v-3);
        }
        /*SL:348*/return func_111206_d;
    }
    
    public static Block getBlock(final String v-3, final DropProcessData v-2) {
        Block func_149684_b = null;
        try {
            final int a1 = getInteger(/*EL:356*/v-3, v-2);
            final Block a2 = /*EL:357*/Block.func_149729_e(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:361*/v-3, v-2);
            /*SL:362*/func_149684_b = Block.func_149684_b(v-3);
        }
        /*SL:364*/return func_149684_b;
    }
    
    public static ItemStack getItemStack(final String v-1, final DropProcessData v0) {
        try {
            final String[] v = /*EL:371*/v-1.split(":");
            final Item v2 = getItem(/*EL:372*/v[0], null);
            /*SL:374*/if (v.length == 1 && v2 != null) {
                return new ItemStack(v2);
            }
            /*SL:375*/if (v.length == 2) {
                final Item a1 = getItem(/*EL:377*/v[0] + v[1], v0);
                /*SL:378*/if (a1 == null && v2 != null) {
                    return new ItemStack(v2, 1, (int)getInteger(v[1]));
                }
                /*SL:379*/return new ItemStack(a1);
            }
            else/*SL:381*/ if (v.length >= 3) {
                final Item a2 = getItem(/*EL:383*/v[0] + v[1], v0);
                /*SL:384*/if (a2 != null) {
                    return new ItemStack(a2, 1, (int)getInteger(v[3]));
                }
            }
        }
        catch (Exception v3) {
            /*SL:389*/return null;
        }
        /*SL:391*/return null;
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
