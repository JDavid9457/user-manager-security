package code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers;

import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserDetailRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserEntityModelMapper {

    public static User toUserEntityToUser(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(toPhoneEntitiesToPhones(userEntity.getPhones()))
                .createdAt(userEntity.getCreatedAt())
                .modifiedAt(userEntity.getModifiedAt())
                .lastLogin(userEntity.getLastLogin())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .build();
    }

    public static UserEntity UserToUserEntityForSave(User user) {
        List<PhoneEntity> listPhones = userPhonesToPhoneEntities(user);
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

    public static List<PhoneEntity> userPhonesToPhoneEntities(User user) {
        return user.getPhones()
                .stream()
                .map(p -> PhoneEntity.builder()
                        .number(p.getNumber())
                        .cityCode(p.getCityCode())
                        .contryCode(p.getContryCode())
                        .build())
                .collect(Collectors.toList());
    }

    static List<Phone> toPhoneEntitiesToPhones(List<PhoneEntity> phoneEntities){
        return phoneEntities.stream().map(phone -> Phone.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .contryCode(phone.getContryCode())
                .build()
        ).toList();
    }

    public static List<UserDetailRequestDTO> userToDetailListDTO(List<User> users) {
        return users.stream()
                .map(UserEntityModelMapper::userToDetailDTO)
                .collect(Collectors.toList());
    }



    public static UserResponseDTO mapUserToDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(user.getIsActive())
                .build();
    }


    public static UserDetailRequestDTO userToDetailDTO(User user) {
        return UserDetailRequestDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .phones(user.getPhones().stream()
                        .map(phone -> new PhoneDTO(phone.getNumber(), phone.getCityCode(), phone.getContryCode()))
                        .collect(Collectors.toList()))
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .lastLogin(user.getLastLogin())
                .isActive(user.getIsActive())
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

}
