package com.invent.management.domain.role;

import com.invent.management.data.role.RoleEntity;
import com.invent.management.data.role.RoleRepository;
import com.invent.management.domain.user.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleServiceImplTest {

    private final String ROLE_TEST = "TEST";

    @Mock
    private RoleRepository roleRepository;
    private RoleModelMapper roleModelMapper;
    private RoleService service;

    private RoleEntity roleEntity;

    @BeforeEach
    public void setUp() {
        roleRepository = mock(RoleRepository.class);
        roleModelMapper = Mappers.getMapper(RoleModelMapper.class);

        service = new RoleServiceImpl(roleRepository, roleModelMapper);

        roleEntity = this.createRoleEntity();
    }

    @Test
    public void getRole_retrievesRoleModel_returnsRoleModel() {

        //Assign
        RoleModel roleModel = this.roleModelMapper.entityToModel(this.roleEntity);
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(this.roleEntity));

        //Act
        RoleModel current = service.getRole(roleModel.getId());

        //Assert
        assertThat(current).isNotNull();
        assertThat(current.getId()).isNotNull();
        assertThat(current.getName()).isEqualTo(roleModel.getName());
    }

    private RoleEntity createRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName(ROLE_TEST);

        return roleEntity;
    }
}
