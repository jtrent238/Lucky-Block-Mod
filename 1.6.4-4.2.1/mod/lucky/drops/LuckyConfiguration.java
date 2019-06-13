package mod.lucky.drops;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import mod.lucky.Lucky;
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
            final BufferedReader v = /*EL:27*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
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
            final BufferedReader v1 = /*EL:43*/new BufferedReader(new InputStreamReader(new FileInputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
            String v2 = /*EL:45*/"";
            final String v3 = /*EL:46*/"";
            String v4 = /*EL:47*/"";
            boolean v5 = /*EL:49*/false;
            String v6;
            /*SL:50*/while ((v6 = v1.readLine()) != null) {
                /*SL:52*/if (v6.startsWith(">")) {
                    /*SL:54*/v4 = v6;
                }
                else {
                    /*SL:58*/if (v6.startsWith("/") || v6.equals("")) {
                        continue;
                    }
                    /*SL:60*/if (v4.equals(">IDs")) {
                        Lucky.blockLuckyID = /*EL:62*/Integer.valueOf(v6);
                    }
                    /*SL:64*/if (v4.equals(">spawnrate")) {
                        Lucky.spawnrate = /*EL:66*/Integer.valueOf(v6);
                    }
                    /*SL:68*/if (v4.equals(">recipe")) {
                        Lucky.recipe = /*EL:70*/v6;
                    }
                    /*SL:73*/if (!v4.equals(">drops")) {
                        continue;
                    }
                    /*SL:75*/if (v5) {
                        /*SL:77*/v2 += "~SEPARATOR~";
                    }
                    /*SL:79*/if (v6.endsWith(">")) {
                        /*SL:81*/v6 = v6.substring(0, v6.length() - 1);
                        /*SL:82*/v5 = false;
                    }
                    else {
                        /*SL:84*/v5 = true;
                    }
                    /*SL:86*/v2 += v6;
                }
            }
            /*SL:91*/v1.close();
            Lucky.allDrops = /*EL:93*/v2.split("~SEPARATOR~");
        }
        catch (Exception v7) {
            /*SL:97*/v7.printStackTrace();
        }
    }
    
    public static void createNewConfigFile() {
        try {
            final File v1 = /*EL:105*/new File("config/lucky/LuckyBlockProperties.txt");
            /*SL:107*/v1.getParentFile().mkdirs();
            /*SL:108*/v1.createNewFile();
            final BufferedReader v2 = /*EL:110*/new BufferedReader(new InputStreamReader(Lucky.class.getResourceAsStream("files/LuckyBlockProperties.txt"), "Unicode"));
            final BufferedWriter v3 = /*EL:111*/new BufferedWriter(new OutputStreamWriter(new FileOutputStream("config/lucky/LuckyBlockProperties.txt"), "Unicode"));
            boolean v4 = /*EL:114*/true;
            String v5;
            /*SL:115*/while ((v5 = v2.readLine()) != null) {
                /*SL:117*/if (!v4) {
                    v3.newLine();
                }
                /*SL:118*/v4 = false;
                /*SL:120*/v3.write(v5);
            }
            /*SL:123*/v2.close();
            /*SL:124*/v3.close();
        }
        catch (Exception v6) {
            /*SL:128*/v6.printStackTrace();
        }
    }
}
