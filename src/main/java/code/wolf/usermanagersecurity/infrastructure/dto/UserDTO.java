package code.wolf.usermanagersecurity.infrastructure.dto;

import code.wolf.usermanagersecurity.infrastructure.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<PhoneEntity> phones;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
    private Role role;
}
