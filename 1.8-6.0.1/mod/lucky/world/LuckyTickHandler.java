package mod.lucky.world;

import mod.lucky.drop.func.DropProcessData;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.ArrayList;

public class LuckyTickHandler
{
    private ArrayList<DelayLuckyDrop> delayDrops;
    
    public LuckyTickHandler() {
        this.delayDrops = new ArrayList<DelayLuckyDrop>();
    }
    
    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent v0) {
        try {
            /*SL:26*/if (this.delayDrops.size() > 0) {
                final Iterator<DelayLuckyDrop> v = /*EL:28*/this.delayDrops.iterator();
                /*SL:29*/while (v.hasNext()) {
                    final DelayLuckyDrop a1 = /*EL:31*/v.next();
                    /*SL:32*/a1.update();
                    /*SL:33*/if (a1.finished()) {
                        v.remove();
                    }
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:39*/"Lucky Block: Error processing delay drop");
            /*SL:40*/v2.printStackTrace();
            /*SL:41*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkSave(final ChunkDataEvent.Save v0) {
        try {
            /*SL:50*/if (this.delayDrops.size() > 0) {
                boolean v = /*EL:52*/false;
                final NBTTagList v2 = /*EL:53*/new NBTTagList();
                /*SL:54*/for (final DelayLuckyDrop a1 : this.delayDrops) {
                    /*SL:56*/if (v0.getChunk().func_177412_p().func_175726_f(a1.getProcessData().getHarvestPos()) == v0.getChunk()) {
                        /*SL:58*/v2.func_74742_a((NBTBase)a1.writeToNBT());
                        /*SL:59*/v = true;
                    }
                }
                /*SL:63*/if (v) {
                    v0.getData().func_74782_a("LuckyBlockDelayDrops", (NBTBase)v2);
                }
            }
        }
        catch (Exception v3) {
            System.err.println(/*EL:68*/"Lucky Block: Error saving chunk properties");
            /*SL:69*/v3.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onChunkLoad(final ChunkDataEvent.Load v-1) {
        try {
            /*SL:78*/if (v-1.getData().func_74764_b("LuckyBlockDelayDrops")) {
                final NBTTagList v0 = /*EL:80*/v-1.getData().func_150295_c("LuckyBlockDelayDrops", 10);
                /*SL:81*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DelayLuckyDrop a1 = /*EL:83*/new DelayLuckyDrop(null, 0L);
                    /*SL:84*/a1.readFromNBT(v0.func_150305_b(v), v-1.getChunk().func_177412_p());
                    /*SL:85*/this.delayDrops.add(a1);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:91*/"Lucky Block: Error loading chunk properties");
            /*SL:92*/v2.printStackTrace();
        }
    }
    
    public void addDelayDrop(final DropProcessData a1) {
        /*SL:98*/this.delayDrops.add(new DelayLuckyDrop(a1, (long)(a1.getDropProperties().getPropertyFloat("delay") * 20.0f)));
    }
}
