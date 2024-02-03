//package com.bkalika.entity;
//
//import jakarta.persistence.DiscriminatorValue;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(schema = "youtube")
////@DiscriminatorValue("manager")
//public class Manager extends User {
//
//    private String project;
//
//    @Builder
//    public Manager(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, String project) {
//        super(id, username, personalInfo, role, company, profile, userChats);
//        this.project = project;
//    }
//}
