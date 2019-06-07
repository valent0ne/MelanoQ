package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.B1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class B1ServiceImpl implements B1Service{

    @Autowired
    private QuestionnaireService questionnaireService;

    public B1 insert(String documentId, B1 b1)  throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getB1() != null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
        }

        q.setB1(b1);

        return questionnaireService.insert(q).getB1();

    }

    public B1 get(String documentId)  throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getB1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        return q.getB1();

    }

    public B1 update(String documentId, B1 b1)  throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getB1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setB1(b1);
        return questionnaireService.update(q).getB1();

    }

    public void delete(String documentId)  throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        if(q.getB1() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setB1(null);
        questionnaireService.update(q);
    }
}
