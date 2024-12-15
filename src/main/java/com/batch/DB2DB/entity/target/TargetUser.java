package com.batch.DB2DB.entity.target;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class TargetUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String adresse;
    private int age;
}
