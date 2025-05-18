package com.ragv.hiberus.tecnicalTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.ragv.hiberus.model.*;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    // Test: Crear empleado exitosamente
    @Test
    void createEmployee_deberiaRetornar200_siEsValido() throws Exception {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        request.setFirstName("Juan");
        request.setLastName("Perez");
        request.setAge(30);
        request.setStatus("A");
        request.setRole("DEV");
        request.setSalary(new BigDecimal("5000.0"));

        DepartmentResponse department = new DepartmentResponse().name("TI");
        department.setStatus("A");

        EmployeeResponse response = new EmployeeResponse(1L, "Juan", "Perez", 30, "DEV", LocalDate.now(),"A",department);

        when(employeeService.createEmployee(eq(1L), any())).thenReturn(response);

        mockMvc.perform(post("/employee/create/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.department.name").value("TI"));
    }

    // Test: Crear empleado con departamento no encontrado
    @Test
    void createEmployee_deberiaRetornar404_siDepartamentoNoExiste() throws Exception {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        request.setFirstName("Juan");
        request.setLastName("Perez");
        request.setAge(30);
        request.setStatus("A");
        request.setRole("DEV");
        request.setSalary(new BigDecimal("5000.0"));

        when(employeeService.createEmployee(eq(99L), any()))
                .thenThrow(new ResourceNotFoundException("Departamento no encontrado con ID: 99"));

        mockMvc.perform(post("/employee/create/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Departamento no encontrado con ID: 99"));
    }

    // Test: Crear empleado con departamento estado inactivo
    @Test
    void createEmployee_deberiaRetornar404_siDepartamentoInactivo() throws Exception {
        EmployeeCreateRequest request = new EmployeeCreateRequest();
        request.setFirstName("Juan");
        request.setLastName("Perez");
        request.setAge(30);
        request.setStatus("A");
        request.setRole("DEV");
        request.setSalary(new BigDecimal("5000.0"));

        when(employeeService.createEmployee(eq(99L), any()))
                .thenThrow(new IllegalArgumentException("No se puede crear un empleado en un departamento inactivo"));

        mockMvc.perform(post("/employee/create/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("No se puede crear un empleado en un departamento inactivo"));
    }

    // Test: Eliminar empleado exitosamente
    @Test
    void deleteEmployee_deberiaRetornar200_siSeElimina() throws Exception {
        mockMvc.perform(post("/employee/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee deleted successfully"));
    }

    // Test: Eliminar empleado no existente
    @Test
    void deleteEmployee_deberiaRetornar404_siNoExiste() throws Exception {
        doThrow(new ResourceNotFoundException("Employee not found"))
                .when(employeeService).deleteEmployee(99L);

        mockMvc.perform(post("/employee/delete/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Employee not found"));
    }

    // Test: Obtener empleado con salario más alto
    @Test
    void getHighestSalary_deberiaRetornar200() throws Exception {
        HighestSalaryResponse response = new HighestSalaryResponse("Ana", new BigDecimal("9000.0"));
        when(employeeService.getEmployeeWithHighestSalary()).thenReturn(response);

        mockMvc.perform(get("/employee/highestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(9000.0));
    }

    // Test: Obtener empleado con salario más alto - No hay empleados activos registrados
    @Test
    void getHighestSalary_deberiaRetornar400() throws Exception {
        HighestSalaryResponse response = new HighestSalaryResponse("Ana", new BigDecimal("9000.0"));
        when(employeeService.getEmployeeWithHighestSalary()).thenThrow(new ResourceNotFoundException("No hay empleados activos registrados"));

        mockMvc.perform(get("/employee/highestSalary"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("No hay empleados activos registrados"));
    }


    // Test: Obtener empleado más joven
    @Test
    void getLowerAge_deberiaRetornar200() throws Exception {
        YoungestEmployeeResponse response = new YoungestEmployeeResponse("Pedro", 20);
        when(employeeService.getYoungestEmployee()).thenReturn(response);

        mockMvc.perform(get("/employee/lowerAge"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(20));
    }

    // Test: Obtener empleado más joven - No usuarios activos
    @Test
    void getLowerAge_deberiaRetornar400() throws Exception {
        YoungestEmployeeResponse response = new YoungestEmployeeResponse("Pedro", 20);
        when(employeeService.getYoungestEmployee()).thenThrow(new ResourceNotFoundException("No hay empleados activos registrados"));

        mockMvc.perform(get("/employee/lowerAge"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("No hay empleados activos registrados"));
    }

    // Test: Contar empleados contratados el último mes
    @Test
    void countLastMonth_deberiaRetornar200() throws Exception {
        EmployeeCountResponse response = new EmployeeCountResponse(5);
        when(employeeService.countEmployeesHiredLastMonth()).thenReturn(response);

        mockMvc.perform(get("/employee/countLastMonth"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(5));
    }
}
