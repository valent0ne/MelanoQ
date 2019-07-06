package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;

@SuppressWarnings("Duplicates")
@Service
public class A1ServiceImpl implements A1Service{

    @Autowired
    QuestionnaireService questionnaireService;

    public A1 insert(String documentId, A1 a1) throws BusinessException{
        // Questionnaire q = questionnaireService.findOneByDocumentId(documentId);
        Questionnaire q = new Questionnaire();
        q.setDocumentId(documentId);
        q.setA1(a1);
        return questionnaireService.insert(q).getA1();
    }

    public A1 get(String documentId) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        return q.getA1();
    }

    @Override
    public A1 update(String documentId, A1 a1) {
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        q.setA1(a1);

        return questionnaireService.update(q).getA1();
    }

    @Override
    public void delete(String documentId) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getA1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        q.setA1(null);

        questionnaireService.update(q);
    }
}
