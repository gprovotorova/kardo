package com.kardoaward.kardo.user.repository;

import com.kardoaward.kardo.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByIdIn(List<Long> id, Pageable page);

    Boolean existsByEmail(String email);

    @Override
    Optional<User> findById(Long aLong);

    UserDetails findByEmail(String email);
}
