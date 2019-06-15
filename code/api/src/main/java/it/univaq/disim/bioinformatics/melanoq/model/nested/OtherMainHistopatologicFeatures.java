package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class OtherMainHistopatologicFeatures {
    private String subtype;
    private String mitoticRate;
    private String ulceration;
    private String tumorGrowthPhase;
    private String regression;
    private String tumorInfiltratingLymphocytes;
    private String associatedNevus;
    private String vascularInvasion;
    private String microsatellitosis;
    private String pigmentation;
    private String solarElastosis;
    private String lateralMarginStatus;
    private String deepMarginStatus;

    public OtherMainHistopatologicFeatures(){

    }

    public OtherMainHistopatologicFeatures(String subtype, String mitoticRate, String tumorGrowthPhase, String regression, String tumorInfiltratingLymphocytes, String associatedNevus, String vascularInvasion, String microsatellitosis, String pigmentation, String solarElastosis, String lateralMarginStatus, String deepMarginStatus) {
        this.subtype = subtype;
        this.mitoticRate = mitoticRate;
        this.tumorGrowthPhase = tumorGrowthPhase;
        this.regression = regression;
        this.tumorInfiltratingLymphocytes = tumorInfiltratingLymphocytes;
        this.associatedNevus = associatedNevus;
        this.vascularInvasion = vascularInvasion;
        this.microsatellitosis = microsatellitosis;
        this.pigmentation = pigmentation;
        this.solarElastosis = solarElastosis;
        this.lateralMarginStatus = lateralMarginStatus;
        this.deepMarginStatus = deepMarginStatus;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getMitoticRate() {
        return mitoticRate;
    }

    public void setMitoticRate(String mitoticRate) {
        this.mitoticRate = mitoticRate;
    }

    public String getUlceration() {
        return ulceration;
    }

    public void setUlceration(String ulceration) {
        this.ulceration = ulceration;
    }

    public String getTumorGrowthRate() {
        return tumorGrowthPhase;
    }

    public void setTumorGrowthRate(String tumorGrowthPhase) {
        this.tumorGrowthPhase = tumorGrowthPhase;
    }

    public String getRegression() {
        return regression;
    }

    public void setRegression(String regression) {
        this.regression = regression;
    }

    public String getTumorInfiltratingLymphocytes() {
        return tumorInfiltratingLymphocytes;
    }

    public void setTumorInfiltratingLymphocytes(String tumorInfiltratingLymphocytes) {
        this.tumorInfiltratingLymphocytes = tumorInfiltratingLymphocytes;
    }

    public String getAssociatedNevus() {
        return associatedNevus;
    }

    public void setAssociatedNevus(String associatedNevus) {
        this.associatedNevus = associatedNevus;
    }

    public String getVascularInvasion() {
        return vascularInvasion;
    }

    public void setVascularInvasion(String vascularInvasion) {
        this.vascularInvasion = vascularInvasion;
    }

    public String getMicrosatellitosis() {
        return microsatellitosis;
    }

    public void setMicrosatellitosis(String microsatellitosis) {
        this.microsatellitosis = microsatellitosis;
    }

    public String getPigmentation() {
        return pigmentation;
    }

    public void setPigmentation(String pigmentation) {
        this.pigmentation = pigmentation;
    }

    public String getSolarElastosis() {
        return solarElastosis;
    }

    public void setSolarElastosis(String solarElastosis) {
        this.solarElastosis = solarElastosis;
    }

    public String getLateralMarginStatus() {
        return lateralMarginStatus;
    }

    public void setLateralMarginStatus(String lateralMarginStatus) {
        this.lateralMarginStatus = lateralMarginStatus;
    }

    public String getDeepMarginStatus() {
        return deepMarginStatus;
    }

    public void setDeepMarginStatus(String deepMarginStatus) {
        this.deepMarginStatus = deepMarginStatus;
    }
}
