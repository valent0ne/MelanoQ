package it.univaq.disim.bioinformatics.melanoq.model;


import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import it.univaq.disim.bioinformatics.melanoq.model.section.*;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class Questionnaire {

    // id used by couchbase as meta fields
    @Id
    private String metaId = UUID.randomUUID().toString();

    // actual id field inside the json document
    @Field
    //@NotNull
    private String documentId;

    // "current" or "story"
    @Field
    //@NotNull
    private String type;

    @Field
    private A1 a1;

    @Field
    private A2 a2;

    @Field
    private B1 b1;

    @Field
    private B2 b2;

    @Field
    private B3 b3;

    @Field
    private Evaluation bEvaluation;

    @Field
    private C1 c1;

    @Field
    private C2 c2;

    @Field
    private C3 c3;

    @Field
    private Evaluation cEvaluation;

    @Field
    private List<D> dList;


    public Questionnaire(){

    }

    public String getMetaId() {
        return metaId;
    }


    public void setMetaId(String metaId) {
        this.metaId = metaId;
    }

    public String getDocumentId() {
        return documentId;
    }


    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public A1 getA1() {
        return a1;
    }

    public void setA1(A1 a1) {
        this.a1 = a1;
    }

    public A2 getA2() {
        return a2;
    }

    public void setA2(A2 a2) {
        this.a2 = a2;
    }

    public B1 getB1() {
        return b1;
    }

    public void setB1(B1 b1) {
        this.b1 = b1;
    }

    public B2 getB2() {
        return b2;
    }

    public void setB2(B2 b2) {
        this.b2 = b2;
    }

    public B3 getB3() {
        return b3;
    }

    public void setB3(B3 b3) {
        this.b3 = b3;
    }

    public Evaluation getbEvaluation() {
        return bEvaluation;
    }

    public void setbEvaluation(Evaluation bEvaluation) {
        this.bEvaluation = bEvaluation;
    }

    public C1 getC1() {
        return c1;
    }

    public void setC1(C1 c1) {
        this.c1 = c1;
    }

    public C2 getC2() {
        return c2;
    }

    public void setC2(C2 c2) {
        this.c2 = c2;
    }

    public C3 getC3() {
        return c3;
    }

    public void setC3(C3 c3) {
        this.c3 = c3;
    }

    public Evaluation getcEvaluation() {
        return cEvaluation;
    }

    public void setcEvaluation(Evaluation cEvaluation) {
        this.cEvaluation = cEvaluation;
    }

    public List<D> getdList() {
        return dList;
    }

    public void setdList(List<D> dList) {
        this.dList = dList;
    }
}
