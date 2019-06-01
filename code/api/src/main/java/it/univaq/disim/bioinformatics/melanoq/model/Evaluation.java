package it.univaq.disim.bioinformatics.melanoq.model;

import com.couchbase.client.java.repository.annotation.Field;

import java.util.List;

public class Evaluation {

    // It can be: All questions, Most questions, Nearly half of the questions, Very few questions
    @Field
    private String complexityOfTheQuestionnaire;

    @Field
    private List<Integer> difficultQuestions;

    public Evaluation(){ }

    public String getComplexityOfTheQuestionnaire() {
        return complexityOfTheQuestionnaire;
    }

    public void setComplexityOfTheQuestionnaire(String complexityOfTheQuestionnaire) {
        this.complexityOfTheQuestionnaire = complexityOfTheQuestionnaire;
    }

    public List<Integer> getDifficultQuestions() {
        return difficultQuestions;
    }

    public void setDifficultQuestions(List<Integer> difficultQuestions) {
        this.difficultQuestions = difficultQuestions;
    }
}
