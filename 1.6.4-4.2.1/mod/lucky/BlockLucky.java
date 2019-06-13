package mod.lucky;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import none.mt;
import mod.lucky.drops.LuckyDrop;
import none.ss;
import none.ye;
import mod.lucky.drops.SpawnOther;
import none.nn;
import net.minecraft.server.MinecraftServer;
import none.nj;
import mod.lucky.drops.SpawnEntity;
import mod.lucky.drops.MakeLuckyDrops;
import java.util.Random;
import none.akc;
import none.uf;
import none.abw;
import none.aqz;

public class BlockLucky extends aqz
{
    public abw world;
    public uf player;
    public int harvestX;
    public int harvestY;
    public int harvestZ;
    
    public BlockLucky(final int a1, final akc a2) {
        super(a1, a2);
    }
    
    public void a(final abw v-17, final uf v-16, final int v-15, final int v-14, final int v-13, final int v-12) {
        try {
            /*SL:56*/this.world = v-17;
            /*SL:57*/this.player = v-16;
            /*SL:58*/this.harvestX = v-15;
            /*SL:59*/this.harvestY = v-14;
            /*SL:60*/this.harvestZ = v-13;
            final Random random = /*EL:62*/new Random();
            final int nextInt = /*EL:64*/random.nextInt(Lucky.allDrops.length);
            final LuckyDrop[] drops = /*EL:65*/MakeLuckyDrops.getDrops(Lucky.allDrops[nextInt]);
            /*SL:68*/for (int n = 0; drops[n] != null; /*SL:129*/++n) {
                final LuckyDrop luckyDrop = drops[n];
                if (luckyDrop.getType().equals("entity")) {
                    SpawnEntity.spawnEntity(this.world, this.player, this.harvestX, this.harvestY, this.harvestZ, luckyDrop.getName(), luckyDrop.getID(), luckyDrop.getNBTTag(), luckyDrop.getRelativToPlayer(), luckyDrop.getXOffset(), luckyDrop.getYOffset(), luckyDrop.getZOffset(), luckyDrop.getAdjustHeight());
                }
                else if (luckyDrop.getType().equals("effect")) {
                    final nj a1 = new nj(luckyDrop.getID(), luckyDrop.getEffectDuration(), luckyDrop.getDamage());
                    this.player.c(a1);
                }
                else if (luckyDrop.getType().equals("difficulty")) {
                    MinecraftServer.F().c(luckyDrop.getID());
                }
                else if (luckyDrop.getType().equals("time")) {
                    for (int a2 = 0; a2 < MinecraftServer.F().b.length; ++a2) {
                        MinecraftServer.F().b[a2].b((long)luckyDrop.getID());
                    }
                }
                else if (luckyDrop.getType().equals("sound")) {
                    this.world.a((nn)this.player, luckyDrop.getName(), 3.0f, 1.0f);
                }
                else if (luckyDrop.getType().equals("sound") || luckyDrop.getType().equals("particle") || luckyDrop.getType().equals("block") || luckyDrop.getType().equals("structure")) {
                    SpawnOther.spawnOther(this.world, this.player, this.harvestX, this.harvestY, this.harvestZ, luckyDrop.getType(), luckyDrop.getID(), luckyDrop.getName(), luckyDrop.getDamage(), luckyDrop.getNBTTag(), luckyDrop.getRelativToPlayer(), luckyDrop.getXOffset(), luckyDrop.getYOffset(), luckyDrop.getZOffset(), luckyDrop.getAdjustHeight());
                }
                else if (!this.world.I && this.world.O().b("doTileDrops")) {
                    final float a3 = 0.7f;
                    final double a4 = this.world.s.nextFloat() * a3 + (1.0f - a3) * 0.5;
                    final double a5 = this.world.s.nextFloat() * a3 + (1.0f - a3) * 0.5;
                    final double a6 = this.world.s.nextFloat() * a3 + (1.0f - a3) * 0.5;
                    final ye v1 = new ye(luckyDrop.getID(), 1, luckyDrop.getDamage());
                    if (luckyDrop.getNBTTag() != null) {
                        v1.e = luckyDrop.getNBTTag();
                    }
                    final ss v2 = new ss(this.world, this.harvestX + a4, this.harvestY + a5, this.harvestZ + a6, v1);
                    v2.b = 10;
                    v-17.d((nn)v2);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(/*EL:134*/"The Lucky Block encountered and error while trying to perform a function. Erorr report below:");
            /*SL:135*/ex.printStackTrace();
        }
    }
    
    public int a(final int a1, final Random a2, final int a3) {
        /*SL:141*/return 0;
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(final int a1) {
        /*SL:150*/return a1 == aqz.z.cF || a1 == aqz.A.cF || a1 == aqz.J.cF;
    }
    
    public boolean f(final abw a1, final int a2, final int a3, final int a4) {
        final aqz v1 = /*EL:159*/BlockLucky.s[a1.a(a2, a3, a4)];
        final aqz v2 = /*EL:160*/BlockLucky.s[a1.a(a2, a3 - 1, a4)];
        /*SL:161*/return (a1.m(a2, a3, a4) >= 8 || a1.l(a2, a3, a4)) && v2 != null && this.canThisPlantGrowOnThisBlockID(v2.cF);
    }
    
    @SideOnly(Side.CLIENT)
    public void a(final mt a1) {
        final String v1 = /*EL:169*/this.a();
        final String v2 = /*EL:170*/v1.substring(v1.indexOf(".") + 1);
        /*SL:172*/this.cW = a1.a("lucky:" + v2);
    }
}
