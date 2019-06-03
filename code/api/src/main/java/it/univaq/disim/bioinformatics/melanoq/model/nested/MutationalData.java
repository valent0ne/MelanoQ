package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class MutationalData {

    private String gene;

    private String sourceOfData;

    // Only if metastatic
    private String site;

    public MutationalData(){ }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getSourceOfData() {
        return sourceOfData;
    }

    public void setSourceOfData(String sourceOfData) {
        this.sourceOfData = sourceOfData;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
