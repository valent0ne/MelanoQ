package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.*;

import java.util.List;

public class C2 {

    @Field
    private List<MedicalDiagnosis> medicalDiagnoses;

    //TODO: Optional
    @Field
    private List<Treatment> previousAndConcomitantTreatments;

    @Field
    private PregnanciesInfo pregnancyHistory;

    @Field
    private List<NonMelanomaSkinCancer> lifetimeHistoryOfNonMelanomaSkinCancer;

    @Field
    private List<NonCutaneousNeoplasia> nonCutaneousNeoplasias;

    public C2(){ }

    public List<MedicalDiagnosis> getMedicalDiagnoses() {
        return medicalDiagnoses;
    }

    public void setMedicalDiagnoses(List<MedicalDiagnosis> medicalDiagnoses) {
        this.medicalDiagnoses = medicalDiagnoses;
    }

    public List<Treatment> getPreviousAndConcomitantTreatments() {
        return previousAndConcomitantTreatments;
    }

    public void setPreviousAndConcomitantTreatments(List<Treatment> previousAndConcomitantTreatments) {
        this.previousAndConcomitantTreatments = previousAndConcomitantTreatments;
    }

    public PregnanciesInfo getPregnancyHistory() {
        return pregnancyHistory;
    }

    public void setPregnancyHistory(PregnanciesInfo pregnancyHistory) {
        this.pregnancyHistory = pregnancyHistory;
    }

    public List<NonMelanomaSkinCancer> getLifetimeHistoryOfNonMelanomaSkinCAncer() {
        return lifetimeHistoryOfNonMelanomaSkinCancer;
    }

    public void setLifetimeHistoryOfNonMelanomaSkinCAncer(List<NonMelanomaSkinCancer> lifetimeHistoryOfNonMelanomaSkinCancer) {
        this.lifetimeHistoryOfNonMelanomaSkinCancer = lifetimeHistoryOfNonMelanomaSkinCancer;
    }

    public List<NonCutaneousNeoplasia> getNonCutaneousNeoplasias() {
        return nonCutaneousNeoplasias;
    }

    public void setNonCutaneousNeoplasias(List<NonCutaneousNeoplasia> nonCutaneousNeoplasias) {
        this.nonCutaneousNeoplasias = nonCutaneousNeoplasias;
    }
}
