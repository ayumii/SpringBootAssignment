package com.cdit.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cdit.webapp.constants.Const;
import com.cdit.webapp.model.Employee;


@Repository
public class EmployeeRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Employee> getEmployee(float minSalary, float maxSalary) {
		
		String sql = "SELECT * FROM employee WHERE salary <= " + maxSalary + " and salary >= " + minSalary;

		return jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setFirstName(rs.getString(Const.EMPLOYEE_FIRST_NAME));
				employee.setLastName(rs.getString(Const.EMPLOYEE_LAST_NAME));
				employee.setSalary(rs.getFloat(Const.EMPLOYEE_SALARY));
				return employee;
			}
			
		});
	}
}
