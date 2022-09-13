package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.form.EmployeeForm;
import br.com.kyros.springproject.form.FeedbackForm;
import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.model.enums.FeedbackType;
import br.com.kyros.springproject.model.enums.Gender;
import br.com.kyros.springproject.model.enums.SkillType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.controller"})
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FeedbacksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach


    @Test
    public void shouldCreateNewFeedback() throws Exception {
        Skill skill = new Skill("Skill", "Concept", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill)));
        EmployeeForm employee = new EmployeeForm("Name", "1", "09843344677", "0", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        EmployeeForm employee2 = new EmployeeForm("Name 2", "2", "09843344677", "1", new Date(2022-01-01), 2000, Gender.Masculino, "D", EmployeeStatus.Ativo);
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));
        FeedbackForm feedback = new FeedbackForm(LocalDate.of(2022, 01, 01), FeedbackType.Elogio, "Skill Name", "Description", "1", "2");
        mockMvc.perform(MockMvcRequestBuilders.post("/feedbacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedback)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}