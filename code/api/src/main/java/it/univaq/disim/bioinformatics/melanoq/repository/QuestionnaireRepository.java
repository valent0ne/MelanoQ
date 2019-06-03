package it.univaq.disim.bioinformatics.melanoq.repository;

import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, String> {


    // do not need to re-declare update, save, delete

    Questionnaire findOneByDocumentId(String documentId);
}
