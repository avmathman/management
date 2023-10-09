package com.invent.management.domain.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.invent.management.domain.exception.DuplicateItemException;
import com.invent.management.domain.exception.ItemNotFoundException;
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

        this.checkRole(roleModel.getName());

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
        this.repository
                .findById(roleModel.getId())
                .orElseThrow(() -> new ItemNotFoundException("Role with given id=" + roleModel.getId() + " does not exist!"));

        roleModel.setName(roleModel.getName().toUpperCase());
        RoleEntity current = this.repository.getReferenceById(roleModel.getId());

        current.setName(roleModel.getName());

        return this.mapper.entityToModel(this.repository.save(current));
    }

    @Override
    public void deleteRole(Long roleId) {
        this.repository
                .findById(roleId)
                .orElseThrow(() -> new ItemNotFoundException("Role with given id=" + roleId + " does not exist!"));

        this.repository.deleteById(roleId);
    }

    @Override
    public RoleModel getRole(Long roleId) {
        return this.mapper.entityToModel(this.repository
                .findById(roleId)
                .orElseThrow(() -> new ItemNotFoundException("Role with given id=" + roleId + " does not exist!")));
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return this.mapper.entitiesToModels(this.repository.findAll());
    }

    @Override
    public void checkMissingRoles(List<String> roles) {
        List<String> existing = this.getAllRoles()
                .stream()
                .map(item -> item.getName())
                .collect(Collectors.toList());

        List<String> missing = new ArrayList<>();

        for (String role: roles) {
            if (!existing.contains(role)) {
                missing.add(role);
            }
        }

        if (missing.size() > 0) {
            throw new ItemNotFoundException("There no roles in database. Incorrect roles: " + missing);
        }
    }

    @Override
    public void checkRole(String role) {
        List<String> existing = this.getAllRoles()
                .stream()
                .map(item -> item.getName())
                .collect(Collectors.toList());

        if (existing.contains(role)) {
            throw new DuplicateItemException("Given role already exist in database. role = " + role);
        }


    }
}
