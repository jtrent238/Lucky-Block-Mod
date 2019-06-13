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
        /*SL:32*/return this.properties;
    }
    
    public DropValue getRawProperty(String a1) {
        /*SL:37*/a1 = processName(a1);
        /*SL:38*/return this.properties.get(a1);
    }
    
    public Object getProperty(String a1) {
        /*SL:43*/a1 = processName(a1);
        final DropValue v1 = /*EL:44*/this.properties.get(a1);
        /*SL:45*/if (v1 == null) {
            /*SL:47*/return this.getDefaultPropertyValue(a1);
        }
        /*SL:49*/return v1.getValue();
    }
    
    public Integer getPropertyInt(final String a1) {
        /*SL:54*/if (this.getProperty(a1) instanceof Float) {
            return (int)this.getProperty(a1);
        }
        /*SL:55*/return (Integer)this.getProperty(a1);
    }
    
    public String getPropertyString(final String a1) {
        /*SL:60*/return (String)this.getProperty(a1);
    }
    
    public Boolean getPropertyBoolean(final String a1) {
        /*SL:65*/return (Boolean)this.getProperty(a1);
    }
    
    public Float getPropertyFloat(final String a1) {
        /*SL:70*/if (this.getProperty(a1) instanceof Integer) {
            return (float)this.getProperty(a1);
        }
        /*SL:71*/return (Float)this.getProperty(a1);
    }
    
    public NBTTagCompound getPropertyNBT(final String a1) {
        /*SL:76*/return (NBTTagCompound)this.getProperty(a1);
    }
    
    public BlockPos getBlockPos() {
        /*SL:81*/return new BlockPos(this.getVecPos());
    }
    
    public Vec3d getVecPos() {
        /*SL:86*/return new Vec3d((double)this.getPropertyFloat("posX"), (double)this.getPropertyFloat("posY"), (double)this.getPropertyFloat("posZ"));
    }
    
    public void setBlockPos(final BlockPos a1) {
        /*SL:91*/this.setProperty("posX", a1.func_177958_n());
        /*SL:92*/this.setProperty("posY", a1.func_177956_o());
        /*SL:93*/this.setProperty("posZ", a1.func_177952_p());
    }
    
    public void setVecPos(final Vec3d a1) {
        /*SL:98*/this.setProperty("posX", a1.field_72450_a);
        /*SL:99*/this.setProperty("posY", a1.field_72448_b);
        /*SL:100*/this.setProperty("posZ", a1.field_72449_c);
    }
    
    public IBlockState getBlockState() {
        Block v1;
        try {
            /*SL:108*/v1 = Block.func_149729_e((int)ValueParser.getInteger(this.getPropertyString("ID")));
        }
        catch (Exception v2) {
            /*SL:112*/v1 = Block.func_149684_b(this.getPropertyString("ID"));
        }
        /*SL:114*/return v1.func_176203_a((int)this.getPropertyInt("damage"));
    }
    
    public void setProperty(String a1, final Object a2) {
        /*SL:119*/a1 = processName(a1);
        /*SL:120*/this.properties.put(a1, new DropValue(a2));
    }
    
    public void setOverrideProperty(final String a1, final Object a2) {
        /*SL:125*/if (!this.hasProperty(a1)) {
            this.setProperty(a1, a2);
        }
    }
    
    public DropValue setRawProperty(String v2, final String v3) {
        /*SL:130*/v2 = processName(v2);
        try {
            final DropValue a1 = /*EL:133*/new DropValue(v3, this.getDefaultPropertyType(v2));
            /*SL:134*/if (a1.needsInitialize()) {
                this.needsInitialize = true;
            }
            /*SL:135*/this.properties.put(v2, a1);
            /*SL:136*/return a1;
        }
        catch (Exception a2) {
            System.err.println(/*EL:140*/"Lucky Block: Error loading property: " + v2 + "=" + v3);
            /*SL:141*/a2.printStackTrace();
            /*SL:143*/return null;
        }
    }
    
    public void setOverrideRawProperty(final String a1, final String a2) {
        /*SL:148*/if (!this.hasProperty(a1)) {
            this.setRawProperty(a1, a2);
        }
    }
    
    public boolean hasProperty(String a1) {
        /*SL:153*/a1 = processName(a1);
        /*SL:154*/return this.properties.get(a1) != null;
    }
    
    public Object defaultCast(String a1, final Object a2) {
        /*SL:159*/a1 = processName(a1);
        final Class v1 = /*EL:160*/this.getDefaultPropertyType(a1);
        /*SL:161*/if (a2 instanceof Integer && v1 == Float.class) {
            return a2;
        }
        /*SL:162*/if (a2 instanceof Float && v1 == Integer.class) {
            return (int)(float)a2;
        }
        /*SL:163*/return v1.cast(a2);
    }
    
    public boolean needsInitialize() {
        /*SL:168*/return this.needsInitialize;
    }
    
    @Override
    public DropProperties initialize(final DropProcessData v-4) {
        final DropProperties copy = /*EL:174*/this.copy();
        /*SL:176*/if (!copy.hasProperty("pos")) {
            /*SL:178*/copy.setOverrideProperty("posX", copy.defaultCast("posX", (float)v-4.getHarvestPos().field_72450_a));
            /*SL:179*/copy.setOverrideProperty("posY", copy.defaultCast("posY", (float)v-4.getHarvestPos().field_72448_b));
            /*SL:180*/copy.setOverrideProperty("posZ", copy.defaultCast("posZ", (float)v-4.getHarvestPos().field_72449_c));
        }
        /*SL:183*/for (final String v1 : DropProperties.multiPosProperties) {
            /*SL:185*/if (copy.hasProperty(v1)) {
                /*SL:187*/copy.getRawProperty(v1).initialize(v-4);
                final String[] a1 = /*EL:188*/copy.getPropertyString(v1).substring(1, copy.getPropertyString(v1).length() - 1).split(",");
                /*SL:189*/copy.setOverrideRawProperty(v1 + "X", a1[0]);
                /*SL:190*/copy.setOverrideRawProperty(v1 + "Y", a1[1]);
                /*SL:191*/copy.setOverrideRawProperty(v1 + "Z", a1[2]);
            }
        }
        /*SL:194*/if (copy.hasProperty("size")) {
            /*SL:196*/copy.getRawProperty("size").initialize(v-4);
            final String[] split = /*EL:197*/copy.getPropertyString("size").substring(1, copy.getPropertyString("size").length() - 1).split(",");
            /*SL:198*/copy.setOverrideRawProperty("length", split[0]);
            /*SL:199*/copy.setOverrideRawProperty("height", split[1]);
            /*SL:200*/copy.setOverrideRawProperty("width", split[2]);
        }
        /*SL:203*/if (copy.needsInitialize) {
            final Iterator iterator = /*EL:205*/copy.properties.entrySet().iterator();
            /*SL:206*/while (iterator.hasNext()) {
                /*SL:208*/iterator.next().getValue().initialize(v-4);
            }
        }
        /*SL:212*/if (copy.hasProperty("posOffsetX")) {
            copy.getRawProperty("posX").setValue(copy.defaultCast("posX", copy.getPropertyFloat("posX") + copy.getPropertyFloat("posOffsetX")));
        }
        /*SL:213*/if (copy.hasProperty("posOffsetY")) {
            copy.getRawProperty("posY").setValue(copy.defaultCast("posY", copy.getPropertyFloat("posY") + copy.getPropertyFloat("posOffsetY")));
        }
        /*SL:214*/if (copy.hasProperty("posOffsetZ")) {
            copy.getRawProperty("posZ").setValue(copy.defaultCast("posZ", copy.getPropertyFloat("posZ") + copy.getPropertyFloat("posOffsetZ")));
        }
        /*SL:216*/for (final String v1 : DropProperties.multiPosProperties) {
            /*SL:218*/if (!copy.hasProperty(v1) && (copy.hasProperty(v1 + "X") || copy.hasProperty(v1 + "Y") || copy.hasProperty(v1 + "Z"))) {
                /*SL:220*/copy.setProperty(v1, "(" + copy.getProperty(v1 + "X").toString() + "," + copy.getProperty(v1 + "Y").toString() + "," + copy.getProperty(v1 + "Z").toString() + ")");
            }
        }
        /*SL:224*/return copy;
    }
    
    @Override
    public void readFromString(final String v-7) {
        final String[] splitBracketString = /*EL:230*/DropStringUtils.splitBracketString(v-7, ',');
        /*SL:231*/for (int i = 0; i < 2; ++i) {
            /*SL:233*/for (final String s : splitBracketString) {
                final String a1 = /*EL:235*/s.substring(0, s.indexOf("=")).trim().toLowerCase(Locale.ENGLISH);
                final String v1 = /*EL:236*/s.substring(s.indexOf("=") + 1).trim();
                /*SL:238*/if (i == 0 && a1.toLowerCase(Locale.ENGLISH).equals("type")) {
                    this.setRawProperty(a1, v1);
                }
                else/*SL:239*/ if (i == 1 && !a1.toLowerCase(Locale.ENGLISH).equals("type")) {
                    this.setRawProperty(a1, v1);
                }
            }
        }
        /*SL:243*/if (this.needsInitialize && !this.hasProperty("reinitialize")) {
            this.setProperty("reinitialize", true);
        }
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound v-2) {
        final NBTTagCompound func_74775_l = /*EL:249*/v-2.func_74775_l("properties");
        /*SL:250*/for (final Object v1 : func_74775_l.func_150296_c()) {
            final DropValue a1 = /*EL:252*/new DropValue(null);
            /*SL:253*/a1.readFromNBT((NBTTagCompound)func_74775_l.func_74781_a((String)v1));
            /*SL:254*/this.properties.put((String)v1, a1);
        }
        /*SL:257*/this.needsInitialize = v-2.func_74767_n("needsInitialize");
    }
    
    @Override
    public String writeToString() {
        /*SL:263*/return null;
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound nbtTagCompound = /*EL:269*/new NBTTagCompound();
        final NBTTagCompound nbtTagCompound2 = /*EL:271*/new NBTTagCompound();
        /*SL:272*/for (final String v1 : this.properties.keySet()) {
            /*SL:273*/nbtTagCompound2.func_74782_a(v1, (NBTBase)this.properties.get(v1).writeToNBT());
        }
        /*SL:275*/nbtTagCompound.func_74782_a("properties", (NBTBase)nbtTagCompound2);
        /*SL:276*/nbtTagCompound.func_74757_a("needsInitialize", this.needsInitialize);
        /*SL:278*/return nbtTagCompound;
    }
    
    private Class getDefaultPropertyType(String v2) {
        /*SL:283*/v2 = processName(v2);
        /*SL:284*/if (this.hasProperty("type")) {
            final String a1 = /*EL:286*/this.getPropertyString("type");
            /*SL:287*/if (DropProperties.defaultValueTypes.get(a1) != null && DropProperties.defaultValueTypes.get(a1).containsKey(v2)) {
                return DropProperties.defaultValueTypes.get(a1).get(v2);
            }
        }
        /*SL:289*/return DropProperties.defaultValueTypes.get("all").get(v2);
    }
    
    private Object getDefaultPropertyValue(String v2) {
        /*SL:294*/v2 = processName(v2);
        /*SL:295*/if (this.hasProperty("type")) {
            final String a1 = /*EL:297*/this.getPropertyString("type");
            /*SL:298*/if (DropProperties.defaultValues.get(a1) != null && DropProperties.defaultValues.get(a1).containsKey(v2)) {
                return DropProperties.defaultValues.get(a1).get(v2);
            }
        }
        /*SL:300*/return DropProperties.defaultValues.get("all").get(v2);
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
        /*SL:319*/a2 = processName(a2);
        /*SL:320*/if (DropProperties.defaultValueTypes.get(a1) == null) {
            DropProperties.defaultValueTypes.put(a1, new HashMap<String, Class>());
        }
        DropProperties.defaultValueTypes.get(/*EL:321*/a1).put(a2, a3);
        /*SL:323*/if (DropProperties.defaultValues.get(a1) == null) {
            DropProperties.defaultValues.put(a1, new HashMap<String, Object>());
        }
        DropProperties.defaultValues.get(/*EL:324*/a1).put(a2, a4);
        /*SL:326*/if (!a1.equals("all")) {
            setDefaultProperty("all", a2, a3, a4);
        }
    }
    
    public static void setReplaceProperty(final String a1, final String a2) {
        DropProperties.replaceProperties.put(/*EL:331*/a1.toLowerCase(Locale.ENGLISH), a2.toLowerCase(Locale.ENGLISH));
    }
    
    private static String processName(String a1) {
        /*SL:336*/a1 = a1.toLowerCase(Locale.ENGLISH);
        /*SL:337*/if (DropProperties.replaceProperties.get(a1) != null) {
            a1 = DropProperties.replaceProperties.get(a1);
        }
        /*SL:338*/return a1;
    }
    
    @Override
    public String toString() {
        String string = /*EL:344*/"";
        /*SL:345*/for (final String v1 : this.properties.keySet()) {
            /*SL:346*/string = string + v1 + "=" + this.properties.get(v1).toString() + ",";
        }
        /*SL:347*/return string.substring(0, string.length() - 1);
    }
    
    static {
        DropProperties.multiPosProperties = new String[] { "pos", "pos2", "posOffset" };
        DropProperties.defaultValueTypes = new HashMap<String, HashMap<String, Class>>();
        DropProperties.defaultValues = new HashMap<String, HashMap<String, Object>>();
        DropProperties.replaceProperties = new HashMap<String, String>();
    }
}
