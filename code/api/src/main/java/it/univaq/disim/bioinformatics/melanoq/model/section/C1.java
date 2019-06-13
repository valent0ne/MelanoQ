package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.*;

import java.util.List;

public class C1 {

    @Field
    private SolarLentigines solarLentigines;

    @Field
    private List<NeviCount> neviCountGreatherThan2mm;

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
    private ActinicKeratoses actinicKeratoses;

    @Field
    private List<CurrentNonMelanomaSkinCancer> currentNonMelanomaSkinCancers;

    public C1(){ }

    public SolarLentigines getSolarLentigines() {
        return solarLentigines;
    }

    public void setSolarLentigines(SolarLentigines solarLentigines) {
        this.solarLentigines = solarLentigines;
    }

    public List<NeviCount> getNeviCountGreatherThan2mm() {
        return neviCountGreatherThan2mm;
    }

    public void setNeviCountGreatherThan2mm(List<NeviCount> neviCountGreatherThan2mm) {
        this.neviCountGreatherThan2mm = neviCountGreatherThan2mm;
    }

    public String getNeviCountLessThan2mm() {
        return neviCountLessThan2mm;
    }

    public void setNeviCountLessThan2mm(String neviCountLessThan2mm) {
        this.neviCountLessThan2mm = neviCountLessThan2mm;
    }

    public ClinicallyAtypicalNevi getClinicallyAtypicalNevi() {
        return clinicallyAtypicalNevi;
    }

    public void setClinicallyAtypicalNevi(ClinicallyAtypicalNevi clinicallyAtypicalNevi) {
        this.clinicallyAtypicalNevi = clinicallyAtypicalNevi;
    }

    public CongenitalNevi getCongenitalNevi() {
        return congenitalNevi;
    }

    public void setCongenitalNevi(CongenitalNevi congenitalNevi) {
        this.congenitalNevi = congenitalNevi;
    }

    public int getBlueNevi() {
        return blueNevi;
    }

    public void setBlueNevi(int blueNevi) {
        this.blueNevi = blueNevi;
    }

    public ActinicKeratoses getActinicKeratoses() {
        return actinicKeratoses;
    }

    public void setActinicKeratoses(ActinicKeratoses actinicKeratoses) {
        this.actinicKeratoses = actinicKeratoses;
    }

    public List<CurrentNonMelanomaSkinCancer> getList<CurrentNonMelanomaSkinCancer>s() {
        return currentNonMelanomaSkinCancers;
    }

    public void setList<CurrentNonMelanomaSkinCancer>s(List<CurrentNonMelanomaSkinCancer> currentNonMelanomaSkinCancers) {
        this.currentNonMelanomaSkinCancers = currentNonMelanomaSkinCancers;
    }
}
