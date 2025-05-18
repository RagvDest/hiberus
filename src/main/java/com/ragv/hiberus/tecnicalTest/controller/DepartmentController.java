package com.ragv.hiberus.tecnicalTest.controller;

import com.ragv.hiberus.model.DepartmentCreateRequest;
import com.ragv.hiberus.model.DepartmentResponse;
import com.ragv.hiberus.model.ErrorResponse;
import com.ragv.hiberus.model.SuccessResponse;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.service.DepartmentService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentCreateRequest request){
        DepartmentResponse res = departmentService.createDepartment(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/delete/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId){
        try{
            departmentService.deleteDepartment(departmentId);
            return ResponseEntity.ok(new SuccessResponse().message("Department deleted successfully"));
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveDepartments() {
        try{
            List<DepartmentResponse> activeDepartments = departmentService.getActiveDepartments();
            return ResponseEntity.ok(activeDepartments);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.status(404).body(new ErrorResponse().error(ex.getMessage()));
        }
    }

}
