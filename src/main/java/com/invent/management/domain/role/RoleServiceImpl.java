package com.invent.management.domain.role;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.invent.management.data.role.RoleEntity;
import com.invent.management.data.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleModelMapper mapper;

    @Autowired
    public RoleServiceImpl(
        RoleRepository repository,
        RoleModelMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RoleModel createRole(RoleModel roleModel) {
        try {
            roleModel.setName(roleModel.getName().toUpperCase());
            return this.mapper.entityToModel(
                    this.repository.save(this.mapper.modelToEntity(roleModel)));
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public RoleModel updateRole(RoleModel roleModel) {
        roleModel.setName(roleModel.getName().toUpperCase());
        RoleEntity current = this.repository.getReferenceById(roleModel.getId());

        current.setName(roleModel.getName());

        return this.mapper.entityToModel(this.repository.save(current));
    }

    @Override
    public void deleteRole(Long roleId) {
        this.repository.deleteById(roleId);
    }

    @Override
    public RoleModel getRole(Long roleId) {
        return this.mapper.entityToModel(this.repository.findById(roleId).orElse(null));
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return this.mapper.entitiesToModels(this.repository.findAll());
    }
    
}
