package com.invent.management.data.user;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    private final String ROLE_TEST = "TEST";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail_checkIfUserExists_returnsUser() {
        
        //Assign
        UserEntity current = this.createUser();

        //Act
        UserEntity found = userRepository
                            .findByEmail(current.getEmail())
                            .orElse(null);

        //Assert
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(current.getId());
        assertThat(found.getFirstname()).isEqualTo(current.getFirstname());
        assertThat(found.getLastname()).isEqualTo(current.getLastname());
        assertThat(found.getEmail()).isEqualTo(current.getEmail());
        assertThat(found.isEnabled()).isEqualTo(current.isEnabled());
    }

    @Test
    public void findByEmail_checkIfUserNotExists_returnsNull() {
        
        //Assign
        String incorrectEmail = "notemail@notemail.com";

        //Act
        UserEntity found = userRepository
                            .findByEmail(incorrectEmail)
                            .orElse(null);

        //Assert
        assertThat(found).isNull();
    }

    private UserEntity createUser() {
        Timestamp timestamp = new Timestamp(1695279144833L);
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstname("Test");
        userEntity.setLastname("Test");
        userEntity.setEmail("test@test.com");
        userEntity.setPassword("pass");
        userEntity.setEnabled(true);
        userEntity.setRoles(ROLE_TEST);
        userEntity.setCreatedAt(timestamp);
        userEntity.setModifiedAt(timestamp);

        return userRepository.save(userEntity);
    }
}
