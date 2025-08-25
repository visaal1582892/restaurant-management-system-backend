package com.rms.restaurant_management_system_backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemCategory;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.service.ItemsService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/staff/items")
public class ItemsController {

	private static final String UPLOAD_DIR = "uploads/";
	private final ItemsService itemsService;

	public ItemsController(ItemsService itemsService) {
		this.itemsService = itemsService;
	}

	@PostMapping("/itemImage")
	public ResponseEntity<CustomResponse> handleFileUpload(@RequestPart(value = "file") MultipartFile file) {
		String fileUrl;
		if (file != null && !file.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR, fileName);

			try {
				Files.write(filePath, file.getBytes());
				fileUrl = "http://localhost:8080/uploads/" + fileName;
			} catch (IOException e) {
				throw new InvalidDataException("image storing error");
			}

		} else {
			fileUrl = "";
		}
		System.out.println(fileUrl);
		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", fileUrl);
		System.out.println(response);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/addItem")
	public ResponseEntity<CustomResponse> addItem(@Valid @RequestBody Items items) {

		if (items.getImageUrl() == null || items.getImageUrl().isBlank()) {
			items.setImageUrl("http://localhost:8080/uploads/default/profile123.png");
		}

		Items item = itemsService.saveItem(items);
		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", item);
		return ResponseEntity.ok(response);

	}

	@GetMapping("getItem/{id}")
	public ResponseEntity<CustomResponse> getItemById(@PathVariable int id) {

		Items item = itemsService.getItemById(id);

		CustomResponse response = new CustomResponse(true, "Item Details Retrived Successfully", item);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/updateItem/{id}")
	public ResponseEntity<CustomResponse> updateItem(@PathVariable int id, @Valid @RequestBody Items items) {

		if (items.getImageUrl() == null || items.getImageUrl().isBlank()) {
			items.setImageUrl("http://localhost:8080/uploads/default/profile123.png");
		}

		Items item = itemsService.updateItem(id, items);
		CustomResponse response = new CustomResponse(true, "Item Updated Successfully", item);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/availability/{id}")
	public ResponseEntity<CustomResponse> changeAvailability(@PathVariable int id) {

		Items item = itemsService.changeAvailability(id);
		CustomResponse response = new CustomResponse(true, "Item availability updated", item);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/all")
	public ResponseEntity<CustomResponse> getAllItems() {
		List<Items> items = itemsService.getAllItems();
		CustomResponse response = new CustomResponse(true, "Items Retrived Successufully", items);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<CustomResponse> deleteItem(@PathVariable int id) {

		Items item = itemsService.deleteItem(id);
		CustomResponse response = new CustomResponse(true, "Item marked as INACTIVE", item);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/categories")
	public ResponseEntity<CustomResponse> getCategories() {
		List<String> categories = Stream.of(ItemCategory.values()).map(category -> category.getName()).toList();
		CustomResponse response = new CustomResponse(true, "Item categories Retrived successfully!", categories);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getAvailabilityOptions")
	public ResponseEntity<CustomResponse> getAvailabilityOptions() {
		List<String> availabilities = Stream.of(ItemAvailability.values()).map(avilable -> avilable.getName()).toList();
		CustomResponse response = new CustomResponse(true, "Items Availabilities Retrived successfully!",
				availabilities);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/availableItems")
	public ResponseEntity<CustomResponse> getAvailableItems() {
		List<Items> items = itemsService.getAllItems().stream().filter(item -> item.getAvailable().equals(ItemAvailability.AVAILABLE)).toList();
		CustomResponse response = new CustomResponse(true, "Items Retrived Successufully", items);
		return ResponseEntity.ok(response);
	}

}
