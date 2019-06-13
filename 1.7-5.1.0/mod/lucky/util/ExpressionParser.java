package mod.lucky.util;

import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import javax.script.ScriptEngineManager;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Random;
import javax.script.ScriptEngine;

public class ExpressionParser
{
    public static final ExpressionParser instance;
    private final ScriptEngine scritptEngine;
    private final Random random;
    public EntityPlayer player;
    public World world;
    public int harvestX;
    public int harvestY;
    public int harvestZ;
    
    public ExpressionParser() {
        final ScriptEngineManager v1 = new ScriptEngineManager(null);
        this.scritptEngine = v1.getEngineByName("JavaScript");
        this.random = new Random();
    }
    
    private String getFormattedString(String a1) throws ExpressionParserException {
        /*SL:36*/if (a1.contains("#random-") || a1.contains("#randomPositiveOrNegative-")) {
            a1 = this.replaceRandoms(a1);
        }
        /*SL:37*/a1 = a1.replaceAll("#randomPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:38*/a1 = a1.replaceAll("#randomPotionEffect", String.valueOf(LuckyFunction.getRandomPotionEffect()));
        /*SL:39*/a1 = a1.replaceAll("#randomSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEgg()));
        /*SL:41*/a1 = a1.replaceAll("#bPosX", String.valueOf(this.harvestX));
        /*SL:42*/a1 = a1.replaceAll("#bPosY", String.valueOf(this.harvestY));
        /*SL:43*/a1 = a1.replaceAll("#bPosZ", String.valueOf(this.harvestZ));
        /*SL:45*/if (this.player != null) {
            /*SL:47*/a1 = a1.replaceAll("#pPosX", String.valueOf(MathHelper.func_76128_c(this.player.field_70165_t)));
            /*SL:48*/a1 = a1.replaceAll("#pPosY", String.valueOf(MathHelper.func_76128_c(this.player.field_70163_u)));
            /*SL:49*/a1 = a1.replaceAll("#pPosZ", String.valueOf(MathHelper.func_76128_c(this.player.field_70161_v)));
            /*SL:50*/a1 = a1.replaceAll("#playerName", this.player.getDisplayName());
        }
        /*SL:53*/a1 = a1.replaceAll("'#'", "'§'");
        /*SL:54*/a1 = a1.replaceAll("#", "§");
        /*SL:55*/a1 = a1.replaceAll("'§'", "#");
        /*SL:57*/return a1;
    }
    
    private String getNewLines(String a1) {
        /*SL:62*/a1 = a1.replaceAll("\\\\t", "\t");
        /*SL:63*/a1 = a1.replaceAll("\\\\b", "\b");
        /*SL:64*/a1 = a1.replaceAll("\\\\n", "\n");
        /*SL:65*/a1 = a1.replaceAll("\\\\r", "\r");
        /*SL:66*/a1 = a1.replaceAll("\\\\f", "\f");
        /*SL:67*/return a1;
    }
    
    public String replaceRandoms(String v-5) throws ExpressionParserException {
        final ArrayList<Integer> list = /*EL:72*/new ArrayList<Integer>();
        int n = /*EL:73*/0;
        while (true) {
            final int index = /*EL:76*/v-5.indexOf("#random-", (n == 0) ? 0 : (list.get(n - 1) + 1));
            final int index2 = /*EL:77*/v-5.indexOf("#randomPositiveOrNegative-", (n == 0) ? 0 : (list.get(n - 1) + 1));
            int v1;
            final int v2;
            /*SL:80*/if ((index < index2 && index != -1) || (index2 == -1 && index > -1)) {
                final int a1 = /*EL:82*/0;
                /*SL:83*/v1 = "#random-".length();
                /*SL:84*/list.add(n, index);
            }
            else {
                /*SL:87*/if ((index2 >= index || index2 == -1) && (index != -1 || index2 <= -1)) {
                    break;
                }
                /*SL:89*/v2 = 1;
                /*SL:90*/v1 = "#randomPositiveOrNegative-".length();
                /*SL:91*/list.add(n, index2);
            }
            final char[] v3 = /*EL:98*/{ ' ', '+', '*', '(', ')', ',', ';', '/', '@' };
            final int v4 = /*EL:99*/this.getEndPoint(v-5, list.get(n) + v1, v3);
            final String v5 = /*EL:100*/v-5.substring(list.get(n), v4);
            final String[] v6 = /*EL:102*/v5.split("-");
            final int v7 = /*EL:104*/this.getInteger(v6[1]);
            final int v8 = /*EL:105*/this.getInteger(v6[2]);
            int v9 = /*EL:106*/0;
            /*SL:107*/if (v2 == 0) {
                /*SL:109*/v9 = this.random.nextInt(v8 - v7 + 1) + v7;
            }
            else/*SL:111*/ if (v2 == 1) {
                /*SL:113*/v9 = this.random.nextInt(v8 - v7 + 1) + v7;
                /*SL:114*/if (this.random.nextInt(2) == 0) {
                    v9 *= -1;
                }
            }
            final String v10 = /*EL:117*/v-5.substring(0, list.get(n));
            final String v11 = /*EL:118*/v-5.substring(v4, v-5.length());
            /*SL:119*/v-5 = v10 + "" + v9 + "" + v11;
            /*SL:121*/++n;
        }
        /*SL:124*/return v-5;
    }
    
    public int getEndPoint(final String v-8, final int v-7, final char[] v-6) {
        final char[] charArray = /*EL:129*/v-8.toCharArray();
        int length = /*EL:130*/charArray.length;
        /*SL:131*/for (int i = v-7; i < charArray.length; ++i) {
            boolean b = /*EL:133*/false;
            /*SL:134*/for (final char a2 : v-6) {
                /*SL:136*/if (charArray[i] == a2) {
                    /*SL:138*/length = i;
                    /*SL:139*/b = true;
                    /*SL:140*/break;
                }
            }
            /*SL:143*/if (b) {
                break;
            }
        }
        /*SL:145*/return length;
    }
    
    public String getString(String v-1) {
        try {
            /*SL:152*/v-1 = this.getFormattedString(v-1);
            final Object v0 = /*EL:153*/this.scritptEngine.eval(v-1);
            /*SL:155*/if (v0 instanceof String) {
                final String a1 = /*EL:157*/(String)v0;
                /*SL:158*/return this.getNewLines(a1);
            }
            /*SL:160*/if (v0 instanceof Double && String.valueOf(v0).endsWith(".0")) {
                final String v = /*EL:162*/String.valueOf(v0);
                final int v2 = /*EL:163*/this.getInteger(v);
                /*SL:164*/return this.getNewLines(String.valueOf(v2));
            }
            /*SL:166*/return this.getNewLines(String.valueOf(v0));
        }
        catch (Exception v3) {
            /*SL:170*/return v-1;
        }
    }
    
    public boolean getBoolean(String v0) throws ExpressionParserException {
        /*SL:176*/v0 = this.getFormattedString(v0);
        try {
            final Object v = /*EL:180*/this.scritptEngine.eval(v0);
            /*SL:182*/if (v instanceof Boolean) {
                final boolean a1 = /*EL:184*/(boolean)v;
                /*SL:185*/return a1;
            }
            /*SL:187*/throw new ExpressionParserException(v0);
        }
        catch (Exception v2) {
            /*SL:191*/throw new ExpressionParserException(v0);
        }
    }
    
    public int getInteger(String v-1) throws ExpressionParserException {
        /*SL:197*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:201*/this.scritptEngine.eval(v-1);
            /*SL:203*/if (v0 instanceof Integer) {
                final int a1 = /*EL:205*/(int)v0;
                /*SL:206*/return a1;
            }
            /*SL:208*/if (v0 instanceof Double) {
                final double v = /*EL:210*/(double)v0;
                /*SL:211*/return (int)v;
            }
            /*SL:213*/if (v0 instanceof Float) {
                final float v2 = /*EL:215*/(float)v0;
                /*SL:216*/return (int)v2;
            }
            /*SL:218*/throw new ExpressionParserException(v-1);
        }
        catch (Exception v3) {
            /*SL:222*/throw new ExpressionParserException(v-1);
        }
    }
    
    public double getDouble(String v-1) throws ExpressionParserException {
        /*SL:228*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:232*/this.scritptEngine.eval(v-1);
            /*SL:234*/if (v0 instanceof Double) {
                final double a1 = /*EL:236*/(double)v0;
                /*SL:237*/return a1;
            }
            /*SL:239*/if (v0 instanceof Integer) {
                final int v = /*EL:241*/(int)v0;
                /*SL:242*/return v;
            }
            /*SL:244*/if (v0 instanceof Float) {
                final float v2 = /*EL:246*/(float)v0;
                /*SL:247*/return v2;
            }
            /*SL:249*/throw new ExpressionParserException(v-1);
        }
        catch (Exception v3) {
            /*SL:253*/throw new ExpressionParserException(v-1);
        }
    }
    
    public float getFloat(String v-1) throws ExpressionParserException {
        /*SL:259*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:263*/this.scritptEngine.eval(v-1);
            /*SL:265*/if (v0 instanceof Float) {
                final float a1 = /*EL:267*/(float)v0;
                /*SL:268*/return a1;
            }
            /*SL:270*/if (v0 instanceof Integer) {
                final int v = /*EL:272*/(int)v0;
                /*SL:273*/return v;
            }
            /*SL:275*/if (v0 instanceof Double) {
                final double v2 = /*EL:277*/(double)v0;
                /*SL:278*/return (float)v2;
            }
            /*SL:280*/throw new ExpressionParserException(v-1);
        }
        catch (Exception v3) {
            /*SL:284*/throw new ExpressionParserException(v-1);
        }
    }
    
    public short getShort(final String v2) throws ExpressionParserException {
        try {
            /*SL:292*/return (short)this.getInteger(v2);
        }
        catch (Exception a1) {
            /*SL:296*/throw new ExpressionParserException(v2);
        }
    }
    
    public byte getByte(final String v2) throws ExpressionParserException {
        try {
            /*SL:304*/return (byte)this.getInteger(v2);
        }
        catch (Exception a1) {
            /*SL:308*/throw new ExpressionParserException(v2);
        }
    }
    
    static {
        instance = new ExpressionParser();
    }
    
    public class ExpressionParserException extends Exception
    {
        public ExpressionParserException(final String a1) {
            super("Invalid input string: " + a1);
        }
        
        public ExpressionParserException() {
        }
    }
}
