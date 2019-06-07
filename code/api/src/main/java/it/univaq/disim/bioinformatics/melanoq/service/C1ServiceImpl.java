package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.C1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class C1ServiceImpl implements C1Service{

    @Autowired
    private QuestionnaireService questionnaireService;


    public C1 insert(String documentId, C1 c1) throws BusinessException {
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC1() != null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
        }

        q.setC1(c1);
        return questionnaireService.insert(q).getC1();
    }

    public C1 get(String documentId) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        return q.getC1();
    }

    public C1 update(String documentId, C1 c1) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setC1(c1);
        return questionnaireService.update(q).getC1();
    }

    public void delete(String documentId) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setC1(null);
        questionnaireService.update(q);
    }
}
