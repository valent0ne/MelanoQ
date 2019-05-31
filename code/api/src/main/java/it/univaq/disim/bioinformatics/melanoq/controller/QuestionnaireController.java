package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController{

    @Autowired
    private QuestionnaireService questionnaireService;


    @GetMapping("/test")
    public Response testAuth(HttpServletRequest request){
        return new Response(HttpStatus.OK, request);
    }



}
