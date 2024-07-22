package code.wolf.usermanagersecurity.infrastructure.dto.request;

import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
