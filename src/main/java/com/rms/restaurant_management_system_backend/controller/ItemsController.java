package com.rms.restaurant_management_system_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.restaurant_management_system_backend.service.ItemsService;
import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

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
	public ResponseEntity<CustomResponse> addItem() {

	}

}

//    @PostMapping("/upload")
//    public ResponseEntity<UserProfile> uploadImage(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("name") String name) {
//        try {
//            // Save file in static folder
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get(UPLOAD_DIR, fileName);
//            Files.write(filePath, file.getBytes());
//
//            // Create UserProfile object
//            UserProfile profile = new UserProfile();
//            profile.setName(name);
//            profile.setImageUrl("/uploads/" + fileName);
//
//            // Save in DB (with imageUrl)
//            UserProfile savedProfile = userProfileService.saveUserProfile(profile);
//
//            // Return saved profile JSON
//            return ResponseEntity.ok(savedProfile);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping
//    public List<UserProfile> getAllProfiles() {
//        return userProfileService.getAllProfiles();
//    }
//}
