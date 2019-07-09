package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;

import java.util.List;

public class Evaluation {

    // It can be: All questions, Most questions, Nearly half of the questions, Very few questions
    @Field
    private String complexityOfTheQuestionnaire;

    @Field
    private List<String> difficultQuestions;

    public Evaluation(){ }

    public String getComplexityOfTheQuestionnaire() {
        return complexityOfTheQuestionnaire;
    }

    public void setComplexityOfTheQuestionnaire(String complexityOfTheQuestionnaire) {
        this.complexityOfTheQuestionnaire = complexityOfTheQuestionnaire;
    }

    public List<String> getDifficultQuestions() {
        return difficultQuestions;
    }

    public void setDifficultQuestions(List<String> difficultQuestions) {
        this.difficultQuestions = difficultQuestions;
    }
}
