package tech.csm.service;

import tech.csm.dto.RegisterFormDto;
import tech.csm.dto.UserDto;
import tech.csm.entity.User;

import java.util.List;

public interface UserService {
    User validateLogin(UserDto userDto);

    User findUserByEmail(String userEmail);

    User saveRegisterForm(RegisterFormDto registerFormDto);

    List<User> getAllCitizen();

    Integer permitCitizen(String userId);

}
