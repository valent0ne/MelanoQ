package it.univaq.disim.bioinformatics.melanoq.model;


import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

@Document
public class Questionnaire {

    // id used by couchbase as meta fields
    @Id
    private String metaId = UUID.randomUUID().toString();

    // actual id field inside the json document
    @Field
    private String documentId = metaId;

    // "current" or "story"
    @Field
    private String type;

    @Field
    private A1 a1;

    public String getMetaId() {
        return metaId;
    }

    // i need to change both ids
    public void setMetaId(String metaId) {
        this.metaId = metaId;
        this.documentId = metaId;
    }

    public String getDocumentId() {
        return documentId;
    }

    // i need to change both ids
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
        this.metaId = documentId;
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


}
