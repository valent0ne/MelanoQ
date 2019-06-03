package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;

public interface QuestionnaireService {

    Questionnaire findOneByDocumentId(String documentId);

    Questionnaire insert(Questionnaire q);

    Questionnaire update(Questionnaire q);


}
