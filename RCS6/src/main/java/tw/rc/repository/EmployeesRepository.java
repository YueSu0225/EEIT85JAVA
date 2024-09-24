package tw.rc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.rc.model.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long>{

}
