package com.ragv.hiberus.tecnicalTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "hi_departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dp_id")
    private Integer id;

    @Column(name = "dp_name")
    private String name;

    @Column(name = "dp_status")
    private String status;
}
