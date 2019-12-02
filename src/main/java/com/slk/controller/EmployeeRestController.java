package com.slk.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.slk.DAO.EmployeeDAOImpl;

import com.slk.model.Employee;

@RestController
@Repository
public class EmployeeRestController {
	
	@Autowired
	private EmployeeDAOImpl employeeDaoimpl;
	
	@PostMapping(value = "/post/employee")
	public ResponseEntity createCustomer(@RequestBody Employee employee) {

		employeeDaoimpl.registerAdmin(employee);

		return new ResponseEntity(employee, HttpStatus.OK);
	}
	
	@GetMapping("/employee")
	public List getEmployees() {
		return employeeDaoimpl.getAllAdmin();
	}
	
	/*@GetMapping("/employee/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {
		//employee = employeeDaoimpl.updateAdmin(id);
		Customer customer = customerDAO.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}*/
	
	@PutMapping("/put/employee/{id}")
	public ResponseEntity updateAdmins(@PathVariable Long id, @RequestBody Employee employee) {

		employee = employeeDaoimpl.updateAdmin(id, employee);

		if (null == employee) {
			return new ResponseEntity("No Employee found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(employee, HttpStatus.OK);
	}
	
	@PutMapping("/put/superuser/{id}")
	public ResponseEntity updateSuser(@PathVariable Long id, @RequestBody Employee employee) {

		employee = employeeDaoimpl.updateSuperuser(id, employee);

		if (null == employee) {
			return new ResponseEntity("No Employee found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(employee, HttpStatus.OK);
	}

	
	
	@DeleteMapping("/delete/employee/{id}")
	public ResponseEntity deleteAdmins(@PathVariable Long id) {

		if (null == employeeDaoimpl.deleteAdmin(id)) {
			return new ResponseEntity("No Admin found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	
}
