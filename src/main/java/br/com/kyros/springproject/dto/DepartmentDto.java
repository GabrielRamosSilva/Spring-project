package br.com.kyros.springproject.dto;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String payrollCode;
    private DepartmentStatus departmentStatus;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.departmentName = department.getDepartmentName();
        this.payrollCode = department.getPayrollCode();
        this.departmentStatus = department.getDepartmentStatus();
    }

    public static Page<DepartmentDto> convertToDto(Page<Department> departments){
        return departments.map(DepartmentDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getPayrollCode() {
        return payrollCode;
    }

    public DepartmentStatus getDepartmentStatus() {
        return departmentStatus;
    }
}
