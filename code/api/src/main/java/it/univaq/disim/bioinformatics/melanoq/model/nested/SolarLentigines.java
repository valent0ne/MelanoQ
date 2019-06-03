package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class SolarLentigines {

    // It can be: None, Few, Many
    private String howMany;

    // true --> yes; false --> no
    private Boolean atSiteOfMelanoma;

    public SolarLentigines(){ }

    public String getHowMany() {
        return howMany;
    }

    public void setHowMany(String howMany) {
        this.howMany = howMany;
    }

    public Boolean getAtSiteOfMelanoma() {
        return atSiteOfMelanoma;
    }

    public void setAtSiteOfMelanoma(Boolean atSiteOfMelanoma) {
        this.atSiteOfMelanoma = atSiteOfMelanoma;
    }
}
