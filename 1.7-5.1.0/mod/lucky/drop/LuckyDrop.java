package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;

public class LuckyDrop
{
    private String type;
    private String id;
    private int damage;
    private int amount;
    private boolean reinitialize;
    private boolean relativeToPlayer;
    private int posX;
    private int posY;
    private int posZ;
    private int effectDuration;
    private NBTTagCompound nbttag;
    private boolean displayCommandOutput;
    private String commandSender;
    
    public LuckyDrop() {
        this.type = "drop";
        this.id = "";
        this.damage = 0;
        this.amount = 1;
        this.reinitialize = false;
        this.relativeToPlayer = false;
        this.posX = 0;
        this.posY = 0;
        this.posZ = 0;
        this.effectDuration = 200;
        this.displayCommandOutput = false;
        this.commandSender = "@";
    }
    
    public void setType(final String a1) {
        /*SL:28*/this.type = a1;
    }
    
    public String getType() {
        /*SL:33*/return this.type;
    }
    
    public void setId(final String a1) {
        /*SL:38*/this.id = a1;
    }
    
    public String getId() {
        /*SL:43*/return this.id;
    }
    
    public void setDamage(final int a1) {
        /*SL:48*/this.damage = a1;
    }
    
    public int getDamage() {
        /*SL:53*/return this.damage;
    }
    
    public void setAmount(final int a1) {
        /*SL:58*/this.amount = a1;
    }
    
    public int getAmount() {
        /*SL:63*/return this.amount;
    }
    
    public void setRelativeToPlayer(final Boolean a1) {
        /*SL:68*/this.relativeToPlayer = a1;
    }
    
    public boolean getRelativeToPlayer() {
        /*SL:73*/return this.relativeToPlayer;
    }
    
    public void setPosX(final int a1) {
        /*SL:78*/this.posX = a1;
    }
    
    public int getPosX() {
        /*SL:83*/return this.posX;
    }
    
    public void setPosY(final int a1) {
        /*SL:88*/this.posY = a1;
    }
    
    public int getPosY() {
        /*SL:93*/return this.posY;
    }
    
    public void setPosZ(final int a1) {
        /*SL:98*/this.posZ = a1;
    }
    
    public int getPosZ() {
        /*SL:103*/return this.posZ;
    }
    
    public void setEffectDuration(final int a1) {
        /*SL:108*/this.effectDuration = a1 * 20;
    }
    
    public int getEffectDuration() {
        /*SL:113*/return this.effectDuration;
    }
    
    public void setNBTTag(final NBTTagCompound a1) {
        /*SL:118*/this.nbttag = a1;
    }
    
    public NBTTagCompound getNBTTag() {
        /*SL:123*/return this.nbttag;
    }
    
    public void setReinitialize(final boolean a1) {
        /*SL:128*/this.reinitialize = true;
    }
    
    public boolean getReinitialize() {
        /*SL:133*/return this.reinitialize;
    }
    
    public void setDisplayCommandOutput(final boolean a1) {
        /*SL:138*/this.displayCommandOutput = a1;
    }
    
    public boolean getDisplayCommandOutput() {
        /*SL:143*/return this.displayCommandOutput;
    }
    
    public void setCommandSender(final String a1) {
        /*SL:148*/this.commandSender = a1;
    }
    
    public String getCommandSender() {
        /*SL:153*/return this.commandSender;
    }
}
