package com.project.m_ajira.Model;

public class AcceptedModel {

    String jobTitle;
    String appName;
    String appEmail;
    String appRates;
    String applicantId;

    public AcceptedModel() {
    }

    public AcceptedModel(String jobTitle, String appName, String appEmail, String appRates, String applicantId) {
        this.jobTitle = jobTitle;
        this.appName = appName;
        this.appEmail = appEmail;
        this.appRates = appRates;
        this.applicantId = applicantId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
}
