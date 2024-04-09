package org.zsell.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zsell.userservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}