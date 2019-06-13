package it.univaq.disim.bioinformatics.melanoq.model.nested;

import java.util.List;

public class PregnanciesInfo {

    private int numberOfFullTermPregnancies;

    private List<String> dateOfBirthOfChildren;

    private int numberOfMiscarriages;

    private Boolean melanomaOccurDuringPregnancy;

    private Boolean melanomaOccurBeforePregnancy;

    private String howManyYearsBeforePregnancy;

    private Boolean melanomaOccurAfterPregnancy;

    private String howManyYearsAfterPregnancy;

    private Boolean IVFBeforeDiagnosis;

    public PregnanciesInfo(){ }

    public int getNumberOfFullTermPregnancies() {
        return numberOfFullTermPregnancies;
    }

    public void setNumberOfFullTermPregnancies(int numberOfFullTermPregnancies) {
        this.numberOfFullTermPregnancies = numberOfFullTermPregnancies;
    }

    public List<String> getDateOfBirthOfChildren() {
        return dateOfBirthOfChildren;
    }

    public void setDateOfBirthOfChildren(List<String> dateOfBirthOfChildren) {
        this.dateOfBirthOfChildren = dateOfBirthOfChildren;
    }

    public int getNumberOfMiscarriages() {
        return numberOfMiscarriages;
    }

    public void setNumberOfMiscarriages(int numberOfMiscarriages) {
        this.numberOfMiscarriages = numberOfMiscarriages;
    }

    public Boolean getMelanomaOccurDuringPregnancy() {
        return melanomaOccurDuringPregnancy;
    }

    public void setMelanomaOccurDuringPregnancy(Boolean melanomaOccurDuringPregnancy) {
        this.melanomaOccurDuringPregnancy = melanomaOccurDuringPregnancy;
    }

    public Boolean getMelanomaOccurBeforePregnancy() {
        return melanomaOccurBeforePregnancy;
    }

    public void setMelanomaOccurBeforePregnancy(Boolean melanomaOccurBeforePregnancy) {
        this.melanomaOccurBeforePregnancy = melanomaOccurBeforePregnancy;
    }

    public String getHowManyYearsBeforePregnancy() {
        return howManyYearsBeforePregnancy;
    }

    public void setHowManyYearsBeforePregnancy(String howManyYearsBeforePregnancy) {
        this.howManyYearsBeforePregnancy = howManyYearsBeforePregnancy;
    }

    public Boolean getMelanomaOccurAfterPregnancy() {
        return melanomaOccurAfterPregnancy;
    }

    public void setMelanomaOccurAfterPregnancy(Boolean melanomaOccurAfterPregnancy) {
        this.melanomaOccurAfterPregnancy = melanomaOccurAfterPregnancy;
    }

    public String getHowManyYearsAfterPregnancy() {
        return howManyYearsAfterPregnancy;
    }

    public void setHowManyYearsAfterPregnancy(String howManyYearsAfterPregnancy) {
        this.howManyYearsAfterPregnancy = howManyYearsAfterPregnancy;
    }

    public Boolean getIVFBeforeDiagnosis() {
        return IVFBeforeDiagnosis;
    }

    public void setIVFBeforeDiagnosis(Boolean IVFBeforeDiagnosis) {
        this.IVFBeforeDiagnosis = IVFBeforeDiagnosis;
    }
}
