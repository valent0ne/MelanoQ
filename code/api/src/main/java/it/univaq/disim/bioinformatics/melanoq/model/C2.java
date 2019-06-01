package it.univaq.disim.bioinformatics.melanoq.model;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.composed.NonCutaneousNeoplasia;
import it.univaq.disim.bioinformatics.melanoq.model.composed.NonMelanomaSkinCancer;
import it.univaq.disim.bioinformatics.melanoq.model.composed.PregnanciesInfo;
import it.univaq.disim.bioinformatics.melanoq.model.composed.Treatment;

import java.util.List;

public class C2 {

    //TODO: waiting to understand what it means
    @Field
    private String historyOfMedicalDiagnoses;

    //TODO: Optional
    @Field
    private List<Treatment> preaviusAndConcomitantTreatments;

    @Field
    private PregnanciesInfo pregnancyHistory;

    @Field
    private List<NonMelanomaSkinCancer> lifetimeHistoryOfNonMelanomaSkinCAncer;

    @Field
    private List<NonCutaneousNeoplasia> nonCutaneousNeoplasias;

    public C2(){ }

    public String getHistoryOfMedicalDiagnoses() {
        return historyOfMedicalDiagnoses;
    }

    public void setHistoryOfMedicalDiagnoses(String historyOfMedicalDiagnoses) {
        this.historyOfMedicalDiagnoses = historyOfMedicalDiagnoses;
    }

    public List<Treatment> getPreaviusAndConcomitantTreatments() {
        return preaviusAndConcomitantTreatments;
    }

    public void setPreaviusAndConcomitantTreatments(List<Treatment> preaviusAndConcomitantTreatments) {
        this.preaviusAndConcomitantTreatments = preaviusAndConcomitantTreatments;
    }

    public PregnanciesInfo getPregnancyHistory() {
        return pregnancyHistory;
    }

    public void setPregnancyHistory(PregnanciesInfo pregnancyHistory) {
        this.pregnancyHistory = pregnancyHistory;
    }

    public List<NonMelanomaSkinCancer> getLifetimeHistoryOfNonMelanomaSkinCAncer() {
        return lifetimeHistoryOfNonMelanomaSkinCAncer;
    }

    public void setLifetimeHistoryOfNonMelanomaSkinCAncer(List<NonMelanomaSkinCancer> lifetimeHistoryOfNonMelanomaSkinCAncer) {
        this.lifetimeHistoryOfNonMelanomaSkinCAncer = lifetimeHistoryOfNonMelanomaSkinCAncer;
    }

    public List<NonCutaneousNeoplasia> getNonCutaneousNeoplasias() {
        return nonCutaneousNeoplasias;
    }

    public void setNonCutaneousNeoplasias(List<NonCutaneousNeoplasia> nonCutaneousNeoplasias) {
        this.nonCutaneousNeoplasias = nonCutaneousNeoplasias;
    }
}
