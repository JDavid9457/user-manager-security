package code.wolf.usermanagersecurity.infrastructure.dto.request;

import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
