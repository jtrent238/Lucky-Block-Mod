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
        /*SL:29*/if (!v-2.contains("#") && !v-2.contains("$")) {
            return v-2;
        }
        /*SL:31*/v-2 = processBracketHash(v-2, v-1);
        /*SL:33*/v-2 = v-2.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:34*/v-2 = v-2.replace("#randPotionParticle", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:35*/v-2 = v-2.replace("#randPotion", LuckyFunction.getRandomPotionName());
        /*SL:36*/v-2 = v-2.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEggID()));
        /*SL:37*/v-2 = v-2.replace("#randSpawnEgg", LuckyFunction.getRandomMobEggName());
        /*SL:39*/if (v-1 != null) {
            final Vec3d v0 = /*EL:41*/v-1.getHarvestPos();
            /*SL:42*/v-2 = v-2.replace("#bPosX", String.valueOf(Math.floor(v0.field_72450_a)));
            /*SL:43*/v-2 = v-2.replace("#bPosY", String.valueOf(Math.floor(v0.field_72448_b)));
            /*SL:44*/v-2 = v-2.replace("#bPosZ", String.valueOf(Math.floor(v0.field_72449_c)));
            /*SL:45*/v-2 = v-2.replace("#bPos", String.valueOf("(" + Math.floor(v0.field_72450_a) + "," + Math.floor(v0.field_72448_b) + "," + Math.floor(v0.field_72449_c) + ")"));
            /*SL:47*/v-2 = v-2.replace("#bExactPosX", String.valueOf(v0.field_72450_a));
            /*SL:48*/v-2 = v-2.replace("#bExactPosY", String.valueOf(v0.field_72448_b));
            /*SL:49*/v-2 = v-2.replace("#bExactPosZ", String.valueOf(v0.field_72449_c));
            /*SL:50*/v-2 = v-2.replace("#bExactPos", String.valueOf("(" + v0.field_72450_a + "," + v0.field_72448_b + "," + v0.field_72449_c + ")"));
            /*SL:52*/if (v-1.getHitEntity() != null) {
                final Vec3d a1 = /*EL:54*/v-1.getHitEntity().func_174791_d();
                /*SL:55*/v-2 = v-2.replace("#ePosX", String.valueOf(Math.floor(a1.field_72450_a)));
                /*SL:56*/v-2 = v-2.replace("#ePosY", String.valueOf(Math.floor(a1.field_72448_b)));
                /*SL:57*/v-2 = v-2.replace("#ePosZ", String.valueOf(Math.floor(a1.field_72449_c)));
                /*SL:58*/v-2 = v-2.replace("#ePos", String.valueOf("(" + Math.floor(a1.field_72450_a) + "," + Math.floor(a1.field_72448_b) + "," + Math.floor(a1.field_72449_c) + ")"));
                /*SL:60*/v-2 = v-2.replace("#eExactPosX", String.valueOf(a1.field_72450_a));
                /*SL:61*/v-2 = v-2.replace("#eExactPosY", String.valueOf(a1.field_72448_b));
                /*SL:62*/v-2 = v-2.replace("#eExactPosZ", String.valueOf(a1.field_72449_c));
                /*SL:63*/v-2 = v-2.replace("#eExactPos", String.valueOf("(" + a1.field_72450_a + "," + a1.field_72448_b + "," + a1.field_72449_c + ")"));
            }
            /*SL:66*/if (v-1.getWorld() != null) {
                /*SL:68*/v-2 = v-2.replace("#time", String.valueOf(v-1.getWorld().func_72820_D()));
            }
            /*SL:71*/if (v-1.getPlayer() != null) {
                /*SL:73*/v-2 = v-2.replace("#pPosX", String.valueOf(Math.floor(v-1.getPlayer().field_70165_t)));
                /*SL:74*/v-2 = v-2.replace("#pPosY", String.valueOf(Math.floor(v-1.getPlayer().field_70163_u)));
                /*SL:75*/v-2 = v-2.replace("#pPosZ", String.valueOf(Math.floor(v-1.getPlayer().field_70161_v)));
                /*SL:76*/v-2 = v-2.replace("#pPos", String.valueOf("(" + Math.floor(v-1.getPlayer().field_70165_t) + "," + Math.floor(v-1.getPlayer().field_70163_u) + "," + Math.floor(v-1.getPlayer().field_70161_v) + ")"));
                /*SL:78*/v-2 = v-2.replace("#pExactPosX", String.valueOf(v-1.getPlayer().field_70165_t));
                /*SL:79*/v-2 = v-2.replace("#pExactPosY", String.valueOf(v-1.getPlayer().field_70163_u));
                /*SL:80*/v-2 = v-2.replace("#pExactPosZ", String.valueOf(v-1.getPlayer().field_70161_v));
                /*SL:81*/v-2 = v-2.replace("#pExactPos", String.valueOf("(" + v-1.getPlayer().field_70165_t + "," + v-1.getPlayer().field_70163_u + "," + v-1.getPlayer().field_70161_v + ")"));
                /*SL:83*/v-2 = v-2.replace("#pName", v-1.getPlayer().func_145748_c_().func_150260_c());
                /*SL:84*/v-2 = v-2.replace("#pUUID", v-1.getPlayer().func_110124_au().toString());
                int v = /*EL:85*/(int)Math.round((v-1.getPlayer().func_70079_am() + 180.0) / 90.0) % 4;
                /*SL:86*/if (v < 0) {
                    v += 4;
                }
                /*SL:87*/v-2 = v-2.replace("#pDirect", String.valueOf(v));
                /*SL:88*/v-2 = v-2.replace("#pYaw", String.valueOf(v-1.getPlayer().func_70079_am()));
                /*SL:89*/v-2 = v-2.replace("#pPitch", String.valueOf(v-1.getPlayer().field_70125_A));
                final EntityTippedArrow v2;
                /*SL:92*/if (v-1.getPlayer() instanceof EntityLivingBase) {
                    final EntityTippedArrow a2 = new EntityTippedArrow(v-1.getWorld(), (EntityLivingBase)v-1.getPlayer());
                }
                else {
                    /*SL:93*/v2 = new EntityTippedArrow(v-1.getWorld(), v-1.getPlayer().field_70165_t, v-1.getPlayer().field_70163_u, v-1.getPlayer().field_70161_v);
                }
                /*SL:94*/v-2 = v-2.replace("#bowPosX", String.valueOf(v2.func_174791_d().field_72450_a));
                /*SL:95*/v-2 = v-2.replace("#bowPosY", String.valueOf(v2.func_174791_d().field_72448_b));
                /*SL:96*/v-2 = v-2.replace("#bowPosZ", String.valueOf(v2.func_174791_d().field_72449_c));
                /*SL:97*/v-2 = v-2.replace("#bowPos", String.valueOf("(" + v2.func_174791_d().field_72450_a + "," + v2.func_174791_d().field_72448_b + "," + v2.func_174791_d().field_72449_c + ")"));
            }
        }
        /*SL:101*/v-2 = v-2.replace("'#'", "#");
        /*SL:102*/v-2 = v-2.replace("'@'", "@");
        /*SL:103*/v-2 = v-2.replace("'$'", "'§'");
        /*SL:104*/v-2 = v-2.replace("$", "§");
        /*SL:105*/v-2 = v-2.replace("'§'", "$");
        /*SL:107*/return v-2;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:112*/a1 = a1.replace("\\\\t", "\t");
        /*SL:113*/a1 = a1.replace("\\\\b", "\b");
        /*SL:114*/a1 = a1.replace("\\\\n", "\n");
        /*SL:115*/a1 = a1.replace("\\\\r", "\r");
        /*SL:116*/a1 = a1.replace("\\\\f", "\f");
        /*SL:117*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:124*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:125*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:126*/v-7.substring(0, v-7.indexOf(40));
            /*SL:128*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:130*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:131*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:132*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:134*/if (b) {
                    final float a1 = /*EL:136*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:137*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = randomFloatClamp(HashVariables.random, /*EL:138*/a1, a2);
                    /*SL:139*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:140*/return String.valueOf(v1);
                }
                final int n = /*EL:144*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:145*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:146*/v2 - n + 1) + n;
                /*SL:147*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:148*/return String.valueOf(v3);
            }
            else {
                /*SL:151*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:153*/splitBracketString.length);
                    /*SL:154*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:156*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:158*/0;
                    int n = /*EL:159*/0;
                    /*SL:160*/if (splitBracketString.length == 1) {
                        /*SL:162*/n = /*EL:163*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:165*/ if (splitBracketString.length == 2) {
                        /*SL:167*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:168*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:170*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:171*/360);
                    final int v4 = /*EL:172*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:173*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:174*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:176*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:178*/splitBracketString[0], v-6));
                    String a3 = /*EL:179*/String.valueOf(eval);
                    /*SL:181*/if (eval instanceof Double && a3.endsWith(".0")) {
                        a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:182*/a3 = String.valueOf(eval);
                    }
                    /*SL:183*/return fixBackslash(a3);
                }
                /*SL:185*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || substring2.equals("#sPosY") || substring2.equals("#sPosZ") || substring2.equals("#sPos"))) {
                    final boolean b = /*EL:187*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]) || DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:188*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:189*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:190*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:192*/v-6.getDropProperties();
                    final Structure v6 = /*EL:193*/Lucky.getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:194*/if (v6 == null) {
                        return "";
                    }
                    final Vec3d v7 = /*EL:195*/new Vec3d((double)dropProperties.getPropertyFloat("posX"), (double)dropProperties.getPropertyFloat("posY"), (double)dropProperties.getPropertyFloat("posZ"));
                    final int v4 = /*EL:196*/dropProperties.getPropertyInt("rotation");
                    /*SL:198*/if (b) {
                        final Vec3d v8 = /*EL:200*/new Vec3d((double)ValueParser.getFloat(splitBracketString[0], v-6), (double)ValueParser.getFloat(splitBracketString[1], v-6), (double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3d v9 = /*EL:201*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), v7, v4);
                        /*SL:203*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:204*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:205*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:206*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:210*/new BlockPos((int)ValueParser.getInteger(splitBracketString[0], v-6), (int)ValueParser.getInteger(splitBracketString[1], v-6), (int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:211*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), v7, v4);
                        /*SL:212*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:213*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:214*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:215*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + Math.round(v11.func_177958_n()) + "," + Math.round(v11.func_177956_o()) + "," + Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:218*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:220*/splitBracketString[0];
                    /*SL:221*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:226*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:227*/ex.printStackTrace();
        }
        /*SL:230*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:235*/new ArrayList<Integer>();
        int n = /*EL:236*/0;
        while (true) {
            int n2 = /*EL:239*/-1;
            int n3 = /*EL:240*/-1;
            /*SL:244*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:246*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:247*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:249*/n2 = a2;
                    /*SL:250*/n3 = a2;
                }
            }
            /*SL:253*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:254*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:255*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:256*/list.add(n, n2);
            String substring = /*EL:258*/"";
            int n5 = /*EL:259*/-1;
            final char[] charArray = /*EL:261*/v-18.toCharArray();
            int n6 = /*EL:262*/0;
            boolean b = /*EL:263*/false;
            boolean b2 = /*EL:264*/false;
            final char[] array = /*EL:266*/new char[charArray.length];
            int n7 = /*EL:267*/0;
            /*SL:269*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:271*/i > 0 && charArray[i - 1] == '\\';
                /*SL:272*/array[n7] = charArray[i];
                /*SL:274*/if (i >= list.get(n) + n4) {
                    /*SL:276*/if (!b3) {
                        /*SL:278*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:279*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:281*/++n6;
                        }
                        /*SL:283*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:285*/--n6;
                        }
                        /*SL:287*/if (n6 == 0) {
                            final char[] v0 = /*EL:289*/new char[n7 + 1];
                            /*SL:290*/for (int v = 0; v < v0.length; ++v) {
                                /*SL:291*/v0[v] = array[v];
                            }
                            /*SL:292*/substring = new String(v0);
                            /*SL:293*/n5 = i;
                            /*SL:294*/break;
                        }
                    }
                    /*SL:299*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:301*/--n7;
                        /*SL:302*/array[n7] = '\"';
                        final boolean v2 = /*EL:303*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:304*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:309*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:311*/++n7;
            }
            /*SL:313*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:315*/substring, v-17);
            final String substring2 = /*EL:316*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:317*/v-18.substring(n5 + 1, v-18.length());
            /*SL:318*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:320*/++n;
        }
        /*SL:322*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:327*/for (final String a1 : HashVariables.hashVariables) {
            /*SL:328*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:329*/for (final String v1 : HashVariables.bracketHashVariables) {
            /*SL:330*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:331*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:332*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:333*/return false;
    }
    
    public static String autoCancelHash(String a1) {
        /*SL:339*/a1 = a1.replace("#", "'#'");
        /*SL:340*/a1 = a1.replace("''#''", "'#'");
        /*SL:341*/a1 = a1.replace("['#']", "#");
        /*SL:342*/return a1;
    }
    
    public static float randomFloatClamp(final Random a1, final float a2, final float a3) {
        /*SL:347*/return (float)(a2 + (a3 - a2) * a1.nextDouble());
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotion", "#randPotionParticle", "#randSpawnEggDamage", "#randSpawnEgg", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#bExactPosX", "#bExactPosY", "#bExactPosZ", "#bExactPos", "#ePosX", "#ePosY", "#ePosZ", "#ePos", "#eExactPosX", "#eExactPosY", "#eExactPosZ", "#eExactPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pExactPosX", "#pExactPosY", "#pExactPosZ", "#pExactPos", "#pName", "#pUUID", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation", "#bowPosX", "#bowPosY", "#bowPosZ", "#bowPos" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(", "#randPotionDamage(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
