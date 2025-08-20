package com.rms.restaurant_management_system_backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rms.restaurant_management_system_backend.domain.Items;
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

	@PostMapping("/addItem")
<<<<<<< HEAD
	public ResponseEntity<CustomResponse> addItem() {
		return null;
=======
	public ResponseEntity<CustomResponse> addItem(@Valid @RequestBody Items items,
			@RequestParam("file") MultipartFile file) {

		if (file != null) {
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_DIR, fileName);

			try {
				Files.write(filePath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			items.setImageUrl("/uploads/" + fileName);
		} else {
			items.setImageUrl("/uploads/defult/defult.jpg");
		}
		itemsService.saveItem(items);
		CustomResponse response = new CustomResponse(true, "Item Saved Succussfully", items);
		return ResponseEntity.ok(response);
>>>>>>> 98dcd7fef9659b8eead91735a1c99c4d267dc456

	}

	private String getImageurl(String url) {
		return "http://localhost:8080" + url;
	}

}
