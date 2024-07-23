package code.wolf.usermanagersecurity.infrastructure.dto.request;

import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailRequestDTO {
    private String id;
    private String username;
    private String email;
    private List<PhoneDTO> phones;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;
    private boolean isActive;
}
