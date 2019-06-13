package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class CongenitalNevi {

    private int numberOfMediumSizedNevi;

    // TODO: to fill only if the preavious one attribute has a value grather than 0
    private String siteOfMediumSizedNevi;

    private int numberOfLargeSizedNevi;

    // TODO: to fill only if the preavious one attribute has a value grather than 0
    private String siteOfLargeSizedNevi;

    private int numberOfGiantNevi;

    // TODO: to fill only if the preavious attribute has a value grather than 0
    private String siteOfGiantSizedNevi;

    public CongenitalNevi(){ }

    public int getNumberOfMediumSizedNevi() {
        return numberOfMediumSizedNevi;
    }

    public void setNumberOfMediumSizedNevi(int numberOfMediumSizedNevi) {
        this.numberOfMediumSizedNevi = numberOfMediumSizedNevi;
    }

    public String getSiteOfMediumSizedNevi() {
        return siteOfMediumSizedNevi;
    }

    public void setSiteOfMediumSizedNevi(String siteOfMediumSizedNevi) {
        this.siteOfMediumSizedNevi = siteOfMediumSizedNevi;
    }

    public int getNumberOfLargeSizedNevi() {
        return numberOfLargeSizedNevi;
    }

    public void setNumberOfLargeSizedNevi(int numberOfLargeSizedNevi) {
        this.numberOfLargeSizedNevi = numberOfLargeSizedNevi;
    }

    public String getSiteOfLargeSizedNevi() {
        return siteOfLargeSizedNevi;
    }

    public void setSiteOfLargeSizedNevi(String siteOfLargeSizedNevi) {
        this.siteOfLargeSizedNevi = siteOfLargeSizedNevi;
    }

    public int getNumberOfGiantNevi() {
        return numberOfGiantNevi;
    }

    public void setNumberOfGiantNevi(int numberOfGiantNevi) {
        this.numberOfGiantNevi = numberOfGiantNevi;
    }

    public String getSiteOfGiantNevi() {
        return siteOfGiantSizedNevi;
    }

    public void setSiteOfGiantNevi(String siteOfGiantSizedNevi) {
        this.siteOfGiantSizedNevi = siteOfGiantSizedNevi;
    }
}
