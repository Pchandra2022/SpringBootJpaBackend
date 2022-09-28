package com.project.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.emp.exception.ResourceNotFoundException;
import com.project.emp.model.Employee;
import com.project.emp.repository.EmployeeRepository;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

	Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired private EmployeeRepository employeeRepository;
	
	//get all employees..
	
	@GetMapping("employees")
	public List<Employee> getAlEmployees(){
		return employeeRepository.findAll();
	}
	
	
	//create employee rest api
	
	@PostMapping("employeesc")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee id by rest api
	
	@GetMapping("employeeById/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee not exist by : "+id));
		log.info("fetched data id : "+employee.toString());
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	
	@PutMapping("employeeu/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Long id){
		Employee emp = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee not exist by : "+id));
		
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		
		Employee updateEmp = employeeRepository.save(emp);
		
		log.info("fetched data id : "+emp.toString());
		return ResponseEntity.ok(updateEmp);
	}
	
	//delete empoyee rest api
	
	@DeleteMapping("employeed/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployeee(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee not exist by : "+id));
		
		employeeRepository.delete(emp);
		Map<String, Boolean> results = new HashMap<>();
		results.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(results);
		
	}
	
	@DeleteMapping("employeedd/{id}")
	public String deleteEmployee(@PathVariable Long id){
		Employee emp = employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee not exist by : "+id));
		
		employeeRepository.delete(emp);
//		Map<String, Boolean> results = new HashMap<>();
//		results.put("deleted", Boolean.TRUE);
		log.info(emp.getId()+"id employee data deleted successfully");		
		return emp.getId()+"id employee data deleted successfully";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
