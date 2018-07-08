package com.example.service.Implement;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserPageRepositroy;
import com.example.service.UserPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("userpageservice")
public class UserPageServiceImpl implements UserPageService {

    @Autowired
    UserPageRepositroy userPageRepositroy;


    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return userPageRepositroy.findAll(pageable);
    }
}
