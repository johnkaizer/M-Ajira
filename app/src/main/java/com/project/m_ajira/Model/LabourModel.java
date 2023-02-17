package com.project.m_ajira.Model;

public class LabourModel {
    String icon;
    String description;
    String briefDesc;
    String rate;
    String owner;
    String ownerPhone;
    String ownerId;
    String place;
    String dateCreated;

    // empty constructor to fetch details from firebase
    public LabourModel() {
    }

    //Constructor
    public LabourModel(String icon, String description, String briefDesc, String rate, String owner, String ownerPhone, String ownerId, String place, String dateCreated) {
        this.icon = icon;
        this.description = description;
        this.briefDesc = briefDesc;
        this.rate = rate;
        this.owner = owner;
        this.ownerPhone = ownerPhone;
        this.ownerId = ownerId;
        this.place = place;
        this.dateCreated = dateCreated;
    }
    //getters and setters methods
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
