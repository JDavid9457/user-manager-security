package code.wolf.usermanagersecurity.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhoneEntity {
    @Id
    private String number;

    @Column(name ="city_code", nullable = false, length = 100)
    private String cityCode;

    @Column(name ="contry_code", nullable = false, length = 100)
    private String contryCode;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
}
