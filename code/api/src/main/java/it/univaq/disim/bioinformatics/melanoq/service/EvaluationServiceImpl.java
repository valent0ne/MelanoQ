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


    public Evaluation insert(String dbCodeNumber, Evaluation e, String section) throws BusinessException {
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        switch(section){

            case "b":
                if(q.getBe() != null){
                    throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
                }
                q.setBe(e);
                break;

            case "c":
                if(q.getCe() != null){
                    throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessage.SECTION_ALREADY_PRESENT);
                }
                q.setCe(e);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }
        questionnaireService.insert(q);
        return e;
    }

    public Evaluation get(String dbCodeNumber, String section) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        Evaluation eSection;

        switch(section) {

            case "b":
                if (q.getBe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                eSection = q.getBe();
                break;

            case "c":
                if (q.getCe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                eSection = q.getCe();
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        return eSection;
    }

    public Evaluation update(String dbCodeNumber, Evaluation e, String section) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        switch(section) {

            case "b":
                if (q.getBe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setBe(e);
                break;

            case "c":
                if (q.getCe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setCe(e);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        questionnaireService.update(q);
        return e;
    }

    public void delete(String dbCodeNumber, String section) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        switch(section) {

            case "b":
                if (q.getBe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setBe(null);
                break;

            case "c":
                if (q.getCe() == null) {
                    throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
                }
                q.setCe(null);
                break;

            default:
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.INTERNAL_SERVER_ERROR);

        }

        questionnaireService.update(q);
    }
}
