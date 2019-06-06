package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;

public interface A1Service {

    A1 insert(String documentId, A1 a1) throws BusinessException;

    A1 get(String documentId) throws BusinessException;

    A1 update(String documentId, A1 a1) throws BusinessException;

    void delete(String documentId) throws BusinessException;



}
