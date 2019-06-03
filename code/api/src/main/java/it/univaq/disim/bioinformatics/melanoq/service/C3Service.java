package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.C3;

public interface C3Service {

    C3 insert(String documentId, C3 c3);

    C3 get(String documentId);

    C3 update(String documentId, C3 c3);

    C3 delete(String documentId);
}
