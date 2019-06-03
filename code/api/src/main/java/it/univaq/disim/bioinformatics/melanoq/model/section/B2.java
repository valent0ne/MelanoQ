package it.univaq.disim.bioinformatics.melanoq.model.section;

import com.couchbase.client.java.repository.annotation.Field;
import it.univaq.disim.bioinformatics.melanoq.model.nested.*;

import java.util.List;

public class B2 {

    //TODO: check if it is an outdoor occupation with at least 4 hours/day spent outdoor in the sun
    @Field
    private OccupationalSunExposure occupationalSunExposure;

    //TODO: check if it is an outdoor activity with at least 4 hours/day spent outdoor in the sun
    @Field
    private RecreationalSunExposure recreationalSunExposure;

    @Field
    private List<IntermittentSunExposure> intermittentSunExposure;

    //It should be a date
    @Field
    private String mostRecentIntermittentSunExposure;

    @Field
    private List<SevereSunBurns> severeSunBurns;

    @Field
    private List<SunscreenUse> sunscreenUses;

    // It can be Never, <50% of time exposure, >50% of time exposure, Always, Not known
    //TODO: it's an optional answer
    @Field
    private String sunProtectionOtherThanSunscreenUseHat;

    // It can be Never, <50% of time exposure, >50% of time exposure, Always, Not known
    //TODO: it's an optional answer
    @Field
    private String sunProtectionOtherThanSunscreenUseClothing;

    // It can be Rarely, Sometimes, Always, Not known
    //TODO: it's an optional answer
    @Field
    private String seekTheShadeDuringUVRHours;

    @Field
    private SunlampsSunbeds sunlampsSunbeds;

    // It can be ever or never
    //TODO: it's an optional answer
    @Field
    private String phototherapyUVBPUVA;

    public B2(){ }

    public OccupationalSunExposure getOccupationalSunExposure() {
        return occupationalSunExposure;
    }

    public void setOccupationalSunExposure(OccupationalSunExposure occupationalSunExposure) {
        this.occupationalSunExposure = occupationalSunExposure;
    }

    public RecreationalSunExposure getRecreationalSunExposure() {
        return recreationalSunExposure;
    }

    public void setRecreationalSunExposure(RecreationalSunExposure recreationalSunExposure) {
        this.recreationalSunExposure = recreationalSunExposure;
    }

    public List<IntermittentSunExposure> getIntermittentSunExposure() {
        return intermittentSunExposure;
    }

    public void setIntermittentSunExposure(List<IntermittentSunExposure> intermittentSunExposure) {
        this.intermittentSunExposure = intermittentSunExposure;
    }

    public String getMostRecentIntermittentSunExposure() {
        return mostRecentIntermittentSunExposure;
    }

    public void setMostRecentIntermittentSunExposure(String mostRecentIntermittentSunExposure) {
        this.mostRecentIntermittentSunExposure = mostRecentIntermittentSunExposure;
    }

    public List<SevereSunBurns> getSevereSunBurns() {
        return severeSunBurns;
    }

    public void setSevereSunBurns(List<SevereSunBurns> severeSunBurns) {
        this.severeSunBurns = severeSunBurns;
    }

    public List<SunscreenUse> getSunscreenUses() {
        return sunscreenUses;
    }

    public void setSunscreenUses(List<SunscreenUse> sunscreenUses) {
        this.sunscreenUses = sunscreenUses;
    }

    public String getSunProtectionOtherThanSunscreenUseHat() {
        return sunProtectionOtherThanSunscreenUseHat;
    }

    public void setSunProtectionOtherThanSunscreenUseHat(String sunProtectionOtherThanSunscreenUseHat) {
        this.sunProtectionOtherThanSunscreenUseHat = sunProtectionOtherThanSunscreenUseHat;
    }

    public String getSunProtectionOtherThanSunscreenUseClothing() {
        return sunProtectionOtherThanSunscreenUseClothing;
    }

    public void setSunProtectionOtherThanSunscreenUseClothing(String sunProtectionOtherThanSunscreenUseClothing) {
        this.sunProtectionOtherThanSunscreenUseClothing = sunProtectionOtherThanSunscreenUseClothing;
    }

    public String getSeekTheShadeDuringUVRHours() {
        return seekTheShadeDuringUVRHours;
    }

    public void setSeekTheShadeDuringUVRHours(String seekTheShadeDuringUVRHours) {
        this.seekTheShadeDuringUVRHours = seekTheShadeDuringUVRHours;
    }

    public SunlampsSunbeds getSunlampsSunbeds() {
        return sunlampsSunbeds;
    }

    public void setSunlampsSunbeds(SunlampsSunbeds sunlampsSunbeds) {
        this.sunlampsSunbeds = sunlampsSunbeds;
    }

    public String getPhototherapyUVBPUVA() {
        return phototherapyUVBPUVA;
    }

    public void setPhototherapyUVBPUVA(String phototherapyUVBPUVA) {
        this.phototherapyUVBPUVA = phototherapyUVBPUVA;
    }
}
