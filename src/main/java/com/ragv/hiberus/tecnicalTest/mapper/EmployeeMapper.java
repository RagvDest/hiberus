package com.ragv.hiberus.tecnicalTest.mapper;

import com.ragv.hiberus.model.EmployeeCreateRequest;
import com.ragv.hiberus.model.EmployeeResponse;
import com.ragv.hiberus.model.HighestSalaryResponse;
import com.ragv.hiberus.model.YoungestEmployeeResponse;
import com.ragv.hiberus.tecnicalTest.entity.DepartmentEntity;
import com.ragv.hiberus.tecnicalTest.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    /**
     * Convierte un objeto EmployeeCreateRequest a EmployeeEntity
     * @param request El DTO con los datos para crear un empleado
     * @param department El departamento al que pertenece el empleado
     * @return La entidad empleado
     */
    @Mapping(target = "department", source = "department")
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "status", source = "request.status")
    @Mapping(target = "id", ignore = true)
    EmployeeEntity toEntity(EmployeeCreateRequest request, DepartmentEntity department);

    /**
     * Convierte un objeto EmployeeEntity a EmployeeResponse
     * Incluye el mapeo del departamento con DepartmentMapper
     * @param entity La entidad empleado
     * @return El DTO de respuesta
     */
    EmployeeResponse toResponse(EmployeeEntity entity);

    /**
     * Convierte una lista de entidades a una lista de DTOs de respuesta
     * @param entities Lista de entidades
     * @return Lista de DTOs de respuesta
     */
    List<EmployeeResponse> toResponseList(List<EmployeeEntity> entities);

    /**
     * Actualiza una entidad existente con los datos de un DTO
     * @param request El DTO con los datos actualizados
     * @param entity La entidad a actualizar
     */
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    void updateEntityFromRequest(EmployeeCreateRequest request, @MappingTarget EmployeeEntity entity);

    /**
     * Crea una respuesta para el empleado con el salario más alto
     * @param entity La entidad del empleado con el salario más alto
     * @return El DTO con el nombre y el salario del empleado
     */
    @Mapping(target = "employeeName", expression = "java(entity.getFirstName() + \" \" + entity.getLastName())")
    @Mapping(target = "salary", source = "salary")
    HighestSalaryResponse toHighestSalaryResponse(EmployeeEntity entity);

    /**
     * Crea una respuesta para el empleado más joven
     * @param entity La entidad del empleado más joven
     * @return El DTO con el nombre y la edad del empleado
     */
    @Mapping(target = "employeeName", expression = "java(entity.getFirstName() + \" \" + entity.getLastName())")
    @Mapping(target = "age", source = "age")
    YoungestEmployeeResponse toYoungestEmployeeResponse(EmployeeEntity entity);
}