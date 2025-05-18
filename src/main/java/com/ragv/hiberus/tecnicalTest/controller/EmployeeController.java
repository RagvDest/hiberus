package com.ragv.hiberus.tecnicalTest.controller;

import com.ragv.hiberus.model.*;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/create/{departmentId}")
    public ResponseEntity<?> createEmployee(@PathVariable Long departmentId, @Valid @RequestBody EmployeeCreateRequest request){
        try{
            EmployeeResponse res = employeeService.createEmployee(departmentId, request);
            return ResponseEntity.ok(res);
        }catch (ResourceNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }catch (IllegalArgumentException ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(400).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

    @PostMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId){
        try{
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok(new SuccessResponse().message("Employee deleted successfully"));
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<?> getHighestSalary(){
        try{
            HighestSalaryResponse res = employeeService.getEmployeeWithHighestSalary();
            return ResponseEntity.ok(res);
        }catch (ResourceNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

    @GetMapping("/lowerAge")
    public ResponseEntity<?> getLowerAge(){
        try{
            YoungestEmployeeResponse res = employeeService.getYoungestEmployee();
            return ResponseEntity.ok(res);
        }catch (ResourceNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

    @GetMapping("/countLastMonth")
    public ResponseEntity<?> countLastMounth(){
        EmployeeCountResponse res = employeeService.countEmployeesHiredLastMonth();
        return ResponseEntity.ok(res);
    }
}
