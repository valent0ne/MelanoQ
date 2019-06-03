package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class NonCutaneousNeoplasia {

    private String type;

    private String ageOfDiagnosis;

    private String yearOfDiagnoses;

    public NonCutaneousNeoplasia(){ }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgeOfDiagnosis() {
        return ageOfDiagnosis;
    }

    public void setAgeOfDiagnosis(String ageOfDiagnosis) {
        this.ageOfDiagnosis = ageOfDiagnosis;
    }

    public String getYearOfDiagnoses() {
        return yearOfDiagnoses;
    }

    public void setYearOfDiagnoses(String yearOfDiagnoses) {
        this.yearOfDiagnoses = yearOfDiagnoses;
    }
}
