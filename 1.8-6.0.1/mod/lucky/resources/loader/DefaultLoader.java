package mod.lucky.resources.loader;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import mod.lucky.resources.IBaseResource;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipInputStream;
import mod.lucky.Lucky;
import java.io.File;

public class DefaultLoader extends BaseLoader
{
    private File resourceDir;
    
    public DefaultLoader(final File a1) {
        this.resourceDir = new File(a1.getPath() + "/config/lucky_block/version-" + Lucky.VERSION);
        Lucky.getInstance();
        this.lucky_block = Lucky.lucky_block;
    }
    
    public void extractDefaultResources() {
        try {
            final InputStream resourceAsStream = /*EL:28*/Lucky.class.getResourceAsStream("default_config.zip");
            final ZipInputStream zipInputStream = /*EL:29*/new ZipInputStream(resourceAsStream);
            ZipEntry v0 = /*EL:31*/null;
            /*SL:32*/while ((v0 = zipInputStream.getNextEntry()) != null) {
                FileOutputStream v = /*EL:34*/null;
                final File v2 = /*EL:35*/new File(this.resourceDir.getPath() + "/" + v0.getName());
                /*SL:36*/if (!v0.isDirectory() && !v2.exists()) {
                    /*SL:38*/if (!v2.getParentFile().exists()) {
                        v2.getParentFile().mkdirs();
                    }
                    /*SL:39*/v2.createNewFile();
                    /*SL:40*/v = new FileOutputStream(v2);
                }
                int v3 = /*EL:43*/0;
                /*SL:44*/while ((v3 = zipInputStream.read()) != -1) {
                    /*SL:45*/if (v != null) {
                        v.write(v3);
                    }
                }
                /*SL:46*/if (v != null) {
                    v.close();
                }
            }
            /*SL:48*/zipInputStream.close();
        }
        catch (Exception ex) {
            System.err.println(/*EL:52*/"Lucky Block: Error extracting default resources");
            /*SL:53*/ex.printStackTrace();
        }
    }
    
    public File getFile(final IBaseResource a1) {
        final File v1 = /*EL:59*/new File(this.resourceDir.getPath() + "/" + a1.getDirectory());
        /*SL:60*/return v1;
    }
    
    @Override
    public InputStream getResourceStream(final IBaseResource v0) {
        try {
            final File a1 = /*EL:68*/this.getFile(v0);
            /*SL:69*/if (a1.isDirectory()) {
                return null;
            }
            /*SL:70*/return new FileInputStream(a1);
        }
        catch (FileNotFoundException v) {
            System.err.println(/*EL:74*/"Lucky Block: Error getting default resource '" + v0.getDirectory() + "'");
            /*SL:75*/v.printStackTrace();
            /*SL:77*/return null;
        }
    }
    
    @Override
    public void loadResource(final IBaseResource a1) {
        /*SL:83*/super.loadResource(a1);
    }
    
    public File getResourceDir() {
        /*SL:88*/return this.resourceDir;
    }
}
