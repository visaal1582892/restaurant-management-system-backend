package com.rms.restaurant_management_system_backend.domain;

import java.sql.Date;

import com.rms.restaurant_management_system_backend.constant.Designation;
import com.rms.restaurant_management_system_backend.constant.EmployeeAvailability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employees {

	private int empId;
	private String name;
	private String email;
	private String phone;
	private EmployeeAvailability availability;
	private Designation designation;
	private Date join_date;
	private Date leaving_date;

}
