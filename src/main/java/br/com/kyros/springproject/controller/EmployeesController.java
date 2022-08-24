package br.com.kyros.springproject.controller;

import br.com.kyros.springproject.dto.EmployeeDto;
import br.com.kyros.springproject.form.EmployeeForm;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.repository.DepartmentRepository;
import br.com.kyros.springproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EmployeeDto> employeesList(){
        List<Employee> employees = employeeRepository.findByOrderByNameAsc();
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
    public List<EmployeeDto> employeesByDepartment(@RequestParam String department){
        List<Employee> employees = employeeRepository.findByDepartmentDepartmentName(department);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byName")
    public List<EmployeeDto> employeeByName(@RequestParam String name){
        List<Employee> employees = employeeRepository.findByName(name);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byAdmissionDate")
    public List<EmployeeDto> employeesByAdmissionDate(@RequestParam Date admissionDate){
        List<Employee> employees = employeeRepository.findByAdmissionDate(admissionDate);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byEmployeeStatus")
    public List<EmployeeDto> employeesByEmployeeStatus(@RequestParam EmployeeStatus employeeStatus){
        List<Employee> employees = employeeRepository.findByEmployeeStatus(employeeStatus);
        return EmployeeDto.convertToDto(employees);
    }

    @GetMapping("/byLeader")
    public List<EmployeeDto> employeesByLeader(@RequestParam String leaderNumber){
        List<Employee> employees = employeeRepository.findByEmployeeLeader(leaderNumber);
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
