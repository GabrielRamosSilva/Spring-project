package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findBySkillName(String skillName);

}
