package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class ActinicKeratoses {

    // It can be: : scalp, face, other exposed areas (hands, arms, trunk, legs)
    private String site;

    // It can be: isolated/scattered, clustered, confluent
    private String typeOfDistribution;


    public ActinicKeratoses(){ }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTypeOfDistribution() {
        return typeOfDistribution;
    }

    public void setTypeOfDistribution(String typeOfDistribution) {
        this.typeOfDistribution = typeOfDistribution;
    }
}
