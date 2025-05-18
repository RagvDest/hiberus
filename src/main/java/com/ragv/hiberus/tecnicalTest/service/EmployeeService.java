package com.ragv.hiberus.tecnicalTest.service;

import com.ragv.hiberus.model.*;
import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    /**
     * Crea un nuevo empleado para un departamento específico
     *
     * @param departmentId ID del departamento al que pertenece el empleado
     * @param request      Datos del empleado a crear
     * @return DTO con los datos del empleado creado
     */
    EmployeeResponse createEmployee(Long departmentId, EmployeeCreateRequest request);

    /**
     * Elimina lógicamente un empleado por su ID
     *
     * @param employeeId ID del empleado a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    boolean deleteEmployee(Long employeeId);

    /**
     * Obtiene el empleado con el salario más alto
     *
     * @return DTO con el nombre del empleado y su salario
     */
    HighestSalaryResponse getEmployeeWithHighestSalary();

    /**
     * Obtiene el empleado más joven
     *
     * @return DTO con el nombre del empleado y su edad
     */
    YoungestEmployeeResponse getYoungestEmployee();

    /**
     * Cuenta los empleados que ingresaron en el último mes
     *
     * @return DTO con el contador de empleados
     */
    EmployeeCountResponse countEmployeesHiredLastMonth();
}
