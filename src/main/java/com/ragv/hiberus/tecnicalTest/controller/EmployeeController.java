package com.ragv.hiberus.tecnicalTest.controller;

import com.ragv.hiberus.model.Employee;
import com.ragv.hiberus.model.EmployeeRequest;
import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;
import com.ragv.hiberus.tecnicalTest.service.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/employee")
@NoArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping("create/{deparmentId}")
    public Employee createEmployee(@PathVariable Integer departmentId, @RequestBody EmployeeRequest employee){
        return employeeService.createEmployee(employee, departmentId);
    }

}
