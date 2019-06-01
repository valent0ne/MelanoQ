package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class Vitamin {
    // It can be: β-carotene, Vitamin A, Vitamin C, Vitamin E, Vitamin D, Multivitamins
    private String name;

    // It can be: Never,	1-3 days/month, 1-3 days/week, 4-6 days/week, Everyday
    private String howOften;

    // It ca be: <1 yr, 1-4 yrs, 5-9 yrs, ≥10 yrs
    private String howLong;

    public Vitamin() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public String getHowLong() {
        return howLong;
    }

    public void setHowLong(String howLong) {
        this.howLong = howLong;
    }
}
