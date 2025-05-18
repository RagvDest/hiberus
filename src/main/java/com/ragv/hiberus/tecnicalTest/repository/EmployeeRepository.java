package com.ragv.hiberus.tecnicalTest.repository;

import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {
    /**
     * Encuentra todos los empleados activos
     * @return Lista de empleados con status = true
     */
    List<EmployeeEntity> findByStatusTrue();

    /**
     * Cuenta los empleados contratados entre dos fechas
     * @param startDate Fecha inicial
     * @param endDate Fecha final
     * @return Número de empleados contratados en ese período
     */
    int countByHireDateBetween(LocalDate startDate, LocalDate endDate);

}
