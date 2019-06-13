package mod.lucky.util;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class LuckyReader
{
    private BufferedReader bufferedReader;
    
    public LuckyReader(final InputStreamReader a1) {
        this.bufferedReader = new BufferedReader(a1);
    }
    
    public String readLine() {
        try {
            String v1 = /*EL:16*/"";
            String v2 = /*EL:17*/null;
            /*SL:18*/while ((v2 = this.bufferedReader.readLine()) != null) {
                /*SL:19*/v2 = v2.trim();
                /*SL:20*/if (!v2.startsWith("/")) {
                    if (v2.equals("")) {
                        continue;
                    }
                    /*SL:21*/if (!v2.endsWith("\\")) {
                        /*SL:25*/v1 += v2;
                        break;
                    }
                    v2 = v2.substring(0, v2.length() - 1).trim();
                    v1 += v2;
                }
            }
            /*SL:29*/if (v2 == null) {
                return null;
            }
            /*SL:30*/return v1;
        }
        catch (IOException v3) {
            System.err.println(/*EL:32*/"Lucky Block: I/O read Error");
            /*SL:33*/v3.printStackTrace();
            /*SL:35*/return "";
        }
    }
    
    public void close() {
        try {
            /*SL:40*/this.bufferedReader.close();
        }
        catch (IOException v1) {
            System.err.println(/*EL:42*/"Lucky Block: I/O close Error");
            /*SL:43*/v1.printStackTrace();
        }
    }
}
