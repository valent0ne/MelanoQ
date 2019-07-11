package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.model.*;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Smoking;
import it.univaq.disim.bioinformatics.melanoq.model.section.*;
import it.univaq.disim.bioinformatics.melanoq.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController{
    private static Logger LOGGER = LoggerFactory.getLogger(QuestionnaireController.class);



    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private A1Service a1Service;

    @Autowired
    private A2Service a2Service;

    @Autowired
    private B1Service b1Service;

    @Autowired
    private B2Service b2Service;

    @Autowired
    private B3Service b3Service;

    @Autowired
    private C1Service c1Service;

    @Autowired
    private C2Service c2Service;

    @Autowired
    private C3Service c3Service;

    @Autowired
    private DService dService;

    @Autowired
    private EvaluationService evaluationService;


/*
    @GetMapping("/test")
    public Response testAuth(HttpServletRequest request){
        return new Response(HttpStatus.OK, request);
    }

    @PostMapping("/qtest")
    public String testQ(HttpServletRequest request){
        Questionnaire q = new Questionnaire();
        q.setType("current");
        q.setDocumentId("pippo");
        q.setA1(new A1());
        q.setA2(new A2());
        q.setB1(new B1());
        B3 b3 = new B3();
        Smoking smoking = new Smoking();
        smoking.setHowOften("na freca");
        smoking.setAgeWhenStartedSmoking(11);
        b3.setSmoking(smoking);
        q.setB3(b3);
        questionnaireService.insert(q);
        return ":)";
    }

    @GetMapping("/qtest/{dbCodeNumber}")
    public Response testQGet(@PathVariable(value="dbCodeNumber") String dbCodeNumber, HttpServletRequest request){
        Questionnaire q = questionnaireService.findOneByDocumentId(dbCodeNumber);
        Response<Questionnaire> response = new Response<>(HttpStatus.OK, request);
        response.setData(q);
        return response;
    }

*/

    /////////////////////// QUESTIONNAIRE //////////////////////////
    @GetMapping("/{dbCodeNumber}")
    public Response getQuestionnaire(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Questionnaire q = questionnaireService.findOneByDbCodeNumber(dbCodeNumber);
        Response<Questionnaire> response = new Response<>(HttpStatus.OK, request);
        response.setData(q);
        return response;
    }

    @PostMapping("/query")
    public Response getQuestionnaires(HttpServletRequest request, @RequestBody Query query){
        List<Questionnaire> q;
        if (query == null || query.getChildren().isEmpty()){
            q = questionnaireService.findAll();
        }else{
            q = questionnaireService.query(query);
        }
        Response<List<Questionnaire>> response = new Response<>(HttpStatus.OK, request);
        response.setData(q);
        return response;
    }

    @GetMapping("/")
    public Response getAllQuestionnaires(HttpServletRequest request){
        List<Questionnaire> q = questionnaireService.findAll();
        Response<List<Questionnaire>> response = new Response<>(HttpStatus.OK, request);
        response.setData(q);
        return response;
    }

    @GetMapping("/validate/{dbCodeNumber}")
    public Response getQuestionnaires(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Boolean outcome = questionnaireService.findOneByDbCodeNumber(dbCodeNumber) != null;
        Response<Boolean> response = new Response<>(HttpStatus.OK, request);
        response.setData(outcome);
        return response;
    }

    //////////////////////////// A1 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/a1")
    public Response insertA1(HttpServletRequest request, @RequestBody A1 a1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
            A1 a1Obj = a1Service.insert(dbCodeNumber, a1);
            Response<A1> response = new Response<>(HttpStatus.CREATED, request);
            response.setData(a1Obj);
            return response;
    }

    @GetMapping("/{dbCodeNumber}/a1")
    public Response getA1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        A1 a1Obj = a1Service.get(dbCodeNumber);
        Response<A1> response = new Response<>(HttpStatus.OK, request);
        response.setData(a1Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/a1")
    public Response updateA1(HttpServletRequest request, @RequestBody A1 a1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        A1 a1Obj = a1Service.update(dbCodeNumber, a1);
        Response<A1> response = new Response<>(HttpStatus.OK, request);
        response.setData(a1Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/a1")
    public Response deleteA1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        a1Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }




    //////////////////////////// A2 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/a2")
    public Response insertA2(HttpServletRequest request, @RequestBody A2 a2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        A2 a2Obj = a2Service.insert(dbCodeNumber, a2);
        Response<A2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(a2Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/a2")
    public Response getA2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        A2 a2Obj = a2Service.get(dbCodeNumber);
        Response<A2> response = new Response<>(HttpStatus.OK, request);
        response.setData(a2Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/a2")
    public Response updateA2(HttpServletRequest request, @RequestBody A2 a2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        A2 a2Obj = a2Service.update(dbCodeNumber, a2);
        Response<A2> response = new Response<>(HttpStatus.OK, request);
        response.setData(a2Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/a2")
    public Response deleteA2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        a2Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B1 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/b1")
    public Response insertB1(HttpServletRequest request, @RequestBody B1 b1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B1 b1Obj = b1Service.insert(dbCodeNumber, b1);
        Response<B1> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b1Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/b1")
    public Response getB1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B1 b1Obj = b1Service.get(dbCodeNumber);
        Response<B1> response = new Response<>(HttpStatus.OK, request);
        response.setData(b1Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/b1")
    public Response updateB1(HttpServletRequest request, @RequestBody B1 b1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B1 b1Obj = b1Service.update(dbCodeNumber, b1);
        Response<B1> response = new Response<>(HttpStatus.OK, request);
        response.setData(b1Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/b1")
    public Response deleteB1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        b1Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B2 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/b2")
    public Response insertB2(HttpServletRequest request, @RequestBody B2 b2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B2 b2Obj = b2Service.insert(dbCodeNumber, b2);
        Response<B2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b2Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/b2")
    public Response getB2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B2 b2Obj = b2Service.get(dbCodeNumber);
        Response<B2> response = new Response<>(HttpStatus.OK, request);
        response.setData(b2Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/b2")
    public Response updateB2(HttpServletRequest request, @RequestBody B2 b2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B2 b2Obj = b2Service.update(dbCodeNumber, b2);
        Response<B2> response = new Response<>(HttpStatus.OK, request);
        response.setData(b2Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/b2")
    public Response deleteB2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        b2Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B3 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/b3")
    public Response insertB3(HttpServletRequest request, @RequestBody B3 b3, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B3 b3Obj = b3Service.insert(dbCodeNumber, b3);
        Response<B3> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b3Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/b3")
    public Response getB3(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B3 b3Obj = b3Service.get(dbCodeNumber);
        Response<B3> response = new Response<>(HttpStatus.OK, request);
        response.setData(b3Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/b3")
    public Response updateB3(HttpServletRequest request, @RequestBody B3 b3, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        B3 b3Obj = b3Service.update(dbCodeNumber, b3);
        Response<B3> response = new Response<>(HttpStatus.OK, request);
        response.setData(b3Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/b3")
    public Response deleteB3(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        b3Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// BEvaluation ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/be")
    public Response insertBEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.insert(dbCodeNumber, e, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(eObj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/be")
    public Response getBEvaluation(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.get(dbCodeNumber, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/be")
    public Response updateBEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.update(dbCodeNumber, e, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/be")
    public Response deleteBEvaluation(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        evaluationService.delete(dbCodeNumber, "b");
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// C1 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/c1")
    public Response insertC1(HttpServletRequest request, @RequestBody C1 c1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C1 c1Obj = c1Service.insert(dbCodeNumber, c1);
        Response<C1> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c1Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/c1")
    public Response getC1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C1 c1Obj = c1Service.get(dbCodeNumber);
        Response<C1> response = new Response<>(HttpStatus.OK, request);
        response.setData(c1Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/c1")
    public Response updateC1(HttpServletRequest request, @RequestBody C1 c1, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C1 c1Obj = c1Service.update(dbCodeNumber, c1);
        Response<C1> response = new Response<>(HttpStatus.OK, request);
        response.setData(c1Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/c1")
    public Response deleteC1(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        c1Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// C2 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/c2")
    public Response insertC2(HttpServletRequest request, @RequestBody C2 c2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C2 c2Obj = c2Service.insert(dbCodeNumber, c2);
        Response<C2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c2Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/c2")
    public Response getC2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C2 c2Obj = c2Service.get(dbCodeNumber);
        Response<C2> response = new Response<>(HttpStatus.OK, request);
        response.setData(c2Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/c2")
    public Response updateC2(HttpServletRequest request, @RequestBody C2 c2, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C2 c2Obj = c2Service.update(dbCodeNumber, c2);
        Response<C2> response = new Response<>(HttpStatus.OK, request);
        response.setData(c2Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/c2")
    public Response deleteC2(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        c2Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// C3 ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/c3")
    public Response insertC3(HttpServletRequest request, @RequestBody C3 c3, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C3 c3Obj = c3Service.insert(dbCodeNumber, c3);
        Response<C3> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c3Obj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/c3")
    public Response getC3(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C3 c3Obj = c3Service.get(dbCodeNumber);
        Response<C3> response = new Response<>(HttpStatus.OK, request);
        response.setData(c3Obj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/c3")
    public Response updateC3(HttpServletRequest request, @RequestBody C3 c3, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        C3 c3Obj = c3Service.update(dbCodeNumber, c3);
        Response<C3> response = new Response<>(HttpStatus.OK, request);
        response.setData(c3Obj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/c3")
    public Response deleteC3(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        c3Service.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// CEvaluation ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/ce")
    public Response insertCEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.insert(dbCodeNumber, e, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(eObj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/ce")
    public Response getCEvaluation(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.get(dbCodeNumber, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/ce")
    public Response updateCEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        Evaluation eObj = evaluationService.update(dbCodeNumber, e, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/ce")
    public Response deleteCEvaluation(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        evaluationService.delete(dbCodeNumber, "c");
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// D ////////////////////////////////

    @PostMapping("/{dbCodeNumber}/d")
    public Response insertD(HttpServletRequest request, @RequestBody D d, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        D dObj = dService.insert(dbCodeNumber, d);
        Response<D> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(dObj);
        return response;
    }

    @GetMapping("/{dbCodeNumber}/d")
    public Response getD(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        List<D> dObj = dService.get(dbCodeNumber);
        Response<List<D>> response = new Response<>(HttpStatus.OK, request);
        response.setData(dObj);
        return response;
    }

    @PatchMapping("/{dbCodeNumber}/d")
    public Response updateD(HttpServletRequest request, @RequestBody D d, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        D dObj = dService.update(dbCodeNumber, d);
        Response<D> response = new Response<>(HttpStatus.OK, request);
        response.setData(dObj);
        return response;
    }

    @DeleteMapping("/{dbCodeNumber}/d")
    public Response deleteD(HttpServletRequest request, @PathVariable(value="dbCodeNumber") String dbCodeNumber){
        dService.delete(dbCodeNumber);
        return new Response<>(HttpStatus.OK, request);
    }


}
