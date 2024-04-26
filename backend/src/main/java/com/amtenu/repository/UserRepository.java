package com.amtenu.repository;

import com.amtenu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User  findByEmail(String email);

}
