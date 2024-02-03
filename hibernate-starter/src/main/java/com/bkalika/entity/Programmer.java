//package com.bkalika.entity;
//
//import jakarta.persistence.*;
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
////@DiscriminatorValue("programmer")
//public class Programmer extends User {
//
//    @Enumerated(EnumType.STRING)
//    private Language language;
//
//    @Builder
//    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, Language language) {
//        super(id, username, personalInfo, role, company, profile, userChats);
//        this.language = language;
//    }
//}
