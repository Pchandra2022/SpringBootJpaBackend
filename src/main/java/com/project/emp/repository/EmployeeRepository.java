package com.project.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.emp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
