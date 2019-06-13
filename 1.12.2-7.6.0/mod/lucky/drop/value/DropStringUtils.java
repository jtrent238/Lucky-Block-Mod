package mod.lucky.drop.value;

public class DropStringUtils
{
    public static String fixBackslash(String a1) {
        /*SL:5*/a1 = a1.replaceAll("\\\\t", "\t");
        /*SL:6*/a1 = a1.replaceAll("\\\\b", "\b");
        /*SL:7*/a1 = a1.replaceAll("\\\\n", "\n");
        /*SL:8*/a1 = a1.replaceAll("\\\\r", "\r");
        /*SL:9*/a1 = a1.replaceAll("\\\\f", "\f");
        /*SL:10*/return a1;
    }
    
    public static boolean hasDecimalPoint(String v1) {
        /*SL:14*/if (v1 == null) {
            return false;
        }
        /*SL:15*/v1 = removeNumSuffix(v1);
        /*SL:16*/for (int a1 = v1.length() - 1; a1 >= 0; --a1) {
            /*SL:17*/if (!Character.isDigit(v1.charAt(a1))) {
                /*SL:18*/return v1.charAt(a1) == '.';
            }
        }
        /*SL:21*/return false;
    }
    
    public static boolean isGenericFloat(final String a1) {
        /*SL:25*/return a1 != null && /*EL:26*/(a1.endsWith("f") || a1.endsWith("F") || /*EL:27*/a1.endsWith("d") || /*EL:28*/a1.endsWith("D") || hasDecimalPoint(/*EL:29*/a1));
    }
    
    public static String removeNumSuffix(final String a1) {
        /*SL:35*/if (a1 == null) {
            return null;
        }
        /*SL:42*/if (a1.endsWith("f") || a1.endsWith("F") || a1.endsWith("d") || a1.endsWith("D") || a1.endsWith("s") || a1.endsWith("S") || a1.endsWith("b") || a1.endsWith("B")) {
            /*SL:43*/return a1.substring(0, a1.length() - 1);
        }
        /*SL:44*/return a1;
    }
    
    public static String[] splitBracketString(final String v-7, final char v-6) {
        final char[] charArray = /*EL:49*/v-7.toCharArray();
        final int[] array = /*EL:50*/new int[1024];
        int n = /*EL:51*/1;
        int n2 = /*EL:52*/0;
        boolean b = /*EL:53*/false;
        /*SL:55*/array[0] = -1;
        /*SL:57*/for (boolean a2 = 0 != 0; a2 < charArray.length; ++a2) {
            /*SL:58*/a2 = (a2 > 0 && charArray[a2 - 1] == '\\');
            /*SL:59*/if (!a2) {
                /*SL:60*/if (charArray[a2] == '\"') {
                    /*SL:61*/b = !b;
                }
                /*SL:64*/if ((charArray[a2] == '(' || charArray[a2] == '[' || charArray[a2] == '{') && !b) {
                    /*SL:65*/++n2;
                }
                /*SL:67*/if ((charArray[a2] == ')' || charArray[a2] == ']' || charArray[a2] == '}') && !b) {
                    /*SL:68*/--n2;
                }
                /*SL:70*/if (n2 < 0) {
                    /*SL:71*/array[n] = a2;
                    /*SL:72*/break;
                }
                /*SL:74*/if (charArray[a2] == v-6 && n2 == 0 && !b) {
                    /*SL:75*/array[n] = a2;
                    /*SL:76*/++n;
                }
            }
        }
        /*SL:80*/if (n2 >= 0) {
            array[n] = v-7.length();
        }
        final String[] v0 = /*EL:83*/new String[n];
        /*SL:84*/for (int v = 0; v < v0.length; ++v) {
            /*SL:85*/v0[v] = v-7.substring(array[v] + 1, array[v + 1]);
        }
        /*SL:88*/return v0;
    }
    
    public static int getEndPoint(final String v1, final int v2, final char... v3) {
        final char[] v4 = /*EL:92*/v1.toCharArray();
        int v5 = /*EL:93*/v4.length;
        /*SL:94*/for (char a3 = (char)v2; a3 < v4.length; ++a3) {
            boolean a2 = /*EL:95*/false;
            /*SL:96*/for (final char a3 : v3) {
                /*SL:97*/if (v4[a3] == a3) {
                    /*SL:98*/v5 = a3;
                    /*SL:99*/a2 = true;
                    /*SL:100*/break;
                }
            }
            /*SL:103*/if (a2) {
                break;
            }
        }
        /*SL:105*/return v5;
    }
}
