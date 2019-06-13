package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import java.util.HashMap;

public abstract class DropFunction
{
    private static HashMap<String, DropFunction> dropFunctions;
    
    public abstract void process(final DropProcessData p0);
    
    public abstract String getType();
    
    public void registerProperties() {
        /*SL:15*/DropProperties.setDefaultProperty("all", "type", String.class, "item");
        /*SL:16*/DropProperties.setDefaultProperty("all", "ID", String.class, "");
        /*SL:17*/DropProperties.setDefaultProperty("all", "damage", Integer.class, 0);
        /*SL:18*/DropProperties.setDefaultProperty("all", "amount", Integer.class, 1);
        /*SL:19*/DropProperties.setDefaultProperty("all", "reinitialize", Boolean.class, false);
        /*SL:20*/DropProperties.setDefaultProperty("all", "delay", Float.class, 0);
        /*SL:21*/DropProperties.setDefaultProperty("all", "postDelayInit", Boolean.class, true);
        /*SL:22*/DropProperties.setDefaultProperty("all", "posX", Integer.class, 0);
        /*SL:23*/DropProperties.setDefaultProperty("all", "posY", Integer.class, 0);
        /*SL:24*/DropProperties.setDefaultProperty("all", "posZ", Integer.class, 0);
        /*SL:25*/DropProperties.setDefaultProperty("all", "pos", String.class, "(0,0,0)");
        /*SL:26*/DropProperties.setDefaultProperty("all", "posOffsetX", Integer.class, 0);
        /*SL:27*/DropProperties.setDefaultProperty("all", "posOffsetY", Integer.class, 0);
        /*SL:28*/DropProperties.setDefaultProperty("all", "posOffsetZ", Integer.class, 0);
        /*SL:29*/DropProperties.setDefaultProperty("all", "posOffset", String.class, "(0,0,0)");
    }
    
    public static void registerFunctions() {
        registerDropFunction(/*EL:36*/new DropFuncBlock());
        registerDropFunction(/*EL:37*/new DropFuncCommand());
        registerDropFunction(/*EL:38*/new DropFuncDifficulty());
        registerDropFunction(/*EL:39*/new DropFuncEffect());
        registerDropFunction(/*EL:40*/new DropFuncEntity());
        registerDropFunction(/*EL:41*/new DropFuncExplosion());
        registerDropFunction(/*EL:42*/new DropFuncFill());
        registerDropFunction(/*EL:43*/new DropFuncItem());
        registerDropFunction(/*EL:44*/new DropFuncMessage());
        registerDropFunction(/*EL:45*/new DropFuncParticle());
        registerDropFunction(/*EL:46*/new DropFuncSound());
        registerDropFunction(/*EL:47*/new DropFuncStructure());
        registerDropFunction(/*EL:48*/new DropFuncTime());
    }
    
    public static DropFunction getDropFunction(final DropProperties a1) {
        /*SL:53*/return getDropFunction(a1.getPropertyString("type"));
    }
    
    public static DropFunction getDropFunction(final String a1) {
        /*SL:58*/return DropFunction.dropFunctions.get(a1);
    }
    
    public static void registerDropFunction(final DropFunction a1) {
        DropFunction.dropFunctions.put(/*EL:63*/a1.getType(), a1);
        /*SL:64*/a1.registerProperties();
    }
    
    static {
        DropFunction.dropFunctions = new HashMap<String, DropFunction>();
    }
}
