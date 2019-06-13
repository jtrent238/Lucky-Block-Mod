package mod.lucky.drop.value;

import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.BlockPos;
import mod.lucky.structure.StructureUtils;
import net.minecraft.util.Vec3;
import net.minecraft.util.MathHelper;
import mod.lucky.util.LuckyFunction;
import mod.lucky.drop.func.DropProcessData;
import javax.script.ScriptEngine;
import java.util.Random;

public class HashVariables
{
    private static String[] hashVariables;
    private static String[] bracketHashVariables;
    private static Random random;
    private static ScriptEngine scriptEngine;
    
    public static String processString(String a2, final DropProcessData v1) {
        /*SL:29*/if (!a2.contains("#") && !a2.contains("$")) {
            return a2;
        }
        /*SL:31*/a2 = processBracketHash(a2, v1);
        /*SL:33*/a2 = a2.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:34*/a2 = a2.replace("#randPotionParticle", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:35*/a2 = a2.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEgg()));
        /*SL:37*/if (v1 != null) {
            /*SL:39*/if (v1.getHarvestPos() != null) {
                /*SL:41*/a2 = a2.replace("#bPosX", String.valueOf(v1.getHarvestPos().func_177958_n()));
                /*SL:42*/a2 = a2.replace("#bPosY", String.valueOf(v1.getHarvestPos().func_177956_o()));
                /*SL:43*/a2 = a2.replace("#bPosZ", String.valueOf(v1.getHarvestPos().func_177952_p()));
                /*SL:44*/a2 = a2.replace("#bPos", String.valueOf("(" + v1.getHarvestPos().func_177958_n() + "," + v1.getHarvestPos().func_177956_o() + "," + v1.getHarvestPos().func_177952_p() + ")"));
            }
            /*SL:47*/if (v1.getWorld() != null) {
                /*SL:49*/a2 = a2.replace("#time", String.valueOf(v1.getWorld().func_72820_D()));
            }
            /*SL:52*/if (v1.getPlayer() != null) {
                /*SL:54*/a2 = a2.replace("#pPosX", String.valueOf(Math.floor(v1.getPlayer().field_70165_t)));
                /*SL:55*/a2 = a2.replace("#pPosY", String.valueOf(Math.floor(v1.getPlayer().field_70163_u)));
                /*SL:56*/a2 = a2.replace("#pPosZ", String.valueOf(Math.floor(v1.getPlayer().field_70161_v)));
                /*SL:57*/a2 = a2.replace("#pPos", String.valueOf("(" + Math.floor(v1.getPlayer().field_70165_t) + "," + Math.floor(v1.getPlayer().field_70163_u) + "," + Math.floor(v1.getPlayer().field_70161_v) + ")"));
                /*SL:58*/a2 = a2.replace("#pName", v1.getPlayer().func_145748_c_().func_150260_c());
                int a3 = /*EL:59*/(int)Math.round((v1.getPlayer().field_70177_z + 180.0) / 90.0) % 4;
                /*SL:60*/if (a3 < 0) {
                    a3 += 4;
                }
                /*SL:61*/a2 = a2.replace("#pDirect", String.valueOf(a3));
                /*SL:62*/a2 = a2.replace("#pYaw", String.valueOf(v1.getPlayer().field_70177_z));
                /*SL:63*/a2 = a2.replace("#pPitch", String.valueOf(v1.getPlayer().field_70125_A));
            }
        }
        /*SL:67*/a2 = a2.replace("'#'", "#");
        /*SL:68*/a2 = a2.replace("'$'", "'§'");
        /*SL:69*/a2 = a2.replace("$", "§");
        /*SL:70*/a2 = a2.replace("'§'", "$");
        /*SL:72*/return a2;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:77*/a1 = a1.replace("\\\\t", "\t");
        /*SL:78*/a1 = a1.replace("\\\\b", "\b");
        /*SL:79*/a1 = a1.replace("\\\\n", "\n");
        /*SL:80*/a1 = a1.replace("\\\\r", "\r");
        /*SL:81*/a1 = a1.replace("\\\\f", "\f");
        /*SL:82*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:89*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:90*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:91*/v-7.substring(0, v-7.indexOf(40));
            /*SL:93*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:95*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:96*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:97*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:99*/if (b) {
                    final float a1 = /*EL:101*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:102*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = /*EL:103*/MathHelper.func_151240_a(HashVariables.random, a1, a2);
                    /*SL:104*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:105*/return String.valueOf(v1);
                }
                final int n = /*EL:109*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:110*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:111*/v2 - n + 1) + n;
                /*SL:112*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:113*/return String.valueOf(v3);
            }
            else {
                /*SL:116*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:118*/splitBracketString.length);
                    /*SL:119*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:121*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:123*/0;
                    int n = /*EL:124*/0;
                    /*SL:125*/if (splitBracketString.length == 1) {
                        /*SL:127*/n = /*EL:128*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:130*/ if (splitBracketString.length == 2) {
                        /*SL:132*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:133*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:135*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:136*/360);
                    final int v4 = /*EL:137*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:138*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:139*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:141*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:143*/splitBracketString[0], v-6));
                    String a3 = /*EL:144*/String.valueOf(eval);
                    /*SL:146*/if (eval instanceof Double && a3.endsWith(".0")) {
                        a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:147*/a3 = String.valueOf(eval);
                    }
                    /*SL:148*/return fixBackslash(a3);
                }
                /*SL:150*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || substring2.equals("#sPosY") || substring2.equals("#sPosZ") || substring2.equals("#sPos"))) {
                    final boolean b = /*EL:152*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]) || DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:153*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:154*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:155*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:157*/v-6.getDropProperties();
                    final Structure v6 = /*EL:158*/v-6.getBlock().getDropProcessor().getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:159*/if (v6 == null) {
                        return "";
                    }
                    final Vec3 v7 = /*EL:160*/new Vec3(dropProperties.getPropertyInt("posX") + 0.5, (double)dropProperties.getPropertyInt("posY"), dropProperties.getPropertyInt("posZ") + 0.5);
                    final int v4 = /*EL:161*/dropProperties.getPropertyInt("rotation");
                    /*SL:163*/if (b) {
                        final Vec3 v8 = /*EL:165*/new Vec3((double)ValueParser.getFloat(splitBracketString[0], v-6), (double)ValueParser.getFloat(splitBracketString[1], v-6), (double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3 v9 = /*EL:166*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), v7, v4);
                        /*SL:168*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:169*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:170*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:171*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:175*/new BlockPos((int)ValueParser.getInteger(splitBracketString[0], v-6), (int)ValueParser.getInteger(splitBracketString[1], v-6), (int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:176*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), v7, v4);
                        /*SL:177*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:178*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:179*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:180*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + Math.round(v11.func_177958_n()) + "," + Math.round(v11.func_177956_o()) + "," + Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:183*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:185*/splitBracketString[0];
                    /*SL:186*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:191*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:192*/ex.printStackTrace();
        }
        /*SL:195*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:200*/new ArrayList<Integer>();
        int n = /*EL:201*/0;
        while (true) {
            int n2 = /*EL:204*/-1;
            int n3 = /*EL:205*/-1;
            /*SL:209*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:211*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:212*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:214*/n2 = a2;
                    /*SL:215*/n3 = a2;
                }
            }
            /*SL:218*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:219*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:220*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:221*/list.add(n, n2);
            String substring = /*EL:223*/"";
            int n5 = /*EL:224*/-1;
            final char[] charArray = /*EL:226*/v-18.toCharArray();
            int n6 = /*EL:227*/0;
            boolean b = /*EL:228*/false;
            boolean b2 = /*EL:229*/false;
            final char[] array = /*EL:231*/new char[charArray.length];
            int n7 = /*EL:232*/0;
            /*SL:234*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:236*/i > 0 && charArray[i - 1] == '\\';
                /*SL:237*/array[n7] = charArray[i];
                /*SL:239*/if (i >= list.get(n) + n4) {
                    /*SL:241*/if (!b3) {
                        /*SL:243*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:244*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:246*/++n6;
                        }
                        /*SL:248*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:250*/--n6;
                        }
                        /*SL:252*/if (n6 == 0) {
                            final char[] v0 = /*EL:254*/new char[n7 + 1];
                            /*SL:255*/for (int v = 0; v < v0.length; ++v) {
                                /*SL:256*/v0[v] = array[v];
                            }
                            /*SL:257*/substring = new String(v0);
                            /*SL:258*/n5 = i;
                            /*SL:259*/break;
                        }
                    }
                    /*SL:264*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:266*/--n7;
                        /*SL:267*/array[n7] = '\"';
                        final boolean v2 = /*EL:268*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:269*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:274*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:276*/++n7;
            }
            /*SL:278*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:280*/substring, v-17);
            final String substring2 = /*EL:281*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:282*/v-18.substring(n5 + 1, v-18.length());
            /*SL:283*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:285*/++n;
        }
        /*SL:287*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:292*/for (final String a1 : HashVariables.hashVariables) {
            /*SL:293*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:294*/for (final String v1 : HashVariables.bracketHashVariables) {
            /*SL:295*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:296*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:297*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:298*/return false;
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotionParticle", "#randSpawnEggDamage", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pName", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
