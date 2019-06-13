package mod.lucky.drop.value;

import javax.script.ScriptException;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.item.Item;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTBase;
import mod.lucky.structure.rotation.Rotations;
import net.minecraft.util.Vec3;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import mod.lucky.util.LuckyFunction;
import net.minecraft.util.BlockPos;
import javax.script.ScriptEngineManager;
import mod.lucky.drop.LuckyDropOld;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Random;
import javax.script.ScriptEngine;

public class ExpressionParserOld
{
    public static final ExpressionParserOld instance;
    private final ScriptEngine scriptEngine;
    private final Random random;
    public EntityPlayer player;
    public World world;
    public int harvestX;
    public int harvestY;
    public int harvestZ;
    public LuckyDropOld structDrop;
    public static String[] hashVariables;
    public static String[] propertyStrings;
    
    public ExpressionParserOld() {
        final ScriptEngineManager v1 = new ScriptEngineManager(null);
        this.scriptEngine = v1.getEngineByName("JavaScript");
        this.random = new Random();
    }
    
    public void initialize(final World a1, final EntityPlayer a2, final BlockPos a3) {
        /*SL:60*/this.world = a1;
        /*SL:61*/this.player = a2;
        /*SL:62*/this.harvestX = a3.func_177958_n();
        /*SL:63*/this.harvestY = a3.func_177956_o();
        /*SL:64*/this.harvestZ = a3.func_177952_p();
    }
    
    private String getFormattedString(String v-1) throws ExpressionParserException {
        /*SL:69*/v-1 = this.replaceProperties(v-1);
        /*SL:71*/v-1 = v-1.replaceAll("#randomPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:72*/v-1 = v-1.replaceAll("#randomPotionEffect", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:73*/v-1 = v-1.replaceAll("#randomSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEgg()));
        /*SL:75*/v-1 = v-1.replaceAll("#bPosX", String.valueOf(this.harvestX));
        /*SL:76*/v-1 = v-1.replaceAll("#bPosY", String.valueOf(this.harvestY));
        /*SL:77*/v-1 = v-1.replaceAll("#bPosZ", String.valueOf(this.harvestZ));
        /*SL:78*/v-1 = v-1.replaceAll("#bPos", String.valueOf("(" + this.harvestX + "," + this.harvestY + "," + this.harvestZ + ")"));
        /*SL:80*/if (this.world != null) {
            v-1 = v-1.replaceAll("#time", String.valueOf(this.world.func_72820_D()));
        }
        /*SL:82*/if (this.player != null) {
            final int a1 = /*EL:84*/MathHelper.func_76128_c(this.player.field_70165_t);
            final int v1 = /*EL:85*/MathHelper.func_76128_c(this.player.field_70163_u);
            final int v2 = /*EL:86*/MathHelper.func_76128_c(this.player.field_70161_v);
            /*SL:87*/v-1 = v-1.replaceAll("#pPosX", String.valueOf(a1));
            /*SL:88*/v-1 = v-1.replaceAll("#pPosY", String.valueOf(v1));
            /*SL:89*/v-1 = v-1.replaceAll("#pPosZ", String.valueOf(v2));
            /*SL:90*/v-1 = v-1.replaceAll("#pPos", String.valueOf("(" + a1 + "," + v1 + "," + v2 + ")"));
            /*SL:91*/v-1 = v-1.replaceAll("#playerName", this.player.func_145748_c_().func_150260_c());
            int v3 = /*EL:92*/(int)Math.round((this.player.field_70177_z + 180.0) / 90.0) % 4;
            /*SL:93*/if (v3 < 0) {
                v3 += 4;
            }
            /*SL:94*/v-1 = v-1.replaceAll("#playerDirect", String.valueOf(v3));
            /*SL:95*/v-1 = v-1.replaceAll("#playerYaw", String.valueOf(this.player.field_70177_z));
            /*SL:96*/v-1 = v-1.replaceAll("#playerPitch", String.valueOf(this.player.field_70125_A));
        }
        /*SL:99*/if (this.structDrop != null) {
            /*SL:101*/v-1 = v-1.replaceAll("#posX", String.valueOf(this.structDrop.getPosX()));
            /*SL:102*/v-1 = v-1.replaceAll("#posY", String.valueOf(this.structDrop.getPosY()));
            /*SL:103*/v-1 = v-1.replaceAll("#posZ", String.valueOf(this.structDrop.getPosZ()));
            /*SL:104*/v-1 = v-1.replaceAll("#pos", String.valueOf("(" + this.structDrop.getPosX() + "," + this.structDrop.getPosY() + "," + this.structDrop.getPosZ() + ")"));
            /*SL:105*/v-1 = v-1.replaceAll("#rotation", String.valueOf(this.structDrop.getRotation()));
        }
        /*SL:108*/v-1 = v-1.replaceAll("'#'", "'§'");
        /*SL:109*/v-1 = v-1.replaceAll("#", "§");
        /*SL:110*/v-1 = v-1.replaceAll("'§'", "#");
        /*SL:112*/return v-1;
    }
    
    private String fixBackslash(String a1) {
        /*SL:117*/a1 = a1.replaceAll("\\\\t", "\t");
        /*SL:118*/a1 = a1.replaceAll("\\\\b", "\b");
        /*SL:119*/a1 = a1.replaceAll("\\\\n", "\n");
        /*SL:120*/a1 = a1.replaceAll("\\\\r", "\r");
        /*SL:121*/a1 = a1.replaceAll("\\\\f", "\f");
        /*SL:122*/return a1;
    }
    
    public String replaceProperties(String v-6) throws ExpressionParserException {
        final ArrayList<Integer> list = /*EL:127*/new ArrayList<Integer>();
        int n = /*EL:128*/0;
        while (true) {
            int n2 = /*EL:131*/-1;
            int n3 = /*EL:132*/-1;
            /*SL:136*/for (int v1 = 0; v1 < ExpressionParserOld.propertyStrings.length; ++v1) {
                final int a1 = /*EL:138*/v-6.indexOf(ExpressionParserOld.propertyStrings[v1], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:139*/if (a1 != -1 && (n2 == -1 || a1 < n2)) {
                    /*SL:141*/n2 = a1;
                    /*SL:142*/n3 = v1;
                }
            }
            /*SL:145*/if (n2 == -1) {
                break;
            }
            final int n4 = /*EL:146*/n3;
            final int v2 = /*EL:147*/ExpressionParserOld.propertyStrings[n3].length();
            /*SL:148*/list.add(n, n2);
            String v3 = /*EL:150*/"";
            int v4 = /*EL:151*/-1;
            final char[] v5 = /*EL:153*/v-6.toCharArray();
            int v6 = /*EL:154*/0;
            boolean v7 = /*EL:155*/false;
            boolean v8 = /*EL:156*/false;
            final char[] v9 = /*EL:158*/new char[v5.length];
            int v10 = /*EL:159*/0;
            /*SL:161*/for (int v11 = 0; v11 < v5.length; ++v11) {
                final boolean v12 = /*EL:163*/v11 > 0 && v5[v11 - 1] == '\\';
                /*SL:164*/v9[v10] = v5[v11];
                /*SL:166*/if (v11 >= list.get(n) + v2 - 1) {
                    /*SL:168*/if (!v12) {
                        /*SL:170*/if (v5[v11] == '\"') {
                            v8 = !v8;
                        }
                        /*SL:171*/if ((v5[v11] == '(' || v5[v11] == '[' || v5[v11] == '{') && !v8) {
                            /*SL:173*/++v6;
                        }
                        /*SL:175*/if ((v5[v11] == ')' || v5[v11] == ']' || v5[v11] == '}') && !v8) {
                            /*SL:177*/--v6;
                        }
                        /*SL:179*/if (v6 == 0) {
                            final char[] v13 = /*EL:181*/new char[v10];
                            /*SL:182*/for (int v14 = 0; v14 < v13.length; ++v14) {
                                /*SL:183*/v13[v14] = v9[v14];
                            }
                            /*SL:184*/v3 = new String(v13);
                            /*SL:185*/v4 = v11;
                            /*SL:186*/break;
                        }
                    }
                    /*SL:191*/if (v5[v11] == '\"' && v12 && v7) {
                        /*SL:193*/--v10;
                        /*SL:194*/v9[v10] = '\"';
                        final boolean v15 = /*EL:195*/v11 > 1 && v5[v11 - 2] == '\\';
                        /*SL:196*/if (!v15) {
                            v8 = !v8;
                        }
                    }
                }
                else/*SL:201*/ if (v5[v11] == '\"' && !v12) {
                    v7 = !v7;
                }
                /*SL:203*/++v10;
            }
            /*SL:206*/v3 = v3.substring(list.get(n) + v2, v3.length());
            final String[] v16 = /*EL:207*/DropStringUtils.splitBracketString(v3, ',');
            String v17 = /*EL:209*/"";
            /*SL:210*/if (n4 == 0 || n4 == 1) {
                final boolean v15 = /*EL:212*/this.isFloat(v16[0]) || this.isFloat(v16[1]);
                /*SL:213*/v16[0] = this.removeSuffix(v16[0]);
                /*SL:214*/v16[1] = this.removeSuffix(v16[1]);
                /*SL:216*/if (v15) {
                    final float v18 = /*EL:218*/this.getFloat(v16[0]);
                    final float v19 = /*EL:219*/this.getFloat(v16[1]);
                    float v20 = /*EL:220*/MathHelper.func_151240_a(this.random, v18, v19);
                    /*SL:221*/if (n4 == 1 && this.random.nextInt(2) == 0) {
                        v20 *= -1.0f;
                    }
                    /*SL:222*/v17 = String.valueOf(v20);
                }
                else {
                    final int v14 = /*EL:226*/this.getInteger(v16[0]);
                    final int v21 = /*EL:227*/this.getInteger(v16[1]);
                    int v22 = /*EL:228*/this.random.nextInt(v21 - v14 + 1) + v14;
                    /*SL:229*/if (n4 == 1 && this.random.nextInt(2) == 0) {
                        v22 *= -1;
                    }
                    /*SL:230*/v17 = String.valueOf(v22);
                }
            }
            else/*SL:233*/ if (n4 == 2) {
                final int v23 = /*EL:235*/this.random.nextInt(v16.length);
                /*SL:236*/v17 = this.getString(v16[v23]);
            }
            else/*SL:238*/ if (n4 == 3 || n4 == 4 || n4 == 5 || n4 == 6) {
                /*SL:240*/if (this.structDrop != null) {
                    final boolean v15 = /*EL:242*/this.isFloat(v16[0]) || this.isFloat(v16[1]) || this.isFloat(v16[2]);
                    /*SL:243*/v16[0] = this.removeSuffix(v16[0]);
                    /*SL:244*/v16[1] = this.removeSuffix(v16[1]);
                    /*SL:245*/v16[2] = this.removeSuffix(v16[2]);
                    final Vec3 v24 = /*EL:247*/new Vec3((double)this.getFloat(v16[0]), (double)this.getFloat(v16[1]), (double)this.getFloat(v16[2]));
                    final int v21 = /*EL:248*/this.structDrop.rotation;
                    final double v25 = /*EL:249*/this.structDrop.centerX - v24.field_72450_a;
                    final double v26 = /*EL:250*/this.structDrop.centerY - v24.field_72448_b;
                    final double v27 = /*EL:251*/this.structDrop.centerZ - v24.field_72449_c;
                    Vec3 v28 = /*EL:252*/new Vec3(this.harvestX - v25, this.harvestY - v26, this.harvestZ - v27);
                    /*SL:253*/v28 = Rotations.rotatePos(v28, new Vec3((double)this.harvestX, (double)this.harvestY, (double)this.harvestZ), v21);
                    /*SL:255*/if (v15) {
                        /*SL:257*/if (n4 == 3) {
                            v17 = String.valueOf((float)v28.field_72450_a);
                        }
                        /*SL:258*/if (n4 == 4) {
                            v17 = String.valueOf((float)v28.field_72448_b);
                        }
                        /*SL:259*/if (n4 == 5) {
                            v17 = String.valueOf((float)v28.field_72449_c);
                        }
                        /*SL:260*/if (n4 == 6) {
                            v17 = String.valueOf(String.valueOf("(" + (float)v28.field_72450_a + "," + (float)v28.field_72448_b + "," + (float)v28.field_72449_c + ")"));
                        }
                    }
                    else {
                        /*SL:264*/if (n4 == 3) {
                            v17 = String.valueOf(Math.round(v28.field_72450_a));
                        }
                        /*SL:265*/if (n4 == 4) {
                            v17 = String.valueOf(Math.round(v28.field_72448_b));
                        }
                        /*SL:266*/if (n4 == 5) {
                            v17 = String.valueOf(Math.round(v28.field_72449_c));
                        }
                        /*SL:267*/if (n4 == 6) {
                            v17 = String.valueOf(String.valueOf("(" + Math.round(v28.field_72450_a) + "," + Math.round(v28.field_72448_b) + "," + Math.round(v28.field_72449_c) + ")"));
                        }
                    }
                }
                else {
                    /*SL:270*/v17 = "0";
                }
            }
            final String v29 = /*EL:273*/v-6.substring(0, list.get(n));
            final String v30 = /*EL:274*/v-6.substring(v4 + 1, v-6.length());
            /*SL:275*/v-6 = v29 + v17 + v30;
            /*SL:277*/++n;
        }
        /*SL:279*/return v-6;
    }
    
    public int getEndPoint(final String v2, final int v3, final char... v4) {
        final char[] v5 = /*EL:284*/v2.toCharArray();
        int v6 = /*EL:285*/v5.length;
        /*SL:286*/for (char a3 = (char)v3; a3 < v5.length; ++a3) {
            boolean a2 = /*EL:288*/false;
            /*SL:289*/for (final char a3 : v4) {
                /*SL:291*/if (v5[a3] == a3) {
                    /*SL:293*/v6 = a3;
                    /*SL:294*/a2 = true;
                    /*SL:295*/break;
                }
            }
            /*SL:298*/if (a2) {
                break;
            }
        }
        /*SL:300*/return v6;
    }
    
    public boolean isFloat(final String v2) {
        boolean v3 = /*EL:305*/false;
        int a1 = /*EL:306*/v2.length() - 1;
        while (a1 >= 0) {
            /*SL:308*/if (Character.isDigit(v2.charAt(a1))) {
                --a1;
            }
            else {
                /*SL:309*/if (v2.charAt(a1) == '.') {
                    v3 = true;
                    break;
                }
                break;
            }
        }
        /*SL:312*/return v2.endsWith("f") || v2.endsWith("F") || v2.endsWith("d") || v2.endsWith("D") || v3;
    }
    
    public boolean isDouble(final String v2) {
        boolean v3 = /*EL:318*/false;
        int a1 = /*EL:319*/v2.length() - 1;
        while (a1 >= 0) {
            /*SL:321*/if (Character.isDigit(v2.charAt(a1))) {
                --a1;
            }
            else {
                /*SL:322*/if (v2.charAt(a1) == '.') {
                    v3 = true;
                    break;
                }
                break;
            }
        }
        /*SL:325*/return v2.endsWith("d") || v2.endsWith("D") || v3;
    }
    
    public String removeSuffix(final String a1) {
        /*SL:332*/if (a1.endsWith("f") || a1.endsWith("F") || a1.endsWith("d") || a1.endsWith("D") || a1.endsWith("s") || a1.endsWith("S") || a1.endsWith("b") || a1.endsWith("B")) {
            return a1.substring(0, a1.length() - 1);
        }
        /*SL:333*/return a1;
    }
    
    public NBTBase getFormattedNBT(final NBTBase v-1) {
        /*SL:338*/if (v-1 instanceof NBTTagString) {
            try {
                /*SL:342*/return this.getNBTValueFromString(((NBTTagString)v-1).func_150285_a_());
            }
            catch (Exception ex) {}
        }
        /*SL:348*/if (v-1 instanceof NBTTagCompound) {
            /*SL:350*/for (final Object v1 : ((NBTTagCompound)v-1).func_150296_c()) {
                final NBTBase a1 = /*EL:352*/this.getFormattedNBT(((NBTTagCompound)v-1).func_74781_a((String)v1));
                /*SL:353*/((NBTTagCompound)v-1).func_74782_a((String)v1, a1);
            }
        }
        /*SL:356*/if (v-1 instanceof NBTTagList) {
            /*SL:358*/if (((NBTTagList)v-1).func_150303_d() == 8) {
                /*SL:360*/for (int v2 = 0; v2 < ((NBTTagList)v-1).func_74745_c(); ++v2) {
                    final String v3 = /*EL:362*/((NBTTagList)v-1).func_150307_f(v2);
                    final NBTBase v4 = /*EL:363*/this.getNBTValueFromString(v3);
                    /*SL:364*/((NBTTagList)v-1).func_150304_a(v2, v4);
                }
            }
            /*SL:367*/if (((NBTTagList)v-1).func_150303_d() == 10) {
                /*SL:369*/for (int v2 = 0; v2 < ((NBTTagList)v-1).func_74745_c(); ++v2) {
                    final NBTTagCompound v5 = /*EL:371*/((NBTTagList)v-1).func_150305_b(v2);
                    final NBTTagCompound v6 = /*EL:372*/(NBTTagCompound)this.getFormattedNBT((NBTBase)v5);
                    /*SL:373*/((NBTTagList)v-1).func_150304_a(v2, (NBTBase)v6);
                }
            }
        }
        /*SL:377*/return v-1;
    }
    
    public NBTBase getNBTValueFromString(final String v0) {
        try {
            final Object a1 = /*EL:384*/this.getFormattedString(v0);
            /*SL:385*/return (NBTBase)new NBTTagString((String)a1);
        }
        catch (Exception v) {
            /*SL:389*/return (NBTBase)new NBTTagString(v0);
        }
    }
    
    private Object getFormattedObject(final String a1) {
        Object v1 = /*EL:395*/a1;
        try {
            /*SL:398*/if (a1.endsWith("d") || a1.endsWith("D")) {
                v1 = this.getDouble(a1.substring(0, a1.length() - 1));
            }
            else/*SL:399*/ if (a1.endsWith("f") || a1.endsWith("F")) {
                v1 = this.getFloat(a1.substring(0, a1.length() - 1));
            }
            else/*SL:400*/ if (a1.endsWith("s") || a1.endsWith("S")) {
                v1 = this.getShort(a1.substring(0, a1.length() - 1));
            }
            else/*SL:401*/ if (a1.endsWith("b") || a1.endsWith("B")) {
                v1 = this.getByte(a1.substring(0, a1.length() - 1));
            }
        }
        catch (Exception ex) {}
        /*SL:406*/return v1;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:411*/for (final String a1 : ExpressionParserOld.hashVariables) {
            /*SL:412*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:413*/for (final String v1 : ExpressionParserOld.propertyStrings) {
            /*SL:414*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:415*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:416*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:417*/return false;
    }
    
    public Object getObject(String v0) throws ExpressionParserException {
        /*SL:422*/v0 = this.getFormattedString(v0);
        try {
            final Object a1 = /*EL:425*/this.scriptEngine.eval(v0);
            /*SL:426*/return a1;
        }
        catch (Exception v) {
            /*SL:430*/return v0;
        }
    }
    
    public Object getObject(String v1, final Class v2) throws ExpressionParserException {
        /*SL:436*/v1 = this.getFormattedString(v1);
        /*SL:438*/if (v2 == String.class) {
            return this.getString(v1);
        }
        /*SL:439*/if (v2 == Integer.class) {
            return this.getInteger(v1);
        }
        /*SL:440*/if (v2 == Boolean.class) {
            return this.getBoolean(v1);
        }
        /*SL:441*/if (v2 == Float.class) {
            return this.getFloat(v1);
        }
        /*SL:442*/if (v2 == Double.class) {
            return this.getDouble(v1);
        }
        /*SL:443*/if (v2 == Short.class) {
            return this.getShort(v1);
        }
        /*SL:444*/if (v2 == Byte.class) {
            return this.getByte(v1);
        }
        /*SL:445*/if (v2 == NBTTagCompound.class) {
            final NBTTagCompound a1 = /*EL:447*/new NBTTagCompound();
            /*SL:448*/this.getNBTTag((NBTBase)new NBTTagCompound(), v1);
            /*SL:449*/return a1;
        }
        /*SL:451*/return null;
    }
    
    public String getString(String v-1) throws ExpressionParserException {
        /*SL:456*/v-1 = v-1.trim();
        /*SL:457*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:460*/this.scriptEngine.eval(v-1);
            /*SL:462*/if (v0 instanceof String) {
                final String a1 = /*EL:464*/(String)v0;
                /*SL:465*/return this.fixBackslash(a1);
            }
            /*SL:468*/if (v0 instanceof Double && String.valueOf(v0).endsWith(".0")) {
                final String v = /*EL:470*/String.valueOf(v0);
                final int v2 = /*EL:471*/this.getInteger(v);
                /*SL:472*/return this.fixBackslash(String.valueOf(v2));
            }
            /*SL:474*/return this.fixBackslash(String.valueOf(v0));
        }
        catch (Exception v3) {
            /*SL:478*/return v-1;
        }
    }
    
    public boolean getBoolean(String v0) throws ExpressionParserException {
        /*SL:484*/v0 = this.getFormattedString(v0);
        try {
            final Object v = /*EL:487*/this.scriptEngine.eval(v0);
            /*SL:489*/if (v instanceof Boolean) {
                final boolean a1 = /*EL:491*/(boolean)v;
                /*SL:492*/return a1;
            }
            /*SL:494*/throw new ExpressionParserException(v0, null);
        }
        catch (Exception v2) {
            /*SL:498*/throw new ExpressionParserException(v0, v2);
        }
    }
    
    public int getInteger(String v-1) throws ExpressionParserException {
        /*SL:504*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:507*/this.scriptEngine.eval(v-1);
            /*SL:509*/if (v0 instanceof Integer) {
                final int a1 = /*EL:511*/(int)v0;
                /*SL:512*/return a1;
            }
            /*SL:514*/if (v0 instanceof Double) {
                final double v = /*EL:516*/(double)v0;
                /*SL:517*/return (int)v;
            }
            /*SL:519*/if (v0 instanceof Float) {
                final float v2 = /*EL:521*/(float)v0;
                /*SL:522*/return (int)v2;
            }
            /*SL:524*/throw new ExpressionParserException(v-1, null);
        }
        catch (Exception v3) {
            /*SL:528*/throw new ExpressionParserException(v-1, v3);
        }
    }
    
    public double getDouble(String v-1) throws ExpressionParserException {
        /*SL:534*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:537*/this.scriptEngine.eval(v-1);
            /*SL:539*/if (v0 instanceof Double) {
                final double a1 = /*EL:541*/(double)v0;
                /*SL:542*/return a1;
            }
            /*SL:544*/if (v0 instanceof Integer) {
                final int v = /*EL:546*/(int)v0;
                /*SL:547*/return v;
            }
            /*SL:549*/if (v0 instanceof Float) {
                final float v2 = /*EL:551*/(float)v0;
                /*SL:552*/return v2;
            }
            /*SL:554*/throw new ExpressionParserException(v-1, null);
        }
        catch (Exception v3) {
            /*SL:558*/throw new ExpressionParserException(v-1, v3);
        }
    }
    
    public float getFloat(String v-1) throws ExpressionParserException {
        /*SL:564*/v-1 = this.getFormattedString(v-1);
        try {
            final Object v0 = /*EL:567*/this.scriptEngine.eval(v-1);
            /*SL:569*/if (v0 instanceof Float) {
                final float a1 = /*EL:571*/(float)v0;
                /*SL:572*/return a1;
            }
            /*SL:574*/if (v0 instanceof Integer) {
                final int v = /*EL:576*/(int)v0;
                /*SL:577*/return v;
            }
            /*SL:579*/if (v0 instanceof Double) {
                final double v2 = /*EL:581*/(double)v0;
                /*SL:582*/return (float)v2;
            }
            /*SL:584*/throw new ExpressionParserException(v-1, null);
        }
        catch (Exception v3) {
            /*SL:588*/throw new ExpressionParserException(v-1, v3);
        }
    }
    
    public short getShort(final String v2) throws ExpressionParserException {
        try {
            /*SL:596*/return (short)this.getInteger(v2);
        }
        catch (Exception a1) {
            /*SL:600*/throw new ExpressionParserException(v2, a1);
        }
    }
    
    public byte getByte(final String v2) throws ExpressionParserException {
        try {
            /*SL:608*/return (byte)this.getInteger(v2);
        }
        catch (Exception a1) {
            /*SL:612*/throw new ExpressionParserException(v2, a1);
        }
    }
    
    public void getNBTTag(final NBTBase v-9, final String v-8) throws ExpressionParserException {
        final String[] splitBracketString;
        final String[] array = /*EL:619*/splitBracketString = DropStringUtils.splitBracketString(v-8.substring(1, v-8.length() - 1), ',');
        for (final String s : splitBracketString) {
            Label_0836: {
                /*SL:621*/if (s != null) {
                    if (!s.equals("")) {
                        final String s2 = /*EL:622*/(v-9 instanceof NBTTagCompound) ? s.substring(0, s.indexOf("=")) : "";
                        final String v-10 = /*EL:623*/(v-9 instanceof NBTTagCompound) ? s.substring(s.indexOf("=") + 1) : s;
                        /*SL:627*/if (s2.equals("id")) {
                            try {
                                final Item a1 = (Item)Item.field_150901_e.func_82594_a(/*EL:631*/(Object)this.getString(v-10));
                                /*SL:632*/if (a1 != null) {
                                    /*SL:634*/this.setTagValue(v-9, s2, Item.field_150901_e.func_148757_b((Object)a1));
                                    break Label_0836;
                                }
                            }
                            catch (Exception ex) {}
                        }
                        final NBTBase v0 = /*EL:642*/CustomNBTTags.getNBTTagFromString(v-10, null);
                        /*SL:643*/if (v-10.startsWith("#") && v0 != null) {
                            /*SL:645*/this.setTagValue(v-9, s2, v0);
                        }
                        else/*SL:647*/ if (v-10.startsWith("(") && v-10.endsWith(")")) {
                            final NBTTagCompound a2 = /*EL:649*/new NBTTagCompound();
                            /*SL:650*/this.getNBTTag((NBTBase)a2, v-10);
                            /*SL:651*/this.setTagValue(v-9, s2, a2);
                        }
                        else/*SL:653*/ if (v-10.startsWith("[") && v-10.endsWith("]")) {
                            final NBTTagList v = /*EL:655*/new NBTTagList();
                            /*SL:656*/this.getNBTTag((NBTBase)v, v-10);
                            /*SL:657*/this.setTagValue(v-9, s2, v);
                        }
                        else/*SL:659*/ if (v-10.startsWith("\"") && v-10.endsWith("\"")) {
                            /*SL:661*/this.setTagValue(v-9, s2, this.getString(v-10));
                        }
                        else {
                            try {
                                /*SL:667*/if (v-10.endsWith("d") || v-10.endsWith("D") || this.isDouble(v-10)) {
                                    this.setTagValue(v-9, s2, this.getDouble(v-10.substring(0, v-10.length() - 1)));
                                }
                                else/*SL:668*/ if (v-10.endsWith("f") || v-10.endsWith("F")) {
                                    this.setTagValue(v-9, s2, this.getFloat(v-10.substring(0, v-10.length() - 1)));
                                }
                                else/*SL:669*/ if (v-10.endsWith("s") || v-10.endsWith("S")) {
                                    this.setTagValue(v-9, s2, this.getShort(v-10.substring(0, v-10.length() - 1)));
                                }
                                else {
                                    /*SL:670*/if (!v-10.endsWith("b") && !v-10.endsWith("B")) {
                                        /*SL:671*/throw new ExpressionParserException();
                                    }
                                    this.setTagValue(v-9, s2, this.getByte(v-10.substring(0, v-10.length() - 1)));
                                }
                            }
                            catch (ExpressionParserException v7) {
                                try {
                                    /*SL:677*/this.setTagValue(v-9, s2, this.getInteger(v-10));
                                }
                                catch (ExpressionParserException v8) {
                                    try {
                                        /*SL:683*/this.setTagValue(v-9, s2, this.getBoolean(v-10));
                                    }
                                    catch (ExpressionParserException v9) {
                                        try {
                                            final String[] v2 = /*EL:689*/v-10.split(":");
                                            /*SL:690*/if (v2.length == 0) {
                                                throw new ExpressionParserException();
                                            }
                                            int v3 = /*EL:692*/0;
                                            /*SL:693*/if (v2[0].endsWith("b") || v2[0].endsWith("B")) {
                                                v3 = 1;
                                            }
                                            final int[] v4 = /*EL:695*/new int[v2.length];
                                            final byte[] v5 = /*EL:696*/new byte[v2.length];
                                            /*SL:697*/for (int v6 = 0; v6 < v2.length; ++v6) {
                                                /*SL:699*/if (v3 == 0) {
                                                    v4[v6] = this.getInteger(v2[v6]);
                                                }
                                                else {
                                                    /*SL:702*/v2[v6] = this.removeSuffix(v2[v6]);
                                                    /*SL:703*/v5[v6] = this.getByte(v2[v6]);
                                                }
                                            }
                                            /*SL:707*/if (v3 == 0) {
                                                /*SL:709*/if (v4.length == 0) {
                                                    throw new ExpressionParserException();
                                                }
                                                /*SL:710*/this.setTagValue(v-9, s2, v4);
                                            }
                                            else {
                                                /*SL:714*/if (v5.length == 0) {
                                                    throw new ExpressionParserException();
                                                }
                                                /*SL:715*/this.setTagValue(v-9, s2, v5);
                                            }
                                        }
                                        catch (ExpressionParserException v10) {
                                            /*SL:720*/this.setTagValue(v-9, s2, this.getString(v-10));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setTagValue(final NBTBase v1, final String v2, final Object v3) {
        /*SL:731*/if (v1 instanceof NBTTagCompound) {
            final NBTTagCompound a1 = /*EL:733*/(NBTTagCompound)v1;
            /*SL:734*/if (v3 instanceof String) {
                a1.func_74778_a(v2, (String)v3);
            }
            /*SL:735*/if (v3 instanceof Boolean) {
                a1.func_74757_a(v2, (boolean)v3);
            }
            /*SL:736*/if (v3 instanceof Integer) {
                a1.func_74768_a(v2, (int)v3);
            }
            /*SL:737*/if (v3 instanceof Float) {
                a1.func_74776_a(v2, (float)v3);
            }
            /*SL:738*/if (v3 instanceof Double) {
                a1.func_74780_a(v2, (double)v3);
            }
            /*SL:739*/if (v3 instanceof Short) {
                a1.func_74777_a(v2, (short)v3);
            }
            /*SL:740*/if (v3 instanceof Byte) {
                a1.func_74774_a(v2, (byte)v3);
            }
            /*SL:741*/if (v3 instanceof int[]) {
                a1.func_74783_a(v2, (int[])v3);
            }
            /*SL:742*/if (v3 instanceof byte[]) {
                a1.func_74773_a(v2, (byte[])v3);
            }
            /*SL:743*/if (v3 instanceof NBTTagCompound) {
                a1.func_74782_a(v2, (NBTBase)v3);
            }
            /*SL:744*/if (v3 instanceof NBTTagList) {
                a1.func_74782_a(v2, (NBTBase)v3);
            }
        }
        /*SL:746*/if (v1 instanceof NBTTagList) {
            final NBTTagList a2 = /*EL:748*/(NBTTagList)v1;
            /*SL:749*/if (v3 instanceof String) {
                a2.func_74742_a((NBTBase)new NBTTagString((String)v3));
            }
            /*SL:750*/if (v3 instanceof Integer) {
                a2.func_74742_a((NBTBase)new NBTTagInt((int)v3));
            }
            /*SL:751*/if (v3 instanceof Float) {
                a2.func_74742_a((NBTBase)new NBTTagFloat((float)v3));
            }
            /*SL:752*/if (v3 instanceof Double) {
                a2.func_74742_a((NBTBase)new NBTTagDouble((double)v3));
            }
            /*SL:753*/if (v3 instanceof Short) {
                a2.func_74742_a((NBTBase)new NBTTagShort((short)v3));
            }
            /*SL:754*/if (v3 instanceof Byte) {
                a2.func_74742_a((NBTBase)new NBTTagByte((byte)v3));
            }
            /*SL:755*/if (v3 instanceof int[]) {
                a2.func_74742_a((NBTBase)new NBTTagIntArray((int[])v3));
            }
            /*SL:756*/if (v3 instanceof byte[]) {
                a2.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v3));
            }
            /*SL:757*/if (v3 instanceof NBTTagCompound) {
                a2.func_74742_a((NBTBase)v3);
            }
            /*SL:758*/if (v3 instanceof NBTTagList) {
                a2.func_74742_a((NBTBase)v3);
            }
        }
    }
    
    static {
        instance = new ExpressionParserOld();
        ExpressionParserOld.hashVariables = new String[] { "#randomPotionDamage", "#randomPotionEffect", "#randomSpawnEggDamage", "#bPosX", "#bPosY", "#bPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#playerName", "#playerDirect", "#playerYaw", "#playerPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation" };
        ExpressionParserOld.propertyStrings = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(" };
    }
    
    public class ExpressionParserException extends Exception
    {
        public ExpressionParserException(final String a2, final Exception a3) {
            super("Invalid input string: " + a2 + ((a3 instanceof ScriptException) ? ("\nScript Error: " + a3.getMessage()) : ""));
        }
        
        public ExpressionParserException() {
        }
    }
}
