package com.slk.DAO;

import java.util.List;

import com.slk.model.Loan;

public interface LoanDAO {
	Loan addLoan(Loan loan);
	List<Loan> getAllLoans();
	Loan updateLoan(Long loanId,Loan loan);
	Long deleteLoan(Long loanId);
	List<Loan> get(String loan_type);
}
