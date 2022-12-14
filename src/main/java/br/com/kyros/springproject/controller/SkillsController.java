package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.SkillDto;
import br.com.kyros.springproject.form.SkillForm;
import br.com.kyros.springproject.model.Skill;
import br.com.kyros.springproject.model.enums.SkillType;
import br.com.kyros.springproject.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/skills")
public class SkillsController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("/")
    public Page<SkillDto> skillsList(@PageableDefault(page=0, size=10) Pageable pageable){
        Page<Skill> skills = skillRepository.findByOrderBySkillNameAsc(pageable);
        return SkillDto.convertToDto(skills);
    }

    @GetMapping("/byName")
    public Page<SkillDto> skillsByName(@RequestParam String skillName, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Skill> skills = skillRepository.findByName(skillName, pageable);
        return SkillDto.convertToDto(skills);
    }

    @GetMapping("/byType")
    public Page<SkillDto> skillsByType(@RequestParam SkillType skillType, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Skill> skills = skillRepository.findBySkillType(skillType, pageable);
        return SkillDto.convertToDto(skills);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<SkillDto> saveNewSkill(@RequestBody @Valid SkillForm skillForm, UriComponentsBuilder uriBuilder) {
        Skill skill = skillForm.convertToSkill(skillRepository);
        skillRepository.save(skill);
        URI uri = uriBuilder.path("skills/{id}").buildAndExpand(skill.getId()).toUri();
        return ResponseEntity.created(uri).body(new SkillDto(skill));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody @Valid SkillForm form) {
        Optional<Skill> optional = skillRepository.findById(id);
        if (optional.isPresent()){
            Skill skill = form.update(id, skillRepository);
            return ResponseEntity.ok(new SkillDto(skill));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id){
        Optional<Skill> optional = skillRepository.findById(id);
        if (optional.isPresent()){
            skillRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
