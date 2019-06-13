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
        /*SL:30*/if (a2 == String.class) {
            return getString(a1, a3);
        }
        /*SL:31*/if (a2 == Integer.class) {
            return getInteger(a1, a3);
        }
        /*SL:32*/if (a2 == Boolean.class) {
            return getBoolean(a1, a3);
        }
        /*SL:33*/if (a2 == Float.class) {
            return getFloat(a1, a3);
        }
        /*SL:34*/if (a2 == Double.class) {
            return getDouble(a1, a3);
        }
        /*SL:35*/if (a2 == Short.class) {
            return getShort(a1, a3);
        }
        /*SL:36*/if (a2 == Byte.class) {
            return getByte(a1, a3);
        }
        /*SL:37*/if (a2 == NBTTagCompound.class) {
            return getNBTTag(a1, a3);
        }
        /*SL:38*/return null;
    }
    
    public static NBTBase getNBTBaseFromValue(final Object a1) {
        /*SL:43*/if (a1.getClass() == String.class) {
            return (NBTBase)new NBTTagString((String)a1);
        }
        /*SL:44*/if (a1.getClass() == Integer.class) {
            return (NBTBase)new NBTTagInt((int)a1);
        }
        /*SL:45*/if (a1.getClass() == Boolean.class) {
            return (NBTBase)new NBTTagByte((byte)(((boolean)a1) ? 1 : 0));
        }
        /*SL:46*/if (a1.getClass() == Float.class) {
            return (NBTBase)new NBTTagFloat((float)a1);
        }
        /*SL:47*/if (a1.getClass() == NBTTagCompound.class) {
            return (NBTBase)a1;
        }
        /*SL:48*/return null;
    }
    
    public static Object getValueFromNBTBase(final NBTBase a1) {
        /*SL:53*/if (a1.getClass() == NBTTagString.class) {
            return ((NBTTagString)a1).func_150285_a_();
        }
        /*SL:54*/if (a1.getClass() == NBTTagInt.class) {
            return ((NBTTagInt)a1).func_150287_d();
        }
        /*SL:55*/if (a1.getClass() == NBTTagByte.class) {
            return ((NBTTagByte)a1).func_150290_f() == 1;
        }
        /*SL:56*/if (a1.getClass() == NBTTagFloat.class) {
            return ((NBTTagFloat)a1).func_150288_h();
        }
        /*SL:57*/if (a1.getClass() == NBTTagCompound.class) {
            return a1;
        }
        /*SL:58*/return null;
    }
    
    public static String getString(final String a1) throws NumberFormatException {
        /*SL:63*/return getString(a1, null);
    }
    
    public static String getString(String a1, final DropProcessData a2) {
        /*SL:68*/a1 = HashVariables.processString(a1, a2);
        /*SL:69*/a1 = StringEscapeUtils.unescapeJava(a1);
        /*SL:70*/if (a1.startsWith("\"") && a1.endsWith("\"")) {
            a1 = a1.substring(1, a1.length() - 1);
        }
        /*SL:71*/return a1;
    }
    
    public static Double calculateNum(String v-7) throws NumberFormatException {
        /*SL:76*/v-7 = DropStringUtils.removeNumSuffix(v-7);
        /*SL:77*/v-7 = v-7.trim();
        double doubleValue = /*EL:78*/0.0;
        String substring = /*EL:80*/"";
        /*SL:81*/if (v-7.startsWith("-") || v-7.startsWith("+")) {
            /*SL:83*/substring = v-7.substring(0, 1);
            /*SL:84*/v-7 = v-7.substring(1, v-7.length());
        }
        final char v-8 = /*EL:87*/v-7.contains("+") ? '+' : (v-7.contains("-") ? '-' : (v-7.contains("*") ? '*' : (v-7.contains("/") ? '/' : '\0')));
        /*SL:88*/if (v-8 != '\0') {
            final String[] splitBracketString = /*EL:90*/DropStringUtils.splitBracketString(v-7, v-8);
            /*SL:91*/if (splitBracketString.length >= 2) {
                final double a1 = /*EL:93*/Double.valueOf(substring + splitBracketString[0]);
                final double v1 = /*EL:94*/Double.valueOf(splitBracketString[1]);
                /*SL:96*/switch (v-8) {
                    case '+': {
                        /*SL:99*/doubleValue = a1 + v1;
                        /*SL:100*/break;
                    }
                    case '-': {
                        /*SL:102*/doubleValue = a1 - v1;
                        /*SL:103*/break;
                    }
                    case '*': {
                        /*SL:105*/doubleValue = a1 * v1;
                        /*SL:106*/break;
                    }
                    case '/': {
                        /*SL:108*/doubleValue = a1 / v1;
                        break;
                    }
                }
            }
        }
        else {
            /*SL:113*/doubleValue = Double.valueOf(substring + v-7);
        }
        /*SL:115*/return doubleValue;
    }
    
    public static Integer getInteger(final String a1) throws NumberFormatException {
        /*SL:120*/return getInteger(a1, null);
    }
    
    public static Integer getInteger(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:125*/a1 = HashVariables.processString(a1, a2);
        /*SL:126*/return (int)(Object)calculateNum(a1);
    }
    
    public static Double getDouble(final String a1) throws NumberFormatException {
        /*SL:131*/return getDouble(a1, null);
    }
    
    public static Double getDouble(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:136*/a1 = HashVariables.processString(a1, a2);
        /*SL:137*/return calculateNum(a1);
    }
    
    public static Float getFloat(final String a1) throws NumberFormatException {
        /*SL:142*/return getFloat(a1, null);
    }
    
    public static Float getFloat(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:147*/a1 = HashVariables.processString(a1, a2);
        /*SL:148*/return (float)(Object)calculateNum(a1);
    }
    
    public static Boolean getBoolean(final String a1) throws NumberFormatException {
        /*SL:153*/return getBoolean(a1, null);
    }
    
    public static Boolean getBoolean(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:158*/a1 = HashVariables.processString(a1, a2);
        /*SL:159*/if (!a1.equals("true") && !a1.equals("false")) {
            throw new NumberFormatException("Lucky Block: Unknown boolean format: " + a1);
        }
        /*SL:160*/return Boolean.valueOf(a1);
    }
    
    public static Short getShort(final String a1) {
        /*SL:165*/return getShort(a1, null);
    }
    
    public static Short getShort(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:170*/a1 = HashVariables.processString(a1, a2);
        /*SL:171*/return (short)(Object)calculateNum(a1);
    }
    
    public static Byte getByte(final String a1) throws NumberFormatException {
        /*SL:176*/return getByte(a1, null);
    }
    
    public static Byte getByte(String a1, final DropProcessData a2) throws NumberFormatException {
        /*SL:181*/a1 = HashVariables.processString(a1, a2);
        /*SL:182*/return (byte)(Object)calculateNum(a1);
    }
    
    public static NBTTagCompound getNBTTag(final String a1) throws Exception {
        /*SL:187*/return getNBTTag(a1, null);
    }
    
    public static NBTTagCompound getNBTTag(final String a1, final DropProcessData a2) throws Exception {
        final Object v1 = getNBTTagValue(/*EL:192*/a1, a2);
        /*SL:193*/if (v1 instanceof NBTTagCompound) {
            return (NBTTagCompound)v1;
        }
        /*SL:194*/throw new Exception();
    }
    
    public static Object getNBTTagValue(final String a1, final DropProcessData a2) throws Exception {
        /*SL:199*/return getNBTTagValue(a1, a2, null, null);
    }
    
    public static Object getNBTTagValue(String v-4, final DropProcessData v-3, final NBTBase v-2, final String v-1) throws Exception {
        /*SL:206*/if (v-4.startsWith("(") && v-4.endsWith(")")) {
            String[] a4 = /*EL:208*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagCompound v1 = /*EL:209*/new NBTTagCompound();
            /*SL:210*/for (final String a2 : a4) {
                /*SL:212*/if (a2 != null) {
                    if (!a2.equals("")) {
                        final String a3 = /*EL:213*/a2.substring(0, a2.indexOf("="));
                        /*SL:214*/a4 = a2.substring(a2.indexOf("=") + 1);
                        setNBTTagValue(/*EL:215*/(NBTBase)v1, a3, getNBTTagValue(a4, v-3, (NBTBase)v1, a3));
                    }
                }
            }
            /*SL:217*/return v1;
        }
        /*SL:219*/if (v-4.startsWith("[") && v-4.endsWith("]")) {
            final String[] v2 = /*EL:221*/DropStringUtils.splitBracketString(v-4.substring(1, v-4.length() - 1), ',');
            final NBTTagList v3 = /*EL:222*/new NBTTagList();
            /*SL:223*/for (final String v4 : v2) {
                /*SL:225*/if (v4 != null) {
                    if (!v4.equals("")) {
                        setNBTTagValue(/*EL:226*/(NBTBase)v3, null, getNBTTagValue(v4, v-3, (NBTBase)v3, v-1));
                    }
                }
            }
            /*SL:228*/return v3;
        }
        /*SL:231*/if (v-4.startsWith("\"") && v-4.endsWith("\"")) {
            /*SL:233*/if (v-2 != null && v-2 instanceof NBTTagList && v-1 != null && ValueParser.DROP_TAGS.contains(v-1.toLowerCase())) {
                /*SL:236*/v-4 = HashVariables.autoCancelHash(v-4);
            }
            /*SL:238*/return getString(v-4, v-3);
        }
        /*SL:240*/if (v-4.startsWith("#")) {
            final NBTBase v5 = /*EL:242*/CustomNBTTags.getNBTTagFromString(v-4, v-3);
            /*SL:243*/if (v5 != null) {
                return v5;
            }
        }
        /*SL:246*/v-4 = getString(v-4, v-3);
        try {
            /*SL:249*/if (v-4.endsWith("f") || v-4.endsWith("F")) {
                return getFloat(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:250*/if (v-4.endsWith("d") || v-4.endsWith("D") || DropStringUtils.hasDecimalPoint(v-4)) {
                return getDouble(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:251*/if (v-4.endsWith("s") || v-4.endsWith("S")) {
                return getShort(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:252*/if (v-4.endsWith("b") || v-4.endsWith("B")) {
                return getByte(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:253*/if (v-4.equals("true") || v-4.equals("false")) {
                return getBoolean(DropStringUtils.removeNumSuffix(v-4), v-3);
            }
            /*SL:254*/return getInteger(DropStringUtils.removeNumSuffix(v-4), v-3);
        }
        catch (Exception ex) {
            try {
                final String[] v2 = /*EL:261*/v-4.split(":");
                /*SL:262*/if (v2.length >= 1) {
                    int v6 = /*EL:264*/0;
                    /*SL:265*/if (v2[0].endsWith("b") || v2[0].endsWith("B")) {
                        v6 = 1;
                    }
                    final int[] v7 = /*EL:267*/new int[v2.length];
                    final byte[] v8 = /*EL:268*/new byte[v2.length];
                    /*SL:269*/for (int v9 = 0; v9 < v2.length; ++v9) {
                        /*SL:271*/if (v6 == 0) {
                            v7[v9] = getInteger(v2[v9], v-3);
                        }
                        else {
                            /*SL:274*/v2[v9] = DropStringUtils.removeNumSuffix(v2[v9]);
                            /*SL:275*/v8[v9] = getByte(v2[v9], v-3);
                        }
                    }
                    /*SL:279*/if (v6 == 0 && v7.length > 0) {
                        return v7;
                    }
                    /*SL:280*/if (v6 == 1 && v8.length > 0) {
                        return v8;
                    }
                }
            }
            catch (Exception ex2) {}
            /*SL:287*/return v-4;
        }
    }
    
    public static void setNBTTagValue(final NBTBase a3, final String v1, final Object v2) {
        /*SL:292*/if (a3 instanceof NBTTagCompound) {
            final NBTTagCompound a4 = /*EL:294*/(NBTTagCompound)a3;
            /*SL:295*/if (v2 instanceof String) {
                a4.func_74778_a(v1, (String)v2);
            }
            /*SL:296*/if (v2 instanceof Boolean) {
                a4.func_74757_a(v1, (boolean)v2);
            }
            /*SL:297*/if (v2 instanceof Integer) {
                a4.func_74768_a(v1, (int)v2);
            }
            /*SL:298*/if (v2 instanceof Float) {
                a4.func_74776_a(v1, (float)v2);
            }
            /*SL:299*/if (v2 instanceof Double) {
                a4.func_74780_a(v1, (double)v2);
            }
            /*SL:300*/if (v2 instanceof Short) {
                a4.func_74777_a(v1, (short)v2);
            }
            /*SL:301*/if (v2 instanceof Byte) {
                a4.func_74774_a(v1, (byte)v2);
            }
            /*SL:302*/if (v2 instanceof int[]) {
                a4.func_74783_a(v1, (int[])v2);
            }
            /*SL:303*/if (v2 instanceof byte[]) {
                a4.func_74773_a(v1, (byte[])v2);
            }
            /*SL:304*/if (v2 instanceof NBTTagCompound) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
            /*SL:305*/if (v2 instanceof NBTTagList) {
                a4.func_74782_a(v1, (NBTBase)v2);
            }
        }
        /*SL:307*/if (a3 instanceof NBTTagList) {
            final NBTTagList a5 = /*EL:309*/(NBTTagList)a3;
            /*SL:310*/if (v2 instanceof String) {
                a5.func_74742_a((NBTBase)new NBTTagString((String)v2));
            }
            /*SL:311*/if (v2 instanceof Integer) {
                a5.func_74742_a((NBTBase)new NBTTagInt((int)v2));
            }
            /*SL:312*/if (v2 instanceof Float) {
                a5.func_74742_a((NBTBase)new NBTTagFloat((float)v2));
            }
            /*SL:313*/if (v2 instanceof Double) {
                a5.func_74742_a((NBTBase)new NBTTagDouble((double)v2));
            }
            /*SL:314*/if (v2 instanceof Short) {
                a5.func_74742_a((NBTBase)new NBTTagShort((short)v2));
            }
            /*SL:315*/if (v2 instanceof Byte) {
                a5.func_74742_a((NBTBase)new NBTTagByte((byte)v2));
            }
            /*SL:316*/if (v2 instanceof int[]) {
                a5.func_74742_a((NBTBase)new NBTTagIntArray((int[])v2));
            }
            /*SL:317*/if (v2 instanceof byte[]) {
                a5.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v2));
            }
            /*SL:318*/if (v2 instanceof NBTTagCompound) {
                a5.func_74742_a((NBTBase)v2);
            }
            /*SL:319*/if (v2 instanceof NBTTagList) {
                a5.func_74742_a((NBTBase)v2);
            }
        }
    }
    
    public static Item getItem(final String v-3, final DropProcessData v-2) {
        Item func_111206_d = null;
        try {
            final int a1 = getInteger(/*EL:328*/v-3, v-2);
            final Item a2 = /*EL:329*/Item.func_150899_d(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:333*/v-3, v-2);
            /*SL:334*/func_111206_d = Item.func_111206_d(v-3);
        }
        /*SL:336*/return func_111206_d;
    }
    
    public static Block getBlock(final String v-3, final DropProcessData v-2) {
        Block func_149684_b = null;
        try {
            final int a1 = getInteger(/*EL:344*/v-3, v-2);
            final Block a2 = /*EL:345*/Block.func_149729_e(a1);
        }
        catch (Exception v2) {
            final String v1 = getString(/*EL:349*/v-3, v-2);
            /*SL:350*/func_149684_b = Block.func_149684_b(v-3);
        }
        /*SL:352*/return func_149684_b;
    }
    
    public static ItemStack getItemStack(final String v-1, final DropProcessData v0) {
        try {
            final String[] v = /*EL:359*/v-1.split(":");
            final Item v2 = getItem(/*EL:360*/v[0], null);
            /*SL:362*/if (v.length == 1 && v2 != null) {
                return new ItemStack(v2);
            }
            /*SL:363*/if (v.length == 2) {
                final Item a1 = getItem(/*EL:365*/v[0] + v[1], v0);
                /*SL:366*/if (a1 == null && v2 != null) {
                    return new ItemStack(v2, 1, (int)getInteger(v[1]));
                }
                /*SL:367*/return new ItemStack(a1);
            }
            else/*SL:369*/ if (v.length >= 3) {
                final Item a2 = getItem(/*EL:371*/v[0] + v[1], v0);
                /*SL:372*/if (a2 != null) {
                    return new ItemStack(a2, 1, (int)getInteger(v[3]));
                }
            }
        }
        catch (Exception v3) {
            /*SL:377*/return null;
        }
        /*SL:379*/return null;
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
