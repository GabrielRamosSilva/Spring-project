package br.com.kyros.springproject.form;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import br.com.kyros.springproject.repository.DepartmentRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepartmentForm {

    @NotNull @NotEmpty
    private String departmentName;
    @NotNull @NotEmpty
    private String payrollCode;
    private DepartmentStatus departmentStatus;

    public Department convertToDepartment(DepartmentRepository departmentRepository){
        return new Department(departmentName, payrollCode, departmentStatus);
    }

    public Department update(Long id, DepartmentRepository departmentRepository){
        Department department = departmentRepository.getOne(id);
        department.setDepartmentName(this.departmentName);
        department.setPayrollCode(this.payrollCode);
        department.setDepartmentStatus(this.departmentStatus);
        return department;
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
}
