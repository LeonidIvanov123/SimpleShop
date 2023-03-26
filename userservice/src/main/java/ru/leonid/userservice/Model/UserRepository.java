package ru.leonid.userservice.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM USERS u WHERE u.username= :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);
}
