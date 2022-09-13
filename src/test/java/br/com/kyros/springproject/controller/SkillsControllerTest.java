package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
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
class SkillsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateNewSkill() throws Exception {
        Skill skill = new Skill("Skill Name", "Concept", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void shouldNotCreateNewSkillWhenNameIsNull() throws Exception {
        Skill skill = new Skill("", "Concept", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotCreateNewSkillWhenConceptIsNull() throws Exception {
        Skill skill = new Skill("Skill Name", "", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldListSkills() throws Exception {
        Skill skill1 = new Skill("Skill 1", "Concept 1", SkillType.Liderança);
        Skill skill2 = new Skill("Skill 2", "Concept 2", SkillType.Operacional);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill1)));
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill2)));
        mockMvc.perform(MockMvcRequestBuilders.get("/skills/"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

//    @Test
//    public void shouldListSkillsByName() throws Exception {
//        Skill skill1 = new Skill("Skill 1", "Concept 1", SkillType.Liderança);
//        Skill skill2 = new Skill("Skill 2", "Concept 2", SkillType.Operacional);
//        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(skill1)));
//        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(skill2)));
//        mockMvc.perform(MockMvcRequestBuilders.get("/skills/byName")
//                        .param("skillName", "Skill 1"))
//                .andExpect(MockMvcResultMatchers.status().is(200))
//                .andExpect(MockMvcResultMatchers.content().json("{\n" +
//                        "    \"skillName\":\"Skill 1\",\n" +
//                        "    \"concept\":\"Concept 1\",\n" +
//                        "    \"skillType\":\"Liderança\"\n" +
//                        "}"));
//    }



    @Test
    public void shouldUpdateSkill() throws Exception {
        Skill skill = new Skill("Skill", "Concept", SkillType.Liderança);
        Skill skillUpdate = new Skill("Skill Update", "Concept Update", SkillType.Operacional);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill)));
        mockMvc.perform(MockMvcRequestBuilders.put("/skills/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skillUpdate)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotUpdateSkillWhenUrlIsWrong() throws Exception {
        Skill skill = new Skill("Skill", "Concept", SkillType.Liderança);
        Skill skillUpdate = new Skill("Skill Update", "Concept Update", SkillType.Operacional);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill)));
        mockMvc.perform(MockMvcRequestBuilders.put("/skills/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skillUpdate)))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void shouldNotUpdateSkillWhenNameIsanull() throws Exception {
        Skill skill = new Skill("Skill", "Concept", SkillType.Liderança);
        Skill skillUpdate = new Skill("", "Concept Update", SkillType.Operacional);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill)));
        mockMvc.perform(MockMvcRequestBuilders.put("/skills/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skillUpdate)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldNotUpdateSkillWhenConceptIsanull() throws Exception {
        Skill skill = new Skill("Skill", "Concept", SkillType.Liderança);
        Skill skillUpdate = new Skill("Skill Update", "", SkillType.Operacional);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill)));
        mockMvc.perform(MockMvcRequestBuilders.put("/skills/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skillUpdate)))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldDeleteSkill() throws Exception {
        Skill skill1 = new Skill("Skill 1", "Concept 1", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill1)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/skills/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void shouldNotDeleteSkillThatDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/skills/1"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    public void shouldNotDeleteSkillWhenUrlIsWrong() throws Exception {
        Skill skill1 = new Skill("Skill 1", "Concept 1", SkillType.Liderança);
        mockMvc.perform(MockMvcRequestBuilders.post("/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(skill1)));
        mockMvc.perform(MockMvcRequestBuilders.delete("/skills/2"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}