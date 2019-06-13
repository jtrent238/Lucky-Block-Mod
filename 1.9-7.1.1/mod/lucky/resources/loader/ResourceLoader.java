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
        /*SL:47*/this.defaultLoader = new DefaultLoader(this.minecraftDir);
        /*SL:48*/this.pluginLoaders = new ArrayList<PluginLoader>();
        final File file = /*EL:49*/new File(this.minecraftDir.getPath() + ResourceLoader.PLUGIN_DIR.getPath());
        /*SL:50*/if (!file.exists()) {
            file.mkdirs();
        }
        final File[] listFiles;
        final File[] array = /*EL:52*/listFiles = file.listFiles();
        for (final File v1 : listFiles) {
            /*SL:54*/if (v1.isDirectory() || v1.getName().endsWith(".zip") || v1.getName().endsWith(".jar")) {
                /*SL:56*/this.pluginLoaders.add(new PluginLoader(v1));
            }
        }
    }
    
    public void registerPlugins() {
        /*SL:63*/for (final PluginLoader v1 : this.pluginLoaders) {
            /*SL:65*/v1.registerPlugin();
        }
    }
    
    public void extractDefaultResources() {
        /*SL:71*/this.defaultLoader.extractDefaultResources();
    }
    
    public DefaultLoader getDefaultLoader() {
        /*SL:77*/return this.defaultLoader;
    }
    
    public ArrayList<PluginLoader> getPluginLoaders() {
        /*SL:82*/return this.pluginLoaders;
    }
    
    public void loadAllResources() {
        /*SL:87*/for (final BaseResource v1 : this.resourceList) {
            /*SL:89*/this.defaultLoader.loadResource(v1);
        }
        /*SL:92*/for (final PluginLoader v2 : this.pluginLoaders) {
            /*SL:94*/for (final BaseResource v3 : this.resourceList) {
                /*SL:96*/v2.loadResource(v3);
            }
            /*SL:98*/v2.initializePlugin();
        }
    }
    
    public void registerResource(final BaseResource a1) {
        /*SL:104*/this.resourceList.add(a1);
    }
    
    static {
        ResourceLoader.PLUGIN_DIR = new File("/addons/lucky_block");
    }
}
