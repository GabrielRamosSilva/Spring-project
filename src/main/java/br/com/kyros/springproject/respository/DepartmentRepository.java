package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);

}
