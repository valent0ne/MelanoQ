package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Occupation {

    private String SICCode;

    //TODO: check if the duration of the occupation is at least 1 year
    private String occupationStartingTime;

    private String occupationEndingTime;

    public Occupation(){ }

    public String getSICCode() {
        return SICCode;
    }

    public void setSICCode(String SICCode) {
        this.SICCode = SICCode;
    }

    public String getOccupationStartingTime() {
        return occupationStartingTime;
    }

    public void setOccupationStartingTime(String occupationStartingTime) {
        this.occupationStartingTime = occupationStartingTime;
    }

    public String getOccupationEndingTime() {
        return occupationEndingTime;
    }

    public void setOccupationEndingTime(String occupationEndingTime) {
        this.occupationEndingTime = occupationEndingTime;
    }
}
