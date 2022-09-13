package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.form.EmployeeForm;
import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.model.enums.Gender;
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

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.controller"})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateNewEmployee() throws Exception {
        EmployeeForm employee = new EmployeeForm("Name", "1", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void shouldNotCreateNewEmployeeWhenNameIsNull() throws Exception {
        EmployeeForm employee = new EmployeeForm("", "2", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotCreateNewEmployeeWhenRegistrationNumberIsNull() throws Exception {
        EmployeeForm employee = new EmployeeForm("Name", "", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

//    @Test
//    public void shouldNotCreateEmployeeWhenRegistrationNumberAlreadyExists() throws Exception {
//        EmployeeForm employee1 = new EmployeeForm("Name 1", "4", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
//        EmployeeForm employee2 = new EmployeeForm("Name 2", "4", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
//        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(employee1)))
//                .andExpect(MockMvcResultMatchers.status().is(201));
//        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(employee2)))
//                .andExpect(MockMvcResultMatchers.status().is(500));
//    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        EmployeeForm employee1 = new EmployeeForm("Name", "1", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)));
        EmployeeForm employee2 = new EmployeeForm("Name 2", "4", "09843344677", "0", new Date(2022-01-01), 3000, Gender.Masculino, "D", EmployeeStatus.Inativo);
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee2)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotUpdateEmployeeWhenNameIsNull() throws Exception {
        EmployeeForm employee = new EmployeeForm("Name", "1", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        EmployeeForm employee2 = new EmployeeForm("", "4", "09843344677", "0", new Date(2022-01-01), 3000, Gender.Masculino, "D", EmployeeStatus.Inativo);
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee2)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        EmployeeForm employee = new EmployeeForm("Name", "5", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/2"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotDeleteEmployeeWhenEmployeeDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void shouldNotDeleteEmployeeWhenUrlIsWrong() throws Exception {
        EmployeeForm employee = new EmployeeForm("Name", "1", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/20"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}