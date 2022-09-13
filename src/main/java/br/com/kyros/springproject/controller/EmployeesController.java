package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.EmployeeDto;
import br.com.kyros.springproject.form.EmployeeForm;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.repository.DepartmentRepository;
import br.com.kyros.springproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/")
    public Page<EmployeeDto> employeesList(@PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByOrderByNameAsc(pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<EmployeeDto> getEmployeeByRegisterNumber(@PathVariable String registrationNumber){
        Optional<Employee> employee = employeeRepository.findOneEmployeeByRegistrationNumber(registrationNumber);
        if (employee.isPresent()){
            return ResponseEntity.ok(new EmployeeDto(employee.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/byDepartment")
    public Page<EmployeeDto> employeesByDepartment(@RequestParam String department, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByDepartmentDepartmentName(department, pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byName")
    public Page<EmployeeDto> employeeByName(@RequestParam String name, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByName(name, pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byAdmissionDate")
    public Page<EmployeeDto> employeesByAdmissionDate(@RequestParam Date admissionDate, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByAdmissionDate(admissionDate, pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byEmployeeStatus")
    public Page<EmployeeDto> employeesByEmployeeStatus(@RequestParam EmployeeStatus employeeStatus, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByEmployeeStatus(employeeStatus, pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byLeader")
    public Page<EmployeeDto> employeesByLeader(@RequestParam String leaderNumber, @PageableDefault(page=0, size=10) Pageable pageable){
        Page<Employee> employees = employeeRepository.findByEmployeeLeader(leaderNumber, pageable);
        return EmployeeDto.convertToDto(employees);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody @Valid EmployeeForm employeeForm, UriComponentsBuilder uriBuilder){
        Employee employee = employeeForm.convertToEmployee(departmentRepository, employeeRepository);
        employeeRepository.save(employee);
        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeDto(employee));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody @Valid EmployeeForm form) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()){
            Employee employee = form.update(id, employeeRepository, departmentRepository);
            return ResponseEntity.ok(new EmployeeDto(employee));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
