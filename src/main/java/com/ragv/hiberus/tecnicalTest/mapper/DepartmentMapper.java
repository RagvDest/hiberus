package com.ragv.hiberus.tecnicalTest.mapper;

import com.ragv.hiberus.model.DepartmentCreateRequest;
import com.ragv.hiberus.model.DepartmentResponse;
import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    /**
     * Convierte un objeto DepartmentCreateRequest a DepartmentEntity
     * @param request El DTO con los datos para crear un departamento
     * @return La entidad departamento
     */
    DepartmentEntity toEntity(DepartmentCreateRequest request);

    /**
     * Convierte un objeto DepartmentEntity a DepartmentResponse
     * @param entity La entidad departamento
     * @return El DTO de respuesta
     */
    DepartmentResponse toResponse(DepartmentEntity entity);

}
