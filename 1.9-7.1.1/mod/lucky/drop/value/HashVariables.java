package mod.lucky.drop.value;

import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.math.BlockPos;
import mod.lucky.structure.StructureUtils;
import mod.lucky.Lucky;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.EntityLivingBase;
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
    
    public static String processString(String v-2, final DropProcessData v-1) {
        /*SL:31*/if (!v-2.contains("#") && !v-2.contains("$")) {
            return v-2;
        }
        /*SL:33*/v-2 = processBracketHash(v-2, v-1);
        /*SL:35*/v-2 = v-2.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:36*/v-2 = v-2.replace("#randPotionParticle", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:37*/v-2 = v-2.replace("#randPotion", LuckyFunction.getRandomPotionName());
        /*SL:38*/v-2 = v-2.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEggID()));
        /*SL:39*/v-2 = v-2.replace("#randSpawnEgg", LuckyFunction.getRandomMobEggName());
        /*SL:41*/if (v-1 != null) {
            final Vec3d v0 = /*EL:43*/v-1.getHarvestPos();
            /*SL:44*/v-2 = v-2.replace("#bPosX", String.valueOf(Math.floor(v0.field_72450_a)));
            /*SL:45*/v-2 = v-2.replace("#bPosY", String.valueOf(Math.floor(v0.field_72448_b)));
            /*SL:46*/v-2 = v-2.replace("#bPosZ", String.valueOf(Math.floor(v0.field_72449_c)));
            /*SL:47*/v-2 = v-2.replace("#bPos", String.valueOf("(" + Math.floor(v0.field_72450_a) + "," + Math.floor(v0.field_72448_b) + "," + Math.floor(v0.field_72449_c) + ")"));
            /*SL:49*/v-2 = v-2.replace("#bExactPosX", String.valueOf(v0.field_72450_a));
            /*SL:50*/v-2 = v-2.replace("#bExactPosY", String.valueOf(v0.field_72448_b));
            /*SL:51*/v-2 = v-2.replace("#bExactPosZ", String.valueOf(v0.field_72449_c));
            /*SL:52*/v-2 = v-2.replace("#bExactPos", String.valueOf("(" + v0.field_72450_a + "," + v0.field_72448_b + "," + v0.field_72449_c + ")"));
            /*SL:54*/if (v-1.getHitEntity() != null) {
                final Vec3d a1 = /*EL:56*/v-1.getHitEntity().func_174791_d();
                /*SL:57*/v-2 = v-2.replace("#ePosX", String.valueOf(Math.floor(a1.field_72450_a)));
                /*SL:58*/v-2 = v-2.replace("#ePosY", String.valueOf(Math.floor(a1.field_72448_b)));
                /*SL:59*/v-2 = v-2.replace("#ePosZ", String.valueOf(Math.floor(a1.field_72449_c)));
                /*SL:60*/v-2 = v-2.replace("#ePos", String.valueOf("(" + Math.floor(a1.field_72450_a) + "," + Math.floor(a1.field_72448_b) + "," + Math.floor(a1.field_72449_c) + ")"));
                /*SL:62*/v-2 = v-2.replace("#eExactPosX", String.valueOf(a1.field_72450_a));
                /*SL:63*/v-2 = v-2.replace("#eExactPosY", String.valueOf(a1.field_72448_b));
                /*SL:64*/v-2 = v-2.replace("#eExactPosZ", String.valueOf(a1.field_72449_c));
                /*SL:65*/v-2 = v-2.replace("#eExactPos", String.valueOf("(" + a1.field_72450_a + "," + a1.field_72448_b + "," + a1.field_72449_c + ")"));
            }
            /*SL:68*/if (v-1.getWorld() != null) {
                /*SL:70*/v-2 = v-2.replace("#time", String.valueOf(v-1.getWorld().func_72820_D()));
            }
            /*SL:73*/if (v-1.getPlayer() != null) {
                /*SL:75*/v-2 = v-2.replace("#pPosX", String.valueOf(Math.floor(v-1.getPlayer().field_70165_t)));
                /*SL:76*/v-2 = v-2.replace("#pPosY", String.valueOf(Math.floor(v-1.getPlayer().field_70163_u)));
                /*SL:77*/v-2 = v-2.replace("#pPosZ", String.valueOf(Math.floor(v-1.getPlayer().field_70161_v)));
                /*SL:78*/v-2 = v-2.replace("#pPos", String.valueOf("(" + Math.floor(v-1.getPlayer().field_70165_t) + "," + Math.floor(v-1.getPlayer().field_70163_u) + "," + Math.floor(v-1.getPlayer().field_70161_v) + ")"));
                /*SL:80*/v-2 = v-2.replace("#pExactPosX", String.valueOf(v-1.getPlayer().field_70165_t));
                /*SL:81*/v-2 = v-2.replace("#pExactPosY", String.valueOf(v-1.getPlayer().field_70163_u));
                /*SL:82*/v-2 = v-2.replace("#pExactPosZ", String.valueOf(v-1.getPlayer().field_70161_v));
                /*SL:83*/v-2 = v-2.replace("#pExactPos", String.valueOf("(" + v-1.getPlayer().field_70165_t + "," + v-1.getPlayer().field_70163_u + "," + v-1.getPlayer().field_70161_v + ")"));
                /*SL:85*/v-2 = v-2.replace("#pName", v-1.getPlayer().func_145748_c_().func_150260_c());
                /*SL:86*/v-2 = v-2.replace("#pUUID", v-1.getPlayer().func_110124_au().toString());
                int v = /*EL:87*/(int)Math.round((v-1.getPlayer().func_70079_am() + 180.0) / 90.0) % 4;
                /*SL:88*/if (v < 0) {
                    v += 4;
                }
                /*SL:89*/v-2 = v-2.replace("#pDirect", String.valueOf(v));
                /*SL:90*/v-2 = v-2.replace("#pYaw", String.valueOf(v-1.getPlayer().func_70079_am()));
                /*SL:91*/v-2 = v-2.replace("#pPitch", String.valueOf(v-1.getPlayer().field_70125_A));
                final EntityTippedArrow v2;
                /*SL:94*/if (v-1.getPlayer() instanceof EntityLivingBase) {
                    final EntityTippedArrow a2 = new EntityTippedArrow(v-1.getWorld(), (EntityLivingBase)v-1.getPlayer());
                }
                else {
                    /*SL:95*/v2 = new EntityTippedArrow(v-1.getWorld(), v-1.getPlayer().field_70165_t, v-1.getPlayer().field_70163_u, v-1.getPlayer().field_70161_v);
                }
                /*SL:96*/v-2 = v-2.replace("#bowPosX", String.valueOf(v2.func_174791_d().field_72450_a));
                /*SL:97*/v-2 = v-2.replace("#bowPosY", String.valueOf(v2.func_174791_d().field_72448_b));
                /*SL:98*/v-2 = v-2.replace("#bowPosZ", String.valueOf(v2.func_174791_d().field_72449_c));
                /*SL:99*/v-2 = v-2.replace("#bowPos", String.valueOf("(" + v2.func_174791_d().field_72450_a + "," + v2.func_174791_d().field_72448_b + "," + v2.func_174791_d().field_72449_c + ")"));
            }
        }
        /*SL:103*/v-2 = v-2.replace("'#'", "#");
        /*SL:104*/v-2 = v-2.replace("'@'", "@");
        /*SL:105*/v-2 = v-2.replace("'$'", "'§'");
        /*SL:106*/v-2 = v-2.replace("$", "§");
        /*SL:107*/v-2 = v-2.replace("'§'", "$");
        /*SL:109*/return v-2;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:114*/a1 = a1.replace("\\\\t", "\t");
        /*SL:115*/a1 = a1.replace("\\\\b", "\b");
        /*SL:116*/a1 = a1.replace("\\\\n", "\n");
        /*SL:117*/a1 = a1.replace("\\\\r", "\r");
        /*SL:118*/a1 = a1.replace("\\\\f", "\f");
        /*SL:119*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:126*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:127*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:128*/v-7.substring(0, v-7.indexOf(40));
            /*SL:130*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:132*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:133*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:134*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:136*/if (b) {
                    final float a1 = /*EL:138*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:139*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = /*EL:140*/MathHelper.func_151240_a(HashVariables.random, a1, a2);
                    /*SL:141*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:142*/return String.valueOf(v1);
                }
                final int n = /*EL:146*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:147*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:148*/v2 - n + 1) + n;
                /*SL:149*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:150*/return String.valueOf(v3);
            }
            else {
                /*SL:153*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:155*/splitBracketString.length);
                    /*SL:156*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:158*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:160*/0;
                    int n = /*EL:161*/0;
                    /*SL:162*/if (splitBracketString.length == 1) {
                        /*SL:164*/n = /*EL:165*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:167*/ if (splitBracketString.length == 2) {
                        /*SL:169*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:170*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:172*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:173*/360);
                    final int v4 = /*EL:174*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:175*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:176*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:178*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:180*/splitBracketString[0], v-6));
                    String a3 = /*EL:181*/String.valueOf(eval);
                    /*SL:183*/if (eval instanceof Double && a3.endsWith(".0")) {
                        a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:184*/a3 = String.valueOf(eval);
                    }
                    /*SL:185*/return fixBackslash(a3);
                }
                /*SL:187*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || substring2.equals("#sPosY") || substring2.equals("#sPosZ") || substring2.equals("#sPos"))) {
                    final boolean b = /*EL:189*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]) || DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:190*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:191*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:192*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:194*/v-6.getDropProperties();
                    final Structure v6 = /*EL:195*/Lucky.getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:196*/if (v6 == null) {
                        return "";
                    }
                    final Vec3d v7 = /*EL:197*/new Vec3d((double)dropProperties.getPropertyFloat("posX"), (double)dropProperties.getPropertyFloat("posY"), (double)dropProperties.getPropertyFloat("posZ"));
                    final int v4 = /*EL:198*/dropProperties.getPropertyInt("rotation");
                    /*SL:200*/if (b) {
                        final Vec3d v8 = /*EL:202*/new Vec3d((double)ValueParser.getFloat(splitBracketString[0], v-6), (double)ValueParser.getFloat(splitBracketString[1], v-6), (double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3d v9 = /*EL:203*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), v7, v4);
                        /*SL:205*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:206*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:207*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:208*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:212*/new BlockPos((int)ValueParser.getInteger(splitBracketString[0], v-6), (int)ValueParser.getInteger(splitBracketString[1], v-6), (int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:213*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), v7, v4);
                        /*SL:214*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:215*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:216*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:217*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + Math.round(v11.func_177958_n()) + "," + Math.round(v11.func_177956_o()) + "," + Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:220*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:222*/splitBracketString[0];
                    /*SL:223*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:228*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:229*/ex.printStackTrace();
        }
        /*SL:232*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:237*/new ArrayList<Integer>();
        int n = /*EL:238*/0;
        while (true) {
            int n2 = /*EL:241*/-1;
            int n3 = /*EL:242*/-1;
            /*SL:246*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:248*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:249*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:251*/n2 = a2;
                    /*SL:252*/n3 = a2;
                }
            }
            /*SL:255*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:256*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:257*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:258*/list.add(n, n2);
            String substring = /*EL:260*/"";
            int n5 = /*EL:261*/-1;
            final char[] charArray = /*EL:263*/v-18.toCharArray();
            int n6 = /*EL:264*/0;
            boolean b = /*EL:265*/false;
            boolean b2 = /*EL:266*/false;
            final char[] array = /*EL:268*/new char[charArray.length];
            int n7 = /*EL:269*/0;
            /*SL:271*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:273*/i > 0 && charArray[i - 1] == '\\';
                /*SL:274*/array[n7] = charArray[i];
                /*SL:276*/if (i >= list.get(n) + n4) {
                    /*SL:278*/if (!b3) {
                        /*SL:280*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:281*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:283*/++n6;
                        }
                        /*SL:285*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:287*/--n6;
                        }
                        /*SL:289*/if (n6 == 0) {
                            final char[] v0 = /*EL:291*/new char[n7 + 1];
                            /*SL:292*/for (int v = 0; v < v0.length; ++v) {
                                /*SL:293*/v0[v] = array[v];
                            }
                            /*SL:294*/substring = new String(v0);
                            /*SL:295*/n5 = i;
                            /*SL:296*/break;
                        }
                    }
                    /*SL:301*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:303*/--n7;
                        /*SL:304*/array[n7] = '\"';
                        final boolean v2 = /*EL:305*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:306*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:311*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:313*/++n7;
            }
            /*SL:315*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:317*/substring, v-17);
            final String substring2 = /*EL:318*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:319*/v-18.substring(n5 + 1, v-18.length());
            /*SL:320*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:322*/++n;
        }
        /*SL:324*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:329*/for (final String a1 : HashVariables.hashVariables) {
            /*SL:330*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:331*/for (final String v1 : HashVariables.bracketHashVariables) {
            /*SL:332*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:333*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:334*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:335*/return false;
    }
    
    public static String autoCancelHash(String a1) {
        /*SL:341*/a1 = a1.replace("#", "'#'");
        /*SL:342*/a1 = a1.replace("''#''", "'#'");
        /*SL:343*/a1 = a1.replace("['#']", "#");
        /*SL:344*/return a1;
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotion", "#randPotionParticle", "#randSpawnEggDamage", "#randSpawnEgg", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#bExactPosX", "#bExactPosY", "#bExactPosZ", "#bExactPos", "#ePosX", "#ePosY", "#ePosZ", "#ePos", "#eExactPosX", "#eExactPosY", "#eExactPosZ", "#eExactPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pExactPosX", "#pExactPosY", "#pExactPosZ", "#pExactPos", "#pName", "#pUUID", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation", "#bowPosX", "#bowPosY", "#bowPosZ", "#bowPos" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(", "#randPotionDamage(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
