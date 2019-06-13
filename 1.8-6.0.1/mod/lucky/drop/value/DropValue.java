package mod.lucky.drop.value;

import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessData;

public class DropValue
{
    private String rawValue;
    private Object value;
    private Class valueType;
    boolean needsInitialize;
    
    public DropValue(final Object a1) {
        this.rawValue = null;
        this.value = null;
        this.value = a1;
    }
    
    public DropValue(final String v1, final Class v2) {
        this.rawValue = null;
        this.value = null;
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
        /*SL:42*/if (this.needsInitialize) {
            try {
                /*SL:46*/this.value = ValueParser.getObject(this.rawValue, this.valueType, v2);
                /*SL:47*/return true;
            }
            catch (Exception a1) {
                System.err.println(/*EL:51*/"Lucky Block: Error processing value: " + this.rawValue);
                /*SL:52*/a1.printStackTrace();
                /*SL:54*/return false;
            }
        }
        /*SL:56*/return true;
    }
    
    public boolean needsInitialize() {
        /*SL:61*/return this.needsInitialize;
    }
    
    public String getRawValue() {
        /*SL:66*/return this.rawValue;
    }
    
    public Object getValue() {
        /*SL:71*/return this.value;
    }
    
    public int getValueInt() {
        /*SL:76*/return (int)this.value;
    }
    
    public String getValueString() {
        /*SL:81*/return (String)this.value;
    }
    
    public boolean getValueBoolean() {
        /*SL:86*/return (boolean)this.value;
    }
    
    public float getValueFloat() {
        /*SL:91*/return (float)this.value;
    }
    
    public NBTTagCompound getValueNBT() {
        /*SL:96*/return (NBTTagCompound)this.value;
    }
    
    public Class getValueType() {
        /*SL:101*/return this.valueType;
    }
    
    public void setValue(final Object a1) {
        /*SL:106*/this.value = a1;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:111*/new NBTTagCompound();
        /*SL:112*/if (this.value != null) {
            v1.func_74782_a("value", ValueParser.getNBTBaseFromValue(this.value));
        }
        /*SL:113*/if (this.rawValue != null) {
            v1.func_74778_a("rawValue", this.rawValue);
        }
        /*SL:114*/if (this.valueType != null) {
            v1.func_74778_a("valueType", (String)ValueParser.classTypeToString.inverse().get(this.valueType));
        }
        /*SL:115*/v1.func_74757_a("needsInitialize", this.needsInitialize);
        /*SL:116*/return v1;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:122*/if (a1.func_74764_b("value")) {
            this.value = ValueParser.getValueFromNBTBase(a1.func_74781_a("value"));
        }
        /*SL:123*/if (a1.func_74764_b("rawValue")) {
            this.rawValue = a1.func_74779_i("rawValue");
        }
        /*SL:124*/if (a1.func_74764_b("valueType")) {
            this.valueType = ValueParser.classTypeToString.get(a1.func_74779_i("valueType"));
        }
        /*SL:125*/this.needsInitialize = a1.func_74767_n("needsInitialize");
    }
    
    public DropValue copy() {
        final DropValue v1 = /*EL:130*/new DropValue(this.value);
        /*SL:131*/v1.rawValue = this.rawValue;
        /*SL:132*/v1.valueType = this.valueType;
        /*SL:133*/v1.needsInitialize = this.needsInitialize;
        /*SL:134*/return v1;
    }
    
    @Override
    public String toString() {
        /*SL:140*/if (this.value != null) {
            return this.value.toString();
        }
        /*SL:141*/if (this.rawValue != null) {
            return this.rawValue;
        }
        /*SL:142*/return "error";
    }
}
