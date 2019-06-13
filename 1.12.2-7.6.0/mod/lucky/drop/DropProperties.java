package mod.lucky.drop;

import net.minecraft.nbt.NBTBase;
import java.util.Locale;
import mod.lucky.drop.value.DropStringUtils;
import java.util.Iterator;
import java.util.Map;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.block.Block;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.value.DropValue;
import java.util.HashMap;

public class DropProperties extends DropBase
{
    private static String[] multiPosProperties;
    private HashMap<String, DropValue> properties;
    private boolean needsInitialize;
    private static HashMap<String, HashMap<String, Class>> defaultValueTypes;
    private static HashMap<String, HashMap<String, Object>> defaultValues;
    private static HashMap<String, String> replaceProperties;
    
    public DropProperties() {
        this.needsInitialize = false;
        this.properties = new HashMap<String, DropValue>();
    }
    
    public HashMap<String, DropValue> getProperties() {
        /*SL:28*/return this.properties;
    }
    
    public DropValue getRawProperty(String a1) {
        /*SL:32*/a1 = processName(a1);
        /*SL:33*/return this.properties.get(a1);
    }
    
    public Object getProperty(String a1) {
        /*SL:37*/a1 = processName(a1);
        final DropValue v1 = /*EL:38*/this.properties.get(a1);
        /*SL:39*/if (v1 == null) {
            /*SL:40*/return this.getDefaultPropertyValue(a1);
        }
        /*SL:41*/return v1.getValue();
    }
    
    public Integer getPropertyInt(final String a1) {
        /*SL:45*/if (this.getProperty(a1) instanceof Float) {
            return (int)this.getProperty(a1);
        }
        /*SL:46*/return (Integer)this.getProperty(a1);
    }
    
    public String getPropertyString(final String a1) {
        /*SL:50*/return (String)this.getProperty(a1);
    }
    
    public Boolean getPropertyBoolean(final String a1) {
        /*SL:54*/return (Boolean)this.getProperty(a1);
    }
    
    public Float getPropertyFloat(final String a1) {
        /*SL:58*/if (this.getProperty(a1) instanceof Integer) {
            /*SL:59*/return (float)this.getProperty(a1);
        }
        /*SL:60*/return (Float)this.getProperty(a1);
    }
    
    public NBTTagCompound getPropertyNBT(final String a1) {
        /*SL:64*/return (NBTTagCompound)this.getProperty(a1);
    }
    
    public BlockPos getBlockPos() {
        /*SL:68*/return new BlockPos(this.getVecPos());
    }
    
    public Vec3d getVecPos() {
        /*SL:72*/return /*EL:75*/new Vec3d((double)this.getPropertyFloat("posX"), (double)this.getPropertyFloat("posY"), (double)this.getPropertyFloat("posZ"));
    }
    
    public void setBlockPos(final BlockPos a1) {
        /*SL:79*/this.setProperty("posX", a1.func_177958_n());
        /*SL:80*/this.setProperty("posY", a1.func_177956_o());
        /*SL:81*/this.setProperty("posZ", a1.func_177952_p());
    }
    
    public void setVecPos(final Vec3d a1) {
        /*SL:85*/this.setProperty("posX", a1.field_72450_a);
        /*SL:86*/this.setProperty("posY", a1.field_72448_b);
        /*SL:87*/this.setProperty("posZ", a1.field_72449_c);
    }
    
    public IBlockState getBlockState() {
        Block v1;
        try {
            /*SL:93*/v1 = Block.func_149729_e((int)ValueParser.getInteger(this.getPropertyString("ID")));
        }
        catch (Exception v2) {
            /*SL:95*/v1 = Block.func_149684_b(this.getPropertyString("ID"));
        }
        /*SL:97*/return v1.func_176203_a((int)this.getPropertyInt("damage"));
    }
    
    public void setProperty(String a1, final Object a2) {
        /*SL:101*/a1 = processName(a1);
        /*SL:102*/this.properties.put(a1, new DropValue(a2));
    }
    
    public void setOverrideProperty(final String a1, final Object a2) {
        /*SL:106*/if (!this.hasProperty(a1)) {
            this.setProperty(a1, a2);
        }
    }
    
    public DropValue setRawProperty(String v2, final String v3) {
        /*SL:110*/v2 = processName(v2);
        try {
            final DropValue a1 = /*EL:112*/new DropValue(v3, this.getDefaultPropertyType(v2));
            /*SL:113*/if (a1.needsInitialize()) {
                this.needsInitialize = true;
            }
            /*SL:114*/this.properties.put(v2, a1);
            /*SL:115*/return a1;
        }
        catch (Exception a2) {
            System.err.println(/*EL:117*/"Lucky Block: Error loading property: " + v2 + "=" + v3);
            /*SL:118*/a2.printStackTrace();
            /*SL:120*/return null;
        }
    }
    
    public void setOverrideRawProperty(final String a1, final String a2) {
        /*SL:124*/if (!this.hasProperty(a1)) {
            this.setRawProperty(a1, a2);
        }
    }
    
    public boolean hasProperty(String a1) {
        /*SL:128*/a1 = processName(a1);
        /*SL:129*/return this.properties.get(a1) != null;
    }
    
    public Object defaultCast(String a1, final Object a2) {
        /*SL:133*/a1 = processName(a1);
        final Class v1 = /*EL:134*/this.getDefaultPropertyType(a1);
        /*SL:135*/if (a2 instanceof Integer && v1 == Float.class) {
            /*SL:136*/return a2;
        }
        /*SL:137*/if (a2 instanceof Float && v1 == Integer.class) {
            /*SL:138*/return (int)(float)a2;
        }
        /*SL:139*/return v1.cast(a2);
    }
    
    public boolean needsInitialize() {
        /*SL:143*/return this.needsInitialize;
    }
    
    @Override
    public DropProperties initialize(final DropProcessData v-4) {
        final DropProperties copy = /*EL:148*/this.copy();
        /*SL:150*/if (!copy.hasProperty("pos")) {
            /*SL:151*/copy.setOverrideProperty("posX", copy.defaultCast("posX", (float)v-4.getHarvestPos().field_72450_a));
            /*SL:153*/copy.setOverrideProperty("posY", copy.defaultCast("posY", (float)v-4.getHarvestPos().field_72448_b));
            /*SL:155*/copy.setOverrideProperty("posZ", copy.defaultCast("posZ", (float)v-4.getHarvestPos().field_72449_c));
        }
        /*SL:159*/for (final String v1 : DropProperties.multiPosProperties) {
            /*SL:160*/if (copy.hasProperty(v1)) {
                /*SL:161*/copy.getRawProperty(v1).initialize(v-4);
                final String[] a1 = /*EL:162*/copy.getPropertyString(v1).substring(/*EL:164*/1, copy.getPropertyString(v1).length() - /*EL:165*/1).split(",");
                /*SL:167*/copy.setOverrideRawProperty(v1 + "X", a1[0]);
                /*SL:168*/copy.setOverrideRawProperty(v1 + "Y", a1[1]);
                /*SL:169*/copy.setOverrideRawProperty(v1 + "Z", a1[2]);
            }
        }
        /*SL:172*/if (copy.hasProperty("size")) {
            /*SL:173*/copy.getRawProperty("size").initialize(v-4);
            final String[] split = /*EL:174*/copy.getPropertyString("size").substring(/*EL:176*/1, copy.getPropertyString("size").length() - /*EL:177*/1).split(",");
            /*SL:179*/copy.setOverrideRawProperty("length", split[0]);
            /*SL:180*/copy.setOverrideRawProperty("height", split[1]);
            /*SL:181*/copy.setOverrideRawProperty("width", split[2]);
        }
        /*SL:184*/if (copy.needsInitialize) {
            final Iterator iterator = /*EL:185*/copy.properties.entrySet().iterator();
            /*SL:186*/while (iterator.hasNext()) {
                /*SL:187*/iterator.next().getValue().initialize(v-4);
            }
        }
        /*SL:191*/if (copy.hasProperty("posOffsetX")) {
            /*SL:192*/copy.getRawProperty("posX").setValue(/*EL:193*/copy.defaultCast("posX", copy.getPropertyFloat("posX") + /*EL:197*/copy.getPropertyFloat("posOffsetX")));
        }
        /*SL:198*/if (copy.hasProperty("posOffsetY")) {
            /*SL:199*/copy.getRawProperty("posY").setValue(/*EL:200*/copy.defaultCast("posY", copy.getPropertyFloat("posY") + /*EL:204*/copy.getPropertyFloat("posOffsetY")));
        }
        /*SL:205*/if (copy.hasProperty("posOffsetZ")) {
            /*SL:206*/copy.getRawProperty("posZ").setValue(/*EL:207*/copy.defaultCast("posZ", copy.getPropertyFloat("posZ") + /*EL:211*/copy.getPropertyFloat("posOffsetZ")));
        }
        /*SL:213*/for (final String v1 : DropProperties.multiPosProperties) {
            /*SL:214*/if (!copy.hasProperty(v1) && (copy.hasProperty(v1 + "X") || /*EL:215*/copy.hasProperty(v1 + "Y") || /*EL:216*/copy.hasProperty(v1 + "Z"))) {
                /*SL:218*/copy.setProperty(v1, "(" + copy.getProperty(v1 + "X").toString() + /*EL:221*/"," + copy.getProperty(v1 + "Y").toString() + /*EL:223*/"," + copy.getProperty(v1 + "Z").toString() + /*EL:225*/")");
            }
        }
        /*SL:230*/return copy;
    }
    
    @Override
    public void readFromString(final String v-7) {
        final String[] splitBracketString = /*EL:235*/DropStringUtils.splitBracketString(v-7, ',');
        /*SL:236*/for (int i = 0; i < 2; ++i) {
            /*SL:237*/for (final String s : splitBracketString) {
                final String a1 = /*EL:238*/s.substring(0, s.indexOf("=")).trim().toLowerCase(Locale.ENGLISH);
                final String v1 = /*EL:240*/s.substring(s.indexOf("=") + 1).trim();
                /*SL:242*/if (i == 0 && a1.toLowerCase(Locale.ENGLISH).equals("type")) {
                    /*SL:243*/this.setRawProperty(a1, v1);
                }
                else/*SL:244*/ if (i == 1 && !a1.toLowerCase(Locale.ENGLISH).equals("type")) {
                    /*SL:245*/this.setRawProperty(a1, v1);
                }
            }
        }
        /*SL:249*/if (this.needsInitialize && !this.hasProperty("reinitialize")) {
            /*SL:250*/this.setProperty("reinitialize", true);
        }
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound v-2) {
        final NBTTagCompound func_74775_l = /*EL:255*/v-2.func_74775_l("properties");
        /*SL:256*/for (final Object v1 : func_74775_l.func_150296_c()) {
            final DropValue a1 = /*EL:257*/new DropValue(null);
            /*SL:258*/a1.readFromNBT((NBTTagCompound)func_74775_l.func_74781_a((String)v1));
            /*SL:259*/this.properties.put((String)v1, a1);
        }
        /*SL:262*/this.needsInitialize = v-2.func_74767_n("needsInitialize");
    }
    
    @Override
    public String writeToString() {
        /*SL:267*/return null;
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound nbtTagCompound = /*EL:272*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound2 = /*EL:274*/new NBTTagCompound();
        /*SL:275*/for (final String v1 : this.properties.keySet()) {
            /*SL:276*/nbtTagCompound2.func_74782_a(v1, (NBTBase)this.properties.get(v1).writeToNBT());
        }
        /*SL:278*/nbtTagCompound.func_74782_a("properties", (NBTBase)nbtTagCompound2);
        /*SL:279*/nbtTagCompound.func_74757_a("needsInitialize", this.needsInitialize);
        /*SL:281*/return nbtTagCompound;
    }
    
    private Class getDefaultPropertyType(String v2) {
        /*SL:285*/v2 = processName(v2);
        /*SL:286*/if (this.hasProperty("type")) {
            final String a1 = /*EL:287*/this.getPropertyString("type");
            /*SL:288*/if (DropProperties.defaultValueTypes.get(a1) != null && DropProperties.defaultValueTypes.get(a1).containsKey(v2)) {
                /*SL:289*/return DropProperties.defaultValueTypes.get(a1).get(v2);
            }
        }
        /*SL:291*/return DropProperties.defaultValueTypes.get("all").get(v2);
    }
    
    private Object getDefaultPropertyValue(String v2) {
        /*SL:295*/v2 = processName(v2);
        /*SL:296*/if (this.hasProperty("type")) {
            final String a1 = /*EL:297*/this.getPropertyString("type");
            /*SL:298*/if (DropProperties.defaultValues.get(a1) != null && DropProperties.defaultValues.get(a1).containsKey(v2)) {
                /*SL:299*/return DropProperties.defaultValues.get(a1).get(v2);
            }
        }
        /*SL:301*/return DropProperties.defaultValues.get("all").get(v2);
    }
    
    public DropProperties copy() {
        final DropProperties dropProperties = /*EL:305*/new DropProperties();
        /*SL:306*/for (final String v1 : this.properties.keySet()) {
            /*SL:307*/dropProperties.properties.put(v1, this.properties.get(v1).copy());
        }
        /*SL:309*/dropProperties.needsInitialize = this.needsInitialize;
        /*SL:310*/return dropProperties;
    }
    
    public static void setDefaultProperty(final String a1, String a2, final Class a3, final Object a4) {
        /*SL:321*/a2 = processName(a2);
        /*SL:322*/if (DropProperties.defaultValueTypes.get(a1) == null) {
            DropProperties.defaultValueTypes.put(/*EL:323*/a1, new HashMap<String, Class>());
        }
        DropProperties.defaultValueTypes.get(/*EL:324*/a1).put(a2, a3);
        /*SL:326*/if (DropProperties.defaultValues.get(a1) == null) {
            DropProperties.defaultValues.put(a1, new HashMap<String, Object>());
        }
        DropProperties.defaultValues.get(/*EL:327*/a1).put(a2, a4);
        /*SL:329*/if (!a1.equals("all")) {
            setDefaultProperty("all", a2, a3, a4);
        }
    }
    
    public static void setReplaceProperty(final String a1, final String a2) {
        DropProperties.replaceProperties.put(/*EL:333*/a1.toLowerCase(Locale.ENGLISH), /*EL:334*/a2.toLowerCase(Locale.ENGLISH));
    }
    
    private static String processName(String a1) {
        /*SL:338*/a1 = a1.toLowerCase(Locale.ENGLISH);
        /*SL:339*/if (DropProperties.replaceProperties.get(a1) != null) {
            a1 = DropProperties.replaceProperties.get(a1);
        }
        /*SL:340*/return a1;
    }
    
    @Override
    public String toString() {
        String string = /*EL:345*/"";
        /*SL:346*/for (final String v1 : this.properties.keySet()) {
            /*SL:347*/string = string + v1 + "=" + this.properties.get(v1).toString() + ",";
        }
        /*SL:348*/return string.substring(0, string.length() - 1);
    }
    
    static {
        DropProperties.multiPosProperties = new String[] { "pos", "pos2", "posOffset" };
        DropProperties.defaultValueTypes = new HashMap<String, HashMap<String, Class>>();
        DropProperties.defaultValues = new HashMap<String, HashMap<String, Object>>();
        DropProperties.replaceProperties = new HashMap<String, String>();
    }
}
