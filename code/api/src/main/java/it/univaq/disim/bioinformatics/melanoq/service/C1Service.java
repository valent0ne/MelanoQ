package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.C1;

public interface C1Service {

    C1 insert(String documentId, C1 c1);

    C1 get(String documentId);

    C1 update(String documentId, C1 c1);

    C1 delete(String documentId);
}
