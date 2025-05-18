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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

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

    @Test
    public void getActiveDepartments_sucess() throws Exception {
        DepartmentResponse dept1 = new DepartmentResponse(1L, "IT", "A");
        DepartmentResponse dept2 = new DepartmentResponse(2L, "HR", "A");
        List<DepartmentResponse> activeDepartments = Arrays.asList(dept1, dept2);

        when(departmentService.getActiveDepartments()).thenReturn(activeDepartments);

        mockMvc.perform(get("/department/active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("IT"))
                .andExpect(jsonPath("$[0].status").value("A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("HR"))
                .andExpect(jsonPath("$[1].status").value("A"));
    }

    @Test
    public void getActiveDepartments_deberiaRetornar404_siNoHayDepartamentosActivos() throws Exception {
        when(departmentService.getActiveDepartments())
                .thenThrow(new ResourceNotFoundException("No active departments found"));

        mockMvc.perform(get("/department/active"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No active departments found"));
    }
}
