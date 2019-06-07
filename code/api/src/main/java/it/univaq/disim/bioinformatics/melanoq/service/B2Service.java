package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.B2;

public interface B2Service {

    B2 insert(String documentId, B2 b2) throws BusinessException;

    B2 get(String documentId) throws BusinessException;

    B2 update(String documentId, B2 b2) throws BusinessException;

    void delete(String documentId) throws BusinessException;
}
