package tech.csm.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String password;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userRole;
    private Integer createdBy;
    private Integer updatedBy;
}
