package mod.lucky.resources.loader;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.LegacyV2Adapter;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResourcePack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
        /*SL:34*/this.loadResource(new ResourcePluginInit());
    }
    
    public void initializePlugin() {
        try {
            /*SL:39*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.getBlock().getWorldGenerator(), 1);
        }
        catch (Exception v1) {
            System.err.println(/*EL:41*/("Lucky Block Addons: Error initializing generation for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : /*EL:43*/"unknown");
        }
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            /*SL:51*/if (this.pluginFile.isDirectory()) {
                final File a1 = /*EL:52*/this.getFile(v0);
                /*SL:53*/if (a1 == null || a1.isDirectory()) {
                    return null;
                }
                /*SL:54*/return new FileInputStream(a1);
            }
            else {
                final ZipFile v = /*EL:57*/new ZipFile(this.pluginFile);
                final ZipEntry v2 = /*EL:58*/v.getEntry(v0.getDirectory());
                /*SL:59*/if (v2 == null || v2.isDirectory()) {
                    return null;
                }
                final InputStream v3 = /*EL:60*/v.getInputStream(v2);
                /*SL:61*/return v3;
            }
        }
        catch (Exception v4) {
            /*SL:64*/if (!v0.isOptional()) {
                System.err.println(/*EL:65*/"Lucky Block: Error getting resource '" + v0.getDirectory() + /*EL:67*/"' from plugin '" + this.pluginFile.getName() + /*EL:69*/"'");
                /*SL:71*/v4.printStackTrace();
            }
            /*SL:74*/return null;
        }
    }
    
    public File getFile(final BaseResource a1) {
        /*SL:78*/return new File(this.pluginFile.getPath() + "/" + a1.getDirectory());
    }
    
    @SideOnly(Side.CLIENT)
    public IResourcePack getResourcePack() {
        IResourcePack v1 = /*EL:83*/null;
        /*SL:84*/if (this.pluginFile.isDirectory()) {
            v1 = (IResourcePack)new FolderResourcePack(this.pluginFile);
        }
        else {
            /*SL:85*/v1 = (IResourcePack)new FileResourcePack(this.pluginFile);
        }
        /*SL:87*/return (IResourcePack)new LegacyV2Adapter(v1);
    }
    
    public File getPluginFile() {
        /*SL:91*/return this.pluginFile;
    }
    
    public String getPluginName() {
        /*SL:95*/return this.pluginName;
    }
    
    public void setPluginName(final String a1) {
        /*SL:99*/this.pluginName = a1;
    }
    
    public boolean hasResource(final BaseResource a1) {
        /*SL:103*/return this.getResourceStream(a1) != null;
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:108*/super.loadResource(a1);
    }
}
