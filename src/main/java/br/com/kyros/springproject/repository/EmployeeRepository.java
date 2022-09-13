package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByRegistrationNumber(String registrationNumber, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.registrationNumber = :registrationNumber")
    Optional<Employee> findOneEmployeeByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT e FROM Employee e WHERE e.registrationNumber = :registrationNumber")
    Employee findLeaderByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    Page<Employee> findByOrderByNameAsc(Pageable pageable);

    Page<Employee> findByDepartmentDepartmentName(String departmentName, Pageable pageable);

    Page<Employee> findByName(String name, Pageable pageable);

    Page<Employee> findByAdmissionDate(Date admissionDate, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.leader.registrationNumber = :registrationNumber")
    Page<Employee> findByEmployeeLeader(@Param("registrationNumber") String leaderNumber, Pageable pageable);

    Page<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus, Pageable pageable);

}
