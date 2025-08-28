package com.rms.restaurant_management_system_backend.utilities;

public class SqlQueries {

	public SqlQueries() {

	}

	// Employee

	public static final String EMPLOYEE_SELECT_BY_EMAIL = "select count(*) from employees where  email = ? and status = 'Active' ";

	public static final String EMPLOYEE_SELECT_BY_MOBILE = "select count(*) from employees where  phone = ? and status = 'Active' ";

	public static final String GET_EMPID_BY_EMAIL = "select emp_id from employees where email = ?";

	public static final String EMPLOYEE_BY_ID = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where emp_id=:emp_id and status='Active'";

	public static final String EMPLOYEE_INSERTION = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (:name,:email,:phone,:status,:designation,:join_date,:leaving_date)";
	
	public static final String EMPLOYEE_INSERT = "insert into employees(name,email,phone,status,designation,join_date,leaving_date) values (?,?,?,?,?,?,?)";


	public static final String GET_ALL_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees";

	public static final String GET_ACTIVE_EMPLOYEES = "select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where status='Active' ";

	public static final String UPDATE_EMPLOYEE = "update employees set name=:name,email=:email,phone=:phone,status=:status,designation=:designation,join_date=:join_date,leaving_date=:leaving_date where emp_id=:emp_id ";

	public static final String DELETE_EMPLOYEE = "update employees set status='Inactive',leaving_date=CURRENT_DATE where emp_id=:emp_id ";

	public static final String UPDATE_EMP_STATUS = "update employees set status=:status where emp_id=:emp_id ";

	public static final String EMPLOYEE_LOG = "insert into employee_log(emp_id,name,email,phone,status,designation,join_date,leaving_date) select emp_id,name,email,phone,status,designation,join_date,leaving_date from employees where emp_id=?";

	// Customer

	public static final String GET_ID_BY_PHONE = "select cust_id from customers where phone=?";

	public static final String CUSTOMER_INSERT = "INSERT into customers(name,phone) VALUES(?,?)";

	public static final String GET_ALL_CUSTOMERS = "SELECT * FROM customers";

	public static final String GET_CUST_BY_ID = "SELECT * FROM customers WHERE cust_id = ?";

	public static final String CUSTOMERS = "SELECT cust_id, name, phone FROM customers WHERE (:hasCustId = false OR cust_id = :custId) AND (:hasName = false OR name = :name) AND (:hasPhone = false OR phone = :phone)";

	// Waiters

	public static final String WAITER_UPDATE_AVAILABILITY = "update waiters set availability=? where wtr_id=?";

	public static final String WAITER_LOG_INSERT = "insert into waiters_log values(?,?,?)";

	public static final String COUNT_WAITER_ASSIGNED_ORDERS = "select count(*) from orders where wtr_id=? and status='Pending'";

	public static final String WAITER_SELECT_BY_ID = "select * from waiters where wtr_id=?";

	public static final String WAITER_INSERT = "insert into waiters(emp_id,availability) values(?,'Available')";

	public static final String WAITER_SELECT_AVAILABLE = "select e.name,w.* from waiters w join employees e on w.emp_id=e.emp_id where w.availability='Available' and e.status='Active' and e.designation='Waiter'";

	public static final String WAITER_DELETE_BY_EMP_ID = "delete from waiters where emp_id=?";

	public static final String WAITER_SELECT_BY_EMP_ID = "select * from waiters where emp_id=?";

	public static final String WAITERS = "SELECT wtr_id, emp_id, availability FROM waiters WHERE (:hasWaiterId = false OR wtr_id = :waiterId) AND (:hasEmployeeId = false OR emp_id = :employeeId) AND (:hasAvailability = false OR availability = :availability)";

	public static final String COUNT_ORDERS = "SELECT COUNT(*) FROM orders WHERE wtr_id = :waiterId and status='Pending'";

	// Order Details

	public static final String GET_ALL_ORDERDETAILS = "SELECT * FROM order_details";

	public static final String ORDER_DETAILS_INSERT = "insert into order_details(ord_id,item_id,quantity,price) values(?,?,?,?)";

	public static final String ORDER_DETAILS_SELECT = "select * from order_details";

	public static final String ORDER_DETAILS = "SELECT ord_details_id, ord_id, item_id, quantity, price FROM order_details WHERE (:hasOrderDetailsId = false OR ord_details_id = :orderDetailsId) AND (:hasOrderId = false OR ord_id = :orderId) AND (:hasItemId = false OR item_id = :itemId) AND (:hasQuantity = false OR quantity = :quantity) AND (:hasPrice = false OR price = :price)";

	// Items

	public static final String ITEM_INSERT = "INSERT INTO items (name, image, description,price, category, availability,status) VALUES (?, ?,?, ?, ?, ?,?)";

	public static final String ITEM_COUNT_SELECT_BY_NAME = "SELECT COUNT(*) FROM items WHERE  name = ? AND status = 'Active' ";

	public static final String ITEM_SELECT_BY_NAME = "SELECT * FROM items WHERE  name = ? AND status = 'Active' ";

	public static final String ITEM_SELECT_BY_ID = "SELECT * FROM items WHERE item_id = ? AND status = 'Active'";

	public static final String ITEM_UPDATE = "UPDATE items SET name = ?, image = ?, description = ?, category = ?,price = ?  WHERE item_id = ? AND status = 'Active'";

	public static final String UPDATE_AVAILABILITY = "UPDATE items SET availability = ? WHERE item_id = ? AND status = 'Active'";

	public static final String ITEM_SELECT_ALL = "SELECT * FROM items WHERE status = 'Active'";

	public static final String ITEM_DELETE = "UPDATE items SET status = 'Inactive' WHERE item_id = ?";

	public static final String Items = "SELECT item_id, name, image, description, price, category, availability, status FROM items WHERE (:hasItemId = false OR item_id = :id) AND (:hasName = false OR name LIKE :name) AND (:hasImage = false OR image = :imageUrl) AND (:hasDescription = false OR description LIKE :description) AND (:hasPrice = false OR price = :price) AND (:hasStatuses = false OR status IN (:statuses)) AND (:hasAvailability = false OR availability IN (:availability)) AND (:hasCategories = false OR category IN (:categories))";

	public static final String SEARCH_ITEMS = "SELECT item_id, name, image, description,price , category, availability, status FROM items WHERE ((:search IS NULL OR LOWER(name) LIKE LOWER(CONCAT('%', :search, '%'))) OR (:search IS NULL OR LOWER(description) LIKE LOWER(CONCAT('%', :search, '%')) )) AND (:category IS NULL OR :category = 'All' OR category = :category) AND ( status = 'Active')";
	// Orders

	public static final String ORDER_INSERT = "INSERT INTO orders (cust_id, wtr_id, ord_date, amount, status) VALUES (?, ?, ?, ?, ?)";

	public static final String UPDATE_ORDER_AMOUNT = "UPDATE orders SET amount = ? WHERE ord_id = ?";

	public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE ord_id = ?";

	public static final String ORDER_BY_ID = "SELECT * FROM orders WHERE ord_id = ?";

	public static final String ALL_ORDERS = "SELECT * FROM orders";

	public static final String GET_ORDERID = "SELECT ord_id FROM orders WHERE cust_id = ? AND wtr_id = ? and status='Pending'";

	public static final String ORDERS_BY_CATEGORY = "SELECT * FROM orders WHERE status = ?";

	public static final String ORDER_LOG = "INSERT INTO orders_log (ord_id, cust_id, wtr_id, ord_date, amount, status) VALUES (?, ?, ?, ?, ?, ?)";

	public static final String ORDERS = "SELECT ord_id, cust_id, wtr_id, ord_date, amount, status FROM orders WHERE (:hasOrderId = false OR ord_id = :orderId) AND (:hasCustomerId = false OR cust_id = :customerId) AND (:hasWaiterId = false OR wtr_id = :waiterId) AND (:hasStartDate = false OR ord_date >= :startDate) AND (:hasEndDate = false OR ord_date <= :endDate) AND (:hasAmount = false OR amount = :amount) AND (:hasStatuses = false OR status IN (:statuses))";

}
