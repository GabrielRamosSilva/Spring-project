package br.com.kyros.springproject.repository;

import br.com.kyros.springproject.model.Department;
import br.com.kyros.springproject.model.enums.DepartmentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"br.com.kyros.springproject.model", "br.com.kyros.springproject.repository"})
@ActiveProfiles("test")
@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void shouldFindDepartmentByDepartmentName() {
        String departmentName = "Department Test";
        Department testDepartment = new Department();
        testDepartment.setDepartmentName(departmentName);
        testDepartment.setPayrollCode("10");
        testDepartment.setDepartmentStatus(DepartmentStatus.Ativo);
        departmentRepository.save(testDepartment);

        Department department = departmentRepository.findByDepartmentName(departmentName);
        Assertions.assertNotNull(department);
        Assertions.assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    public void test() {
        Department department = new Department("Department", "10", DepartmentStatus.Ativo);
        departmentRepository.save(department);
        String departmentName = "Department";
        Department findDepartment = departmentRepository.findByDepartmentName(departmentName);
        Assertions.assertNotNull(department);
        Assertions.assertEquals(departmentName, findDepartment.getDepartmentName());

    }

    @Test
    public void shouldNotFindDepartmentIfDepartmentNameDoesNotExist() {
        String departmentName = "Department";
        Department department = departmentRepository.findByDepartmentName(departmentName);
        Assertions.assertNull(department);
    }

}