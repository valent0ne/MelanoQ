package it.univaq.disim.bioinformatics.melanoq.model;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.composed.Smoking;
import it.univaq.disim.bioinformatics.melanoq.model.composed.Vitamin;

import java.util.List;

public class B3 {

    @Field
    private Smoking smoking;

    //It can be: Never,	1-3 days/month, 1-3 days/week, 4-6 days/week, Everyday
    // TODO: it can be optional
    @Field
    private String intakeOfVitaminesDuringLastYears;

    // The following attribute incorporate questions 17 and 18 of session B
    // TODO: it can be optional
    @Field
    private List<Vitamin> Vitamins;




}
