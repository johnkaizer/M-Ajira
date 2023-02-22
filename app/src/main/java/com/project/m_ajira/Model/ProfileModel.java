package com.project.m_ajira.Model;

public class ProfileModel {
    String ImageUrl;
    String UserName;
    String UserDob;
    String UserPhone;
    String UserEmail;
    String UserHome;
    String CurrentHome;
    String Gender;
    String Education;
    String Skill;
    String UserRates;
    String userId;

    public ProfileModel() {
    }

    public ProfileModel(String imageUrl, String userName, String userDob, String userPhone, String userEmail, String userHome, String currentHome, String gender, String education, String skill, String userRates, String userId) {
        ImageUrl = imageUrl;
        UserName = userName;
        UserDob = userDob;
        UserPhone = userPhone;
        UserEmail = userEmail;
        UserHome = userHome;
        CurrentHome = currentHome;
        Gender = gender;
        Education = education;
        Skill = skill;
        UserRates = userRates;
        this.userId = userId;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserDob() {
        return UserDob;
    }

    public void setUserDob(String userDob) {
        UserDob = userDob;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserHome() {
        return UserHome;
    }

    public void setUserHome(String userHome) {
        UserHome = userHome;
    }

    public String getCurrentHome() {
        return CurrentHome;
    }

    public void setCurrentHome(String currentHome) {
        CurrentHome = currentHome;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getUserRates() {
        return UserRates;
    }

    public void setUserRates(String userRates) {
        UserRates = userRates;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
