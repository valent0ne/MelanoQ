package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class SunscreenUse {

    // It can be childhood, adolescence, adulthood or 10 years before melanoma diagnosis (for cases) or last 10 years (for controls)
    private String agePeriod;

    // It can be Never, <50% of time exposure, >50% of time exposure, Always, Not known
    private String howOften;

    // It can be SPF<20, SPF>20, Not known
    private String type;


    public SunscreenUse(){ }

    public String getAgePeriod() {
        return agePeriod;
    }

    public void setAgePeriod(String agePeriod) {
        this.agePeriod = agePeriod;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
