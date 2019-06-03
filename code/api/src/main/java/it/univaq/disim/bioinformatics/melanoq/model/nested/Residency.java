package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Residency {

    private String residencyCountry;

    private String residencyCity;

    private String residencyStartingTime;

    private String residencyEndingTime;

    public Residency(){ }

    public String getResidencyCountry() {
        return residencyCountry;
    }

    public void setResidencyCountry(String residencyCountry) {
        this.residencyCountry = residencyCountry;
    }

    public String getResidencyCity() {
        return residencyCity;
    }

    public void setResidencyCity(String residencyCity) {
        this.residencyCity = residencyCity;
    }

    public String getResidencyStartingTime() {
        return residencyStartingTime;
    }

    public void setResidencyStartingTime(String residencyStartingTime) {
        this.residencyStartingTime = residencyStartingTime;
    }

    public String getResidencyEndingTime() {
        return residencyEndingTime;
    }

    public void setResidencyEndingTime(String residencyEndingTime) {
        this.residencyEndingTime = residencyEndingTime;
    }
}
