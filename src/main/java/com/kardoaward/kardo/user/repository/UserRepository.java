package com.kardoaward.kardo.user.repository;

import com.kardoaward.kardo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Boolean existsUserByNameAndSurnameAndBirthday(String name, String surname, LocalDate birthday);

    @Override
    Optional<User> findById(Long aLong);

    User findByEmail(String email);
}
