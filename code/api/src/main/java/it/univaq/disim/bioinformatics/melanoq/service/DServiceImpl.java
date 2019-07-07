package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.ErrorMessage;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.model.section.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("Duplicates")
@Service
public class DServiceImpl implements DService{

    @Autowired
    private QuestionnaireService questionnaireService;


    public D insert(String dbCodeNumber, D d) throws BusinessException {

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        q.getDList().add(d);

        questionnaireService.insert(q);
        return d;
    }

    public List<D> get(String dbCodeNumber) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getDList().size() == 0 || q.getDList() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        return q.getDList();
    }

    public D update(String dbCodeNumber, D d) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        List<D> dList = q.getDList();
        // If the element with the same identifier of d is not already in the list then there is an error
        if(!dList.contains(d.getIdentifier())){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        // Otherwise, i.e. the element with the same identifier is already in the list, remove it
        dList.remove(d.getIdentifier());
        // next add the new element d in the list
        dList.add(d);

        q.setDList(dList);
        questionnaireService.update(q);
        return d;
    }

    public void delete(String dbCodeNumber) throws BusinessException{

        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        if(q.getDList().size() == 0 || q.getDList() == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }

        q.setDList(null);
        questionnaireService.update(q);
    }

    public void delete(String dbCodeNumber, D d) throws BusinessException{
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);

        List<D> dList = q.getDList();
        // If the element with the same identifier of d is not already in the list than there is an error
        if(!dList.contains(d)){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessage.SECTION_MISSING);
        }
        // Otherwise, i.e. the element with the same identifier is already in the list, remove it
        dList.remove(d);

        q.setDList(dList);
        questionnaireService.update(q);
    }

}
