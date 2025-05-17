package com.ragv.hiberus.tecnicalTest.service;

import com.ragv.hiberus.model.Department;
import com.ragv.hiberus.model.DepartmentRequest;
import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;

public interface DepartmentService {
    Department createDepartment(DepartmentRequest department);
    void deleteDepartmentById(Integer departmentId);
}
