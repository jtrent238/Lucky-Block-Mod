package mod.lucky.util;

import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import java.util.Random;
import mod.lucky.drop.LuckyDrop;
import mod.lucky.drop.LuckyDropBase;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

public class MakeLuckyDrops
{
    public ExpressionParser expressionParser;
    public EntityPlayer player;
    public World world;
    public int harvestX;
    public int harvestY;
    public int harvestZ;
    
    public MakeLuckyDrops() {
        this.expressionParser = ExpressionParser.instance;
    }
    
    public LuckyDrop[] getDrops(final LuckyDropBase a5, final World a6, final EntityPlayer v1, final int v2, final int v3, final int v4) throws ExpressionParser.ExpressionParserException {
        System.out.println(/*EL:42*/"Chosen drop: " + a5.getDropValue());
        /*SL:44*/this.world = a6;
        /*SL:45*/this.player = v1;
        /*SL:46*/this.harvestX = v2;
        /*SL:47*/this.harvestY = v3;
        /*SL:48*/this.harvestZ = v4;
        /*SL:50*/this.expressionParser.world = a6;
        /*SL:51*/this.expressionParser.player = v1;
        /*SL:52*/this.expressionParser.harvestX = v2;
        /*SL:53*/this.expressionParser.harvestY = v3;
        /*SL:54*/this.expressionParser.harvestZ = v4;
        String[] v5 = /*EL:57*/{ /*EL:58*/a5.getDropValue() };
        boolean a7;
        do {
            /*SL:61*/a7 = false;
            int a8 = /*EL:62*/0;
            /*SL:63*/while (a8 < v5.length) {
                /*SL:65*/if (v5[a8].startsWith("group")) {
                    /*SL:67*/a7 = true;
                    final String[] a9 = /*EL:69*/this.getGroupContents(v5[a8], ';');
                    /*SL:70*/v5 = this.addToArray(v5, a9, a8);
                    /*SL:71*/a8 += a9.length;
                }
                else {
                    /*SL:75*/++a8;
                }
            }
        } while (/*EL:78*/a7);
        /*SL:84*/return this.makeDropsFromArray(v5);
    }
    
    public String[] getGroupContents(final String v1, final char v2) throws ExpressionParser.ExpressionParserException {
        final String v3 = /*EL:89*/v1.startsWith("[") ? "[" : "(";
        final String v4 = /*EL:90*/v1.endsWith("]") ? "]" : ")";
        final String v5 = /*EL:91*/v1.substring(v1.indexOf(v3) + 1, v1.lastIndexOf(v4));
        String[] v6 = /*EL:92*/LuckyFunction.splitBracketString(v5, v2);
        /*SL:95*/if (v1.startsWith("group:")) {
            final int a1 = /*EL:97*/this.expressionParser.getInteger(v1.substring(v1.indexOf(":") + 1, v1.indexOf(v3)));
            /*SL:98*/v6 = this.decreaseDropAmount(v6, a1);
        }
        /*SL:101*/return v6;
    }
    
    public String[] decreaseDropAmount(final String[] v2, final int v3) {
        final Random v4 = /*EL:106*/new Random();
        final boolean[] v5 = /*EL:108*/new boolean[v2.length];
        int v6 = /*EL:109*/0;
        do {
            final int a1 = /*EL:112*/v4.nextInt(v2.length);
            /*SL:113*/if (!v5[a1]) {
                /*SL:115*/v5[a1] = true;
                /*SL:116*/++v6;
            }
        } while (/*EL:119*/v6 != v3);
        final String[] v7 = /*EL:122*/new String[v3];
        /*SL:123*/v6 = 0;
        /*SL:124*/for (int a2 = 0; a2 < v2.length; ++a2) {
            /*SL:126*/if (v5[a2]) {
                /*SL:128*/v7[v6] = v2[a2];
                /*SL:129*/++v6;
            }
        }
        /*SL:133*/return v7;
    }
    
    public String[] addToArray(final String[] v-7, final String[] v-6, final int v-5) {
        final String[] array = /*EL:138*/new String[v-7.length - 1 + v-6.length];
        int n = /*EL:139*/0;
        /*SL:141*/for (int i = 0; i < v-7.length; ++i) {
            /*SL:143*/if (i == v-5) {
                /*SL:145*/for (final String a2 : v-6) {
                    /*SL:147*/array[n] = a2;
                    /*SL:148*/++n;
                }
            }
            else {
                /*SL:153*/array[n] = v-7[i];
                /*SL:154*/++n;
            }
        }
        /*SL:158*/return array;
    }
    
    public LuckyDrop[] makeDropsFromArray(final String[] v-6) throws ExpressionParser.ExpressionParserException {
        final LuckyDrop[] array = /*EL:163*/new LuckyDrop[1024];
        int n = /*EL:164*/0;
        /*SL:165*/for (final String v0 : v-6) {
            final LuckyDrop v = /*EL:167*/this.setDropProperties(v0);
            /*SL:169*/for (int v2 = v.getAmount(), a1 = 0; a1 < v2; ++a1) {
                /*SL:174*/if (v.getReinitialize()) {
                    array[n] = this.setDropProperties(v0);
                }
                else {
                    /*SL:175*/array[n] = v;
                }
                /*SL:177*/array[n].setAmount(1);
                /*SL:178*/++n;
            }
        }
        /*SL:181*/return array;
    }
    
    public LuckyDrop setDropProperties(final String v-13) throws ExpressionParser.ExpressionParserException {
        final LuckyDrop luckyDrop = /*EL:186*/new LuckyDrop();
        final String[] splitBracketString = /*EL:187*/LuckyFunction.splitBracketString(v-13, ',');
        /*SL:189*/luckyDrop.setPosX(this.harvestX);
        /*SL:190*/luckyDrop.setPosY(this.harvestY);
        /*SL:191*/luckyDrop.setPosZ(this.harvestZ);
        boolean b = /*EL:193*/false;
        int integer = /*EL:194*/0;
        int integer2 = /*EL:195*/0;
        boolean b2 = /*EL:197*/false;
        int intValue = /*EL:198*/0;
        /*SL:199*/for (final String s : splitBracketString) {
            String s2 = /*EL:201*/s.substring(0, s.indexOf("=")).trim();
            final String v0 = /*EL:202*/s.substring(s.indexOf("=") + 1).trim();
            /*SL:203*/if (((v0.startsWith("(") && v0.endsWith(")")) || (v0.startsWith("[") && v0.endsWith("]")) || (v0.startsWith("{") && v0.endsWith("}"))) && !s2.equals("NBTTag")) {
                s2 = s2.substring(1, s2.length() - 1);
            }
            /*SL:205*/if (s2.equals("type")) {
                luckyDrop.setType(this.expressionParser.getString(v0));
            }
            /*SL:206*/if (s2.equals("ID")) {
                luckyDrop.setId(this.expressionParser.getString(v0));
            }
            /*SL:207*/if (s2.equals("damage")) {
                luckyDrop.setDamage(this.expressionParser.getInteger(v0));
            }
            /*SL:208*/if (s2.equals("amount")) {
                luckyDrop.setAmount(this.expressionParser.getInteger(v0));
            }
            /*SL:209*/if (s2.equals("reinitialize")) {
                luckyDrop.setReinitialize(this.expressionParser.getBoolean(v0));
            }
            /*SL:210*/if (s2.equals("posX")) {
                luckyDrop.setPosX(this.expressionParser.getInteger(v0));
            }
            /*SL:211*/if (s2.equals("posY")) {
                luckyDrop.setPosY(this.expressionParser.getInteger(v0));
            }
            /*SL:212*/if (s2.equals("posZ")) {
                luckyDrop.setPosZ(this.expressionParser.getInteger(v0));
            }
            /*SL:213*/if (s2.equals("displayCommandOutput")) {
                luckyDrop.setDisplayCommandOutput(this.expressionParser.getBoolean(v0));
            }
            /*SL:214*/if (s2.equals("commandSender")) {
                luckyDrop.setCommandSender(this.expressionParser.getString(v0));
            }
            /*SL:215*/if (s2.equals("relativeToPlayer")) {
                /*SL:217*/luckyDrop.setPosX(MathHelper.func_76128_c(this.player.field_70165_t));
                /*SL:218*/luckyDrop.setPosY(MathHelper.func_76128_c(this.player.field_70163_u));
                /*SL:219*/luckyDrop.setPosZ(MathHelper.func_76128_c(this.player.field_70161_v));
            }
            /*SL:221*/if (s2.equals("duration")) {
                luckyDrop.setEffectDuration(this.expressionParser.getInteger(v0));
            }
            /*SL:222*/if (s2.equals("adjustHeight")) {
                /*SL:224*/b2 = true;
                /*SL:225*/intValue = Integer.valueOf(v0);
            }
            /*SL:228*/if (s2.equals("scatterOffset")) {
                /*SL:230*/b = true;
                final String[] a1 = /*EL:231*/v0.split("-");
                /*SL:232*/integer = this.expressionParser.getInteger(a1[0]);
                /*SL:233*/integer2 = this.expressionParser.getInteger(a1[1]);
            }
            /*SL:236*/if (s2.equals("NBTTag")) {
                NBTTagCompound v = /*EL:238*/new NBTTagCompound();
                /*SL:240*/if (v0.startsWith("#")) {
                    v = (NBTTagCompound)CustomNBTTags.getNBTTagFromString(v0);
                }
                else {
                    /*SL:241*/this.getNBTTag((NBTBase)v, v0);
                }
                /*SL:243*/luckyDrop.setNBTTag(v);
            }
        }
        /*SL:247*/if (b) {
            int n = /*EL:249*/0;
            while (true) {
                final Random random = /*EL:252*/new Random();
                final int i = /*EL:254*/integer2 - random.nextInt(integer2 * 2 + 1);
                final int j = /*EL:255*/integer2 - random.nextInt(integer2 * 2 + 1);
                /*SL:257*/if (i >= integer || i <= integer * -1 || j >= integer || j <= integer * -1) {
                    /*SL:259*/luckyDrop.setPosX(luckyDrop.getPosX() + i);
                    /*SL:260*/luckyDrop.setPosZ(luckyDrop.getPosZ() + j);
                    /*SL:261*/break;
                }
                /*SL:264*/if (n > 1000) {
                    break;
                }
                /*SL:266*/++n;
            }
        }
        /*SL:270*/if (b2) {
            final boolean b3 = /*EL:272*/false;
            int posY = /*EL:273*/luckyDrop.getPosY();
            int i = /*EL:274*/0;
            /*SL:275*/for (int j = luckyDrop.getPosY(); j < luckyDrop.getPosY() + 16; ++j) {
                /*SL:277*/if (this.world.func_147439_a(luckyDrop.getPosX(), j, luckyDrop.getPosZ()).func_149662_c()) {
                    /*SL:279*/i = 0;
                    /*SL:280*/posY = j + 1;
                }
                else {
                    /*SL:284*/++i;
                }
                /*SL:287*/if (i == intValue) {
                    /*SL:289*/luckyDrop.setPosY(posY);
                    /*SL:290*/break;
                }
            }
        }
        /*SL:295*/return luckyDrop;
    }
    
    public void getNBTTag(final NBTBase v-9, final String v-8) throws ExpressionParser.ExpressionParserException {
        final String[] groupContents;
        final String[] array = /*EL:301*/groupContents = this.getGroupContents(v-8, ',');
        for (final String s : groupContents) {
            final String s2 = /*EL:303*/(v-9 instanceof NBTTagCompound) ? s.substring(0, s.indexOf("=")) : "";
            String substring = /*EL:304*/(v-9 instanceof NBTTagCompound) ? s.substring(s.indexOf("=") + 1) : s;
            /*SL:305*/if (substring.startsWith("{") && substring.endsWith("}")) {
                substring = substring.substring(1, substring.length() - 1);
            }
            Label_0931: {
                /*SL:307*/if (s2.equals("id")) {
                    try {
                        final Item a1 = (Item)Item.field_150901_e.func_82594_a(/*EL:311*/this.expressionParser.getString(substring));
                        /*SL:313*/if (a1 != null) {
                            /*SL:315*/this.setTagValue(v-9, s2, Item.field_150901_e.func_148757_b((Object)a1));
                            break Label_0931;
                        }
                    }
                    catch (Exception ex) {}
                }
                final NBTBase v0 = /*EL:323*/CustomNBTTags.getNBTTagFromString(substring);
                /*SL:324*/if (substring.startsWith("#") && v0 != null) {
                    /*SL:326*/this.setTagValue(v-9, s2, v0);
                }
                else/*SL:328*/ if (substring.startsWith("(") && substring.endsWith(")")) {
                    final NBTTagCompound v = /*EL:330*/new NBTTagCompound();
                    /*SL:331*/this.getNBTTag((NBTBase)v, substring);
                    /*SL:332*/this.setTagValue(v-9, s2, v);
                }
                else/*SL:334*/ if (substring.startsWith("[") && substring.endsWith("]")) {
                    final NBTTagList v2 = /*EL:336*/new NBTTagList();
                    /*SL:337*/this.getNBTTag((NBTBase)v2, substring);
                    /*SL:338*/this.setTagValue(v-9, s2, v2);
                }
                else/*SL:340*/ if (substring.startsWith("\"") && substring.endsWith("\"")) {
                    /*SL:342*/this.setTagValue(v-9, s2, this.expressionParser.getString(substring));
                }
                else {
                    try {
                        /*SL:348*/if (substring.endsWith("d") || substring.endsWith("D")) {
                            this.setTagValue(v-9, s2, this.expressionParser.getDouble(substring.substring(0, substring.length() - 1)));
                        }
                        else/*SL:349*/ if (substring.endsWith("f") || substring.endsWith("F")) {
                            this.setTagValue(v-9, s2, this.expressionParser.getFloat(substring.substring(0, substring.length() - 1)));
                        }
                        else/*SL:350*/ if (substring.endsWith("s") || substring.endsWith("S")) {
                            this.setTagValue(v-9, s2, this.expressionParser.getShort(substring.substring(0, substring.length() - 1)));
                        }
                        else {
                            /*SL:351*/if (!substring.endsWith("b") && !substring.endsWith("B")) {
                                final ExpressionParser expressionParser = /*EL:352*/this.expressionParser;
                                expressionParser.getClass();
                                throw expressionParser.new ExpressionParserException();
                            }
                            this.setTagValue(v-9, s2, this.expressionParser.getByte(substring.substring(0, substring.length() - 1)));
                        }
                    }
                    catch (ExpressionParser.ExpressionParserException v8) {
                        try {
                            /*SL:358*/this.setTagValue(v-9, s2, this.expressionParser.getInteger(substring));
                        }
                        catch (ExpressionParser.ExpressionParserException v9) {
                            try {
                                /*SL:364*/this.setTagValue(v-9, s2, this.expressionParser.getBoolean(substring));
                            }
                            catch (ExpressionParser.ExpressionParserException v10) {
                                try {
                                    final String[] v3 = /*EL:370*/substring.split(":");
                                    /*SL:371*/if (v3.length == 0) {
                                        final ExpressionParser expressionParser2 = this.expressionParser;
                                        expressionParser2.getClass();
                                        throw expressionParser2.new ExpressionParserException();
                                    }
                                    int v4 = /*EL:373*/0;
                                    /*SL:374*/if (v3[0].endsWith("b") || v3[0].endsWith("B")) {
                                        v4 = 1;
                                    }
                                    final int[] v5 = /*EL:376*/new int[v3.length];
                                    final byte[] v6 = /*EL:377*/new byte[v3.length];
                                    /*SL:378*/for (int v7 = 0; v7 < v3.length; ++v7) {
                                        /*SL:380*/if (v4 == 0) {
                                            v5[v7] = this.expressionParser.getInteger(v3[v7]);
                                        }
                                        else {
                                            /*SL:383*/if (v3[v7].endsWith("b") || v3[0].endsWith("B")) {
                                                v3[v7] = v3[v7].substring(0, v3[v7].length() - 1);
                                            }
                                            /*SL:384*/v6[v7] = this.expressionParser.getByte(v3[v7]);
                                        }
                                    }
                                    /*SL:388*/if (v4 == 0) {
                                        /*SL:390*/if (v5.length == 0) {
                                            final ExpressionParser expressionParser3 = this.expressionParser;
                                            expressionParser3.getClass();
                                            throw expressionParser3.new ExpressionParserException();
                                        }
                                        /*SL:391*/this.setTagValue(v-9, s2, v5);
                                    }
                                    else {
                                        /*SL:395*/if (v6.length == 0) {
                                            final ExpressionParser expressionParser4 = this.expressionParser;
                                            expressionParser4.getClass();
                                            throw expressionParser4.new ExpressionParserException();
                                        }
                                        /*SL:396*/this.setTagValue(v-9, s2, v6);
                                    }
                                }
                                catch (ExpressionParser.ExpressionParserException v11) {
                                    /*SL:401*/this.setTagValue(v-9, s2, this.expressionParser.getString(substring));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setTagValue(final NBTBase v1, final String v2, final Object v3) {
        /*SL:412*/if (v1 instanceof NBTTagCompound) {
            final NBTTagCompound a1 = /*EL:414*/(NBTTagCompound)v1;
            /*SL:415*/if (v3 instanceof String) {
                a1.func_74778_a(v2, (String)v3);
            }
            /*SL:416*/if (v3 instanceof Boolean) {
                a1.func_74757_a(v2, (boolean)v3);
            }
            /*SL:417*/if (v3 instanceof Integer) {
                a1.func_74768_a(v2, (int)v3);
            }
            /*SL:418*/if (v3 instanceof Float) {
                a1.func_74776_a(v2, (float)v3);
            }
            /*SL:419*/if (v3 instanceof Double) {
                a1.func_74780_a(v2, (double)v3);
            }
            /*SL:420*/if (v3 instanceof Short) {
                a1.func_74777_a(v2, (short)v3);
            }
            /*SL:421*/if (v3 instanceof Byte) {
                a1.func_74774_a(v2, (byte)v3);
            }
            /*SL:422*/if (v3 instanceof int[]) {
                a1.func_74783_a(v2, (int[])v3);
            }
            /*SL:423*/if (v3 instanceof byte[]) {
                a1.func_74773_a(v2, (byte[])v3);
            }
            /*SL:424*/if (v3 instanceof NBTTagCompound) {
                a1.func_74782_a(v2, (NBTBase)v3);
            }
            /*SL:425*/if (v3 instanceof NBTTagList) {
                a1.func_74782_a(v2, (NBTBase)v3);
            }
        }
        /*SL:427*/if (v1 instanceof NBTTagList) {
            final NBTTagList a2 = /*EL:429*/(NBTTagList)v1;
            /*SL:430*/if (v3 instanceof String) {
                a2.func_74742_a((NBTBase)new NBTTagString((String)v3));
            }
            /*SL:431*/if (v3 instanceof Integer) {
                a2.func_74742_a((NBTBase)new NBTTagInt((int)v3));
            }
            /*SL:432*/if (v3 instanceof Float) {
                a2.func_74742_a((NBTBase)new NBTTagFloat((float)v3));
            }
            /*SL:433*/if (v3 instanceof Double) {
                a2.func_74742_a((NBTBase)new NBTTagDouble((double)v3));
            }
            /*SL:434*/if (v3 instanceof Short) {
                a2.func_74742_a((NBTBase)new NBTTagShort((short)v3));
            }
            /*SL:435*/if (v3 instanceof Byte) {
                a2.func_74742_a((NBTBase)new NBTTagByte((byte)v3));
            }
            /*SL:436*/if (v3 instanceof int[]) {
                a2.func_74742_a((NBTBase)new NBTTagIntArray((int[])v3));
            }
            /*SL:437*/if (v3 instanceof byte[]) {
                a2.func_74742_a((NBTBase)new NBTTagByteArray((byte[])v3));
            }
            /*SL:438*/if (v3 instanceof NBTTagCompound) {
                a2.func_74742_a((NBTBase)v3);
            }
            /*SL:439*/if (v3 instanceof NBTTagList) {
                a2.func_74742_a((NBTBase)v3);
            }
        }
    }
}
