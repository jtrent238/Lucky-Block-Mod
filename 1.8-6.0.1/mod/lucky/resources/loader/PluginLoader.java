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
import mod.lucky.resources.IBaseResource;
import mod.lucky.resources.ResourcePluginInit;
import java.io.File;

public class PluginLoader extends BaseLoader
{
    private File pluginFile;
    
    public PluginLoader(final File v2) {
        try {
            this.pluginFile = v2;
        }
        catch (Exception a1) {
            System.err.println("Lucky Block: Failed to load plugin loader for file " + v2.toString());
            a1.printStackTrace();
        }
    }
    
    public void registerPlugin() {
        /*SL:41*/this.loadResource(new ResourcePluginInit());
    }
    
    public void initializePlugin() {
        /*SL:46*/RecipeSorter.register(Block.field_149771_c.func_177774_c((Object)this.getBlock()).toString(), (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        /*SL:47*/GameRegistry.addRecipe((IRecipe)this.getBlock().getCrafting());
        /*SL:48*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.getBlock().getWorldGenerator(), 1);
    }
    
    @Override
    public InputStream getResourceStream(final IBaseResource v0) {
        try {
            /*SL:56*/if (this.pluginFile.isDirectory()) {
                final File a1 = /*EL:58*/this.getFile(v0);
                /*SL:59*/if (a1.isDirectory()) {
                    return null;
                }
                /*SL:60*/return new FileInputStream(a1);
            }
            else {
                final ZipFile v = /*EL:64*/new ZipFile(this.pluginFile);
                final ZipEntry v2 = /*EL:65*/v.getEntry(v0.getDirectory());
                /*SL:66*/if (v2.isDirectory()) {
                    return null;
                }
                final InputStream v3 = /*EL:67*/v.getInputStream(v2);
                /*SL:68*/return v3;
            }
        }
        catch (Exception v4) {
            System.err.println(/*EL:73*/"Lucky Block: Error getting resource '" + v0.getDirectory() + "' from plugin '" + this.pluginFile.getName() + "'");
            /*SL:74*/v4.printStackTrace();
            /*SL:76*/return null;
        }
    }
    
    public File getFile(final IBaseResource a1) {
        /*SL:81*/return new File(this.pluginFile.getPath() + "/" + a1.getDirectory());
    }
    
    @SideOnly(Side.CLIENT)
    public IResourcePack getResourcePack() {
        /*SL:87*/if (this.pluginFile.isDirectory()) {
            return (IResourcePack)new FolderResourcePack(this.pluginFile);
        }
        /*SL:88*/return (IResourcePack)new FileResourcePack(this.pluginFile);
    }
    
    public File getPluginFile() {
        /*SL:93*/return this.pluginFile;
    }
    
    public boolean hasResource(final IBaseResource a1) {
        /*SL:98*/return this.getResourceStream(a1) != null;
    }
    
    @Override
    public void loadResource(final IBaseResource a1) {
        /*SL:104*/super.loadResource(a1);
    }
}
