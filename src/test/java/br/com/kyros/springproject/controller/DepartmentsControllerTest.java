package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import br.com.kyros.springproject.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.controller"})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DepartmentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

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
        Department department = new Department("", "11", DepartmentStatus.Ativo);
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
        Department department1 = new Department("Department 1", "12", DepartmentStatus.Ativo);
        Department department2 = new Department("Department 2", "12", DepartmentStatus.Ativo);
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
        Department department1 = new Department("Department 1", "13", DepartmentStatus.Ativo);
        Department department2 = new Department("Department 2", "14", DepartmentStatus.Ativo);
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
        Department department = new Department("Department", "15", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("Department updated", "15", DepartmentStatus.Inativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.put("/departments/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentUpdated)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotUpdateDepartmentWhenUrlIsWrong() throws Exception {
        Department department = new Department("Department", "16", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("Department updated", "16", DepartmentStatus.Ativo);
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
        Department department = new Department("Department", "17", DepartmentStatus.Ativo);
        Department departmentUpdated = new Department("", "17", DepartmentStatus.Ativo);
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
        Department department = new Department("Department", "18", DepartmentStatus.Ativo);
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
        Department department = new Department("Department 1", "19", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotDeleteDepartmentThatDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/6"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void shouldNotDeleteDepartmentWhenUrlIsWrong() throws Exception {
        Department department = new Department("Department 1", "20", DepartmentStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(department)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/999"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}