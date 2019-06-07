package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.BusinessException;
import it.univaq.disim.bioinformatics.melanoq.model.section.D;

import java.util.List;

public interface DService {

    D insert(String documentId, D d) throws BusinessException;

    List<D> get(String documentId) throws BusinessException;

    D update(String documentId, D d) throws BusinessException;

    void delete(String documentId) throws BusinessException;

    void delete(String documentId, D d) throws BusinessException;

}
