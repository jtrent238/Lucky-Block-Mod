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
        this.resourceDir = new File(a1.getPath() + "/config/lucky_block/version-" + Lucky.VERSION);
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
                FileOutputStream v = /*EL:30*/null;
                final File v2 = /*EL:31*/new File(this.resourceDir.getPath() + "/" + v0.getName());
                /*SL:32*/if (!v0.isDirectory() && !v2.exists()) {
                    /*SL:34*/if (!v2.getParentFile().exists()) {
                        v2.getParentFile().mkdirs();
                    }
                    /*SL:35*/v2.createNewFile();
                    /*SL:36*/v = new FileOutputStream(v2);
                }
                int v3;
                /*SL:40*/while ((v3 = zipInputStream.read()) != -1) {
                    /*SL:41*/if (v != null) {
                        v.write(v3);
                    }
                }
                /*SL:42*/if (v != null) {
                    v.close();
                }
            }
            /*SL:44*/zipInputStream.close();
        }
        catch (Exception ex) {
            System.err.println(/*EL:48*/"Lucky Block: Error extracting default resources");
            /*SL:49*/ex.printStackTrace();
        }
    }
    
    public File getFile(final BaseResource a1) {
        final File v1 = /*EL:55*/new File(this.resourceDir.getPath() + "/" + a1.getDirectory());
        /*SL:56*/return v1;
    }
    
    @Override
    public InputStream getResourceStream(final BaseResource v0) {
        try {
            final File a1 = /*EL:64*/this.getFile(v0);
            /*SL:65*/if (a1.isDirectory()) {
                return null;
            }
            /*SL:66*/return new FileInputStream(a1);
        }
        catch (FileNotFoundException v) {
            System.err.println(/*EL:70*/"Lucky Block: Error getting default resource '" + v0.getDirectory() + "'");
            /*SL:71*/v.printStackTrace();
            /*SL:73*/return null;
        }
    }
    
    @Override
    public void loadResource(final BaseResource a1) {
        /*SL:79*/super.loadResource(a1);
    }
    
    public File getResourceDir() {
        /*SL:84*/return this.resourceDir;
    }
}
