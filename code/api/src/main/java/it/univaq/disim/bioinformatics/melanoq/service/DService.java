package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.D;

public interface DService {

    D insert(String documentId, D d);

    D get(String documentId);

    D update(String documentId, D d);

    D delete(String documentId);
}
