package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class MedicalDiagnosis {

    private String diagnosisName;

    private String ICD10Code;

    public MedicalDiagnosis(){ }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getICD10Code() {
        return ICD10Code;
    }

    public void setICD10Code(String ICD10Code) {
        this.ICD10Code = ICD10Code;
    }
}
