package com.talha.physicswallahassignment.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;




public class TeacherModel {
    public TeacherModel() {
    }

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("profileImage")
    String profileImage;
    @SerializedName("qualification")
    List<String> qualification;
    @SerializedName("subjects")
    List<String> subjects;
    public TeacherModel(int id, String name, String profileImage, List<String> qualification, List<String> subjects) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.qualification = qualification;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public List<String> getQualification() {
        return qualification;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
