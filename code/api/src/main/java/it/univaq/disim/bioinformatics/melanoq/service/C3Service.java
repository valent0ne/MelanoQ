package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.C3;

public interface C3Service {

    C3 insert(String documentId, C3 c3) throws BusinessException;

    C3 get(String documentId) throws BusinessException;

    C3 update(String documentId, C3 c3) throws BusinessException;

    void delete(String documentId) throws BusinessException;
}
