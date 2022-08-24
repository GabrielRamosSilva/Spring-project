package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.controller"})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateNewDepartment() throws Exception {
        Department department = new Department("Department 1", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void shouldNotCreateNewDepartmentWhenNameIsNull() throws Exception {
        Department department = new Department("", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotCreateNewDepartmentWhenPayrollCodeIsNull() throws Exception {
        Department department = new Department("Department", "", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotCreateNewDepartmentWhenPayrollCodeAlreadyExists() throws Exception {
        Department department1 = new Department("Department 1", "10", DepartmentStatus.Ativo);
        Department department2 = new Department("Department 2", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department1)))
                .andExpect(MockMvcResultMatchers.status().is(201));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department2)))
                .andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    public void shouldListDepartments() throws Exception {
        Department department1 = new Department("Department 1", "10", DepartmentStatus.Ativo);
        Department department2 = new Department("Department 2", "20", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department1)));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/"))
                .andExpect(MockMvcResultMatchers.status().is(200));
                //.andExpect(MockMvcResultMatchers.content(objectMapper.readValue()));
    }

    @Test
    public void shouldUpdateDepartment() throws Exception {
        Department department = new Department("Department", "10", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("Department updated", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentUpdated)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotUpdateDepartmentWhenUrlIsWrong() throws Exception {
        Department department = new Department("Department", "10", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("Department updated", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentUpdated)))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void shouldNotUpdateDepartmentWhenNameIsNull() throws Exception {
        Department department = new Department("Department", "10", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentUpdated)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotUpdateDepartmentWhenPayrollCodeIsNull() throws Exception {
        Department department = new Department("Department", "10", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("Department updated", "", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentUpdated)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldDeleteDepartment() throws Exception {
        Department department = new Department("Department 1", "10", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotDeleteDepartmentThatDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }












    @Test
    public void shouldNotReturnNewDepartmentWhenPayRollCodeIsNull() throws Exception {
        URI uri = new URI("/departments");
        String json = "{\"\":\"Department 1\", \"payrollCode\":\"\", \"departmentStatus\":\"Ativo\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));
    }

}