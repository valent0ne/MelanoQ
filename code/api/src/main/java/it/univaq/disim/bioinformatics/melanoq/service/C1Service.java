package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.C1;

public interface C1Service {

    C1 insert(String documentId, C1 c1) throws BusinessException;

    C1 get(String documentId) throws BusinessException;

    C1 update(String documentId, C1 c1) throws BusinessException;

    void delete(String documentId) throws BusinessException;
}
