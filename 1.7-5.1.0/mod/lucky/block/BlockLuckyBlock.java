package mod.lucky.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.init.Blocks;
import net.minecraft.world.WorldServer;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.potion.PotionEffect;
import mod.lucky.drop.LuckyDrop;
import mod.lucky.util.ChooseLuckyDrop;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTBase;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import mod.lucky.Lucky;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.util.ChunkCoordinates;
import mod.lucky.command.LuckyCommandLogic;
import mod.lucky.drop.LuckyDropBase;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import java.util.Random;
import mod.lucky.util.MakeLuckyDrops;
import mod.lucky.util.SpawnEntity;
import mod.lucky.util.SpawnOther;
import net.minecraft.block.BlockContainer;

public class BlockLuckyBlock extends BlockContainer
{
    public SpawnOther spawnOther;
    public SpawnEntity spawnEntity;
    public MakeLuckyDrops makeLuckyDrops;
    private final Random random;
    
    public BlockLuckyBlock(final Material a1) {
        super(a1);
        this.random = new Random();
        this.spawnEntity = new SpawnEntity();
        this.spawnOther = new SpawnOther();
        this.makeLuckyDrops = new MakeLuckyDrops();
    }
    
    public void func_149695_a(final World a1, final int a2, final int a3, final int a4, final Block a5) {
        /*SL:58*/if (a1.func_72864_z(a2, a3, a4)) {
            /*SL:60*/this.removeLuckyBlock(a1, null, a2, a3, a4, true);
        }
    }
    
    public boolean removedByPlayer(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5) {
        /*SL:67*/return this.removeLuckyBlock(a1, a2, a3, a4, a5, false);
    }
    
    public boolean removeLuckyBlock(final World v-14, EntityPlayer v-13, final int v-12, final int v-11, final int v-10, final boolean v-9) {
        try {
            int luck = /*EL:74*/0;
            String[] drops = /*EL:75*/null;
            LuckyDropBase[] v-15 = /*EL:76*/null;
            final TileEntityLuckyBlock tileEntityLuckyBlock = /*EL:78*/(TileEntityLuckyBlock)v-14.func_147438_o(v-12, v-11, v-10);
            /*SL:79*/if (tileEntityLuckyBlock != null) {
                /*SL:81*/luck = tileEntityLuckyBlock.getLuck();
                /*SL:82*/drops = tileEntityLuckyBlock.getDrops();
                /*SL:83*/if (drops != null && drops.length != 0) {
                    /*SL:85*/v-15 = new LuckyDropBase[drops.length];
                    /*SL:86*/for (int a1 = 0; a1 < v-15.length; ++a1) {
                        /*SL:88*/v-15[a1] = new LuckyDropBase(drops[a1]);
                    }
                }
                /*SL:91*/v-14.func_147475_p(v-12, v-11, v-10);
            }
            /*SL:94*/if (!v-14.func_147468_f(v-12, v-11, v-10)) {
                return false;
            }
            /*SL:96*/if (!v-14.field_72995_K) {
                /*SL:98*/if (v-9) {
                    final LuckyCommandLogic a2 = /*EL:100*/new LuckyCommandLogic();
                    /*SL:101*/a2.setWorld(v-14);
                    /*SL:102*/a2.setCommandCoordinates(new ChunkCoordinates(v-12, v-11, v-10));
                    /*SL:103*/v-13 = (EntityPlayer)PlayerSelector.func_82386_a((ICommandSender)a2, "@p");
                }
                /*SL:106*/if (v-13.field_71075_bZ.field_75098_d && !Lucky.instance.doDropsOnCreativeMode && !v-9) {
                    /*SL:108*/return true;
                }
                /*SL:110*/if (EnchantmentHelper.func_77502_d((EntityLivingBase)v-13) && !v-9) {
                    final ItemStack a3 = /*EL:112*/new ItemStack(Lucky.lucky_block);
                    final NBTTagCompound a4 = /*EL:113*/new NBTTagCompound();
                    /*SL:115*/if (luck != 0) {
                        a4.func_74768_a("Luck", luck);
                    }
                    /*SL:116*/if (drops != null && drops.length != 0) {
                        a4.func_74782_a("Drops", (NBTBase)LuckyFunction.getNBTTagListFromArray(drops));
                    }
                    /*SL:117*/if (a4.func_74764_b("Luck") || a4.func_74764_b("Drops")) {
                        a3.func_77982_d(a4);
                    }
                    final float a5 = /*EL:119*/0.7f;
                    final double a6 = /*EL:120*/v-14.field_73012_v.nextFloat() * a5 + (1.0f - a5) * 0.5;
                    final double v1 = /*EL:121*/v-14.field_73012_v.nextFloat() * a5 + (1.0f - a5) * 0.5;
                    final double v2 = /*EL:122*/v-14.field_73012_v.nextFloat() * a5 + (1.0f - a5) * 0.5;
                    final EntityItem v3 = /*EL:123*/new EntityItem(v-14, v-12 + a6, v-11 + v1, v-10 + v2, a3);
                    /*SL:124*/v3.field_145804_b = 10;
                    /*SL:125*/v-14.func_72838_d((Entity)v3);
                    /*SL:127*/return true;
                }
                final LuckyDrop[] v-16 = /*EL:131*/(v-15 != null && v-15.length != 0) ? this.makeLuckyDrops.getDrops(ChooseLuckyDrop.chooseDrop(v-15, luck), v-14, v-13, v-12, v-11, v-10) : this.makeLuckyDrops.getDrops(ChooseLuckyDrop.chooseDrop(Lucky.instance.allDrops, luck), v-14, v-13, v-12, v-11, v-10);
                /*SL:132*/this.doDrop(v-16, v-14, v-13, v-14.field_73012_v, v-12, v-11, v-10);
                /*SL:134*/return true;
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:141*/"The Lucky Block encountered and error while trying to perform a function. Error report below:");
            /*SL:142*/ex.printStackTrace();
        }
        /*SL:145*/return true;
    }
    
    public void doDrop(final LuckyDrop[] v-16, final World v-15, final EntityPlayer v-14, final Random v-13, final int v-12, final int v-11, final int v-10) {
        try {
            /*SL:152*/this.spawnEntity.setRandom(v-13);
            /*SL:155*/for (int n = 0; v-16[n] != null; /*SL:240*/++n) {
                final LuckyDrop luckyDrop = v-16[n];
                if (luckyDrop.getType().equals("entity")) {
                    this.spawnEntity.spawnEntity(v-15, v-14, luckyDrop);
                }
                else if (luckyDrop.getType().equals("effect")) {
                    final PotionEffect a1 = new PotionEffect((int)Integer.valueOf(luckyDrop.getId()), luckyDrop.getEffectDuration(), luckyDrop.getDamage());
                    v-14.func_70690_d(a1);
                }
                else if (luckyDrop.getType().equals("difficulty")) {
                    final EnumDifficulty a2 = (!luckyDrop.getId().equalsIgnoreCase("peaceful") && !luckyDrop.getId().equalsIgnoreCase("p")) ? ((!luckyDrop.getId().equalsIgnoreCase("easy") && !luckyDrop.getId().equalsIgnoreCase("e")) ? ((!luckyDrop.getId().equalsIgnoreCase("normal") && !luckyDrop.getId().equalsIgnoreCase("n")) ? ((!luckyDrop.getId().equalsIgnoreCase("hard") && !luckyDrop.getId().equalsIgnoreCase("h")) ? EnumDifficulty.func_151523_a((int)Integer.valueOf(luckyDrop.getId())) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
                    MinecraftServer.func_71276_C().func_147139_a(a2);
                }
                else if (luckyDrop.getType().equals("time")) {
                    for (final WorldServer a6 : MinecraftServer.func_71276_C().field_71305_c) {
                        a6.func_72877_b((long)Long.valueOf(luckyDrop.getId()));
                    }
                }
                else if (luckyDrop.getType().equals("sound")) {
                    v-15.func_72956_a((Entity)v-14, luckyDrop.getId(), 3.0f, 1.0f);
                }
                else if (luckyDrop.getType().equals("command")) {
                    final LuckyCommandLogic a7 = new LuckyCommandLogic();
                    a7.setWorld(v-15);
                    a7.setCommandCoordinates(new ChunkCoordinates(luckyDrop.getPosX(), luckyDrop.getPosY(), luckyDrop.getPosZ()));
                    a7.setCommand(luckyDrop.getId());
                    a7.setCommandSender(luckyDrop.getCommandSender());
                    a7.setDoOutput(luckyDrop.getDisplayCommandOutput());
                    a7.executeCommand();
                }
                else if (luckyDrop.getType().equals("message")) {
                    MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(luckyDrop.getId()));
                }
                else if (luckyDrop.getType().equals("particle") || luckyDrop.getType().equals("block") || luckyDrop.getType().equals("structure") || luckyDrop.getType().equals("falling_block")) {
                    this.spawnOther.spawnOther(v-15, v-14, v-13, luckyDrop);
                }
                else if (!v-15.field_72995_K && v-15.func_82736_K().func_82766_b("doTileDrops")) {
                    final float n2 = 0.7f;
                    final double n3 = v-15.field_73012_v.nextFloat() * n2 + (1.0f - n2) * 0.5;
                    final double n4 = v-15.field_73012_v.nextFloat() * n2 + (1.0f - n2) * 0.5;
                    final double n5 = v-15.field_73012_v.nextFloat() * n2 + (1.0f - n2) * 0.5;
                    ItemStack v2;
                    try {
                        final int v1 = Integer.valueOf(luckyDrop.getId());
                        v2 = new ItemStack((Item)Item.field_150901_e.func_148754_a(v1), 1, luckyDrop.getDamage());
                    }
                    catch (NumberFormatException v4) {
                        v2 = new ItemStack((Item)Item.field_150901_e.func_82594_a(luckyDrop.getId()), 1, luckyDrop.getDamage());
                    }
                    v2.func_77982_d(luckyDrop.getNBTTag());
                    final EntityItem v3 = new EntityItem(v-15, luckyDrop.getPosX() + n3, luckyDrop.getPosY() + n4, luckyDrop.getPosZ() + n5, v2);
                    v3.field_145804_b = 10;
                    v-15.func_72838_d((Entity)v3);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:245*/"The Lucky Block encountered and error while trying to perform a function. Error report below:");
            /*SL:246*/ex.printStackTrace();
        }
    }
    
    public Item func_149650_a(final int a1, final Random a2, final int a3) {
        /*SL:253*/return null;
    }
    
    private boolean canStayOnBlock(final Block a1) {
        /*SL:258*/return a1 == Blocks.field_150349_c || a1 == Blocks.field_150346_d || a1 == Blocks.field_150354_m || a1 == Blocks.field_150348_b || a1 == Blocks.field_150351_n || a1 == Blocks.field_150424_aL || a1 == Blocks.field_150425_aM || a1 == Blocks.field_150385_bj || a1 == Blocks.field_150385_bj || a1 == Blocks.field_150377_bs;
    }
    
    public boolean func_149718_j(final World a1, final int a2, final int a3, final int a4) {
        final Block v1 = /*EL:264*/a1.func_147439_a(a2, a3, a4);
        final Block v2 = /*EL:265*/a1.func_147439_a(a2, a3 - 1, a4);
        /*SL:266*/return (a1.func_72883_k(a2, a3, a4) >= 8 || a1.func_72937_j(a2, a3, a4)) && v2 != null && this.canStayOnBlock(v2);
    }
    
    public TileEntity func_149915_a(final World a1, final int a2) {
        /*SL:272*/return new TileEntityLuckyBlock();
    }
    
    public void func_149689_a(final World a1, final int a2, final int a3, final int a4, final EntityLivingBase a5, final ItemStack a6) {
        final TileEntityLuckyBlock v1 = /*EL:278*/(TileEntityLuckyBlock)a1.func_147438_o(a2, a3, a4);
        /*SL:279*/if (v1 == null) {
            return;
        }
        final int v2 = /*EL:281*/ItemLuckyBlock.getLuck(a6);
        final String[] v3 = /*EL:282*/ItemLuckyBlock.getDrops(a6);
        /*SL:284*/if (v2 != 0) {
            v1.setLuck(v2);
        }
        /*SL:285*/if (v3 != null && v3 != null && v3.length != 0) {
            v1.setDrops(v3);
        }
        /*SL:287*/v1.func_70296_d();
        /*SL:288*/a1.func_147471_g(a2, a3, a4);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_149651_a(final IIconRegister a1) {
        /*SL:295*/this.field_149761_L = a1.func_94245_a("lucky:lucky_block");
    }
}
