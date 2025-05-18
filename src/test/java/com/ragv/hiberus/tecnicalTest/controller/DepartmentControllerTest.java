package com.ragv.hiberus.tecnicalTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ragv.hiberus.model.DepartmentCreateRequest;
import com.ragv.hiberus.model.DepartmentResponse;
import com.ragv.hiberus.model.SuccessResponse;
import com.ragv.hiberus.tecnicalTest.exception.ResourceNotFoundException;
import com.ragv.hiberus.tecnicalTest.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    @Test
    void createDepartment_sucess() throws Exception{
        DepartmentCreateRequest request = new DepartmentCreateRequest().name("TI");
        request.setStatus("A");

        DepartmentResponse res = new DepartmentResponse().name("TI");
        res.setStatus("A");
        when(departmentService.createDepartment(any())).thenReturn(res);

        mockMvc.perform(post("/department/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TI"));
    }

    @Test
    void deleteDepartment_sucess() throws Exception{
        mockMvc.perform(post("/department/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Department deleted successfully"));
    }

    @Test
    void deleteDepartment_deberiaRetornar404_siFallaEliminacion() throws Exception {
        doThrow(new ResourceNotFoundException("No se encontró el departamento")).when(departmentService).deleteDepartment(99L);

        mockMvc.perform(post("/department/delete/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No se encontró el departamento"));
    }

}
