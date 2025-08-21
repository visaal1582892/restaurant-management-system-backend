package com.rms.restaurant_management_system_backend.utilities;

public class SqlQueries {

	public SqlQueries() {

	}

	public static final String EMPLOYEE_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";

	public static final String GET_ALL_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees";

	public static final String GET_ACTIVE_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where status='Active' ";

	public static final String UPDATE_EMPLOYEE = "update employees set name=?,email=?,phone=?,designation=?,join_date=?,leaving_date=? where emp_id=? and status='Active' ";

	public static final String DELETE_EMPLOYEE = "update employees set status='Inactive' where emp_id=?";

	public static final String UPDATE_EMP_STATUS = "update employees set status=? where emp_id=?";

	public static final String MEMBER_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";

	public static final String CUSTOMER_INSERT = "INSERT into customers(name,phone) VALUES(?,?)";

	public static final String GET_ALL_ORDERDETAILS = "SELECT * FROM order_details";
	
	public static final String WAITER_INSERT="insert into waiters(emp_id,availability) values(?,'Available')";

	public static final String WAITER_UPDATE_AVAILABILITY="update waiters set availability=? where wtr_id=?";
	
	public static final String WAITER_LOG_INSERT="insert into waiters_log values(?,?,?)";
	
	public static final String COUNT_WAITER_ASSIGNED_ORDERS="select count(*) from orders where wtr_id=?";
	
}
