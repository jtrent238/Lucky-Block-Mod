package mod.lucky.drop;

import mod.lucky.drop.value.ExpressionParserOld;
import java.util.Random;

public class LuckyDropRaw
{
    private String rawDrop;
    private int luck;
    private float chance;
    private final Random random;
    
    public LuckyDropRaw(String v2) {
        this.luck = 2;
        this.chance = 1.0f;
        if ((v2.startsWith("(") && v2.endsWith(")")) || (v2.startsWith("[") && v2.endsWith("]")) || (v2.startsWith("{") && v2.endsWith("}"))) {
            v2 = v2.substring(1, v2.length() - 1);
        }
        this.rawDrop = v2;
        this.random = new Random();
        try {
            this.getDropProperties();
        }
        catch (ExpressionParserOld.ExpressionParserException a1) {
            System.out.println("The Lucky Block encountered and error while loading properties for drop: " + v2);
            a1.printStackTrace();
        }
    }
    
    public LuckyDropRaw() {
        this.luck = 2;
        this.chance = 1.0f;
        this.random = new Random();
    }
    
    public void getDropProperties() throws ExpressionParserOld.ExpressionParserException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       mod/lucky/drop/value/ExpressionParserOld.instance:Lmod/lucky/drop/value/ExpressionParserOld;
        //     3: astore_1        /* v-4 */
        //     4: new             Ljava/util/ArrayList;
        //     7: dup            
        //     8: invokespecial   java/util/ArrayList.<init>:()V
        //    11: astore_2        /* v-3 */
        //    12: iconst_0       
        //    13: istore_3        /* v-2 */
        //    14: aload_0         /* v-5 */
        //    15: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //    18: ldc             "@chance="
        //    20: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    23: istore          v-1
        //    25: aload_0         /* v-5 */
        //    26: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //    29: ldc             "@luck="
        //    31: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    34: istore          v0
        //    36: iload           v-1
        //    38: iload           v0
        //    40: if_icmpge       49
        //    43: iload           v-1
        //    45: iconst_m1      
        //    46: if_icmpne       61
        //    49: iload           v0
        //    51: iconst_m1      
        //    52: if_icmpne       84
        //    55: iload           v-1
        //    57: iconst_m1      
        //    58: if_icmple       84
        //    61: iconst_0       
        //    62: istore          v1
        //    64: ldc             "@chance="
        //    66: invokevirtual   java/lang/String.length:()I
        //    69: istore          v2
        //    71: aload_2         /* v-3 */
        //    72: iload_3         /* v-2 */
        //    73: iload           v-1
        //    75: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    78: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
        //    81: goto            129
        //    84: iload           v0
        //    86: iload           v-1
        //    88: if_icmpge       97
        //    91: iload           v0
        //    93: iconst_m1      
        //    94: if_icmpne       109
        //    97: iload           v-1
        //    99: iconst_m1      
        //   100: if_icmpne       363
        //   103: iload           v0
        //   105: iconst_m1      
        //   106: if_icmple       363
        //   109: iconst_1       
        //   110: istore          v1
        //   112: ldc             "@luck="
        //   114: invokevirtual   java/lang/String.length:()I
        //   117: istore          v2
        //   119: aload_2         /* v-3 */
        //   120: iload_3         /* v-2 */
        //   121: iload           v0
        //   123: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   126: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
        //   129: bipush          8
        //   131: newarray        C
        //   133: dup            
        //   134: iconst_0       
        //   135: bipush          43
        //   137: castore        
        //   138: dup            
        //   139: iconst_1       
        //   140: bipush          42
        //   142: castore        
        //   143: dup            
        //   144: iconst_2       
        //   145: bipush          40
        //   147: castore        
        //   148: dup            
        //   149: iconst_3       
        //   150: bipush          41
        //   152: castore        
        //   153: dup            
        //   154: iconst_4       
        //   155: bipush          44
        //   157: castore        
        //   158: dup            
        //   159: iconst_5       
        //   160: bipush          59
        //   162: castore        
        //   163: dup            
        //   164: bipush          6
        //   166: bipush          47
        //   168: castore        
        //   169: dup            
        //   170: bipush          7
        //   172: bipush          64
        //   174: castore        
        //   175: astore          v3
        //   177: aload_1         /* v-4 */
        //   178: aload_0         /* v-5 */
        //   179: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   182: aload_2         /* v-3 */
        //   183: iload_3         /* v-2 */
        //   184: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   187: checkcast       Ljava/lang/Integer;
        //   190: invokevirtual   java/lang/Integer.intValue:()I
        //   193: iload           7
        //   195: iadd           
        //   196: aload           v3
        //   198: invokevirtual   mod/lucky/drop/value/ExpressionParserOld.getEndPoint:(Ljava/lang/String;I[C)I
        //   201: istore          v4
        //   203: aload_0         /* v-5 */
        //   204: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   207: aload_2         /* v-3 */
        //   208: iload_3         /* v-2 */
        //   209: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   212: checkcast       Ljava/lang/Integer;
        //   215: invokevirtual   java/lang/Integer.intValue:()I
        //   218: iload           7
        //   220: iadd           
        //   221: iload           v4
        //   223: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   226: astore          v5
        //   228: aload           v5
        //   230: ldc             "("
        //   232: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   235: ifeq            263
        //   238: aload           v5
        //   240: ldc             ")"
        //   242: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   245: ifeq            263
        //   248: aload           v5
        //   250: iconst_1       
        //   251: aload           v5
        //   253: invokevirtual   java/lang/String.length:()I
        //   256: iconst_1       
        //   257: isub           
        //   258: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   261: astore          v5
        //   263: iload           6
        //   265: ifne            278
        //   268: aload_0         /* v-5 */
        //   269: aload_1         /* v-4 */
        //   270: aload           v5
        //   272: invokevirtual   mod/lucky/drop/value/ExpressionParserOld.getFloat:(Ljava/lang/String;)F
        //   275: putfield        mod/lucky/drop/LuckyDropRaw.chance:F
        //   278: iload           6
        //   280: iconst_1       
        //   281: if_icmpne       294
        //   284: aload_0         /* v-5 */
        //   285: aload_1         /* v-4 */
        //   286: aload           v5
        //   288: invokevirtual   mod/lucky/drop/value/ExpressionParserOld.getInteger:(Ljava/lang/String;)I
        //   291: putfield        mod/lucky/drop/LuckyDropRaw.luck:I
        //   294: aload_0         /* v-5 */
        //   295: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   298: iconst_0       
        //   299: aload_2         /* v-3 */
        //   300: iload_3         /* v-2 */
        //   301: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   304: checkcast       Ljava/lang/Integer;
        //   307: invokevirtual   java/lang/Integer.intValue:()I
        //   310: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   313: astore          v6
        //   315: aload_0         /* v-5 */
        //   316: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   319: iload           v4
        //   321: aload_0         /* v-5 */
        //   322: getfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   325: invokevirtual   java/lang/String.length:()I
        //   328: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   331: astore          v7
        //   333: aload_0         /* v-5 */
        //   334: new             Ljava/lang/StringBuilder;
        //   337: dup            
        //   338: invokespecial   java/lang/StringBuilder.<init>:()V
        //   341: aload           v6
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: aload           v7
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   354: putfield        mod/lucky/drop/LuckyDropRaw.rawDrop:Ljava/lang/String;
        //   357: iinc            v-2, 1
        //   360: goto            14
        //   363: return         
        //    Exceptions:
        //  throws mod.lucky.drop.value.ExpressionParserOld.ExpressionParserException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------------------------
        //  64     20      6     v1    I
        //  71     13      7     v2    I
        //  25     335     4     v-1   I
        //  36     324     5     v0    I
        //  112    17      6     v1    I
        //  119    10      7     v2    I
        //  177    183     8     v3    [C
        //  203    157     9     v4    I
        //  228    132     10    v5    Ljava/lang/String;
        //  315    45      11    v6    Ljava/lang/String;
        //  333    27      12    v7    Ljava/lang/String;
        //  0      364     0     v-5   Lmod/lucky/drop/LuckyDropRaw;
        //  4      360     1     v-4   Lmod/lucky/drop/value/ExpressionParserOld;
        //  12     352     2     v-3   Ljava/util/ArrayList;
        //  14     350     3     v-2   I
        //    LocalVariableTypeTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------------------------
        //  12     352     2     v-3   Ljava/util/ArrayList<Ljava/lang/Integer;>;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3037)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2446)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:109)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at cuchaz.enigma.Deobfuscator.getSourceTree(Deobfuscator.java:224)
        //     at cuchaz.enigma.Deobfuscator.writeSources(Deobfuscator.java:306)
        //     at cuchaz.enigma.gui.GuiController$1.run(GuiController.java:110)
        //     at cuchaz.enigma.gui.ProgressDialog$1.run(ProgressDialog.java:98)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public LuckyDropRaw copy() {
        final LuckyDropRaw v1 = /*EL:87*/new LuckyDropRaw("");
        /*SL:89*/v1.rawDrop = this.rawDrop;
        /*SL:90*/v1.luck = this.luck;
        /*SL:91*/v1.chance = this.chance;
        /*SL:93*/return v1;
    }
    
    public String getDropValue() {
        /*SL:98*/return this.rawDrop;
    }
    
    public void setDropValue(final String a1) {
        /*SL:103*/this.rawDrop = a1;
    }
    
    public int getLuck() {
        /*SL:108*/return this.luck;
    }
    
    public void setLuck(final int a1) {
        /*SL:113*/this.luck = a1;
    }
    
    public float getChance() {
        /*SL:118*/return this.chance;
    }
    
    public void setChance(final float a1) {
        /*SL:123*/this.chance = a1;
    }
    
    @Override
    public String toString() {
        /*SL:129*/return this.rawDrop;
    }
}
