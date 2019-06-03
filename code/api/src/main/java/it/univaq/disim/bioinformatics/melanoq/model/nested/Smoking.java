package it.univaq.disim.bioinformatics.melanoq.model.nested;

public class Smoking {

    //It can be never, former smoker(quit one year before diagnosis or earlier), Current smoker
    private String howOften;

    //TODO: if it's never the following attributes must be remained empty otherwise it will be mandatory to fill them
    private int ageWhenStartedSmoking;

    private  String howLongHaveYouBeenSmoking;

    //It can be: one pack/day or more, up to half pack/day, only occasionally
    private String howMuchTipicallySmoke;

    public Smoking(){ }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public int getAgeWhenStartedSmoking() {
        return ageWhenStartedSmoking;
    }

    public void setAgeWhenStartedSmoking(int ageWhenStartedSmoking) {
        this.ageWhenStartedSmoking = ageWhenStartedSmoking;
    }

    public String getHowLongHaveYouBeenSmoking() {
        return howLongHaveYouBeenSmoking;
    }

    public void setHowLongHaveYouBeenSmoking(String howLongHaveYouBeenSmoking) {
        this.howLongHaveYouBeenSmoking = howLongHaveYouBeenSmoking;
    }

    public String getHowMuchTipicallySmoke() {
        return howMuchTipicallySmoke;
    }

    public void setHowMuchTipicallySmoke(String howMuchTipicallySmoke) {
        this.howMuchTipicallySmoke = howMuchTipicallySmoke;
    }
}
