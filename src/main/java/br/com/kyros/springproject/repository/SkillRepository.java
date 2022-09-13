package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findBySkillName(String skillName);

    @Query("SELECT s FROM Skill s WHERE s.skillName = :skillName")
    Page<Skill> findByName (@Param("skillName") String skillName, Pageable pageable);

    Page<Skill> findBySkillType (SkillType skillType, Pageable pageable);

    Page<Skill> findByOrderBySkillNameAsc(Pageable pageable);

}
