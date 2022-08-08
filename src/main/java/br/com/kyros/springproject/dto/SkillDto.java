package br.com.kyros.springproject.dto;

import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;

import java.util.List;
import java.util.stream.Collectors;

public class SkillDto {

    private Long id;
    private String skillName;
    private String concept;
    private SkillType skillType;

    public SkillDto(Skill skill) {
        this.id = skill.getId();
        this.skillName = skill.getSkillName();
        this.concept = skill.getConcept();
        this.skillType = skill.getSkillType();
    }

    public static List<SkillDto> convertToDto(List<Skill> skills){
        return skills.stream().map(SkillDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getConcept() {
        return concept;
    }

    public SkillType getSkillType() {
        return skillType;
    }
}
