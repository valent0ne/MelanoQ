package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.A2;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("Duplicates")
@Service
public class A2ServiceImpl implements A2Service{

    @Autowired
    private QuestionnaireService questionnaireService;

    public A2 insert(String dbCodeNumber, A2 a2) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA2() != null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
        }

        q.setA2(a2);
        return questionnaireService.insert(q).getA2();

    }


    public A2 get(String dbCodeNumber) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        return q.getA2();

    }

    public A2 update(String dbCodeNumber, A2 a2) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setA2(a2);
        return questionnaireService.update(q).getA2();

    }

    public void delete(String dbCodeNumber) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getA2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        q.setA2(null);

        questionnaireService.update(q);
    }

}
