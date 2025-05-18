package com.ragv.hiberus.tecnicalTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity(name = "hi_employees")
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "em_first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "em_last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "em_age", nullable = false)
    private Integer age;

    @Column(name = "em_role", nullable = false, length = 100)
    private String role;

    @Column(name = "em_salary", precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(name = "em_hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "em_termination_date")
    private LocalDate terminationDate;

    @Column(name = "em_status", nullable = false)
    private Boolean status;

    // Columna para almacenar el ID del departamento
    @Column(name = "em_department_id", nullable = false, insertable = false, updatable = false)
    private Long departmentId;

    // Relación muchos a uno: muchos empleados pertenecen a un departamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_department_id", nullable = false)
    private DepartmentEntity department;
}
