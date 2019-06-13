package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;

public class LuckyDropOld
{
    public String type;
    public String id;
    public int damage;
    public int amount;
    public boolean reinitialize;
    public boolean relativeToPlayer;
    public int posX;
    public int posY;
    public int posZ;
    public int pos2X;
    public int pos2Y;
    public int pos2Z;
    public int centerX;
    public int centerY;
    public int centerZ;
    public boolean setCenterX;
    public boolean setCenterY;
    public boolean setCenterZ;
    public int rotation;
    public boolean doUpdate;
    public int blockMode;
    public int effectDuration;
    public NBTTagCompound nbttag;
    public boolean displayCommandOutput;
    public String commandSender;
    
    public LuckyDropOld() {
        this.type = "drop";
        this.id = "";
        this.damage = 0;
        this.amount = 1;
        this.reinitialize = false;
        this.relativeToPlayer = false;
        this.posX = 0;
        this.posY = 0;
        this.posZ = 0;
        this.pos2X = 0;
        this.pos2Y = 0;
        this.pos2Z = 0;
        this.centerX = 0;
        this.centerY = 0;
        this.centerZ = 0;
        this.setCenterX = false;
        this.setCenterY = false;
        this.setCenterZ = false;
        this.rotation = 0;
        this.doUpdate = true;
        this.blockMode = 0;
        this.effectDuration = 200;
        this.displayCommandOutput = false;
        this.commandSender = "@";
    }
    
    public void setType(final String a1) {
        /*SL:36*/this.type = a1;
    }
    
    public String getType() {
        /*SL:41*/return this.type;
    }
    
    public void setId(final String a1) {
        /*SL:46*/this.id = a1;
    }
    
    public String getId() {
        /*SL:51*/return this.id;
    }
    
    public void setDamage(final int a1) {
        /*SL:56*/this.damage = a1;
    }
    
    public int getDamage() {
        /*SL:61*/return this.damage;
    }
    
    public void setAmount(final int a1) {
        /*SL:66*/this.amount = a1;
    }
    
    public int getAmount() {
        /*SL:71*/return this.amount;
    }
    
    public void setRelativeToPlayer(final Boolean a1) {
        /*SL:76*/this.relativeToPlayer = a1;
    }
    
    public boolean getRelativeToPlayer() {
        /*SL:81*/return this.relativeToPlayer;
    }
    
    public void setPosX(final int a1) {
        /*SL:86*/this.posX = a1;
    }
    
    public int getPosX() {
        /*SL:91*/return this.posX;
    }
    
    public void setPosY(final int a1) {
        /*SL:96*/this.posY = a1;
    }
    
    public int getPosY() {
        /*SL:101*/return this.posY;
    }
    
    public void setPosZ(final int a1) {
        /*SL:106*/this.posZ = a1;
    }
    
    public int getPosZ() {
        /*SL:111*/return this.posZ;
    }
    
    public void setCenterX(final int a1) {
        /*SL:116*/this.centerX = a1;
        /*SL:117*/this.setCenterX = true;
    }
    
    public int getCenterX() {
        /*SL:122*/return this.centerX;
    }
    
    public void setCenterY(final int a1) {
        /*SL:127*/this.centerY = a1;
        /*SL:128*/this.setCenterY = true;
    }
    
    public int getCenterY() {
        /*SL:133*/return this.centerY;
    }
    
    public void setCenterZ(final int a1) {
        /*SL:138*/this.centerZ = a1;
        /*SL:139*/this.setCenterZ = true;
    }
    
    public int getCenterZ() {
        /*SL:144*/return this.centerZ;
    }
    
    public void setRotation(final int a1) {
        /*SL:149*/this.rotation = a1;
    }
    
    public int getRotation() {
        /*SL:154*/return this.rotation;
    }
    
    public boolean getDoUpdate() {
        /*SL:159*/return this.doUpdate;
    }
    
    public void setDoUpdate(final boolean a1) {
        /*SL:164*/this.doUpdate = a1;
    }
    
    public void setEffectDuration(final int a1) {
        /*SL:169*/this.effectDuration = a1 * 20;
    }
    
    public int getEffectDuration() {
        /*SL:174*/return this.effectDuration;
    }
    
    public void setNBTTag(final NBTTagCompound a1) {
        /*SL:179*/this.nbttag = a1;
    }
    
    public NBTTagCompound getNBTTag() {
        /*SL:184*/return this.nbttag;
    }
    
    public void setReinitialize(final boolean a1) {
        /*SL:189*/this.reinitialize = a1;
    }
    
    public boolean getReinitialize() {
        /*SL:194*/return this.reinitialize;
    }
    
    public void setDisplayCommandOutput(final boolean a1) {
        /*SL:199*/this.displayCommandOutput = a1;
    }
    
    public boolean getDisplayCommandOutput() {
        /*SL:204*/return this.displayCommandOutput;
    }
    
    public void setCommandSender(final String a1) {
        /*SL:209*/this.commandSender = a1;
    }
    
    public String getCommandSender() {
        /*SL:214*/return this.commandSender;
    }
}
