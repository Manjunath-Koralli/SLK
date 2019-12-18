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

import com.slk.DAO.BranchDAOImpl;
import com.slk.model.Branch;


@RestController
@Repository
public class BankRestController {
	
	@Autowired
	private BranchDAOImpl branchDaoImpl;
	
	@PostMapping(value = "/post/branch")
	public ResponseEntity createBranch(@RequestBody  Branch branch) {

		branchDaoImpl.registerBranch(branch);

		return new ResponseEntity(branch, HttpStatus.OK);
	}
	
	@GetMapping("/branch")
	public List getBranches() {
		return branchDaoImpl.getAllBranch();
	}
	
	@PutMapping("/put/branch/{id}")
	public ResponseEntity updateBranch(@PathVariable Long id, @RequestBody Branch branch) {

		branch = branchDaoImpl.updateBranch(id, branch);

		if (null == branch) {
			return new ResponseEntity("No Branch found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(branch, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/branch/{id}")
	public ResponseEntity deleteBranch(@PathVariable Long id) {

		if (null == branchDaoImpl.deleteBranch(id)) {
			return new ResponseEntity("No Branch found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	
}
	
