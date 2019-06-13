package mod.lucky;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;

public class LuckyConfiguration
{
    public static int blockLuckyID;
    public static String[] allDrops;
    
    public static String getConfigVersion() {
        final File v0 = /*EL:23*/new File("config/lucky/LuckyBlockProperties.txt");
        /*SL:24*/if (!v0.exists()) {
            return "";
        }
        try {
            final BufferedReader v = /*EL:28*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
            final String v2 = /*EL:29*/v.readLine();
            /*SL:30*/v.close();
            /*SL:31*/if (v2 != null) {
                return v2;
            }
            /*SL:32*/return "";
        }
        catch (Exception v3) {
            /*SL:36*/return "";
        }
    }
    
    public static void readConfig() {
        try {
            final BufferedReader v1 = /*EL:44*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
            String v2 = /*EL:46*/"";
            String v3 = /*EL:47*/"";
            String v4 = /*EL:48*/"";
            boolean v5 = /*EL:50*/false;
            String v6;
            /*SL:51*/while ((v6 = v1.readLine()) != null) {
                /*SL:53*/if (v6.startsWith(">")) {
                    /*SL:55*/v4 = v6;
                }
                else {
                    /*SL:59*/if (v6.startsWith("/") || v6.equals("")) {
                        continue;
                    }
                    /*SL:61*/if (v4.equals(">IDs")) {
                        /*SL:63*/v3 = v6;
                    }
                    /*SL:66*/if (!v4.equals(">drops")) {
                        continue;
                    }
                    /*SL:68*/if (v5) {
                        /*SL:70*/v2 += "~SEPARATOR~";
                    }
                    /*SL:72*/if (v6.endsWith(">")) {
                        /*SL:74*/v6 = v6.substring(0, v6.length() - 1);
                        /*SL:75*/v5 = false;
                    }
                    else {
                        /*SL:77*/v5 = true;
                    }
                    /*SL:79*/v2 += v6;
                }
            }
            /*SL:84*/v1.close();
            LuckyConfiguration.allDrops = /*EL:86*/v2.split("~SEPARATOR~");
            LuckyConfiguration.blockLuckyID = /*EL:87*/Integer.valueOf(v3);
        }
        catch (Exception v7) {
            /*SL:91*/v7.printStackTrace();
        }
    }
    
    public static void createNewConfigFile() {
        try {
            final File v1 = /*EL:99*/new File("config/lucky/LuckyBlockProperties.txt");
            /*SL:101*/v1.getParentFile().mkdirs();
            /*SL:102*/v1.createNewFile();
            final BufferedReader v2 = /*EL:104*/new BufferedReader(new InputStreamReader(Lucky.class.getResourceAsStream("files/LuckyBlockProperties.txt"), "Unicode"));
            final BufferedWriter v3 = /*EL:105*/new BufferedWriter(new OutputStreamWriter(new FileOutputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
            boolean v4 = /*EL:108*/true;
            String v5;
            /*SL:109*/while ((v5 = v2.readLine()) != null) {
                /*SL:111*/if (!v4) {
                    v3.newLine();
                }
                /*SL:112*/v4 = false;
                /*SL:114*/v3.write(v5);
            }
            /*SL:117*/v2.close();
            /*SL:118*/v3.close();
        }
        catch (Exception v6) {
            /*SL:122*/v6.printStackTrace();
        }
    }
    
    static {
        LuckyConfiguration.blockLuckyID = 850;
    }
}
