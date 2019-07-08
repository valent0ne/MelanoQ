package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(QuestionnaireServiceImpl.class);



    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    private static final TimeZone tz = TimeZone.getTimeZone("GMT");

    public Questionnaire findOneByDbCodeNumber(String dbCodeNumber){
        LOGGER.info("findOneByDbCodeNumber: dbCodeNUmber = {}", dbCodeNumber);
        Questionnaire q = questionnaireRepository.findOneByDbCodeNumberIgnoreCase(dbCodeNumber);
        if(q == null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.DB_CODE_NUMBER_NOT_VALID);
        }
        return q;
    }

    public List<Questionnaire> findAll(){

        List<Questionnaire> q = questionnaireRepository.findAll();

        return q;
    }

    public Questionnaire insert(Questionnaire q) throws BusinessException{
        //if there is no section A1, then there is something wrong
        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_A1);
        }

        Date date = new Date(System.currentTimeMillis());
        sdf.setTimeZone(tz);
        if(q.getA1().getDatesOfUpdateQuestionnaire() != null){
            q.getA1().getDatesOfUpdateQuestionnaire().add(sdf.format(date));
        }else{
            List<String> datesOfUpdateQuestionnaire = new ArrayList<>();
            datesOfUpdateQuestionnaire.add(sdf.format(date));
            q.getA1().setDatesOfUpdateQuestionnaire(datesOfUpdateQuestionnaire);
        }

        LOGGER.info("Inserting data to db...");

        return questionnaireRepository.save(q);
    }

    public Questionnaire update(Questionnaire q) throws BusinessException{
        // update does not exists in couchbase
        return this.insert(q);
    }





}
