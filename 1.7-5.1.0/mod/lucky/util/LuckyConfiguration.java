package mod.lucky.util;

import java.util.Iterator;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import mod.lucky.Lucky;
import net.minecraft.item.Item;
import mod.lucky.drop.LuckyDropBase;
import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;

public class LuckyConfiguration
{
    public static String getConfigVersion() {
        final File v0 = /*EL:22*/new File("config/lucky/LuckyBlockProperties.txt");
        /*SL:23*/if (!v0.exists()) {
            return "";
        }
        try {
            final BufferedReader v = /*EL:27*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt")));
            final String v2 = /*EL:28*/v.readLine();
            /*SL:29*/v.close();
            /*SL:30*/if (v2 != null) {
                return v2;
            }
            /*SL:31*/return "";
        }
        catch (Exception v3) {
            /*SL:35*/return "";
        }
    }
    
    public static void readConfig() {
        try {
            final BufferedReader bufferedReader = /*EL:43*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt")));
            String s = /*EL:45*/"";
            final boolean b = /*EL:47*/false;
            final ArrayList<LuckyDropBase> list = /*EL:49*/new ArrayList<LuckyDropBase>();
            final ArrayList<Item> list2 = /*EL:50*/new ArrayList<Item>();
            final ArrayList<Integer> v9 = /*EL:51*/new ArrayList<Integer>();
            final ArrayList<Integer> v10 = /*EL:52*/new ArrayList<Integer>();
            int n = /*EL:54*/0;
            String string = /*EL:55*/"";
            final ExpressionParser v0 = ExpressionParser.instance;
            String v11;
            /*SL:57*/while ((v11 = bufferedReader.readLine()) != null) {
                /*SL:59*/if (v11.startsWith(">")) {
                    /*SL:61*/s = v11;
                }
                else {
                    /*SL:65*/if (v11.startsWith("/") || v11.equals("")) {
                        continue;
                    }
                    try {
                        /*SL:69*/if (s.equals(">properties")) {
                            final String v = /*EL:71*/v11.substring(0, v11.indexOf(61));
                            final String v2 = /*EL:72*/v11.substring(v11.indexOf(61) + 1, v11.length());
                            /*SL:74*/if (v.equals("recipe")) {
                                Lucky.instance.luckyCrafting.setLuckyBlockRecipe(v2);
                            }
                            /*SL:75*/if (v.equals("spawnrate")) {
                                Lucky.instance.luckyGenerator.setSpawnrate(v0.getInteger(v2));
                            }
                            /*SL:76*/if (v.equals("structureChance")) {
                                Lucky.instance.luckyGenerator.setStructureChance(v0.getInteger(v2));
                            }
                            /*SL:77*/if (v.equals("doDropsOnCreativeMode")) {
                                Lucky.instance.doDropsOnCreativeMode = v0.getBoolean(v11);
                            }
                        }
                        /*SL:79*/if (s.equals(">craftingitems")) {
                            String v = /*EL:81*/v11.substring(0, v11.indexOf(61));
                            final String v2 = /*EL:82*/v11.substring(v11.indexOf(61) + 1, v11.length());
                            String v3 = /*EL:84*/"";
                            /*SL:85*/if (v.indexOf(44) != -1) {
                                /*SL:87*/v3 = v.substring(v.indexOf(44) + 1, v.length());
                                /*SL:88*/v = v.substring(0, v.indexOf(44));
                            }
                            final Item v4 = (Item)Item.field_150901_e.func_82594_a(/*EL:91*/v);
                            int v5 = /*EL:92*/0;
                            int v6 = /*EL:93*/-1;
                            try {
                                /*SL:97*/v5 = v0.getInteger(v2);
                                /*SL:98*/v6 = v0.getInteger(v3);
                            }
                            catch (ExpressionParser.ExpressionParserException ex2) {}
                            /*SL:104*/list2.add(v4);
                            /*SL:105*/v9.add(v6);
                            /*SL:106*/v10.add(v5);
                        }
                        /*SL:109*/if (!s.equals(">drops")) {
                            continue;
                        }
                        boolean v7 = /*EL:111*/false;
                        /*SL:112*/if (v11.endsWith(">")) {
                            /*SL:114*/v11 = v11.substring(0, v11.length() - 1);
                            /*SL:115*/v7 = true;
                        }
                        /*SL:118*/string += v11;
                        /*SL:120*/if (v7) {
                            continue;
                        }
                        /*SL:122*/list.add(n, new LuckyDropBase(string));
                        /*SL:123*/string = "";
                        /*SL:124*/++n;
                    }
                    catch (Exception v8) {
                        System.err.println(/*EL:130*/"The Lucky Block encountered and error while reading properties from the config file. Error report below:");
                        /*SL:131*/v8.printStackTrace();
                    }
                }
            }
            /*SL:135*/bufferedReader.close();
            Lucky.instance.allDrops = /*EL:137*/list.<LuckyDropBase>toArray(new LuckyDropBase[list.size()]);
            Lucky.instance.luckyCrafting.luckyCraftingItems = /*EL:138*/list2.<Item>toArray(new Item[list2.size()]);
            Lucky.instance.luckyCrafting.luckyCraftingItemDamage = convertIntListToArray(/*EL:139*/v9);
            Lucky.instance.luckyCrafting.luckyCraftingLevels = convertIntListToArray(/*EL:140*/v10);
        }
        catch (Exception ex) {
            /*SL:144*/ex.printStackTrace();
        }
    }
    
    public static void createNewConfigFile() {
        try {
            final File v1 = /*EL:152*/new File("config/lucky/LuckyBlockProperties.txt");
            /*SL:154*/if (v1.exists()) {
                v1.delete();
            }
            /*SL:156*/v1.getParentFile().mkdirs();
            /*SL:157*/v1.createNewFile();
            final BufferedReader v2 = /*EL:159*/new BufferedReader(new InputStreamReader(Lucky.class.getResourceAsStream("files/LuckyBlockProperties.txt")));
            final BufferedWriter v3 = /*EL:160*/new BufferedWriter(new OutputStreamWriter(new FileOutputStream("config/lucky/LuckyBlockProperties.txt")));
            boolean v4 = /*EL:163*/true;
            String v5;
            /*SL:164*/while ((v5 = v2.readLine()) != null) {
                /*SL:166*/if (!v4) {
                    v3.newLine();
                }
                /*SL:167*/v4 = false;
                /*SL:169*/v3.write(v5);
            }
            /*SL:172*/v2.close();
            /*SL:173*/v3.close();
        }
        catch (Exception v6) {
            /*SL:177*/v6.printStackTrace();
        }
    }
    
    public static void fixIds(final String v-8) {
        final int[] array = /*EL:183*/new int[10];
        final String[] array2 = /*EL:184*/new String[10];
        final String[] array3 = /*EL:185*/new String[10];
        int n = /*EL:186*/0;
        /*SL:187*/for (int i = 0; i < 10; ++i) {
            /*SL:189*/array[i] = v-8.indexOf("ID=", (i == 0) ? 0 : (array[i - 1] + 1));
            /*SL:190*/if (array[i] == -1) {
                break;
            }
            int j = /*EL:192*/-1;
            int n2 = /*EL:194*/v-8.indexOf(44, array[i]);
            /*SL:195*/if (n2 != -1 && (n2 < j || j == -1)) {
                j = n2;
            }
            /*SL:197*/n2 = v-8.indexOf(59, array[i]);
            /*SL:198*/if (n2 != -1 && (n2 < j || j == -1)) {
                j = n2;
            }
            /*SL:200*/n2 = v-8.indexOf(41, array[i]);
            /*SL:201*/if (n2 != -1 && (n2 < j || j == -1)) {
                j = n2;
            }
            /*SL:203*/n2 = v-8.length();
            /*SL:204*/if (n2 != -1 && (n2 < j || j == -1)) {
                j = n2;
            }
            /*SL:206*/array2[i] = v-8.substring(array[i], j);
            try {
                final int a1 = /*EL:209*/Integer.valueOf(array2[i].split("=")[1]);
                final String v1 = /*EL:210*/Item.field_150901_e.func_148750_c((Object)Item.func_150899_d(a1)).split(":")[1];
                /*SL:211*/array3[i] = "ID=" + v1;
            }
            catch (Exception v2) {
                /*SL:215*/array3[i] = array2[i];
            }
            /*SL:218*/++n;
        }
        String s = /*EL:221*/v-8;
        /*SL:222*/for (int j = 0; j < n; ++j) {
            /*SL:224*/s = s.replaceAll(array2[j], array3[j]);
        }
        /*SL:226*/s = s.replaceAll("name", "ID");
        System.out.println(/*EL:228*/s);
    }
    
    public static int[] convertIntListToArray(final ArrayList<Integer> v1) {
        final int[] v2 = /*EL:233*/new int[v1.size()];
        final Iterator<Integer> v3 = /*EL:234*/v1.iterator();
        /*SL:235*/for (int a1 = 0; a1 < v2.length; ++a1) {
            /*SL:237*/v2[a1] = v3.next();
        }
        /*SL:239*/return v2;
    }
}
