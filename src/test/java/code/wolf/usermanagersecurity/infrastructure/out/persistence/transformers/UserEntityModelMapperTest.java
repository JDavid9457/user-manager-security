package code.wolf.usermanagersecurity.infrastructure.out.persistence.transformers;

import code.wolf.usermanagersecurity.domain.model.Phone;
import code.wolf.usermanagersecurity.domain.model.User;
import code.wolf.usermanagersecurity.infrastructure.dto.request.UserDetailRequestDTO;
import code.wolf.usermanagersecurity.infrastructure.dto.response.UserResponseDTO;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.PhoneEntity;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.Role;
import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserEntityModelMapperTest {

    @Test
    void testToUserEntityToUser() {
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
                .token("eyJhbGciOiJIUzI1NiJ9")
                .isActive(true)
                .build();

        User user = UserEntityModelMapper.toUserEntityToUser(userEntity);

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
    }

    @Test
    void testUserToUserEntityForSave() {
        User user = User.builder()
                .name("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .password("hunter2A*")
                .phones(List.of(Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .token("eyJhbGciOiJIUzI1NiJ9")
                .build();

        UserEntity userEntity = UserEntityModelMapper.UserToUserEntityForSave(user);

        assertNotNull(userEntity);
        assertEquals(user.getName(), userEntity.getUsername());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getPassword(), userEntity.getPassword());
        assertNotNull(userEntity.getPhones());
        assertEquals(1, userEntity.getPhones().size());
        assertEquals(user.getPhones().get(0).getNumber(), userEntity.getPhones().get(0).getNumber());
        assertNotNull(userEntity.getCreatedAt());
        assertNotNull(userEntity.getModifiedAt());
        assertNotNull(userEntity.getLastLogin());
        assertEquals(user.getToken(), userEntity.getToken());
        assertTrue(userEntity.isActive());
        assertEquals(Role.USER, userEntity.getRole());
    }

    @Test
    void testUserPhonesToPhoneEntities() {
        User user = User.builder()
                .phones(List.of(Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .build();

        List<PhoneEntity> phoneEntities = UserEntityModelMapper.userPhonesToPhoneEntities(user);

        assertNotNull(phoneEntities);
        assertEquals(1, phoneEntities.size());
        assertEquals(user.getPhones().get(0).getNumber(), phoneEntities.get(0).getNumber());
    }

    @Test
    void testToPhoneEntitiesToPhones() {
        List<PhoneEntity> phoneEntities = List.of(PhoneEntity.builder()
                .number("123456789")
                .cityCode("01")
                .contryCode("502")
                .build());

        List<Phone> phones = UserEntityModelMapper.toPhoneEntitiesToPhones(phoneEntities);

        assertNotNull(phones);
        assertEquals(1, phones.size());
        assertEquals(phoneEntities.get(0).getNumber(), phones.get(0).getNumber());
    }

    @Test
    void testUserToDetailListDTO() {
        User user = User.builder()
                .name("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .phones(List.of(Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .build();

        List<UserDetailRequestDTO> userDetailList = UserEntityModelMapper.userToDetailListDTO(List.of(user));

        assertNotNull(userDetailList);
        assertEquals(1, userDetailList.size());
        assertEquals(user.getName(), userDetailList.get(0).getUsername());
        assertEquals(user.getEmail(), userDetailList.get(0).getEmail());
        assertNotNull(userDetailList.get(0).getPhones());
        assertEquals(user.getPhones().get(0).getNumber(), userDetailList.get(0).getPhones().get(0).getNumber());
        assertEquals(user.getCreatedAt(), userDetailList.get(0).getCreatedAt());
        assertEquals(user.getModifiedAt(), userDetailList.get(0).getModifiedAt());
        assertEquals(user.getLastLogin(), userDetailList.get(0).getLastLogin());
        assertTrue(userDetailList.get(0).isActive());
    }

    @Test
    void testMapUserToDTO() {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("eyJhbGciOiJIUzI1NiJ9")
                .isActive(true)
                .build();

        UserResponseDTO userResponseDTO = UserEntityModelMapper.mapUserToDTO(user);

        assertNotNull(userResponseDTO);
        assertEquals(user.getId(), userResponseDTO.getId());
        assertEquals(user.getCreatedAt(), userResponseDTO.getCreatedAt());
        assertEquals(user.getModifiedAt(), userResponseDTO.getModifiedAt());
        assertEquals(user.getLastLogin(), userResponseDTO.getLastLogin());
        assertEquals(user.getToken(), userResponseDTO.getToken());
        assertEquals(user.getIsActive(), userResponseDTO.getIsactive());
    }

    @Test
    void testUserToDetailDTO() {
        User user = User.builder()
                .name("ricardo Rodriguez")
                .email("ricardo@riguez.org")
                .phones(List.of(Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .contryCode("502")
                        .build()))
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .build();

        UserDetailRequestDTO userDetailDTO = UserEntityModelMapper.userToDetailDTO(user);

        assertNotNull(userDetailDTO);
        assertEquals(user.getName(), userDetailDTO.getUsername());
        assertEquals(user.getEmail(), userDetailDTO.getEmail());
        assertNotNull(userDetailDTO.getPhones());
        assertEquals(user.getPhones().get(0).getNumber(), userDetailDTO.getPhones().get(0).getNumber());
        assertEquals(user.getCreatedAt(), userDetailDTO.getCreatedAt());
        assertEquals(user.getModifiedAt(), userDetailDTO.getModifiedAt());
        assertEquals(user.getLastLogin(), userDetailDTO.getLastLogin());
        assertTrue(userDetailDTO.isActive());
    }
}
