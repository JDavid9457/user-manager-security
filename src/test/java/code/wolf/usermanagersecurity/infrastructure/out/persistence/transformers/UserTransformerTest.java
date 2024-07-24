package code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers;

import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.PhoneDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTransformerTest {
    @Test
    void testToEntityToUser() {
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .username("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(List.of(PhoneEntity.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("testToken")
                .isActive(true)
                .build();

        User user = UserTransformer.toEntityToUser(userEntity);

        assertNotNull(user);
        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getUsername(), user.getName());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getCreatedAt(), user.getCreatedAt());
        assertEquals(userEntity.getModifiedAt(), user.getModifiedAt());
        assertEquals(userEntity.getLastLogin(), user.getLastLogin());
        assertEquals(userEntity.getToken(), user.getToken());
        assertEquals(userEntity.isActive(), user.getIsActive());
        assertNotNull(user.getPhones());
        assertEquals(userEntity.getPhones().size(), user.getPhones().size());
        assertEquals(userEntity.getPhones().get(0).getNumber(), user.getPhones().get(0).getNumber());
    }

    @Test
    void testToEntityToPhones() {
        List<PhoneEntity> phoneEntities = List.of(PhoneEntity.builder()
                .number("123456789")
                .cityCode("01")
                .contryCode("502")
                .build());

        List<Phone> phones = UserTransformer.toEntityToPhones(phoneEntities);

        assertNotNull(phones);
        assertEquals(1, phones.size());
        assertEquals(phoneEntities.get(0).getNumber(), phones.get(0).getNumber());
        assertEquals(phoneEntities.get(0).getCityCode(), phones.get(0).getCityCode());
        assertEquals(phoneEntities.get(0).getContryCode(), phones.get(0).getContryCode());
    }

    @Test
    void testToToUser() {
        UserRequestDTO userDTO = UserRequestDTO.builder()
                .username("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(List.of(PhoneDTO.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .build();

        User user = UserTransformer.toToUser(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getUsername(), user.getName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(userDTO.getPhones().size(), user.getPhones().size());
        assertEquals(userDTO.getPhones().get(0).getNumber(), user.getPhones().get(0).getNumber());
    }

    @Test
    void testPhoneToPhonesDTO() {
        List<PhoneDTO> phoneDTOs = List.of(PhoneDTO.builder()
                .number("123456789")
                .cityCode("01")
                .contryCode("502")
                .build());

        List<Phone> phones = UserTransformer.phoneToPhonesDTO(phoneDTOs);

        assertNotNull(phones);
        assertEquals(1, phones.size());
        assertEquals(phoneDTOs.get(0).getNumber(), phones.get(0).getNumber());
        assertEquals(phoneDTOs.get(0).getCityCode(), phones.get(0).getCityCode());
        assertEquals(phoneDTOs.get(0).getContryCode(), phones.get(0).getContryCode());
    }
}