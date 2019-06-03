package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.A1;

public interface A1Service {

    A1 insert(String documentId, A1 a1);

    A1 get(String documentId);

    A1 update(String documentId, A1 a1);

    void delete(String documentId);



}
