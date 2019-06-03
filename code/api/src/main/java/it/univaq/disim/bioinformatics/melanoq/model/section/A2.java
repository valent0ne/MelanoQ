package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Occupation;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Residency;

import java.util.List;

public class A2 {

    @Field
    private String sex;

    //TODO: if the date of birth is unknown than put UNK
    @Field
    private String dateOfBirth;

    @Field
    private String cityOfBirth;

    @Field
    private String provinceOfBirth;

    @Field
    private String countryOfBirth;

    //TODO: weight must be checked every 12 months
    @Field
    private String weight;

    //TODO: height must be checked every 12 months
    @Field
    private String height;

    @Field
    private String ethnicity;

    @Field
    private List<Residency> residencies;

    @Field
    private String education;

    @Field
    private String currentOccupationalStatus;

    // TODO: It can be optional
    @Field
    private List<Occupation> historyOfOccupations;

    public A2(){ }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getProvinceOfBirth() {
        return provinceOfBirth;
    }

    public void setProvinceOfBirth(String provinceOfBirth) {
        this.provinceOfBirth = provinceOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public List<Residency> getResidencies() {
        return residencies;
    }

    public void setResidencies(List<Residency> residencies) {
        this.residencies = residencies;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCurrentOccupationalStatus() {
        return currentOccupationalStatus;
    }

    public void setCurrentOccupationalStatus(String currentOccupationalStatus) {
        this.currentOccupationalStatus = currentOccupationalStatus;
    }

    public List<Occupation> getHistoryOfOccupations() {
        return historyOfOccupations;
    }

    public void setHistoryOfOccupations(List<Occupation> historyOfOccupations) {
        this.historyOfOccupations = historyOfOccupations;
    }
}
