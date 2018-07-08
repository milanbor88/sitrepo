package com.example.service.Implement;

import com.example.model.Role;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MySuserDetailsServiceIpml implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            com.example.model.User user = userService.findUserEmail(username);

            if(user == null){
                throw  new UsernameNotFoundException("Ne postoji user" + username);
            }
            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, getAUtorities(user.getUserRoles().get(0).getRole()));
            //org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getActive(), true, true, true, getAUtorities(user.getUserRoles().get(0).getRole()));
            return userDetails;
        }catch (final Exception e){
            throw new RuntimeException();
        }
    }

    private final Collection<? extends GrantedAuthority> getAUtorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role.getRole()));

        return  authorities;

    }
}
