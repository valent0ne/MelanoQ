package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.C2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class C2ServiceImpl implements C2Service{

    @Autowired
    private QuestionnaireService questionnaireService;


    public C2 insert(String documentId, C2 c2) throws BusinessException {
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC2() != null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
        }

        q.setC2(c2);
        return questionnaireService.insert(q).getC2();
    }

    public C2 get(String documentId) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        return q.getC2();
    }

    public C2 update(String documentId, C2 c2) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setC2(c2);
        return questionnaireService.update(q).getC2();
    }

    public void delete(String documentId) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getC2() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setC2(null);
        questionnaireService.update(q);
    }
}
