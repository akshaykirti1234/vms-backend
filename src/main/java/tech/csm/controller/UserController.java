package tech.csm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.csm.entity.User;
import tech.csm.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getAllCitizen")
	public ResponseEntity<?> getAllCitizen() {
		List<User> users = userService.getAllCitizen();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("permitCitizen/{userId}")
	public ResponseEntity<?> permitCitizen(@PathVariable String userId) {
		try {
			Integer affectedRows = userService.permitCitizen(userId);
			if (affectedRows > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
