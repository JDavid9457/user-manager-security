package code.wolf.usermanagersecurity.infrastructure.persistence.transformers;

import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.config.security.jwt.JwtService;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.ResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {


    public static UserEntity toUserEntitySave(User user) {
        List<PhoneEntity> listPhones = UserMapper.toUserEntity(user);
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .username(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(listPhones)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(user.getToken())
                .isActive(true)
                .role(Role.USER)
                .build();
    }

    public static UserEntity toUserEntityUpdate(User user) {
        List<PhoneEntity> listPhones = UserMapper.toUserEntity(user);
        return UserEntity.builder()
                .username(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(listPhones)
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .role(Role.USER)
                .build();
    }

    public static List<PhoneEntity> toUserEntity(User user) {
        return user.getPhones()
                .stream().map(p -> PhoneEntity.builder()
                        .number(p.getNumber())
                        .cityCode(p.getCityCode())
                        .contryCode(p.getContryCode())
                        .build())
                .collect(Collectors.toList());
    }

    public static UserResponseDTO toUserResponseDTO(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isactive(user.isActive())
                .build();
    }

    public static UserResponseDTO mapUserToDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .lastLogin(user.getLastLogin())
                .isactive(user.getIsActive())
                .build();
    }
}


