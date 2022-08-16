package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    void addRole(Role role);

    List<Role> listRoles();

    Role getRoleByID(Long id);

    Set<Role> findRolesByName(String roleName);
}
