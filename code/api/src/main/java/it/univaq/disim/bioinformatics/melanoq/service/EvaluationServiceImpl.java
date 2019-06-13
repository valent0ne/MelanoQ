package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private QuestionnaireService questionnaireService;


    public Evaluation insert(String documentId, Evaluation e, String section) throws BusinessException {
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        switch(section){

            case "b":
                if(q.getBEvaluation() != null){
                    throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
                }
                q.setBEvaluation(e);
                break;

            case "c":
                if(q.getCEvaluation() != null){
                    throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
                }
                q.setCEvaluation(e);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }
        questionnaireService.insert(q);
        return e;
    }

    public Evaluation get(String documentId, String section) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        Evaluation eSection;

        switch(section) {

            case "b":
                if (q.getBEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                eSection = q.getBEvaluation();
                break;

            case "c":
                if (q.getCEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                eSection = q.getCEvaluation();
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        return eSection;
    }

    public Evaluation update(String documentId, Evaluation e, String section) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        switch(section) {

            case "b":
                if (q.getBEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setBEvaluation(e);
                break;

            case "c":
                if (q.getCEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setCEvaluation(e);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        questionnaireService.update(q);
        return e;
    }

    public void delete(String documentId, String section) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);

        switch(section) {

            case "b":
                if (q.getBEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setBEvaluation(null);
                break;

            case "c":
                if (q.getCEvaluation() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setCEvaluation(null);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        questionnaireService.update(q);
    }
}