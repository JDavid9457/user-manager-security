package code.wolf.usermanagersecurity.infrastructure.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequestDTO {
    private String email;
    private String password;
}
