package mod.lucky.resources.loader;

import java.util.Iterator;
import mod.lucky.resources.ResourceStructures;
import mod.lucky.resources.ResourceStructuresDir;
import mod.lucky.resources.ResourceRecipes;
import mod.lucky.resources.ResourceProperties;
import mod.lucky.resources.ResourceNaturalGen;
import mod.lucky.resources.ResourceLuckCrafting;
import mod.lucky.resources.ResourceDrops;
import mod.lucky.resources.IBaseResource;
import java.util.ArrayList;
import java.io.File;

public class ResourceLoader
{
    private static File PLUGIN_DIR;
    private boolean isClient;
    private File minecraftDir;
    private ArrayList<IBaseResource> resourceList;
    private DefaultLoader defaultLoader;
    private ArrayList<PluginLoader> pluginLoaders;
    
    public ResourceLoader(final File a1) {
        this.minecraftDir = a1;
        this.resourceList = new ArrayList<IBaseResource>();
        this.registerResource(new ResourceDrops());
        this.registerResource(new ResourceLuckCrafting());
        this.registerResource(new ResourceNaturalGen());
        this.registerResource(new ResourceProperties());
        this.registerResource(new ResourceRecipes());
        this.registerResource(new ResourceStructuresDir());
        this.registerResource(new ResourceStructures());
        this.resetLoaders();
    }
    
    public void resetLoaders() {
        /*SL:41*/this.defaultLoader = new DefaultLoader(this.minecraftDir);
        /*SL:42*/this.pluginLoaders = new ArrayList<PluginLoader>();
        final File file = /*EL:43*/new File(this.minecraftDir.getPath() + ResourceLoader.PLUGIN_DIR.getPath());
        /*SL:44*/if (!file.exists()) {
            file.mkdirs();
        }
        final File[] listFiles;
        final File[] array = /*EL:46*/listFiles = file.listFiles();
        for (final File v1 : listFiles) {
            /*SL:48*/if (v1.isDirectory() || v1.getName().endsWith(".zip") || v1.getName().endsWith(".jar")) {
                /*SL:50*/this.pluginLoaders.add(new PluginLoader(v1));
            }
        }
    }
    
    public void registerPlugins() {
        /*SL:57*/for (final PluginLoader v1 : this.pluginLoaders) {
            /*SL:59*/v1.registerPlugin();
        }
    }
    
    public void extractDefaultResources() {
        /*SL:65*/this.defaultLoader.extractDefaultResources();
    }
    
    public DefaultLoader getDefaultLoader() {
        /*SL:71*/return this.defaultLoader;
    }
    
    public ArrayList<PluginLoader> getPluginLoaders() {
        /*SL:76*/return this.pluginLoaders;
    }
    
    public void loadAllResources() {
        /*SL:81*/for (final IBaseResource v1 : this.resourceList) {
            /*SL:83*/this.defaultLoader.loadResource(v1);
        }
        /*SL:86*/for (final PluginLoader v2 : this.pluginLoaders) {
            /*SL:88*/for (final IBaseResource v3 : this.resourceList) {
                /*SL:90*/v2.loadResource(v3);
            }
            /*SL:92*/v2.initializePlugin();
        }
    }
    
    public void registerResource(final IBaseResource a1) {
        /*SL:98*/this.resourceList.add(a1);
    }
    
    static {
        ResourceLoader.PLUGIN_DIR = new File("/addons/lucky_block");
    }
}
