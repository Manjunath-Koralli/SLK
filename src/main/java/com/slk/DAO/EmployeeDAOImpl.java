package com.slk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.DAO.EmployeeDAO;
import com.slk.model.Employee;
import com.slk.util.DBUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	private static List<Employee> admins;
	private static List<Employee> superuser;
	{
		connection = DBUtil.getConnection();
		System.out.println(connection);
	}
	
	
	 

	@Override
	public List<Employee> getAllAdmin() {
		
		
		String query = "Select * from employee where employee_role = 'managing director' ";
		//Employee emp;
		admins = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				int empId = rs.getInt(1);
				String empName = rs.getString(2);
				String empDob = rs.getString(3);
				long empContact = rs.getLong(4);
				
				String email = rs.getString(5);
				String uname = rs.getString(6);
				String pwd = rs.getString(7);
				String erole = rs.getString(8);
				System.out.println(empId + " " + empName + " " + empDob + " " + empContact);
				emp.setEmpId(empId);
				emp.setName(empName);
				emp.setDob(empDob);
				emp.setContact(empContact);
				emp.setUsername(uname);
				emp.setPwd(pwd);
				emp.setRole(erole);
				//admins.add(new Employee(empId,empName,empDob,empContact));
				admins.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admins;
	}
	
	@Override
	public List<Employee> getSuperuser() {
		// TODO Auto-generated method stub
		
		String query = "Select * from employee where employee_role = 'CEO' ";
		//Employee emp;
		superuser = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				int empId = rs.getInt(1);
				String empName = rs.getString(2);
				String empDob = rs.getString(3);
				long empContact = rs.getLong(4);
				String email = rs.getString(5);
				String uname = rs.getString(6);
				String pwd = rs.getString(7);
				String erole = rs.getString(8);
				System.out.println(empId + " " + empName + " " + empDob + " " + empContact);
				emp.setEmpId(empId);
				emp.setName(empName);
				emp.setDob(empDob);
				emp.setContact(empContact);
				emp.setEmail(email);
				emp.setUsername(uname);
				emp.setPwd(pwd);
				emp.setRole(erole);
				//admins.add(new Employee(empId,empName,empDob,empContact));
				superuser.add(emp);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return superuser;
		
		
	}
	

	@Override
	public Employee registerAdmin(Employee employee) {
		// TODO Auto-generated method stub
		String role = "admin";
		String select_query = "Select max(employee_id) from employee";
		Statement select_stmt;
		int i=0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int emp_Id = select_rs.getInt(1);
			int temp_empId = ++emp_Id;
			/*employee.setEmpId(temp_empId);
			employee.setName("Soumya");
			employee.setDob("1997-07-25");
			employee.setContact(876260339);
			employee.setUsername("sou");
			employee.setPwd("sou23");
			employee.setRole(role);*/
			PreparedStatement pst = connection.prepareStatement("Insert into Employee values(?,?,?,?,?,?,?)");
			
			pst.setInt(1,temp_empId);
			pst.setString(2,employee.getName());
			pst.setString(3,employee.getDob());
			pst.setLong(4,employee.getContact());
			pst.setString(5, employee.getEmail());
			pst.setString(6,employee.getUsername());
			pst.setString(7,employee.getPwd());
			pst.setString(8,role);
			i = pst.executeUpdate();
			System.out.println(i + " records inserted");
			//admins.add(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return employee;
	}
	

	@Override
	public Employee updateAdmin(Long empId,Employee emp)
	{
		try {
			String updSql = "UPDATE  Employee set employee_name = ?,dob = ?,contact = ?,mail = ?username =?,password = ?,employee_role=? WHERE employee_id = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, emp.getName());
			pst.setString(2, emp.getDob());
			pst.setLong(3, emp.getContact());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getUsername());
			pst.setString(6, emp.getPwd());
			pst.setString(7, emp.getRole());
			pst.setLong(8, empId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Superuser updated");
			}
			return emp;
		
		} catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Employee updateSuperuser(Long empId, Employee emp) {
		// TODO Auto-generated method stub
		try {
			String updSql = "UPDATE  Employee set employee_name = ?,dob = ?,contact = ?,mail = ?username =?,password = ?,employee_role=? WHERE employee_id = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, emp.getName());
			pst.setString(2, emp.getDob());
			pst.setLong(3, emp.getContact());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getUsername());
			pst.setString(6, emp.getPwd());
			pst.setString(7, emp.getRole());
			pst.setLong(8, empId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Superuser updated");
			}
			return emp;
		
		} catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long deleteAdmin(Long empId) {
		// TODO Auto-generated method stub
		try{
			
			String sql="DELETE FROM Employee WHERE employee_id = ? ";			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1, empId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Admin Deleted");
			}
			
			return empId;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Employee get(Long empId) {
		// TODO Auto-generated method stub
		String query = "Select * from employee where employee_id = ?";
		
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setLong(1, empId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmpId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setDob(rs.getString(3));
				employee.setContact(rs.getLong(4));
				employee.setUsername(rs.getString(5));
				employee.setPwd(rs.getString(6));
				employee.setRole(rs.getString(7));
				return employee;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		return null;
	}
	
	@Override
	public Employee updateCustomer(Long acc_no,Employee emp)
	{
		try {
			
			//String role = "admin";
			String updSql = "UPDATE  customer_account set approval = ? WHERE customer_Acc_no = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			//pst.setString(1, );
			//pst.setString(2, acc_no);
			
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Customer updated");
			}
			return emp;
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	



	

	

	

	
	

}
