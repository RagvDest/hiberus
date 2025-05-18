package com.ragv.hiberus.tecnicalTest.service.impl;

import com.ragv.hiberus.model.DepartmentCreateRequest;
import com.ragv.hiberus.model.DepartmentResponse;
import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.mapper.DepartmentMapper;
import com.ragv.hiberus.tecnicalTest.repository.DepartmentRepository;
import com.ragv.hiberus.tecnicalTest.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        DepartmentEntity entity = departmentMapper.toEntity(request);
        DepartmentEntity savedEntity = departmentRepository.save(entity);
        return departmentMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public boolean deleteDepartment(Long departmentId) {
        DepartmentEntity entity = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con ID: " + departmentId));

        // Eliminar lógicamente (cambiar estado a inactivo)
        entity.setStatus("I");
        departmentRepository.save(entity);
        return true;
    }

    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) {
        DepartmentEntity entity = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con ID: " + departmentId));

        return departmentMapper.toResponse(entity);
    }
}
