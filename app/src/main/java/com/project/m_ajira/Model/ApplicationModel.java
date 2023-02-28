package com.project.m_ajira.Model;

public class ApplicationModel {
    String jobTitle;
    String ownerEmail;
    String appName;
    String appEmail;
    String appRates;
    String appDesc;
    String appLocation;
    String appReason;
    String userUid;

    public ApplicationModel() {
    }

    public ApplicationModel(String jobTitle, String ownerEmail, String appName, String appEmail, String appRates, String appDesc, String appLocation, String appReason, String userUid) {
        this.jobTitle = jobTitle;
        this.ownerEmail = ownerEmail;
        this.appName = appName;
        this.appEmail = appEmail;
        this.appRates = appRates;
        this.appDesc = appDesc;
        this.appLocation = appLocation;
        this.appReason = appReason;
        this.userUid = userUid;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppEmail() {
        return appEmail;
    }

    public void setAppEmail(String appEmail) {
        this.appEmail = appEmail;
    }

    public String getAppRates() {
        return appRates;
    }

    public void setAppRates(String appRates) {
        this.appRates = appRates;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getAppLocation() {
        return appLocation;
    }

    public void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getAppReason() {
        return appReason;
    }

    public void setAppReason(String appReason) {
        this.appReason = appReason;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }
}
