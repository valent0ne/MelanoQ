package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;

import java.util.Date;
import java.util.List;

public class A1 {

    @Field
    private String subject;

    @Field
    private String dbCodeNumber;

    @Field
    private String dateOfQuestionnaireAdministration;

    //TODO: DD-MMM-YYYY it's why we put the date as a string
    @Field
    private List<Date> datesOfUpdateQuestionnaire;

    @Field
    private String typeOfMelanoma;


    public A1(){ }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDbCodeNumber() {
        return dbCodeNumber;
    }

    public void setDbCodeNumber(String dbCodeNumber) {
        this.dbCodeNumber = dbCodeNumber;
    }

    public String getTimeSdateOfQuestionnaireAdministration() {
        return dateOfQuestionnaireAdministration;
    }

    public void setTimeSdateOfQuestionnaireAdministration(String dateOfQuestionnaireAdministration) {
        this.dateOfQuestionnaireAdministration = dateOfQuestionnaireAdministration;
    }

    public List<Date> getDatesOfUpdateQuestionnaire() {
        return datesOfUpdateQuestionnaire;
    }

    public void setDatesOfUpdateQuestionnaire(List<Date> datesOfUpdateQuestionnaire) {
        this.datesOfUpdateQuestionnaire = datesOfUpdateQuestionnaire;
    }

    public String getTypeOfMelanoma() {
        return typeOfMelanoma;
    }

    public void setTypeOfMelanoma(String typeOfMelanoma) {
        this.typeOfMelanoma = typeOfMelanoma;
    }
}
