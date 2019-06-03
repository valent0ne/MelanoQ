package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Smoking;
import it.univaq.disim.bioinformatics.melanoq.model.nested.Vitamin;

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

    public Smoking getSmoking() {
        return smoking;
    }

    public void setSmoking(Smoking smoking) {
        this.smoking = smoking;
    }

    public String getIntakeOfVitaminesDuringLastYears() {
        return intakeOfVitaminesDuringLastYears;
    }

    public void setIntakeOfVitaminesDuringLastYears(String intakeOfVitaminesDuringLastYears) {
        this.intakeOfVitaminesDuringLastYears = intakeOfVitaminesDuringLastYears;
    }

    public List<Vitamin> getVitamins() {
        return Vitamins;
    }

    public void setVitamins(List<Vitamin> vitamins) {
        Vitamins = vitamins;
    }
}
