package com.volcaniccoder.volxfastscroll;

public class VolxCharModel {

    private Character character;
    private boolean isBlink;
    private boolean isGone;

    public VolxCharModel(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isBlink() {
        return isBlink;
    }

    public void setBlink(boolean blink) {
        isBlink = blink;
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }
}
