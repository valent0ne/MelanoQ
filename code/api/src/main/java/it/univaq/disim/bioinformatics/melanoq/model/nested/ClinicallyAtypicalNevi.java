package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class ClinicallyAtypicalNevi {

    private int howMany;

    // true--> yes; false --> no
    private Boolean atSiteOfMelanoma;

    public ClinicallyAtypicalNevi(){ }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public Boolean getAtSiteOfMelanoma() {
        return atSiteOfMelanoma;
    }

    public void setAtSiteOfMelanoma(Boolean atSiteOfMelanoma) {
        this.atSiteOfMelanoma = atSiteOfMelanoma;
    }
}
