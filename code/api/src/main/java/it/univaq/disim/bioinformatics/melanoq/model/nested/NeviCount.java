package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class NeviCount {

    // It can be: Scalp, Face (including ears), Neck, Anterior thorax + abdomen, Back, Upper extremities including deltoid,
    // Lower extremities including gluteus, Palms, Soles
    private String site;

    private int leftZoneNumber;

    private int rightZoneNumber;

    public NeviCount(){ }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getLeftZoneNumber() {
        return leftZoneNumber;
    }

    public void setLeftZoneNumber(int leftZoneNumber) {
        this.leftZoneNumber = leftZoneNumber;
    }

    public int getRightZoneNumber() {
        return rightZoneNumber;
    }

    public void setRightZoneNumber(int rightZoneNumber) {
        this.rightZoneNumber = rightZoneNumber;
    }
}
