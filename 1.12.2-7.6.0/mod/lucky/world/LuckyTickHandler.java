package mod.lucky.world;

import mod.lucky.drop.func.DropProcessData;
import mod.lucky.Lucky;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.ITextComponent;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import mod.lucky.drop.func.DropProcessor;
import java.util.concurrent.ConcurrentHashMap;

public class LuckyTickHandler
{
    private ConcurrentHashMap<Integer, Object> delayDrops;
    private boolean shownUpdateVersion;
    private DropProcessor dropProcessor;
    
    public LuckyTickHandler() {
        this.shownUpdateVersion = true;
        try {
            this.delayDrops = new ConcurrentHashMap<Integer, Object>();
            this.dropProcessor = new DropProcessor();
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(final TickEvent.ClientTickEvent v-9) {
        try {
            /*SL:38*/if (Minecraft.func_71410_x().field_71439_g != null && this.shownUpdateVersion) {
                /*SL:39*/this.shownUpdateVersion = false;
                final URL url = /*EL:41*/new URL("http://www.minecraftascending.com/projects/lucky_block/download/version/version_log.txt");
                final BufferedReader bufferedReader = /*EL:44*/new BufferedReader(new InputStreamReader(url.openStream()));
                final int intValue = /*EL:46*/Integer.valueOf("7.6.0".replace(".", ""));
                final int intValue2 = /*EL:47*/Integer.valueOf("1.12.2".replace(".", ""));
                String line;
                /*SL:50*/while ((line = bufferedReader.readLine()) != null) {
                    final String[] split = /*EL:51*/line.split("\\|");
                    final int intValue3 = /*EL:52*/Integer.valueOf(split[0].replace(".", ""));
                    final int intValue4 = /*EL:53*/Integer.valueOf(split[1].replace(".", ""));
                    /*SL:55*/if (intValue4 >= intValue2 && intValue3 > intValue) {
                        final String a1 = /*EL:56*/split[2];
                        final ITextComponent v1 = /*EL:57*/ITextComponent.Serializer.func_150699_a(a1);
                        /*SL:58*/Minecraft.func_71410_x().field_71439_g.func_145747_a(/*EL:60*/TextComponentUtils.func_179985_a((ICommandSender)null, v1, (Entity)null));
                        /*SL:61*/break;
                    }
                }
            }
        }
        catch (Exception ex) {
            /*SL:66*/ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent v0) {
        try {
            /*SL:73*/for (int v = 0; v > -1; ++v) {
                /*SL:74*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                    final DelayLuckyDrop a1 = /*EL:75*/this.delayDrops.get(v);
                    /*SL:76*/a1.update();
                    /*SL:77*/if (a1.finished()) {
                        /*SL:78*/this.delayDrops.remove(v);
                        /*SL:79*/if (this.delayDrops.containsKey(v + 1)) {
                            this.delayDrops.put(v, 0);
                        }
                    }
                }
                /*SL:82*/if (!this.delayDrops.containsKey(v)) {
                    break;
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:85*/"Lucky Block: Error processing delay drop");
            /*SL:86*/v2.printStackTrace();
            /*SL:87*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkSave(final ChunkDataEvent.Save v-2) {
        try {
            /*SL:94*/if (this.delayDrops.size() > 0) {
                boolean b = /*EL:95*/false;
                final NBTTagList v0 = /*EL:96*/new NBTTagList();
                /*SL:98*/for (int v = 0; v > -1; ++v) {
                    /*SL:99*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                        final DelayLuckyDrop a1 = /*EL:100*/this.delayDrops.get(v);
                        /*SL:105*/if (v-2.getChunk().func_177412_p().func_175726_f(a1.getProcessData().getHarvestBlockPos()) == v-2.getChunk()) {
                            /*SL:106*/v0.func_74742_a((NBTBase)a1.writeToNBT());
                            /*SL:107*/b = true;
                        }
                    }
                    /*SL:110*/if (!this.delayDrops.containsKey(v)) {
                        break;
                    }
                }
                /*SL:113*/if (b) {
                    v-2.getData().func_74782_a("LuckyBlockDelayDrops", (NBTBase)v0);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:116*/"Lucky Block: Error saving chunk properties");
            /*SL:117*/ex.printStackTrace();
            /*SL:118*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkLoad(final ChunkDataEvent.Load v-1) {
        try {
            /*SL:125*/if (v-1.getData().func_74764_b("LuckyBlockDelayDrops")) {
                final NBTTagList v0 = /*EL:126*/v-1.getData().func_150295_c("LuckyBlockDelayDrops", 10);
                /*SL:127*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DelayLuckyDrop a1 = /*EL:128*/new DelayLuckyDrop(Lucky.lucky_block.getDropProcessor(), /*EL:129*/null, 0L);
                    /*SL:130*/a1.readFromNBT(v0.func_150305_b(v), v-1.getChunk().func_177412_p());
                    /*SL:131*/this.addDelayDrop(a1);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:135*/"Lucky Block: Error loading chunk properties");
            /*SL:136*/v2.printStackTrace();
            /*SL:137*/this.delayDrops.clear();
        }
    }
    
    public void addDelayDrop(final DropProcessor a1, final DropProcessData a2, final float a3) {
        /*SL:142*/this.addDelayDrop(new DelayLuckyDrop(a1, a2, (long)(a3 * 20.0f)));
    }
    
    public void addDelayDrop(final DelayLuckyDrop v2) {
        /*SL:146*/for (int a1 = 0; a1 > -1; ++a1) {
            /*SL:147*/if (!this.delayDrops.containsKey(a1) || !(this.delayDrops.get(a1) instanceof DelayLuckyDrop)) {
                /*SL:148*/this.delayDrops.put(a1, v2);
                /*SL:149*/break;
            }
        }
    }
}
