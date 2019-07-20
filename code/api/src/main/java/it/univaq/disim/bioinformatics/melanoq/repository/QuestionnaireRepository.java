package it.univaq.disim.bioinformatics.melanoq.repository;

import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, String> {

    // do not need to re-declare update, save, delete

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and UPPER(a1.dbCodeNumber) = UPPER($1)")
    Questionnaire findOneByDbCodeNumberIgnoreCase(String dbCodeNumber);

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter}")
    List<Questionnaire> findAll();
}
