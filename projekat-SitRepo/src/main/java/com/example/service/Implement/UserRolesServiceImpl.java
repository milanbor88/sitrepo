package com.example.service.Implement;

import com.example.model.UserRoles;
import com.example.repository.UserRolesRepository;
import com.example.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userRolesService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserRolesServiceImpl implements UserRolesService {
    @Autowired
    UserRolesRepository rolesRep;

    @Override
    public UserRoles saveUserRoles(UserRoles userRoles) {
        return rolesRep.save(userRoles);
    }
}
