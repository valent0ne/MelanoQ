package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.A1;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;


    public Questionnaire save(Questionnaire q){

        //TODO
        A1 a1 = new A1();
        a1.setSubject("subjectValue");
        a1.setTimeSdateOfQuestionnaireAdministration(new Date());
        a1.setDatesOfUpdateQuestionnaire(null);
        a1.setDbCodeNumber("dbCodeNumberValue");
        a1.setTypeOfMelanoma("typeOfMelanomaValue");

        Questionnaire q2 = new Questionnaire();
        q2.setA1(a1);
        q2.setType("story");

        return questionnaireRepository.save(q2);
    }


}
