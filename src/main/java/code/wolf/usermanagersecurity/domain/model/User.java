package code.wolf.usermanagersecurity.domain.model;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
    @Enumerated(EnumType.STRING)
    private Role role;

}
