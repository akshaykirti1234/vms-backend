package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.csm.dto.RegisterFormDto;
import tech.csm.dto.UserDto;
import tech.csm.entity.User;
import tech.csm.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/validateLogin")
    public ResponseEntity<User> validateLogin(@RequestBody UserDto userDto) {

        User user = userService.findUserByEmail(userDto.getUserEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User validateUser = userService.validateLogin(userDto);
        if (validateUser != null) {
            return new ResponseEntity<>(validateUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/saveRegisterForm")
    public ResponseEntity<?> saveRegisterForm(@ModelAttribute RegisterFormDto registerFormDto) {
        User user = new User();
        User savedUser = userService.saveRegisterForm(registerFormDto);
        if (savedUser != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}