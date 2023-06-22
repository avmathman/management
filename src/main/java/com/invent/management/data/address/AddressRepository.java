package com.invent.management.data.address;

import com.invent.management.data.user.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<UserEntity, Long> {}
