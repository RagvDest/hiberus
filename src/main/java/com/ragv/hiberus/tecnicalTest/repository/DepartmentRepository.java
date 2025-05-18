package com.ragv.hiberus.tecnicalTest.repository;

import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity,Long> {
    List<DepartmentEntity> findByStatus(String status);
}
