package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findBySkillName(String skillName);

    @Query("SELECT s FROM Skill s WHERE s.skillName = :skillName")
    List<Skill> findByName (@Param("skillName") String skillName);

    List<Skill> findBySkillType (SkillType skillType);

    List<Skill> findByOrderBySkillNameAsc();

}
