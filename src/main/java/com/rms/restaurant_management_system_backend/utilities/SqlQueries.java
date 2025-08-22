package com.rms.restaurant_management_system_backend.utilities;

public class SqlQueries {

	public SqlQueries() {

	}

	public static final String EMPLOYEE_SELECT_BY_EMAIL = "SELECT COUNT(*) FROM employees WHERE  email = ? AND status = 'Active' ";

	public static final String EMPLOYEE_SELECT_BY_MOBILE = "SELECT COUNT(*) FROM employees WHERE  phone = ? AND status = 'Active' ";

	public static final String GET_EMPID_BY_EMAIL = "select emp_id from employees where email = ?";

	public static final String EMPLOYEE_BY_ID = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where emp_id=? and status='Active' ";

	public static final String EMPLOYEE_INSERT = "insert into employees(name,email,phone,designation) values (?,?,?,?)";

	public static final String GET_ALL_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees";

	public static final String GET_ACTIVE_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where status='Active' ";

	public static final String UPDATE_EMPLOYEE = "update employees set name=?,email=?,phone=?,status=?,designation=?,join_date=?,leaving_date=? where emp_id=?";

	public static final String DELETE_EMPLOYEE = "update employees set status='Inactive' where emp_id=?";

	public static final String UPDATE_EMP_STATUS = "update employees set status=? where emp_id=?";

	public static final String MEMBER_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";

	public static final String GET_ID_BY_PHONE = "select cust_id from customers where phone=?";

	public static final String CUSTOMER_INSERT = "INSERT into customers(name,phone) VALUES(?,?)";

	public static final String GET_ALL_ORDERDETAILS = "SELECT * FROM order_details";

	public static final String WAITER_UPDATE_AVAILABILITY = "update waiters set availability=? where wtr_id=?";

	public static final String WAITER_LOG_INSERT = "insert into waiters_log values(?,?,?)";

	public static final String COUNT_WAITER_ASSIGNED_ORDERS = "select count(*) from orders where wtr_id=? and status='Pending'";

	public static final String WAITER_SELECT_BY_ID = "select * from waiters where wtr_id=?";

	public static final String WAITER_INSERT = "insert into waiters(emp_id,availability) values(?,'Available')";
	
	public static final String WAITER_SELECT_AVAILABLE="select e.name,w.* from waiters w join employees e  where availability='Available'";
	
	public static final String WAITER_DELETE_BY_EMP_ID="delete from waiters where emp_id=?";

	public static final String ORDER_DETAILS_INSERT = "insert into order_details(ord_id,item_id,quantity,price) values(?,?,?,?)";

	public static final String ORDER_DETAILS_SELECT = "select * from order_details";

}
