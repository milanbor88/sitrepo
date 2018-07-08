package com.example.repository;

import com.example.model.Role;
import com.example.model.User;
import com.example.model.UserRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPageRepositroy extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
}
