package code.wolf.usermanagersecurity.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhoneDTO {
    private Long id;
    private String number;
    private String cityCode;
    private String contryCode;


}
