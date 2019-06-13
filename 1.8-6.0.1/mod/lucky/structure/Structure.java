package mod.lucky.structure;

import mod.lucky.drop.DropProperties;
import mod.lucky.drop.func.DropFunction;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.DropStringUtils;
import mod.lucky.resources.IBaseResource;
import mod.lucky.resources.ResourceStructureFile;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import net.minecraft.util.Vec3;
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
    protected Vec3 centerPos;
    
    public Structure() {
        this.blockMode = "replace";
        this.blockUpdate = true;
    }
    
    public void readProperties(final String v-8, final BaseLoader v-7) {
        final String[] split;
        final String[] array = /*EL:43*/split = v-8.split(",");
        for (final String s : split) {
            final String[] a1 = /*EL:45*/s.split("=");
            final String a2 = /*EL:46*/a1[0];
            final String v1 = /*EL:47*/a1[1];
            /*SL:49*/if (a2.equalsIgnoreCase("ID")) {
                this.id = ValueParser.getString(v1);
            }
            /*SL:50*/if (a2.equalsIgnoreCase("overlayStruct")) {
                this.overlayStruct = ValueParser.getString(v1);
            }
            /*SL:51*/if (a2.equalsIgnoreCase("file")) {
                /*SL:53*/this.fileName = ValueParser.getString(v1);
                /*SL:54*/this.fileStream = v-7.getResourceStream(new ResourceStructureFile(this.fileName));
            }
            /*SL:56*/if (a2.equalsIgnoreCase("centerX")) {
                /*SL:58*/if (DropStringUtils.isGenericFloat(v1)) {
                    this.centerX = ValueParser.getFloat(v1);
                }
                else {
                    /*SL:59*/this.centerX = ValueParser.getFloat(v1) + (DropStringUtils.isGenericFloat(v1) ? 0.0f : 0.5f);
                }
            }
            /*SL:61*/if (a2.equalsIgnoreCase("centerY")) {
                this.centerY = ValueParser.getFloat(v1);
            }
            /*SL:62*/if (a2.equalsIgnoreCase("centerZ")) {
                /*SL:64*/if (DropStringUtils.isGenericFloat(v1)) {
                    this.centerZ = ValueParser.getFloat(v1);
                }
                else {
                    /*SL:65*/this.centerZ = ValueParser.getFloat(v1) + (DropStringUtils.isGenericFloat(v1) ? 0.0f : 0.5f);
                }
            }
            /*SL:67*/if (a2.equalsIgnoreCase("blockMode")) {
                this.blockMode = ValueParser.getString(v1);
            }
            /*SL:68*/if (a2.equalsIgnoreCase("blockUpdate")) {
                this.blockUpdate = ValueParser.getBoolean(v1);
            }
        }
    }
    
    public Structure newTypeInstance() {
        /*SL:74*/if (this.fileName.endsWith(".luckystruct")) {
            /*SL:76*/return new LuckyStructure().copyProperties(this);
        }
        /*SL:80*/return new SchematicStructure().copyProperties(this);
    }
    
    public void readFromFile() {
    }
    
    public void process(final DropProcessData a1) {
    }
    
    protected void processOverlay(final DropProcessData v-1) {
        /*SL:96*/if (this.overlayStruct != null) {
            final DropProperties a1 = /*EL:98*/v-1.getDropProperties();
            final String v1 = /*EL:100*/a1.getPropertyString("ID");
            final String v2 = /*EL:101*/a1.getPropertyString("blockMode");
            /*SL:103*/a1.setProperty("ID", this.overlayStruct);
            /*SL:104*/a1.setProperty("blockMode", "overlay");
            /*SL:105*/DropFunction.getDropFunction(a1).process(v-1);
            /*SL:106*/a1.setProperty("ID", v1);
            /*SL:107*/a1.setProperty("blockMode", v2);
        }
    }
    
    public String getId() {
        /*SL:113*/return this.id;
    }
    
    public Vec3 getCenterPos() {
        /*SL:118*/return this.centerPos;
    }
    
    protected void initCenterPos() {
        final int v1 = /*EL:123*/(int)(this.length / 2.0f);
        final int v2 = /*EL:124*/(int)(this.width / 2.0f);
        /*SL:125*/if (this.centerX == null) {
            this.centerX = v1 + 0.5f;
        }
        /*SL:126*/if (this.centerY == null) {
            this.centerY = 0.0f;
        }
        /*SL:127*/if (this.centerZ == null) {
            this.centerZ = v2 + 0.5f;
        }
        /*SL:128*/this.centerPos = new Vec3((double)this.centerX, (double)this.centerY, (double)this.centerZ);
    }
    
    public Structure copyProperties(final Structure a1) {
        /*SL:133*/this.fileName = a1.fileName;
        /*SL:134*/this.fileStream = a1.fileStream;
        /*SL:135*/this.id = a1.id;
        /*SL:136*/this.overlayStruct = a1.overlayStruct;
        /*SL:137*/this.centerX = a1.centerX;
        /*SL:138*/this.centerY = a1.centerY;
        /*SL:139*/this.centerZ = a1.centerZ;
        /*SL:140*/this.centerPos = a1.centerPos;
        /*SL:141*/this.blockMode = a1.blockMode;
        /*SL:142*/this.blockUpdate = a1.blockUpdate;
        /*SL:143*/this.length = a1.length;
        /*SL:144*/this.height = a1.height;
        /*SL:145*/this.width = a1.width;
        /*SL:146*/return this;
    }
}
