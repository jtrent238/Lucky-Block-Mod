package mod.lucky.resources.loader;

import java.util.Iterator;
import mod.lucky.resources.ResourceStructures;
import mod.lucky.resources.ResourceStructuresDir;
import mod.lucky.resources.ResourceRecipes;
import mod.lucky.resources.ResourceProperties;
import mod.lucky.resources.ResourceNaturalGen;
import mod.lucky.resources.ResourceLuckCrafting;
import mod.lucky.resources.ResourcePotionDrops;
import mod.lucky.resources.ResourceBowDrops;
import mod.lucky.resources.ResourceSwordDrops;
import mod.lucky.resources.ResourceDrops;
import mod.lucky.resources.BaseResource;
import java.util.ArrayList;
import java.io.File;

public class ResourceLoader
{
    private static File PLUGIN_DIR;
    private boolean isClient;
    private File minecraftDir;
    private ArrayList<BaseResource> resourceList;
    private DefaultLoader defaultLoader;
    private ArrayList<PluginLoader> pluginLoaders;
    
    public ResourceLoader(final File a1) {
        this.minecraftDir = a1;
        this.resourceList = new ArrayList<BaseResource>();
        this.registerResource(new ResourceDrops());
        this.registerResource(new ResourceSwordDrops());
        this.registerResource(new ResourceBowDrops());
        this.registerResource(new ResourcePotionDrops());
        this.registerResource(new ResourceLuckCrafting());
        this.registerResource(new ResourceNaturalGen());
        this.registerResource(new ResourceProperties());
        this.registerResource(new ResourceRecipes());
        this.registerResource(new ResourceStructuresDir());
        this.registerResource(new ResourceStructures());
        this.resetLoaders();
    }
    
    public void resetLoaders() {
        /*SL:33*/this.defaultLoader = new DefaultLoader(this.minecraftDir);
        /*SL:34*/this.pluginLoaders = new ArrayList<PluginLoader>();
        final File file = /*EL:35*/new File(this.minecraftDir.getPath() + ResourceLoader.PLUGIN_DIR.getPath());
        /*SL:36*/if (!file.exists()) {
            file.mkdirs();
        }
        final File[] listFiles;
        final File[] array = /*EL:38*/listFiles = file.listFiles();
        for (final File v1 : listFiles) {
            /*SL:40*/if (v1.isDirectory() || v1.getName().endsWith(".zip") || v1.getName().endsWith(/*EL:41*/".jar")) {
                /*SL:42*/this.pluginLoaders.add(new PluginLoader(v1));
            }
        }
    }
    
    public void registerPlugins() {
        /*SL:48*/for (final PluginLoader v1 : this.pluginLoaders) {
            v1.registerPlugin();
        }
    }
    
    public void extractDefaultResources() {
        /*SL:52*/this.defaultLoader.extractDefaultResources();
    }
    
    public DefaultLoader getDefaultLoader() {
        /*SL:56*/return this.defaultLoader;
    }
    
    public ArrayList<PluginLoader> getPluginLoaders() {
        /*SL:60*/return this.pluginLoaders;
    }
    
    public void loadAllResources(final boolean v-3) {
        /*SL:64*/for (final BaseResource a1 : this.resourceList) {
            /*SL:65*/if (v-3 == a1.postInit()) {
                this.defaultLoader.loadResource(a1);
            }
        }
        /*SL:67*/for (final PluginLoader pluginLoader : this.pluginLoaders) {
            /*SL:68*/for (final BaseResource v1 : this.resourceList) {
                /*SL:69*/if (v-3 == v1.postInit()) {
                    pluginLoader.loadResource(v1);
                }
            }
        }
    }
    
    public void registerResource(final BaseResource a1) {
        /*SL:74*/this.resourceList.add(a1);
    }
    
    static {
        ResourceLoader.PLUGIN_DIR = new File("/addons/lucky_block");
    }
}
