package com.invent.management.data.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data repository for users.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
