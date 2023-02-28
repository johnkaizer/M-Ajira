package com.project.m_ajira.Model;

public class LabourModel {
    String Title;
    String Description;
    String rate;
    String owner;
    String ownerEmail;
    String ownerPhone;
    String ownerId;
    String place;
    String dateCreated;

    // empty constructor to fetch details from firebase
    public LabourModel() {
    }

    //Constructor

    public LabourModel(String title, String description, String rate, String owner, String ownerEmail, String ownerPhone, String ownerId, String place, String dateCreated) {
        Title = title;
        Description = description;
        this.rate = rate;
        this.owner = owner;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.ownerId = ownerId;
        this.place = place;
        this.dateCreated = dateCreated;
    }
    //getters and setters methods


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
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
