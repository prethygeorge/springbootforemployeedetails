package com.prethy.springboot.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prethy.springboot.backend.entity.Employee;
import com.prethy.springboot.backend.exception.ResourceNotFoundException;
import com.prethy.springboot.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public ResponseEntity<Employee> getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id: "+id) );
		return ResponseEntity.ok(employee);
	}

	public ResponseEntity<Employee> updateEmployee(Long id, Employee employee) {
		// TODO Auto-generated method stub
		Employee employee1 = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id: "+id) );
		employee1.setFirstName(employee.getFirstName());
		employee1.setLastName(employee.getLastName());
		employee1.setEmailId(employee.getEmailId());
		Employee updatedEmployee = employeeRepository.save(employee1);
		return ResponseEntity.ok(updatedEmployee);
	}

	public ResponseEntity<Map<String, Boolean>> deleteEmployees(Long id) {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id: "+id) );
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	

}
