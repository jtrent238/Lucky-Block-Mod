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
        /*SL:44*/this.loadResource(new ResourcePluginInit());
    }
    
    public void initializePlugin() {
        try {
            /*SL:51*/RecipeSorter.register(((ResourceLocation)Block.field_149771_c.func_177774_c((Object)this.getBlock())).toString(), (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
            /*SL:52*/if (this.getBlock() != null) {
                GameRegistry.addRecipe((IRecipe)this.getBlock().getCrafting());
            }
            /*SL:53*/if (this.getSword() != null) {
                GameRegistry.addRecipe((IRecipe)this.getSword().getCrafting());
            }
            /*SL:54*/if (this.getBow() != null) {
                GameRegistry.addRecipe((IRecipe)this.getBow().getCrafting());
            }
            /*SL:55*/if (this.getPotion() != null) {
                GameRegistry.addRecipe((IRecipe)this.getPotion().getCrafting());
            }
        }
        catch (Exception v1) {
            System.err.println(/*EL:59*/("Lucky Block Addons: Error initializing crafting for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : "unknown");
        }
        try {
            /*SL:64*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.getBlock().getWorldGenerator(), 1);
        }
        catch (Exception v1) {
            System.err.println(/*EL:68*/("Lucky Block Addons: Error initializing generation for add-on: " + this.pluginFile != null) ? this.pluginFile.toString() : "unknown");
        }
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            /*SL:77*/if (this.pluginFile.isDirectory()) {
                final File a1 = /*EL:79*/this.getFile(v0);
                /*SL:80*/if (a1 == null || a1.isDirectory()) {
                    return null;
                }
                /*SL:81*/return new FileInputStream(a1);
            }
            else {
                final ZipFile v = /*EL:85*/new ZipFile(this.pluginFile);
                final ZipEntry v2 = /*EL:86*/v.getEntry(v0.getDirectory());
                /*SL:87*/if (v2 == null || v2.isDirectory()) {
                    return null;
                }
                final InputStream v3 = /*EL:88*/v.getInputStream(v2);
                /*SL:89*/return v3;
            }
        }
        catch (Exception v4) {
            /*SL:94*/if (!v0.isOptional()) {
                System.err.println(/*EL:96*/"Lucky Block: Error getting resource '" + v0.getDirectory() + "' from plugin '" + this.pluginFile.getName() + "'");
                /*SL:97*/v4.printStackTrace();
            }
            /*SL:100*/return null;
        }
    }
    
    public File getFile(final BaseResource a1) {
        /*SL:105*/return new File(this.pluginFile.getPath() + "/" + a1.getDirectory());
    }
    
    @SideOnly(Side.CLIENT)
    public IResourcePack getResourcePack() {
        IResourcePack v1 = /*EL:111*/null;
        /*SL:112*/if (this.pluginFile.isDirectory()) {
            v1 = (IResourcePack)new FolderResourcePack(this.pluginFile);
        }
        else {
            /*SL:113*/v1 = (IResourcePack)new FileResourcePack(this.pluginFile);
        }
        /*SL:115*/return (IResourcePack)new LegacyV2Adapter(v1);
    }
    
    public File getPluginFile() {
        /*SL:120*/return this.pluginFile;
    }
    
    public String getPluginName() {
        /*SL:125*/return this.pluginName;
    }
    
    public void setPluginName(final String a1) {
        /*SL:130*/this.pluginName = a1;
    }
    
    public boolean hasResource(final BaseResource a1) {
        /*SL:135*/return this.getResourceStream(a1) != null;
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:141*/super.loadResource(a1);
    }
}
