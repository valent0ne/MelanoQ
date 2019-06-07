package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.C2;

public interface C2Service {

    C2 insert(String documentId, C2 c2) throws BusinessException;

    C2 get(String documentId) throws BusinessException;

    C2 update(String documentId, C2 c2) throws BusinessException;

    void delete(String documentId) throws BusinessException;
}
