package it.univaq.disim.bioinformatics.melanoq.controller;

import it.univaq.disim.bioinformatics.melanoq.model.*;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Smoking;
import it.univaq.disim.bioinformatics.melanoq.model.section.A1;
import it.univaq.disim.bioinformatics.melanoq.model.section.A2;
import it.univaq.disim.bioinformatics.melanoq.model.section.B1;
import it.univaq.disim.bioinformatics.melanoq.model.section.B3;
import it.univaq.disim.bioinformatics.melanoq.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController{

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private A1Service a1Service;
/*
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

*/

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

    @GetMapping("/qtest/{documentId}")
    public Response testQGet(@PathVariable(value="documentId") String documentId, HttpServletRequest request){
        Questionnaire q = questionnaireService.findOneByDocumentId(documentId);
        Response<Questionnaire> response = new Response<>(HttpStatus.OK, request);
        response.setData(q);
        return response;
    }




    //////////////////////////// A1 ////////////////////////////////

    @PostMapping("/{documentId}/a1")
    public Response insertA1(HttpServletRequest request, @RequestBody A1 a1, @PathVariable(value="documentId") String documentId){
            A1 a1Obj = a1Service.insert(documentId, a1);
            Response<A1> response = new Response<>(HttpStatus.CREATED, request);
            response.setData(a1Obj);
            return response;
    }
 /*
    @GetMapping("/{documentId}/a1")
    public Response getA1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        A1 a1Obj = a1Service.get(documentId);
        Response<A1> response = new Response<>(HttpStatus.OK, request);
        response.setData(a1Obj);
        return response;
    }

    @PatchMapping("/{documentId}/a1")
    public Response updateA1(HttpServletRequest request, @RequestBody A1 a1, @PathVariable(value="documentId") String documentId){
        A1 a1Obj = a1Service.update(documentId, a1);
        Response<A1> response = new Response<>(HttpStatus.OK, request);
        response.setData(a1Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/a1")
    public Response deleteA1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        a1Service.delete(documentId);
        return = new Response<>(HttpStatus.OK, request);
    }




    //////////////////////////// A2 ////////////////////////////////

    @PostMapping("/{documentId}/a2")
    public Response insertA2(HttpServletRequest request, @RequestBody A2 a2, @PathVariable(value="documentId") String documentId){
        A2 a2Obj = a2Service.insert(documentId, a2);
        Response<A2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(a2Obj);
        return response;
    }

    @GetMapping("/{documentId}/a2")
    public Response getA2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        A2 a2Obj = a2Service.get(documentId);
        Response<A2> response = new Response<>(HttpStatus.OK, request);
        response.setData(a2Obj);
        return response;
    }

    @PatchMapping("/{documentId}/a2")
    public Response updateA2(HttpServletRequest request, @RequestBody A2 a2, @PathVariable(value="documentId") String documentId){
        A2 a2Obj = a2Service.update(documentId, a2);
        Response<A2> response = new Response<>(HttpStatus.OK, request);
        response.setData(a2Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/a2")
    public Response deleteA2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        a2Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B1 ////////////////////////////////

    @PostMapping("/{documentId}/b1")
    public Response insertB1(HttpServletRequest request, @RequestBody B1 b1, @PathVariable(value="documentId") String documentId){
        B1 b1Obj = b1Service.insert(documentId, b1);
        Response<B1> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b1Obj);
        return response;
    }

    @GetMapping("/{documentId}/b1")
    public Response getB1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        B1 b1Obj = b1Service.get(documentId);
        Response<B1> response = new Response<>(HttpStatus.OK, request);
        response.setData(b1Obj);
        return response;
    }

    @PatchMapping("/{documentId}/b1")
    public Response updateB1(HttpServletRequest request, @RequestBody B1 b1, @PathVariable(value="documentId") String documentId){
        B1 b1Obj = b1Service.update(documentId, b1);
        Response<B1> response = new Response<>(HttpStatus.OK, request);
        response.setData(b1Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/b1")
    public Response deleteB1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        b1Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B2 ////////////////////////////////

    @PostMapping("/{documentId}/b2")
    public Response insertB2(HttpServletRequest request, @RequestBody B2 b2, @PathVariable(value="documentId") String documentId){
        B2 b2Obj = b2Service.insert(documentId, b2);
        Response<B2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b2Obj);
        return response;
    }

    @GetMapping("/{documentId}/b2")
    public Response getB2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        B2 b2Obj = b2Service.get(documentId);
        Response<B2> response = new Response<>(HttpStatus.OK, request);
        response.setData(b2Obj);
        return response;
    }

    @PatchMapping("/{documentId}/b2")
    public Response updateB2(HttpServletRequest request, @RequestBody B2 b2, @PathVariable(value="documentId") String documentId){
        B2 b2Obj = b2Service.update(documentId, b2);
        Response<B2> response = new Response<>(HttpStatus.OK, request);
        response.setData(b2Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/b2")
    public Response deleteB2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        b2Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// B3 ////////////////////////////////

    @PostMapping("/{documentId}/b3")
    public Response insertB3(HttpServletRequest request, @RequestBody B3 b3, @PathVariable(value="documentId") String documentId){
        B3 b3Obj = b3Service.insert(documentId, b3);
        Response<B3> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(b3Obj);
        return response;
    }

    @GetMapping("/{documentId}/b3")
    public Response getB3(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        B3 b3Obj = b3Service.get(documentId);
        Response<B3> response = new Response<>(HttpStatus.OK, request);
        response.setData(b3Obj);
        return response;
    }

    @PatchMapping("/{documentId}/b3")
    public Response updateB3(HttpServletRequest request, @RequestBody B3 b3, @PathVariable(value="documentId") String documentId){
        B3 b3Obj = b3Service.update(documentId, b3);
        Response<B3> response = new Response<>(HttpStatus.OK, request);
        response.setData(b3Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/b3")
    public Response deleteB3(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        b3Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// BEvaluation ////////////////////////////////

    @PostMapping("/{documentId}/b/evaluation")
    public Response insertBEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.insert(documentId, e, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(eObj);
        return response;
    }

    @GetMapping("/{documentId}/b/evaluation")
    public Response getBEvaluation(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.get(documentId, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @PatchMapping("/{documentId}/b/evaluation")
    public Response updateBEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.update(documentId, e, "b");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @DeleteMapping("/{documentId}/b/evaluation")
    public Response deleteBEvaluation(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        evaluationService.delete(documentId, "b");
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// C1 ////////////////////////////////

    @PostMapping("/{documentId}/c1")
    public Response insertC1(HttpServletRequest request, @RequestBody C1 c1, @PathVariable(value="documentId") String documentId){
        C1 c1Obj = c1Service.insert(documentId, c1);
        Response<C1> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c1Obj);
        return response;
    }

    @GetMapping("/{documentId}/c1")
    public Response getC1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        C1 c1Obj = c1Service.get(documentId);
        Response<C1> response = new Response<>(HttpStatus.OK, request);
        response.setData(c1Obj);
        return response;
    }

    @PatchMapping("/{documentId}/c1")
    public Response updateC1(HttpServletRequest request, @RequestBody C1 c1, @PathVariable(value="documentId") String documentId){
        C1 c1Obj = c1Service.update(documentId, c1);
        Response<C1> response = new Response<>(HttpStatus.OK, request);
        response.setData(c1Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/c1")
    public Response deleteC1(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        c1Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// C2 ////////////////////////////////

    @PostMapping("/{documentId}/c2")
    public Response insertC2(HttpServletRequest request, @RequestBody C2 c2, @PathVariable(value="documentId") String documentId){
        C2 c2Obj = c2Service.insert(documentId, c2);
        Response<C2> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c2Obj);
        return response;
    }

    @GetMapping("/{documentId}/c2")
    public Response getC2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        C2 c2Obj = c2Service.get(documentId);
        Response<C2> response = new Response<>(HttpStatus.OK, request);
        response.setData(c2Obj);
        return response;
    }

    @PatchMapping("/{documentId}/c2")
    public Response updateC2(HttpServletRequest request, @RequestBody C2 c2, @PathVariable(value="documentId") String documentId){
        C2 c2Obj = c2Service.update(documentId, c2);
        Response<C2> response = new Response<>(HttpStatus.OK, request);
        response.setData(c2Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/c2")
    public Response deleteC2(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        c2Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// C3 ////////////////////////////////

    @PostMapping("/{documentId}/c3")
    public Response insertC3(HttpServletRequest request, @RequestBody C3 c3, @PathVariable(value="documentId") String documentId){
        C3 c3Obj = c3Service.insert(documentId, c3);
        Response<C3> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(c3Obj);
        return response;
    }

    @GetMapping("/{documentId}/c3")
    public Response getC3(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        C3 c3Obj = c3Service.get(documentId);
        Response<C3> response = new Response<>(HttpStatus.OK, request);
        response.setData(c3Obj);
        return response;
    }

    @PatchMapping("/{documentId}/c3")
    public Response updateC3(HttpServletRequest request, @RequestBody C3 c3, @PathVariable(value="documentId") String documentId){
        C3 c3Obj = c3Service.update(documentId, c3);
        Response<C3> response = new Response<>(HttpStatus.OK, request);
        response.setData(c3Obj);
        return response;
    }

    @DeleteMapping("/{documentId}/c3")
    public Response deleteC3(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        c3Service.delete(documentId);
        return new Response<>(HttpStatus.OK, request);

    }



    //////////////////////////// CEvaluation ////////////////////////////////

    @PostMapping("/{documentId}/c/evaluation")
    public Response insertCEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.insert(documentId, e, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(eObj);
        return response;
    }

    @GetMapping("/{documentId}/b/evaluation")
    public Response getCEvaluation(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.get(documentId, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @PatchMapping("/{documentId}/b/evaluation")
    public Response updateCEvaluation(HttpServletRequest request, @RequestBody Evaluation e, @PathVariable(value="documentId") String documentId){
        Evaluation eObj = evaluationService.update(documentId, e, "c");
        Response<Evaluation> response = new Response<>(HttpStatus.OK, request);
        response.setData(eObj);
        return response;
    }

    @DeleteMapping("/{documentId}/b/evaluation")
    public Response deleteCEvaluation(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        evaluationService.delete(documentId, "c");
        return new Response<>(HttpStatus.OK, request);
    }



    //////////////////////////// D ////////////////////////////////

    @PostMapping("/{documentId}/d")
    public Response insertD(HttpServletRequest request, @RequestBody D d, @PathVariable(value="documentId") String documentId){
        D dObj = dService.insert(documentId, d);
        Response<D> response = new Response<>(HttpStatus.CREATED, request);
        response.setData(dObj);
        return response;
    }

    @GetMapping("/{documentId}/d")
    public Response getD(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        D dObj = dService.get(documentId);
        Response<D> response = new Response<>(HttpStatus.OK, request);
        response.setData(dObj);
        return response;
    }

    @PatchMapping("/{documentId}/d")
    public Response updateD(HttpServletRequest request, @RequestBody D d, @PathVariable(value="documentId") String documentId){
        D dObj = dService.update(documentId, d);
        Response<D> response = new Response<>(HttpStatus.OK, request);
        response.setData(dObj);
        return response;
    }

    @DeleteMapping("/{documentId}/d")
    public Response deleteD(HttpServletRequest request, @PathVariable(value="documentId") String documentId){
        dService.delete(documentId);
        return = new Response<>(HttpStatus.OK, request);
    }

*/
}
