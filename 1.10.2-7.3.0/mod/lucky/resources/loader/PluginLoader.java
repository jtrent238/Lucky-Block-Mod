package mod.lucky.resources.loader;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.RecipeSorter;
import mod.lucky.crafting.LuckyCrafting;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import mod.lucky.resources.BaseResource;
import mod.lucky.resources.ResourcePluginInit;
import java.io.File;

public class PluginLoader extends BaseLoader
{
    private File pluginFile;
    private String pluginName;
    
    public PluginLoader(final File v2) {
        this.pluginName = "random_block";
        try {
            this.pluginFile = v2;
            this.pluginName = v2.getName();
        }
        catch (Exception a1) {
            System.err.println("Lucky Block: Failed to load plugin loader for file " + v2.toString());
            a1.printStackTrace();
        }
    }
    
    public void registerPlugin() {
        /*SL:43*/this.loadResource(new ResourcePluginInit());
    }
    
    public void initializePlugin() {
        try {
            /*SL:50*/RecipeSorter.register(((ResourceLocation)Block.field_149771_c.func_177774_c((Object)this.getBlock())).toString(), (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
            /*SL:51*/if (this.getBlock() != null) {
                GameRegistry.addRecipe((IRecipe)this.getBlock().getCrafting());
            }
            /*SL:52*/if (this.getSword() != null) {
                GameRegistry.addRecipe((IRecipe)this.getSword().getCrafting());
            }
            /*SL:53*/if (this.getBow() != null) {
                GameRegistry.addRecipe((IRecipe)this.getBow().getCrafting());
            }
            /*SL:54*/if (this.getPotion() != null) {
                GameRegistry.addRecipe((IRecipe)this.getPotion().getCrafting());
            }
        }
        catch (Exception v1) {
            System.err.println(/*EL:58*/("Lucky Block Addons: Error initializing crafting for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : "unknown");
        }
        try {
            /*SL:63*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.getBlock().getWorldGenerator(), 1);
        }
        catch (Exception v1) {
            System.err.println(/*EL:67*/("Lucky Block Addons: Error initializing generation for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : "unknown");
        }
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            /*SL:76*/if (this.pluginFile.isDirectory()) {
                final File a1 = /*EL:78*/this.getFile(v0);
                /*SL:79*/if (a1 == null || a1.isDirectory()) {
                    return null;
                }
                /*SL:80*/return new FileInputStream(a1);
            }
            else {
                final ZipFile v = /*EL:84*/new ZipFile(this.pluginFile);
                final ZipEntry v2 = /*EL:85*/v.getEntry(v0.getDirectory());
                /*SL:86*/if (v2 == null || v2.isDirectory()) {
                    return null;
                }
                final InputStream v3 = /*EL:87*/v.getInputStream(v2);
                /*SL:88*/return v3;
            }
        }
        catch (Exception v4) {
            /*SL:93*/if (!v0.isOptional()) {
                System.err.println(/*EL:95*/"Lucky Block: Error getting resource '" + v0.getDirectory() + "' from plugin '" + this.pluginFile.getName() + "'");
                /*SL:96*/v4.printStackTrace();
            }
            /*SL:99*/return null;
        }
    }
    
    public File getFile(final BaseResource a1) {
        /*SL:104*/return new File(this.pluginFile.getPath() + "/" + a1.getDirectory());
    }
    
    @SideOnly(Side.CLIENT)
    public IResourcePack getResourcePack() {
        /*SL:110*/if (this.pluginFile.isDirectory()) {
            return (IResourcePack)new FolderResourcePack(this.pluginFile);
        }
        /*SL:111*/return (IResourcePack)new FileResourcePack(this.pluginFile);
    }
    
    public File getPluginFile() {
        /*SL:116*/return this.pluginFile;
    }
    
    public String getPluginName() {
        /*SL:121*/return this.pluginName;
    }
    
    public void setPluginName(final String a1) {
        /*SL:126*/this.pluginName = a1;
    }
    
    public boolean hasResource(final BaseResource a1) {
        /*SL:131*/return this.getResourceStream(a1) != null;
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:137*/super.loadResource(a1);
    }
}
