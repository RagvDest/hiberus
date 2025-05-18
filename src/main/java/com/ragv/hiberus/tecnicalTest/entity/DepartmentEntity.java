package com.ragv.hiberus.tecnicalTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "hi_departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dp_name", nullable = false, length = 100)
    private String name;

    @Column(name = "dp_status", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeEntity> employees;
}
