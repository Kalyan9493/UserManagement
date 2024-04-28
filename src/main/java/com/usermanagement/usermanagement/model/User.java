package com.usermanagement.usermanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "mobile_number")
    private Integer mobileNumber;
    @Column(name = "password")
    private String password;
}
