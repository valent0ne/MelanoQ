package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.model.Questionnaire;
import it.univaq.disim.bioinformatics.melanoq.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController{

    @Autowired
    private QuestionnaireService questionnaireService;


    @PostMapping("/")
    public Response createQuestionnaire() throws Exception{
        //TODO
        questionnaireService.save(new Questionnaire());
        return Response.DEFAULT_RESPONSE_OK;
    }



}
