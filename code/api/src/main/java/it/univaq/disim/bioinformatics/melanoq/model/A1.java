package it.univaq.disim.bioinformatics.melanoq.model;

import java.util.Date;
import java.util.List;

public class A1 {

    private String subject;
    private String dbCodeNumber;
    private Date timeSdateOfQuestionnaireAdministration;
    private List<Date> datesOfUpdateQuestionnaire;
    private String typeOfMelanoma;


    public A1(){ }


    public A1(String subject, String dbCodeNumber, Date timeSdateOfQuestionnaireAdministration, List<Date> datesOfUpdateQuestionnaire, String typeOfMelanoma) {
        this.subject = subject;
        this.dbCodeNumber = dbCodeNumber;
        this.timeSdateOfQuestionnaireAdministration = timeSdateOfQuestionnaireAdministration;
        this.datesOfUpdateQuestionnaire = datesOfUpdateQuestionnaire;
        this.typeOfMelanoma = typeOfMelanoma;
    }

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

    public Date getTimeSdateOfQuestionnaireAdministration() {
        return timeSdateOfQuestionnaireAdministration;
    }

    public void setTimeSdateOfQuestionnaireAdministration(Date timeSdateOfQuestionnaireAdministration) {
        this.timeSdateOfQuestionnaireAdministration = timeSdateOfQuestionnaireAdministration;
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
