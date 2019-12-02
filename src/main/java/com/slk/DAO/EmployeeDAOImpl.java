package com.slk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.Employee;
import com.slk.util.DBUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	private static List<Employee> admins;
	
	
	 {
		connection = DBUtil.getConnection();
		System.out.println(connection);
		String query = "Select * from employee where emp_role = 'admin' ";
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
				String uname = rs.getString(5);
				String pwd = rs.getString(6);
				String erole = rs.getString(7);
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
		
		
	}

	@Override
	public List<Employee> getAllAdmin() {
		return admins;
	}
	
	

	@Override
	public Employee registerAdmin(Employee employee) {
		// TODO Auto-generated method stub
		String role = "admin";
		String select_query = "Select max(empId) from employee";
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
			pst.setString(5,employee.getUsername());
			pst.setString(6,employee.getPwd());
			pst.setString(7,role);
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
			
			
			String updSql = "UPDATE  Employee set name = ?,DOB = ?,phone_no = ? WHERE empId = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, "Vinayak");
			pst.setString(2, "1997-08-13");
			pst.setLong(3, 819713939);
			pst.setLong(4, empId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Admin updated");
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
			
			
			String updSql = "UPDATE  Employee set name = ? WHERE empId = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, "Prajwal");
			//pst.setString(2, "1997-08-13");
			//pst.setLong(3, 819713939);
			pst.setLong(2, empId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Super User updated");
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
			
			String sql="DELETE FROM Employee WHERE empId = ? ";			
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
		String query = "Select * from employee where empId = ?";
		
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
				return employee;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		return null;
	}

	
	

}
