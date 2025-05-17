package com.ragv.hiberus.tecnicalTest.repository;

import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {
}
