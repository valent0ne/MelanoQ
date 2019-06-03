package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.B2;

public interface B2Service {

    B2 insert(String documentId, B2 b2);

    B2 get(String documentId);

    B2 update(String documentId, B2 b2);

    B2 delete(String documentId);
}
