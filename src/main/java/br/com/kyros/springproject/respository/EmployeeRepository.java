package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT e FROM Employee e WHERE e.registrationNumber = :registrationNumber")
    Optional<Employee> findOneEmployeeByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    List<Employee> findEmployeeDepartmentByDepartmentDepartmentName(String departmentName);

    List<Employee> findByOrderByNameAsc();

}
