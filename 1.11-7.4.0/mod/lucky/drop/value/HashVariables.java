package mod.lucky.drop.value;

import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.math.BlockPos;
import mod.lucky.structure.StructureUtils;
import mod.lucky.Lucky;
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
        /*SL:30*/if (!v-2.contains("#") && !v-2.contains("$")) {
            return v-2;
        }
        /*SL:32*/v-2 = processBracketHash(v-2, v-1);
        /*SL:34*/v-2 = v-2.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:35*/v-2 = v-2.replace("#randPotionParticle", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:36*/v-2 = v-2.replace("#randPotion", LuckyFunction.getRandomPotionName());
        /*SL:37*/v-2 = v-2.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEggID()));
        /*SL:38*/v-2 = v-2.replace("#randSpawnEgg", LuckyFunction.getRandomMobEggName());
        /*SL:40*/if (v-1 != null) {
            final Vec3d v0 = /*EL:42*/v-1.getHarvestPos();
            /*SL:43*/v-2 = v-2.replace("#bPosX", String.valueOf(Math.floor(v0.field_72450_a)));
            /*SL:44*/v-2 = v-2.replace("#bPosY", String.valueOf(Math.floor(v0.field_72448_b)));
            /*SL:45*/v-2 = v-2.replace("#bPosZ", String.valueOf(Math.floor(v0.field_72449_c)));
            /*SL:46*/v-2 = v-2.replace("#bPos", String.valueOf("(" + Math.floor(v0.field_72450_a) + "," + Math.floor(v0.field_72448_b) + "," + Math.floor(v0.field_72449_c) + ")"));
            /*SL:48*/v-2 = v-2.replace("#bExactPosX", String.valueOf(v0.field_72450_a));
            /*SL:49*/v-2 = v-2.replace("#bExactPosY", String.valueOf(v0.field_72448_b));
            /*SL:50*/v-2 = v-2.replace("#bExactPosZ", String.valueOf(v0.field_72449_c));
            /*SL:51*/v-2 = v-2.replace("#bExactPos", String.valueOf("(" + v0.field_72450_a + "," + v0.field_72448_b + "," + v0.field_72449_c + ")"));
            /*SL:53*/if (v-1.getHitEntity() != null) {
                final Vec3d a1 = /*EL:55*/v-1.getHitEntity().func_174791_d();
                /*SL:56*/v-2 = v-2.replace("#ePosX", String.valueOf(Math.floor(a1.field_72450_a)));
                /*SL:57*/v-2 = v-2.replace("#ePosY", String.valueOf(Math.floor(a1.field_72448_b)));
                /*SL:58*/v-2 = v-2.replace("#ePosZ", String.valueOf(Math.floor(a1.field_72449_c)));
                /*SL:59*/v-2 = v-2.replace("#ePos", String.valueOf("(" + Math.floor(a1.field_72450_a) + "," + Math.floor(a1.field_72448_b) + "," + Math.floor(a1.field_72449_c) + ")"));
                /*SL:61*/v-2 = v-2.replace("#eExactPosX", String.valueOf(a1.field_72450_a));
                /*SL:62*/v-2 = v-2.replace("#eExactPosY", String.valueOf(a1.field_72448_b));
                /*SL:63*/v-2 = v-2.replace("#eExactPosZ", String.valueOf(a1.field_72449_c));
                /*SL:64*/v-2 = v-2.replace("#eExactPos", String.valueOf("(" + a1.field_72450_a + "," + a1.field_72448_b + "," + a1.field_72449_c + ")"));
            }
            /*SL:67*/if (v-1.getWorld() != null) {
                /*SL:69*/v-2 = v-2.replace("#time", String.valueOf(v-1.getWorld().func_72820_D()));
            }
            /*SL:72*/if (v-1.getPlayer() != null) {
                /*SL:74*/v-2 = v-2.replace("#pPosX", String.valueOf(Math.floor(v-1.getPlayer().field_70165_t)));
                /*SL:75*/v-2 = v-2.replace("#pPosY", String.valueOf(Math.floor(v-1.getPlayer().field_70163_u)));
                /*SL:76*/v-2 = v-2.replace("#pPosZ", String.valueOf(Math.floor(v-1.getPlayer().field_70161_v)));
                /*SL:77*/v-2 = v-2.replace("#pPos", String.valueOf("(" + Math.floor(v-1.getPlayer().field_70165_t) + "," + Math.floor(v-1.getPlayer().field_70163_u) + "," + Math.floor(v-1.getPlayer().field_70161_v) + ")"));
                /*SL:79*/v-2 = v-2.replace("#pExactPosX", String.valueOf(v-1.getPlayer().field_70165_t));
                /*SL:80*/v-2 = v-2.replace("#pExactPosY", String.valueOf(v-1.getPlayer().field_70163_u));
                /*SL:81*/v-2 = v-2.replace("#pExactPosZ", String.valueOf(v-1.getPlayer().field_70161_v));
                /*SL:82*/v-2 = v-2.replace("#pExactPos", String.valueOf("(" + v-1.getPlayer().field_70165_t + "," + v-1.getPlayer().field_70163_u + "," + v-1.getPlayer().field_70161_v + ")"));
                /*SL:84*/v-2 = v-2.replace("#pName", v-1.getPlayer().func_145748_c_().func_150260_c());
                /*SL:85*/v-2 = v-2.replace("#pUUID", v-1.getPlayer().func_110124_au().toString());
                int v = /*EL:86*/(int)Math.round((v-1.getPlayer().func_70079_am() + 180.0) / 90.0) % 4;
                /*SL:87*/if (v < 0) {
                    v += 4;
                }
                /*SL:88*/v-2 = v-2.replace("#pDirect", String.valueOf(v));
                /*SL:89*/v-2 = v-2.replace("#pYaw", String.valueOf(v-1.getPlayer().func_70079_am()));
                /*SL:90*/v-2 = v-2.replace("#pPitch", String.valueOf(v-1.getPlayer().field_70125_A));
                final EntityTippedArrow v2;
                /*SL:93*/if (v-1.getPlayer() instanceof EntityLivingBase) {
                    final EntityTippedArrow a2 = new EntityTippedArrow(v-1.getWorld(), (EntityLivingBase)v-1.getPlayer());
                }
                else {
                    /*SL:94*/v2 = new EntityTippedArrow(v-1.getWorld(), v-1.getPlayer().field_70165_t, v-1.getPlayer().field_70163_u, v-1.getPlayer().field_70161_v);
                }
                /*SL:95*/v-2 = v-2.replace("#bowPosX", String.valueOf(v2.func_174791_d().field_72450_a));
                /*SL:96*/v-2 = v-2.replace("#bowPosY", String.valueOf(v2.func_174791_d().field_72448_b));
                /*SL:97*/v-2 = v-2.replace("#bowPosZ", String.valueOf(v2.func_174791_d().field_72449_c));
                /*SL:98*/v-2 = v-2.replace("#bowPos", String.valueOf("(" + v2.func_174791_d().field_72450_a + "," + v2.func_174791_d().field_72448_b + "," + v2.func_174791_d().field_72449_c + ")"));
            }
        }
        /*SL:102*/v-2 = v-2.replace("'#'", "#");
        /*SL:103*/v-2 = v-2.replace("'@'", "@");
        /*SL:104*/v-2 = v-2.replace("'$'", "'§'");
        /*SL:105*/v-2 = v-2.replace("$", "§");
        /*SL:106*/v-2 = v-2.replace("'§'", "$");
        /*SL:108*/return v-2;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:113*/a1 = a1.replace("\\\\t", "\t");
        /*SL:114*/a1 = a1.replace("\\\\b", "\b");
        /*SL:115*/a1 = a1.replace("\\\\n", "\n");
        /*SL:116*/a1 = a1.replace("\\\\r", "\r");
        /*SL:117*/a1 = a1.replace("\\\\f", "\f");
        /*SL:118*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:125*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:126*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:127*/v-7.substring(0, v-7.indexOf(40));
            /*SL:129*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:131*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:132*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:133*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:135*/if (b) {
                    final float a1 = /*EL:137*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:138*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = randomFloatClamp(HashVariables.random, /*EL:139*/a1, a2);
                    /*SL:140*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:141*/return String.valueOf(v1);
                }
                final int n = /*EL:145*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:146*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:147*/v2 - n + 1) + n;
                /*SL:148*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:149*/return String.valueOf(v3);
            }
            else {
                /*SL:152*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:154*/splitBracketString.length);
                    /*SL:155*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:157*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:159*/0;
                    int n = /*EL:160*/0;
                    /*SL:161*/if (splitBracketString.length == 1) {
                        /*SL:163*/n = /*EL:164*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:166*/ if (splitBracketString.length == 2) {
                        /*SL:168*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:169*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:171*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:172*/360);
                    final int v4 = /*EL:173*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:174*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:175*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:177*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:179*/splitBracketString[0], v-6));
                    String a3 = /*EL:180*/String.valueOf(eval);
                    /*SL:182*/if (eval instanceof Double && a3.endsWith(".0")) {
                        a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:183*/a3 = String.valueOf(eval);
                    }
                    /*SL:184*/return fixBackslash(a3);
                }
                /*SL:186*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || substring2.equals("#sPosY") || substring2.equals("#sPosZ") || substring2.equals("#sPos"))) {
                    final boolean b = /*EL:188*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]) || DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:189*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:190*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:191*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:193*/v-6.getDropProperties();
                    final Structure v6 = /*EL:194*/Lucky.getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:195*/if (v6 == null) {
                        return "";
                    }
                    final Vec3d v7 = /*EL:196*/new Vec3d((double)dropProperties.getPropertyFloat("posX"), (double)dropProperties.getPropertyFloat("posY"), (double)dropProperties.getPropertyFloat("posZ"));
                    final int v4 = /*EL:197*/dropProperties.getPropertyInt("rotation");
                    /*SL:199*/if (b) {
                        final Vec3d v8 = /*EL:201*/new Vec3d((double)ValueParser.getFloat(splitBracketString[0], v-6), (double)ValueParser.getFloat(splitBracketString[1], v-6), (double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3d v9 = /*EL:202*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), v7, v4);
                        /*SL:204*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:205*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:206*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:207*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:211*/new BlockPos((int)ValueParser.getInteger(splitBracketString[0], v-6), (int)ValueParser.getInteger(splitBracketString[1], v-6), (int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:212*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), v7, v4);
                        /*SL:213*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:214*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:215*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:216*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + Math.round(v11.func_177958_n()) + "," + Math.round(v11.func_177956_o()) + "," + Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:219*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:221*/splitBracketString[0];
                    /*SL:222*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:227*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:228*/ex.printStackTrace();
        }
        /*SL:231*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:236*/new ArrayList<Integer>();
        int n = /*EL:237*/0;
        while (true) {
            int n2 = /*EL:240*/-1;
            int n3 = /*EL:241*/-1;
            /*SL:245*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:247*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:248*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:250*/n2 = a2;
                    /*SL:251*/n3 = a2;
                }
            }
            /*SL:254*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:255*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:256*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:257*/list.add(n, n2);
            String substring = /*EL:259*/"";
            int n5 = /*EL:260*/-1;
            final char[] charArray = /*EL:262*/v-18.toCharArray();
            int n6 = /*EL:263*/0;
            boolean b = /*EL:264*/false;
            boolean b2 = /*EL:265*/false;
            final char[] array = /*EL:267*/new char[charArray.length];
            int n7 = /*EL:268*/0;
            /*SL:270*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:272*/i > 0 && charArray[i - 1] == '\\';
                /*SL:273*/array[n7] = charArray[i];
                /*SL:275*/if (i >= list.get(n) + n4) {
                    /*SL:277*/if (!b3) {
                        /*SL:279*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:280*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:282*/++n6;
                        }
                        /*SL:284*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:286*/--n6;
                        }
                        /*SL:288*/if (n6 == 0) {
                            final char[] v0 = /*EL:290*/new char[n7 + 1];
                            /*SL:291*/for (int v = 0; v < v0.length; ++v) {
                                /*SL:292*/v0[v] = array[v];
                            }
                            /*SL:293*/substring = new String(v0);
                            /*SL:294*/n5 = i;
                            /*SL:295*/break;
                        }
                    }
                    /*SL:300*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:302*/--n7;
                        /*SL:303*/array[n7] = '\"';
                        final boolean v2 = /*EL:304*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:305*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:310*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:312*/++n7;
            }
            /*SL:314*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:316*/substring, v-17);
            final String substring2 = /*EL:317*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:318*/v-18.substring(n5 + 1, v-18.length());
            /*SL:319*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:321*/++n;
        }
        /*SL:323*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:328*/for (final String a1 : HashVariables.hashVariables) {
            /*SL:329*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:330*/for (final String v1 : HashVariables.bracketHashVariables) {
            /*SL:331*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:332*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:333*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:334*/return false;
    }
    
    public static String autoCancelHash(String a1) {
        /*SL:340*/a1 = a1.replace("#", "'#'");
        /*SL:341*/a1 = a1.replace("''#''", "'#'");
        /*SL:342*/a1 = a1.replace("['#']", "#");
        /*SL:343*/return a1;
    }
    
    public static float randomFloatClamp(final Random a1, final float a2, final float a3) {
        /*SL:348*/return (float)(a2 + (a3 - a2) * a1.nextDouble());
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotion", "#randPotionParticle", "#randSpawnEggDamage", "#randSpawnEgg", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#bExactPosX", "#bExactPosY", "#bExactPosZ", "#bExactPos", "#ePosX", "#ePosY", "#ePosZ", "#ePos", "#eExactPosX", "#eExactPosY", "#eExactPosZ", "#eExactPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pExactPosX", "#pExactPosY", "#pExactPosZ", "#pExactPos", "#pName", "#pUUID", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation", "#bowPosX", "#bowPosY", "#bowPosZ", "#bowPos" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(", "#randPotionDamage(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
