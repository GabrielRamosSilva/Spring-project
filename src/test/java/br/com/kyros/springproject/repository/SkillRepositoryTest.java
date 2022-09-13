package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.repository"})
@ActiveProfiles("test")
@DataJpaTest
class SkillRepositoryTest {

    @Autowired
    SkillRepository skillRepository;

    @Test
    public void shouldFindSkillByName() {
        Skill skill = new Skill("Skill Name", "Concept", SkillType.Liderança);
        skillRepository.save(skill);
        String skillName = "Skill Name";
        Skill findSkill = skillRepository.findBySkillName(skillName);
        Assertions.assertEquals(skillName, findSkill.getSkillName());
    }

    @Test
    public void shouldNotFindSkillByName() {
        String skillName = "Skill Name";
        Skill skill = skillRepository.findBySkillName(skillName);
        Assertions.assertNull(skill);
    }

    @Test
    public void shouldFindSkillPageByName() {
        Skill skill = new Skill("Skill Name", "Concept", SkillType.Liderança);
        skillRepository.save(skill);
        String skillName = "Skill Name";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Skill> findSkill = skillRepository.findByName(skillName, pageable);
        Assertions.assertEquals(skillName, findSkill.stream().collect(Collectors.toList()).get(0).getSkillName());
    }

    @Test
    public void shouldNotFindSkillPageByName() {
        Skill skill = new Skill("Another Skill", "Concept", SkillType.Liderança);
        skillRepository.save(skill);
        String skillName = "Skill";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Skill> findSkill = skillRepository.findByName(skillName, pageable);
        Assertions.assertNull(findSkill);
    }

}