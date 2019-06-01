package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class OccupationalSunExposure {

    private String occupation;

    private String hoursPerDay;

    private String daysPerMonth;

    private String monthsPerYear;

    private String years;

    public OccupationalSunExposure(){ }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(String hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public String getDaysPerMonth() {
        return daysPerMonth;
    }

    public void setDaysPerMonth(String daysPerMonth) {
        this.daysPerMonth = daysPerMonth;
    }

    public String getMonthsPerYear() {
        return monthsPerYear;
    }

    public void setMonthsPerYear(String monthsPerYear) {
        this.monthsPerYear = monthsPerYear;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
