package mod.lucky.world;

import mod.lucky.drop.func.DropProcessData;
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
import mod.lucky.Lucky;
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
            /*SL:46*/if (Minecraft.func_71410_x().field_71439_g != null && this.shownUpdateVersion) {
                /*SL:48*/this.shownUpdateVersion = false;
                final URL url = /*EL:50*/new URL("http://www.minecraftascending.com/projects/lucky_block/download/version/version_log.txt");
                final BufferedReader bufferedReader = /*EL:51*/new BufferedReader(new InputStreamReader(url.openStream()));
                final int intValue = /*EL:53*/Integer.valueOf(Lucky.VERSION.replace(".", ""));
                final int intValue2 = /*EL:54*/Integer.valueOf(Lucky.MC_VERSION.replace(".", ""));
                String line;
                /*SL:57*/while ((line = bufferedReader.readLine()) != null) {
                    final String[] split = /*EL:59*/line.split("\\|");
                    final int intValue3 = /*EL:60*/Integer.valueOf(split[0].replace(".", ""));
                    final int intValue4 = /*EL:61*/Integer.valueOf(split[1].replace(".", ""));
                    /*SL:63*/if (intValue4 >= intValue2 && intValue3 > intValue) {
                        final String a1 = /*EL:65*/split[2];
                        final ITextComponent v1 = /*EL:66*/ITextComponent.Serializer.func_150699_a(a1);
                        /*SL:67*/Minecraft.func_71410_x().field_71439_g.func_145747_a(TextComponentUtils.func_179985_a((ICommandSender)null, v1, (Entity)null));
                        /*SL:68*/break;
                    }
                }
            }
        }
        catch (Exception ex) {
            /*SL:75*/ex.printStackTrace();
        }
    }
    
    @SubscribeEvent
    public void onServerTick(final TickEvent.ServerTickEvent v0) {
        try {
            /*SL:84*/for (int v = 0; v > -1; ++v) {
                /*SL:86*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                    final DelayLuckyDrop a1 = /*EL:88*/this.delayDrops.get(v);
                    /*SL:89*/a1.update();
                    /*SL:90*/if (a1.finished()) {
                        /*SL:92*/this.delayDrops.remove(v);
                        /*SL:93*/if (this.delayDrops.containsKey(v + 1)) {
                            this.delayDrops.put(v, 0);
                        }
                    }
                }
                /*SL:96*/if (!this.delayDrops.containsKey(v)) {
                    break;
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:101*/"Lucky Block: Error processing delay drop");
            /*SL:102*/v2.printStackTrace();
            /*SL:103*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkSave(final ChunkDataEvent.Save v-2) {
        try {
            /*SL:112*/if (this.delayDrops.size() > 0) {
                boolean b = /*EL:114*/false;
                final NBTTagList v0 = /*EL:115*/new NBTTagList();
                /*SL:117*/for (int v = 0; v > -1; ++v) {
                    /*SL:119*/if (this.delayDrops.containsKey(v) && this.delayDrops.get(v) instanceof DelayLuckyDrop) {
                        final DelayLuckyDrop a1 = /*EL:121*/this.delayDrops.get(v);
                        /*SL:122*/if (v-2.getChunk().func_177412_p().func_175726_f(a1.getProcessData().getHarvestBlockPos()) == v-2.getChunk()) {
                            /*SL:124*/v0.func_74742_a((NBTBase)a1.writeToNBT());
                            /*SL:125*/b = true;
                        }
                    }
                    /*SL:128*/if (!this.delayDrops.containsKey(v)) {
                        break;
                    }
                }
                /*SL:131*/if (b) {
                    v-2.getData().func_74782_a("LuckyBlockDelayDrops", (NBTBase)v0);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:136*/"Lucky Block: Error saving chunk properties");
            /*SL:137*/ex.printStackTrace();
            /*SL:138*/this.delayDrops.clear();
        }
    }
    
    @SubscribeEvent
    public void onChunkLoad(final ChunkDataEvent.Load v-1) {
        try {
            /*SL:147*/if (v-1.getData().func_74764_b("LuckyBlockDelayDrops")) {
                final NBTTagList v0 = /*EL:149*/v-1.getData().func_150295_c("LuckyBlockDelayDrops", 10);
                /*SL:150*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DelayLuckyDrop a1 = /*EL:152*/new DelayLuckyDrop(Lucky.lucky_block.getDropProcessor(), null, 0L);
                    /*SL:153*/a1.readFromNBT(v0.func_150305_b(v), v-1.getChunk().func_177412_p());
                    /*SL:154*/this.addDelayDrop(a1);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:160*/"Lucky Block: Error loading chunk properties");
            /*SL:161*/v2.printStackTrace();
            /*SL:162*/this.delayDrops.clear();
        }
    }
    
    public void addDelayDrop(final DropProcessor a1, final DropProcessData a2, final float a3) {
        /*SL:168*/this.addDelayDrop(new DelayLuckyDrop(a1, a2, (long)(a3 * 20.0f)));
    }
    
    public void addDelayDrop(final DelayLuckyDrop v2) {
        /*SL:173*/for (int a1 = 0; a1 > -1; ++a1) {
            /*SL:175*/if (!this.delayDrops.containsKey(a1) || !(this.delayDrops.get(a1) instanceof DelayLuckyDrop)) {
                /*SL:177*/this.delayDrops.put(a1, v2);
                /*SL:178*/break;
            }
        }
    }
}
