package itus._2014.king.springvuejsblog.repositories;

import itus._2014.king.springvuejsblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
