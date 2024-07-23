package code.wolf.usermanagersecurity.infrastructure.out.persistence.repositories;

import code.wolf.usermanagersecurity.infrastructure.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(String id);
}
