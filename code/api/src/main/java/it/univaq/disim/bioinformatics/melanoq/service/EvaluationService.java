package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.Evaluation;

public interface EvaluationService {

    Evaluation insert(String documentId, Evaluation e);

    Evaluation get(String documentId);

    Evaluation update(String documentId, Evaluation e);

    Evaluation delete(String documentId);
}
