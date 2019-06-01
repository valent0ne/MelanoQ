package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.A1;
import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Autowired
    QuestionnaireRepository questionnaireRepository;





}
