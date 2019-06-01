package it.univaq.disim.bioinformatics.melanoq.model.composed;

public class SunlampsSunbeds {

    private int lifetimeNumberOfSession;

    private int ageAtFirstExposure;

    private int ageAtLastExposure;

    public SunlampsSunbeds(){ }

    public int getLifetimeNumberOfSession() {
        return lifetimeNumberOfSession;
    }

    public void setLifetimeNumberOfSession(int lifetimeNumberOfSession) {
        this.lifetimeNumberOfSession = lifetimeNumberOfSession;
    }

    public int getAgeAtFirstExposure() {
        return ageAtFirstExposure;
    }

    public void setAgeAtFirstExposure(int ageAtFirstExposure) {
        this.ageAtFirstExposure = ageAtFirstExposure;
    }

    public int getAgeAtLastExposure() {
        return ageAtLastExposure;
    }

    public void setAgeAtLastExposure(int ageAtLastExposure) {
        this.ageAtLastExposure = ageAtLastExposure;
    }
}
