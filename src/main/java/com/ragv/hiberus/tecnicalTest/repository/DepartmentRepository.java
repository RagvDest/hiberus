package com.ragv.hiberus.tecnicalTest.repository;

import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity,Long> {
}
