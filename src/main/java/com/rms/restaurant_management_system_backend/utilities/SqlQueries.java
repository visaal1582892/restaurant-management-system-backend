package com.rms.restaurant_management_system_backend.utilities;

public class SqlQueries {

	public SqlQueries() {

	}

	public static final String EMPLOYEE_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";

	public static final String GET_ALL_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees";

	public static final String GET_ACTIVE_EMPLOYEES = "select (emp_id,name,email,phone,status,designation,join_date,leaving_date) from employees where status=Active";

}
