package com.ragv.hiberus.tecnicalTest.service;

import com.ragv.hiberus.model.Employee;
import com.ragv.hiberus.model.EmployeeRequest;
import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;

public interface EmployeeService {
    Employee createEmployee(EmployeeRequest employee, Integer departmentId);
    void deleteEmployeeById(Integer employeeId);
}
