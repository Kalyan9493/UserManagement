package com.usermanagement.usermanagement.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "name")
    private String name;

}
