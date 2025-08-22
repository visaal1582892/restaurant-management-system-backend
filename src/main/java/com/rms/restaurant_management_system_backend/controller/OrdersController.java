package com.rms.restaurant_management_system_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.constant.OrderStatus;
import com.rms.restaurant_management_system_backend.domain.Orders;
import com.rms.restaurant_management_system_backend.service.OrdersService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@RestController
@RequestMapping("api/staff/orders")
public class OrdersController {

	private final OrdersService ordersService;

	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	@PostMapping("/addOrder")
	public ResponseEntity<CustomResponse> addOrder(@RequestBody Map<String, Object> payload) {
		try {
			String name = (String) payload.get("name");
			String phone = (String) payload.get("phone");
			int waiterId = Integer.valueOf(payload.get("waiterId").toString());
			int id = ordersService.addOrder(name, phone, waiterId);
			return ResponseEntity.ok(new CustomResponse(true, "Order created successfully", id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new CustomResponse(false, e.getMessage(), null));
		}
	}

	@PutMapping("/updateAmount")
	public ResponseEntity<CustomResponse> updateAmount(@RequestBody Orders order) {
		ordersService.updateAmount(order);
		return ResponseEntity.ok(new CustomResponse(true, "Amount updated successfully", order));
	}

	@PutMapping("/updateStatus")
	public ResponseEntity<CustomResponse> updateStatus(@RequestParam int orderId, @RequestParam OrderStatus status) {
		ordersService.updateStatus(orderId, status);
		return ResponseEntity.ok(new CustomResponse(true, "Order status updated successfully", status));
	}

	@GetMapping("/allOrders")
	public ResponseEntity<CustomResponse> getAllOrders() {
		List<Orders> orders = ordersService.getAllOrders();
		return ResponseEntity.ok(new CustomResponse(true, "Orders fetched successfully", orders));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> getOrderById(@PathVariable int id) {
		Orders order = ordersService.getOrderById(id);
		return ResponseEntity.ok(new CustomResponse(true, "Order fetched successfully", order));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<CustomResponse> getOrdersByCategory(@PathVariable String category) {
		List<Orders> pending = ordersService.getOrdersByCategory(category);
		return ResponseEntity.ok(new CustomResponse(true, category + " orders fetched successfully", pending));
	}

}
