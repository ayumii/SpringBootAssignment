package com.cdit.webapp.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdit.webapp.constants.Const;
import com.cdit.webapp.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/users")
	public ResponseEntity<String> getUsers(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Const.EMPLOYEE_RESULTS, employeeRepository.getEmployee(Const.MIN_SALARY, Const.MAX_SALARY));
		return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
				
	}

}
