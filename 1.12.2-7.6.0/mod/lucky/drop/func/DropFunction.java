package mod.lucky.drop.func;

import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.DropProperties;
import java.util.HashMap;

public abstract class DropFunction
{
    private static HashMap<String, DropFunction> dropFunctions;
    
    public abstract void process(final DropProcessData p0);
    
    public abstract String getType();
    
    public void registerProperties() {
    }
    
    public static void registerGlobalProperties() {
        /*SL:15*/DropProperties.setDefaultProperty("all", "type", String.class, "item");
        /*SL:16*/DropProperties.setDefaultProperty("all", "ID", String.class, "");
        /*SL:17*/DropProperties.setDefaultProperty("all", "damage", Integer.class, 0);
        /*SL:18*/DropProperties.setDefaultProperty("all", "amount", Integer.class, 1);
        /*SL:19*/DropProperties.setDefaultProperty("all", "reinitialize", Boolean.class, false);
        /*SL:20*/DropProperties.setDefaultProperty("all", "postDelayInit", Boolean.class, true);
        /*SL:21*/DropProperties.setDefaultProperty("all", "delay", Float.class, 0);
        /*SL:22*/DropProperties.setDefaultProperty("all", "posX", Float.class, 0);
        /*SL:23*/DropProperties.setDefaultProperty("all", "posY", Float.class, 0);
        /*SL:24*/DropProperties.setDefaultProperty("all", "posZ", Float.class, 0);
        /*SL:25*/DropProperties.setDefaultProperty("all", "pos", String.class, "(0,0,0)");
        /*SL:26*/DropProperties.setDefaultProperty("all", "pos2X", Float.class, 0);
        /*SL:27*/DropProperties.setDefaultProperty("all", "pos2Y", Float.class, 0);
        /*SL:28*/DropProperties.setDefaultProperty("all", "pos2Z", Float.class, 0);
        /*SL:29*/DropProperties.setDefaultProperty("all", "pos2", String.class, "(0,0,0)");
        /*SL:30*/DropProperties.setDefaultProperty("all", "posOffsetX", Float.class, 0);
        /*SL:31*/DropProperties.setDefaultProperty("all", "posOffsetY", Float.class, 0);
        /*SL:32*/DropProperties.setDefaultProperty("all", "posOffsetZ", Float.class, 0);
        /*SL:33*/DropProperties.setDefaultProperty("all", "posOffset", String.class, "(0,0,0)");
        /*SL:34*/DropProperties.setDefaultProperty("all", "centerX", Integer.class, 0);
        /*SL:35*/DropProperties.setDefaultProperty("all", "centerY", Integer.class, 0);
        /*SL:36*/DropProperties.setDefaultProperty("all", "centerZ", Integer.class, 0);
        /*SL:37*/DropProperties.setDefaultProperty("all", "rotation", Integer.class, 0);
        /*SL:38*/DropProperties.setDefaultProperty("all", "doUpdate", Boolean.class, false);
        /*SL:39*/DropProperties.setDefaultProperty("all", "blockMode", String.class, "replace");
        /*SL:40*/DropProperties.setDefaultProperty("all", "displayCommandOutput", Boolean.class, false);
        /*SL:41*/DropProperties.setDefaultProperty("all", "commandSender", String.class, "@");
        /*SL:42*/DropProperties.setDefaultProperty("all", "duration", Integer.class, 200);
        /*SL:43*/DropProperties.setDefaultProperty("all", "NBTTag", NBTTagCompound.class, null);
    }
    
    public static void registerFunctions() {
        registerDropFunction(/*EL:49*/new DropFuncBlock());
        registerDropFunction(/*EL:50*/new DropFuncCommand());
        registerDropFunction(/*EL:51*/new DropFuncDifficulty());
        registerDropFunction(/*EL:52*/new DropFuncEffect());
        registerDropFunction(/*EL:53*/new DropFuncEntity());
        registerDropFunction(/*EL:54*/new DropFuncExplosion());
        registerDropFunction(/*EL:55*/new DropFuncFill());
        registerDropFunction(/*EL:56*/new DropFuncItem());
        registerDropFunction(/*EL:57*/new DropFuncMessage());
        registerDropFunction(/*EL:58*/new DropFuncParticle());
        registerDropFunction(/*EL:59*/new DropFuncSound());
        registerDropFunction(/*EL:60*/new DropFuncStructure());
        registerDropFunction(/*EL:61*/new DropFuncTime());
        registerDropFunction(/*EL:62*/new DropFuncNothing());
        registerGlobalProperties();
    }
    
    public static DropFunction getDropFunction(final DropProperties a1) {
        /*SL:67*/return getDropFunction(a1.getPropertyString("type"));
    }
    
    public static DropFunction getDropFunction(final String a1) {
        /*SL:71*/return DropFunction.dropFunctions.get(a1);
    }
    
    public static void registerDropFunction(final DropFunction a1) {
        DropFunction.dropFunctions.put(/*EL:75*/a1.getType(), a1);
        /*SL:76*/a1.registerProperties();
    }
    
    static {
        DropFunction.dropFunctions = new HashMap<String, DropFunction>();
    }
}
