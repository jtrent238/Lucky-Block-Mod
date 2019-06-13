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
        /*SL:31*/this.type = a1;
    }
    
    public String getType() {
        /*SL:35*/return this.type;
    }
    
    public void setId(final String a1) {
        /*SL:39*/this.id = a1;
    }
    
    public String getId() {
        /*SL:43*/return this.id;
    }
    
    public void setDamage(final int a1) {
        /*SL:47*/this.damage = a1;
    }
    
    public int getDamage() {
        /*SL:51*/return this.damage;
    }
    
    public void setAmount(final int a1) {
        /*SL:55*/this.amount = a1;
    }
    
    public int getAmount() {
        /*SL:59*/return this.amount;
    }
    
    public void setRelativeToPlayer(final Boolean a1) {
        /*SL:63*/this.relativeToPlayer = a1;
    }
    
    public boolean getRelativeToPlayer() {
        /*SL:67*/return this.relativeToPlayer;
    }
    
    public void setPosX(final int a1) {
        /*SL:71*/this.posX = a1;
    }
    
    public int getPosX() {
        /*SL:75*/return this.posX;
    }
    
    public void setPosY(final int a1) {
        /*SL:79*/this.posY = a1;
    }
    
    public int getPosY() {
        /*SL:83*/return this.posY;
    }
    
    public void setPosZ(final int a1) {
        /*SL:87*/this.posZ = a1;
    }
    
    public int getPosZ() {
        /*SL:91*/return this.posZ;
    }
    
    public void setCenterX(final int a1) {
        /*SL:95*/this.centerX = a1;
        /*SL:96*/this.setCenterX = true;
    }
    
    public int getCenterX() {
        /*SL:100*/return this.centerX;
    }
    
    public void setCenterY(final int a1) {
        /*SL:104*/this.centerY = a1;
        /*SL:105*/this.setCenterY = true;
    }
    
    public int getCenterY() {
        /*SL:109*/return this.centerY;
    }
    
    public void setCenterZ(final int a1) {
        /*SL:113*/this.centerZ = a1;
        /*SL:114*/this.setCenterZ = true;
    }
    
    public int getCenterZ() {
        /*SL:118*/return this.centerZ;
    }
    
    public void setRotation(final int a1) {
        /*SL:122*/this.rotation = a1;
    }
    
    public int getRotation() {
        /*SL:126*/return this.rotation;
    }
    
    public boolean getDoUpdate() {
        /*SL:130*/return this.doUpdate;
    }
    
    public void setDoUpdate(final boolean a1) {
        /*SL:134*/this.doUpdate = a1;
    }
    
    public void setEffectDuration(final int a1) {
        /*SL:138*/this.effectDuration = a1 * 20;
    }
    
    public int getEffectDuration() {
        /*SL:142*/return this.effectDuration;
    }
    
    public void setNBTTag(final NBTTagCompound a1) {
        /*SL:146*/this.nbttag = a1;
    }
    
    public NBTTagCompound getNBTTag() {
        /*SL:150*/return this.nbttag;
    }
    
    public void setReinitialize(final boolean a1) {
        /*SL:154*/this.reinitialize = a1;
    }
    
    public boolean getReinitialize() {
        /*SL:158*/return this.reinitialize;
    }
    
    public void setDisplayCommandOutput(final boolean a1) {
        /*SL:162*/this.displayCommandOutput = a1;
    }
    
    public boolean getDisplayCommandOutput() {
        /*SL:166*/return this.displayCommandOutput;
    }
    
    public void setCommandSender(final String a1) {
        /*SL:170*/this.commandSender = a1;
    }
    
    public String getCommandSender() {
        /*SL:174*/return this.commandSender;
    }
}
