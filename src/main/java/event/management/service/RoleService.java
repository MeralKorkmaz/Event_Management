package event.management.service;

import event.management.repository.RoleRepository;
import event.management.domain.user.Role;
import event.management.domain.user.RoleType;
import event.management.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(RoleType roleType){
        return roleRepository.findByEnumRoleEquals(roleType).orElseThrow(()-> new ResourceNotFoundException("RoleType does not exist"));
    }

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
}
