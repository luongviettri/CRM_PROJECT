package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    public List<RoleModel> GetAllRoles() {
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.getAllRoles();
    }

    public boolean deleteRoleById(int id) {
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.deleteRoleById(id) >= 1;
    }

    public boolean addRole(String name, String description) {
        RoleRepository roleRepository = new RoleRepository();
        return roleRepository.addNewRole(name, description) >= 1;
    }
}
