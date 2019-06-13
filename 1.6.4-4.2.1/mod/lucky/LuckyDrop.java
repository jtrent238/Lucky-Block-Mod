package mod.lucky;

import none.by;

public class LuckyDrop
{
    private String type;
    private int ID;
    private String name;
    private int damage;
    private int amount;
    private boolean reinitialize;
    private boolean relevantToPlayer;
    private int posXOffset;
    private int posYOffset;
    private int posZOffset;
    private int effectDuration;
    private by NBTTag;
    private int adjustHeight;
    
    public LuckyDrop() {
        this.type = "drop";
        this.ID = -1;
        this.name = "";
        this.damage = 0;
        this.amount = 1;
        this.reinitialize = false;
        this.relevantToPlayer = false;
        this.posXOffset = 0;
        this.posYOffset = 0;
        this.posZOffset = 0;
        this.effectDuration = 200;
        this.adjustHeight = 0;
    }
    
    public void setType(final String a1) {
        /*SL:28*/this.type = a1;
    }
    
    public String getType() {
        /*SL:32*/return this.type;
    }
    
    public void setID(final int a1) {
        /*SL:37*/this.ID = a1;
    }
    
    public int getID() {
        /*SL:41*/return this.ID;
    }
    
    public void setName(final String a1) {
        /*SL:46*/this.name = a1;
    }
    
    public String getName() {
        /*SL:50*/return this.name;
    }
    
    public void setDamage(final int a1) {
        /*SL:55*/this.damage = a1;
    }
    
    public int getDamage() {
        /*SL:59*/return this.damage;
    }
    
    public void setAmount(final int a1) {
        /*SL:64*/this.amount = a1;
    }
    
    public int getAmount() {
        /*SL:68*/return this.amount;
    }
    
    public void setRelativToPlayer(final Boolean a1) {
        /*SL:73*/this.relevantToPlayer = a1;
    }
    
    public boolean getRelativToPlayer() {
        /*SL:77*/return this.relevantToPlayer;
    }
    
    public void setXOffset(final int a1) {
        /*SL:82*/this.posXOffset = a1;
    }
    
    public int getXOffset() {
        /*SL:86*/return this.posXOffset;
    }
    
    public void setYOffset(final int a1) {
        /*SL:91*/this.posYOffset = a1;
    }
    
    public int getYOffset() {
        /*SL:95*/return this.posYOffset;
    }
    
    public void setZOffset(final int a1) {
        /*SL:100*/this.posZOffset = a1;
    }
    
    public int getZOffset() {
        /*SL:104*/return this.posZOffset;
    }
    
    public void setEffectDuration(final int a1) {
        /*SL:109*/this.effectDuration = a1 * 20;
    }
    
    public int getEffectDuration() {
        /*SL:113*/return this.effectDuration;
    }
    
    public void setNBTTag(final by a1) {
        /*SL:118*/this.NBTTag = a1;
    }
    
    public by getNBTTag() {
        /*SL:122*/return this.NBTTag;
    }
    
    public void setReinitialize(final boolean a1) {
        /*SL:127*/this.reinitialize = true;
    }
    
    public boolean getReinitialize() {
        /*SL:131*/return this.reinitialize;
    }
    
    public void setAdjustHeight(final int a1) {
        /*SL:136*/this.adjustHeight = a1;
    }
    
    public int getAdjustHeight() {
        /*SL:140*/return this.adjustHeight;
    }
}
