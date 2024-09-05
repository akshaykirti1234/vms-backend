package tech.csm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tech.csm.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("from User where userEmail = :userEmail and deletedFlag = false")
    User findUserByEmail(String userEmail);

    @Query("from User where userEmail = :userEmail and password = :password and deletedFlag = false")
    User findUserByEmailandPasswod(String userEmail, String password);

    @Query("from User where userRole = 'user' and deletedFlag = false order by createdOn desc")
    List<User> getAllCitizen();

    @Modifying
    @Query("update User set permit = true where userId = :userId")
    Integer permitCitizen(String userId);

}
