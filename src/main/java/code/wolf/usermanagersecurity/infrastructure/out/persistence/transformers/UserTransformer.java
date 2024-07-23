package code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers;

import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserTransformer {
    public static User toEntityToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(toEntityToPhones(userEntity.getPhones()))
                .createdAt(userEntity.getCreatedAt())
                .modifiedAt(userEntity.getModifiedAt())
                .lastLogin(userEntity.getLastLogin())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .build();
    }

    static List<Phone> toEntityToPhones(List<PhoneEntity> phoneEntityList){
        return phoneEntityList.stream().map(phone -> Phone.builder()
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .contryCode(phone.getContryCode())
                .build()
        ).toList();
    }

    public static User toToUser(UserRequestDTO userDTO){
        return User.builder()
                .name(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phones(phoneToPhonesDTO(userDTO.getPhones()))
                .build();
    }

    static List<Phone> phoneToPhonesDTO(List<PhoneDTO> phonesDTO){
        return phonesDTO.stream().map(phoneDTO -> Phone.builder()
                .number(phoneDTO.getNumber())
                .cityCode(phoneDTO.getCityCode())
                .contryCode(phoneDTO.getContryCode())
                .build()
        ).collect(Collectors.toList());
    }

}
