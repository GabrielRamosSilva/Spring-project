package br.com.kyros.springproject.model;

import br.com.kyros.springproject.model.enums.EmployeeStatus;
import br.com.kyros.springproject.model.enums.Gender;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String registrationNumber;
    private String cpf;
    private String leader;
    private LocalDate admissionDate;
    private int salary;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus = EmployeeStatus.Ativo;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    private Department department;

    public Employee() { }

    public Employee(String name, String registrationNumber, String cpf, String leader, LocalDate admissionDate, int salary, Gender gender, Department department, EmployeeStatus employeeStatus) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.cpf = cpf;
        this.leader = leader;
        this.admissionDate = admissionDate;
        this.salary = salary;
        this.gender = gender;
        this.department = department;
        this.employeeStatus = employeeStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(registrationNumber, employee.registrationNumber) && Objects.equals(cpf, employee.cpf) && Objects.equals(leader, employee.leader) && Objects.equals(admissionDate, employee.admissionDate) && employeeStatus == employee.employeeStatus && gender == employee.gender && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, registrationNumber, cpf, leader, admissionDate, salary, employeeStatus, gender, department);
    }
}
