package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.A2;

public interface A2Service {

    A2 insert(String documentId, A2 a2) throws BusinessException;

    A2 get(String documentId) throws BusinessException;

    A2 update(String documentId, A2 a2) throws BusinessException;

    void delete(String documentId) throws BusinessException;
}
