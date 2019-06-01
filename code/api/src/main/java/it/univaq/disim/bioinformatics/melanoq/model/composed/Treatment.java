package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class Treatment {

    private String treatmentName;

    private String treatmentStartingTime;

    private String treatmentEndingTime;

    public Treatment(){ }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getTreatmentStartingTime() {
        return treatmentStartingTime;
    }

    public void setTreatmentStartingTime(String treatmentStartingTime) {
        this.treatmentStartingTime = treatmentStartingTime;
    }

    public String getTreatmentEndingTime() {
        return treatmentEndingTime;
    }

    public void setTreatmentEndingTime(String treatmentEndingTime) {
        this.treatmentEndingTime = treatmentEndingTime;
    }
}
