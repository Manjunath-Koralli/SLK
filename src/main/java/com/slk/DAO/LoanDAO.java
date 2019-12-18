package com.slk.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.Loan;
@Repository
public interface LoanDAO {
	Loan addLoan(Loan loan);
	List<Loan> getAllLoans();
	Loan updateLoan(Long loanId,Loan loan);
	Long deleteLoan(Long loanId);
	List<Loan> get(String loan_type);
}
