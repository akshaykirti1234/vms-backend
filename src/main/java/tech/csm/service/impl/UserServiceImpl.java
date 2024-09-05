package tech.csm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.csm.dto.RegisterFormDto;
import tech.csm.dto.UserDto;
import tech.csm.entity.User;
import tech.csm.repository.UserRepo;
import tech.csm.service.UserService;
import tech.csm.util.FileHandler;
import tech.csm.util.PasswordGenerator;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FileHandler fileHandler;
    @Autowired
    private PasswordGenerator passwordGenerator;

    @Override
    public User findUserByEmail(String userEmail) {
        return userRepo.findUserByEmail(userEmail);
    }

    @Override
    public User validateLogin(UserDto userDto) {
        String userEmail = userDto.getUserEmail();
        String password = userDto.getPassword();
        return userRepo.findUserByEmailandPasswod(userEmail, password);
    }

    @Override
    public User saveRegisterForm(RegisterFormDto registerFormDto) {
        try {
            User user = new User();
            user.setUserName(registerFormDto.getUserName());
            user.setUserEmail(registerFormDto.getUserEmail());
            user.setUserPhone(registerFormDto.getUserPhone());
            user.setUserRole("user");
            user.setPassword(passwordGenerator.generatePassword(user.getUserName(), user.getUserPhone()));
            String filePath = fileHandler.saveFile(registerFormDto.getFile());
            user.setPhotoPath(filePath);
            System.out.println(user);
            User save = userRepo.save(user);
            return user;
        } catch (IOException e) {
            // Handle exception (log it, rethrow it, etc.)
            throw new RuntimeException("Failed to save file", e);
        } catch (Exception e) {
            // Handle other exceptions if needed
            throw new RuntimeException("Failed to process register form", e);
        }
    }

    @Override
    public List<User> getAllCitizen() {
        return userRepo.getAllCitizen();
    }

    @Override
    @Transactional
    public Integer permitCitizen(String userId) {
        return userRepo.permitCitizen(userId);
    }
	

}
