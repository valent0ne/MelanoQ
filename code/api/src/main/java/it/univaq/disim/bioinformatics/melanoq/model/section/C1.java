package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.*;

import java.util.List;

public class C1 {

    @Field
    private SolarLentigines solarLentigines;

    @Field
    private List<NeviCount> neviCountGratherThan2mm;

    // It can be: 0, 1-50, 51-100, 101-200, >200
    //TODO: it is an optional answer
    @Field
    private String neviCountLessThan2mm;

    @Field
    private ClinicallyAtypicalNevi clinicallyAtypicalNevi;

    @Field
    private CongenitalNevi congenitalNevi;

    // if the number is grather than 0 then it means also yes, otherwise no
    //TODO: it is an optional answer
    @Field
    private int blueNevi;

    @Field
    private List<ActinicKeratoses> actinicKeratoses;

    @Field
    private List<CurrentNonMelanomaSkinCancer> currentNonMelanomaSkinCancers;

    public C1(){ }

    public SolarLentigines getSolarLentigines() {
        return solarLentigines;
    }

    public List<NeviCount> getNeviCountGratherThan2mm() {
        return neviCountGratherThan2mm;
    }

    public String getNeviCountLessThan2mm() {
        return neviCountLessThan2mm;
    }

    public ClinicallyAtypicalNevi getClinicallyAtypicalNevi() {
        return clinicallyAtypicalNevi;
    }

    public CongenitalNevi getCongenitalNevi() {
        return congenitalNevi;
    }

    public int getBlueNevi() {
        return blueNevi;
    }

    public List<ActinicKeratoses> getActinicKeratoses() {
        return actinicKeratoses;
    }

    public List<CurrentNonMelanomaSkinCancer> getCurrentNonMelanomaSkinCancers() {
        return currentNonMelanomaSkinCancers;
    }
}
