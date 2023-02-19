package com.project.m_ajira.Model;

public class ProfileModel {
    String ImageUrl;
    String fullName;
    String Dob;
    String phone;
    String email;
    String home;
    String currentHome;
    String gender;
    String HighEducation;
    String speciality;
    String rates;
    String userId;

    public ProfileModel() {
    }


    public ProfileModel(String imageUrl, String fullName, String dob, String phone, String email, String home, String currentHome, String gender, String highEducation, String speciality, String rates, String userId) {
        ImageUrl = imageUrl;
        this.fullName = fullName;
        Dob = dob;
        this.phone = phone;
        this.email = email;
        this.home = home;
        this.currentHome = currentHome;
        this.gender = gender;
        HighEducation = highEducation;
        this.speciality = speciality;
        this.rates = rates;
        this.userId = userId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCurrentHome() {
        return currentHome;
    }

    public void setCurrentHome(String currentHome) {
        this.currentHome = currentHome;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHighEducation() {
        return HighEducation;
    }

    public void setHighEducation(String highEducation) {
        HighEducation = highEducation;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }
}
