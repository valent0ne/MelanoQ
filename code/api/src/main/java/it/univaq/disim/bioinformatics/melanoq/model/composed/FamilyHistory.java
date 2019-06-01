package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class FamilyHistory {

    // It can be YES/NO/Not known/Other (specify: _____)
    private String precence;

    private String type;

    // It can be maternal or paternal side
    private String sideOfAffectedReltive;

    // It can be 1st, 2nd, 3rd
    private String degreeOfReltive;

    private String ageAtDiagnosis;


    public FamilyHistory(){ }

    public String getPrecence() {
        return precence;
    }

    public void setPrecence(String precence) {
        this.precence = precence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSideOfAffectedReltive() {
        return sideOfAffectedReltive;
    }

    public void setSideOfAffectedReltive(String sideOfAffectedReltive) {
        this.sideOfAffectedReltive = sideOfAffectedReltive;
    }

    public String getDegreeOfReltive() {
        return degreeOfReltive;
    }

    public void setDegreeOfReltive(String degreeOfReltive) {
        this.degreeOfReltive = degreeOfReltive;
    }

    public String getAgeAtDiagnosis() {
        return ageAtDiagnosis;
    }

    public void setAgeAtDiagnosis(String ageAtDiagnosis) {
        this.ageAtDiagnosis = ageAtDiagnosis;
    }
}
