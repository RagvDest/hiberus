package com.ragv.hiberus.tecnicalTest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

    @Data
    @Entity(name = "hi_employees")
    public class EmployeeEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "em_id")
        private Integer id;

        @Column(name = "em_firstname")
        private String firstName;

        @Column(name = "em_secondname")
        private String secondName;

        @Column(name = "em_age")
        private Integer age;

        @Column(name = "em_role")
        private String role;

        @Column(name = "em_ini_date")
        private LocalDate iniDate;

        @Column(name = "em_exit_date")
        private LocalTime exitDate;

        @Column(name = "em_status")
        private String status;

    }
