package mod.lucky.structure;

import mod.lucky.drop.DropProperties;
import mod.lucky.drop.func.DropFunction;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.DropStringUtils;
import mod.lucky.resources.BaseResource;
import mod.lucky.resources.ResourceStructureFile;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import net.minecraft.util.math.Vec3d;
import java.io.InputStream;

public class Structure
{
    public static final int STRUCTURE_BLOCK_LIMIT = 100000;
    protected int length;
    protected int height;
    protected int width;
    protected String fileName;
    protected InputStream fileStream;
    protected String overlayStruct;
    protected String id;
    protected String blockMode;
    protected boolean blockUpdate;
    protected Float centerX;
    protected Float centerY;
    protected Float centerZ;
    protected Vec3d centerPos;
    
    public Structure() {
        this.blockMode = "replace";
        this.blockUpdate = true;
    }
    
    public void readProperties(final String v-8, final BaseLoader v-7) {
        final String[] split;
        final String[] array = /*EL:39*/split = v-8.split(",");
        for (final String s : split) {
            final String[] a1 = /*EL:40*/s.split("=");
            final String a2 = /*EL:41*/a1[0];
            final String v1 = /*EL:42*/a1[1];
            /*SL:44*/if (a2.equalsIgnoreCase("ID")) {
                this.id = ValueParser.getString(v1);
            }
            /*SL:45*/if (a2.equalsIgnoreCase("overlayStruct")) {
                /*SL:46*/this.overlayStruct = ValueParser.getString(v1);
            }
            /*SL:47*/if (a2.equalsIgnoreCase("file")) {
                /*SL:48*/this.fileName = ValueParser.getString(v1);
                /*SL:49*/this.fileStream = v-7.getResourceStream(new ResourceStructureFile(this.fileName));
            }
            /*SL:51*/if (a2.equalsIgnoreCase("centerX")) {
                /*SL:52*/if (DropStringUtils.isGenericFloat(v1)) {
                    /*SL:53*/this.centerX = ValueParser.getFloat(v1);
                }
                else {
                    /*SL:56*/this.centerX = ValueParser.getFloat(v1) + /*EL:57*/(DropStringUtils.isGenericFloat(v1) ? 0.0f : 0.5f);
                }
            }
            /*SL:59*/if (a2.equalsIgnoreCase("centerY")) {
                /*SL:60*/this.centerY = ValueParser.getFloat(v1);
            }
            /*SL:61*/if (a2.equalsIgnoreCase("centerZ")) {
                /*SL:62*/if (DropStringUtils.isGenericFloat(v1)) {
                    /*SL:63*/this.centerZ = ValueParser.getFloat(v1);
                }
                else {
                    /*SL:66*/this.centerZ = ValueParser.getFloat(v1) + /*EL:67*/(DropStringUtils.isGenericFloat(v1) ? 0.0f : 0.5f);
                }
            }
            /*SL:69*/if (a2.equalsIgnoreCase("blockMode")) {
                /*SL:70*/this.blockMode = ValueParser.getString(v1);
            }
            /*SL:71*/if (a2.equalsIgnoreCase("blockUpdate")) {
                /*SL:72*/this.blockUpdate = ValueParser.getBoolean(v1);
            }
        }
    }
    
    public Structure newTypeInstance() {
        /*SL:77*/if (this.fileName.endsWith(".luckystruct")) {
            /*SL:78*/return new LuckyStructure().copyProperties(this);
        }
        /*SL:80*/return new SchematicStructure().copyProperties(this);
    }
    
    public void readFromFile() {
    }
    
    public void process(final DropProcessData a1) {
    }
    
    protected void processOverlay(final DropProcessData v-1) {
        /*SL:89*/if (this.overlayStruct != null) {
            final DropProperties a1 = /*EL:90*/v-1.getDropProperties();
            final String v1 = /*EL:92*/a1.getPropertyString("ID");
            final String v2 = /*EL:93*/a1.getPropertyString("blockMode");
            /*SL:95*/a1.setProperty("ID", this.overlayStruct);
            /*SL:96*/a1.setProperty("blockMode", "overlay");
            /*SL:97*/DropFunction.getDropFunction(a1).process(v-1);
            /*SL:98*/a1.setProperty("ID", v1);
            /*SL:99*/a1.setProperty("blockMode", v2);
        }
    }
    
    public String getId() {
        /*SL:104*/return this.id;
    }
    
    public Vec3d getCenterPos() {
        /*SL:108*/return this.centerPos;
    }
    
    protected void initCenterPos() {
        final int v1 = /*EL:112*/(int)(this.length / 2.0f);
        final int v2 = /*EL:113*/(int)(this.width / 2.0f);
        /*SL:114*/if (this.centerX == null) {
            this.centerX = v1 + 0.5f;
        }
        /*SL:115*/if (this.centerY == null) {
            this.centerY = 0.0f;
        }
        /*SL:116*/if (this.centerZ == null) {
            this.centerZ = v2 + 0.5f;
        }
        /*SL:117*/this.centerPos = new Vec3d((double)this.centerX, (double)this.centerY, (double)this.centerZ);
    }
    
    public Structure copyProperties(final Structure a1) {
        /*SL:121*/this.fileName = a1.fileName;
        /*SL:122*/this.fileStream = a1.fileStream;
        /*SL:123*/this.id = a1.id;
        /*SL:124*/this.overlayStruct = a1.overlayStruct;
        /*SL:125*/this.centerX = a1.centerX;
        /*SL:126*/this.centerY = a1.centerY;
        /*SL:127*/this.centerZ = a1.centerZ;
        /*SL:128*/this.centerPos = a1.centerPos;
        /*SL:129*/this.blockMode = a1.blockMode;
        /*SL:130*/this.blockUpdate = a1.blockUpdate;
        /*SL:131*/this.length = a1.length;
        /*SL:132*/this.height = a1.height;
        /*SL:133*/this.width = a1.width;
        /*SL:134*/return this;
    }
}
