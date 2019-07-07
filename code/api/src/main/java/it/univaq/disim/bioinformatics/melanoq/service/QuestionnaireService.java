package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    Questionnaire findOneByDbCodeNumber(String dbCodeNumber);

    Questionnaire insert(Questionnaire q) throws BusinessException;

    Questionnaire update(Questionnaire q) throws BusinessException;

    List<Questionnaire> findAll ();


}
