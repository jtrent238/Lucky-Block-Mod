package mod.lucky.drop.value;

public class DropStringUtils
{
    public static String fixBackslash(String a1) {
        /*SL:7*/a1 = a1.replaceAll("\\\\t", "\t");
        /*SL:8*/a1 = a1.replaceAll("\\\\b", "\b");
        /*SL:9*/a1 = a1.replaceAll("\\\\n", "\n");
        /*SL:10*/a1 = a1.replaceAll("\\\\r", "\r");
        /*SL:11*/a1 = a1.replaceAll("\\\\f", "\f");
        /*SL:12*/return a1;
    }
    
    public static boolean hasDecimalPoint(String v1) {
        /*SL:17*/if (v1 == null) {
            return false;
        }
        /*SL:18*/v1 = removeNumSuffix(v1);
        /*SL:19*/for (int a1 = v1.length() - 1; a1 >= 0; --a1) {
            /*SL:21*/if (!Character.isDigit(v1.charAt(a1))) {
                /*SL:22*/return v1.charAt(a1) == '.';
            }
        }
        /*SL:25*/return false;
    }
    
    public static boolean isGenericFloat(final String a1) {
        /*SL:30*/return a1 != null && /*EL:31*/(a1.endsWith("f") || a1.endsWith("F") || a1.endsWith("d") || a1.endsWith("D") || hasDecimalPoint(a1));
    }
    
    public static String removeNumSuffix(final String a1) {
        /*SL:37*/if (a1 == null) {
            return null;
        }
        /*SL:39*/if (a1.endsWith("f") || a1.endsWith("F") || a1.endsWith("d") || a1.endsWith("D") || a1.endsWith("s") || a1.endsWith("S") || a1.endsWith("b") || a1.endsWith("B")) {
            return a1.substring(0, a1.length() - 1);
        }
        /*SL:40*/return a1;
    }
    
    public static String[] splitBracketString(final String v-7, final char v-6) {
        final char[] charArray = /*EL:46*/v-7.toCharArray();
        final int[] array = /*EL:47*/new int[1024];
        int n = /*EL:48*/1;
        int n2 = /*EL:49*/0;
        boolean b = /*EL:50*/false;
        /*SL:52*/array[0] = -1;
        /*SL:54*/for (boolean a2 = 0 != 0; a2 < charArray.length; ++a2) {
            /*SL:56*/a2 = (a2 > 0 && charArray[a2 - 1] == '\\');
            /*SL:57*/if (!a2) {
                /*SL:59*/if (charArray[a2] == '\"') {
                    /*SL:61*/b = !b;
                }
                /*SL:64*/if ((charArray[a2] == '(' || charArray[a2] == '[' || charArray[a2] == '{') && !b) {
                    /*SL:66*/++n2;
                }
                /*SL:68*/if ((charArray[a2] == ')' || charArray[a2] == ']' || charArray[a2] == '}') && !b) {
                    /*SL:70*/--n2;
                }
                /*SL:72*/if (n2 < 0) {
                    /*SL:74*/array[n] = a2;
                    /*SL:75*/break;
                }
                /*SL:77*/if (charArray[a2] == v-6 && n2 == 0 && !b) {
                    /*SL:79*/array[n] = a2;
                    /*SL:80*/++n;
                }
            }
        }
        /*SL:84*/if (n2 >= 0) {
            array[n] = v-7.length();
        }
        final String[] v0 = /*EL:87*/new String[n];
        /*SL:88*/for (int v = 0; v < v0.length; ++v) {
            /*SL:90*/v0[v] = v-7.substring(array[v] + 1, array[v + 1]);
        }
        /*SL:93*/return v0;
    }
    
    public static int getEndPoint(final String v1, final int v2, final char... v3) {
        final char[] v4 = /*EL:98*/v1.toCharArray();
        int v5 = /*EL:99*/v4.length;
        /*SL:100*/for (char a3 = (char)v2; a3 < v4.length; ++a3) {
            boolean a2 = /*EL:102*/false;
            /*SL:103*/for (final char a3 : v3) {
                /*SL:105*/if (v4[a3] == a3) {
                    /*SL:107*/v5 = a3;
                    /*SL:108*/a2 = true;
                    /*SL:109*/break;
                }
            }
            /*SL:112*/if (a2) {
                break;
            }
        }
        /*SL:114*/return v5;
    }
}
