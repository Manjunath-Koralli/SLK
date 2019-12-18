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

import com.slk.DAO.LoanDAOImpl;

import com.slk.model.Loan;



@RestController
@Repository
public class LoanRestController {
	
	@Autowired
	private LoanDAOImpl loanDaoImpl;
	
	@PostMapping(value = "/post/loan")
	public ResponseEntity createLoan(@RequestBody Loan loan) {

		loanDaoImpl.addLoan(loan);

		return new ResponseEntity(loan, HttpStatus.OK);
	}
	
	@GetMapping("/loan")
	public List getLoans() {
		return loanDaoImpl.getAllLoans();
	}
	
	@PutMapping("/put/loan/{id}")
	public ResponseEntity updateLoans(@PathVariable Long id, @RequestBody Loan loan) {

		loan = loanDaoImpl.updateLoan(id, loan);

		if (null == loan) {
			return new ResponseEntity("No Loan found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(loan, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/loan/{id}")
	public ResponseEntity deleteLoans(@PathVariable Long id) {

		if (null == loanDaoImpl.deleteLoan(id)) {
			return new ResponseEntity("No Loan found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	
	@GetMapping("/loan/{l_type}")
	public ResponseEntity<List> getLoanByType(@PathVariable String l_type) {
		return (ResponseEntity<List>) loanDaoImpl.get(l_type);
	}

}
