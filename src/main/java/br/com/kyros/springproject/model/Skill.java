package br.com.kyros.springproject.model;

import br.com.kyros.springproject.model.enums.SkillType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Skill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillName;
    private String concept;
    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    public Skill() { }

    public Skill(String skillName, String concept, SkillType skillType){
        this.skillName = skillName;
        this.concept = concept;
        this.skillType = skillType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(skillName, skill.skillName) && Objects.equals(concept, skill.concept) && skillType == skill.skillType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skillName, concept, skillType);
    }
}
