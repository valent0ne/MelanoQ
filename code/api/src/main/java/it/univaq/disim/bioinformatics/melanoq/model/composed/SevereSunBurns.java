package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class SevereSunBurns {

    // It can be: at age <18 yrs, at age ≥18 yrs, at site of melanoma, in the last 5 yrs
    private String period;

    // It can be: yes, no, not known
    private String precence;

    // Number of sun burns
    private int number;


    public SevereSunBurns(){ }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPrecence() {
        return precence;
    }

    public void setPrecence(String precence) {
        this.precence = precence;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
