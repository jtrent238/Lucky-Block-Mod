package mod.lucky.block;

import net.minecraft.util.EnumBlockRenderType;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.nbt.NBTBase;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;
import mod.lucky.command.LuckyCommandLogic;
import mod.lucky.drop.DropContainer;
import java.util.ArrayList;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import java.util.Random;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.world.LuckyGenerator;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.block.BlockContainer;

public class BlockLuckyBlock extends BlockContainer
{
    private DropProcessor dropProcessor;
    private LuckyGenerator worldGenerator;
    private LuckyCrafting crafting;
    private IForgeRegistryEntry.Impl<IRecipe> blockRecipe;
    private boolean creativeModeDrops;
    private final Random random;
    
    public BlockLuckyBlock(final Material a1) {
        super(a1);
        this.creativeModeDrops = false;
        this.random = new Random();
        this.func_149672_a(SoundType.field_185851_d);
        this.dropProcessor = new DropProcessor();
        this.worldGenerator = new LuckyGenerator(this);
        this.crafting = new LuckyCrafting((Block)this);
    }
    
    public void func_176213_c(final World a1, final BlockPos a2, final IBlockState a3) {
        /*SL:58*/a1.markAndNotifyBlock(a2, (Chunk)null, a3, a3, 3);
    }
    
    public void func_189540_a(final IBlockState a1, final World a2, final BlockPos a3, final Block a4, final BlockPos a5) {
        /*SL:64*/if (!a2.field_72995_K && /*EL:66*/a2.func_175640_z(a3)) {
            /*SL:68*/this.removeLuckyBlock(a2, null, a3, true);
        }
    }
    
    public boolean removedByPlayer(final IBlockState a1, final World a2, final BlockPos a3, final EntityPlayer a4, final boolean a5) {
        /*SL:76*/return this.removeLuckyBlock(a2, a4, a3, false);
    }
    
    public boolean removeLuckyBlock(final World v-8, EntityPlayer v-7, final BlockPos v-6, final boolean v-5) {
        try {
            int luck = /*EL:83*/0;
            String[] drops = /*EL:84*/null;
            ArrayList<DropContainer> a5 = /*EL:85*/null;
            final TileEntityLuckyBlock tileEntityLuckyBlock = /*EL:87*/(TileEntityLuckyBlock)v-8.func_175625_s(v-6);
            /*SL:88*/if (tileEntityLuckyBlock != null) {
                /*SL:90*/luck = tileEntityLuckyBlock.getLuck();
                /*SL:91*/drops = tileEntityLuckyBlock.getDrops();
                /*SL:92*/if (drops != null && drops.length != 0) {
                    /*SL:94*/a5 = new ArrayList<DropContainer>();
                    /*SL:95*/for (DropContainer a2 : drops) {
                        /*SL:97*/a2 = new DropContainer();
                        /*SL:98*/a2.readFromString(a2);
                        /*SL:99*/a5.add(a2);
                    }
                }
                /*SL:102*/v-8.func_175713_t(v-6);
            }
            /*SL:105*/if (!v-8.func_175698_g(v-6)) {
                return false;
            }
            /*SL:107*/if (!v-8.field_72995_K) {
                /*SL:109*/if (v-5) {
                    final LuckyCommandLogic a3 = /*EL:111*/new LuckyCommandLogic();
                    /*SL:112*/a3.setWorld(v-8);
                    /*SL:113*/a3.setPosition(v-6);
                    /*SL:114*/v-7 = (EntityPlayer)CommandBase.func_184888_a(v-8.func_73046_m(), (ICommandSender)a3, "@p");
                }
                /*SL:117*/if (v-7.field_71075_bZ.field_75098_d && !this.creativeModeDrops && !v-5) {
                    /*SL:119*/return true;
                }
                /*SL:121*/if (EnchantmentHelper.func_185284_a(Enchantments.field_185306_r, (EntityLivingBase)v-7) > 0 && !v-5) {
                    final ItemStack a4 = /*EL:123*/new ItemStack((Block)this);
                    final NBTTagCompound v1 = /*EL:124*/new NBTTagCompound();
                    /*SL:126*/if (luck != 0) {
                        v1.func_74768_a("Luck", luck);
                    }
                    /*SL:127*/if (a5 != null) {
                        v1.func_74782_a("Drops", (NBTBase)LuckyFunction.getNBTTagListFromStringArray(drops));
                    }
                    /*SL:128*/if (v1.func_74764_b("Luck") || a5 != null) {
                        a4.func_77982_d(v1);
                    }
                    /*SL:130*/Block.func_180635_a(v-8, v-6, a4);
                    /*SL:131*/return true;
                }
                /*SL:135*/if (a5 != null) {
                    this.getDropProcessor().processRandomDrop(a5, new DropProcessData(v-8, (Entity)v-7, v-6), luck);
                }
                else {
                    /*SL:136*/this.getDropProcessor().processRandomDrop(new DropProcessData(v-8, (Entity)v-7, v-6), luck);
                }
                /*SL:137*/return true;
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:143*/"The Lucky Block encountered and error while trying to perform a function. Error report below:");
            /*SL:144*/ex.printStackTrace();
        }
        /*SL:147*/return true;
    }
    
    public Item func_180660_a(final IBlockState a1, final Random a2, final int a3) {
        /*SL:153*/return null;
    }
    
    private boolean canStayOnBlock(final Block a1) {
        /*SL:158*/return a1 == Blocks.field_185774_da || a1 == Blocks.field_150346_d || a1 == Blocks.field_150354_m || a1 == Blocks.field_150348_b || a1 == Blocks.field_150351_n || a1 == Blocks.field_150424_aL || a1 == Blocks.field_150425_aM || a1 == Blocks.field_150385_bj || a1 == Blocks.field_150377_bs;
    }
    
    public boolean canBlockStay(final World a1, final BlockPos a2) {
        final Block v1 = /*EL:163*/a1.func_180495_p(a2).func_177230_c();
        final Block v2 = /*EL:164*/a1.func_180495_p(new BlockPos(a2.func_177958_n(), a2.func_177956_o() - 1, a2.func_177952_p())).func_177230_c();
        /*SL:165*/return v1.func_176200_f((IBlockAccess)a1, a2) && v1 != Blocks.field_150355_j && v1 != Blocks.field_150358_i && v1 != Blocks.field_150353_l && v1 != Blocks.field_150356_k && (a1.getBlockLightOpacity(a2) >= 8 || a1.func_175710_j(a2)) && v2 != null && this.canStayOnBlock(v2);
    }
    
    public TileEntity func_149915_a(final World a1, final int a2) {
        /*SL:171*/return new TileEntityLuckyBlock();
    }
    
    public void func_180633_a(final World a1, final BlockPos a2, final IBlockState a3, final EntityLivingBase a4, final ItemStack a5) {
        final TileEntityLuckyBlock v1 = /*EL:177*/(TileEntityLuckyBlock)a1.func_175625_s(a2);
        /*SL:178*/if (v1 == null) {
            return;
        }
        final int v2 = /*EL:180*/ItemLuckyBlock.getLuck(a5);
        final String[] v3 = /*EL:181*/ItemLuckyBlock.getDrops(a5);
        /*SL:183*/v1.setLuck(v2);
        /*SL:184*/if (v3 != null && v3.length != 0) {
            v1.setDrops(v3);
        }
        /*SL:186*/v1.func_70296_d();
        /*SL:187*/a1.markAndNotifyBlock(a2, (Chunk)null, a3, a3, 3);
        /*SL:189*/if (a1.func_175640_z(a2) && !a1.field_72995_K) {
            this.removeLuckyBlock(a1, null, a2, true);
        }
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:194*/return this.dropProcessor;
    }
    
    public LuckyGenerator getWorldGenerator() {
        /*SL:199*/return this.worldGenerator;
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:202*/return this.crafting;
    }
    
    public BlockLuckyBlock setBlockRecipe(final IForgeRegistryEntry.Impl<IRecipe> a1) {
        /*SL:203*/this.blockRecipe = a1;
        return this;
    }
    
    public IForgeRegistryEntry.Impl<IRecipe> getBlockRecipe() {
        /*SL:204*/return this.blockRecipe;
    }
    
    public boolean getCreativeModeDrops() {
        /*SL:208*/return this.creativeModeDrops;
    }
    
    public void setCreativeModeDrops(final boolean a1) {
        /*SL:213*/this.creativeModeDrops = a1;
    }
    
    public EnumBlockRenderType func_149645_b(final IBlockState a1) {
        /*SL:219*/return EnumBlockRenderType.MODEL;
    }
}
