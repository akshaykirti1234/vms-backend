package tech.csm.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterFormDto {
    private String userName;
    private String userPhone;
    private String userEmail;
    private MultipartFile file;
}
