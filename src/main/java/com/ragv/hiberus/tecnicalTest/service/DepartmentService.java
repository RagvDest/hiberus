package com.ragv.hiberus.tecnicalTest.service;

import com.ragv.hiberus.model.DepartmentCreateRequest;
import com.ragv.hiberus.model.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    /**
     * Crea un nuevo departamento
     * @param request Datos del departamento a crear
     * @return DTO con los datos del departamento creado
     */
    DepartmentResponse createDepartment(DepartmentCreateRequest request);

    /**
     * Elimina lógicamente un departamento por su ID
     * @param departmentId ID del departamento a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    boolean deleteDepartment(Long departmentId);

    /**
     * Obtiene un departamento por su ID
     * @param departmentId ID del departamento
     * @return DTO con los datos del departamento encontrado
     */
    DepartmentResponse getDepartmentById(Long departmentId);
}