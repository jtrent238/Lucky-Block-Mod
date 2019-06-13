package mod.lucky.block;

import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.nbt.NBTBase;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import mod.lucky.command.LuckyCommandLogic;
import mod.lucky.drop.DropContainer;
import java.util.ArrayList;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import java.util.Random;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.world.LuckyGenerator;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.block.BlockContainer;

public class BlockLuckyBlock extends BlockContainer
{
    private DropProcessor dropProcessor;
    private LuckyGenerator worldGenerator;
    private LuckyCrafting crafting;
    private boolean creativeModeDrops;
    private final Random random;
    
    public BlockLuckyBlock(final Material a1) {
        super(a1);
        this.creativeModeDrops = false;
        this.random = new Random();
        this.dropProcessor = new DropProcessor();
        this.worldGenerator = new LuckyGenerator(this);
        this.crafting = new LuckyCrafting((Block)this);
    }
    
    public void func_176213_c(final World a1, final BlockPos a2, final IBlockState a3) {
        /*SL:51*/a1.func_175689_h(a2);
    }
    
    public void func_176204_a(final World a1, final BlockPos a2, final IBlockState a3, final Block a4) {
        /*SL:57*/if (a1.func_175640_z(a2)) {
            /*SL:59*/this.removeLuckyBlock(a1, null, a2, true);
        }
    }
    
    public boolean removedByPlayer(final World a1, final BlockPos a2, final EntityPlayer a3, final boolean a4) {
        /*SL:66*/return this.removeLuckyBlock(a1, a3, a2, false);
    }
    
    public boolean removeLuckyBlock(final World v-8, EntityPlayer v-7, final BlockPos v-6, final boolean v-5) {
        try {
            int luck = /*EL:73*/0;
            String[] drops = /*EL:74*/null;
            ArrayList<DropContainer> a5 = /*EL:75*/null;
            final TileEntityLuckyBlock tileEntityLuckyBlock = /*EL:77*/(TileEntityLuckyBlock)v-8.func_175625_s(v-6);
            /*SL:78*/if (tileEntityLuckyBlock != null) {
                /*SL:80*/luck = tileEntityLuckyBlock.getLuck();
                /*SL:81*/drops = tileEntityLuckyBlock.getDrops();
                /*SL:82*/if (drops != null && drops.length != 0) {
                    /*SL:84*/a5 = new ArrayList<DropContainer>();
                    /*SL:85*/for (DropContainer a2 : drops) {
                        /*SL:87*/a2 = new DropContainer();
                        /*SL:88*/a2.readFromString(a2);
                        /*SL:89*/a5.add(a2);
                    }
                }
                /*SL:92*/v-8.func_175713_t(v-6);
            }
            /*SL:95*/if (!v-8.func_175698_g(v-6)) {
                return false;
            }
            /*SL:97*/if (!v-8.field_72995_K) {
                /*SL:99*/if (v-5) {
                    final LuckyCommandLogic a3 = /*EL:101*/new LuckyCommandLogic();
                    /*SL:102*/a3.setWorld(v-8);
                    /*SL:103*/a3.setPosition(v-6);
                    /*SL:104*/v-7 = (EntityPlayer)PlayerSelector.func_82386_a((ICommandSender)a3, "@p");
                }
                /*SL:107*/if (v-7.field_71075_bZ.field_75098_d && !this.creativeModeDrops && !v-5) {
                    /*SL:109*/return true;
                }
                /*SL:111*/if (EnchantmentHelper.func_77502_d((EntityLivingBase)v-7) && !v-5) {
                    final ItemStack a4 = /*EL:113*/new ItemStack((Block)this);
                    final NBTTagCompound v1 = /*EL:114*/new NBTTagCompound();
                    /*SL:116*/if (luck != 0) {
                        v1.func_74768_a("Luck", luck);
                    }
                    /*SL:117*/if (a5 != null) {
                        v1.func_74782_a("Drops", (NBTBase)LuckyFunction.getNBTTagListFromStringArray(drops));
                    }
                    /*SL:118*/if (v1.func_74764_b("Luck") || a5 != null) {
                        a4.func_77982_d(v1);
                    }
                    /*SL:120*/Block.func_180635_a(v-8, v-6, a4);
                    /*SL:121*/return true;
                }
                /*SL:125*/if (a5 != null) {
                    this.getDropProcessor().processRandomDrop(a5, new DropProcessData(this, v-8, v-7, v-6), luck);
                }
                else {
                    /*SL:126*/this.getDropProcessor().processRandomDrop(new DropProcessData(this, v-8, v-7, v-6), luck);
                }
                /*SL:127*/return true;
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:133*/"The Lucky Block encountered and error while trying to perform a function. Error report below:");
            /*SL:134*/ex.printStackTrace();
        }
        /*SL:137*/return true;
    }
    
    public Item func_180660_a(final IBlockState a1, final Random a2, final int a3) {
        /*SL:143*/return null;
    }
    
    private boolean canStayOnBlock(final Block a1) {
        /*SL:148*/return a1 == Blocks.field_150349_c || a1 == Blocks.field_150346_d || a1 == Blocks.field_150354_m || a1 == Blocks.field_150348_b || a1 == Blocks.field_150351_n || a1 == Blocks.field_150424_aL || a1 == Blocks.field_150425_aM || a1 == Blocks.field_150385_bj || a1 == Blocks.field_150385_bj || a1 == Blocks.field_150377_bs;
    }
    
    public boolean canBlockStay(final World a1, final BlockPos a2) {
        final Block v1 = /*EL:153*/a1.func_180495_p(a2).func_177230_c();
        final Block v2 = /*EL:154*/a1.func_180495_p(new BlockPos(a2.func_177958_n(), a2.func_177956_o() - 1, a2.func_177952_p())).func_177230_c();
        /*SL:157*/return v1.func_176200_f(a1, a2) && v1 != Blocks.field_150355_j && v1 != Blocks.field_150358_i && v1 != Blocks.field_150353_l && v1 != Blocks.field_150356_k && (a1.getBlockLightOpacity(a2) >= 8 || a1.func_175710_j(a2)) && v2 != null && this.canStayOnBlock(v2);
    }
    
    public TileEntity func_149915_a(final World a1, final int a2) {
        /*SL:163*/return new TileEntityLuckyBlock();
    }
    
    public void func_180633_a(final World a1, final BlockPos a2, final IBlockState a3, final EntityLivingBase a4, final ItemStack a5) {
        final TileEntityLuckyBlock v1 = /*EL:169*/(TileEntityLuckyBlock)a1.func_175625_s(a2);
        /*SL:170*/if (v1 == null) {
            return;
        }
        final int v2 = /*EL:172*/ItemLuckyBlock.getLuck(a5);
        final String[] v3 = /*EL:173*/ItemLuckyBlock.getDrops(a5);
        /*SL:175*/v1.setLuck(v2);
        /*SL:176*/if (v3 != null && v3.length != 0) {
            v1.setDrops(v3);
        }
        /*SL:178*/v1.func_70296_d();
        /*SL:179*/a1.func_175689_h(a2);
        /*SL:181*/if (a1.func_175640_z(a2) && !a1.field_72995_K) {
            this.removeLuckyBlock(a1, null, a2, true);
        }
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:186*/return this.dropProcessor;
    }
    
    public LuckyGenerator getWorldGenerator() {
        /*SL:191*/return this.worldGenerator;
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:196*/return this.crafting;
    }
    
    public boolean getCrativeModeDrops() {
        /*SL:201*/return this.creativeModeDrops;
    }
    
    public void setCrativeModeDrops(final boolean a1) {
        /*SL:206*/this.creativeModeDrops = a1;
    }
    
    public int func_149645_b() {
        /*SL:212*/return 3;
    }
}
