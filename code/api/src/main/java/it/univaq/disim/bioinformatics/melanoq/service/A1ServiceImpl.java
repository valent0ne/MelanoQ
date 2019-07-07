package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;

@SuppressWarnings("Duplicates")
@Service
public class A1ServiceImpl implements A1Service{

    @Autowired
    QuestionnaireService questionnaireService;

    private static Logger LOGGER = LoggerFactory.getLogger(A1ServiceImpl.class);

    public A1 insert(String dbCodeNumber, A1 a1) throws BusinessException{
        Questionnaire qExisting = null;
        try{
            qExisting = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);
        }catch (Exception e){
            LOGGER.warn("Inserting new A1 section (expected case): "+e.getMessage());
        }

        if(qExisting != null){
            LOGGER.warn("Inserting new A1 section (unexpected case): DB code number already in use");
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.DB_CODE_NUMBER_ALREADY_IN_USE);
        }
        LOGGER.warn("Inserting new A1 section...");
        Questionnaire q = new Questionnaire();
        q.setA1(a1);
        return questionnaireService.insert(q).getA1();
    }

    public A1 get(String dbCodeNumber) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        return q.getA1();
    }

    @Override
    public A1 update(String dbCodeNumber, A1 a1) {
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        q.setA1(a1);

        return questionnaireService.update(q).getA1();
    }

    @Override
    public void delete(String dbCodeNumber) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        q.setA1(null);

        questionnaireService.update(q);
    }
}
