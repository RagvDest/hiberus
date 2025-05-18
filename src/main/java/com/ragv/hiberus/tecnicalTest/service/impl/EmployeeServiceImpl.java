package com.ragv.hiberus.tecnicalTest.service.impl;

import com.ragv.hiberus.model.*;
import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.mapper.EmployeeMapper;
import com.ragv.hiberus.tecnicalTest.repository.DepartmentRepository;
import com.ragv.hiberus.tecnicalTest.repository.EmployeeRepository;
import com.ragv.hiberus.tecnicalTest.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponse createEmployee(Long departmentId, EmployeeCreateRequest request) {
        // Verificar que el departamento existe
        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento no encontrado con ID: " + departmentId));

        // Verificar que el departamento está activo
        if (department.getStatus().equals("I")) {
            throw new IllegalArgumentException("No se puede crear un empleado en un departamento inactivo");
        }

        // Crear y guardar el empleado
        EmployeeEntity entity = employeeMapper.toEntity(request, department);
        EmployeeEntity savedEntity = employeeRepository.save(entity);
        return employeeMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public boolean deleteEmployee(Long employeeId) {
        EmployeeEntity entity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con ID: " + employeeId));

        // Eliminar lógicamente (cambiar estado a inactivo)
        entity.setStatus("I");
        employeeRepository.save(entity);
        return true;
    }


    @Override
    public HighestSalaryResponse getEmployeeWithHighestSalary() {
        // Obtener todos los empleados activos
        List<EmployeeEntity> activeEmployees = employeeRepository.findByStatusActive();

        if (activeEmployees.isEmpty()) {
            throw new ResourceNotFoundException("No hay empleados activos registrados");
        }

        // Encontrar el empleado con el salario más alto
        EmployeeEntity highestPaidEmployee = activeEmployees.stream()
                .filter(employee -> employee.getSalary() != null) // Filtrar empleados con salario nulo
                .max(Comparator.comparing(EmployeeEntity::getSalary))
                .orElseThrow(() -> new ResourceNotFoundException("No hay empleados con salario registrado"));

        return employeeMapper.toHighestSalaryResponse(highestPaidEmployee);
    }

    @Override
    public YoungestEmployeeResponse getYoungestEmployee() {
        // Obtener todos los empleados activos
        List<EmployeeEntity> activeEmployees = employeeRepository.findByStatusActive();

        if (activeEmployees.isEmpty()) {
            throw new ResourceNotFoundException("No hay empleados activos registrados");
        }

        // Encontrar el empleado más joven
        EmployeeEntity youngestEmployee = activeEmployees.stream()
                .min(Comparator.comparing(EmployeeEntity::getAge))
                .orElseThrow(() -> new ResourceNotFoundException("Error al determinar el empleado más joven"));

        return employeeMapper.toYoungestEmployeeResponse(youngestEmployee);
    }

    @Override
    public EmployeeCountResponse countEmployeesHiredLastMonth() {
        // Calcular fechas para el último mes
        LocalDate today = LocalDate.now();
        LocalDate oneMonthAgo = today.minusMonths(1);

        // Contar empleados contratados en el último mes
        int count = employeeRepository.countByHireDateBetween(oneMonthAgo, today);

        return new EmployeeCountResponse(count);
    }
}
