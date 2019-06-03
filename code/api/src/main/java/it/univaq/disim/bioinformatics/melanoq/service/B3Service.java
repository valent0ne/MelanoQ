package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.section.B3;

public interface B3Service {

    B3 insert(String documentId, B3 b3);

    B3 get(String documentId);

    B3 update(String documentId, B3 b3);

    B3 delete(String documentId);
}
