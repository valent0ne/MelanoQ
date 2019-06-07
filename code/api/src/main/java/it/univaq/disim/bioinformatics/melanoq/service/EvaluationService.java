package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.Evaluation;

public interface EvaluationService {

    Evaluation insert(String documentId, Evaluation e, String section) throws BusinessException;

    Evaluation get(String documentId, String section) throws BusinessException;

    Evaluation update(String documentId, Evaluation e, String section) throws BusinessException;

    void delete(String documentId, String section) throws BusinessException;
}
