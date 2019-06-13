package mod.lucky.drop.value;

import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessData;

public class DropValue
{
    private String rawValue;
    private Object value;
    private Class valueType;
    private boolean needsInitialize;
    
    public DropValue(final Object a1) {
        this.rawValue = null;
        this.value = null;
        this.needsInitialize = false;
        this.value = a1;
    }
    
    public DropValue(final String v1, final Class v2) {
        this.rawValue = null;
        this.value = null;
        this.needsInitialize = false;
        this.valueType = v2;
        this.rawValue = v1;
        if (HashVariables.containsHashVariables(v1)) {
            this.needsInitialize = true;
        }
        else {
            try {
                this.value = ValueParser.getObject(v1, v2, null);
            }
            catch (Exception a1) {
                System.err.println("Lucky Block: Error processing value: " + this.rawValue);
                a1.printStackTrace();
            }
        }
    }
    
    public boolean initialize(final DropProcessData v2) {
        /*SL:32*/if (this.needsInitialize) {
            try {
                /*SL:34*/this.value = ValueParser.getObject(this.rawValue, this.valueType, v2);
                /*SL:35*/return true;
            }
            catch (Exception a1) {
                System.err.println(/*EL:37*/"Lucky Block: Error processing value: " + this.rawValue);
                /*SL:38*/a1.printStackTrace();
            }
        }
        /*SL:41*/return false;
    }
    
    public boolean needsInitialize() {
        /*SL:45*/return this.needsInitialize;
    }
    
    public String getRawValue() {
        /*SL:49*/return this.rawValue;
    }
    
    public Object getValue() {
        /*SL:53*/return this.value;
    }
    
    public int getValueInt() {
        /*SL:57*/return (int)this.value;
    }
    
    public String getValueString() {
        /*SL:61*/return (String)this.value;
    }
    
    public boolean getValueBoolean() {
        /*SL:65*/return (boolean)this.value;
    }
    
    public float getValueFloat() {
        /*SL:69*/return (float)this.value;
    }
    
    public NBTTagCompound getValueNBT() {
        /*SL:73*/return (NBTTagCompound)this.value;
    }
    
    public Class getValueType() {
        /*SL:77*/return this.valueType;
    }
    
    public void setValue(final Object a1) {
        /*SL:81*/this.value = a1;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:85*/new NBTTagCompound();
        /*SL:86*/if (this.value != null) {
            v1.func_74782_a("value", ValueParser.getNBTBaseFromValue(this.value));
        }
        /*SL:87*/if (this.rawValue != null) {
            v1.func_74778_a("rawValue", this.rawValue);
        }
        /*SL:88*/if (this.valueType != null) {
            /*SL:89*/v1.func_74778_a("valueType", (String)ValueParser.classTypeToString.inverse().get(this.valueType));
        }
        /*SL:90*/v1.func_74757_a("needsInitialize", this.needsInitialize);
        /*SL:91*/return v1;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:95*/if (a1.func_74764_b("value")) {
            /*SL:96*/this.value = ValueParser.getValueFromNBTBase(a1.func_74781_a("value"));
        }
        /*SL:97*/if (a1.func_74764_b("rawValue")) {
            this.rawValue = a1.func_74779_i("rawValue");
        }
        /*SL:98*/if (a1.func_74764_b("valueType")) {
            /*SL:99*/this.valueType = ValueParser.classTypeToString.get(a1.func_74779_i("valueType"));
        }
        /*SL:100*/this.needsInitialize = a1.func_74767_n("needsInitialize");
    }
    
    public DropValue copy() {
        final DropValue v1 = /*EL:104*/new DropValue(this.value);
        /*SL:105*/v1.rawValue = this.rawValue;
        /*SL:106*/v1.valueType = this.valueType;
        /*SL:107*/v1.needsInitialize = this.needsInitialize;
        /*SL:108*/return v1;
    }
    
    @Override
    public String toString() {
        /*SL:113*/if (this.value != null) {
            return this.value.toString();
        }
        /*SL:114*/if (this.rawValue != null) {
            return this.rawValue;
        }
        /*SL:115*/return "error";
    }
}
