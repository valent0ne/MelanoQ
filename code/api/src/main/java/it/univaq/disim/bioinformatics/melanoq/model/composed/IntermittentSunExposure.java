package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class IntermittentSunExposure {

    // It can be childhood, adolescence, adulthood or 10 years before melanoma diagnosis (for cases) or last 10 years (for controls)
    private String agePeriod;

    private String weeksOfVacation;

    private String hoursSpentBetween11AMAnd4PM;

    public IntermittentSunExposure(){ }

    public String getAgePeriod() {
        return agePeriod;
    }

    public void setAgePeriod(String agePeriod) {
        this.agePeriod = agePeriod;
    }

    public String getWeeksOfVacation() {
        return weeksOfVacation;
    }

    public void setWeeksOfVacation(String weeksOfVacation) {
        this.weeksOfVacation = weeksOfVacation;
    }

    public String getHoursSpentBetween11AMAnd4PM() {
        return hoursSpentBetween11AMAnd4PM;
    }

    public void setHoursSpentBetween11AMAnd4PM(String hoursSpentBetween11AMAnd4PM) {
        this.hoursSpentBetween11AMAnd4PM = hoursSpentBetween11AMAnd4PM;
    }
}
