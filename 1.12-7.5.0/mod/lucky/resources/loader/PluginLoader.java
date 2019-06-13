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
        /*SL:40*/this.loadResource(new ResourcePluginInit());
    }
    
    public void initializePlugin() {
        try {
            /*SL:47*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.getBlock().getWorldGenerator(), 1);
        }
        catch (Exception v1) {
            System.err.println(/*EL:51*/("Lucky Block Addons: Error initializing generation for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : "unknown");
        }
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            /*SL:60*/if (this.pluginFile.isDirectory()) {
                final File a1 = /*EL:62*/this.getFile(v0);
                /*SL:63*/if (a1 == null || a1.isDirectory()) {
                    return null;
                }
                /*SL:64*/return new FileInputStream(a1);
            }
            else {
                final ZipFile v = /*EL:68*/new ZipFile(this.pluginFile);
                final ZipEntry v2 = /*EL:69*/v.getEntry(v0.getDirectory());
                /*SL:70*/if (v2 == null || v2.isDirectory()) {
                    return null;
                }
                final InputStream v3 = /*EL:71*/v.getInputStream(v2);
                /*SL:72*/return v3;
            }
        }
        catch (Exception v4) {
            /*SL:77*/if (!v0.isOptional()) {
                System.err.println(/*EL:79*/"Lucky Block: Error getting resource '" + v0.getDirectory() + "' from plugin '" + this.pluginFile.getName() + "'");
                /*SL:80*/v4.printStackTrace();
            }
            /*SL:83*/return null;
        }
    }
    
    public File getFile(final BaseResource a1) {
        /*SL:88*/return new File(this.pluginFile.getPath() + "/" + a1.getDirectory());
    }
    
    @SideOnly(Side.CLIENT)
    public IResourcePack getResourcePack() {
        IResourcePack v1 = /*EL:94*/null;
        /*SL:95*/if (this.pluginFile.isDirectory()) {
            v1 = (IResourcePack)new FolderResourcePack(this.pluginFile);
        }
        else {
            /*SL:96*/v1 = (IResourcePack)new FileResourcePack(this.pluginFile);
        }
        /*SL:98*/return (IResourcePack)new LegacyV2Adapter(v1);
    }
    
    public File getPluginFile() {
        /*SL:103*/return this.pluginFile;
    }
    
    public String getPluginName() {
        /*SL:108*/return this.pluginName;
    }
    
    public void setPluginName(final String a1) {
        /*SL:113*/this.pluginName = a1;
    }
    
    public boolean hasResource(final BaseResource a1) {
        /*SL:118*/return this.getResourceStream(a1) != null;
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:124*/super.loadResource(a1);
    }
}
