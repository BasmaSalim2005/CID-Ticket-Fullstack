package com.basma.Demo1.user;

import com.basma.Demo1.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByfirstname(String firstname);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findByRoleIn(Collection<Role> roles);


}
