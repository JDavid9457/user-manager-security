package code.wolf.usermanagersecurity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Phone {
    private Long id;
    private String number;
    private String cityCode;
    private String contryCode;
}
