package br.com.kyros.springproject.form;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
import br.com.kyros.springproject.repository.SkillRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SkillForm {

    @NotNull @NotEmpty
    private String skillName;
    @NotNull @NotEmpty
    private String concept;
    private SkillType skillType;

    public Skill convertToSkill(SkillRepository skillRepository) {
        return new Skill(skillName, concept, skillType);
    }

    public Skill update(Long id, SkillRepository skillRepository){
        Skill skill = skillRepository.getOne(id);
        skill.setSkillName(this.skillName);
        skill.setConcept(this.concept);
        skill.setSkillType(this.skillType);
        return skill;
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
}
