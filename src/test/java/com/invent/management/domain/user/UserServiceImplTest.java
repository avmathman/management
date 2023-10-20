package com.invent.management.domain.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.invent.management.domain.role.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.invent.management.data.user.UserEntity;
import com.invent.management.data.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled
public class UserServiceImplTest {

    private final String ROLE_TEST = "TEST";

    @Mock
    private UserRepository userRepository;

    private UserModelMapper userModelMapper;

    @Mock
    private RoleService roleService;

    private UserService service;

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        cleanAll();
        userRepository = mock(UserRepository.class);
        userModelMapper = Mappers.getMapper(UserModelMapper.class);
        roleService = mock(RoleService.class);

        service = new UserServiceImpl(userRepository, userModelMapper, roleService);

        userEntity = this.createUserEntity(userEntity -> userEntity.setFirstname("First"));
    }
    
    @Test
    public void createUser_createsUserModel_returnsUserModel() {
        
        //Assign
        UserModel userModel = this.userModelMapper.entityToModel(this.userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(this.userEntity);
        doNothing().when(roleService).checkMissingRoles(userModel.getRoles());

        //Act
        UserModel current = service.createUser(userModel);

        //Assert
        assertThat(current).isNotNull();
        assertThat(current.getId()).isNotNull();
        assertThat(current.getFirstname()).isEqualTo(userModel.getFirstname());
        assertThat(current.getLastname()).isEqualTo(userModel.getLastname());
        assertThat(current.getEmail()).isEqualTo(userModel.getEmail());
        assertThat(current.getPassword()).isEqualTo(userModel.getPassword());
    }

    @Test
    public void updateUser_updatesUserModel_returnsUserModel() {
        
        //Assign
        UserModel userModel = this.userModelMapper.entityToModel(this.userEntity);
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(this.userEntity));
        when(userRepository.save(any(UserEntity.class))).thenReturn(this.userEntity);

        //Act
        UserModel current = service.updateUser(userModel);

        //Assert
        assertThat(current).isNotNull();
        assertThat(current.getId()).isNotNull();
        assertThat(current.getFirstname()).isEqualTo(userModel.getFirstname());
        assertThat(current.getLastname()).isEqualTo(userModel.getLastname());
        assertThat(current.getEmail()).isEqualTo(userModel.getEmail());
        assertThat(current.getPassword()).isEqualTo(userModel.getPassword());
    }

    @Test
    public void deleteUser_deletesUserInDB_returnsUserModel() {
        
        //Assign
        when(userRepository.findById(this.userEntity.getId())).thenReturn(Optional.ofNullable(this.userEntity));

        //Act
        service.deleteUser(this.userEntity.getId());
        UserEntity result = userRepository.getById(this.userEntity.getId());

        //Assert
        assertThat(result).isNull();
    }

    @Test
    public void getUser_retrievesUserModel_returnsUserModel() {
        
        //Assign
        UserModel userModel = this.userModelMapper.entityToModel(this.userEntity);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(this.userEntity));

        //Act
        UserModel current = service.getUser(userModel.getId());

        //Assert
        assertThat(current).isNotNull();
        assertThat(current.getId()).isNotNull();
        assertThat(current.getFirstname()).isEqualTo(userModel.getFirstname());
        assertThat(current.getLastname()).isEqualTo(userModel.getLastname());
        assertThat(current.getEmail()).isEqualTo(userModel.getEmail());
        assertThat(current.getPassword()).isEqualTo(userModel.getPassword());
    }

    @Test
    public void getAllUsers_retrievesListOfUserModel_returnsListUserModel() {
        
        //Assign
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(this.userEntity);
        this.userEntity.setId(2L);
        userEntities.add(this.userEntity);

        List<UserModel> userModels = this.userModelMapper.entitiesToModels(userEntities);
        when(userRepository.findAll()).thenReturn(userEntities);

        //Act
         List<UserModel> current = service.getAllUsers();

        //Assert
        assertThat(current.size()).isEqualTo(userModels.size());
        assertThat(current.get(0).getId()).isEqualTo(userModels.get(0).getId());
        assertThat(current.get(0).getFirstname()).isEqualTo(userModels.get(0).getFirstname());
        assertThat(current.get(0).getLastname()).isEqualTo(userModels.get(0).getLastname());
        assertThat(current.get(0).getEmail()).isEqualTo(userModels.get(0).getEmail());
    }

    private UserEntity createUserEntity(Consumer<UserEntity> userEntityModifier) {
        Timestamp timestamp = new Timestamp(1695279144833L);
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setLastname("Lastname");
        userEntity.setEmail("test@test.com");
        userEntity.setPassword("pass");
        userEntity.setEnabled(true);
        userEntity.setRoles(ROLE_TEST);
        userEntity.setCreatedAt(timestamp);
        userEntity.setModifiedAt(timestamp);

        userEntityModifier.accept(userEntity);

        return userEntity;
    }

    private void cleanAll() {
        this.userRepository.deleteAll();
    }
}
