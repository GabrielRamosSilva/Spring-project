package br.com.kyros.springproject.form;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.model.enums.Gender;
import br.com.kyros.springproject.repository.DepartmentRepository;
import br.com.kyros.springproject.repository.EmployeeRepository;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class EmployeeForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String registrationNumber;
    //@CPF
    private String cpf;
    @NotNull @NotEmpty
    private String leaderNumber;
    @DateTimeFormat
    private Date admissionDate;
    private int salary;
    private Gender gender;
    @NotNull @NotEmpty
    private String departmentName;
    private EmployeeStatus employeeStatus;
    private Date dismissionDate;

    public EmployeeForm(String name, String registrationNumber, String cpf, String leaderNumber, Date admissionDate, int salary, Gender gender, String departmentName, EmployeeStatus employeeStatus) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.cpf = cpf;
        this.leaderNumber = leaderNumber;
        this.admissionDate = admissionDate;
        this.salary = salary;
        this.gender = gender;
        this.departmentName = departmentName;
        this.employeeStatus = employeeStatus;
    }

    public Employee convertToEmployee(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        Department department = departmentRepository.findByDepartmentName(departmentName);
        Employee leader = employeeRepository.findLeaderByRegistrationNumber(leaderNumber);
        return new Employee(name, registrationNumber, cpf, leader, admissionDate, salary, gender, department, employeeStatus, dismissionDate);
    }

    public Employee update(Long id, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
        Employee employee = employeeRepository.getOne(id);
        employee.setName(this.name);
        employee.setRegistrationNumber(this.registrationNumber);
        employee.setCpf(this.cpf);
        employee.setLeader(employeeRepository.findLeaderByRegistrationNumber(this.leaderNumber));
        employee.setAdmissionDate(this.admissionDate);
        employee.setSalary(this.salary);
        employee.setGender(this.gender);
        employee.setDepartment(departmentRepository.findByDepartmentName(this.departmentName));
        //employee.setEmployeeStatus(this.employeeStatus);
        employee.dismissEmployee(this.dismissionDate);
        return employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLeaderNumber() {
        return leaderNumber;
    }

    public void setLeaderNumber(String leaderNumber) {
        this.leaderNumber = leaderNumber;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender genderName) {
        this.gender = genderName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Date getDismissionDate() {
        return dismissionDate;
    }

    public void setDismissionDate(Date dismissionDate) {
        this.dismissionDate = dismissionDate;

    }
}
