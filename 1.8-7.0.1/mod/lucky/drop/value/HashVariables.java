package mod.lucky.drop.value;

import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.BlockPos;
import mod.lucky.structure.StructureUtils;
import mod.lucky.Lucky;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.entity.projectile.EntityArrow;
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
    
    public static String processString(String v-3, final DropProcessData v-2) {
        /*SL:31*/if (!v-3.contains("#") && !v-3.contains("$")) {
            return v-3;
        }
        /*SL:33*/v-3 = processBracketHash(v-3, v-2);
        /*SL:35*/v-3 = v-3.replace("#randPotionDamage", String.valueOf(LuckyFunction.getRandomPotionDamage()));
        /*SL:36*/v-3 = v-3.replace("#randPotionParticle", String.valueOf(LuckyFunction.getRandomStatusEffect()));
        /*SL:37*/v-3 = v-3.replace("#randSpawnEggDamage", String.valueOf(LuckyFunction.getRandomMobEgg()));
        /*SL:39*/if (v-2 != null) {
            final Vec3 harvestPos = /*EL:41*/v-2.getHarvestPos();
            /*SL:42*/v-3 = v-3.replace("#bPosX", String.valueOf(Math.floor(harvestPos.field_72450_a)));
            /*SL:43*/v-3 = v-3.replace("#bPosY", String.valueOf(Math.floor(harvestPos.field_72448_b)));
            /*SL:44*/v-3 = v-3.replace("#bPosZ", String.valueOf(Math.floor(harvestPos.field_72449_c)));
            /*SL:45*/v-3 = v-3.replace("#bPos", String.valueOf("(" + Math.floor(harvestPos.field_72450_a) + "," + Math.floor(harvestPos.field_72448_b) + "," + Math.floor(harvestPos.field_72449_c) + ")"));
            /*SL:47*/v-3 = v-3.replace("#bExactPosX", String.valueOf(harvestPos.field_72450_a));
            /*SL:48*/v-3 = v-3.replace("#bExactPosY", String.valueOf(harvestPos.field_72448_b));
            /*SL:49*/v-3 = v-3.replace("#bExactPosZ", String.valueOf(harvestPos.field_72449_c));
            /*SL:50*/v-3 = v-3.replace("#bExactPos", String.valueOf("(" + harvestPos.field_72450_a + "," + harvestPos.field_72448_b + "," + harvestPos.field_72449_c + ")"));
            /*SL:52*/if (v-2.getHitEntity() != null) {
                final Vec3 a1 = /*EL:54*/v-2.getHitEntity().func_174791_d();
                /*SL:55*/v-3 = v-3.replace("#ePosX", String.valueOf(Math.floor(a1.field_72450_a)));
                /*SL:56*/v-3 = v-3.replace("#ePosY", String.valueOf(Math.floor(a1.field_72448_b)));
                /*SL:57*/v-3 = v-3.replace("#ePosZ", String.valueOf(Math.floor(a1.field_72449_c)));
                /*SL:58*/v-3 = v-3.replace("#ePos", String.valueOf("(" + Math.floor(a1.field_72450_a) + "," + Math.floor(a1.field_72448_b) + "," + Math.floor(a1.field_72449_c) + ")"));
                /*SL:60*/v-3 = v-3.replace("#eExactPosX", String.valueOf(a1.field_72450_a));
                /*SL:61*/v-3 = v-3.replace("#eExactPosY", String.valueOf(a1.field_72448_b));
                /*SL:62*/v-3 = v-3.replace("#eExactPosZ", String.valueOf(a1.field_72449_c));
                /*SL:63*/v-3 = v-3.replace("#eExactPos", String.valueOf("(" + a1.field_72450_a + "," + a1.field_72448_b + "," + a1.field_72449_c + ")"));
            }
            /*SL:66*/if (v-2.getWorld() != null) {
                /*SL:68*/v-3 = v-3.replace("#time", String.valueOf(v-2.getWorld().func_72820_D()));
            }
            /*SL:71*/if (v-2.getPlayer() != null) {
                /*SL:73*/v-3 = v-3.replace("#pPosX", String.valueOf(Math.floor(v-2.getPlayer().field_70165_t)));
                /*SL:74*/v-3 = v-3.replace("#pPosY", String.valueOf(Math.floor(v-2.getPlayer().field_70163_u)));
                /*SL:75*/v-3 = v-3.replace("#pPosZ", String.valueOf(Math.floor(v-2.getPlayer().field_70161_v)));
                /*SL:76*/v-3 = v-3.replace("#pPos", String.valueOf("(" + Math.floor(v-2.getPlayer().field_70165_t) + "," + Math.floor(v-2.getPlayer().field_70163_u) + "," + Math.floor(v-2.getPlayer().field_70161_v) + ")"));
                /*SL:78*/v-3 = v-3.replace("#pExactPosX", String.valueOf(v-2.getPlayer().field_70165_t));
                /*SL:79*/v-3 = v-3.replace("#pExactPosY", String.valueOf(v-2.getPlayer().field_70163_u));
                /*SL:80*/v-3 = v-3.replace("#pExactPosZ", String.valueOf(v-2.getPlayer().field_70161_v));
                /*SL:81*/v-3 = v-3.replace("#pExactPos", String.valueOf("(" + v-2.getPlayer().field_70165_t + "," + v-2.getPlayer().field_70163_u + "," + v-2.getPlayer().field_70161_v + ")"));
                /*SL:83*/v-3 = v-3.replace("#pName", v-2.getPlayer().func_145748_c_().func_150260_c());
                /*SL:84*/v-3 = v-3.replace("#pUUID", v-2.getPlayer().func_110124_au().toString());
                int a2 = /*EL:85*/(int)Math.round((v-2.getPlayer().func_70079_am() + 180.0) / 90.0) % 4;
                /*SL:86*/if (a2 < 0) {
                    a2 += 4;
                }
                /*SL:87*/v-3 = v-3.replace("#pDirect", String.valueOf(a2));
                /*SL:88*/v-3 = v-3.replace("#pYaw", String.valueOf(v-2.getPlayer().func_70079_am()));
                /*SL:89*/v-3 = v-3.replace("#pPitch", String.valueOf(v-2.getPlayer().field_70125_A));
                final EntityArrow v1 = /*EL:91*/new EntityArrow(v-2.getWorld(), (EntityLivingBase)v-2.getPlayer(), v-2.getBowPower());
                /*SL:92*/v-3 = v-3.replace("#bowPosX", String.valueOf(v1.func_174791_d().field_72450_a));
                /*SL:93*/v-3 = v-3.replace("#bowPosY", String.valueOf(v1.func_174791_d().field_72448_b));
                /*SL:94*/v-3 = v-3.replace("#bowPosZ", String.valueOf(v1.func_174791_d().field_72449_c));
                /*SL:95*/v-3 = v-3.replace("#bowPos", String.valueOf("(" + v1.func_174791_d().field_72450_a + "," + v1.func_174791_d().field_72448_b + "," + v1.func_174791_d().field_72449_c + ")"));
            }
        }
        /*SL:99*/v-3 = v-3.replace("'#'", "#");
        /*SL:100*/v-3 = v-3.replace("'@'", "@");
        /*SL:101*/v-3 = v-3.replace("'$'", "'§'");
        /*SL:102*/v-3 = v-3.replace("$", "§");
        /*SL:103*/v-3 = v-3.replace("'§'", "$");
        /*SL:105*/return v-3;
    }
    
    private static String fixBackslash(String a1) {
        /*SL:110*/a1 = a1.replace("\\\\t", "\t");
        /*SL:111*/a1 = a1.replace("\\\\b", "\b");
        /*SL:112*/a1 = a1.replace("\\\\n", "\n");
        /*SL:113*/a1 = a1.replace("\\\\r", "\r");
        /*SL:114*/a1 = a1.replace("\\\\f", "\f");
        /*SL:115*/return a1;
    }
    
    private static String getBracketHashValue(final String v-7, final DropProcessData v-6) {
        try {
            final String substring = /*EL:122*/v-7.substring(v-7.indexOf(40) + 1, v-7.length() - 1);
            final String[] splitBracketString = /*EL:123*/DropStringUtils.splitBracketString(substring, ',');
            final String substring2 = /*EL:124*/v-7.substring(0, v-7.indexOf(40));
            /*SL:126*/if (substring2.equals("#rand") || substring2.equals("#randPosNeg")) {
                final boolean b = /*EL:128*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]);
                /*SL:129*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                /*SL:130*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                /*SL:132*/if (b) {
                    final float a1 = /*EL:134*/ValueParser.getFloat(splitBracketString[0], v-6);
                    final float a2 = /*EL:135*/ValueParser.getFloat(splitBracketString[1], v-6);
                    float v1 = /*EL:136*/MathHelper.func_151240_a(HashVariables.random, a1, a2);
                    /*SL:137*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                        v1 *= -1.0f;
                    }
                    /*SL:138*/return String.valueOf(v1);
                }
                final int n = /*EL:142*/ValueParser.getInteger(splitBracketString[0], v-6);
                final int v2 = /*EL:143*/ValueParser.getInteger(splitBracketString[1], v-6);
                int v3 = HashVariables.random.nextInt(/*EL:144*/v2 - n + 1) + n;
                /*SL:145*/if (substring2.equals("#randPosNeg") && HashVariables.random.nextInt(2) == 0) {
                    v3 *= -1;
                }
                /*SL:146*/return String.valueOf(v3);
            }
            else {
                /*SL:149*/if (substring2.equals("#randList")) {
                    final int n2 = HashVariables.random.nextInt(/*EL:151*/splitBracketString.length);
                    /*SL:152*/return ValueParser.getString(splitBracketString[n2], v-6);
                }
                /*SL:154*/if (substring2.equals("#circleOffset")) {
                    int n2 = /*EL:156*/0;
                    int n = /*EL:157*/0;
                    /*SL:158*/if (splitBracketString.length == 1) {
                        /*SL:160*/n = /*EL:161*/(n2 = ValueParser.getInteger(splitBracketString[0], v-6));
                    }
                    else/*SL:163*/ if (splitBracketString.length == 2) {
                        /*SL:165*/n2 = ValueParser.getInteger(splitBracketString[0], v-6);
                        /*SL:166*/n = ValueParser.getInteger(splitBracketString[1], v-6);
                    }
                    final int v2 = HashVariables.random.nextInt(/*EL:168*/n - n2 + 1) + n2;
                    final int v3 = HashVariables.random.nextInt(/*EL:169*/360);
                    final int v4 = /*EL:170*/(int)Math.round(v2 * Math.sin(Math.toRadians(v3)));
                    final int v5 = /*EL:171*/(int)Math.round(v2 * Math.cos(Math.toRadians(v3)));
                    /*SL:172*/return "(" + v4 + "," + 0 + "," + v5 + ")";
                }
                /*SL:174*/if (substring2.equals("#eval")) {
                    final Object eval = HashVariables.scriptEngine.eval(processString(/*EL:176*/splitBracketString[0], v-6));
                    String a3 = /*EL:177*/String.valueOf(eval);
                    /*SL:179*/if (eval instanceof Double && a3.endsWith(".0")) {
                        a3 = a3.substring(0, a3.length() - 2);
                    }
                    else {
                        /*SL:180*/a3 = String.valueOf(eval);
                    }
                    /*SL:181*/return fixBackslash(a3);
                }
                /*SL:183*/if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && (substring2.equals("#sPosX") || substring2.equals("#sPosY") || substring2.equals("#sPosZ") || substring2.equals("#sPos"))) {
                    final boolean b = /*EL:185*/DropStringUtils.isGenericFloat(splitBracketString[0]) || DropStringUtils.isGenericFloat(splitBracketString[1]) || DropStringUtils.isGenericFloat(splitBracketString[2]);
                    /*SL:186*/splitBracketString[0] = DropStringUtils.removeNumSuffix(splitBracketString[0]);
                    /*SL:187*/splitBracketString[1] = DropStringUtils.removeNumSuffix(splitBracketString[1]);
                    /*SL:188*/splitBracketString[2] = DropStringUtils.removeNumSuffix(splitBracketString[2]);
                    final DropProperties dropProperties = /*EL:190*/v-6.getDropProperties();
                    final Structure v6 = /*EL:191*/Lucky.getStructure(dropProperties.getPropertyString("ID"));
                    /*SL:192*/if (v6 == null) {
                        return "";
                    }
                    final Vec3 v7 = /*EL:193*/new Vec3((double)dropProperties.getPropertyFloat("posX"), (double)dropProperties.getPropertyFloat("posY"), (double)dropProperties.getPropertyFloat("posZ"));
                    final int v4 = /*EL:194*/dropProperties.getPropertyInt("rotation");
                    /*SL:196*/if (b) {
                        final Vec3 v8 = /*EL:198*/new Vec3((double)ValueParser.getFloat(splitBracketString[0], v-6), (double)ValueParser.getFloat(splitBracketString[1], v-6), (double)ValueParser.getFloat(splitBracketString[2], v-6));
                        final Vec3 v9 = /*EL:199*/StructureUtils.getWorldPos(v8, v6.getCenterPos(), v7, v4);
                        /*SL:201*/if (substring2.equals("#sPosX")) {
                            return String.valueOf((float)v9.field_72450_a);
                        }
                        /*SL:202*/if (substring2.equals("#sPosY")) {
                            return String.valueOf((float)v9.field_72448_b);
                        }
                        /*SL:203*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf((float)v9.field_72449_c);
                        }
                        /*SL:204*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + (float)v9.field_72450_a + "," + (float)v9.field_72448_b + "," + (float)v9.field_72449_c + ")"));
                        }
                    }
                    else {
                        final BlockPos v10 = /*EL:208*/new BlockPos((int)ValueParser.getInteger(splitBracketString[0], v-6), (int)ValueParser.getInteger(splitBracketString[1], v-6), (int)ValueParser.getInteger(splitBracketString[2], v-6));
                        final BlockPos v11 = /*EL:209*/StructureUtils.getWorldPos(v10, v6.getCenterPos(), v7, v4);
                        /*SL:210*/if (substring2.equals("#sPosX")) {
                            return String.valueOf(Math.round(v11.func_177958_n()));
                        }
                        /*SL:211*/if (substring2.equals("#sPosY")) {
                            return String.valueOf(Math.round(v11.func_177956_o()));
                        }
                        /*SL:212*/if (substring2.equals("#sPosZ")) {
                            return String.valueOf(Math.round(v11.func_177952_p()));
                        }
                        /*SL:213*/if (substring2.equals("#sPos")) {
                            return String.valueOf(String.valueOf("(" + Math.round(v11.func_177958_n()) + "," + Math.round(v11.func_177956_o()) + "," + Math.round(v11.func_177952_p()) + ")"));
                        }
                    }
                }
                else/*SL:216*/ if (v-6 != null && v-6.getProcessType() == DropProcessData.EnumProcessType.LUCKY_STRUCT && v-6.getDropProperties() != null && substring2.equals("#drop")) {
                    final String a4 = /*EL:218*/splitBracketString[0];
                    /*SL:219*/return v-6.getDropProperties().getProperty(a4).toString();
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:224*/"Lucky Block: Error processing hash variable: " + v-7);
            /*SL:225*/ex.printStackTrace();
        }
        /*SL:228*/return "";
    }
    
    private static String processBracketHash(String v-18, final DropProcessData v-17) {
        final ArrayList<Integer> list = /*EL:233*/new ArrayList<Integer>();
        int n = /*EL:234*/0;
        while (true) {
            int n2 = /*EL:237*/-1;
            int n3 = /*EL:238*/-1;
            /*SL:242*/for (int a2 = 0; a2 < HashVariables.bracketHashVariables.length; ++a2) {
                /*SL:244*/a2 = v-18.indexOf(HashVariables.bracketHashVariables[a2], (n == 0) ? 0 : (list.get(n - 1) + 1));
                /*SL:245*/if (a2 != -1 && (n2 == -1 || a2 < n2)) {
                    /*SL:247*/n2 = a2;
                    /*SL:248*/n3 = a2;
                }
            }
            /*SL:251*/if (n2 == -1) {
                break;
            }
            final String s = /*EL:252*/HashVariables.bracketHashVariables[n3];
            final int n4 = /*EL:253*/HashVariables.bracketHashVariables[n3].length() - 1;
            /*SL:254*/list.add(n, n2);
            String substring = /*EL:256*/"";
            int n5 = /*EL:257*/-1;
            final char[] charArray = /*EL:259*/v-18.toCharArray();
            int n6 = /*EL:260*/0;
            boolean b = /*EL:261*/false;
            boolean b2 = /*EL:262*/false;
            final char[] array = /*EL:264*/new char[charArray.length];
            int n7 = /*EL:265*/0;
            /*SL:267*/for (int i = 0; i < charArray.length; ++i) {
                final boolean b3 = /*EL:269*/i > 0 && charArray[i - 1] == '\\';
                /*SL:270*/array[n7] = charArray[i];
                /*SL:272*/if (i >= list.get(n) + n4) {
                    /*SL:274*/if (!b3) {
                        /*SL:276*/if (charArray[i] == '\"') {
                            b2 = !b2;
                        }
                        /*SL:277*/if ((charArray[i] == '(' || charArray[i] == '[' || charArray[i] == '{') && !b2) {
                            /*SL:279*/++n6;
                        }
                        /*SL:281*/if ((charArray[i] == ')' || charArray[i] == ']' || charArray[i] == '}') && !b2) {
                            /*SL:283*/--n6;
                        }
                        /*SL:285*/if (n6 == 0) {
                            final char[] v0 = /*EL:287*/new char[n7 + 1];
                            /*SL:288*/for (int v = 0; v < v0.length; ++v) {
                                /*SL:289*/v0[v] = array[v];
                            }
                            /*SL:290*/substring = new String(v0);
                            /*SL:291*/n5 = i;
                            /*SL:292*/break;
                        }
                    }
                    /*SL:297*/if (charArray[i] == '\"' && b3 && b) {
                        /*SL:299*/--n7;
                        /*SL:300*/array[n7] = '\"';
                        final boolean v2 = /*EL:301*/i >= 2 && charArray[i - 2] == '\\';
                        /*SL:302*/if (!v2) {
                            b2 = !b2;
                        }
                    }
                }
                else/*SL:307*/ if (charArray[i] == '\"' && !b3) {
                    b = !b;
                }
                /*SL:309*/++n7;
            }
            /*SL:311*/substring = substring.substring(list.get(n), substring.length());
            final String bracketHashValue = getBracketHashValue(/*EL:313*/substring, v-17);
            final String substring2 = /*EL:314*/v-18.substring(0, list.get(n));
            final String v3 = /*EL:315*/v-18.substring(n5 + 1, v-18.length());
            /*SL:316*/v-18 = substring2 + bracketHashValue + v3;
            /*SL:318*/++n;
        }
        /*SL:320*/return v-18;
    }
    
    public static boolean containsHashVariables(final String v-3) {
        /*SL:325*/for (final String a1 : HashVariables.hashVariables) {
            /*SL:326*/if (v-3.contains(a1)) {
                return true;
            }
        }
        /*SL:327*/for (final String v1 : HashVariables.bracketHashVariables) {
            /*SL:328*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:329*/for (final String v1 : CustomNBTTags.nbtHashVariables) {
            /*SL:330*/if (v-3.contains(v1)) {
                return true;
            }
        }
        /*SL:331*/return false;
    }
    
    public static String autoCancelHash(String a1) {
        /*SL:337*/a1 = a1.replace("#", "'#'");
        /*SL:338*/a1 = a1.replace("''#''", "'#'");
        /*SL:339*/a1 = a1.replace("['#']", "#");
        /*SL:340*/return a1;
    }
    
    static {
        HashVariables.hashVariables = new String[] { "#randPotionDamage", "#randPotionParticle", "#randSpawnEggDamage", "#bPosX", "#bPosY", "#bPosZ", "#bPos", "#bExactPosX", "#bExactPosY", "#bExactPosZ", "#bExactPos", "#ePosX", "#ePosY", "#ePosZ", "#ePos", "#eExactPosX", "#eExactPosY", "#eExactPosZ", "#eExactPos", "#time", "#pPosX", "#pPosY", "#pPosZ", "#pPos", "#pExactPosX", "#pExactPosY", "#pExactPosZ", "#pExactPos", "#pName", "#pDirect", "#pYaw", "#pPitch", "#posX", "#posY", "#posZ", "#pos", "#rotation", "#bowPosX", "#bowPosY", "#bowPosZ", "#bowPos" };
        HashVariables.bracketHashVariables = new String[] { "#rand(", "#randPosNeg(", "#randList(", "#circleOffset(", "#sPosX(", "#sPosY(", "#sPosZ(", "#sPos(", "#drop(", "#eval(", "#randPotionDamage(" };
        HashVariables.random = new Random();
        HashVariables.scriptEngine = new ScriptEngineManager(null).getEngineByName("JavaScript");
    }
}
