package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.B1;

public interface B1Service {

    B1 insert(String documentId, B1 b1);

    B1 get(String documentId);

    B1 update(String documentId, B1 b1);

    B1 delete(String documentId);
}
