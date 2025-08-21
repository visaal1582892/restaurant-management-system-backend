package com.rms.restaurant_management_system_backend.utilities;

public class SqlQueries {

	public SqlQueries() {
		
	}

	public static final String MEMBER_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";
	
	public static final String CUSTOMER_INSERT = "INSERT into customers(name,phone) VALUES(?,?)";
	
	public static final String GET_ALL_ORDERDETAILS= "SELECT * FROM order_details";
}
