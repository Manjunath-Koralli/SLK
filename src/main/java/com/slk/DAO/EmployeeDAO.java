package com.slk.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.Employee;

@Repository
public interface EmployeeDAO {
	Employee registerAdmin(Employee employee);
	List<Employee> getAllAdmin();
	List<Employee> getSuperuser();
	Employee get(Long empId);
	Long deleteAdmin(Long empId);
	Employee updateAdmin(Long empId,Employee emp);
	Employee updateSuperuser(Long empId,Employee emp);
	
}