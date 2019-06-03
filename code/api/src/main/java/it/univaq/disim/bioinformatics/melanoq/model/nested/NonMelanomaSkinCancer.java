package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class NonMelanomaSkinCancer {

    // It can be BCC, SCC, in situ SCC
    private String type;

    private int number;

    //It can be after or before melanoma
    private String when;

    private String Site;

    private String dateOfDiagnoses;

    public NonMelanomaSkinCancer(){ }

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

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getDateOfDiagnoses() {
        return dateOfDiagnoses;
    }

    public void setDateOfDiagnoses(String dateOfDiagnoses) {
        this.dateOfDiagnoses = dateOfDiagnoses;
    }
}
