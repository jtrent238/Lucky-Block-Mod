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
            String v1 = /*EL:20*/"";
            String v2 = /*EL:21*/null;
            /*SL:22*/while ((v2 = this.bufferedReader.readLine()) != null) {
                /*SL:24*/v2 = v2.trim();
                /*SL:25*/if (!v2.startsWith("/")) {
                    if (v2.equals("")) {
                        continue;
                    }
                    /*SL:26*/if (!v2.endsWith("\\")) {
                        /*SL:32*/v1 += v2;
                        break;
                    }
                    v2 = v2.substring(0, v2.length() - 1).trim();
                    v1 += v2;
                }
            }
            /*SL:36*/if (v2 == null) {
                return null;
            }
            /*SL:37*/return v1;
        }
        catch (IOException v3) {
            System.err.println(/*EL:41*/"Lucky Block: I/O read Error");
            /*SL:42*/v3.printStackTrace();
            /*SL:44*/return "";
        }
    }
    
    public void close() {
        try {
            /*SL:51*/this.bufferedReader.close();
        }
        catch (IOException v1) {
            System.err.println(/*EL:55*/"Lucky Block: I/O close Error");
            /*SL:56*/v1.printStackTrace();
        }
    }
}
