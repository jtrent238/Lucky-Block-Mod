package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;
import java.util.Locale;
import mod.lucky.drop.func.DropProcessData;

public class DropContainer extends DropBase
{
    private String rawDrop;
    private int luck;
    private float chance;
    private boolean setChance;
    private DropBase drop;
    
    public DropContainer() {
        this.luck = 0;
        this.chance = 1.0f;
    }
    
    public String readLuckChance(final String v-5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2        /* v-4 */
        //     8: iconst_0       
        //     9: istore_3        /* v-3 */
        //    10: aload_1         /* v-5 */
        //    11: ldc             "@chance="
        //    13: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    16: istore          v-2
        //    18: aload_1         /* v-5 */
        //    19: ldc             "@luck="
        //    21: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    24: istore          v-1
        //    26: iload           v-2
        //    28: iload           v-1
        //    30: if_icmpge       39
        //    33: iload           v-2
        //    35: iconst_m1      
        //    36: if_icmpne       51
        //    39: iload           v-1
        //    41: iconst_m1      
        //    42: if_icmpne       74
        //    45: iload           v-2
        //    47: iconst_m1      
        //    48: if_icmple       74
        //    51: iconst_0       
        //    52: istore          a1
        //    54: ldc             "@chance="
        //    56: invokevirtual   java/lang/String.length:()I
        //    59: istore          v1
        //    61: aload_2         /* v-4 */
        //    62: iload_3         /* v-3 */
        //    63: iload           v-2
        //    65: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    68: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
        //    71: goto            119
        //    74: iload           v-1
        //    76: iload           v-2
        //    78: if_icmpge       87
        //    81: iload           v-1
        //    83: iconst_m1      
        //    84: if_icmpne       99
        //    87: iload           v-2
        //    89: iconst_m1      
        //    90: if_icmpne       384
        //    93: iload           v-1
        //    95: iconst_m1      
        //    96: if_icmple       384
        //    99: iconst_1       
        //   100: istore          v0
        //   102: ldc             "@luck="
        //   104: invokevirtual   java/lang/String.length:()I
        //   107: istore          v1
        //   109: aload_2         /* v-4 */
        //   110: iload_3         /* v-3 */
        //   111: iload           v-1
        //   113: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   116: invokevirtual   java/util/ArrayList.add:(ILjava/lang/Object;)V
        //   119: bipush          8
        //   121: newarray        C
        //   123: dup            
        //   124: iconst_0       
        //   125: bipush          43
        //   127: castore        
        //   128: dup            
        //   129: iconst_1       
        //   130: bipush          42
        //   132: castore        
        //   133: dup            
        //   134: iconst_2       
        //   135: bipush          40
        //   137: castore        
        //   138: dup            
        //   139: iconst_3       
        //   140: bipush          41
        //   142: castore        
        //   143: dup            
        //   144: iconst_4       
        //   145: bipush          44
        //   147: castore        
        //   148: dup            
        //   149: iconst_5       
        //   150: bipush          59
        //   152: castore        
        //   153: dup            
        //   154: bipush          6
        //   156: bipush          47
        //   158: castore        
        //   159: dup            
        //   160: bipush          7
        //   162: bipush          64
        //   164: castore        
        //   165: astore          v2
        //   167: aload_1         /* v-5 */
        //   168: aload_2         /* v-4 */
        //   169: iload_3         /* v-3 */
        //   170: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   173: checkcast       Ljava/lang/Integer;
        //   176: invokevirtual   java/lang/Integer.intValue:()I
        //   179: iload           7
        //   181: iadd           
        //   182: iconst_1       
        //   183: newarray        C
        //   185: dup            
        //   186: iconst_0       
        //   187: bipush          64
        //   189: castore        
        //   190: invokestatic    mod/lucky/drop/value/DropStringUtils.getEndPoint:(Ljava/lang/String;I[C)I
        //   193: istore          v3
        //   195: aload_1         /* v-5 */
        //   196: aload_2         /* v-4 */
        //   197: iload_3         /* v-3 */
        //   198: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   201: checkcast       Ljava/lang/Integer;
        //   204: invokevirtual   java/lang/Integer.intValue:()I
        //   207: iload           7
        //   209: iadd           
        //   210: iload           v3
        //   212: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   215: astore          v4
        //   217: aload           v4
        //   219: ldc             "("
        //   221: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   224: ifeq            252
        //   227: aload           v4
        //   229: ldc             ")"
        //   231: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   234: ifeq            252
        //   237: aload           v4
        //   239: iconst_1       
        //   240: aload           v4
        //   242: invokevirtual   java/lang/String.length:()I
        //   245: iconst_1       
        //   246: isub           
        //   247: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   250: astore          v4
        //   252: iload           6
        //   254: ifne            274
        //   257: aload_0         /* v-6 */
        //   258: aload           v4
        //   260: invokestatic    mod/lucky/drop/value/ValueParser.getFloat:(Ljava/lang/String;)Ljava/lang/Float;
        //   263: invokevirtual   java/lang/Float.floatValue:()F
        //   266: putfield        mod/lucky/drop/DropContainer.chance:F
        //   269: aload_0         /* v-6 */
        //   270: iconst_1       
        //   271: putfield        mod/lucky/drop/DropContainer.setChance:Z
        //   274: iload           6
        //   276: iconst_1       
        //   277: if_icmpne       292
        //   280: aload_0         /* v-6 */
        //   281: aload           v4
        //   283: invokestatic    mod/lucky/drop/value/ValueParser.getInteger:(Ljava/lang/String;)Ljava/lang/Integer;
        //   286: invokevirtual   java/lang/Integer.intValue:()I
        //   289: putfield        mod/lucky/drop/DropContainer.luck:I
        //   292: goto            327
        //   295: astore          v5
        //   297: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   300: new             Ljava/lang/StringBuilder;
        //   303: dup            
        //   304: invokespecial   java/lang/StringBuilder.<init>:()V
        //   307: ldc             "Lucky Block: Error reading luck/chance for drop: "
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: aload_1         /* v-5 */
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   319: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   322: aload           v5
        //   324: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   327: aload_1         /* v-5 */
        //   328: iconst_0       
        //   329: aload_2         /* v-4 */
        //   330: iload_3         /* v-3 */
        //   331: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   334: checkcast       Ljava/lang/Integer;
        //   337: invokevirtual   java/lang/Integer.intValue:()I
        //   340: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   343: astore          v5
        //   345: aload_1         /* v-5 */
        //   346: iload           v3
        //   348: aload_1         /* v-5 */
        //   349: invokevirtual   java/lang/String.length:()I
        //   352: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   355: astore          v6
        //   357: new             Ljava/lang/StringBuilder;
        //   360: dup            
        //   361: invokespecial   java/lang/StringBuilder.<init>:()V
        //   364: aload           v5
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: aload           v6
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   377: astore_1        /* v-5 */
        //   378: iinc            v-3, 1
        //   381: goto            10
        //   384: aload_1         /* v-5 */
        //   385: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------------
        //  54     20      6     a1    I
        //  61     13      7     v1    I
        //  297    30      11    v5    Ljava/lang/Exception;
        //  18     363     4     v-2   I
        //  26     355     5     v-1   I
        //  102    17      6     v0    I
        //  109    10      7     v1    I
        //  167    214     8     v2    [C
        //  195    186     9     v3    I
        //  217    164     10    v4    Ljava/lang/String;
        //  345    36      11    v5    Ljava/lang/String;
        //  357    24      12    v6    Ljava/lang/String;
        //  0      386     0     v-6   Lmod/lucky/drop/DropContainer;
        //  0      386     1     v-5   Ljava/lang/String;
        //  8      378     2     v-4   Ljava/util/ArrayList;
        //  10     376     3     v-3   I
        //    LocalVariableTypeTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------------------------
        //  8      378     2     v-4   Ljava/util/ArrayList<Ljava/lang/Integer;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  252    292    295    327    Ljava/lang/Exception;
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
    
    public DropBase getDrop() {
        /*SL:87*/return this.drop;
    }
    
    public int getLuck() {
        /*SL:92*/return this.luck;
    }
    
    public float getChance() {
        /*SL:97*/return this.chance;
    }
    
    public void setDrop(final DropBase a1) {
        /*SL:102*/this.drop = a1;
    }
    
    public void setLuck(final int a1) {
        /*SL:107*/this.luck = a1;
    }
    
    public void setChance(final float a1) {
        /*SL:112*/this.chance = a1;
    }
    
    public boolean wasChanceSet() {
        /*SL:117*/return this.setChance;
    }
    
    public DropContainer copy() {
        final DropContainer v1 = /*EL:122*/new DropContainer();
        /*SL:123*/v1.setLuck(this.luck);
        /*SL:124*/v1.setChance(this.chance);
        /*SL:125*/v1.setDrop(this.drop);
        /*SL:126*/return v1;
    }
    
    @Override
    public DropContainer initialize(final DropProcessData a1) {
        /*SL:132*/return this;
    }
    
    @Override
    public void readFromString(String v2) {
        try {
            /*SL:140*/this.rawDrop = v2;
            /*SL:141*/v2 = this.readLuckChance(v2);
            /*SL:142*/this.readDropFromString(v2);
        }
        catch (Exception a1) {
            System.err.println(/*EL:146*/"Lucky Block: Error reading drop: " + v2);
            /*SL:147*/a1.printStackTrace();
        }
    }
    
    private void readDropFromString(final String v0) {
        /*SL:153*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            final DropGroup a1 = /*EL:155*/new DropGroup();
            /*SL:156*/a1.readFromString(v0);
            /*SL:157*/this.drop = a1;
        }
        else {
            final DropProperties v = /*EL:161*/new DropProperties();
            /*SL:162*/v.readFromString(v0);
            /*SL:163*/this.drop = v;
        }
    }
    
    @Override
    public String writeToString() {
        /*SL:170*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:182*/return null;
    }
    
    @Override
    public String toString() {
        /*SL:188*/return this.rawDrop;
    }
}
