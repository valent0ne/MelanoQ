package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.A2;

public interface A2Service {

    A2 insert(String documentId, A2 a2);

    A2 get(String documentId);

    A2 update(String documentId, A2 a2);

    A2 delete(String documentId);
}
