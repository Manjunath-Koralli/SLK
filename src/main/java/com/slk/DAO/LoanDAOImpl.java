package com.slk.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.slk.model.Branch;
import com.slk.model.Loan;
import com.slk.util.DBUtil;

public class LoanDAOImpl implements LoanDAO{
	
	Connection connection = null;
	private static List<Loan> loans;
	
	{
		connection = DBUtil.getConnection();
		System.out.println(connection);
	}

	@Override
	public Loan addLoan(Loan loan) {
		// TODO Auto-generated method stub
		String select_query = "Select max(loan_id) from loan";
		Statement select_stmt;
		int i=0;
		try {
			select_stmt = connection.createStatement();
			ResultSet select_rs = select_stmt.executeQuery(select_query);

			select_rs.next();
			int loan_Id = select_rs.getInt(1);
			int temp_loanId = ++loan_Id;
			/*employee.setEmpId(temp_empId);
			employee.setName("Soumya");
			employee.setDob("1997-07-25");
			employee.setContact(876260339);
			employee.setUsername("sou");
			employee.setPwd("sou23");
			employee.setRole(role);*/
			PreparedStatement pst = connection.prepareStatement("Insert into Loan values(?,?,?,?)");
			
			pst.setInt(1,temp_loanId);
			pst.setString(2,loan.getLoan_type());
			pst.setString(3,loan.getLoan_irate());
			pst.setString(4,loan.getLoan_desc());
		
			
			i = pst.executeUpdate();
			System.out.println(i + " records inserted");
			//admins.add(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return loan;
		
	
	}

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		String query = "Select * from loan";
		
		loans = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Loan loan = new Loan();
				int loan_Id = rs.getInt(1);
				String loan_type = rs.getString(2);
				String loan_rate = rs.getString(3);
				String loan_desc = rs.getString(4);
				
				loan.setLoan_id(loan_Id);
				loan.setLoan_type(loan_type);
				loan.setLoan_irate(loan_rate);
				loan.setLoan_desc(loan_desc);
				loans.add(loan);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loans;
		
	
	}

	@Override
	public Loan updateLoan(Long loanId, Loan loan) {
		// TODO Auto-generated method stub
		try {
			String updSql = "UPDATE  Loan set loan_type = ?, interest_rate = ?, loan_desc = ? WHERE loan_id = ?";
			PreparedStatement pst = connection.prepareStatement(updSql);
			
			pst.setString(1, loan.getLoan_type());
			pst.setString(2, loan.getLoan_irate());
			pst.setString(3, loan.getLoan_desc());
			pst.setLong(4, loanId);
			
			
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Loan updated");
			}
			return loan;
		
		} catch (SQLException e)
		{
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Long deleteLoan(Long loanId) {
		// TODO Auto-generated method stub
		try{
			
			String sql="DELETE FROM loan WHERE loanId = ? ";			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setLong(1, loanId);
			
			int res  = pst.executeUpdate();
			
			if(res > 0)
			{
				System.out.println("Loan Deleted");
			}
			
			return loanId;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
		

	@Override
	public List<Loan> get(String l_type) {
		// TODO Auto-generated method stub
		String query = "Select * from loan where loan_type = l_type";
		
		loans = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Loan loan = new Loan();
				int loan_Id = rs.getInt(1);
				String loan_type = rs.getString(2);
				String loan_rate = rs.getString(3);
				String loan_desc = rs.getString(4);
				
				loan.setLoan_id(loan_Id);
				loan.setLoan_type(loan_type);
				loan.setLoan_irate(loan_rate);
				loan.setLoan_desc(loan_desc);
				loans.add(loan);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loans;
		
	}

}
