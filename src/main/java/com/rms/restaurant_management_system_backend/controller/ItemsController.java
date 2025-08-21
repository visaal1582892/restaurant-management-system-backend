package com.rms.restaurant_management_system_backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rms.restaurant_management_system_backend.constant.ItemAvailability;
import com.rms.restaurant_management_system_backend.constant.ItemStatus;
import com.rms.restaurant_management_system_backend.domain.Items;
import com.rms.restaurant_management_system_backend.exception.InvalidDataException;
import com.rms.restaurant_management_system_backend.service.ItemsService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/items")
public class ItemsController {

	private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
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
				fileUrl = "/uploads/" + fileName;
			} catch (IOException e) {
				throw new InvalidDataException("image storing error");
			}

		} else {
			fileUrl = "";
		}

		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", fileUrl);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/addItem")
	public ResponseEntity<CustomResponse> addItem(@Valid @RequestBody Items items) {

		if (items.getImageUrl() == null || items.getImageUrl().isBlank()) {
			items.setImageUrl("/uploads/defult/profile123.png");
		}

		itemsService.saveItem(items);
		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", null);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> getItemById(@PathVariable int id) {

		Items item = itemsService.getItemById(id);

		CustomResponse response = new CustomResponse(true, "Item Details Retrived Successfully", item);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/updateItem/{id}")
	public ResponseEntity<CustomResponse> updateItem(@PathVariable int id, @Valid @RequestBody Items items) {

		Items existingItem = itemsService.getItemById(id);

//		if (existingItem == null) {
//			return ResponseEntity.badRequest().body(new CustomResponse(false, "Item not found", null));
//		}

//		handleFileUpload(items, file);

		// Update only fields that are allowed
		existingItem.setName(items.getName());
		existingItem.setDescription(items.getDescription());
		existingItem.setPrice(items.getPrice());
		existingItem.setCategory(items.getCategory());
		existingItem.setAvailable(items.getAvailable());
		if (items.getImageUrl() != null) {
			existingItem.setImageUrl(items.getImageUrl());
		}

		itemsService.saveItem(existingItem);
		return ResponseEntity.ok(new CustomResponse(true, "Item Updated Successfully", existingItem));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomResponse> deleteItem(@PathVariable int id) {
		Items item = itemsService.getItemById(id);
		if (item == null) {
			return ResponseEntity.badRequest().body(new CustomResponse(false, "Item not found", null));
		}
		item.setStatus(ItemStatus.INACTIVE);
		itemsService.saveItem(item);
		return ResponseEntity.ok(new CustomResponse(true, "Item marked as INACTIVE", item));
	}

	@PatchMapping("/availability/{id}")
	public ResponseEntity<CustomResponse> changeAvailability(@PathVariable int id,
			@RequestParam ItemAvailability availability) {
		Items item = itemsService.getItemById(id);
		if (item == null) {
			return ResponseEntity.badRequest().body(new CustomResponse(false, "Item not found", null));
		}
		item.setAvailable(availability);
		itemsService.saveItem(item);
		return ResponseEntity.ok(new CustomResponse(true, "Item availability updated", item));
	}

	@GetMapping("/all")
	public ResponseEntity<List<Items>> getAllItems() {
		return ResponseEntity.ok(itemsService.getAllItems());
	}

//	private String getImageurl(String url) {
//		return "http://localhost:8080" + url;
//	}

}
