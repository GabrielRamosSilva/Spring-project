package br.com.kyros.springproject.model;

import br.com.kyros.springproject.model.enums.DepartmentStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    @Column(unique=true)
    private String payrollCode;
    @Enumerated(EnumType.STRING)
    private DepartmentStatus departmentStatus = DepartmentStatus.Ativo;

    public Department() { }

    public Department(String departmentName, String payrollCode, DepartmentStatus departmentStatus) {
        this.departmentName = departmentName;
        this.payrollCode = payrollCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public void setPayrollCode(String payrollCode) {
        this.payrollCode = payrollCode;
    }

    public DepartmentStatus getDepartmentStatus() {
        return departmentStatus;
    }

    public void setDepartmentStatus(DepartmentStatus departmentStatus) {
        this.departmentStatus = departmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(departmentName, that.departmentName) && Objects.equals(payrollCode, that.payrollCode) && departmentStatus == that.departmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName, payrollCode, departmentStatus);
    }
}
