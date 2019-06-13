package mod.lucky.resources.loader;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import mod.lucky.resources.BaseResource;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
import mod.lucky.block.BlockLuckyBlock;
import mod.lucky.Lucky;
import java.io.File;

public class DefaultLoader extends BaseLoader
{
    private File resourceDir;
    
    public DefaultLoader(final File a1) {
        this.resourceDir = new File(a1.getPath() + "/config/lucky_block/version-" + "7.6.0");
        Lucky.getInstance();
        final BlockLuckyBlock lucky_block = Lucky.lucky_block;
        Lucky.getInstance();
        final ItemLuckySword lucky_sword = Lucky.lucky_sword;
        Lucky.getInstance();
        final ItemLuckyBow lucky_bow = Lucky.lucky_bow;
        Lucky.getInstance();
        this.setLuckyBlockItems(lucky_block, lucky_sword, lucky_bow, Lucky.lucky_potion);
    }
    
    public void extractDefaultResources() {
        try {
            final InputStream resourceAsStream = /*EL:24*/Lucky.class.getResourceAsStream("default_config.zip");
            final ZipInputStream zipInputStream = /*EL:25*/new ZipInputStream(resourceAsStream);
            ZipEntry v0;
            /*SL:28*/while ((v0 = zipInputStream.getNextEntry()) != null) {
                FileOutputStream v = /*EL:29*/null;
                final File v2 = /*EL:30*/new File(this.resourceDir.getPath() + "/" + v0.getName());
                /*SL:31*/if (!v0.isDirectory() && !v2.exists()) {
                    /*SL:32*/if (!v2.getParentFile().exists()) {
                        v2.getParentFile().mkdirs();
                    }
                    /*SL:33*/v2.createNewFile();
                    /*SL:34*/v = new FileOutputStream(v2);
                }
                int v3;
                /*SL:38*/while ((v3 = zipInputStream.read()) != -1) {
                    /*SL:39*/if (v != null) {
                        v.write(v3);
                    }
                }
                /*SL:40*/if (v != null) {
                    v.close();
                }
            }
            /*SL:42*/zipInputStream.close();
        }
        catch (Exception ex) {
            System.err.println(/*EL:44*/"Lucky Block: Error extracting default resources");
            /*SL:45*/ex.printStackTrace();
        }
    }
    
    public File getFile(final BaseResource a1) {
        final File v1 = /*EL:50*/new File(this.resourceDir.getPath() + "/" + a1.getDirectory());
        /*SL:51*/return v1;
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            final File a1 = /*EL:57*/this.getFile(v0);
            /*SL:58*/if (a1.isDirectory()) {
                return null;
            }
            /*SL:59*/return new FileInputStream(a1);
        }
        catch (FileNotFoundException v) {
            System.err.println(/*EL:61*/"Lucky Block: Error getting default resource '" + v0.getDirectory() + /*EL:62*/"'");
            /*SL:63*/v.printStackTrace();
            /*SL:65*/return null;
        }
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:70*/super.loadResource(a1);
    }
    
    public File getResourceDir() {
        /*SL:74*/return this.resourceDir;
    }
}
