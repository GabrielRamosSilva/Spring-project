package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.DepartmentDto;
import br.com.kyros.springproject.form.DepartmentForm;
import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.respository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/")
    public List<DepartmentDto> departmentsList(){
        List<Department> departments = departmentRepository.findAll();
        return DepartmentDto.convertToDto(departments);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DepartmentDto> saveNewDepartment(@RequestBody @Valid DepartmentForm departmentForm, UriComponentsBuilder uriBuilder){
        Department department = departmentForm.convertToDepartment(departmentRepository);
        departmentRepository.save(department);
        URI uri = uriBuilder.path("departments/{id}").buildAndExpand(department.getId()).toUri();
        return ResponseEntity.created(uri).body(new DepartmentDto(department));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentForm form) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()){
            Department department = form.update(id, departmentRepository);
            return ResponseEntity.ok(new DepartmentDto(department));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()){
            departmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
