package br.com.kyros.springproject.respository;

import br.com.kyros.springproject.model.Employee;
import br.com.kyros.springproject.model.enums.EmployeeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT e FROM Employee e WHERE e.registrationNumber = :registrationNumber")
    Optional<Employee> findOneEmployeeByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT e FROM Employee e WHERE e.registrationNumber = :registrationNumber")
    Employee findLeaderByRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    List<Employee> findByOrderByNameAsc();

    List<Employee> findByDepartmentDepartmentName(String departmentName);

    List<Employee> findByName(String name);

    List<Employee> findByAdmissionDate(Date admissionDate);

    @Query("SELECT e FROM Employee e WHERE e.leader.registrationNumber = :registrationNumber")
    List<Employee> findByEmployeeLeader(@Param("registrationNumber") String leaderNumber);

    List<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus);

}
