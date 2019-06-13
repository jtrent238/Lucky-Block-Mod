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
        /*SL:84*/if (!v-2.contains("#") && !v-2.contains("$")) {
            return v-2;
        }
        /*SL:86*/v-2 = processBracketHash(v-2, v-1);
        /*SL:89*/v-2 = v-2.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:91*/v-2 = v-2.replace("#randPotionParticle", /*EL:92*/String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:93*/v-2 = v-2.replace("#randPotion", LuckyFunction.getRandomPotionName());
        /*SL:95*/v-2 = v-2.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEggID()));
        /*SL:96*/v-2 = v-2.replace("#randSpawnEgg", LuckyFunction.getRandomMobEggName());
        /*SL:98*/if (v-1 != null) {
            final Vec3d v0 = /*EL:99*/v-1.getHarvestPos();
            /*SL:100*/v-2 = v-2.replace("#bPosX", String.valueOf(Math.floor(v0.field_72450_a)));
            /*SL:101*/v-2 = v-2.replace("#bPosY", String.valueOf(Math.floor(v0.field_72448_b)));
            /*SL:102*/v-2 = v-2.replace("#bPosZ", String.valueOf(Math.floor(v0.field_72449_c)));
            /*SL:104*/v-2 = v-2.replace("#bPos", /*EL:106*/String.valueOf("(" + /*EL:108*/Math.floor(v0.field_72450_a) + "," + /*EL:110*/Math.floor(v0.field_72448_b) + "," + /*EL:112*/Math.floor(v0.field_72449_c) + ")"));
            /*SL:115*/v-2 = v-2.replace("#bExactPosX", String.valueOf(v0.field_72450_a));
            /*SL:116*/v-2 = v-2.replace("#bExactPosY", String.valueOf(v0.field_72448_b));
            /*SL:117*/v-2 = v-2.replace("#bExactPosZ", String.valueOf(v0.field_72449_c));
            /*SL:119*/v-2 = v-2.replace("#bExactPos", /*EL:121*/String.valueOf("(" + v0.field_72450_a + "," + v0.field_72448_b + "," + v0.field_72449_c + ")"));
            /*SL:123*/if (v-1.getHitEntity() != null) {
                final Vec3d a1 = /*EL:124*/v-1.getHitEntity().func_174791_d();
                /*SL:125*/v-2 = v-2.replace("#ePosX", String.valueOf(Math.floor(a1.field_72450_a)));
                /*SL:126*/v-2 = v-2.replace("#ePosY", String.valueOf(Math.floor(a1.field_72448_b)));
                /*SL:127*/v-2 = v-2.replace("#ePosZ", String.valueOf(Math.floor(a1.field_72449_c)));
                /*SL:129*/v-2 = v-2.replace("#ePos", /*EL:131*/String.valueOf("(" + /*EL:133*/Math.floor(a1.field_72450_a) + "," + /*EL:135*/Math.floor(a1.field_72448_b) + "," + /*EL:137*/Math.floor(a1.field_72449_c) + ")"));
                /*SL:140*/v-2 = v-2.replace("#eExactPosX", String.valueOf(a1.field_72450_a));
                /*SL:141*/v-2 = v-2.replace("#eExactPosY", String.valueOf(a1.field_72448_b));
                /*SL:142*/v-2 = v-2.replace("#eExactPosZ", String.valueOf(a1.field_72449_c));
                /*SL:144*/v-2 = v-2.replace("#eExactPos", /*EL:146*/String.valueOf("(" + a1.field_72450_a + "," + a1.field_72448_b + "," + a1.field_72449_c + ")"));
            }
            /*SL:149*/if (v-1.getWorld() != null) {
                /*SL:150*/v-2 = v-2.replace("#time", String.valueOf(v-1.getWorld().func_72820_D()));
            }
            /*SL:153*/if (v-1.getPlayer() != null) {
                /*SL:154*/v-2 = v-2.replace("#pPosX", String.valueOf(Math.floor(v-1.getPlayer().field_70165_t)));
                /*SL:155*/v-2 = v-2.replace("#pPosY", String.valueOf(Math.floor(v-1.getPlayer().field_70163_u)));
                /*SL:156*/v-2 = v-2.replace("#pPosZ", String.valueOf(Math.floor(v-1.getPlayer().field_70161_v)));
                /*SL:158*/v-2 = v-2.replace("#pPos", /*EL:160*/String.valueOf("(" + /*EL:162*/Math.floor(v-1.getPlayer().field_70165_t) + "," + /*EL:164*/Math.floor(v-1.getPlayer().field_70163_u) + "," + /*EL:166*/Math.floor(v-1.getPlayer().field_70161_v) + ")"));
                /*SL:169*/v-2 = v-2.replace("#pExactPosX", String.valueOf(v-1.getPlayer().field_70165_t));
                /*SL:170*/v-2 = v-2.replace("#pExactPosY", String.valueOf(v-1.getPlayer().field_70163_u));
                /*SL:171*/v-2 = v-2.replace("#pExactPosZ", String.valueOf(v-1.getPlayer().field_70161_v));
                /*SL:173*/v-2 = v-2.replace("#pExactPos", /*EL:175*/String.valueOf("(" + v-1.getPlayer().field_70165_t + /*EL:177*/"," + v-1.getPlayer().field_70163_u + /*EL:179*/"," + v-1.getPlayer().field_70161_v + /*EL:181*/")"));
                /*SL:185*/v-2 = v-2.replace("#pName", v-1.getPlayer().func_145748_c_().func_150260_c());
                /*SL:186*/v-2 = v-2.replace("#pUUID", v-1.getPlayer().func_110124_au().toString());
                int v = /*EL:188*/(int)Math.round((v-1.getPlayer().func_70079_am() + 180.0) / 90.0) % 4;
                /*SL:189*/if (v < 0) {
                    v += 4;
                }
                /*SL:190*/v-2 = v-2.replace("#pDirect", String.valueOf(v));
                /*SL:192*/v-2 = v-2.replace("#pYaw", String.valueOf(v-1.getPlayer().func_70079_am()));
                /*SL:193*/v-2 = v-2.replace("#pPitch", String.valueOf(v-1.getPlayer().field_70125_A));
                final EntityTippedArrow v2;
                /*SL:196*/if (v-1.getPlayer() instanceof EntityLivingBase) {
                    final EntityTippedArrow a2 = /*EL:199*/new EntityTippedArrow(v-1.getWorld(), (EntityLivingBase)v-1.getPlayer());
                }
                else {
                    /*SL:206*/v2 = new EntityTippedArrow(v-1.getWorld(), v-1.getPlayer().field_70165_t, v-1.getPlayer().field_70163_u, v-1.getPlayer().field_70161_v);
                }
                /*SL:207*/v-2 = v-2.replace("#bowPosX", String.valueOf(v2.func_174791_d().field_72450_a));
                /*SL:208*/v-2 = v-2.replace("#bowPosY", String.valueOf(v2.func_174791_d().field_72448_b));
                /*SL:209*/v-2 = v-2.replace("#bowPosZ", String.valueOf(v2.func_174791_d().field_72449_c));
                /*SL:211*/v-2 = v-2.replace("#bowPos", /*EL:213*/String.valueOf("(" + v2.func_174791_d().field_72450_a + /*EL:215*/"," + v2.func_174791_d().field_72448_b + /*EL:217*/"," + v2.func_174791_d().field_72449_c + /*EL:219*/")"));
            }
        }
        /*SL:224*/v-2 = v-2.replace("'#'", "#");
        /*SL:225*/v-2 = v-2.replace("'@'", "@");
        /*SL:226*/v-2 = v-2.replace("'$'", "'§'");
        /*SL:227*/v-2 = v-2.replace("$", "§");
        /*SL:228*/v-2 = v-2.replace("'§'", "$");
        /*SL:230*/return v-2;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:234*/a1 = a1.replace("\\\\t", "\t");
        /*SL:235*/a1 = a1.replace("\\\\b", "\b");
        /*SL:236*/a1 = a1.replace("\\\\n", "\n");
        /*SL:237*/a1 = a1.replace("\\\\r", "\r");
        /*SL:238*/a1 = a1.replace("\\\\f", "\f");
        /*SL:239*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:244*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:245*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:246*/v-7.substring(0, v-7.indexOf(40));
            /*SL:248*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:250*/DropStringUtils.isGenericFloat(splitBracketString[0]) || /*EL:251*/DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:252*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:253*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:255*/if (b) {
                    final float a1 = /*EL:256*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:257*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = randomFloatClamp(HashVariables.random, /*EL:258*/a1, a2);
                    /*SL:259*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:260*/return String.valueOf(v1);
                }
                final int n = /*EL:262*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:263*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:264*/v2 - n + 1) + n;
                /*SL:265*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:266*/return String.valueOf(v3);
            }
            else {
                /*SL:268*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:269*/splitBracketString.length);
                    /*SL:270*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:271*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:272*/0;
                    int n = /*EL:273*/0;
                    /*SL:274*/if (splitBracketString.length == 1) {
                        /*SL:275*/n = /*EL:276*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:277*/ if (splitBracketString.length == 2) {
                        /*SL:278*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:279*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:281*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:282*/360);
                    final int v4 = /*EL:283*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:284*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:285*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:286*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:287*/splitBracketString[0], v-6));
                    String a3 = /*EL:288*/String.valueOf(eval);
                    /*SL:290*/if (eval instanceof Double && a3.endsWith(".0")) {
                        /*SL:291*/a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:292*/a3 = String.valueOf(eval);
                    }
                    /*SL:293*/return fixBackslash(a3);
                }
                /*SL:296*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || /*EL:297*/substring2.equals("#sPosY") || /*EL:298*/substring2.equals("#sPosZ") || /*EL:299*/substring2.equals("#sPos"))) {
                    final boolean b = /*EL:302*/DropStringUtils.isGenericFloat(splitBracketString[0]) || /*EL:303*/DropStringUtils.isGenericFloat(splitBracketString[1]) || /*EL:304*/DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:305*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:306*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:307*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:309*/v-6.getDropProperties();
                    final Structure v6 = /*EL:310*/Lucky.getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:311*/if (v6 == null) {
                        return "";
                    }
                    final Vec3d v7 = /*EL:312*/new Vec3d((double)dropProperties.getPropertyFloat("posX"), /*EL:314*/(double)dropProperties.getPropertyFloat("posY"), /*EL:315*/(double)dropProperties.getPropertyFloat("posZ"));
                    final int v4 = /*EL:317*/dropProperties.getPropertyInt("rotation");
                    /*SL:319*/if (b) {
                        final Vec3d v8 = /*EL:320*/new Vec3d(/*EL:322*/(double)ValueParser.getFloat(splitBracketString[0], v-6), /*EL:323*/(double)ValueParser.getFloat(splitBracketString[1], v-6), /*EL:324*/(double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3d v9 = /*EL:326*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), /*EL:327*/v7, v4);
                        /*SL:329*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:330*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:331*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:332*/if (substring2.equals("#sPos")) {
                            /*SL:333*/return String.valueOf(/*EL:334*/String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:343*/new BlockPos(/*EL:345*/(int)ValueParser.getInteger(splitBracketString[0], v-6), /*EL:346*/(int)ValueParser.getInteger(splitBracketString[1], v-6), /*EL:347*/(int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:349*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), /*EL:350*/v7, v4);
                        /*SL:351*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:352*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:353*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:354*/if (substring2.equals("#sPos")) {
                            /*SL:355*/return String.valueOf(/*EL:356*/String.valueOf("(" + /*EL:358*/Math.round(v11.func_177958_n()) + "," + /*EL:360*/Math.round(v11.func_177956_o()) + "," + /*EL:362*/Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:367*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:369*/splitBracketString[0];
                    /*SL:370*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:373*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:374*/ex.printStackTrace();
        }
        /*SL:377*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:381*/new ArrayList<Integer>();
        int n = /*EL:382*/0;
        while (true) {
            int n2 = /*EL:384*/-1;
            int n3 = /*EL:385*/-1;
            /*SL:389*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:391*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + /*EL:392*/1));
                /*SL:393*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:394*/n2 = a2;
                    /*SL:395*/n3 = a2;
                }
            }
            /*SL:398*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:399*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:400*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:401*/list.add(n, n2);
            String substring = /*EL:403*/"";
            int n5 = /*EL:404*/-1;
            final char[] charArray = /*EL:406*/v-18.toCharArray();
            int n6 = /*EL:407*/0;
            boolean b = /*EL:408*/false;
            boolean b2 = /*EL:409*/false;
            final char[] array = /*EL:411*/new char[charArray.length];
            int n7 = /*EL:412*/0;
            /*SL:414*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:415*/i > 0 && charArray[i - 1] == '\\';
                /*SL:416*/array[n7] = charArray[i];
                /*SL:418*/if (i >= list.get(n) + n4) {
                    /*SL:419*/if (!b3) {
                        /*SL:420*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:421*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:422*/++n6;
                        }
                        /*SL:424*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:425*/--n6;
                        }
                        /*SL:427*/if (n6 == 0) {
                            final char[] v0 = /*EL:428*/new char[n7 + 1];
                            /*SL:429*/for (int v = 0; v < v0.length; ++v) {
                                v0[v] = array[v];
                            }
                            /*SL:430*/substring = new String(v0);
                            /*SL:431*/n5 = i;
                            /*SL:432*/break;
                        }
                    }
                    /*SL:437*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:438*/--n7;
                        /*SL:439*/array[n7] = '\"';
                        final boolean v2 = /*EL:440*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:441*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:444*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:446*/++n7;
            }
            /*SL:448*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:450*/substring, v-17);
            final String substring2 = /*EL:451*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:452*/v-18.substring(n5 + 1, v-18.length());
            /*SL:453*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:455*/++n;
        }
        /*SL:457*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:461*/for (final String a1 : HashVariables.hashVariables) {
            if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:462*/for (final String v1 : HashVariables.bracketHashVariables) {
            if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:463*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:464*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:465*/return false;
    }
    
    public static String autoCancelHash(String a1) {
        /*SL:470*/a1 = a1.replace("#", "'#'");
        /*SL:471*/a1 = a1.replace("''#''", "'#'");
        /*SL:472*/a1 = a1.replace("['#']", "#");
        /*SL:473*/return a1;
    }
    
    public static float randomFloatClamp(final Random a1, final float a2, final float a3) {
        /*SL:477*/return (float)(a2 + (a3 - a2) * a1.nextDouble());
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotion", "#randPotionParticle", "#randSpawnEggDamage", "#randSpawnEgg", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#bExactPosX", "#bExactPosY", "#bExactPosZ", "#bExactPos", "#ePosX", "#ePosY", "#ePosZ", "#ePos", "#eExactPosX", "#eExactPosY", "#eExactPosZ", "#eExactPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pExactPosX", "#pExactPosY", "#pExactPosZ", "#pExactPos", "#pName", "#pUUID", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation", "#bowPosX", "#bowPosY", "#bowPosZ", "#bowPos" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(", "#randPotionDamage(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
