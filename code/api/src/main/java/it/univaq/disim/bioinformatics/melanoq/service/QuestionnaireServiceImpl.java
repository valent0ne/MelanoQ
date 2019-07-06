package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    public Questionnaire findOneByDocumentId(String documentId){

        Questionnaire q = questionnaireRepository.findOneByDocumentId(documentId);
        if(q == null){
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.DOC_ID_NOT_VALID);
        }
        return q;
    }

    public Questionnaire insert(Questionnaire q){
        return questionnaireRepository.save(q);
    }

    public Questionnaire update(Questionnaire q){
        // update does not exists in couchbase, execute additonal logic prior to save (update) e.g., change timestamp etc...
        return questionnaireRepository.save(q);
    }





}
