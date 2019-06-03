package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.C2;

public interface C2Service {

    C2 insert(String documentId, C2 c2);

    C2 get(String documentId);

    C2 update(String documentId, C2 c2);

    C2 delete(String documentId);
}
