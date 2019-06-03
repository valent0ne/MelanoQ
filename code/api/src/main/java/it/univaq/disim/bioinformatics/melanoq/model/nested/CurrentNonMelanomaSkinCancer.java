package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class CurrentNonMelanomaSkinCancer {

    // It can be BCC, SCC or in situ SCC
    private String type;

    private int number;

    private String site;


    public CurrentNonMelanomaSkinCancer(){ }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
