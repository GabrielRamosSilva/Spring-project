package br.com.kyros.springproject.dto;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.model.enums.Gender;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDto {

    private Long id;
    private String name;
    private String registrationNumber;
    private String cpf;
    private Employee leader;
    private Date admissionDate;
    private int salary;
    private EmployeeStatus employeeStatus;
    private Gender gender;
    private Department department;
    private Date dismissionDate;

    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.registrationNumber = employee.getRegistrationNumber();
        this.cpf = employee.getCpf();
        this.leader = employee.getLeader();
        this.admissionDate = employee.getAdmissionDate();
        this.salary = employee.getSalary();
        this.employeeStatus = employee.getEmployeeStatus();
        this.gender = employee.getGender();
        this.department = employee.getDepartment();
        this.dismissionDate = employee.getDismissionDate();
    }

    public static List<EmployeeDto> convertToDto(List<Employee> employees){
        return employees.stream().map(EmployeeDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public Employee getLeader() {
        return leader;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public int getSalary() {
        return salary;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public Date getDismissionDate() {
        return dismissionDate;
    }
}
